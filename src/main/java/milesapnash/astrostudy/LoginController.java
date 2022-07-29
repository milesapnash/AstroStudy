package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class LoginController {

  @FXML
  TextField username;

  @FXML
  TextField password;

  public static void login(ActionEvent event, User user){
    final Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    try {
      final FXMLLoader loader = new FXMLLoader(AstroStudyApplication.class.getResource("menu-view.fxml"));
      final Parent root = loader.load();
      final Scene scene = new Scene(root);
      final URL url = AstroStudyApplication.class.getResource("application.css");

      if (url == null) {
        System.out.println("CSS Resource not found.");
        System.exit(-1);
      }

      MenuController menuController = loader.getController();
      menuController.setUser(user);

      scene.getStylesheets().add(url.toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void attemptLogin(ActionEvent event) {
    if (!Objects.equals(username.getText(), "")){
      int userID = Integer.parseInt(username.getText());
      User u = new User("User " + userID, userID);
      login(event, u);
    }
  }

  @FXML
  void toSignUp(ActionEvent event) {
    switchScene(event, "register");
  }
}
