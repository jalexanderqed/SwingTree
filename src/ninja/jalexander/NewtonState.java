package ninja.jalexander;

/**
 * Created by john on 3/19/16.
 */
public class NewtonState {
    public double x;
    public double y;
    public double dx;
    public double dy;

    public NewtonState(double x, double y, double dx, double dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public NewtonState(NewtonState s){
        x = s.x;
        y = s.y;
        dx = s.dx;
        dy = s.dy;
    }
}
