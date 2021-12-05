package graphics;

import game.Game;
import game.GameSeeder;
import game.Serializer;
import game.GameMaker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import utils.Point2D;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Separate main file, only to be used for the graphics branch.
 */
public class GraphicsMain extends Application {

    private static Scene mainScene;
    //NOTE: Not sure if storing RenderPane as private static is clean, but it's a temp solution at least
    private static RenderPane renderPane;
    private int boardSize;
    private GameSeeder gameSeeder;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        GameMaker gameMaker = new GameMaker();

        int[] sizeLoad  = gameMaker.getBoardSize();

        this.boardSize = sizeLoad[0];
        int load = sizeLoad[1];

        mainStage.setTitle("1190");

        Game g;
        // Init RenderPane and add to scene graph
        if (load == 1) {
            g = Serializer.deserialize();
            int size = g.getSize();
            renderPane = new RenderPane(g, new Point2D(32 * size,
                    32 * size));
           this.gameSeeder = new GameSeeder(g);
        }
        else {
            g = new Game(this.boardSize);

            renderPane = new RenderPane(g, new Point2D(32 * this.boardSize,
                    32 * this.boardSize));
          
          
          this.gameSeeder = new GameSeeder(g);

        //TODO: to hook up with GUI
        this.gameSeeder.addGoal(new Point2D(17, 17));
        this.gameSeeder.addDownAlligatorDen(new Point2D(12, 13));
        this.gameSeeder.addRightAlligatorDen(new Point2D(7,8));
        this.gameSeeder.addChasingElement(new Point2D(10,5), 30);
        this.gameSeeder.addChasingElement(new Point2D(5,16), 15);
      
      //TODO

      this.gameSeeder.addPortal(new Point2D(5, 15));
      this.gameSeeder.addPortal(new Point2D(3, 10));
      this.gameSeeder.addPortal(new Point2D(16, 7));
      this.gameSeeder.addRock(new Point2D(15, 15));


        }
        


        renderPane.start();

        // Load resources using FXML
        // NOTE: This method of loading resources may not work when packaged as a JAR
        URL url = new File("src/main/assets/testScene.fxml").toURI().toURL();
        Scene fxmlScene = new Scene(FXMLLoader.load(url));
        mainStage.setScene(fxmlScene);

        // TODO: Extract to another class later
        BorderPane bp = (BorderPane) fxmlScene.lookup("#layout");
        bp.setCenter(renderPane.getAnchor());
        Button playButton = (Button) fxmlScene.lookup("#playButton");
        PlayButtonController pbc = new PlayButtonController(playButton);
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (pbc.isPlayMode()) {
                renderPane.stop();
            } else {
                renderPane.start();
            }
        });
        // TODO: Extract to another class later
        Button saveButton = (Button) fxmlScene.lookup("#saveButton");
        saveButton.setOnMouseClicked(event -> {
            Serializer.serialize(renderPane.getGame());
        });

        // Show stage
        mainStage.show();
    }


}
