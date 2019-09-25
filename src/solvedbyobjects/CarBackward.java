package solvedbyobjects;


//Car which moves from right to left or from bottom to top
//Reference point is on the top left
public class CarBackward extends Car {
	
	CarBackward(int position, int velocity,  int laneIndex) {
		super(position, velocity, laneIndex);
	}

	//
	@Override
	public void move() {
		position -= velocity;
	}	
}
