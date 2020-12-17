package sample;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static java.lang.System.exit;

public class Main extends GameApplication {
    private BallComponent playerball;
    private List<ObstacleComponent> total_obstacles;
    private List<Boolean> visited;
    private int count = 0;
    private int total_obstacles_no=0;
    private int total_stars_no=0;
    private int total_colour_changer_no=0;
    private int total_life_giver_no=0;

    private List<ObstacleComponent> total_stars;
    private List<ObstacleComponent> total_color_changers;
    private List<ObstacleComponent> total_life_giver;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(900);
        settings.setHeight(900);
        settings.setTitle("Color Switch 2");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory()
        {
            @Override
            public FXGLMenu newMainMenu()
            {
                return new MyMenu();
            }
        });
    }


    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                playerball.up();
//                System.out.println(total_obstacles.size());
                for (int i = 0; i < total_obstacles.size(); i++) {
                    total_obstacles.get(i).getEntity().translateY(15);
                    if (playerball.getEntity().getY() < total_obstacles.get(i).getEntity().getY() && visited.get(i) == false) {
                        getGameState().increment("score", 1);
                        visited.set(i, true);

                    }
                }
                for (int i = 0; i < total_stars.size(); i++) {

                    total_stars.get(i).getEntity().translateY(15);
                }
                for (int i = 0; i < total_color_changers.size(); i++) {

                    total_color_changers.get(i).getEntity().translateY(15);
                }
                for (int i = 0; i < total_stars.size(); i++) {

                    total_life_giver.get(i).getEntity().translateY(15);
                }


            }

            @Override
            protected void onActionEnd() {
                playerball.stop();


            }
        }, KeyCode.W);

        onKeyDown(KeyCode.U, () -> {
            play("hello.wav");
        });

    }


    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("life", 1);


    }

    @Override
    protected void initUI() {
        Text score = new Text();

        score.layoutBoundsProperty().addListener((observable, oldValue, newBounds) -> {
            score.setTranslateX(getAppWidth() / 2 - newBounds.getWidth() / 2 + 100);
        });

        score.setTranslateY(50);
        score.setFont(Font.font(36));
        score.setFill(Color.WHITE);
        score.textProperty().bind(getGameState().intProperty("score").asString("score %d"));
        getGameScene().addUINode(score);

        Text life = new Text();

        life.layoutBoundsProperty().addListener((observable, oldValue, newBounds) -> {
            life.setTranslateX(getAppWidth() / 2 - newBounds.getWidth() / 2 - 100);
        });

        life.setTranslateY(50);
        life.setFont(Font.font(36));
        life.setFill(Color.WHITE);
        life.textProperty().bind(getGameState().intProperty("life").asString("life %d"));
        getGameScene().addUINode(life);

    }
    private Entity player;



    @Override
    protected void initGame() {



        getGameWorld().addEntityFactory(new GameEntityFactory());
        getGameScene().setBackgroundColor(Color.rgb(5, 5, 5));
        initGameObjects();


    }

    @Override
    protected void onUpdate(double tpf) {
    if(getGameState().getInt("life")<1)
    {
//        System.out.println("score "+getGameState().getInt("score"));
//        exit(0);

    }
    if(visited!=null &&visited.size()>2&&visited.get(visited.size()-2)==true)
    {
        initObstacles();
        initScoreBooster();
        initLifeGiver();
        initColorChanger();
    }


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

                                                          if (a.getComponent(BallComponent.class).getColor().equals(b.getComponent(ObstacleComponent.class).getColor())) {
                                                              System.out.println("1");
//                        ObstacleComponent m=b.getComponent(ObstacleComponent.class);
//                        System.out.println(m.idef);

                                                          } else {
                                                              System.out.println("collision" + count);

                                                              count++;
                                                          }

                                                      } catch (Exception e) {
                                                          if (b.getComponent(BallComponent.class).getColor().equals(a.getComponent(ObstacleComponent.class).getColor())) {
                                                              System.out.println("2");
//                        ObstacleComponent m=b.getComponent(ObstacleComponent.class);
//                        System.out.println(m.idef);

                                                          } else {
                                                              System.out.println("collision" + count);
                                                              count++;
                                                          }


                                                      }


                                                  }

                                                  @Override
                                                  protected void onCollisionEnd(Entity a, Entity b) {
                                                      super.onCollisionEnd(a, b);
                                                      try {

                                                          if (a.getComponent(BallComponent.class).getColor().equals(b.getComponent(ObstacleComponent.class).getColor())) {
                                                              System.out.println("1");


                                                          } else {
                                                              System.out.println("collision" + count);
                                                              getGameState().increment("life", -1);

                                                              count++;
                                                          }

                                                      } catch (Exception e) {
                                                          if (b.getComponent(BallComponent.class).getColor().equals(a.getComponent(ObstacleComponent.class).getColor())) {
                                                              System.out.println("2");


                                                          } else {
                                                              System.out.println("collision" + count);
                                                              getGameState().increment("life", -1);
                                                              count++;
                                                          }


                                                      }
                                                  }
                                              }

        );
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.ScoreBooster) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                if (a.getType() == EntityType.ScoreBooster)
                {
                    total_stars.remove(a.getComponent(ObstacleComponent.class));

                }

                else
                    {
                        total_stars.remove(b.getComponent(ObstacleComponent.class));

                }

            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                getGameState().increment("score", 100);
                if (a.getType() == EntityType.ScoreBooster)
                {

                    getGameWorld().removeEntity(a);
                }

                else
                {

                    getGameWorld().removeEntity(b);
                }

            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.LifeGiver) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                if (a.getType() == EntityType.LifeGiver)
                {
                    total_life_giver.remove(a.getComponent(ObstacleComponent.class));

                }

                else
                {
                    total_life_giver.remove(b.getComponent(ObstacleComponent.class));

                }

            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                getGameState().increment("life", 1);
                if (a.getType() == EntityType.LifeGiver)
                {

                    getGameWorld().removeEntity(a);
                }

                else
                {

                    getGameWorld().removeEntity(b);
                }

            }
        });
        //gold blue blueviolet red
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL,EntityType.ColorChanger) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                b.removeFromWorld();
                if(a.getType()!=EntityType.BALL) {
                    Entity temp=b;
                    b=a;
                    a=temp;
                }

                    Color fin=a.getComponent(BallComponent.class).getColor();
                    while (a.getComponent(BallComponent.class).getColor()==fin)
                {
                    switch (random(0,4))
                    {
                        case 0:fin=Color.GOLD;break;
                        case 1:fin=Color.BLUE;break;
                        case 2:fin=Color.BLUEVIOLET;break;
                        case 3:fin=Color.RED;break;
                    }

                }
                    a.getViewComponent().clearChildren();
                    Circle c=new Circle(12,fin);
                    a.getViewComponent().addChild(c);
                    a.getComponent(BallComponent.class).setColor(fin);

                    total_color_changers.remove(b.getComponent(ObstacleComponent.class));





            }
        });


    }

    void createRectangle(double h, double w) {
        h -= 200;
        w += 200;
        double s = 350;
        SpawnData data = new SpawnData(w, h);
        data.put("side", s);
        data.put("rx", -s / 2);
        data.put("ry", s / 2);
        Entity obs1 = spawn("obstacle", data);
        obs1.getComponent(ObstacleComponent.class).setYcord(h);

        total_obstacles.add(obs1.getComponent(ObstacleComponent.class));

        Entity obs2 = spawn("obstacle", data);
        obs2.getComponent(ObstacleComponent.class).setYcord(h);
        Rectangle a = new Rectangle(0, 0, 10, s);
        a.setFill(Color.GOLD);
        obs2.getViewComponent().clearChildren();
        obs2.getViewComponent().addChild(a);
        obs2.rotateBy(-90);
        ObstacleComponent temp = obs2.getComponent(ObstacleComponent.class);
        temp.setColor(Color.GOLD);
        total_obstacles.add(obs2.getComponent(ObstacleComponent.class));

        Entity obs3 = spawn("obstacle", data);
        obs3.getComponent(ObstacleComponent.class).setYcord(h);
        a = new Rectangle(0, 0, 10, s);
        a.setFill(Color.BLUE);
        obs3.getViewComponent().clearChildren();
        obs3.getViewComponent().addChild(a);
        obs3.setRotation(90);
        temp = obs3.getComponent(ObstacleComponent.class);
        temp.setColor(Color.BLUE);
        total_obstacles.add(obs3.getComponent(ObstacleComponent.class));

        Entity obs4 = spawn("obstacle", data);
        obs4.getComponent(ObstacleComponent.class).setYcord(h);
        a = new Rectangle(0, 0, 10, s);
        a.setFill(Color.BLUEVIOLET);
        obs4.getViewComponent().clearChildren();
        obs4.getViewComponent().addChild(a);
        obs4.setRotation(180);
        temp = obs4.getComponent(ObstacleComponent.class);
        temp.setColor(Color.BLUEVIOLET);
        total_obstacles.add(obs4.getComponent(ObstacleComponent.class));
    }

    void createFan(double h, double w) {
        h -= 200;
        w += 200;
        double s = 200;
        SpawnData data = new SpawnData(w, h);
        data.put("side", s);
        data.put("rx", 0.0);
        data.put("ry", s);
        Entity obs1 = spawn("obstacle", data);
        obs1.getComponent(ObstacleComponent.class).setYcord(h);
        if (total_obstacles == null)
            total_obstacles = new ArrayList<>();
        total_obstacles.add(obs1.getComponent(ObstacleComponent.class));

        Entity obs2 = spawn("obstacle", data);
        obs2.getComponent(ObstacleComponent.class).setYcord(h);
        Rectangle a = new Rectangle(0, 0, 10, s);
        a.setFill(Color.GOLD);
        obs2.getViewComponent().clearChildren();
        obs2.getViewComponent().addChild(a);
        obs2.rotateBy(90);
        ObstacleComponent temp = obs2.getComponent(ObstacleComponent.class);
        temp.setColor(Color.GOLD);
        total_obstacles.add(obs2.getComponent(ObstacleComponent.class));

        Entity obs3 = spawn("obstacle", data);
        obs3.getComponent(ObstacleComponent.class).setYcord(h);
        a = new Rectangle(0, 0, 10, s);
        a.setFill(Color.BLUE);
        obs3.getViewComponent().clearChildren();
        obs3.getViewComponent().addChild(a);
        obs3.setRotation(180);
        temp = obs3.getComponent(ObstacleComponent.class);
        temp.setColor(Color.BLUE);
        total_obstacles.add(obs3.getComponent(ObstacleComponent.class));

        Entity obs4 = spawn("obstacle", data);
        obs4.getComponent(ObstacleComponent.class).setYcord(h);
        a = new Rectangle(0, 0, 10, s);
        a.setFill(Color.BLUEVIOLET);
        obs4.getViewComponent().clearChildren();
        obs4.getViewComponent().addChild(a);
        obs4.setRotation(270);
        temp = obs4.getComponent(ObstacleComponent.class);
        temp.setColor(Color.BLUEVIOLET);
        total_obstacles.add(obs4.getComponent(ObstacleComponent.class));


    }

    void createTriangle(double h, double w) {
        h -= 200;
        w += 100;
        double s = 400;
        SpawnData data = new SpawnData(w, h);
        data.put("side", s);
        data.put("rx", (-s / 2) / Math.pow(3, 0.5));
        data.put("ry", s / 2);

        Entity obs1 = spawn("obstacle", data);
        obs1.getComponent(ObstacleComponent.class).setYcord(h);
//        obs1.rotateBy(30);
        if (total_obstacles == null)
            total_obstacles = new ArrayList<>();
        total_obstacles.add(obs1.getComponent(ObstacleComponent.class));
        ObstacleComponent temp;
        Rectangle a = new Rectangle(0, 0, 10, s);
        if (random(0, 2) == 0) {
            Entity obs2 = spawn("obstacle", data);
            obs2.getComponent(ObstacleComponent.class).setYcord(h);

            a.setFill(Color.GOLD);
            obs2.getViewComponent().clearChildren();
            obs2.getViewComponent().addChild(a);
            obs2.rotateBy(120);
//        obs2.translateX(-50);
            temp = obs2.getComponent(ObstacleComponent.class);
            temp.setColor(Color.GOLD);
            total_obstacles.add(obs2.getComponent(ObstacleComponent.class));
//
        } else {
            Entity obs3 = spawn("obstacle", data);
            obs3.getComponent(ObstacleComponent.class).setYcord(h);
            a = new Rectangle(0, 0, 10, s);
            a.setFill(Color.BLUE);
            obs3.getViewComponent().clearChildren();
            obs3.getViewComponent().addChild(a);
            obs3.setRotation(120);
            temp = obs3.getComponent(ObstacleComponent.class);
            temp.setColor(Color.BLUE);
            total_obstacles.add(obs3.getComponent(ObstacleComponent.class));

        }


//
        Entity obs4 = spawn("obstacle", data);
        obs4.getComponent(ObstacleComponent.class).setYcord(h);
        a = new Rectangle(0, 0, 10, s);
        a.setFill(Color.BLUEVIOLET);
        obs4.getViewComponent().clearChildren();
        obs4.getViewComponent().addChild(a);
        obs4.setRotation(240);
//        obs4.translateX(180);

        temp = obs4.getComponent(ObstacleComponent.class);
        temp.setColor(Color.BLUEVIOLET);
        total_obstacles.add(obs4.getComponent(ObstacleComponent.class));


    }

    private void initScoreBooster() {
        if(total_stars==null)
            total_stars=new ArrayList<>(10);
        for (int i = total_stars_no; i < total_stars_no+ 5; i++) {
            Entity a = spawn("ScoreBooster", getAppWidth() / 2 - 25, getAppHeight() / 2 + 100 - 500 - 1000 * i);
            a.getComponent(ObstacleComponent.class).setYcord(getAppHeight() / 2 + 100 - 500 - 1000 * i);
            total_stars.add(a.getComponent(ObstacleComponent.class));
        }
        total_stars_no+=5;

    }
    private void initColorChanger()
    {
        if(total_color_changers==null)
            total_color_changers=new ArrayList<>(10);
        for (int i = total_colour_changer_no; i < total_colour_changer_no+ 10; i++) {
            Entity a = spawn("ColorChanger", getAppWidth() / 2 , getAppHeight() / 2  - 750 * i );
            a.getComponent(ObstacleComponent.class).setYcord(getAppHeight() / 2 + - 750 * i);
            total_color_changers.add(a.getComponent(ObstacleComponent.class));
        }
        total_colour_changer_no+=10;

    }
    private void initLifeGiver()
    {
        if(total_life_giver==null)
            total_life_giver=new ArrayList<>(10);
        for (int i = total_life_giver_no; i < total_life_giver_no+ 5; i++) {
            Entity a = spawn("LifeGiver", getAppWidth() / 2-25 , getAppHeight() / 2 -1000  - 2000 * i);
            a.getComponent(ObstacleComponent.class).setYcord(getAppHeight() / 2 -1000 - 2000 * i);
            total_life_giver.add(a.getComponent(ObstacleComponent.class));
        }
        total_life_giver_no+=5;

    }
    private void initObstacles()
    {
        if (total_obstacles == null)
            total_obstacles = new ArrayList<>(100);
        for (int i = total_obstacles_no; i < total_obstacles_no + 20; i++) {
            int x = random(0, 3);
            switch (x) {
                case 0:
                    createRectangle(getAppHeight() / 2 - 700 * i, getAppWidth() / 2);
                    break;
                case 1:
                    createTriangle(getAppHeight() / 2 - 700 * i, getAppWidth() / 2);
                    break;
                case 2:
                    createFan(getAppHeight() / 2 - 700 * i, getAppWidth() / 2);
                    createFan(getAppHeight() / 2 - 700 * i, 50);
                    break;
            }
        }
        total_obstacles_no+=20;
        if(visited==null)
            visited = new ArrayList<>(total_obstacles.size());
        for (int i = visited.size(); i < total_obstacles.size(); i++)
            visited.add(false);

    }

    private void initGameObjects() {

        Entity ball = spawn("ball", getAppWidth() / 2, getAppHeight() - 30);
        playerball = ball.getComponent(BallComponent.class);
        initObstacles();
        initScoreBooster();
        initColorChanger();
        initLifeGiver();





    }
}
class MyMenu extends FXGLMenu {
    public MyMenu() {
        super(MenuType.MAIN_MENU);
        Colorbutton btnPlay =new Colorbutton("Play Game", this::fireNewGame);
        Colorbutton btnQuit =new Colorbutton("Exit Game", this::fireExit);
        Colorbutton btnSave =new Colorbutton("Save Game", this::fireSave);
        Colorbutton btnReload=new Colorbutton("Reload Game",this::createContentLoad);
        var box=new VBox(15,btnPlay,btnQuit,btnSave,btnReload);
        box.setTranslateX(100);
        box.setTranslateY(400);
        getContentRoot().getChildren().addAll(box);

    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull StringBinding stringBinding, @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull String s, @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Node createBackground(double v, double v1) {

        return FXGL.texture("colorswitch.png");
    }

    @NotNull
    @Override
    protected Node createProfileView(@NotNull String s) {
        return new Text();
    }

    @NotNull
    @Override
    protected Node createTitleView(@NotNull String s) {
        return new Text();
    }

    @NotNull
    @Override
    protected Node createVersionView(@NotNull String s) {
        return new Text();
    }
}
 class Colorbutton extends StackPane {

     public Colorbutton(String name, Runnable action) {

         var bg = new Rectangle(200, 40);
         bg.setStroke(Color.WHITE);

         var text = FXGL.getUIFactory().newText(name, Color.WHITE, 18);

         bg.fillProperty().bind(
                 Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.BLACK)
         );

         text.fillProperty().bind(
                 Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.WHITE)
         );

         setOnMouseClicked(e -> action.run());

         getChildren().addAll(bg, text);
     }
 }

//The song is added just for fun and with no intention to copywright .It was downloaded from pagalworld.com
//The color switch image is taken from google images

