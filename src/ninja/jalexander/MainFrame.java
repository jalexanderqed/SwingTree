package ninja.jalexander;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by john on 3/19/16.
 */
public class MainFrame extends JFrame {
    public MainPanel panel;

    public MainFrame(ArrayList<IntPair> graph, ArrayList<Vertex> vertices){
        super("Swing Tree");
        panel = new MainPanel(graph, vertices);
        this.add(panel);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
