package driver;
import java.io.*;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*Dana Musleh 
 * 1162145
 * LL1 Parser Project - Compiler 
 */


public class Main extends Application{
	
	
    public static void main(String[] args) {
    	
    	
	    Application.launch(args);
    }
    
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//BrowseFile scene = new BrowseFile(primaryStage);
		SearchLayout searchLayout = new SearchLayout(primaryStage);
        Scene searchScene = new Scene(searchLayout.getLayout(),500,450);
        
        primaryStage.setScene(searchScene);
        primaryStage.setTitle("LL1 Parser");
        primaryStage.show();
	}

}
   

/// This was for testing (Without Browse)  Debug Needed

//
//	        String filePath = "file.txt"; // Path to your input file
//
//        try {
//            String input = readFileContent(filePath); // Read input from file
//            Tokenizer tokenizer = new Tokenizer(input);
//            List<String> tokens = tokenizer.generateTokens();
//            for (String string : tokens) {
//    			System.out.println(string);
//  		}
//
//            if (tokenizer.isErrorOccurred()) {
//                return; // Exit the program or handle the error as needed
//            }
//
//            Parser parser = new Parser(tokens);
//            parser.parse();
//
//            if (parser.isErrorOccurred()) {
//	            System.out.println("Parsing Failed!!!");
//            } else {
//	            System.out.println("Parsing Successful!!!");
//
//            }
//        } catch (IOException e) {
//            System.out.println("Parsing Failed!!!");
//
//        }
//    }
//
//    private static String readFileContent(String filePath) throws IOException {
//        StringBuilder fileContent = new StringBuilder();
//        File file = new File(filePath);
//        if (!file.exists() || !file.isFile()) {
//            throw new FileNotFoundException("File not found: " + filePath);
//        }
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                fileContent.append(line).append("\n");
//            }
//        }
//        return fileContent.toString();
//    }
    