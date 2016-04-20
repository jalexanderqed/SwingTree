package ninja.jalexander;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by john on 3/19/16.
 */
public class Globals {
    public static int pixWidth = 800;
    public static int pixHeight = 800;
    public static int drawMultiplier = 8;
    public static int nodeRadius = 0;
    public static double unitWidth = (double)pixWidth / drawMultiplier;
    public static double unitHeight = (double)pixHeight / drawMultiplier;

    public static double heightMultiplier = 0.5;

    public static HashSet<Vertex> fixedPoints = new HashSet<Vertex>();

    public static int numLinearPoints = 100;
    public static double restLengthMultiplier = 0.1;
    public static boolean initForWave = true;
    public static double initSineMultiplier = 0.1;
    public static double initSineHeightMultiplier = 1;

    public static int branchFactor = 10;
    public static int treeDepth = 3;

    public static boolean gravity = false;
    public static boolean doRepulsion = false;
    public static double gravityConstant = 50;
    public static double repulsiveMultiplier = 40;
    public static double defaultEdgeLength = 3;
    public static double defaultK = 100;
    public static double bMultiplier = 0.001;
    public static double defaultB = defaultK * bMultiplier;
    public static double defaultWeight = 0.5;
    public static ArrayList<Vertex> vertices;
}
