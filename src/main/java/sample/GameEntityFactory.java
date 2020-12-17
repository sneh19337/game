package sample;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GameEntityFactory implements EntityFactory {
    @Spawns("ball")
    public Entity newBall(SpawnData data)
    {
//        System.out.println("hellos");


        Entity x= entityBuilder()
                .from(data)
                .type(EntityType.BALL)
                .view(new Circle(12,Color.BLUEVIOLET))
                .bbox(new HitBox(BoundingShape.circle(12)))
                .with(new CollidableComponent(true))
                .with(new BallComponent())
                .build();
        x.getComponent(BallComponent.class).setColor(Color.BLUEVIOLET);
        return x;


    }

    @Spawns("obstacle")
    public  Entity newObstacle(SpawnData data)
    {
        double s=0;
        s=data.get("side");
        double rx=data.get("rx");
        double ry=data.get("ry");
        Rectangle a=new Rectangle(0,0,10,s);
//        a.setRotationAxis(new Point3D(10,0,0));
        a.setFill(Color.RED);

//        a.setRotationAxis(new Point3D(1,1,10));

        Entity asa=entityBuilder().from(data)
                .type(EntityType.OBSTACLE)
                .view(a)
                .bbox(new HitBox(BoundingShape.box(1,s)))
                .rotationOrigin(new Point2D(rx,ry))
                .with(new CollidableComponent(true))
                .with(new ObstacleComponent())
                .build();
        asa.getComponent(ObstacleComponent.class).setColor(Color.RED);

//        asa.rotateToVector(new Point2D(5,50));

        return asa;

    }
    @Spawns("ScoreBooster")
    public Entity newScoreBooster(SpawnData data)
    {
        Texture f=texture("star.png");
        f.setFitHeight(50);
        f.setPreserveRatio(true);

        Entity ScoreBooster = entityBuilder().from(data).type(EntityType.ScoreBooster)
                .viewWithBBox(f).collidable().with(new ObstacleComponent())
                .build();
        return ScoreBooster;

    }
    @Spawns("ColorChanger")
    public Entity newColorChanger(SpawnData data)
    {
        double sw=0;
        double sh=0;

        Arc arc=new Arc();
        arc.setCenterX(sw/4);
        arc.setCenterY(sh/15);
        arc.setRadiusX(20);
        arc.setRadiusY(20);
        arc.setLength(90);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.RED);
        arc.setStroke(Color.RED);
        Arc arc1=new Arc();
        arc1.setCenterX(sw/4);
        arc1.setCenterY(sh/15);
        arc1.setRadiusX(20);
        arc1.setRadiusY(20);
        arc1.setStartAngle(90);
        arc1.setLength(90);
        arc1.setType(ArcType.ROUND);
        arc1.setFill(Color.YELLOW);
        arc1.setStroke(Color.YELLOW);
        Arc arc2=new Arc();
        arc2.setCenterX(sw/4);
        arc2.setCenterY(sh/15);
        arc2.setRadiusX(20);
        arc2.setRadiusY(20);
        arc2.setStartAngle(180);
        arc2.setLength(90);
        arc2.setType(ArcType.ROUND);
        arc2.setFill(Color.BLUEVIOLET);
        arc2.setStroke(Color.BLUEVIOLET);
        Arc arc3=new Arc();
        arc3.setCenterX(sw/4);
        arc3.setCenterY(sh/15);
        arc3.setRadiusX(20);
        arc3.setRadiusY(20);
        arc3.setStartAngle(270);
        arc3.setLength(90);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.BLUE);
        arc3.setStroke(Color.BLUE);

        Entity ScoreBooster = entityBuilder().from(data).type(EntityType.ColorChanger).bbox(new HitBox(BoundingShape.circle(20)))
                .view(arc).view(arc1).view(arc2).view(arc3).collidable().with(new ObstacleComponent()).rotationOrigin(0,0)
                .build();
        return ScoreBooster;

    }
    @Spawns("LifeGiver")
    public Entity newLifeGiver(SpawnData data)
    {
        Texture f=texture("heart1.png");
        f.setFitHeight(50);
        f.setPreserveRatio(true);

        Entity ScoreBooster = entityBuilder().from(data).type(EntityType.LifeGiver)
                .viewWithBBox(f).collidable().with(new ObstacleComponent())
                .build();
        return ScoreBooster;

    }




}

