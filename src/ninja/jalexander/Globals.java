package ninja.jalexander;

import java.util.ArrayList;

/**
 * Created by john on 3/19/16.
 */
public class Globals {
    public static int pixWidth = 800;
    public static int pixHeight = 800;
    public static int drawMultiplier = 8;
    public static double unitWidth = (double)pixWidth / drawMultiplier;
    public static double unitHeight = (double)pixHeight / drawMultiplier;

    public static double heightMultiplier = 0.15;

    public static boolean gravity = true;
    public static double gravityConstant = 50;
    public static double repulsiveMultiplier = 40;
    public static int treeDepth = 4;
    public static double defaultEdgeLength = 3;
    public static double defaultK = 100;
    public static double defaultB = 5;
    public static double defaultWeight = 1;
    public static int branchFactor = 5;
    public static ArrayList<Vertex> vertices;
}
