package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.CellEnvironment;


/**
 * @author ANevmivanyi
 * @version 1.0
 * date - 09/05/2018
 */

/**
 * <p>The {@code ButtonHendlerPane} defines special pain,
 * which main purpose is to support push-button and label services.</p>
 */
public  class ButtonHendlerPane extends FlowPane{

    /**<p>{@code CellEnvironment} object, initialized by setter-method</p>*/
    private CellEnvironment cellEnvironment;

    /**<p>Creates and configures a label that shows the current generation </p>*/
    private final Label GENERATION_LBL = new Label(" Generation: 0" ){
        {
            this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
            this.setTextFill(Color.GREEN);
            this.setFont(new Font("Arial",18));
            this.setPrefSize(160, 30);
            this.setPickOnBounds(true);
            this.setCacheShape(true);
        }

    };

    /**<p>Creates and configures a label that shows the number of living cells </p>*/
    private final Label POPULATION_LBL = new Label(" Population: 0" ){
        {
            this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
            this.setTextFill(Color.GREEN);
            this.setFont(new Font("Arial",18));
            this.setPrefSize(160, 30);
            this.setCacheShape(true);
        }
    };

    /**<p>Creates and configures a button that controls the operation of the game processes </p>*/
    private  final Button START_STOP = new Button(" Start "){
        {
            this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
            this.setTextFill(Color.GREEN);
            this.setFont(new Font("Arial",18));
            setOnMouseClicked(event -> {
                if (START_STOP.getText().equals(" Stop ")) {
                    START_STOP.setText(" Start ");
                } else {
                    START_STOP.setText(" Stop ");
                }
            });
        }
    };

    /**<p>Creates and configures a button that refresh cells properties to default values </p>*/
    private  final Button CLEAN = new Button(" Clean "){
        {
            this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
            this.setTextFill(Color.GREEN);
            this.setFont(new Font("Arial",18));
            setOnMouseClicked(event -> {
                if(cellEnvironment != null){
                    START_STOP.setText(" Start ");
                    cellEnvironment.refreshCellMatrix();
                    setGenerationToZero();
                }
            });
        }
    };

    /**
     * <p>Creates the instance of the class</p>
     */
    protected  ButtonHendlerPane(){
        //
        HBox hBox = new HBox(START_STOP,POPULATION_LBL, GENERATION_LBL, CLEAN);
        hBox.setSpacing(30);
        this.getChildren().addAll(hBox);
    }

    public  Button getStartStop() {
        return START_STOP;
    }

    public void setPopulationLabelText(int s){
       POPULATION_LBL.setText(" Population: " + String.valueOf(s));
    }
    public void setGENERATION_LBLText(int s){
        GENERATION_LBL.setText(" Generation: " + String.valueOf(s));
    }

   public void setCellEnvironment(CellEnvironment cellEnvironment) {
        this.cellEnvironment = cellEnvironment;
    }

   // to override!
    public void setGenerationToZero(){
        //TODO
    }
}
