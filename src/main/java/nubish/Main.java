package nubish;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX application that shows Nubish as a WhatsApp-style chat.
 */
public class Main extends Application {
    private static final double WINDOW_WIDTH = 520;
    private static final double WINDOW_HEIGHT = 680;
    private static final double MESSAGE_WIDTH_RATIO = 0.78;

    private final Nubish nubish = new Nubish();

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private TextField input;

    @FXML
    private Button sendButton;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        stage.setTitle("Nubish");
        stage.setScene(scene);
        stage.show();

        addNubishMessage(nubish.start());
        addNubishMessage(nubish.getTaskList());
        addNubishMessage(nubish.remind());
        chatBox.heightProperty().addListener((observable) -> scrollToLatestMessage());
    }

    @Override
    public void stop() {
        nubish.save();
    }

    @FXML
    private void handleUserInput() {
        String command = input.getText().trim();
        if (command.isEmpty()) {
            return;
        }

        addUserMessage(command);
        input.clear();

        String response = nubish.getResponse(command);
        addNubishMessage(response);

        if (command.equalsIgnoreCase("printBye")) {
            input.setDisable(true);
            sendButton.setDisable(true);
        }
    }

    private void addUserMessage(String message) {
        chatBox.getChildren().add(createMessageRow(message, true));
        scrollToLatestMessage();
    }

    private void addNubishMessage(String message) {
        if (!message.isEmpty()) {
            chatBox.getChildren().add(createMessageRow(message, false));
            scrollToLatestMessage();
        }
    }

    private HBox createMessageRow(String message, boolean isUser) {
        HBox row = loadDialogBox();
        row.setAlignment(isUser ? Pos.TOP_RIGHT : Pos.TOP_LEFT);

        Label bubble = (Label) row.lookup("#bubble");
        bubble.setText(message);
        bubble.setTextOverrun(OverrunStyle.CLIP);
        bubble.setMinHeight(Region.USE_PREF_SIZE);
        bubble.setPrefHeight(Region.USE_COMPUTED_SIZE);
        bubble.setMaxHeight(Double.MAX_VALUE);
        bubble.maxWidthProperty().bind(chatScrollPane.widthProperty().multiply(MESSAGE_WIDTH_RATIO));
        bubble.getStyleClass().add(isUser ? "user-bubble" : "nubish-bubble");
        if (!isUser && message.contains("\n")) {
            bubble.getStyleClass().add("ascii-bubble");
        }

        Label avatarLabel = (Label) row.lookup("#avatarLabel");
        avatarLabel.setText(isUser ? "You" : "N");

        StackPane avatar = (StackPane) row.lookup("#avatar");
        avatar.getStyleClass().add(isUser ? "user-avatar" : "nubish-avatar");

        Region leftSpacer = (Region) row.lookup("#leftSpacer");
        Region rightSpacer = (Region) row.lookup("#rightSpacer");
        Node leftTail = row.lookup("#leftTail");
        Node rightTail = row.lookup("#rightTail");
        StackPane bubbleGroup = (StackPane) row.lookup("#bubbleGroup");
        StackPane.setAlignment(bubble, isUser ? Pos.TOP_RIGHT : Pos.TOP_LEFT);
        StackPane.setAlignment(leftTail, Pos.TOP_LEFT);
        StackPane.setAlignment(rightTail, Pos.TOP_RIGHT);

        setVisible(leftTail, !isUser);
        setVisible(rightTail, isUser);

        if (isUser) {
            row.getChildren().setAll(leftSpacer, bubbleGroup, avatar);
        } else {
            row.getChildren().setAll(avatar, bubbleGroup, rightSpacer);
        }

        return row;
    }

    private HBox loadDialogBox() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Could not load dialog box FXML.", e);
        }
    }

    private void setVisible(Node node, boolean isVisible) {
        node.setVisible(isVisible);
        node.setManaged(isVisible);
    }

    private void scrollToLatestMessage() {
        Platform.runLater(() -> chatScrollPane.setVvalue(1.0));
    }
}
