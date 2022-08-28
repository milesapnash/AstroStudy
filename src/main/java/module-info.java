module milesapnash.astrostudy {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;

  opens milesapnash.astrostudy to javafx.fxml;
  exports milesapnash.astrostudy;
  exports milesapnash.astrostudy.controllers;
  opens milesapnash.astrostudy.controllers to javafx.fxml;
}