
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rui
 */
public class LifeController {

    private TimerTask task;

    private boolean[][] currentGeneration;
    private final IView view;

    public LifeController(IView view) {
        if(view == null)
            throw new IllegalArgumentException("View cannot be null.");

        this.view = view;
        currentGeneration = new boolean[view.getWidth()][view.getHeight()];
        final Random rnd = new Random();
        for(int row = 0; row < currentGeneration.length; ++row)
            for(int col = 0; col < currentGeneration[row].length; ++col)
                currentGeneration[row][col] = rnd.nextBoolean();
    }

    private int countNeighbors(final boolean[][] generation, final int row, final int col) {
        int numNeighbors = 0;

        //NW
        if ((row - 1 >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row - 1][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        //W
        if ((row >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        //SW
        if ((row + 1 < generation.length) && (col - 1 >= 0)) {
            numNeighbors = generation[row + 1][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        //S
        if ((row + 1 < generation.length) && (col < generation[0].length)) {
            numNeighbors = generation[row + 1][col] ? numNeighbors + 1 : numNeighbors;
        }
        //SE
        if ((row + 1 < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row + 1][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        //E
        if ((row < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        //NE
        if ((row - 1 >= 0) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row - 1][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        //N
        if ((row - 1 >= 0) && (col < generation[0].length)) {
            numNeighbors = generation[row - 1][col] ? numNeighbors + 1 : numNeighbors;
        }

        return numNeighbors;
    }

    private boolean[][] cloneGeneration(final boolean originalGeneration[][]) {
        final boolean[][] newGeneration = new boolean[originalGeneration.length][];
        for (int row = 0; row < originalGeneration.length; ++row) {
            newGeneration[row] = Arrays.copyOf(originalGeneration[row], originalGeneration[row].length);
        }
        return newGeneration;
    }

    private boolean[][] progressGeneration(final boolean[][] generation) {
        final boolean[][] nextGeneration = cloneGeneration(generation);

        for (int row = 0; row < generation.length; ++row) {
            for (int col = 0; col < generation[row].length; ++col) {
                final int numNeighbors = countNeighbors(generation, row, col);
                // cell dies
                if ((numNeighbors < 2) || (numNeighbors > 3)) {
                    nextGeneration[row][col] = false;
                }
                // cell status is maintained
                if (numNeighbors == 2) {
                    nextGeneration[row][col] = generation[row][col];
                }
                // new cell is born or status is maintained
                if (numNeighbors == 3) {
                    nextGeneration[row][col] = true;
                }
            }
        }

        return nextGeneration;
    }

    public void start() {
        task = new TimerTask() {
            @Override
            public void run() {
                currentGeneration = progressGeneration(currentGeneration);
                view.drawGeneration(currentGeneration);
            }
        };
        new Timer().schedule(task, 0, 15);
    }
    
    public void stop() {
        if(task == null)
            return;
        task.cancel();
    }
}
