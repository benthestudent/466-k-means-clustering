import java.util.ArrayList;
import java.util.HashMap;

public class Mushroom {

//    Variable Information:
//            (n: nominal, m: metrical; nominal values as sets of values)
//   1. cap-diameter (m):			float number in cm
    private double capDiameter;
//   2. cap-shape (n):            bell=b, conical=c, convex=x, flat=f,
//    sunken=s, spherical=p, others=o
    private char capShape;
//   3. cap-surface (n):          fibrous=i, grooves=g, scaly=y, smooth=s,
//    shiny=h, leathery=l, silky=k, sticky=t,
//    wrinkled=w, fleshy=e
    private char capSurface;
//   4. cap-color (n):            brown=n, buff=b, gray=g, green=r, pink=p,
//    purple=u, red=e, white=w, yellow=y, blue=l,
//    orange=o,  black=k
    private char capColor;
//   5. does-bruise-bleed (n):	bruises-or-bleeding=t,no=f
    private char bruiseOrBleeds;
//   6. gill-attachment (n):      adnate=a, adnexed=x, decurrent=d, free=e,
//    sinuate=s, pores=p, none=f, unknown=?
//            7. gill-spacing (n):         close=c, distant=d, none=f
    private char gillAttachment;
//   8. gill-color (n):           see cap-color + none=f
    private char gillColor;
//   9. stem-height (m):			float number in cm
    private double stemHeight;
//   10. stem-width (m):			float number in mm
    private double stemWidth;
//   11. stem-root (n):           bulbous=b, swollen=s, club=c, cup=u, equal=e,
//    rhizomorphs=z, rooted=r
    private char stemRoot;
//   12. stem-surface (n): 		see cap-surface + none=f
    private char stemSurface;
//   13. stem-color (n):			see cap-color + none=f
    private char stemColor;
//   14. veil-type (n):           partial=p, universal=
    private char veilType;
//   15. veil-color (n):          see cap-color + none=f
    private char veilColor;
//   16. has-ring (n):            ring=t, none=f
    private char hasRing;
//   17. ring-type (n):           cobwebby=c, evanescent=e, flaring=r, grooved=g,
//    large=l, pendant=p, sheathing=s, zone=z, scaly=y, movable=m, none=f, unknown=?
    private char ringType;
//            18. spore-print-color (n):   see cap color
    private char sporePrintColor;
//   19. habitat (n):             grasses=g, leaves=l, meadows=m, paths=p, heaths=h,
//    urban=u, waste=w, woods=d
    private char habitat;
//   20. season (n):				spring=s, summer=u, autumn=a, winter=w
    private char season;


    private char classification;
    private HashMap<String, Character> nominalAttributes;

    public Mushroom(double capDiameter, double stemHeight, double stemWidth, HashMap<String, Character> nominals){
        this.capDiameter =capDiameter;
        this.stemHeight = stemHeight;
        this.stemWidth = stemWidth;
        this.nominalAttributes = new HashMap<>();
        for(String attr : nominals.keySet()){
            this.nominalAttributes.put(attr, nominals.get(attr));
        }
    }
    public Mushroom(char classification, double capDiameter, char capShape, char capSurface, char capColor, char bruiseOrBleeds,
        char gillAttachment, char gillColor, double stemHeight, double stemWidth, char stemRoot,
        char stemSurface, char stemColor, char veilType, char veilColor, char hasRing,
        char ringType, char sporePrintColor, char habitat, char season) {
        this.classification = classification;
        this.nominalAttributes = new HashMap<>();
        this.capDiameter = capDiameter;
        this.nominalAttributes.put("capShape", capShape);
        this.capShape = capShape;
        this.nominalAttributes.put("capSurface", capSurface);
        this.capSurface = capSurface;
        this.nominalAttributes.put("capColor", capColor);
        this.capColor = capColor;
        this.nominalAttributes.put("bruiseOrBleeds", bruiseOrBleeds);
        this.bruiseOrBleeds = bruiseOrBleeds;
        this.nominalAttributes.put("gillAttachment", gillAttachment);
        this.gillAttachment = gillAttachment;
        this.nominalAttributes.put("gillColor", gillColor);
        this.gillColor = gillColor;
        this.stemHeight = stemHeight;
        this.stemWidth = stemWidth;
        this.nominalAttributes.put("stemRoot", stemRoot);
        this.stemRoot = stemRoot;
        this.nominalAttributes.put("stemSurface", stemSurface);
        this.stemSurface = stemSurface;
        this.nominalAttributes.put("stemColor", stemColor);
        this.stemColor = stemColor;
        this.nominalAttributes.put("veilType", veilType);
        this.veilType = veilType;
        this.nominalAttributes.put("veilColor", veilColor);
        this.veilColor = veilColor;
        this.nominalAttributes.put("hasRing", hasRing);
        this.hasRing = hasRing;
        this.nominalAttributes.put("ringType", ringType);
        this.ringType = ringType;
        this.nominalAttributes.put("sporePrintColor", sporePrintColor);
        this.sporePrintColor = sporePrintColor;
        this.nominalAttributes.put("habitat", habitat);
        this.habitat = habitat;
        this.nominalAttributes.put("season", season);
        this.season = season;
    }

