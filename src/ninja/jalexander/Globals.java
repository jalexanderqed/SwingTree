package ninja.jalexander;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by john on 3/19/16.
 */
public class Globals {
    public static int pixWidth;
    public static int pixHeight;
    public static int drawMultiplier; // Controls zoom
    public static int nodeRadius;
    public static double unitWidth;
    public static double unitHeight;

    public static double heightMultiplier;

    public static HashSet<Vertex> fixedPoints;

    public static int numLinearPoints;
    public static double restLengthMultiplier;
    public static boolean initForWave;
    public static double initSineMultiplier;
    public static double initSineHeightMultiplier;

    public static int branchFactor;
    public static int treeDepth;

    public static boolean gravity;
    public static boolean doRepulsion;
    public static double gravityConstant;
    public static double repulsiveMultiplier;
    public static double defaultEdgeLength;
    public static double defaultK;
    public static double bMultiplier;
    public static double defaultB;
    public static double defaultWeight;
    public static ArrayList<Vertex> vertices;

    public static void setPixSize(int width, int height){
        pixWidth = width;
        pixHeight = height;
        unitWidth = (double) pixWidth / drawMultiplier;
        unitHeight = (double) pixHeight / drawMultiplier;
    }
}
