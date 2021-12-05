package graphics.dialog;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Represents a dialog window that presents the user with a blank text field.
 */
public class TextDialog extends Dialog {

    private static final File TEMPLATE = new File("src/main/assets/textDialog.fxml");
    private TextField textField;
    private Label errorMsg;
    private Button okButton;

    /**
     * Constructs an instance given a user prompt.
     * @param prompt The user prompt.
     * @throws IOException Occurs when something goes wrong while loading the FXML template.
     */
    public TextDialog(String prompt) throws IOException {
        super(prompt);

        URL url = TEMPLATE.toURI().toURL();
        Scene sc = new Scene(FXMLLoader.load(url));
        stage.setScene(sc);

        label = (Label) sc.lookup("#prompt");
        label.setText(prompt);
        errorMsg = (Label) sc.lookup("#errorMsg");
        clearErrorMsg();
        textField = (TextField) sc.lookup("#textField");
        okButton = (Button) sc.lookup("#button");
    }

    /**
     * Displays an error message to the user via the dialog.
     * @param msg The error message.
     */
    public void setErrorMsg(String msg) {
        errorMsg.setText("Error: " + msg);
    }

    /**
     * Clears the error message.
     */
    public void clearErrorMsg() { errorMsg.setText(""); }

    /**
     * Fetches the text currently in the text field.
     * @return The text in the text field.
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Adds an observer to this instance, which is triggered when the user presses the "OK" button.
     * @param handler The observer object.
     */
    public void addOnClickedButton(EventHandler<MouseEvent> handler) {
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }

    /**
     * Adds an observer to this instance, which is triggered when the user presses a key
     * while in the text field (ie. the user is typing).
     * @param handler The observer object.
     */
    public void addOnKeyPressed(EventHandler<KeyEvent> handler) {
        textField.addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }
}
