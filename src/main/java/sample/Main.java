package sample;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }
    private BallComponent playerball;
    private List<ObstacleComponent> total_obstacles;
    int count=0;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(580);
        settings.setHeight(720);
        settings.setTitle("Color Switch 2");
        settings.setMainMenuEnabled(true);

    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                playerball.up();
                System.out.println(total_obstacles.size());
                for(int i=0;i<total_obstacles.size();i++)
                    total_obstacles.get(i).getEntity().translateY(20);






            }

            @Override
            protected void onActionEnd() {
                playerball.stop();


            }
        }, KeyCode.W);

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {


    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameEntityFactory());
        getGameScene().setBackgroundColor(Color.rgb(5, 5, 5));
        initGameObjects();

    }

    @Override
    protected void onUpdate(double tpf) {
//        var a=getGameWorld().getEntitiesByComponent(ObstacleComponent.class);
//        int c=0;
//        f.physics.setAngularVelocity(1);
//        f.rotateBy(1);
//
//            if(f.getEntity().getY()>200)
//                f.down(-1*420);
//            else
//                f.stop();
        boolean a=false;
//        for(int i=0;i<total_obstacles.size();i++)
////            if(playerball.getEntity().getY()<=total_obstacles.get(i).getEntity().getY())
//                total_obstacles.get(i).getEntity().translateY(10);
//        if(a)
//        {
//            for(int i=0;i<total_obstacles.size();i++)
//                total_obstacles.get(i).getEntity().translateY(10);
//
//        }
//        for(int i=0;i<total_obstacles.size();i++)
//            if(total_obstacles.get(i).getEntity().getY()>getAppHeight()/2-100)
//                total_obstacles.get(i).getEntity().translateY(-5);

//
//
//        var a=getGameWorld().getEntitiesByType(EntityType.OBSTACLE);
//        for (var x:a)
//        {
////            x.rotateBy(1);
//
//        }

    }

    @Override
    protected void initPhysics() {
//        getPhysicsWorld().setGravity(0, 9);
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.OBSTACLE) {


            @Override
            protected void onCollision(Entity a, Entity b) {
//                System.out.println("helloasa");
//                System.out.println(a.getComponents());
//                System.out.println(b.getComponents());
                try {

                    if(a.getComponent(BallComponent.class).getColor().equals(b.getComponent(ObstacleComponent.class).getColor()))
                    {
                        System.out.println("1");
//                        ObstacleComponent m=b.getComponent(ObstacleComponent.class);
//                        System.out.println(m.idef);

                    }
                    else
                    {
                        System.out.println("collision"+count);

                    }
                    count++;
                }
                catch (Exception e)
                {
                    if(b.getComponent(BallComponent.class).getColor().equals(a.getComponent(ObstacleComponent.class).getColor()))
                    {
                        System.out.println("2");
//                        ObstacleComponent m=b.getComponent(ObstacleComponent.class);
//                        System.out.println(m.idef);

                    }
                    else
                        System.out.println("collision"+count);
                    count++;
                }


            }


        });


    }
    void createRectangle(double h,double w)
    {
        h-=100;
        w+=100;

        Entity obs1 =spawn("obstacle", w, h);
        if(total_obstacles==null)
            total_obstacles=new ArrayList<>();
        total_obstacles.add(obs1.getComponent(ObstacleComponent.class));

        Entity obs2 =spawn("obstacle", w, h);
        Rectangle a=new Rectangle(0,0,10,200);
        a.setFill(Color.GOLD);
        obs2.getViewComponent().clearChildren();
        obs2.getViewComponent().addChild(a);
        obs2.rotateBy(-90);
        ObstacleComponent temp=obs2.getComponent(ObstacleComponent.class);
        temp.setColor(Color.GOLD);
        total_obstacles.add(obs2.getComponent(ObstacleComponent.class));

        Entity obs3 =spawn("obstacle", w, h);
        a=new Rectangle(0,0,10,200);
        a.setFill(Color.BLUE);
        obs3.getViewComponent().clearChildren();
        obs3.getViewComponent().addChild(a);
        obs3.setRotation(90);
        temp=obs3.getComponent(ObstacleComponent.class);
        temp.setColor(Color.BLUE);
        total_obstacles.add(obs3.getComponent(ObstacleComponent.class));

        Entity obs4 =spawn("obstacle", w, h);
        a=new Rectangle(0,0,10,200);
        a.setFill(Color.BLUEVIOLET);
        obs4.getViewComponent().clearChildren();
        obs4.getViewComponent().addChild(a);
        obs4.setRotation(180);
        temp=obs4.getComponent(ObstacleComponent.class);
        temp.setColor(Color.BLUEVIOLET);
        total_obstacles.add(obs4.getComponent(ObstacleComponent.class));
    }


    private void initGameObjects() {


        Entity ball = spawn("ball", getAppWidth() /2, getAppHeight()  - 30);
        playerball = ball.getComponent(BallComponent.class);
        for(int i=0;i<3;i++)
            createRectangle(getAppHeight()/2-500*i,getAppWidth() /2);

    }



//    @Override
//    protected void initUI() {
//
//
//    }
}
