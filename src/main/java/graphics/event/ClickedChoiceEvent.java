package graphics.event;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.Button;

public class ClickedChoiceEvent extends Event {

    private Button choiceButton;
    private int choice;

    public ClickedChoiceEvent(EventType<? extends Event> eventType, int choice, Button choiceButton) {
        super(eventType);
        this.choice = choice;
        this.choiceButton = choiceButton;
    }

    public int getChoice() {
        return choice;
    }


}
