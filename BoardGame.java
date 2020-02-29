import java.util.*;
public class BoardGame {
	static boolean player1sTurn = true;
    static boolean gameOver = false;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
    	
    	System.out.print("G R I D  G A M E");
    	System.out.println("\n****************");
        String[][] grid = gameGrid(6,7);
        
        int column = 0;
        while (!gameOver) {

            String diskColor = (player1sTurn) ? "red" : "yellow";
            displayGrid(grid);
            boolean firstInput = true;
            do {
                if (!firstInput) {
                    System.out.println("COLUMN IS FULL. Try again...");
                }else {
                	column = isValidColumn(diskColor, grid);
					firstInput = true;
                }  	
            } while (!dropDisk(grid, column));

            if (fourInALine(grid)) {
                displayGrid(grid);
                System.out.print("The "+diskColor+" player won! Do you want to play again? (y/n)");
                char s = input.next().charAt(0);
                if (s == 'y' || s == 'Y') {
                    grid = gameGrid(6, 7);
                    player1sTurn = false;
                } else {
                    System.exit(0);
                }
            }
            player1sTurn = !player1sTurn;
        }

    }
    
    public static int isValidColumn(String dColor, String [][]g) {
    	int col = 0;
    	int tempCol = 0;
		boolean flag = false;
		do {
			System.out.println("Drop a color " + dColor + " disk at column (0-6): ");
			if(input.hasNextInt()) {
				tempCol = input.nextInt();
				boolean inrange = false;
				do {
					if(tempCol < 7) {
						col = tempCol;
						inrange = true;
					}else {
						System.out.println("Enter a number between 0 and 6");
						tempCol = 0;
						input.next();
					}
				}while(!inrange);
				
				
				if(tempCol < 7) {
					col = tempCol;
					flag = true;
				}else {
					System.out.println("Enter a number between 0 and 6");
					tempCol = 0;
					input.next();
				}
			}else {
				System.out.println("Please enter a column number 0-7");
						input.next();
						flag = false;
			}
		}while(!flag);
		return col;
    }

    public static void displayGrid(String[][] m) {

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println("");
        }
    }

    //Show the design
    public static String[][] gameGrid(int row, int column) {

        String[][] m = new String[row][column];
        for (int i = 0; i < m.length; i++) {

            for (int j = 0; j < m[i].length; j++) {
                if (j == 0)
                    m[i][j] = "| |";
                else
                    m[i][j] = " |";

            }
        }
        return m;
    }

    public static boolean fourInALine(String[][] m) {

        String s = (player1sTurn) ? "R" : "Y";
        boolean flag = false;

        int occurrence = 0;
        for (int j = 0; j < m[0].length - 3; j++) {
        	//to reset the cursor from the 1st row
            int y = m.length - 1; 
            int x = j;
            while (x < m[0].length && y >= 0) {

                 if (m[y][x].contains(s)) {              
                     occurrence++;                      
                    if (occurrence == 4) {
                    	flag =  true;   
                    }
                } else {                                 
                     occurrence = 0;   
                     flag = false;
                }                                       
                x++;
                y--;
            }
        }
        
     // column starts from left side
        for (int i = m.length - 2; i > 2; i--) {
            int x = 0; 
            int y = i;
            occurrence = 0;
            while (x < m[0].length && y >= 0) {        
                                                          
                                                          
                if (m[y][x].contains(s)) {              
                    occurrence++;                        
                    if (occurrence == 4) {
                    	flag = true;    
                    }
                } else {
                    occurrence = 0;
                    flag = false;
                }

                x++;
                y--;
            }
        }

        // j >= 3 Only checking 4 Occurences
        for (int j = m[0].length - 1; j >= 3; j--) {
        	// row always starts on last row
            int y = m.length -1; 
            int x = j;
            occurrence = 0;

            while (x >= 0 && y >= 0) {
                                                          
                if (m[y][x].contains(s)) {               
                    occurrence++;                         
                    if (occurrence == 4) {
                    	flag = true;       
                    }
                } else {                                  
                    occurrence = 0; 
                    flag = false;
                }
                x--;
                y--;
            }

        }

        // i > 2 --> reason: only checking occurrences of 4
        for (int i = m.length - 2; i > 2; i--) {
            int x = m[0].length - 1;
            int y = i;
            occurrence = 0;
            while (x >= 0 && y >= 0) {                  
                                                           
                if (m[y][x].contains(s)) {                 
                    occurrence++;                           
                    if (occurrence == 4) {
                    	flag = true;     
                    }
                } else {                                   
                    occurrence = 0;
                    flag = false;
                }
                x--;
                y--;
            }

        }
        return flag;
    }

    //to figure out which disk to drop
    public static boolean dropDisk(String[][] m, int column) {
        String s;
        if (player1sTurn) {
            s = (column > 0) ?  "R|" : "|R|";
        } else {
            s = (column > 0) ? "Y|" : "|Y|";
        }
        boolean didRowUpdate = false;
        int row = 0;
        
        for (int i = 0; i < m.length; i++) {

            if (isClear(m[i][column])) {
                didRowUpdate = true;
                row = i;
            }
        }

        if (!didRowUpdate) return false;

        m[row][column] = s;

        return true;
    }

    public static boolean isClear(String s) {

        return s.contains("| |") || s.contains(" |");
    }


}
