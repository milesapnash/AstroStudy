package milesapnash.astrostudy;

import java.util.Objects;

public record Question(String text, String answer, String[] options, String topic, int id) {

  public static Question blankQuestion() {
    return new Question("", "", new String[]{"", "", ""}, "", -1);
  }

  public static Question exampleQuestion() {
    return new Question("What is the first moon on Earth?", "Moon", new String[]{"Galileo", "Saturn", "Titan"}, "Telescopes", 99);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Question question = (Question) o;
    return id == question.id && topic.equals(question.topic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topic, id);
  }
}
