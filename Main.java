import java.util.ArrayList;

/*
 *  The Game of Life
 *  
 *  main for the Life.java
 */
 
public class Main {

  public static void main(String[] args) {
    
    //Life game = new Life(); 
    
    /*
     *  _0123
     *  0_x_x
     *  1x_xx
     *  2_x_x
     *  3____
     */
    
    ArrayList<Cell> init = new ArrayList<Cell>();
    
    init.add(new Cell(0, 1)); 
    init.add(new Cell(0, 3)); 
    init.add(new Cell(1, 0)); 
    init.add(new Cell(1, 2)); 
    init.add(new Cell(1, 3)); 
    init.add(new Cell(2, 1)); 
    init.add(new Cell(2, 3)); 
    
    
    Life game = new Life(init); 
    
    System.out.println(game); 
    for (int i = 0; i < 10; ++i) {
      game.simulate(1); 
      System.out.println(game); 
    }
    
  }
  
}
