package solvedbyobjects;


//Car which moves from left to right or from top to bottom
//Reference point is on the top left
public class CarForward extends Car {
	
	CarForward(int position, int velocity,  int laneIndex) {
	    super(position, velocity, laneIndex);
	}

	@Override
    public void move() {
		position += velocity;
	}

}
