package solvedbyobjects;

/*
 * A class represent cars on the road
 */
public abstract class Car {
	//position of a car
	protected int position;
	//velocity of a car
	protected int velocity;
	//lane on which a car will move
	protected int laneIndex;
	//the maximum of velocity
	protected final int VMAX = 5;


	Car(int position, int velocity, int laneIndex) {
		this.position = position;
	    this.velocity = velocity;
	    this.laneIndex = laneIndex;
	} 
	
	//a car moves forward or backward
	//to implement this was created CarForward and CarBackward classes 
	public abstract void move();

	/*
	 * getters for position, velocity, laneIndex
	 */
	public int getPosition() {
	    return position;
	}

	public int getVelocity() {
	    return velocity;
	}

	public int getLaneIndex() { 
		return laneIndex;
	}

	
//	public void moveBack() {
//	    position -= velocity;
//	}

	//According to the Nagel-Schrekenberg model a car increase velocity by one until vmax
	public void speedUp() {
		if (velocity < VMAX) {
			velocity++;
	    }
	}
	
	//if the velocity of a car is more than distance to the closest car It needs to equate its velocity to distance
	public void speedDown(Road road) {
		int distanceToClosestCar = road.distanceToClosestTo(this);
		if (velocity >= distanceToClosestCar) {
			velocity = distanceToClosestCar;
		}
	}
}
