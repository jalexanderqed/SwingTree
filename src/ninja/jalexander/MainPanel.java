package ninja.jalexander;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by john on 3/19/16.
 */
public class MainPanel extends JPanel{
    private ArrayList<IntPair> graph;
    private ArrayList<Vertex> vertices;

    private static final int MULT = Globals.drawMultiplier;
    private static final int RADIUS = Globals.nodeRadius;
    private static final Color VERTEX_COLOR = Color.BLACK;
    private static final Color EDGE_COLOR = Color.BLACK;
    public Color backgroundColor = Color.WHITE;

    public MainPanel(ArrayList<IntPair> g, ArrayList<Vertex> v){
        super();
        graph = g;
        vertices = v;
    }

    public void paint(Graphics g){
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        for(IntPair p : graph){
            Vertex first = vertices.get(p.a);
            Vertex second = vertices.get(p.b);

            g.setColor(VERTEX_COLOR);
            g.fillOval((int)(MULT * first.state.x) - RADIUS, (int)(MULT * first.state.y) - RADIUS, 2 * RADIUS, 2 * RADIUS);
            g.fillOval((int)(MULT * second.state.x) - RADIUS, (int)(MULT * second.state.y) - RADIUS, 2 * RADIUS, 2 * RADIUS);

            g.setColor(EDGE_COLOR);
            g.drawLine((int)(MULT * first.state.x), (int)(MULT * first.state.y), (int)(MULT * second.state.x), (int)(MULT * second.state.y));
        }
    }
}
