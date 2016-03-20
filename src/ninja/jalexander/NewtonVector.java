package ninja.jalexander;

/**
 * Created by john on 3/19/16.
 */
public class NewtonVector {
    public double x;
    public double y;

    public double dx;
    public double dy;

    public NewtonVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public NewtonVector(NewtonState from, NewtonState to) {
        x = to.x - from.x;
        y = to.y - from.y;
        dx = to.dx - from.dx;
        dy = to.dy - from.dy;
    }

    public void normalize() {
        double length = Math.sqrt(x * x + y * y);
        x /= length;
        y /= length;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }
}
