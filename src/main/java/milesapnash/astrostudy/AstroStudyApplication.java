package milesapnash.astrostudy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class AstroStudyApplication extends Application {
  public static void switchScene(ActionEvent event, String fileName){
    final Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    try {
      final Parent root = new FXMLLoader(AstroStudyApplication.class.getResource(fileName + "-view.fxml")).load();
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

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    try {
      final Parent root = new FXMLLoader(getClass().getResource("main-view.fxml")).load();
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
}
