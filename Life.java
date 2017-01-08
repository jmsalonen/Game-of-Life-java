import java.util.ArrayList;

public class Life {
  
  private int gen; // generation counter
  private ArrayList<Cell> cells; // all living cells 
  
  // Game of Life initialisation 
  public Life(ArrayList<Cell> init) {
    if (init != null) {
      gen = 0; 
      cells = new ArrayList<Cell>(); 
      cells.addAll(init);     
    }
  }
  
  // Game of Life initialisation - no argument
  public Life() {
    gen = 0;     
    
    ArrayList<Cell> init = new ArrayList<Cell>();
    init.add(new Cell(0, 1)); 
    init.add(new Cell(0, 3)); 
    init.add(new Cell(1, 0)); 
    init.add(new Cell(1, 2)); 
    init.add(new Cell(1, 3)); 
    init.add(new Cell(2, 1)); 
    init.add(new Cell(2, 3)); 
    
    cells = init; 
  }
  
  // returns the current generation
  public int generation() {
    return gen; 
  }
  
  // Cell neighbour count 
  public int countNeighbors(int i, int j, ArrayList<Cell> c) {
    int nbcount = 0; 
    
    if (c.size() > 0) {
      
      for (int x = 0; x < c.size(); ++x) {
        // TOP
        if (c.get(x).i == i-1 && c.get(x).j == j-1) {
          ++nbcount;
        }
        if (c.get(x).i == i-1 && c.get(x).j == j) {
          ++nbcount;
        }
        if (c.get(x).i == i-1 && c.get(x).j == j+1) {
          ++nbcount;
        }
        
        // MID
        if (c.get(x).i == i && c.get(x).j == j-1) {
          ++nbcount;
        }
        if (c.get(x).i == i && c.get(x).j == j+1) {
          ++nbcount;
        }
        
        // BOTTOM
        if (c.get(x).i == i+1 && c.get(x).j == j-1) {
          ++nbcount;
        }
        if (c.get(x).i == i+1 && c.get(x).j == j) {
          ++nbcount;
        }
        if (c.get(x).i == i+1 && c.get(x).j == j+1) {
          ++nbcount;
        }
      }
      
    }
    
    return nbcount; 
  }
  
  
  // Checks if the cell in the given coordinate is alive
  public boolean isAlive(int i, int j, ArrayList<Cell> c) {
    
    if (c.size() > 0) {
      for (int x = 0; x < c.size(); ++x) {
        if (c.get(x).i == i && c.get(x).j == j) {
          return true; 
        }
      }
    }
    return false; 
    
  }
  
  public void addCell(int i, int j) {
    if (!isAlive(i, j, cells)) {
      cells.add(new Cell(i, j));
    }
  }
  
  public ArrayList<Cell> cells() {
    ArrayList<Cell> status = new ArrayList<Cell>(); 
    status = cells; 
    return status; 
  }
  
  public void simulate(int steps) {
    
    while (steps > 0) {
      
      ArrayList<Cell> newCells = new ArrayList<Cell>(); 
      int begin_i = 0, begin_j = 0, end_i = 0, end_j = 0; 
      
      // find the beginning and the end of the "area" with living cells
      for (int x = 0; x < cells.size(); ++x) {
        if (cells.get(x).i < begin_i) {
          begin_i = cells.get(x).i;
        }
        if (cells.get(x).j < begin_j) {
          begin_j = cells.get(x).j;
        }
        if (cells.get(x).i > end_i) {
          end_i = cells.get(x).i; 
        }
        if (cells.get(x).j > end_j) {
          end_j = cells.get(x).j; 
        }
      }
      
      // Let's increase the "map" size just in case. 
      begin_i -= 2; 
      begin_j -= 2; 
      end_i += 2; 
      end_j += 2; 
      
      for (int i = begin_i; i < end_i; ++i) {
        for (int j = begin_j; j < end_j; ++j) {
          
          boolean alive = isAlive(i, j, cells); 
          int neighbors = countNeighbors(i, j, cells); 
          
          if (alive) {
            if (neighbors == 2 || neighbors == 3) 
              newCells.add(new Cell(i, j));
          }
          else {
            if (neighbors == 3) {
              newCells.add(new Cell(i, j)); 
            }
          }
          
        }
      }
      
      cells = newCells; 
      --steps;
      ++gen; 
      
    }
    
  }
  
  /* A simple visualizer for testing purposes */
  public String toString() {
    String visual = "Generation: " + gen + "\n"; 
    
    for (int i = -10; i <= 10; ++i) {
      for (int j = -20; j <= 20; ++j) {
        if (isAlive(i, j, cells)) {
          visual += '#'; 
        }
        else if (i % 3 == 0 && j % 3 == 0) {
          visual += '.'; 
        }
        else {
          visual += ' '; 
        }
      }
      visual += '\n';
    }
    
    return visual; 
  }

}
