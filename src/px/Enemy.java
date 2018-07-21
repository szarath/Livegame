package px;



import px.paint.Ilive;

public class Enemy extends Live {

	private int x, y;
	private double vx, vy;

	private boolean start;
	private boolean ndir = false;

	private double pc = 2;
	private double nc = -2;
/**
 * constructor has random function to for the enemy location  
 */
	public Enemy() {

		x = (int) (150 + (Math.random() * ((748 - 150) + 1)));
		y = (int) (150 + (Math.random() * ((478 - 150) + 1)));
		start = true;
	}
/**
 * Move method for the enemy an algorithm that tells its moveing direction 
 */
	public void move() {
		if (start) {
			int velocity = 1 + (int) (Math.random() * ((2 - 1) + 1));

			if (velocity == 1)
				vx = pc;
			if (velocity == 2)
				vx = nc;

			velocity = 3 + (int) (Math.random() * ((4 - 3) + 1));

			if (velocity == 3)
				vy = pc;
			if (velocity == 4)
				vy = nc;

			start = false;
		}

		if (x >= 785) {
			x = 784;
			ndir = true;
		}

		if (x <= 0) {
			x = 1;
			ndir = true;
		}

		if (y >= 538) {
			y = 537;
			ndir = true;
		}

		if (y <= 100) {
			y = 101;
			ndir = true;
		}

		if (ndir) {
			if ((vx == pc) && (vy == pc)) {
				if (y == 537)
					vy = nc;
				if (x == 784)
					vx = nc;
			}

			else if ((vx == pc) && (vy == nc)) {
				if (y == 101)
					vy = pc;
				if (x == 784)
					vx = nc;
			}

			else if ((vx == nc) && (vy == pc)) {
				if (y == 537)
					vy = nc;
				if (x == 1)
					vx = pc;
			}

			else if ((vx == nc) && (vy == nc)) {
				if (y == 101)
					vy = pc;
				if (x == 1)
					vx = pc;
			}

			ndir = false;
		}

		x = (int) (x + vx);
		y = (int) (y + vy);
	}

	/**
	 * @return the get x of the enemy
	 */
	public int getX() {
		return (int) x;
	}


	/**
	 * @return the get y of the enemy
	 */
	public int getY() {
		return y;
	}

	@Override
	public void accept(Ilive l) {
		l.paint(this);

	}

}
