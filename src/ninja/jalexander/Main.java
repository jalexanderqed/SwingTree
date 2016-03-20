package ninja.jalexander;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<IntPair> graph = new ArrayList<IntPair>();
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        Globals.vertices = vertices;

        init(graph, vertices);

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
            while(System.currentTimeMillis() < start + 20){
                try{
                    Thread.sleep(2);
                }
                catch(Exception e){}
            }
            if(count > 10) frame.panel.backgroundColor = Color.WHITE;
            count++;
        }
    }

    public static void init(ArrayList<IntPair> graph, ArrayList<Vertex> vertices){
        initVertex(1, Globals.unitWidth / 2, Globals.unitHeight * Globals.heightMultiplier, graph, vertices);
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
            if(v != vertices.get(0))
                v.update(dt);
        }
        for(Vertex v : vertices){
            if(v != vertices.get(0))
                v.flip();
        }
    }
}
