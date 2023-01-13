package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import milesapnash.astrostudy.MockAPI;
import milesapnash.astrostudy.User;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class LoginController implements DataController  {
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
  public <A> void parseData(A data) {
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
    loginButton.setStyle("-fx-background-color: gray ");
    loginButton.setDisable(true);
    loginEnabled = false;
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
    int id = MockAPI.login(username,password);
    if (id >= 0) {
      User u = new User(username, id);
      switchScene(event, "menu", u);
    } else {
      errorLabel.setText("Either username or password incorrect");
    }
  }

  @FXML
  void toSignUp(ActionEvent event) {
    switchScene(event, "register", null);
  }

  @FXML
  void forgottenPassword(ActionEvent event) {
    switchScene(event, "reset-password");
  }
}
