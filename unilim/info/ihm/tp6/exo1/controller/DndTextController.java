package unilim.info.ihm.tp6.exo1.controller;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;

public class DndTextController {

    public static void manageSourceDragAndDrop(Text source) {
        // Écouteur d'événement DRAG_DETECTED
        source.setOnDragDetected(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            Dragboard dragboard = source.startDragAndDrop(TransferMode.ANY);
            dragboard.setContent(content);
        });

        source.setOnDragDone(event -> {
            if (event.getEventType() == DragEvent.DRAG_DROPPED) {
                String droppedText = event.getDragboard().getString();

                source.setText("");
                ((Text) event.getTarget()).setText(droppedText);
            }
        });
    }

    public static void manageTargetDragAndDrop(Text target) {
        // Écouteur d'événement DRAG_OVER
        target.setOnDragOver(event -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });

        // Écouteur d'événement DRAG_ENTERED
        target.setOnDragEntered(event -> {
            target.setStyle("-fx-background-color: #ccc");
        });

        // Écouteur d'événement DRAG_EXITED
        target.setOnDragExited(event -> {
            target.setStyle("-fx-background-color: #fff");
        });
        
        // Écouteur d'événement DRAG_DROPPED
        target.setOnDragDropped(event -> {
            String droppedText = event.getDragboard().getString();

            target.setText(droppedText);

            target.setStyle("-fx-background-color: #fff");
        });
    }
}
