package milesapnash.astrostudy;

import java.util.Objects;

public record Question(String text, String answer, String topic) {

  public static Question blankQuestion() {
    return new Question("", "", "");
  }

  public static Question exampleQuestion() {
    return new Question("Who is the best football player of all time?", "Craig Dawson", "Truth");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Question question = (Question) o;
    return Objects.equals(text, question.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }
}
