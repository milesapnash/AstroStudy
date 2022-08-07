package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class LoginController {

  @FXML
  TextField usernameField;
  @FXML
  PasswordField passwordField;
  @FXML
  Label errorLabel;

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
    String username = usernameField.getText();
    String password = passwordField.getText();
    errorLabel.setText("");

    if (username.isEmpty()){
      errorLabel.setText("Username cannot be blank");
      return;
    }
    if (password.isEmpty()){
      errorLabel.setText("Password cannot be blank");
      return;
    }

    // TODO: API Request- Get hashed + salted password for username, compare
    if (true) {
      User u = new User(usernameField.getText() + passwordField.getText(), 0);
      login(event, u);
    } else {
      errorLabel.setText("Either username or password incorrect");
    }
  }

  @FXML
  void toSignUp(ActionEvent event) {
    switchScene(event, "register");
  }

  @FXML
  void forgottenPassword(ActionEvent event) {
    switchScene(event, "reset-password");
  }
}
