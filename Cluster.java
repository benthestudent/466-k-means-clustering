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



}
