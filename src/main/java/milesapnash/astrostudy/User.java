package milesapnash.astrostudy;

public record User(String username, int id) {

  @Override
  public String toString() {
    return username;
  }
}
