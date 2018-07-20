package gameoflife;
/**
 * @author ANevmivanyi
 * @version 1.0
 * date - 12/05/2018
 */

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Cell;
import model.GOLModel;
import view.GOLView;


/**
 * Controller class. Fields : model <b>model</b> and <b>view</b>
 * Provides model and view binding
 */
public class Controller {
    /** Model*/
    private GOLModel model;
    /** View*/
    private GOLView view;

    /**
     * Instantination of Controller object.
     * Create default GOLView and GOLModel objects
     * Bind components
     * @param stage
     */
  Controller(Stage stage){
      view = new GOLView();
      model = new GOLModel();
      istallGridPane(view.getGridPane(),model.getEnvironment().getMatrix());
      view.getButtonFlowPane().getChildren().add(model.getButtonPane());
      view.setStage(stage);
      model.run();
  }

    /**
     * Launch Application
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(GameOfLife.class, args);
    }


    /**
     * Instaling GridPane with Cell elements.
     * @param gridPane
     * @param cellEnvironment
     */
    private void istallGridPane(GridPane gridPane, Cell[][] cellEnvironment){
        for (Cell[] cellArr : cellEnvironment){
            for(Cell cell : cellArr){
                gridPane.add(cell, cell.getIndexX(), cell.getIndexY());
            }
        }
    }

    void show(){
        view.show();
    }






}
