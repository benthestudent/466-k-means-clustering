import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MushroomClassification {

    public static void main(String args[]){
        ArrayList<Mushroom> mushrooms = new ArrayList<>();
        mushrooms = readData();

        ArrayList<Cluster> clusters = new ArrayList<>();
        clusters = kMeans(mushrooms, 20, 10);

        System.out.println("Complete");

        clusterEvaluation(clusters);
    }

    public static char emptyLineCheck(String line){
        if(line.equals("")){
            return ' ';
        }
        else{
            return line.charAt(0);
        }
    }

    public static ArrayList<Mushroom> readData(){
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




            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static ArrayList<Cluster> kMeans(ArrayList<Mushroom> mushrooms, int k, int maxIterations) {
        // Initialize k clusters with random centroids
        ArrayList<Cluster> clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int randomIndex = (int) (Math.random() * mushrooms.size());
            Mushroom randomMushroom = mushrooms.get(randomIndex);
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
                    double distance = mushroom.findDistance(cluster.centroid);
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


    public static void clusterEvaluation(ArrayList<Cluster> clusters){
        // this method prints the ratio between poisonous and edible mushrooms in each cluster
        int i = 0;
        for(Cluster c : clusters){
            int p_ratio = 0;
            int e_ratio = 0;
            for(Mushroom m : c.clusterMushrooms){
                if(m.getClassification() == 'p'){
                    p_ratio ++;
                }else{
                    e_ratio++;
                }
            }
            System.out.printf("In cluster %d, there were %d poisonous mushrooms and %d edible mushrooms.\n", i, p_ratio, e_ratio);
            System.out.printf("\tPercent poisonous: %f vs Percent edible %f\n\n", (double) p_ratio / c.clusterMushrooms.size(), (double) e_ratio / c.clusterMushrooms.size());
            i++;
        }
    }

}
