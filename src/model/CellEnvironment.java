package model;

/**
 * @author ANevmivanyi
 * @version 1.0
 * date - 09/05/2018
 */



/**
 * <p>The {@code CellEnvironment} defines a special container, which contains matrix of the {@code Cell} objects </p>
 * <p>Singleton.Implements the logic of matrix creation , update, and calculating population estimates</p>
 * */
public class CellEnvironment {
    /**<p>The instance of {@code CellEnvironment}</p>*/
    private static CellEnvironment environment;

    /** <p>{@code Cell} objects matrix</p>*/
    private static Cell[][] cellMatrix;

    /**<p>defines the number of living cells</p>*/
    private volatile int populationCounter;

    /** special variable for calculation*/
    private int countVarService = 0;


    /**
     * <p>Creates a new instance of {@code CellEnvironment} object  and initialize {@code Cell}  objects matrix</p>
     * @param width     the width of the Application window
     * @param height    the height of the Application window
     * @param cellSize  the size parameters of the cell
     */
    private CellEnvironment(int width, int height, int cellSize){
        cellMatrix = new Cell[width/cellSize][height/cellSize];
        for(int i = 0, x = 0; i < cellMatrix.length; i ++, x += cellSize){
            for(int j = 0, y = 0; j < cellMatrix[i].length; j ++, y += cellSize){
                cellMatrix[i][j] = new Cell((double)x,(double) y , cellSize, j, i);
            }
        }
    }


    /**
     * <p>This method sets default values for cells parameters
     * </p>
     */
    public void refreshCellMatrix(){
        for (Cell[] cellArr : cellMatrix){
            for(Cell cell : cellArr){
                cell.cleanCell();
            }
        }
    }


    /**<p> typical Singlton - style getter of the {@code CellEnvironment} instance</p>*/
    static CellEnvironment getEnvironment(int width, int height, int cellSize) {
        if( environment != null) return environment;
        else {
            return environment = new CellEnvironment(width, height, cellSize);
        }
    }

    public Cell[][] getMatrix() {
        return cellMatrix;
    }


    /**
     * <p>This method calculates the number of living cells around the current cell and updates value of the {@code Cell} livingNeighborsCounter parameter</p>
     * @param cell          the current cell
     * @param cellMatrix     {@code Cell} objects matrix
     */
    private  void toCountLivingNeighbors(Cell cell, Cell[][] cellMatrix) {

        int X = cell.getIndexX();
        int Y = cell.getIndexY();


        if (cellMatrix[((Y - 1) < 0 ? cellMatrix.length - 1 : Y - 1)]
                [((X - 1) < 0 ? cellMatrix[Y].length - 1 : X - 1)].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        if (cellMatrix[Y]
                [((X - 1) < 0 ? cellMatrix[Y].length - 1 : X - 1)].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        if (cellMatrix[((Y + 1) == cellMatrix.length ? 0 : Y + 1)]
                [((X - 1) < 0 ? cellMatrix[Y].length - 1 : X - 1)].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        if (cellMatrix[((Y - 1) < 0 ? cellMatrix.length - 1 : Y - 1)]
                [X].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        if (cellMatrix[((Y + 1) == cellMatrix.length ? 0 : Y + 1)]
                [X].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        if (cellMatrix[((Y - 1) < 0 ? cellMatrix.length - 1 : Y - 1)]
                [((X + 1) == cellMatrix[Y].length ? 0 : X + 1)].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        if (cellMatrix[Y]
                [((X + 1) == cellMatrix[Y].length ? 0 : X + 1)].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        if (cellMatrix[((Y + 1) == cellMatrix.length ? 0 : Y + 1)]
                [((X + 1) == cellMatrix[Y].length ? 0 : X + 1)].getState() == CellRegime.ALIVE) {
            countVarService++;
        }
        cell.setLivingNeighborsCounter(countVarService);
        countVarService = 0;
    }


    private  void countOperation(){
        for(int i = 0; i < cellMatrix.length; i++){
            for (int j = 0; j < cellMatrix[i].length ; j++) {
                toCountLivingNeighbors(cellMatrix[i][j], cellMatrix);
            }
        }
    }

    /**<p>This method updates cells state and calculate population of living ones</p>*/
    private  void refreshCellsState(){
        populationCounter = 0;
        for (Cell[] aCellMatrix : cellMatrix) {
            for (Cell cell : aCellMatrix) {
                cell.runCellLifeCicle();
                populationCounter += cell.getCellDecimalState();
            }
        }
    }

    /**<p>this method implements the life cycle of a cell generation </p>*/
     void runOneStep(){
        countOperation();
        refreshCellsState();
    }

    int getPopulationCounter() {
        return populationCounter;
    }
}
