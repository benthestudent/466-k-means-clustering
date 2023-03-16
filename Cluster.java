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
}
