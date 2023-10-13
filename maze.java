import java.util.Random;

public class MazeGenerator {
    private int width;
    private int height;
    private char[][] maze;
    private Random random = new Random();

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new char[2 * height + 1][2 * width + 1];
    }

    public void generateMaze() {
        for (int i = 0; i < 2 * height + 1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                maze[i][j] = (i % 2 == 0 || j % 2 == 0) ? ' ' : '#';
            }
        }

        generateMazeRecursive(1, 1);

        maze[0][1] = 'S';
        maze[2 * height][2 * width - 1] = 'E';
    }

    private void generateMazeRecursive(int row, int col) {
        int[] directions = {0, 1, 2, 3};
        for (int i = 0; i < 4; i++) {
            int randIndex = random.nextInt(4);
            int tmp = directions[randIndex];
            directions[randIndex] = directions[i];
            directions[i] = tmp;

            int newRow = row + 2 * (tmp / 2) - 1;
            int newCol = col + 2 * (tmp % 2) - 1;

            if (newRow >= 1 && newRow < 2 * height && newCol >= 1 && newCol < 2 * width && maze[newRow][newCol] == '#') {
                maze[newRow][newCol] = ' ';
                maze[row + (newRow - row) / 2][col + (newCol - col) / 2] = ' ';
                generateMazeRecursive(newRow, newCol);
            }
        }
    }

    public void printMaze() {
        for (int i = 0; i < 2 * height + 1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(20, 10);
        mazeGenerator.generateMaze();
        mazeGenerator.printMaze();
    }
}
