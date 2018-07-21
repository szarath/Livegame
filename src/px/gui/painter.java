package px.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import px.Enemy;
import px.Hero;
import px.paint.Ilive;

public class painter implements Ilive {

	private Graphics g;
	private Window w;
	private Hero h;

	/**
	 * @param g
	 *            the Graphics
	 * @param h
	 *            the Hero class
	 * @param w
	 *            the Window class
	 */
	public painter(Graphics g, Hero h, Window w) {
		this.g = g;
		this.h = h;
		this.w = w;
		backg();
	}

	@Override
	public void paint(Enemy enemy) {
		g.setColor(Color.BLUE);
	
		g.fillOval(enemy.getX(), enemy.getY(), 10, 10);
	}

	@Override
	public void paint(Hero hero) {
		g.setColor(Color.YELLOW);
		g.fillOval(hero.getX(), hero.getY(), 10, 10);

	}
/**
 * function to draw basic like the panel with score health and number of enemy
 */
	public void backg() {

		g.setColor(Color.WHITE);
		g.drawLine(0, 100, 800, 100);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.BLACK);
		g.fillRect(0, 100, 800, 500);
		g.setFont(new Font("Arial", 0, 35));

		g.drawString("Score : " + w.getScore(), 10, 60);
		g.drawString("Number : " + w.getNenemy(), 300, 60);
		g.drawString("Health : " + h.getCurrenthealth(), 600, 60);
	}

}
