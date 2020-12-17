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




}

