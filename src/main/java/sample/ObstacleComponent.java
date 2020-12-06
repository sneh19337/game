package sample;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;

public class ObstacleComponent extends ColorComponent {
    double y=360;
    int x=0;

    protected PhysicsComponent physics;

    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(Color c) {
        super.setColor(c);
    }

    void down(double x)
    {

//                physics.setVelocityY(x);

//        entity.translateY(100);

    }
    void stop()
    {
//        physics.setVelocityY(0);
    }
//    @Override
//    public void onUpdate(double tpf) {
////        getEntity().rotateBy(x/10/tpf);
//
//    }

@Override
public void onUpdate(double tpf) {
//    System.out.println(entity.getX() +" "+entity.getY());
//    System.out.println("a");
//    physics.setLinearVelocity(0,0);

//    entity.rotateBy( entity.getRotation()+1);
//    physics.setVelocityY(5);
//    System.out.println(entity.getY());


//    x.setSpeed(1);
//    x.setDirection(new Point2D(0,0));
//    entity.setRotation(entity.getRotation()+5);
//    getEntity().rotateToVector(new Point2D(100,100));
//    getEntity().rotateToVector(new Point2D(1,1));
    if(entity.getY()>=FXGL.getAppHeight()/2-100)
    {
//            physics.setLinearVelocity(0,0);
        entity.translateY(-10);
//            y=false;
    }
    if(x%3==0)
    {
        getEntity().rotateBy(2);



        x=x%3;
    }
    x++;


}
}
