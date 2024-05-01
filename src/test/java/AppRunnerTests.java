import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CalcModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;
import view.MainController;

import java.io.IOException;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class AppRunnerTests {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("main.fxml"));
        BorderPane view = loader.load();
        MainController controller = loader.getController();
        CalcModel model = new CalcModel();
        controller.setModel(model);

        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testMultiplication(FxRobot robot) {
        calculate(robot, 10, 20, "#multiply");
        assertResultEquals(robot, 200);
    }

    @Test
    public void testAddition(FxRobot robot) {
        calculate(robot, 10, 20, "#add");
        assertResultEquals(robot, 30);
    }

    @Test
    public void testSubtraction(FxRobot robot) {
        calculate(robot, 10, 20, "#subtract");
        assertResultEquals(robot, -10);
    }

    @Test
    public void testDivision(FxRobot robot) {
        calculate(robot, 10, 20, "#divide");
        assertResultEquals(robot, 0.5);
    }

    private void calculate(FxRobot robot, int num1, int num2, String btnId) {
        enterValue(robot, "#num1", String.valueOf(num1));
        enterValue(robot, "#num2", String.valueOf(num2));
        robot.clickOn(btnId);
    }

    private void enterValue(FxRobot robot, String fieldId, String text) {
        robot.clickOn(fieldId);
        robot.eraseText(10); // Assuming maximum 10 characters to be safe
        robot.write(text);
    }

    private void assertResultEquals(FxRobot robot, double expected) {
        Label resultLabel = robot.lookup("#result").queryAs(Label.class);
        Assertions.assertThat(Double.parseDouble(resultLabel.getText())).isCloseTo(expected, Assertions.within(0.01));
    }
}
