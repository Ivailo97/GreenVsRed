import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private static final String INPUT_DELIMITER = ", ";
    private int[][] grid;
    private int targetRow;
    private int targetCol;
    private int generations;
    private Rule[] rules;

    public Game() throws IOException {
        init();
    }

    public void start() {

        int greenGenerations = 0;

        for (int i = 0; i <= generations; i++) {

            if (grid[targetRow][targetCol] == 1) {
                greenGenerations++;
            }

            List<Change> changes = Arrays.stream(rules).map(Rule::apply)
                    .flatMap(List::stream).collect(Collectors.toList());

            Arrays.stream(rules).forEach(rule -> changes.addAll(rule.apply()));

            initRules();

            applyChanges(changes);
        }

        System.out.println(greenGenerations);
    }

    private void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split(INPUT_DELIMITER)).mapToInt(Integer::parseInt).toArray();
        int x = dimensions[0];
        int y = dimensions[1];

        grid = new int[x][y];
        for (int i = 0; i < y; i++) {
            grid[i] = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[] tokens = Arrays.stream(reader.readLine().split(INPUT_DELIMITER)).mapToInt(Integer::parseInt).toArray();
        this.targetRow = tokens[1];
        this.targetCol = tokens[0];
        this.generations = tokens[2];

        initRules();
    }

    private void initRules() {
        Rule firstRule = new GreenToRedRule(grid);
        Rule secondRule = new RedToGreenRule(grid);
        rules = new Rule[]{firstRule, secondRule};
    }

    private void applyChanges(List<Change> changes) {
        for (Change change : changes) {
            grid[change.getRow()][change.getCol()] = change.getValue();
        }
    }
}
