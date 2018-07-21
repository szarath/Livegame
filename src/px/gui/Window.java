package px.gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import px.Enemy;
import px.Hero;
import px.adapter.Collision;
import px.adapter.Keyadapt;
import px.file.Writer;
import server.server;
import sun.applet.Main;

public class Window extends JPanel implements ActionListener, Serializable {

	
	private static final long serialVersionUID = -6282062229136487535L;
	private static Hero h;
	private static int score = 0;// the initial score 
	private static Timer time;
	private static int stime = (int) System.currentTimeMillis();// the current time 
	private static painter p;
	private static Collision c;
	private ArrayList<Enemy> enemies; //the array list of enemies 
	
	private  static boolean check;

	private static boolean hit = false;
	private static int hld;
	private static final int maxstate = 10;
	private static Timer hurt;
	private int nenemy;
	/**constructor of the Frame 
	 * @param nenemy the number of enemy to initialize 
	 * @param f the frame that it should change in 
	 */
	public Window(int nenemy, JFrame f) {
		this.nenemy = nenemy;

		h = new Hero();
		enemies = new ArrayList<Enemy>();

		for (int i = 0; i < nenemy; i++) {
			this.enemies.add(new Enemy());
		}
		f.addKeyListener(new Keyadapt(h));//add new key listener 
		c = new Collision(h, enemies, hit);// the collision class 

	
		setFocusable(true);

		time = new Timer(1, this);//create a timer to runthrough the events 
		hurt = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (hit) {
					if (hld < maxstate) {
						hld++;

					} else {
						hld = 0;
						hit = false;
					}
				}
			}
		});
		time.start();// Timer time start 
		hurt.start();// Timer hurt start 

	}
/**
 * @return the number of enemy
 */
	public int getNenemy() {
		return nenemy;
	}

	@Override
	protected void paintComponent(Graphics g) {

		p = new painter(g, h, this);

		h.accept(p);
		for (int i = 0; i < enemies.size(); i++) {

			enemies.get(i).accept(p);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		h.move();

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).move();
		}
		c.doing();

		getScore();
		repaint();
		revalidate();
		if (h.getCurrenthealth() <= 0) {
			time.stop();
			hurt.stop();
			
			JOptionPane.showMessageDialog(this, "Game Over. Why didn't you live");
		
			try {
				new Writer(this,Frame.name);
				 
				  if(check == true)// to if server is running 
				{
					Frame.sendCommand("SCORE " + Integer.toString(getScore())+ " " + Integer.toString(Frame.getEnumber()));
					System.out.println("Sent the information");
					int choice = JOptionPane.showOptionDialog(null ,"Do you want to exit. Keep in mind if you hosted the server and exit others can't store their score", "Server", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null,null );//mesage box with yes or now for exit 
					
					if(choice == JOptionPane.YES_OPTION)//if the option is yes 
					{
						System.exit(0);
					}
					
				}
				  else
					{
						System.exit(0);
					}
				
			
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		
	}



	
	
	
	/*
 * function to get the current score of the hero
 */
	public int getScore() {
		int ctime = (int) System.currentTimeMillis();
		score = (int) ((ctime - stime) / 100);
		return score;
	}
	
	public static boolean isCheck() {
		return check;
	}
	/**
	 * 
	 * @param check to set the boolean value 
	 */
	public static void setCheck(boolean check) {
		Window.check = check;
	}
	
}
