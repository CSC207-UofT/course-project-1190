package graphics.dialog;

import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This class is an abstraction of any small pop-up window prompting the user to input
 * some values.
 */
public abstract class Dialog {
    protected Stage stage;
    protected Label label;

    /**
     * Constructs an instance given a user prompt.
     * @param prompt The user prompt.
     */
    public Dialog(String prompt) {
        stage = new Stage();
        stage.setTitle("User Dialog");
        stage.setResizable(false);
    }

    /**
     * Makes the instance visible for interaction.
     */
    public void show() {
        stage.show();
    }

    /**
     * Makes the instance invisible to prevent further interaction.
     */
    public void hide() {
        stage.hide();
    }
}
