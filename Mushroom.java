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
    private boolean stemColor;
//   14. veil-type (n):           partial=p, universal=u
//   15. veil-color (n):          see cap-color + none=f
//   16. has-ring (n):            ring=t, none=f
//   17. ring-type (n):           cobwebby=c, evanescent=e, flaring=r, grooved=g,
//    large=l, pendant=p, sheathing=s, zone=z, scaly=y, movable=m, none=f, unknown=?
//            18. spore-print-color (n):   see cap color
//   19. habitat (n):             grasses=g, leaves=l, meadows=m, paths=p, heaths=h,
//    urban=u, waste=w, woods=d
//   20. season (n):				spring=s, summer=u, autumn=a, winter=w


    public double findEuclidianDistance(Mushroom other){
        //find Euclidian distance between numeric values of this mushroom object and the other mushroom
        return 0;
    }

    public double findNominalDistance(Mushroom other){
        //find distance between nominal values of this mushroom object and another mushroom
        return 0;
    }

}
