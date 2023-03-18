import java.util.ArrayList;

public class Cluster {

    public ArrayList<Mushroom> clusterMushrooms;
    public Mushroom centroid;

    public Cluster(Mushroom centroid){
        this.centroid = centroid;
        clusterMushrooms = new ArrayList<>();
    }

    public Cluster(ArrayList<Mushroom> clusterMushrooms, Mushroom centroid){
        this.clusterMushrooms = clusterMushrooms;
        this.centroid = centroid;
    }

    public Cluster(){
        this.clusterMushrooms = new ArrayList<>();
    }

    public double findDensity() {
        double squareSum = 0.0;
        for(Mushroom shroom: clusterMushrooms){
            squareSum += shroom.findCombinedDistance(centroid) * shroom.findCombinedDistance(centroid);
        }
        return Math.sqrt(squareSum) / clusterMushrooms.size();
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
            for (Mushroom mushroom : mushrooms) {
                double minDistance = Double.MAX_VALUE;
                Cluster nearestCluster = null;
                for (Cluster cluster : clusters) {
                    double distance = mushroom.findCombinedDistance(cluster.centroid);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestCluster = cluster;
                    }
                }
                nearestCluster.clusterMushrooms.add(mushroom);
            }
    
            // Update centroids of each cluster
            for (Cluster cluster : clusters) {
                if (!cluster.clusterMushrooms.isEmpty()) {
                    Mushroom newCentroid = calculateCentroid(cluster.clusterMushrooms);
                    cluster.centroid = newCentroid;
                    cluster.clusterMushrooms.clear();
                }
            }
        }
    
        return clusters;
    }
    
    private static Mushroom calculateCentroid(ArrayList<Mushroom> mushrooms) {
        //get mushroom in center from list of mushrooms
    }
}
