package px.adapter;

import java.util.ArrayList;

import px.Enemy;
import px.Hero;

public class Collision {
	
	private Hero h;
	private int[] enex;
	private int[] eney;
	private int[] uex;
	private int[] uey;
	private int[] lex;
	private int[] ley;
	private boolean hit;
	private int change = 5;
	private boolean work = true;
	private ArrayList<Enemy> enemies;
	/**
	 * 
	 * @param h the Hero class is sent in
	 * @param enemies the arraylist of enemies 
	 * @param hit to see if the hero and enemy hit or not 
	 */
	public Collision(Hero h,ArrayList<Enemy> enemies, boolean hit )
	{
		this.h =h;
		this.enemies = enemies;
		this.hit = hit;
		
		enex = new int[enemies.size()];
		eney = new int[enemies.size()];
		uex = new int[enemies.size()];
		uey = new int[enemies.size()];
		lex = new int[enemies.size()];
		ley = new int[enemies.size()];
	}
	
	
	
	/**
	 * function doing checks if the hero and the enemy collide any time
	 */
	public void doing() {

		int hx = h.getX();
		int hy = h.getY();

		for (int i = 0; i < enemies.size(); i++) {
			enex[i] = (int) enemies.get(i).getX();
			eney[i] = (int) enemies.get(i).getY();

		}
		for (int i = 0; i < enemies.size(); i++) {
			lex[i] = enex[i] - change;
			uex[i] = enex[i] + change;
			ley[i] = eney[i] - change;
			uey[i] = eney[i] + change;

		}

		if (work == true) {
			for (int i = 0; i < enemies.size(); i++) {
				if (((hx >= lex[i]) && (hx <= uex[i]))
						&& ((hy >= ley[i]) && (hy <= uey[i]))) {
					if (hit == false) {
						h.hit();
					}
				}
			}

			if (h.getCurrenthealth() == 0) {
				work = false;

			}
		}
	}

}
