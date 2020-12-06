package sample;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.entity.components.TransformComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CircleShapeData;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.collision.shapes.CircleShape;
import com.almasb.fxgl.physics.box2d.common.Rotation;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GameEntityFactory implements EntityFactory {
    @Spawns("ball")
    public Entity newBall(SpawnData data)
    {
        System.out.println("hellos");


        Entity x= entityBuilder()
                .from(data)
                .type(EntityType.BALL)
                .viewWithBBox(new Circle(20,Color.BLUEVIOLET))
                .with(new CollidableComponent(true))
                .with(new BallComponent())
                .build();
        x.getComponent(BallComponent.class).setColor(Color.BLUEVIOLET);
        return x;


    }
    @Spawns("obstacle")
    public  Entity newObstacle(SpawnData data)
    {
        Rectangle a=new Rectangle(0,0,10,200);
//        a.setRotationAxis(new Point3D(10,0,0));
        a.setFill(Color.RED);

//        a.setRotationAxis(new Point3D(1,1,10));

        Entity asa=entityBuilder().from(data)
                .type(EntityType.OBSTACLE)
                .viewWithBBox(a)
                .rotationOrigin(new Point2D(-100,100))
                .with(new CollidableComponent(true))
                .with(new ObstacleComponent())
                .build();
        asa.getComponent(ObstacleComponent.class).setColor(Color.RED);

//        asa.rotateToVector(new Point2D(5,50));

        return asa;

    }

//    public Entity newObstacle(SpawnData data)
//    {
//        double pi=90*2*3.14/7;
//        var arc=new Arc();
//        arc.setStartAngle(0);
//        arc.setCenterY(0);
//        arc.setCenterX(0);
//        arc.setRadiusX(90);
//        arc.setRadiusY(90);
//        arc.setLength(pi);
//        arc.setType(ArcType.OPEN);
//        arc.setStrokeWidth(10);
////        arc.setFill(Color.RED);
//        arc.setStroke(Color.RED);
////        arc.set
//
//        var arc1=new Arc();
//        arc1.setStartAngle(90);
//        arc1.setCenterY(0);
//        arc1.setCenterX(0);
//        arc1.setRadiusX(90);
//        arc1.setRadiusY(90);
//        arc1.setLength(pi);
//        arc1.setType(ArcType.OPEN);
//        arc1.setStrokeWidth(10);
//
//        var arc2=new Arc();
//        arc2.setStartAngle(180);
//        arc2.setCenterY(0);
//        arc2.setCenterX(0);
//        arc2.setRadiusX(90);
//        arc2.setRadiusY(90);
//        arc2.setLength(pi);
//        arc2.setType(ArcType.OPEN);
//        arc2.setStrokeWidth(10);
//        var arc3=new Arc();
//        arc3.setStartAngle(270);
//        arc3.setCenterY(0);
//        arc3.setCenterX(0);
//        arc3.setRadiusX(90);
//        arc3.setRadiusY(90);
//        arc3.setLength(pi);
//        arc3.setType(ArcType.OPEN);
//        arc3.setStrokeWidth(10);
//
////        arc.setFill(Color.RED);
//        arc1.setStroke(Color.BLUE);
//        arc2.setStroke(Color.BLUEVIOLET);
//        arc3.setStroke(Color.YELLOW);
////        PhysicsComponent physics=new PhysicsComponent();
////        ProjectileComponent as=new ProjectileComponent();
////        ProjectileComponent x=new ProjectileComponent(new Point2D(0,0),0);
////        AutoRotationComponent as=new AutoRotationComponent();
////        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(0, 0));
////        physics.setOnPhysicsInitialized(() -> physics.setAngularVelocity(100));
////        physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(1.0f));
////        physics.setBodyType(BodyType.KINEMATIC);
//
//
//
//        return entityBuilder()
//                .from(data)
//                .type(EntityType.OBSTACLE)
//
//                .view(arc)
//                .view(arc1)
//                .view(arc2)
//                .view(arc3)
//                .bbox(new HitBox(BoundingShape.circle(90)))
//                .with(new CollidableComponent(true))
////                .with(physics)
//                .with(new ObstacleComponent())
//                .rotationOrigin(0,0)
//                .build();
//
//
//    }


}

