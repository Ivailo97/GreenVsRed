import java.util.ArrayList;
import java.util.List;

public class RedToGreenRule extends Rule {

    private int[][] grid;

    private List<Change> changes;

    public RedToGreenRule(int[][] grid) {
        this.grid = grid;
        this.changes = new ArrayList<>();
    }

    @Override
    public List<Change> apply() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == RED) {
                    int neighbors = calculateGreenNeighbors(row, col, grid);
                    if ((neighbors == 3 || neighbors == 6) && doesntHaveNeededGreenNeighbors(neighbors)) {
                        Change change = new Change(row, col, GREEN);
                        changes.add(change);
                    }
                }
            }
        }
        return changes;
    }

    private static boolean doesntHaveNeededGreenNeighbors(int greenNeighbors) {
        return greenNeighbors != 0 && greenNeighbors != 1 && greenNeighbors != 4 && greenNeighbors != 2
                && greenNeighbors != 5 && greenNeighbors != 7 && greenNeighbors != 8;
    }
}
