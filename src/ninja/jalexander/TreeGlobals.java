package ninja.jalexander;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by john on 10/6/16.
 */
public class TreeGlobals extends Globals {
    public TreeGlobals() {
        drawMultiplier = 16;
        nodeRadius = 4;
        setPixSize(800, 800);

        heightMultiplier = 0.15;

        fixedPoints = new HashSet<Vertex>();

        branchFactor = 5;
        treeDepth = 3;

        gravity = true;
        doRepulsion = true;
        gravityConstant = 100;
        repulsiveMultiplier = 40;
        defaultEdgeLength = 3;
        defaultK = 100;
        bMultiplier = 0.05;
        defaultB = defaultK * bMultiplier;
        defaultWeight = 1;
    }
}
