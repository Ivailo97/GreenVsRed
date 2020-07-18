import java.util.List;

public abstract class Rule {

    protected static final int GREEN = 1;
    protected static final int RED = 0;

    protected int calculateGreenNeighbors(int row, int col, int[][] grid) {

        int greenNeighbors = 0;

        if (inBounds(row - 1, col - 1, grid) && grid[row - 1][col - 1] == 1) {
            greenNeighbors++;
        }

        if (inBounds(row - 1, col, grid) && grid[row - 1][col] == 1) {
            greenNeighbors++;
        }

        if (inBounds(row - 1, col + 1, grid) && grid[row - 1][col + 1] == 1) {
            greenNeighbors++;
        }

        if (inBounds(row, col + 1, grid) && grid[row][col + 1] == 1) {
            greenNeighbors++;
        }

        if (inBounds(row + 1, col + 1, grid) && grid[row + 1][col + 1] == 1) {
            greenNeighbors++;
        }

        if (inBounds(row + 1, col, grid) && grid[row + 1][col] == 1) {
            greenNeighbors++;
        }

        if (inBounds(row + 1, col - 1, grid) && grid[row + 1][col - 1] == 1) {
            greenNeighbors++;
        }

        if (inBounds(row, col - 1, grid) && grid[row][col - 1] == 1) {
            greenNeighbors++;
        }

        return greenNeighbors;
    }

    public abstract List<Change> apply();

    private boolean inBounds(int row, int col, int[][] grid) {
        return row < grid.length && row >= 0 && col >= 0 && col < grid[row].length;
    }
}
