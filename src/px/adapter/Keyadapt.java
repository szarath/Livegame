package px.adapter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import px.Hero;

public class Keyadapt extends KeyAdapter {
	private Hero h;
	/**
	 * @param h the hero that has to be moved 
	 */
	public Keyadapt(Hero h)
	{
		
		this.h = h;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) h.setvx(0);;
		if (key == KeyEvent.VK_RIGHT) h.setvx(0);
		if (key == KeyEvent.VK_UP) h.setvy(0);
		if (key == KeyEvent.VK_DOWN) h.setvy(0);
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) h.setvx((int) -5);
		if (key == KeyEvent.VK_RIGHT) h.setvx((int) 5);
		if (key == KeyEvent.VK_UP) h.setvy((int) -5);
		if (key == KeyEvent.VK_DOWN) h.setvy((int) 5);
	
		
	}

	

}
