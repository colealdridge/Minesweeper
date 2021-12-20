import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;


public class MinesweeperFrame extends JFrame {
	
	

	public MinesweeperFrame() {
		setTitle("Minesweeper");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize(screenSize.width/2,screenSize.height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new MinesweeperPanel(this);
		setJMenuBar(MinesweeperPanel.MenuBar);
		add(panel);
		setVisible(true);
	}


}