public class MazeSolve {
  
  private static int[] verifyMaze(char[][] maze) {
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

  private static char[][] solveH(int[] pStart, char[][] maze) {
    int x = pStart[0];
    int y = pStart[1];

    maze[x][y] = (maze[x][y] == 'S') ? 'S' : '.';

    
  }

  public static char[][] solve(char[][] maze) {
    return MazeSolve.solveH(MazeSolve.verifyMaze(maze), maze);    
  }
}