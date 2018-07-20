package model;

import view.ButtonHendlerPane;
import javafx.application.Platform;
/**
 * @author ANevmivanyi
 * @version 1.0
 * date - 09/05/2018
 */


/**
 * <p>The {@code GOLModel} defines the special object, which implements the logic of MVC pattern.
 * The {@code GOLModel} class also contains META-parameters like a WIDTH, HEIGHT, CELL_SIZE.
 * </p>
 */
public class GOLModel {
    /**<p>The width of the application window, in pixels</p>*/
    private static final int WIDTH = 600;
    /**<p>The height of the application window, in pixels</p>*/
    private static final int HEIGHT = 600;

    /**<p>The size (height and weight) of the {@code Cell} object</p>*/
    private static final int CELL_SIZE = 5;

    /**<p>{@code CellEnvironment} object, initialized by {@code GOLModel} constructor</p>*/
    private CellEnvironment environment;

    /**<p>{@code ButtonHendlerPane} object, initialized by {@code GOLModel} constructor</p>*/
    private ButtonHendlerPane buttonPane;

    /**<p>This property defines the generation counter</p>*/
    private int generationCounter;

    /**
     * <p> Creates a new instance of {@code GOLModel} ,
     * initializes {@code CellEnvironment} and  {@code ButtonHendlerPane} objects,
     * binds them</p>
     */
    public GOLModel() {
        environment = CellEnvironment.getEnvironment(WIDTH,HEIGHT, CELL_SIZE);
        buttonPane = new ButtonHendlerPane(){
            @Override
            public void setGenerationToZero(){
                generationCounter = 0;
                setPopulationLabelText(0);
                setGENERATION_LBLText(0);
            }
        };
        buttonPane.setCellEnvironment(environment);
    }

    /**
     * <p>This method executes  main game mechanism.</p>
     * <p>Creates the object of the anonymous class, which extends {@code Thread.class}  and overrides the run() method.</p>
     */
    public void run(){
        new Thread("ThreadOfLife"){
            {
                setDaemon(true);
            }
            @Override
            public void run(){
                while(true) {

                    try {
                        while (buttonPane.getStartStop().getText().equals(" Stop ")) {
                            //weak place
                            final int counter = generationCounter++;
                            environment.runOneStep();
                            Platform.runLater(() -> {
                                buttonPane.setPopulationLabelText(environment.getPopulationCounter());
                                buttonPane.setGENERATION_LBLText(counter);
                            });
                            Thread.sleep(200);
                        }
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        System.out.println("ThreadOfLife is interrupted");
                    }
                }
            }
        }.start();
    }

    public CellEnvironment getEnvironment() {
        return environment;
    }


    public ButtonHendlerPane getButtonPane() {
        return buttonPane;
    }

}
