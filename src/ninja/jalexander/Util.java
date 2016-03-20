package ninja.jalexander;

/**
 * Created by john on 3/19/16.
 */
public class Util {
    public static double calcDist(NewtonState p1, NewtonState p2){
        double xDif = p1.x - p2.x;
        double yDif = p1.y - p2.y;
        return Math.sqrt(xDif * xDif + yDif * yDif);
    }
}
