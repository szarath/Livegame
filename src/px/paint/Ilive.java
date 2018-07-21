package px.paint;

import px.Hero;
import px.Enemy;
public interface Ilive {
	/**
	 * @param enemy the Enemy Class 
	 */
	void paint(Enemy enemy);
	/**
	 * @param hero the Hero Class 
	 */
	void paint(Hero hero);
}
