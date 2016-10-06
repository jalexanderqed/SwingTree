package ninja.jalexander;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by john on 3/19/16.
 */
public class MainFrame extends JFrame {
    public MainPanel panel;

    public MainFrame(){
        super("Swing Tree");
        this.setSize(Main.globals.pixWidth, Main.globals.pixHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        Main.globals.setPixSize(this.getWidth(), this.getHeight());
    }

    public void addComponents(ArrayList<IntPair> graph, ArrayList<Vertex> vertices){
        panel = new MainPanel(graph, vertices);
        this.add(panel);
    }
}
