package simon;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * 
 * @author Edgar Morales
 * 
 *
 */

public class Simon implements ActionListener, MouseListener{
	
	public static Simon simon;
	public Renderer renderer;
	public static final int width = 900, height = 900;
	public int color = 0,ticks, indexPattern,dark,points;
	public boolean colorsPattern = true;
	public ArrayList<Integer> pattern;
	public Random random;
	private boolean gameIsOver;
	
	public Simon(){
		JFrame frame = new JFrame("Simon Says: Juega");//creating a frame and naming the window
		Timer timer = new Timer(20,this);//To perform a task repeatedly to perform animation or update a component that displays progress toward a goal every 0.2 seconds.
		
		renderer = new Renderer();
		
		frame.setSize(width + 15, height + 35);//with a height of 835 and a width of 815
		frame.setVisible(true);// setting the frame to be visible
		frame.add(renderer);
		frame.addMouseListener(this);
		frame.setResizable(false);//this doesn't allow the user to resize the frame/window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when "X" is clicked, the window closes
		
		timer.start();//the timer is starting
		start();
	}
	
	public void start(){
		random = new Random();
		pattern = new ArrayList<Integer>();
		indexPattern = 0;
		color = 0;
		ticks = 0;
		points = 0;
	}
	
	public void paint(Graphics2D g) {	
		//make the edges to look smooth and not like some stairs
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
//--------- handleing the green button -------------------------------------------------------------------------
		if( color == 1)	{ g.setColor(Color.green); }
		else{			      g.setColor(new Color(0,100,0));}
		g.fillRect(0, 0,(width+15)/2,(height+35)/2);// fill green button}
//--------- handleing the red button ---------------------------------------------------------------------------
		if(color == 2){  	  g.setColor(Color.red.brighter());}
		else{				  g.setColor(new Color(100,0,0));}
		g.fillRect((width+15)/2, 0, (width+15)/2, (height+35)/2);// fill red button
//--------- handleing the blue button --------------------------------------------------------------------------
		if(color == 3){	  g.setColor(Color.blue.brighter());}
		else{				  g.setColor(new Color(0,0,100));}
		g.fillRect(0, (height+35)/2, (width+15)/2, (height+35)/2);// fill blue button
//--------- handleing the yellow button ------------------------------------------------------------------------
		if( color == 4){	  g.setColor(Color.yellow.brighter());}
		else{ 				  g.setColor(new Color(100,100,0));}
		g.fillRect((width+15)/2, (height+35)/2,(width+15)/2, (height+35)/2);//yellow button
//--------- black dividers -------------------------------------------------------------------------------------
		g.setColor(Color.BLACK);
		g.fillOval(260,280, 380, 380);//circle in the middle
		g.fillRect((width+15)/2 -28,0,50, height);//vertical line
		g.fillRect(0,(height+35)/2 -25, width +15, 50);//horizontal line
		g.setStroke(new BasicStroke(200));//increasing the thickness of the outline of the oval
		g.drawOval(-96, -98, width+200, height+190);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1,143));
		
		if(gameIsOver){
		g.drawString(":(", width/2 - 60, height/2 + 50);
		}
		else{
			g.setFont(new Font("Arial", 1,50));
			g.drawString("Points", width/2 - 80, height/2 - 60);
			g.setFont(new Font("Arial", 1,143));
			g.drawString(points+"", width/2 - 40, height/2 + 70);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX(); //mouse x-coordinate
		int y = e.getY();//mouse y-coordinate
		
		if(!colorsPattern && !gameIsOver){//if there's no pattern
			
			if(x > 0 && x < (width +15)/2 && y > 0 && y < (height+35)/2){
				color = 1;//green
				ticks = 1;
				points++;
			}
			
			else if(x > (width +15)/2 && x < width + 15 && y > 0 && y < (height+35)/2){
				color = 2;//red
				ticks = 1;
				points++;
			}
			
			else if(x > 0 && x < (width + 15)/2 && y > (height+35)/2 && y < (height+35)){
				color = 3;//blue
				ticks = 1;
				points++;
				}
			
			else if(x > (width +15)/2 && x < width + 15 && y > (height+35)/2 && y < (height+35)){
				color = 4;//blue
				ticks = 1;
				points++;
				}
			
			if(color != 0){
				if(pattern.get(indexPattern) == color){
					indexPattern++;
				}
				else{
					gameIsOver = true;
				}
				
				}
			}
		else if(gameIsOver){
			start();
			gameIsOver = false;
		}
		}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ticks++;
		if(ticks % 20 == 0){//now the light up button lasts half of a second
			color = 0;	
			if(dark >= 0){
				dark--;
			}
		}
		if(colorsPattern){//since there is no pattern in the beginning, we create it
			if(dark <= 0){//dark is only used to put a break b/w lights and see one separately at the time
				if(indexPattern >= pattern.size()){
					color  = random.nextInt(4)+1;//getting numbers from (0 to 3) + 1
					pattern.add(color);//adding the numbers of each color to the array
					indexPattern = 0;
					colorsPattern = false;
				}
				else{							
					color = pattern.get(indexPattern);
					indexPattern++;
				}
				dark = 2;//this is meant for the break b/w colors,it's 2 so that the break doesn't last long
			}
		}
		
		else if(indexPattern == pattern.size()){
				colorsPattern = true;
				indexPattern = 0;
				dark = 2;
				}
		
		renderer.repaint();
	}
	
	public static void main( String[] args){
		simon = new Simon();
	}
}
