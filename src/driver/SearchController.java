package driver;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SearchController {
    private File selectedFile;

    public void handleBrowse(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        
        // Adding filter for txt files only
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
        } else {
            showAlert("No file selected", "Please select a file to proceed.", null);
        }
    }

    public void handleParse() {
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                parseContent(content);
            } catch (IOException e) {
                showAlert("Error reading file", "Error reading file: " + e.getMessage(), null);
            }
        } else {
            showAlert("No file selected", "Please select a file to parse.", null);
        }
    }

    // Method to integrate parsing logic
    private void parseContent(String content) {
        Tokenizer tokenizer = new Tokenizer(content);
        List<String> tokens = tokenizer.generateTokens();

        if (tokenizer.isErrorOccurred()) {
            return; // Exit the program or handle the error as needed
        }

        Parser parser = new Parser(tokens);
        parser.parse();

        if (parser.isErrorOccurred()) {
            showAlert("Parsing Failed", "Parsing Failed!!!", "fail.png");
        } else {
            showAlert("Parsing Successful", "Parsing was successful!", "success.png");
           showAlert(parser.getError() , "Error", "Solve it ....");

            
        }

        System.out.println("Parsing content: " + content);
    }
///// View parsing successful or parsing failed
    private void showAlert(String title, String message, String imageName) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        if (imageName != null && !imageName.isEmpty()) {
            // Load image using resource path
            Image image = new Image(getClass().getResourceAsStream("/images/" + imageName));
            if (image != null) {
                ImageView imageView = new ImageView(image);
                alert.setGraphic(imageView);
            } else {
            	// debug needed
                System.out.println("Image resource not found: " + imageName);
            }
        }

        alert.showAndWait();
    }

   
    private Image loadImage(String fileName) {
        try {
            // Load image using absolute path
            String absolutePath = new File("images/" + fileName).getAbsolutePath();
            System.out.println("Absolute path to image: " + absolutePath); // Print absolute path
            return new Image(new FileInputStream(absolutePath));
        } catch (FileNotFoundException e) {
            // debug , handle needed
            System.out.println("Image file not found: " + fileName);
            return null;
        }
    }


   
}
