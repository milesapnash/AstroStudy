package milesapnash.astrostudy;

import java.util.List;

public record Question(String text, String answer, List<String> options, String topic) {

  public static Question blankQuestion() {
    return new Question("", "", List.of("","",""), "");
  }

  public static Question exampleQuestion() {
    return new Question("What is the first moon on Earth?", "Moon", List.of("Galileo","Saturn","Titan"), "Telescopes");
  }
}
