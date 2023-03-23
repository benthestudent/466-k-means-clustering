import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MushroomClassification {

    public static void main(String args[]){
        ArrayList<Mushroom> mushrooms = new ArrayList<>();
        mushrooms = readData(-1);

        String distType = "all";
        ArrayList<Cluster> selectiveClusters = new ArrayList<>();
        for(int k = 2; k <= 10; k++){
            selectiveClusters = kMeans(mushrooms, k, 20, distType);
            System.out.printf("Evaluating Algorithm Using All Attributes and k=%d: \n", k);
            clusterEvaluation(selectiveClusters, distType);
            System.out.println();
        }





    }

    public static char emptyLineCheck(String line){
        if(line.equals("")){
            return ' ';
        }
        else{
            return line.charAt(0);
        }
    }

    public static ArrayList<Mushroom> readData(int numMushrooms){
        //Read data and create Mushroom objects for each mushroom
        //and put them into an arraylist
        String csvFile = "secondary_data.csv";
        String line = "";
        String delimiter = ";";
        ArrayList<Mushroom> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the header line and discard it
            br.readLine();

            // Read the remaining lines and store the data in a List of HashMaps
            while ((line = br.readLine()) != null) {
                //Make a mushroom object for each line
                
                String[] row = line.split(delimiter);

                // pass in the mushroom's classification, so we can examine the accuracy of the algorithm
                char classification = emptyLineCheck(row[0]);

                double capDiameter = Double.parseDouble(row[1]);
                char capShape = emptyLineCheck(row[2]);
                char capSurface = emptyLineCheck(row[3]);
                char capColor = emptyLineCheck(row[4]);
                char bruiseOrBleeds = emptyLineCheck(row[5]);
                char gillAttachment = emptyLineCheck(row[6]);
                char gillColor = emptyLineCheck(row[8]);
                double stemHeight = Double.parseDouble(row[9]);
                double stemWidth = Double.parseDouble(row[10]);
                char stemRoot = emptyLineCheck(row[11]);
                char stemSurface = emptyLineCheck(row[12]);
                char stemColor = emptyLineCheck(row[13]);
                char veilType = emptyLineCheck(row[14]);
                char veilColor = emptyLineCheck(row[15]);
                char ringNumber = emptyLineCheck(row[16]);
                char ringType = emptyLineCheck(row[17]);
                char sporePrintColor = emptyLineCheck(row[18]);
                char habitat = emptyLineCheck(row[19]);
                char season = emptyLineCheck(row[20]);
                Mushroom mushroom = new Mushroom(classification, capDiameter, capShape, capSurface, capColor, bruiseOrBleeds,
                gillAttachment, gillColor, stemHeight, stemWidth, stemRoot, stemSurface, stemColor, veilType, 
                veilColor, ringNumber, ringType, sporePrintColor, habitat, season);
                data.add(mushroom);
                if(numMushrooms > 0 && data.size() >= numMushrooms){
                    break;
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static ArrayList<Cluster> kMeans(ArrayList<Mushroom> mushrooms, int k, int maxIterations, String distType) {
        // Initialize k clusters with random centroids
        ArrayList<Cluster> clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int randomIndex = (int) (Math.random() * mushrooms.size());
            Mushroom randomMushroom = mushrooms.get(randomIndex);

            // make sure we don't select the same type of mushroom as a centroid twice
            boolean diff = false;
            while(!diff && !clusters.isEmpty()){
                for(Cluster c : clusters){
                    if(c.centroid.different(randomMushroom, distType)){
                        diff = true;
                    }
                }
                randomMushroom = mushrooms.get(randomIndex);
            }
            Cluster cluster = new Cluster(randomMushroom);
            clusters.add(cluster);
        }

        // Perform k-means iterations
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Assign each mushroom to the nearest cluster
            // clear the cluster's mushrooms for each iteration
            for(Cluster c : clusters){
                c.clusterMushrooms.clear();
            }
            for (Mushroom mushroom : mushrooms) {
                double minDistance = Double.MAX_VALUE;
                Cluster nearestCluster = null;
                for (Cluster cluster : clusters) {
                    double distance = mushroom.findDistance(cluster.centroid, distType);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestCluster = cluster;
                    }
                }
                nearestCluster.clusterMushrooms.add(mushroom);
            }

            // Update centroids of each cluster
            boolean converged = true;
            for (Cluster cluster : clusters) {
                if (!cluster.clusterMushrooms.isEmpty()) {
                    converged = converged & cluster.updateCentroid();
                }
            }
            if(converged){
                return clusters;
            }
        }

        return clusters;
    }


    public static void clusterEvaluation(ArrayList<Cluster> clusters, String distType){
        // this method prints various stats for the clusters produced by the algorithm including:
        //  -largeness: how many data points are in each cluster
        //  -density: the average distance between each point and the centroid
        //  -silhouette score: measures the overlap between clusters (see silhoetteScore method for more details)
        //  -ratio between number of edible and poisonous mushrooms
        //  -average silhoette score of clusters
        //  -entropy of all clusters
        //  -purity of all clusters

        HashMap<Integer, Double> silhouetteScores = silhouetteScore(clusters, distType);
        System.out.printf("%-9s|%-6s|%-9s|%-17s|%-10s\n", "Cluster", "Size","Density","Silhoette Score", "Ratio (P:E)");
        System.out.println("-----------------------------------------------------------------");
        for(int i = 0; i < clusters.size(); i++){
            Cluster c = clusters.get(i);
            double p_ratio = 0;
            double e_ratio = 0;
            for(Mushroom m : c.clusterMushrooms){
                if(m.getClassification() == 'p'){
                    p_ratio ++;
                }else{
                    e_ratio++;
                }
            }
            p_ratio = p_ratio / c.clusterMushrooms.size();
            e_ratio = e_ratio / c.clusterMushrooms.size();
            System.out.printf("%-10d%-7d%-10.4f%-14.4f%8.2f:%.2f\n", i, c.clusterMushrooms.size(), c.findDensity(distType), silhouetteScores.get(i), p_ratio, e_ratio);
        }
        //find average silhouette Score
        double avgSilScore = 0;
        int values = 0;
        for(Map.Entry<Integer, Double> m : silhouetteScores.entrySet()){
            avgSilScore += !Double.isNaN(m.getValue()) ? m.getValue() : 0;
            values = !Double.isNaN(m.getValue()) ? values + 1 : values;
        }
        avgSilScore /= values;
        System.out.println("Average Silhouette Score: " + avgSilScore);
        System.out.println("Entropy: " + findEntropy(clusters));
        System.out.println("Purity: " + findPurity(clusters));
    }


    public static HashMap<Integer, Double> silhouetteScore(ArrayList<Cluster> clusters, String distType){
//        a = Average intra-cluster distance of p with all the points in the same cluster . .
//        b = Average distance of p with any cluster that is not the one p belongs to. If there are N clusters, we get N-1 such averages. Take the minimum of these and call it b.
//                silhouette-score for p = (b - a)/max(b, a)
//        To get a score on a cluster level, average the scores of each point in the cluster.
        HashMap<Integer, Double> clusterScores = new HashMap<>();
        for(int i = 0; i < clusters.size(); i++){
            double a;
            double b;
            double silhoetteScore = 0;
            int numDistances;

            Cluster curr = clusters.get(i);
            for(Mushroom m1 : curr.clusterMushrooms){
                a = 0;
                b = 0;
                numDistances = 0;
                for(Cluster other : clusters){
                    if(!curr.equals(other)){
                        b += m1.findDistance(other.centroid, distType);
                    }
                }
                for(Mushroom m2 : curr.clusterMushrooms){
                    if(!m1.equals(m2)){
                        numDistances++;
                        a += m1.findDistance(m2, distType);
                    }
                }
                a = a / numDistances;
                b = b / (clusters.size() - 1);
                silhoetteScore += (b-a) / Math.max(b,a);
            }
            silhoetteScore = silhoetteScore / curr.clusterMushrooms.size();

            clusterScores.put(i, silhoetteScore);
        }
        return clusterScores;
    }

    public static double findEntropy(ArrayList<Cluster> clusters){
        double entropy = 0;
        int totalSize = 0;
        for(Cluster c : clusters){
            totalSize += c.clusterMushrooms.size();
        }
        for(Cluster c : clusters) {
            int n = c.clusterMushrooms.size();
            int numPoisonous = 0;
            int numEdible = 0;
            for (Mushroom m : c.clusterMushrooms) {
                if (m.getClassification() == 'p') {
                    numPoisonous++;
                } else {
                    numEdible++;
                }
            }
            double Eij = ((double) numEdible / n);
            double Pij = ((double) numPoisonous / n);
            double entropyC = Eij != 1 && Eij != 0 ? -Eij * log2(Eij) : 0;
            entropyC = Pij != 1 && Pij != 0 ? entropyC - Pij * log2(Pij) : entropyC;
            entropy += ((double) n / totalSize) * entropyC;
        }
        return entropy;
    }

    public static double findPurity(ArrayList<Cluster> clusters){
        int totalSize = 0;
        double purity = 0;
        for(Cluster c : clusters){
            totalSize += c.clusterMushrooms.size();
        }
        for(Cluster c : clusters) {
            int n = c.clusterMushrooms.size();
            if(n == 0){
                continue;
            }
            int numPoisonous = 0;
            int numEdible = 0;
            for (Mushroom m : c.clusterMushrooms) {
                if (m.getClassification() == 'p') {
                    numPoisonous++;
                } else {
                    numEdible++;
                }
            }
            double Eij = ((double) numEdible / n);
            double Pij = ((double) numPoisonous / n);
            double purityC = Math.max(Eij, Pij);
            purity += ((double) n / totalSize) * purityC;
        }
        return purity;
    }

    public static double log2(double a){
        return Math.log(a) / Math.log(2);
    }

}