    public String toString() {
        return "Classification: " + classification + "\n"
                +  "Cap diameter: " + capDiameter + "\n"
             + "Cap shape: " + capShape + "\n"
             + "Cap surface: " + capSurface + "\n"
             + "Cap color: " + capColor + "\n"
             + "Bruises or bleeds: " + bruiseOrBleeds + "\n"
             + "Gill attachment: " + gillAttachment + "\n"
             + "Gill color: " + gillColor + "\n"
             + "Stem height: " + stemHeight + "\n"
             + "Stem width: " + stemWidth + "\n"
             + "Stem root: " + stemRoot + "\n"
             + "Stem surface: " + stemSurface + "\n"
             + "Stem color: " + stemColor + "\n"
             + "Veil type: " + veilType + "\n"
             + "Veil color: " + veilColor + "\n"
             + "Has ring: " + hasRing + "\n"
             + "Ring type: " + ringType + "\n"
             + "Spore print color: " + sporePrintColor + "\n"
             + "Habitat: " + habitat + "\n"
             + "Season: " + season;
    }

    public double findDistance(Mushroom other){
        //find Euclidian distance between numeric values of this mushroom object and the other mushroom
        //HashMap<String, Double> featureDistances = new HashMap<>();
        ArrayList<Double> euclideanDistances = new ArrayList<>();

        euclideanDistances.add(findSingleEuclidDist(other.capDiameter, this.capDiameter));
        euclideanDistances.add(findSingleEuclidDist(other.stemHeight, this.stemHeight));
        euclideanDistances.add(findSingleEuclidDist(other.stemWidth, this.stemWidth));

        // Ben: I am removing the following code because my nominalDistance method finds the
        // distance using all the nominal attributes with the simple matching method
        // which I think will give better results than one-hot encoding, and so we won't
        // need these.
//        euclideanDistances.add(findSingleEuclidDist(other.capShape, this.capShape));
//        euclideanDistances.add(findSingleEuclidDist(other.capSurface, this.capSurface));
//        euclideanDistances.add(findSingleEuclidDist(other.capColor, this.capColor));
//        euclideanDistances.add(findSingleEuclidDist(other.bruiseOrBleeds, this.bruiseOrBleeds));
//        euclideanDistances.add(findSingleEuclidDist(other.gillAttachment, this.gillAttachment));
//        euclideanDistances.add(findSingleEuclidDist(other.gillColor, this.gillColor));
//        euclideanDistances.add(findSingleEuclidDist(other.stemRoot, this.stemRoot));
//        euclideanDistances.add(findSingleEuclidDist(other.stemSurface, this.stemSurface));
//        euclideanDistances.add(findSingleEuclidDist(other.stemColor, this.stemColor));
//        euclideanDistances.add(findSingleEuclidDist(other.veilType, this.veilType));
//        euclideanDistances.add(findSingleEuclidDist(other.veilColor, this.veilColor));
//        euclideanDistances.add(findSingleEuclidDist(other.hasRing, this.hasRing));
//        euclideanDistances.add(findSingleEuclidDist(other.ringType, this.ringType));
//        euclideanDistances.add(findSingleEuclidDist(other.sporePrintColor, this.sporePrintColor));
//        euclideanDistances.add(findSingleEuclidDist(other.habitat, this.habitat));
//        euclideanDistances.add(findSingleEuclidDist(other.season, this.season));


        double nominalDist = findNominalDistance(other);

        double vectorMagnitude = 0.0;
        for(double val: euclideanDistances){
            vectorMagnitude += val*val;
        }

        // in order for the normalization to be accurate using both numeric and categorical
        // data, I am going to give both sets of attributes a weight proportional to the number
        // of categories they represent (i.e. because 3 of 19 attr. are numeric their weight
        // will be 3/19, and the weight of the nominal distance will be 16/19)
        int numAttr = euclideanDistances.size() + nominalAttributes.size();
        vectorMagnitude *= (double) euclideanDistances.size() / numAttr;

        // add the weighted nominal Distance to the vector's magnitude
        vectorMagnitude += nominalDist * (double) nominalAttributes.size() / numAttr;

        vectorMagnitude = Math.sqrt(vectorMagnitude);

        for(int i = 0; i < euclideanDistances.size(); i++){
            double normalizedDist = (double) euclideanDistances.get(i) / vectorMagnitude;
            euclideanDistances.set(i, normalizedDist);
        }
        //I am not sure if the math checks out on this but I think the following should work
        double normalizedNominalDist = (nominalDist * (double) nominalAttributes.size() / numAttr) / vectorMagnitude;

         double sum = 0.0;
         for(double val: euclideanDistances){
             sum += val;
         }
         sum += normalizedNominalDist;

        return sum;
    }

