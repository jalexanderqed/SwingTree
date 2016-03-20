package ninja.jalexander;

import java.util.LinkedList;

/**
 * Created by john on 3/19/16.
 */
public class Vertex {
    private double mass;
    private double inverseMass;
    private LinkedList<VertexEdgePair> neighbors = new LinkedList<VertexEdgePair>();

    public NewtonState state = new NewtonState(0, 0, 0, 0);
    public NewtonState nextState = new NewtonState(0, 0, 0, 0);

    public Vertex(double m){
        mass = m;
        inverseMass = 1.0 / m;
    }

    public void update(double dt){
        Derivative a, b, c, d;

        a = evaluate(0, new Derivative(0, 0, 0, 0));
        b = evaluate(dt * 0.5, a);
        c = evaluate(dt * 0.5, b);
        d = evaluate(dt * 0.5, c);

        double dx = (1.0 / 6.0) * (a.dx + 2 * (b.dx + c.dx) + d.dx);
        double dy = (1.0 / 6.0) * (a.dy + 2 * (b.dy + c.dy) + d.dy);
        double ax = (1.0 / 6.0) * (a.ax + 2 * (b.ax + c.ax) + d.ax);
        double ay = (1.0 / 6.0) * (a.ay + 2 * (b.ay + c.ay) + d.ay);

        nextState = new NewtonState(state);

        nextState.x += dx * dt;
        nextState.y += dy * dt;
        nextState.dx += ax * dt;
        nextState.dy += ay * dt;
    }

    public void flip(){
        state = nextState;
    }

    public Derivative evaluate(double dt,
                               Derivative d){
        NewtonState newState = new NewtonState(state);
        newState.x = state.x + d.dx * dt;
        newState.y = state.y + d.dy * dt;
        newState.dx = state.dx + d.ax * dt;
        newState.dy = state.dy + d.ay * dt;

        Derivative output = new Derivative(0, 0, 0, 0);
        output.dx = newState.dx;
        output.dy = newState.dy;

        Acceleration a = calculateAcceleration(newState);
        output.ax = a.ax;
        output.ay = a.ay;

        return output;
    }

    public Acceleration calculateAcceleration(NewtonState s){
        double fx = 0;
        double fy = 0;

        for(VertexEdgePair p : neighbors){
            NewtonVector vector = new NewtonVector(p.vertex.state, s);
            double displacement = vector.length() - p.edge.restLength;
            vector.normalize();
            fx -= p.edge.k * displacement * vector.x + p.edge.b * vector.dx;
            fy -= p.edge.k * displacement * vector.y + p.edge.b * vector.dy;
        }

        for(Vertex v : Globals.vertices){
            if(v == this) continue;

            NewtonVector vector = new NewtonVector(v.state, state);
            double dist = vector.length();
            double multiplier = Globals.repulsiveMultiplier / (dist * dist);
            fx += vector.x * multiplier;
            fy += vector.y * multiplier;
        }

        if(Globals.gravity) fy += Globals.gravityConstant * mass;
        return new Acceleration(fx * inverseMass, fy * inverseMass);
    }

    public void addNeighbor(VertexEdgePair p){
        neighbors.add(p);
    }

    public double Mass(){
        return mass;
    }

    public double InverseMass(){
        return inverseMass;
    }

    public void changeMass(double m){
        mass = m;
        inverseMass = 1.0 / m;
    }
}
