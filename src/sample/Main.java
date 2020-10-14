package sample;

import BasicIO.BasicIO;
import BasicIO.LoginData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        if(LoginController.scene==null){
        FXMLLoader loader= new FXMLLoader();
        Parent root = loader.load(getClass().getResource("LoginScene.fxml").openStream());
        LoginController loginController= loader.getController();
        loginController.setStage(primaryStage);
        LoginController.scene=new Scene(root, 300, 275);}

        BasicIO<LoginData> basicIO= new BasicIO<LoginData>();
        List<LoginData> list;
        File file= new File("src/data/userIDs/list.dat");
        if(file.exists()) list= (LinkedList<LoginData>) basicIO.getList(new LinkedList<>());
        else{list= (LinkedList<LoginData>) basicIO.getList(new LinkedList<>());}

        LoginController.basicIO= basicIO;
        LoginController.list= list;
        SignupController.basicIO= basicIO;
        SignupController.list= list;

        primaryStage.setTitle("Spa-Management-Java");
        primaryStage.setScene(LoginController.scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
