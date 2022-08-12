package milesapnash.astrostudy;

import java.util.ArrayList;
import java.util.List;

public record Question(String text, String answer, List<String> options, String topic) {

  public static Question blankQuestion() {
    return new Question("", "", new ArrayList<>(), "");
  }
}
