package ninja.jalexander;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by john on 10/6/16.
 */
public class TransverseWaveGlobals extends Globals {
    public TransverseWaveGlobals() {
        drawMultiplier = 8;
        nodeRadius = 0;
        setPixSize(800, 800);

        heightMultiplier = 0.5;

        fixedPoints = new HashSet<Vertex>();

        numLinearPoints = 100;
        restLengthMultiplier = 0.1;
        initForWave = true;
        initSineMultiplier = 0.1;
        initSineHeightMultiplier = 1;

        gravity = false;
        doRepulsion = false;
        defaultEdgeLength = 3;
        defaultK = 100;
        bMultiplier = 0.001;
        defaultB = defaultK * bMultiplier;
        defaultWeight = 0.5;
    }
}
