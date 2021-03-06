import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;


	public class Game implements Serializable {
	
	private int bombNum = 8;
	final static int boardSize = 10;
	private int[][] board = new int[boardSize][boardSize];
	private int[][] eventCount = new int[boardSize][boardSize];
	private int[][] used = new int[boardSize][boardSize];
	private boolean bomb[][] = new boolean[10][10];
	private boolean flag[][] = new boolean[10][10];
	public boolean lost;
	
	
		
		public Game(int num) {
			
			initializer();
			bombMaker(num);
			bombVicinityCounter();
			lost = false;
			
		}
		
		public void initializer() {
			Random random = new Random();
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					eventCount[i][j] = 0;
					used[i][j] = 0;
					bomb[i][j] = false;
					flag[i][j] = false;
				
				}
			}
		}
		
		public int returnUsed(int i, int j) {
			
			return used[i][j];
		}

		public int getUsed(int i, int j) {
			
			return used[i][j] = 1;
		}

		public int returnCount(int i, int j) {
			
			return eventCount[i][j];
		}

		public int countOne(int i, int j) {
			
			return eventCount[i][j] = 1;
		}

		public int countTwo(int i, int j) {
			
			return eventCount[i][j] = 2;
		}

		public void bombMaker(int num) {
			Random random = new Random();
			bombNum = num;
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if(random.nextInt()%bombNum == 0) {
						bomb[i][j] = true;
						if(bomb[i][j] == true) {
							board[i][j] = -1;
						}
					}
				
				}
			}
		}
		
		
		
		
		public boolean flagMaker(int row, int col) {
			
			if (flagCounter() !=false){
				return flag[row][col] = true;
			} 
			return flag[row][col];
		}
		
		public int getBoard(int i, int j) {
			
			return board[i][j];
		}
		
		public boolean getBomb(int i, int j) {
			
			return bomb[i][j];
		}
		
		public boolean getFlag(int i, int j) {
			
			return flag[i][j];
		}

	
		public boolean noFlag() {
			int count = 0;
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if(flag[i][j] == true) {
						count++;

					}
					
				}
			}

			if(count == 0) {
				return false;

			} else return true;


		}
		
		public int bombCount() {
			int count = 0;
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if(bomb[i][j] == true) {
						count++;

					}
					
				}
			}

			return count;


		}

		public boolean winGame() {
			int count = 0;
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if(bomb[i][j] == true) {
						count++;

					}
					
				}
			}
			
			if(count == 0 && flagCounter() != false) {
				return false;

			} else return true;


		}
		
	
		public boolean flagCounter() {
			int count = 0;
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if(returnCount(i,j) == 2) {

						count = count;
					} else if(getFlag(i,j) == true) {
						count++;

					}
					
				}
			}

			if(count > 30) {
				return false;

			} else return true;


		}
		
		public void flagMax(int i, int j) {
			
			if(flagCounter()== false) {
				JOptionPane.showMessageDialog(null, "Cannot win with more than 30 flags");
		
			}
		


		}

		
		public void win() {

			if(winGame() == false) {
		 
				JOptionPane.showMessageDialog(null, "You won.");
				System.exit(1);
		
			} 

		}
	
		public void lose(int i, int j) {

			if(getBomb(i,j) == true) {
				JOptionPane.showMessageDialog(null, "Loser");
				lost = true;
			} 
			
		}



		public void bombVicinityCounter() {
			
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					int count = 0;
					if (i > 0 && j > 0) {
						if (bomb[i - 1][j - 1] == true)
							count++;
					}
					if (j > 0) {
						if (bomb[i][j - 1] == true)
							count++;
						if (i < 10 - 1) {
							if (bomb[i + 1][j - 1] == true)
							count++;
						}
					}
					if (i > 0) {
						if (bomb[i - 1][j] == true)
							count++;
					}
					if (i < 10 - 1) {
						if (bomb[i + 1][j] == true)
							count++;
					}
					if (i > 0) {
						if (j < 10 - 1) {
							if (bomb[i - 1][j + 1] == true)
							count++;
						}
					}
					if (j < 10 - 1) {
						if (bomb[i][j + 1] == true)
							count++;
					}
					if (i < 10 - 1 && j < 10 - 1) {
						if (bomb[i + 1][j + 1] == true)
							count++;
					}
					if(board[i][j] >= 0) {
						board[i][j] = count;
					}
				}
			}
	


		}
		
		public void startGame(Game game){
			
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					game.getBoard(i,j);
				
				}
			}
			
		}
		
		public void saveGame(File file)	{
			try{
				FileOutputStream saveFile = new FileOutputStream(file);
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				save.writeObject(this);
				save.close();

			}	catch(IOException e){
				e.printStackTrace();
			}
		}
		
		public void loadGame(File file) {
			try{
				FileInputStream loadFile = new FileInputStream(file);
				ObjectInputStream load = new ObjectInputStream(loadFile);
				Game game = (Game)load.readObject();
				startGame(game);
				load.close();
				
				

			}	catch(IOException e){
				e.printStackTrace();
			}	catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}	
