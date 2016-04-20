package ninja.jalexander;

import sun.nio.cs.ext.MacHebrew;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<IntPair> graph = new ArrayList<IntPair>();
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        Globals.vertices = vertices;

        initLinear(graph, vertices);

        MainFrame frame = new MainFrame(graph, vertices);
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
        double stepSize = Globals.unitWidth / (Globals.numLinearPoints + 1.0);
        double height = Globals.unitHeight * 0.5;
        int startIndex = vertices.size();

        Vertex last = new Vertex(Globals.defaultWeight);
        last.state.x = stepSize;
        last.state.y = height;
        vertices.add(last);
        Globals.fixedPoints.add(last);

        for(int i = 1; i < Globals.numLinearPoints; i++){
            int index = vertices.size();
            Vertex v = new Vertex(Globals.defaultWeight);
            v.state.x = stepSize * (i + 1);
            v.state.y = height;
            vertices.add(v);

            Edge edge = new Edge(stepSize * Globals.restLengthMultiplier, Globals.defaultK, Globals.defaultB);
            v.addNeighbor(new VertexEdgePair(last, edge));
            last.addNeighbor(new VertexEdgePair(v, edge));
            graph.add(new IntPair(index, index - 1));
            last = v;
        }
        Globals.fixedPoints.add(last);

        if(Globals.initForWave){
            initSineWave(startIndex,
                    (int)Math.round(Globals.numLinearPoints * Globals.initSineMultiplier),
                    Globals.numLinearPoints * stepSize * Globals.initSineMultiplier * Globals.initSineHeightMultiplier,
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
        int rootIndex = initVertex(1, Globals.unitWidth / 2, Globals.unitHeight * Globals.heightMultiplier, graph, vertices);
        Globals.fixedPoints.add(vertices.get(rootIndex));
    }

    public static int initVertex(int depth, double x, double y, ArrayList<IntPair> graph, ArrayList<Vertex> vertices){
        Vertex v = new Vertex(Globals.defaultWeight);
        v.state.x = x;
        v.state.y = y;
        int myPos = vertices.size();
        vertices.add(v);
        if(depth == Globals.treeDepth) return myPos;

        double eachWidth = Globals.unitWidth / Math.pow(Globals.branchFactor, depth);
        double myWidth = (Globals.branchFactor + 1) * eachWidth;

        for(int i = 0; i < Globals.branchFactor; i++){
            int nextIndex = initVertex(depth + 1, x - myWidth / 2 + ((i + 1) * eachWidth), y + Globals.defaultEdgeLength, graph, vertices);
            Vertex next = vertices.get(nextIndex);
            Edge edge = new Edge(Globals.defaultEdgeLength, Globals.defaultK, Globals.defaultB);
            v.addNeighbor(new VertexEdgePair(next, edge));
            next.addNeighbor(new VertexEdgePair(v, edge));
            graph.add(new IntPair(nextIndex, myPos));
        }
        return myPos;
    }

    public static void update(ArrayList<Vertex> vertices, double dt){
        for(Vertex v : vertices){
            if(!Globals.fixedPoints.contains(v))
                v.update(dt);
        }
        for(Vertex v : vertices){
            if(!Globals.fixedPoints.contains(v))
                v.flip();
        }
    }
}
