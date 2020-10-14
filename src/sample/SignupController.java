package sample;

import BasicIO.BasicIO;
import BasicIO.LoginData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Func;
import model.Signup;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class SignupController {
    static Stage mStage;
    public static Scene scene;
    public static BasicIO<LoginData> basicIO;
    public static List<LoginData> list;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }
    private final String PASSLENGTH_REGEX = "^.{8,32}$";
    private final Pattern pattern1= Pattern.compile(PASSLENGTH_REGEX);
    private final String EMAIL_REGEX= "^.+@.+\\..+$";
    private final Pattern pattern2= Pattern.compile(EMAIL_REGEX);

    @FXML private PasswordField passwordField;
    @FXML private Text passwordText;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Text confirmPasswordText;
    @FXML private TextField emailField;
    @FXML private Text emailText;
    @FXML private TextField usernameField;
    @FXML private TextField name;

    @FXML private void signupPressed(ActionEvent evt){

        if(isCorrectForm()){
        Signup.signUp(basicIO, list ,name.getText(),usernameField.getText(),
                passwordField.getText(), emailField.getText());
        }
    }

    @FXML private void loginPressed(ActionEvent evt){
        mStage.setTitle("Spa-Management-Java");
        mStage.setScene(LoginController.scene);
        mStage.show();
    }

    @FXML void passwordLength(KeyEvent keyEvent) {
//        String regex= "^//w{8,32}$";
        if(validPassword()){
            passwordText.setText("Good");
        }else{passwordText.setText("Is not long enough");}
    }
    private boolean validPassword(){
        return pattern1.matcher(passwordField.getText()).matches();
    }

    @FXML void confirmPass(KeyEvent keyEvent) {
        if(equalPassword()) confirmPasswordText.setText("");
        else confirmPasswordText.setText("Your passwords are not equal");
    }
    private boolean equalPassword(){
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    @FXML void validEmail(KeyEvent keyEvent){
        if(isValidEmail()) emailText.setText("");
        else emailText.setText("Email is not valid");
    }
    @FXML boolean isValidEmail(){
        return pattern2.matcher(emailField.getText()).matches();
    }
    private boolean isCorrectForm(){
        return validPassword()||isValidEmail()||equalPassword();
    }


}
