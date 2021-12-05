package graphics;

import game.Game;
import game.GameSeeder;
import game.Serializer;
import graphics.dialog.BoolDialog;
import graphics.dialog.TextDialog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import utils.Point2D;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Separate main file, only to be used for the graphics branch.
 */
public class GraphicsMain extends Application {

    private Scene mainScene;
    private DialogPresenter dp;
    private RenderPane renderPane;
    private int boardSize;

    private GameSeeder gameSeeder;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        // Dependency extracted from DialogPresenter
        BoolDialog bd = new BoolDialog("User save state detected. Would you like" +
                " to load it?");
        TextDialog td = new TextDialog("Please enter the size of the board.");

        dp = new DialogPresenter(bd, td);
        dp.addPropertyChangeListener(evt -> {
            String propertyName = evt.getPropertyName();
            if (propertyName.equals("done")) {
                try {
                    this.onDialogsPresented(mainStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        dp.present();
    }

    // Essentially "does everything else" after the dialogs are finished presenting.
    //TODO: Bloated method, extract some code from it
    private void onDialogsPresented(Stage mainStage) throws IOException {

        this.boardSize = dp.getRequestedBoardSize();

        mainStage.setTitle("1190");
        mainStage.setResizable(true);

        // Init RenderPane and add to scene graph
        if (dp.requestedLoadFromSave()) {
            Game g = Serializer.deserialize();

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
        URL url = new File("src/main/assets/main.fxml").toURI().toURL();
        Scene fxmlScene = new Scene(FXMLLoader.load(url));
        mainStage.setScene(fxmlScene);

        ScrollPane sp = (ScrollPane) fxmlScene.lookup("#renderPaneParent");
        sp.setContent(renderPane.getCanvas());
        Button playButton = (Button) fxmlScene.lookup("#playButton");
        PlayButtonController pbc = new PlayButtonController(playButton);
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            renderPane.changeGameState();
        });

        Button saveButton = (Button) fxmlScene.lookup("#saveButton");
        saveButton.setOnMouseClicked(event -> {
            renderPane.resetGameToBaseState();
            Serializer.serialize(renderPane.getGame());
        });

        // Show stage
        mainStage.show();
    }
}
