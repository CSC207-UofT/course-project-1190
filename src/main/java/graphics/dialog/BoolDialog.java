package graphics.dialog;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Represents a dialog window that presents the user with a boolean choice.
 */
public class BoolDialog extends Dialog {

    private static final File TEMPLATE = new File("src/main/assets/boolDialog.fxml");
    private Button yesButton;
    private Button noButton;

    /**
     * Constructs an instance given a user prompt.
     * @param prompt The user prompt.
     * @throws IOException Occurs when something goes wrong while loading the FXML template.
     */
    public BoolDialog(String prompt) throws IOException {
        super(prompt);
        URL url = TEMPLATE.toURI().toURL();
        Scene sc = new Scene(FXMLLoader.load(url));
        stage.setScene(sc);

        yesButton = (Button) sc.lookup("#yesButton");
        noButton = (Button) sc.lookup("#noButton");
        label = (Label) sc.lookup("#prompt");
        label.setText(prompt);
    }

    /**
     * Adds an observer to this instance, which is triggered when the user presses the "YES" button.
     * @param handler The observer object.
     */
    public void addOnClickedYes(EventHandler<MouseEvent> handler) {
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }

    /**
     * Adds an observer to this instance, which is triggered when the user presses the "NO" button.
     * @param handler The observer object.
     */
    public void addOnClickedNo(EventHandler<MouseEvent> handler) {
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }
}
