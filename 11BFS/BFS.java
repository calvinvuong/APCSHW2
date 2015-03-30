import java.util.*;
import java.io.*;

public class BFS {
  
  private class MoveNode {
    public int x_;
    public int y_;
    public MoveNode prev_;

    public MoveNode(int[] xy, MoveNode prev) {
      this(xy[0], xy[1], prev);
    }

    public MoveNode(int x, int y, MoveNode prev) {
      x_ = x;
      y_ = y;
      prev_ = prev;
    }
  }

  private char[][] maze_;
  private int[] startPoint_;

  public BFS(String filename) {
    getMaze(filename);
    startPoint_ = new int[2];
    verifyMaze();
  }

  /**
   * Precondition: the maze must be a rectangular thingy of text.
   * The maze cannot be empty
   * The file should have no trailing newline.
   * The maze must have an S and an E and cannot have @ or '.'
   */
  private void getMaze(String filename) {
    File file;
    Scanner read;
    try {
      file = new File(filename);
      read = new Scanner(file);
    } catch (Exception e) {
      throw new Error("File not found.");
    }
    
    ArrayList<String> lines = new ArrayList<String>();
    while (read.hasNextLine()) {
      String line = read.nextLine();
      lines.add(line.toUpperCase());
    }
    try {
      maze_ = new char[lines.size()][lines.get(0).length()];
      for (int i = 0; i < lines.size(); ++i) {
        for (int j = 0; j < lines.get(i).length(); ++j) {
          maze_[i][j] = lines.get(i).charAt(j);
        }
      }
    } catch (Exception e) {
      throw new Error("Invalid maze");
    }
  }

  private void clearCrapFromMaze() {
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        if (maze_[i][j] == '.') {
          maze_[i][j] = ' ';
        }
      }
    }
  }

  private void outputMaze(boolean animate) {
    if (animate) {
      try {
        Thread.sleep(10);
        for (int i = 0; i < 50; ++i) {
          System.out.print("\n");
        }
      } catch (Exception e) {}
    }
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        System.out.print(maze_[i][j]);
      }
      System.out.print("\n");
    }
  }

  private void verifyMaze() {
    int s = 0;
    int e = 0;
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        if (maze_[i][j] == 'S') {
          s++;
          startPoint_[0] = i;
          startPoint_[1] = j;
        }
        if (maze_[i][j] == 'E') {
          e++;
        }
      }
    }
    if (s != 1 || e != 1) {
      throw new Error("Invalid maze");
    }
  }

  private boolean verifySquare(int[] xy) {
    return verifySquare(xy[0], xy[1]);
  }

  private boolean verifySquare(int x, int y) {
    try {
      return maze_[x][y] != 'X' && maze_[x][y] != '.' && maze_[x][y] != 'S';
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }
  }

  public void breadthSolve() {
    MyDeque<MoveNode> moves = new MyDeque<MoveNode>();
    moves.add(new MoveNode(startPoint_[0], startPoint_[1], null));

    while (true) {
      int numCurrentMoves = moves.size();
      for (int d = 0; d < numCurrentMoves; ++d) {
        MoveNode first = moves.removeFirst();
        int[][] candidates = new int[][] {
          new int[] { first.x_ + 1, first.y_ },
          new int[] { first.x_ - 1, first.y_ },
          new int[] { first.x_, first.y_ + 1 },
          new int[] { first.x_, first.y_ - 1}
        };
        for (int i = 0; i < candidates.length; ++i) {
          if (maze_[candidates[i][0]][candidates[i][1]] == 'E') {
            while (first.prev_ != null) {
              maze_[first.x_][first.y_] = '@';
              first = first.prev_;
            }
            clearCrapFromMaze();
            outputMaze(true);
            return;
          }
          if (verifySquare(candidates[i])) {
            maze_[candidates[i][0]][candidates[i][1]] = '.';
            moves.add(new MoveNode(candidates[i], first));
          }
          outputMaze(true);
        }
      }
    }
  }

  public static void main(String[] args) {
    BFS m = new BFS(args[0]);
    m.breadthSolve();
  }
}
