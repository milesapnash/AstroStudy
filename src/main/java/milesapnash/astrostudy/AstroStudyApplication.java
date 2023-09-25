package milesapnash.astrostudy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import milesapnash.astrostudy.controllers.DataController;
import milesapnash.astrostudy.controllers.SetupController;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AstroStudyApplication extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    try {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
      final Parent root = loader.load();
      final Scene scene = new Scene(root);
      final URL url = this.getClass().getResource("application.css");

      if (url == null) {
        System.out.println("CSS Resource not found.");
        System.exit(-1);
      }

      stage.setTitle("AstroStudy");
      stage.getIcons().add(new Image("file:icon.png"));
      stage.setResizable(false);

      scene.getStylesheets().add(url.toExternalForm());
      stage.setScene(scene);
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchScene(ActionEvent event, String fileName){
    final Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    try {
      final FXMLLoader loader = new FXMLLoader(AstroStudyApplication.class.getResource(fileName + "-view.fxml"));
      final Parent root = loader.load();
      final Scene scene = new Scene(root);
      final URL url = AstroStudyApplication.class.getResource("application.css");

      if (url == null) {
        System.out.println("CSS Resource not found.");
        System.exit(-1);
      }

      scene.getStylesheets().add(url.toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static <T> void switchScene(ActionEvent event, String fileName, T data){
    final Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    try {
      final FXMLLoader loader = new FXMLLoader(AstroStudyApplication.class.getResource(fileName + "-view.fxml"));
      final Parent root = loader.load();
      final Scene scene = new Scene(root);
      final URL url = AstroStudyApplication.class.getResource("application.css");

      if (url == null) {
        System.out.println("CSS Resource not found.");
        System.exit(-1);
      }

      DataController controller = loader.getController();
      controller.parseData(data);

      scene.getStylesheets().add(url.toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static <T> void setupScene(ActionEvent event, String fileName){
    final Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    try {
      final FXMLLoader loader = new FXMLLoader(AstroStudyApplication.class.getResource(fileName + "-view.fxml"));
      final Parent root = loader.load();
      final Scene scene = new Scene(root);
      final URL url = AstroStudyApplication.class.getResource("application.css");

      if (url == null) {
        System.out.println("CSS Resource not found.");
        System.exit(-1);
      }

      SetupController controller = loader.getController();
      controller.setup();

      scene.getStylesheets().add(url.toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean validateEmail(String input){
    final Matcher emailMatcher = Pattern.compile("^(.+)@(.+)$").matcher(input);
    return emailMatcher.find();
  }
}
