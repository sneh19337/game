package sample;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.paint.Color;

public class BallComponent extends ColorComponent {
    protected PhysicsComponent physics;
    int x=0;
    boolean y=true;
    private static final double BAT_SPEED = 420;


    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(Color c) {
        super.setColor(c);
    }

    public void up() {
        if (entity.getY() >= FXGL.getAppHeight()/2) {
//            physics.setVelocityY(-BAT_SPEED);
            entity.translateY(-10);
            x++;
        }
        else
            stop();
    }

    public void stop() {

//        physics.setLinearVelocity(0, 400);
        y=true;

    }

    @Override
    public void onUpdate(double tpf) {

        if(entity.getY()<=FXGL.getAppHeight()*9/10)
        {
//            physics.setLinearVelocity(0,0);
            entity.translateY(3);
//            y=false;
        }


    }
}
