package graphics;

import graphics.dialog.BoolDialog;
import graphics.dialog.TextDialog;
import javafx.beans.InvalidationListener;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//TODO: Not happy with class name

// NOTE: I think the class can be generalized to work with N dialogs if needed in the future.
/**
 * This class packages instructions for presenting the user with dialogs,
 * providing a much more minimal interface.
 */
public class DialogPresenter {

    private boolean requestToLoadFromSave = false;
    private int requestedBoardSize = 20;
    private BoolDialog bd;
    private TextDialog td;
    private ArrayList<InvalidationListener> listeners = new ArrayList<>();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Creates a new instance of this class.
     * @throws IOException Thrown when any Dialog in this constructor throws such an exception.
     */
    public DialogPresenter(BoolDialog bd, TextDialog td) throws IOException {
        this.bd = bd;
        this.td = td;
        bd.addOnClickedYes(event -> {
            System.out.println("User clicked 'Yes'");
            requestToLoadFromSave = true;
            pcs.firePropertyChange("done", false, true);
            bd.hide();
        });
        bd.addOnClickedNo(event -> {
            System.out.println("User clicked 'No'");
            requestToLoadFromSave = false;
            bd.hide();
            td.show();
        });
        td.addOnKeyPressed(this::onUserSubmit);
        td.addOnClickedButton(this::onUserSubmit);
    }

    /**
     * Presents the contained dialogs to the user
     */
    public void present() {
        File file = new File("game.ser");
        if (file.exists()) {
            bd.show();
        } else {
            td.show();
        }
    }

    private void onUserSubmit(Event event) {
        try {
            // Suppress response if user typed in non-ENTER key
            if (event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                if (keyEvent.getCode() != KeyCode.ENTER) {
                    return;
                }
            }
            requestedBoardSize = Integer.parseInt(td.getText());
            td.clearErrorMsg();
            td.hide();
            pcs.firePropertyChange("done", false, true);
            System.out.printf("User entered integer '%d'.\n", requestedBoardSize);
        } catch (NumberFormatException nfe) {
            td.setErrorMsg("Input is not a valid integer.");
            System.out.printf("User entered non-integer '%s'.\n", td.getText());
        } catch (Exception e) {
            td.setErrorMsg("An unknown error occurred.");
        }
    }

    //-------------- GETTERS/ADDERS --------------

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public boolean requestedLoadFromSave() {
        return this.requestToLoadFromSave;
    }

    public int getRequestedBoardSize() {
        return this.requestedBoardSize;
    }
}
