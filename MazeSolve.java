public class MazeSolve {
  
  private char[][] maze_;
  private ArrayList<String> lines;

  public MazeSolve(String filename) {
    maze_ = this.getMaze(filename);
  }

  /**
   * Precondition: the maze must be a rectangular thingy of text.
   * The maze cannot be empty
   * The file should have no trailing newline.
   */
  private char[][] getMaze(String filename) {
    File file;
    Scanner read;
    try {
      file = new File(filename);
      read = new Scanner(file);
    } catch (Exception e) {
      throw new Error("File not found.");
    }
    
    lines = new ArrayList<String>();
    while (read.hasNextLine()) {
      String line = read.nextLine();
      words.add(line.toUpperCase());
    }

    char[][] maze_ = new char[words.get(0).length][words.size()];
    for (int i = 0; i < words.size(); ++i) {
      for (int j = 0; j < words.get(i).length; ++j) {
        maze[i][j] = words.get(i).charAt(j);
      }
    }
  }

  private void outputMaze() {
    for (int i = 0; i < lines.size(); ++i) {
      System.out.println(lines.get(i));
    }
  }

  private int[] verifyMaze(char[][] maze) {
    int s = 0;
    int e = 0;
    int[] startPoint = new int[2];
    for (int i = 0; i < maze.length; ++i) {
      for (int j = 0; j < maze[i].length; ++j) {
        if (maze[i][j] == 'E') {
          e++;
        }
        if (maze[i][j] == 'S') {
          j++;
          startPoint = new int[] {
            i, j
          };
        }
      }
    }
    if (s == 1 && e == 1) {
      return startPoint;
    }
    throw new Error("Invalid maze");
  }

  private static boolean isMazeSolveable(int[] pStart char[][] maze) {
    int x = pStart[0];
    int y = pStart[1];

    maze[x][y] = maze[x][y] == ' ' ? '.' : maze[x][y];

    if (maze[x][y] == 'E') {
      return true;
    } else if (maze[x][y] == ' ') {
      int[] candidate1 = new int[] {
        x + 1, y
      };
      int[] candidate2 = new int[] {
        x, y + 1
      };
      int[] candidate3 = new int[] {
        x - 1, y
      };
      int[] candidate4 = new int[] {
        x, y - 1
      };
      return isMazeSolveable(candidate1, maze) ||
        isMazeSolveable(candidate2, maze) ||
        isMazeSolveable(candidate3, maze) ||
        isMazeSolveable(candidate4, maze);
    } else {
      return false;
    }
  }

  private boolean solveH(int[] pStart) {
    int x = pStart[0];
    int y = pStart[1];

    if (maze[x][y] == 'E') {
      return true;
    }
    if (maze[x][y] == ' ') {
      int[] candidate1 = new int[] {
        x + 1, y
      };
      int[] candidate2 = new int[] {
        x, y + 1
      };
      int[] candidate3 = new int[] {
        x - 1, y
      };
      int[] candidate4 = new int[] {
        x, y - 1
      };
      if (solve(candidate1) || solve(candidate2) || solve(candidate3) ||
          solve(candidate4)) {
        return true;
      }
      maze[x][y] = '.';
    }
    return false;
  }

  public static char[][] solve(char[][] maze) {
    return MazeSolve.solveH(MazeSolve.verifyMaze(maze), maze);    
  }

  public static void main(String[] args) {
    MazeSolve m = new MazeSolve("maze");
    m.outputMaze();
  }
}