    public static double findSingleEuclidDist(double a, double b) {
        return Math.sqrt((a-b)*(a-b));
    }

    // commenting these out bc the findNominalDist method should replace them
//    public static double findSingleEuclidDist(boolean a, boolean b){
//        return a == b ? 0.0 : 2.0;
//    }
//
//    public static double findSingleEuclidDist(char a, char b){
//        //Using one hot encoding, catagorical data represented as array, get euclidean dist of array
//        //In this case if they match euclid
//        return a == b ? 0.0 : 2.0;
//    }



    public double findNominalDistance(Mushroom other){
        //find distance between nominal values of this mushroom object and another mushroom

        //Nominal Categories: capShape, capSurface, capColor, bruiseOrBleeds, gillAttachment
        //                      gillColor, stemRoot, stemSurface, stemColor, veilType, veilColor
        //                      hasRing, ringType, sporePrintColor, habitat, season
        int numOfNomAtt = nominalAttributes.size();
        int mismatches = 0;
        for(String attr : nominalAttributes.keySet()){
            if(this.nominalAttributes.get(attr) != other.nominalAttributes.get(attr)){
                mismatches++;
            }
        }

        return (double) mismatches / numOfNomAtt;
    }

    // Ben: removing this because the findDistance (previously findEuclidianDistance) Method
    // now does this too and normalization.
//    public double findCombinedDistance(Mushroom other){
//        //find combined distance between this mushroom object and another mushroom
//        return Math.sqrt(findEuclidianDistance(other)*findEuclidianDistance(other) + findNominalDistance(other)*findNominalDistance(other));
//    }

    public double findF1Score(Mushroom other){
        return 0;
    }


    public char getNominalAttribute(String attr){
        return nominalAttributes.get(attr);
    }

    public HashMap<String, Character> getNominalAttributes(){
        return nominalAttributes;
    }

    public void setNominalAttribute(String attribute, char val){
        if(nominalAttributes.containsKey(attribute)){
            nominalAttributes.put(attribute, val);
        }
    }

    public double getCapDiameter(){
        return capDiameter;
    }

    public void setCapDiameter(double newCapDiam){
        this.capDiameter = newCapDiam;
    }

    public double getStemHeight(){
        return stemHeight;
    }

    public void setStemHeight(double newStemHeight) {
        this.stemHeight = newStemHeight;
    }

    public double getStemWidth(){
        return stemWidth;
    }

    public void setStemWidth(double newStemWidth){
        this.stemWidth = newStemWidth;
    }

    public char getClassification(){
        return this.classification;
    }
    public Mushroom copy(){
        Mushroom clone = new Mushroom(this.capDiameter, this.stemHeight, this.stemWidth, this.nominalAttributes);
        return clone;
    }

}
