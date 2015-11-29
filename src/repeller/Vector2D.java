package repeller;

/** Created by Giuseppe on 18/09/2015. */
public class Vector2D {

    public static Vector2D add(Vector2D v, Vector2D u){
        return new Vector2D(v.x + u.x, v.y + u.y);
    }

    public static Vector2D sub(Vector2D v, Vector2D u){
        return new Vector2D(v.x - u.x, v.y - u.y);
    }

    public static Vector2D mul(Vector2D v, double scalar){
        Vector2D vector2D = new Vector2D(v);
        vector2D.mul(scalar);
        return vector2D;
    }

    public static Vector2D div(Vector2D v, double dividend){
        Vector2D vector2D = new Vector2D(v);
        vector2D.div(dividend);
        return vector2D;
    }

    public double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D get(){
        return new Vector2D(this);
    }

    public Vector2D(Vector2D vector2D) {
        this.x = vector2D.x;
        this.y = vector2D.y;
    }

    public void add(double x, double y){
        this.x += x;
        this.y += y;
    }

    public void add(Vector2D vectorToAdd){
        this.x += vectorToAdd.x;
        this.y += vectorToAdd.y;
    }

    public void sub(double x, double y){
        this.x -= x;
        this.y -= y;
    }

    public void sub(Vector2D vectorToAdd){
        this.x -= vectorToAdd.x;
        this.y -= vectorToAdd.y;
    }

    public void mul(double scalar){
        this.x *= scalar;
        this.y *= scalar;
    }

    public void div(double dividend){
        this.x /= dividend;
        this.y /= dividend;
    }

    public double mag(){
        return Math.sqrt(x*x + y*y);
    }

    public void normalize(){
        double m = mag();
        if (m != 0) div(m);
    }

    public void limit(double max){
        if (mag() > max){
            normalize();
            mul(max);
        }
    }

    public double heading(){
        return Math.atan2(y, x);
    }

}
