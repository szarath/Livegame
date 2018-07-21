package px;


import px.paint.Ilive;

public class Hero extends Live {
	
	private int x, y , vx,vy;
	private double xs,ys;
	private int maxhealth = 100;
	private int currenthealth;

	/**
	 * constructor of the hero set the x and y position also the current health
	 */
	public Hero()
	{
		currenthealth = maxhealth;
		x = 10;
		y = 100;
		
		
		
	}
	

	/**
	 * move function of the hero 
	 */
	public void move()
	{
		if (x <= 0)
		{
			vx = 0;
			xs = 0;
			x = 1;
		}
		
		if (x >= 785)
		{
			vx = 0;
			xs = 0;
			x = 784;
		}
		
		if (y <= 100)
		{
			vy = 0;
			ys = 0;
			y = 101;
		}
		
		if (y >= 538)
		{
			vy = 0; 
			ys = 0;
			y = 537;
		}
		
		if (vx == 0)
		{
			if (xs > 0) xs = xs - 0.1;
			if (xs < 0) xs = xs + 0.1;
		}
		
		if (vy == 0)
		{
			if (ys > 0) ys = ys - 0.1;
			if (ys < 0) ys = ys + 0.1;
		}
		
		xs += vx * .12;
		ys += vy * .12;
		if (xs >= 3.2) xs = 3.2;
		if (xs <= -3.2) xs = -3.2;
		if (ys >= 3.2) ys = 3.2;
		if (ys <= -3.2) ys = -3.2;
		x = (int) (x + xs);
		y = (int) (y + ys);
	}
	/**
	 * @return teh get x fo the hero
	 */
	public int getX()
	{
		return (int) x;
	}

	/**
	 * @return the get y of the hero
	 */
	public int getY()
	{
		return (int) y;
	}
	
	

	/**
	 * set the vx 
	 */
	public void setvx(int vx) {
		this.vx = vx;
	}



	/**
	 * set the vy
	 */
	public void setvy(int vy) {
		this.vy = vy;
	}



	/**
	 * @return the current health of the hero
	 */
	public int getCurrenthealth() {
		return currenthealth;
	}
/**
 * hit function of the hero -1 current health for every hit
 */
	public void hit()
	{
		currenthealth -=1;
		
	}


	



	@Override
	public void accept(Ilive l) {
		l.paint(this);
	}



}

