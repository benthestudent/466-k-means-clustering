import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cluster {

    public ArrayList<Mushroom> clusterMushrooms;
    public Mushroom centroid;

    public Cluster(Mushroom centroid){
        // it's important that the centroid does not refer to any actual mushroom,
        // so we can use this copy method to create a clone of a mushroom in the dataset for our centroid
        this.centroid = centroid.copy();
        clusterMushrooms = new ArrayList<>();
    }

    public Cluster(ArrayList<Mushroom> clusterMushrooms, Mushroom centroid){
        this.clusterMushrooms = clusterMushrooms;
        this.centroid = centroid;
    }

    public Cluster(){
        this.clusterMushrooms = new ArrayList<>();
    }

    public double findDensity(String distanceType) {
        double squareSum = 0.0;
        for(Mushroom shroom: clusterMushrooms){
            double dist = shroom.findDistance(centroid, distanceType);
            dist *= dist;
            squareSum += dist;
        }
        return (double) Math.sqrt(squareSum) / clusterMushrooms.size();
    }




    public boolean updateCentroid(){
        if(this.clusterMushrooms.isEmpty()){
            return false;
        }

        // this variable will increment with each change made to the centroid or groups of changes (for numeric attr.)
        // and be used to determine if the centroid remain unchanged. If the centroid does not change, the method
        // will return true, so the algorithm knows if its converging.
        int changes = 0;

        // Using the k-modes approach, we need to update the nominal attributes of our centroid
        // based on the mushrooms in the cluster.
        for(String attribute : this.centroid.getNominalAttributes().keySet()){
            char mode = findMode(attribute);
            if(this.centroid.getNominalAttributes().get(attribute) != mode){
                changes ++;
            }
            this.centroid.setNominalAttribute(attribute, findMode(attribute));
        }

        // Next, for the three numeric attributes, we will find the average over all the mushrooms in
        // the cluster and set the value of the centroid's attributes to their respective means
        double capDiameterAvg = 0;
        double stemHeightAvg = 0;
        double stemWidthAvg = 0;
        int clusterSize = clusterMushrooms.size();
        for(Mushroom m : clusterMushrooms){
            capDiameterAvg += m.getCapDiameter();
            stemHeightAvg += m.getStemHeight();
            stemWidthAvg += m.getStemWidth();
        }
        capDiameterAvg = capDiameterAvg / clusterSize;
        stemHeightAvg = stemHeightAvg / clusterSize;
        stemWidthAvg = stemWidthAvg / clusterSize;

        if(this.centroid.getCapDiameter() != capDiameterAvg || this.centroid.getStemHeight() !=
            stemHeightAvg || this.centroid.getStemWidth() != stemWidthAvg){
            changes++;
        }

        this.centroid.setCapDiameter(capDiameterAvg);
        this.centroid.setStemHeight(stemHeightAvg);
        this.centroid.setStemWidth(stemWidthAvg);

        if(changes > 0){
            return false;
        }
        return true;
    }

    private char findMode(String attribute){
        HashMap<Character, Integer> valueCounts = new HashMap<>();
        for(Mushroom m : clusterMushrooms){
            char val = m.getNominalAttribute(attribute);
            if(val != 0) {
                if (valueCounts.containsKey(val)) {
                    valueCounts.put(val, valueCounts.get(val) + 1);
                } else {
                    valueCounts.put(val, 1);
                }
            }
        }

        char mode = clusterMushrooms.get(0).getNominalAttribute(attribute);
        int mode_count = 1;
        for(Map.Entry<Character, Integer> val : valueCounts.entrySet()){
            if(val.getValue() > mode_count){
                mode = val.getKey();
                mode_count = val.getValue();
            }
        }
        return mode;
    }
}
