import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class MinesweeperPanel extends JPanel implements Serializable {
	
	private Game game = new Game(8);
	public static JMenuBar MenuBar = new JMenuBar();
	private JButton[][] squares = new JButton[10][10];
	public int row = 0;
	public int col = 0;
	public int count = 0;
	public JFileChooser fc = new JFileChooser();

	public MinesweeperPanel(MinesweeperFrame frame) {
		
		
		setLayout(new GridLayout(10,10));
		MouseHandler mouseHandler = new MouseHandler();
		setLayout(new GridLayout(10,10));
		MenuPanel();

		for ( int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
			
			squares[i][j] = new JButton();
			
			squares[i][j].addMouseListener(mouseHandler);
			add(squares[i][j]);
			}
		}
		
		
		
	}
	
	public void MenuPanel() {
		fc = new JFileChooser();
        MenuBar = new JMenuBar();
	
        JMenu FileMenu;
        JMenu FileNew;
        JMenuItem FileOpen;
        JMenuItem FileSave;
        JMenuItem Quit;
        JMenuItem begin;
        JMenuItem medium;
        JMenuItem hard;

     
        FileMenu = new JMenu("File");
        
        FileNew = new JMenu("New");
	
	FileOpen = new JMenuItem("Open");
	FileOpen.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
				
				int num = fc.showSaveDialog(null);
                    if(num == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        game.loadGame(file);
					}
					
					
			 
			}
		
				
				
				
            
        });
       
        FileSave = new JMenuItem("Save");
		FileSave.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
				
				 int num = fc.showSaveDialog(null);
                    if(num == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        game.saveGame(file);
                    }
				
				

			
			}
		
				
				
				
            
        });
   
        Quit = new JMenuItem("Quit");
		Quit.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
               
                                  
								  System.exit(1);
                            
            }
        });

        
        begin = new JMenuItem("Beginner");
		begin.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
				
					
				

			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
				squares[i][j].setText("");
			
				game = new Game(15);
				
				}
			}
		
				
				
				
            }
        });
      
        medium = new JMenuItem("Intermediate");
		medium.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
				
					
				

			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
				squares[i][j].setText("");
			
				game = new Game(8);
				
				}
			}
		
				
				
				
            }
        });
       
        hard = new JMenuItem("Advanced");
		hard.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
				
					
				

			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
				squares[i][j].setText("");
			
				game = new Game(2);
				
				}
			}
		
				
				
				
            }
        });
      
         FileNew.add(begin);
        FileNew.add(medium);
        FileNew.add(hard);

        FileMenu.add(FileNew);
        FileMenu.add(FileOpen);
        FileMenu.add(FileSave);
        FileMenu.add(Quit);
		
        MenuBar.add(FileMenu);


    }
	

	private class MouseHandler extends MouseAdapter { 
		
		public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		
		
			
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
						
						
					if(e.getSource() == squares[i][j]) {
							
							
							
						if(e.getButton() == MouseEvent.BUTTON1) {
							
							squares[i][j].setText(Integer.toString(game.getBoard(i,j)));
								
							game.lose(i,j);
							reveal();	
							
						}
							
						if(e.getButton() == MouseEvent.BUTTON3 && game.returnUsed(i,j) != 1) {
								
								
							if(game.returnCount(i,j) == 0 || game.returnCount(i,j) == 2) {
								
								game.flagMaker(i,j);
									
								if(game.getFlag(i,j)) {
										
									
										squares[i][j].setText("f");
										game.flagMax(i,j);
								}
								
								game.countOne(i,j);
								
							} else if(game.returnCount(i,j) == 1) {
									
								squares[i][j].setText("");
									
								game.countTwo(i,j);
							}	
								
								
								
							game.win();	
						}

							
							
					}
				}
					
			}
		}

	
	}
	
	public void reveal() {
		for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
						if(game.lost) {
							squares[i][j].setText(Integer.toString(game.getBoard(i,j)));
						}
				}
		}
	}

		

}