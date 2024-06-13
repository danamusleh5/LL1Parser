package driver;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchLayout {
    private VBox layout;
    private Label instructionLabel;
    private Button browseButton;
    private Button parseButton;
    private ImageView iconView;
    private SearchController controller;

    public SearchLayout(Stage primaryStage) {
        // Initialize controller
        controller = new SearchController();                                      

        // Create the UI components
        instructionLabel = new Label("Click Parse to proceed...");
        browseButton = new Button("Browse");
        parseButton = new Button("Parse!");
        parseButton.setStyle("-fx-background-color: #90EE90;"); 

        // Create ImageView for icon
        iconView = new ImageView(new Image("images/search.jpg")); //// cHANGE THE ICON 
        iconView.setFitWidth(300);
        iconView.setFitHeight(300);
        
        primaryStage.getIcons().add(new Image("images/search.jpg ")); 

        // VBox layout
        layout = new VBox(10); // Spacing of 10 between elements
        layout.getChildren().addAll(iconView, instructionLabel, browseButton, parseButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;"); // Padding and center alignment

        // Set up actions
        browseButton.setOnAction(e -> controller.handleBrowse(primaryStage));
        parseButton.setOnAction(e -> controller.handleParse());
    }

    public VBox getLayout() {
        return layout;
    }
}
