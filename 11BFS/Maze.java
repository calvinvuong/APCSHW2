import java.util.*;
import java.io.*;

public class Maze {
  
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

  private static final String CLEAR = "\033[2J";
  private static final String HIDE = "\033[?25l";
  private static final String SHOW = "\033[?25h";
  private static final String RESET = "\033[0;0H";
  private static final int BREADTH_FIRST_MODE = 0;
  private static final int DEPTH_FIRST_MODE = 1;

  private String filename_;
  private char[][] maze_;
  private int[] startPoint_;
  private LinkedList<Integer> solution_;

  public Maze(String filename) {
    filename_ = filename;
    getMaze(filename);
    verifyMaze();
    solution_ = new LinkedList<Integer>();
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

  public String toString() {
    String out = "";
    for (char[] i : maze_) {
      for (char j : i) {
        out += j;
      }
      out += "\n";
    }
    return out;
  }

  public String toString(boolean animate) {
    if (animate) {
      try {
        Thread.sleep(100);
      }
      catch (Exception e) {}
      return CLEAR + RESET + toString();
    }
    return toString();
  }
  
  private void outputMaze(boolean animate) {
    System.out.println(toString(animate));
  }

  private void verifyMaze() {
    int s = 0;
    int e = 0;
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        if (maze_[i][j] == 'S') {
          s++;
          startPoint_ = new int[] { i, j };
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

  private boolean solve(boolean animate, int mode) {
    MyDeque<MoveNode> moves = new MyDeque<MoveNode>();
    moves.add(new MoveNode(startPoint_[0], startPoint_[1], null));

    while (moves.size() != 0) {
      int numCurrentMoves = moves.size();
      for (int d = 0; d < numCurrentMoves; ++d) {
        MoveNode first;
        if (mode == BREADTH_FIRST_MODE) {
          first = moves.removeFirst();
        } else if (mode == DEPTH_FIRST_MODE) {
          first = moves.removeLast();
        } else {
          throw new Error("Invalid mode");
        }
        int[][] candidates = new int[][] {
          new int[] { first.x_ + 1, first.y_ },
          new int[] { first.x_ - 1, first.y_ },
          new int[] { first.x_, first.y_ + 1 },
          new int[] { first.x_, first.y_ - 1}
        };
        for (int[] candidate : candidates) {
          if (maze_[candidate[0]][candidate[1]] == 'E') {
            while (first.prev_ != null) {
              maze_[first.x_][first.y_] = '@';
              solution_.addFirst(first.x_);
              solution_.add(1, first.y_);
              first = first.prev_;
            }
            clearCrapFromMaze();
            outputMaze(animate);
            return true;
          }
          if (verifySquare(candidate)) {
            maze_[candidate[0]][candidate[1]] = '.';
            moves.add(new MoveNode(candidate, first));
          }
        }
        outputMaze(animate);
      }
    }
    return false;
  }
    

  public boolean solveBFS(boolean animate) {
    return solve(animate, BREADTH_FIRST_MODE);
  }

  public boolean solveBFS() {
    return solveBFS(false);
  }

  public boolean solveDFS(boolean animate) {
    return solve(animate, DEPTH_FIRST_MODE);
  }

  public boolean solveDFS() {
    return solveDFS(false);
  }

  public int[] solutionCoordinates() {
    int[] out = new int[solution_.size()];
    int c = 0;
    for (int i : solution_) {
      out[c] = i;
      c++;
    }
    return out;
  }

  public static void main(String[] args) {
    Maze m = new Maze(args[0]);
    m.solveDFS(true);
    System.out.println(Arrays.toString(m.solutionCoordinates()));
  }
}
