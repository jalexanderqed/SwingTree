package ninja.jalexander;

import sun.nio.cs.ext.MacHebrew;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static int simType;
    public static Globals globals;

    public static int TREE = 0;
    public static int WAVE = 1;

    public static void main(String[] args) {
        if(args.length > 0){
            if(args[0].equals("tree")){
                simType = TREE;
            }
            else if(args[0].equals("wave")){
                simType = WAVE;
            }
        }
        else{
            simType = TREE;
        }

        if(simType == TREE){
            globals = new TreeGlobals();
        }
        else {
            globals = new TransverseWaveGlobals();
        }

        MainFrame frame = new MainFrame();

        ArrayList<IntPair> graph = new ArrayList<IntPair>();
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        globals.vertices = vertices;

        if(simType == TREE){
            initTree(graph, vertices);
        }
        else {
            initLinear(graph, vertices);
        }

        frame.addComponents(graph, vertices);

        frame.panel.repaint();
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){}

        frame.panel.backgroundColor = Color.GREEN;
        int count = 0;

        long last = System.currentTimeMillis();
        while(true){
            long start = System.currentTimeMillis();
            update(vertices, (start - last) / 1000.0);
            last = System.currentTimeMillis();

            frame.panel.repaint();
            /*
            while(System.currentTimeMillis() < start + 2){
                try{
                    Thread.sleep(2);
                }
                catch(Exception e){}
            }
            */
            try{
                Thread.sleep(1);
            }
            catch(Exception e){}
            if(count > 10) frame.panel.backgroundColor = Color.WHITE;
            count++;
        }
    }

    public static void initLinear(ArrayList<IntPair> graph, ArrayList<Vertex> vertices){
        double stepSize = globals.unitWidth / (globals.numLinearPoints + 1.0);
        double height = globals.unitHeight * 0.5;
        int startIndex = vertices.size();

        Vertex last = new Vertex(globals.defaultWeight);
        last.state.x = stepSize;
        last.state.y = height;
        vertices.add(last);
        globals.fixedPoints.add(last);

        for(int i = 1; i < globals.numLinearPoints; i++){
            int index = vertices.size();
            Vertex v = new Vertex(globals.defaultWeight);
            v.state.x = stepSize * (i + 1);
            v.state.y = height;
            vertices.add(v);

            Edge edge = new Edge(stepSize * globals.restLengthMultiplier, globals.defaultK, globals.defaultB);
            v.addNeighbor(new VertexEdgePair(last, edge));
            last.addNeighbor(new VertexEdgePair(v, edge));
            graph.add(new IntPair(index, index - 1));
            last = v;
        }
        globals.fixedPoints.add(last);

        if(globals.initForWave){
            initSineWave(startIndex,
                    (int)Math.round(globals.numLinearPoints * globals.initSineMultiplier),
                    globals.numLinearPoints * stepSize * globals.initSineMultiplier * globals.initSineHeightMultiplier,
                    vertices);
        }
    }

    public static void initSineWave(int startNode, int length, double height, ArrayList<Vertex> vertices){
        double angleStep = Math.PI / length;
        for(int i = 0; i <= length; i++){
            int index = i + startNode;
            vertices.get(index).state.y -= height * Math.sin(i * angleStep);
        }
    }

    public static void initTree(ArrayList<IntPair> graph, ArrayList<Vertex> vertices){
        int rootIndex = initVertex(1, globals.unitWidth / 2, globals.unitHeight * globals.heightMultiplier, graph, vertices);
        globals.fixedPoints.add(vertices.get(rootIndex));
    }

    public static int initVertex(int depth, double x, double y, ArrayList<IntPair> graph, ArrayList<Vertex> vertices){
        Vertex v = new Vertex(globals.defaultWeight);
        v.state.x = x;
        v.state.y = y;
        int myPos = vertices.size();
        vertices.add(v);
        if(depth == globals.treeDepth) return myPos;

        double eachWidth = globals.unitWidth / Math.pow(globals.branchFactor, depth);
        double myWidth = (globals.branchFactor + 1) * eachWidth;

        for(int i = 0; i < globals.branchFactor; i++){
            int nextIndex = initVertex(depth + 1, x - myWidth / 2 + ((i + 1) * eachWidth), y + globals.defaultEdgeLength, graph, vertices);
            Vertex next = vertices.get(nextIndex);
            Edge edge = new Edge(globals.defaultEdgeLength, globals.defaultK, globals.defaultB);
            v.addNeighbor(new VertexEdgePair(next, edge));
            next.addNeighbor(new VertexEdgePair(v, edge));
            graph.add(new IntPair(nextIndex, myPos));
        }
        return myPos;
    }

    public static void update(ArrayList<Vertex> vertices, double dt){
        for(Vertex v : vertices){
            if(!globals.fixedPoints.contains(v))
                v.update(dt);
        }
        for(Vertex v : vertices){
            if(!globals.fixedPoints.contains(v))
                v.flip();
        }
    }
}
