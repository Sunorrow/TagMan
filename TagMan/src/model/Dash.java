package model;

public class Dash extends GameObject {

	private Game game;
	private TagMan tagMan;
	private boolean dropping;
	private int fallSpeed;
	
	
	public Dash(Game game) {
		this.game = game;
		this.tagMan = game.getTagMan();
		setWidth(15);
		setHeight(60);
	}
	
	@Override
	public void moveObject(int x, int y) {
		if (checkCollision(tagMan, x, y)) {
			tagMan.setDead(true);
			super.moveObject(x, y);
			game.setLevelInProgress(false);
			game.getMainController().stopTimer();
		}
		super.moveObject(x, y);
	}
	
	public boolean getDropping() {
		return dropping;
	}
	
	public void setDropping(boolean dropping) {
		this.dropping = dropping;
	}
	
	public void setFallSpeed(int speed) {
		this.fallSpeed = speed;
	}

	public int getFallSpeed() {
		return fallSpeed;
	}
	
	private boolean checkCollision(TagMan tagMan, int moveX, int moveY) {
		boolean collision = false;
		
		int newPosX = this.getX() + moveX;
		int newPosY = this.getY() + moveY;

		int xTagMax = tagMan.getX() + tagMan.getWidth() / 2;
		int xDashMax = newPosX + this.getWidth();

		int xTagMin = tagMan.getX() - tagMan.getWidth() / 2;
		int xDashMin = newPosX;

		int yTagMax = tagMan.getY() + tagMan.getHeight() / 2;
		int yDashMax = newPosY + this.getHeight();

		int yTagMin = tagMan.getY() - tagMan.getHeight() / 2;
		int yDashMin = newPosY;

		// Conditions for collision are:
		// 1: The left side of TagMan is to the left of maximum X of the other object.
		// 2: The right side of TagMan is to the right of the minimum X of the other
		// object.
		// 3: The
		if (xDashMin < xTagMax && xDashMax > xTagMin && yDashMin < yTagMax && yDashMax > yTagMin) {
			collision = true;
			System.out.println(collision);
		}
		
		return collision;
	}
	
}
