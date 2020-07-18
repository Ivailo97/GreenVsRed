import java.util.ArrayList;
import java.util.List;

public class GreenToRedRule extends Rule {

    private int[][] grid;

    private List<Change> changes;

    public GreenToRedRule(int[][] grid) {
        this.grid = grid;
        this.changes = new ArrayList<>();
    }

    @Override
    public List<Change> apply() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                if (grid[row][col] == GREEN) {

                    int neighbors = calculateGreenNeighbors(row, col, grid);

                    if (hasConcreteGreenNeighbors(neighbors)) {
                        Change change = new Change(row, col, RED);
                        changes.add(change);
                    }

                    if (neighbors == 2 || neighbors == 3 || neighbors == 6) {
                        Change change = new Change(row, col, GREEN);
                        changes.add(change);
                    }
                }
            }
        }
        return changes;
    }

    private boolean hasConcreteGreenNeighbors(int greenNeighbors) {
        return greenNeighbors == 0 || greenNeighbors == 1 || greenNeighbors == 4
                || greenNeighbors == 5 || greenNeighbors == 7 || greenNeighbors == 8;
    }
}
