# AstroStudy

An interactive general-knowledge quiz application built with JavaFX. Originally my A Level Computing
coursework (VB.NET / Windows Forms, 2019-2020, graded 72/75), rebuilt from scratch in Java to
explore JavaFX and improve on the original design.

The original [final report](doc/report.pdf) can be found in the `/doc` folder.

## Features

- **Topic-based quizzes** — choose from Capitals, Geography, History, or Science (223 questions total)
- **Random mode** — pull questions from all topics at once
- **Fuzzy answer matching** — answers are accepted within a Levenshtein distance of 1, forgiving minor typos
- **User accounts** — register, log in, and reset password
- **Question management** — browse and edit the question bank from within the app

## Tech Stack

- **Java 17** with JPMS modules
- **JavaFX 17** (FXML + CSS)
- **Maven** (with wrapper — no global install required)
- **JUnit 5** for tests

## Prerequisites

- **JDK 17+** (JavaFX is bundled via Maven dependencies, no separate install needed)

## Getting Started

```bash
# Clone the repository
git clone https://github.com/milesapnash/AstroStudy.git
cd AstroStudy

# Run the application
./mvnw javafx:run

# Run tests
./mvnw test
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Project Structure

```
src/main/java/milesapnash/astrostudy/
  AstroStudyApplication.java   # Entry point and scene navigation
  MockAPI.java                 # In-memory data layer (auth, questions)
  Question.java                # Question record
  User.java                    # User record
  TopicData.java               # DTO for topic selection
  TestData.java                # DTO for quiz sessions
  controllers/                 # JavaFX FXML controllers

src/main/resources/milesapnash/astrostudy/
  *.fxml                       # Scene layouts
  application.css              # Global stylesheet
  questions/                   # CSV question banks
    capitals.csv   (103 questions)
    geography.csv  (40 questions)
    history.csv    (40 questions)
    science.csv    (40 questions)

src/test/java/                 # JUnit 5 test suite
```

## Adding Questions

Questions are stored as CSV files in `src/main/resources/milesapnash/astrostudy/questions/`. Each
line is a comma-separated question/answer pair:

```
France,Paris
Germany,Berlin
```

To add a new topic, create a new `.csv` file in that directory — the filename (without extension)
becomes the topic name.
