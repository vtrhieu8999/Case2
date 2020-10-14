package sample;

import BasicIO.BasicIO;
import BasicIO.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Func;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginController {
    private static Stage mStage;
    public static Scene scene;
    public static BasicIO<LoginData> basicIO;
    public static List<LoginData> list;

    @FXML private PasswordField passwordField;
    @FXML private TextField usernameField;


    public void setStage(Stage mStage) {
        LoginController.mStage = mStage;
    }

    @FXML private Text actiontarget;


    @FXML protected void loginPressed(ActionEvent event){
        actiontarget.setText("Log in button pressed");
        String username = usernameField.getText();
        String password = passwordField.getText();
        String path =basicIO.findUser(list ,username, password);
        if(Pattern.compile("^.+Customer/.+$").matcher(path).matches()) path= "Customer/"+ path;
        else path= "Staff/"+ path;
        Object user= Func.readFile(path);
        if(user!=null)
        System.out.println("hello");
    }

    @FXML protected void signupPressed(ActionEvent actionEvent) throws IOException {
        if(SignupController.scene==null)
        {
            Node node= (Node) actionEvent.getSource();
            FXMLLoader loader = new FXMLLoader();
            SignupController.mStage= (Stage) node.getScene().getWindow();
            SignupController.mStage.close();
            Parent root = loader.load(getClass().getResource("SignupScene.fxml").openStream());
            SignupController.mStage.setUserData(basicIO);
            SignupController signupController = loader.getController();
            signupController.setStage(SignupController.mStage);
            SignupController.scene= new Scene(root);

        }
        mStage.setTitle("Sign-Up");
        mStage.setScene(SignupController.scene);
        mStage.show();

    }


}
