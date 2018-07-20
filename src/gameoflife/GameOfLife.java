package gameoflife;
/**
 * @author ANevmivanyi
 * @version 1.0
 * date - 12/05/2018
 */
import javafx.application.Application;
import javafx.stage.Stage;

/** GameOfLife class
 * typical JavaFX class, that provides the main logic of JavaFX Applications
 */
public class GameOfLife extends Application {
    private Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
        controller = new Controller(primaryStage);
        controller.show();

    }

}
