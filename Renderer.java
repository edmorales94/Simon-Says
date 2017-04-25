package simon;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//The reason why this class was created was to override the paintComponent method
@SuppressWarnings("serial")
public class Renderer extends JPanel{
	
	/*This is a "repaint" kind of method that will be called when a component needs 
	to be re-painted, ranging from moving, re-sizing, changing focus or being hidden 
	by other frames. This method takes care of the flickering of the repainting process*/
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(Simon.simon != null){
			Simon.simon.paint((Graphics2D) g);
		}
	}
	
}
