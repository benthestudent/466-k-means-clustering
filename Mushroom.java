import java.util.ArrayList;

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
    private boolean bruiseOrBleeds;
//   6. gill-attachment (n):      adnate=a, adnexed=x, decurrent=d, free=e,
//    sinuate=s, pores=p, none=f, unknown=?
//            7. gill-spacing (n):         close=c, distant=d, none=f
    private char gillAttachment;
//   8. gill-color (n):           see cap-color + none=f
    private boolean gillColor;
//   9. stem-height (m):			float number in cm
    private double stemHeight;
//   10. stem-width (m):			float number in mm
    private double stemWidth;
//   11. stem-root (n):           bulbous=b, swollen=s, club=c, cup=u, equal=e,
//    rhizomorphs=z, rooted=r
    private char stemRoot;
//   12. stem-surface (n): 		see cap-surface + none=f
    private boolean stemSurface;
//   13. stem-color (n):			see cap-color + none=f
    private char stemColor;
//   14. veil-type (n):           partial=p, universal=
    private boolean veilType; 
//   15. veil-color (n):          see cap-color + none=f
    private boolean veilColor;
//   16. has-ring (n):            ring=t, none=f
    private boolean hasRing;
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
    public Mushroom(double capDiameter, char capShape, char capSurface, char capColor, boolean bruiseOrBleeds,
        char gillAttachment, boolean gillColor, double stemHeight, double stemWidth, char stemRoot,
        boolean stemSurface, char stemColor, boolean veilType, boolean veilColor, boolean hasRing,
        char ringType, char sporePrintColor, char habitat, char season) {
        this.capDiameter = capDiameter;
        this.capShape = capShape;
        this.capSurface = capSurface;
        this.capColor = capColor;
        this.bruiseOrBleeds = bruiseOrBleeds;
        this.gillAttachment = gillAttachment;
        this.gillColor = gillColor;
        this.stemHeight = stemHeight;
        this.stemWidth = stemWidth;
        this.stemRoot = stemRoot;
        this.stemSurface = stemSurface;
        this.stemColor = stemColor;
        this.veilType = veilType;
        this.veilColor = veilColor;
        this.hasRing = hasRing;
        this.ringType = ringType;
        this.sporePrintColor = sporePrintColor;
        this.habitat = habitat;
        this.season = season;
    }

    public String toString() {
        return "Cap diameter: " + capDiameter + "\n"
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

    public double findEuclidianDistance(Mushroom other){
        //find Euclidian distance between numeric values of this mushroom object and the other mushroom
        //HashMap<String, Double> featureDistances = new HashMap<>();
        ArrayList<Double> euclideanDistances = new ArrayList<>();

        euclideanDistances.add(findSingleEuclidDist(other.capDiameter, this.capDiameter));
        euclideanDistances.add(findSingleEuclidDist(other.capShape, this.capShape));
        euclideanDistances.add(findSingleEuclidDist(other.capSurface, this.capSurface));
        euclideanDistances.add(findSingleEuclidDist(other.capColor, this.capColor));
        euclideanDistances.add(findSingleEuclidDist(other.bruiseOrBleeds, this.bruiseOrBleeds));
        euclideanDistances.add(findSingleEuclidDist(other.gillAttachment, this.gillAttachment));
        euclideanDistances.add(findSingleEuclidDist(other.gillColor, this.gillColor));
        euclideanDistances.add(findSingleEuclidDist(other.stemHeight, this.stemHeight));
        euclideanDistances.add(findSingleEuclidDist(other.stemWidth, this.stemWidth));
        euclideanDistances.add(findSingleEuclidDist(other.stemRoot, this.stemRoot));
        euclideanDistances.add(findSingleEuclidDist(other.stemSurface, this.stemSurface));
        euclideanDistances.add(findSingleEuclidDist(other.stemColor, this.stemColor));
        euclideanDistances.add(findSingleEuclidDist(other.veilType, this.veilType));
        euclideanDistances.add(findSingleEuclidDist(other.veilColor, this.veilColor));
        euclideanDistances.add(findSingleEuclidDist(other.hasRing, this.hasRing));
        euclideanDistances.add(findSingleEuclidDist(other.ringType, this.ringType));
        euclideanDistances.add(findSingleEuclidDist(other.sporePrintColor, this.sporePrintColor));
        euclideanDistances.add(findSingleEuclidDist(other.habitat, this.habitat));
        euclideanDistances.add(findSingleEuclidDist(other.season, this.season));

        double vectorMagnitude = 0.0;
        for(double val: euclideanDistances){
            vectorMagnitude += val*val;
        }
        vectorMagnitude = Math.sqrt(vectorMagnitude);

        for(int i = 0; i < euclideanDistances.size(); i++){
            double normalizedDist = euclideanDistances.get(i) / vectorMagnitude;
            euclideanDistances.add(i, normalizedDist);
        }

         double sum = 0.0;
         for(double val: euclideanDistances){
             sum += val;
         }

        return sum;
    }

    public static double findSingleEuclidDist(double a, double b) {
        return Math.sqrt(a*a + b*b);
    }

    public static double findSingleEuclidDist(boolean a, boolean b){
        return a == b ? 0.0 : 2.0;
    }

    public static double findSingleEuclidDist(char a, char b){
        //Using one hot encoding, catagorical data represented as array, get euclidean dist of array
        //In this case if they match euclid
        return a == b ? 0.0 : 2.0;
    }



    public double findNominalDistance(Mushroom other){
        //find distance between nominal values of this mushroom object and another mushroom
        return 0;
    }

    public double findCombinedDistance(Mushroom other){
        //find combined distance between this mushroom object and another mushroom
        return Math.sqrt(findEuclidianDistance(other)*findEuclidianDistance(other) + findNominalDistance(other)*findNominalDistance(other));
    }

    public double findF1Score(Mushroom other){
        return 0;
    }

}
