package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import milesapnash.astrostudy.MockAPI;
import milesapnash.astrostudy.User;

import static milesapnash.astrostudy.AstroStudyApplication.setupScene;
import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class LoginController implements SetupController  {
  private boolean loginEnabled = false;

  @FXML
  TextField usernameField;
  @FXML
  PasswordField passwordField;
  @FXML
  Button loginButton;
  @FXML
  Label errorLabel;

  @Override
  public void setup() {
    disableButton();

    usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (usernameField.getText().isEmpty() && loginEnabled){
        disableButton();
      } else if (!(passwordField.getText().isEmpty() || loginEnabled)){
        enableButton();
      }
      });

    passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (passwordField.getText().isEmpty() && loginEnabled){
        disableButton();
      } else if (!(usernameField.getText().isEmpty() || loginEnabled)){
        enableButton();
      }
    });
  }

  private void enableButton(){
    loginButton.setStyle("-fx-background-color: white ");
    loginButton.setDisable(false);
    loginEnabled = true;
  }

  private void disableButton(){
    loginButton.setStyle("-fx-background-color: grey ");
    loginButton.setDisable(true);
    loginEnabled = false;
  }

  @FXML
  void attemptLogin(ActionEvent event) {
    if (!loginEnabled){
      return;
    }

    String username = usernameField.getText();
    errorLabel.setText("");

    int id = MockAPI.login(username, passwordField.getText());
    if (id >= 0) {
      User u = new User(username, id);
      switchScene(event, "menu", u);
    } else {
      errorLabel.setText("Either username or password incorrect");
    }
  }

  @FXML
  void toSignUp(ActionEvent event) {
    setupScene(event, "register");
  }

  @FXML
  void forgottenPassword(ActionEvent event) {
    switchScene(event, "reset-password");
  }
}
