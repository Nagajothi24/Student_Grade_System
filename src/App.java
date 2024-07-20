
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private TextField[] marksFields;
    private Label totalMarksLabel;
    private Label averagePercentageLabel;
    private Label gradeLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Grade Calculator");

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);

        // Number of subjects input
        Label subjectsLabel = new Label("Enter the number of subjects:");
        TextField subjectsField = new TextField();
        Button submitButton = new Button("Submit");
        root.getChildren().addAll(subjectsLabel, subjectsField, submitButton);

        // GridPane for subjects' marks input
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Labels for results
        totalMarksLabel = new Label("Total Marks Obtained: ");
        averagePercentageLabel = new Label("Average Percentage: ");
        gradeLabel = new Label("Grade: ");
        root.getChildren().addAll(gridPane, totalMarksLabel, averagePercentageLabel, gradeLabel);

        submitButton.setOnAction(e -> {
            int numSubjects = Integer.parseInt(subjectsField.getText());
            marksFields = new TextField[numSubjects];
            gridPane.getChildren().clear();

            // Add text fields for each subject's marks
            for (int i = 0; i < numSubjects; i++) {
                Label subjectLabel = new Label("Marks for subject " + (i + 1) + ":");
                TextField marksField = new TextField();
                marksFields[i] = marksField;
                gridPane.add(subjectLabel, 0, i);
                gridPane.add(marksField, 1, i);
            }

            Button calculateButton = new Button("Calculate");
            gridPane.add(calculateButton, 1, numSubjects);

            calculateButton.setOnAction(ev -> calculateGrades(numSubjects));
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateGrades(int numSubjects) {
        int totalMarks = 0;

        for (TextField marksField : marksFields) {
            totalMarks += Integer.parseInt(marksField.getText());
        }

        double averagePercentage = (double) totalMarks / numSubjects;
        char grade = calculateGrade(averagePercentage);

        totalMarksLabel.setText("Total Marks Obtained: " + totalMarks);
        averagePercentageLabel.setText(String.format("Average Percentage: %.2f%%", averagePercentage));
        gradeLabel.setText("Grade: " + grade);
    }

    private char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}

