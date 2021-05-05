package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.FileNameMap;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    private TextField regularExpressionField;

    @FXML
    private Button checkButton;

    @FXML
    private Button switchTextCaseButton;

    @FXML
    private Button removeInvisibleCharactersButton;

    @FXML
    private ToggleButton ignoreSpecialCharactersButton;

    private boolean upperCase = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void checkText(){

        String textToCheck = textArea.getText();
        String regularExpression = regularExpressionField.getText();

        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(textToCheck);


        if (matcher.matches()){
            JOptionPane.showMessageDialog(null, "OK");
        }
        else{
            JOptionPane.showMessageDialog(null, "NG");
        }

    }

    public void removeInvisibleCharacters(){
        String text = textArea.getText();
        text = Pattern.compile("\\s").matcher(text).replaceAll("");
        textArea.setText(text);
    }

    public void changeTextCase(){
        String text = textArea.getText();
        if(upperCase){
            textArea.setText(text.toLowerCase());
            upperCase = false;
        }
        else{
            textArea.setText(text.toUpperCase());
            upperCase = true;
        }
    }

    public void openCheatSheet(){
        BorderPane borderPane = new BorderPane();
        File file = new File("src/resources/regexCheatSheet.png");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(image.getHeight());
        imageView.setFitWidth(image.getWidth());
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        borderPane.setCenter(imageView);
        Stage newStage = new Stage();
        newStage.setTitle("Regex cheat sheet");
        newStage.setWidth(image.getWidth()+20);
        newStage.setHeight(image.getHeight()+20);
        Scene scene = new Scene(borderPane);
        newStage.setScene(scene);
        newStage.show();
    }
}
