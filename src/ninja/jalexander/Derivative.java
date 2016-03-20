package ninja.jalexander;

/**
 * Created by john on 3/19/16.
 */
public class Derivative {
    public double dx;
    public double dy;
    public double ax;
    public double ay;

    public Derivative(double dx, double dy, double ax, double ay){
        this.dx = dx;
        this.dy = dy;
        this.ax = ax;
        this.ay = ay;
    }

    public Derivative(Derivative d){
        dx = d.dx;
        dy = d.dy;
        ax = d.ax;
        ay = d.ay;
    }
}
