package model;

/**
 * @author ANevmivanyi
 * @version 1.0
 * date - 08/05/2018
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The {@code Cell} defines a special rectangle object
 * According to the rules of the game, Cell.class is  a class
 * that implements the logic of a living or dead cell.
 * Instance keeps its state, position, the number of surrounding living cells.
 */
public class Cell extends Rectangle {
    private static final Color DEATH_COLOR = Color.BLACK;
    private static final Color LIFE_COLOR = Color.GREEN;

    /** a cell can have only one current state ({@code CellRegime.ALIVE} or {@code CellRegime.LIFELESS})
     * */
    private CellRegime state;

    /** x-axis position on the GridPane*/
    private int indexX;

    /** y-axis position on the GridPane*/
    private int indexY;

    /** shows the number of living(state = CellRegime.ALIVE) cells around instance */
    private int livingNeighborsCounter;

    /**
     * <p> Creates a new instance of Сell with the given position and size </p>
     * @param x      the x-axis coordinate of the cell in the application window
     * @param y      the x-axis coordinate of the cell in the application window
     * @param size   the height and width of the cell
     * @param indexX x-axis position on the GridPane
     * @param indexY y-axis position on the GridPane
     */
    Cell(double x, double y, int size, int indexX, int indexY) {
        super(x,y,size,size);
        this.indexX = indexX;
        this.indexY = indexY;
        this.state = CellRegime.LIFELESS;
        this.setFill(DEATH_COLOR);
        this.initEventHendler(true);
    }

     CellRegime getState(){
        return state;
     }


    /**
     *<p>Adds mouse events support</p>
     * @param flag turn-on/turn-off  mouse event hendler
     */
    private void initEventHendler(boolean flag){
         if(flag){
             this.setOnMousePressed(event -> {
                 inverseCellState();
                 Cell cell = (Cell)event.getSource();
             });
             this.setOnDragDetected(event ->
             {
                 Cell cell = (Cell) event.getSource();
                 cell.startFullDrag();
             });
             this.setOnMouseDragEntered(event -> inverseCellState());
         }
     }
     /**
      * <p>Change the state of the cell to the opposite</p>
      * */
     private void inverseCellState(){
         if(state.equals(CellRegime.LIFELESS)){
             toLive();
         }else
         if(state.equals(CellRegime.ALIVE)){
             toDie();
         }
     }


     private void toDie(){
         state = CellRegime.LIFELESS;
         this.setFill(DEATH_COLOR);
     }


     private void toLive(){
         state = CellRegime.ALIVE;
         this.setFill(LIFE_COLOR);
     }

     /** <p>set default values</p>*/
    void cleanCell(){
         toDie();
         livingNeighborsCounter =0;
     }

    void setLivingNeighborsCounter(int livingNeighborsCounter) {
        this.livingNeighborsCounter = livingNeighborsCounter;
    }


    /**
     * <p>this method implements the basic rule of the game:
     if the number of surrounding living cells is less than 2 , the cell dies
     if the number of surrounding living cells is greater than 3, the cell dies
     if the number of surrounding living cells is 3, the cell comes to life </p>
     */
    void runCellLifeCicle(){
         if(livingNeighborsCounter < 2 || livingNeighborsCounter > 3){
            toDie();
         }
         else if(livingNeighborsCounter == 3){
             toLive();
         }
    }

    /**
     * <p>This method converts Cell state to decimal value</p>
     * @return 1 if the cell is alive, 0 if the cell is dead.
     */
    int getCellDecimalState(){
        return state.equals(CellRegime.ALIVE) ? 1 : 0;
    }

    public int getIndexX() {
        return indexX;
    }

    public int getIndexY() {
        return indexY;
    }

}
