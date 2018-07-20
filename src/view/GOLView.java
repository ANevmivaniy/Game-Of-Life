package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Cell;


/**
 * @author ANevmivanyi
 * @version 1.0
 * date - 12/05/2018
 */

/**
 * <p>The {@code GOLView} defines the special view-object </p>
 */
public class GOLView {
    private Stage stage;
    private Scene scene;
    private GridPane gridPane;
    // Layout for buttons
    private FlowPane buttonFlowPane;

    /**
     * <p>Creates the instance of the {@code GOLView} class, configures the application view</p>
     */
    public GOLView(){
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);

        buttonFlowPane = new FlowPane();

        BorderPane root = new BorderPane();
        root.setTop(buttonFlowPane);
        root.setCenter(gridPane);

        //create a scene
        scene = new Scene(root);
    }

    /**
     * <p>Sets stage  parameters and scene</p>
     * @param stage  Stage
     */
    public void setStage(Stage stage){

        stage.setTitle("Game Of Life");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        this.stage = stage;
    }
    public void show(){
        stage.show();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public FlowPane getButtonFlowPane(){
        return buttonFlowPane;
    }
}
