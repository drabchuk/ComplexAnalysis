package objects;

import geometry.Angles;
import geometry.Vector;

/**
 * Created by Денис on 03.04.2015.
 */
public class Observer {
    public Vector position;
    private Angles angles;
    public Vector direction;
    public Vector screenX;
    public Vector screenY;
    public Vector screenZ;
    public Vector screen2dX;
    public Vector screen2dY;
    public Vector position3d;
    public Vector direction3d;
    public Observer(){
        this.direction = new Vector(0,0,0,5);
        angles = new Angles();
        this.position = new Vector(0,0,0,-5);
        this.screenX = new Vector(1,0,0,0);
        this.screenY = new Vector(0,1,0,0);
        this.screenZ = new Vector(0,0,1,0);
        this.screen2dX = new Vector(1,0,0,0);
        this.screen2dY = new Vector(0,1,0,0);
        this.position3d = new Vector(0,0,-10,0);
        this.direction3d = new Vector(0,0,10,0);
    }

    public void Rotate (Angles a) {
        direction.Transform(a, 1);
        screenX.Transform(a, 1);
        screenY.Transform(a, 1);
        screenZ.Transform(a, 1);
    }
    public void Move (char axis, char dir) {
        double step = 0.1;
        if (dir == 'b') {
            step = step * (-1);
        }
        switch (axis) {
            case 't': {
                position = position.plus(direction.normalize().ConstProduct(step));
                break;
            }
            case 'x': {
                position = position.plus(screenX.normalize().ConstProduct(step));
                break;
            }
            case 'y': {
                position = position.plus(screenY.normalize().ConstProduct(step));
                break;
            }
            case 'z': {
                position = position.plus(screenZ.normalize().ConstProduct(step));
                break;
            }
            default: {
                break;
            }
        }
    }
}
