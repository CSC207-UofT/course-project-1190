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

public class ChoiceDialog extends Dialog {

    private static final File TEMPLATE = new File("src/main/assets/choiceDialog.fxml");
    private Button[] buttons = new Button[6];

    /**
     * Constructs an instance given a user prompt.
     *
     * @param prompt The user prompt.
     */
    public ChoiceDialog(String prompt, String[] buttonPrompt) throws IOException {
        super(prompt);

        URL url = TEMPLATE.toURI().toURL();
        Scene sc = new Scene(FXMLLoader.load(url));
        stage.setScene(sc);

        assert buttonPrompt.length == buttons.length;

        for (int i = 0; i < buttons.length; i++) {
            String id = "#button" + (i + 1);
            buttons[i] = (Button) sc.lookup(id);
            buttons[i].setText(buttonPrompt[i]);
        }

        Label label = (Label) sc.lookup("#prompt");
        label.setText(prompt);
    }

    public void addOnClickedButton(EventHandler<MouseEvent> handler) {
        for (Button b : buttons) {
            b.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        }
    }


}
