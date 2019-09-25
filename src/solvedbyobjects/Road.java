package solvedbyobjects;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

/*
 * In this class we make a situation on the road
 * Here we create cars and calculate distance between them 
 */

public abstract class Road {
	
	//size of cells
	protected int scale;
	
	//overall number of iterations
	private int numberOfIterations;
	
	//the length of a road. 
	//I did a vertical and horizontal road the same size (symmetrical), so I don't need the height
	private final int L;
	
	//number of lanes on each road
	//Roads are symmetrical, so number of lanes is the same
	private final int numberOfLanes;

	//list of lanes which contains list of cars
	ArrayList<ArrayList<Car>> lanes;
	//list of cars
	ArrayList<Car> cars;
	
	//this variable is necessary to produce the random speed and the random position for a car
	Random rand;
	
	//initializing start values
	Road(PApplet p) {
		//width is the size of a interactive screen in the applet
		L = p.width;
	    scale = 20;
	    numberOfIterations = 0;
	    numberOfLanes = 2;
	    
	    lanes = new ArrayList<ArrayList<Car>>();
	    cars = new ArrayList<Car>();
	    rand = new Random();
	    
	    //add on each lane of the road lists of cars
	    //number of elements in the list is numberOflanes
	    for (int positionY = 0; positionY < numberOfLanes; positionY++) {	
	    	lanes.add(new ArrayList<Car>());
	    }
	    //method creates cars
	    //see below its implementation 
	    createCars();
	  }

	//cars are displayed differently for the vertical and horizontal road
	//Implementation are in RoadX and RoadY classes 
	public abstract void displayCars(ArrayList<ArrayList<Car>> lanes, PApplet p);
	
	//numbers of cells
	public int getLength() {
		  return L/scale;
	}
	
	
	public void createCars() {
		
		//add cars to the lanes
		for (int laneIndex = 0; laneIndex < numberOfLanes / 2; laneIndex++) {
			int newPosition = 0;
			
			//CarForward moves across positive x and y coordinates
			//Reference point(zero coordinate) is on the top left
			//y-coord directed to down, x-coord directed to the top right
			Car car = new CarForward (
	    			newPosition, 
	    			rand.nextInt(4), 
	    			laneIndex 
	    			);

			cars.add(car);
			lanes.get(laneIndex).add(car);
		}
		
		//there will be cars on the rest of lanes
		for (int laneIndex = numberOfLanes / 2; laneIndex < numberOfLanes; laneIndex++) {
			//these cars moves from limit coordinates 
			int newPosition = getLength() - 1;
			
			//these cars moves opposite direction to carForward
			Car car = new CarBackward (
	    			newPosition, 
	    			rand.nextInt(4),  
	    			laneIndex 
	    			);

			cars.add(car);
			lanes.get(laneIndex).add(car);
		}
	}

	
	//after some time add some new cars
	public void createCarAfterNIterations() {
		if (numberOfIterations > rand.nextInt(5)){
			createCars();
			numberOfIterations = 0;
		}
	}

	//According to Nagel-Schrekenberg model we need to have distance between cars
	//if the velocity of a car is more than distance to the closest car It needs to equate its velocity to distance
	//So the method returns the distance to the closest car
	public int distanceToClosestTo(Car car) {
		int closestDistance = getLength();
		ArrayList<Car> cars = lanes.get(car.getLaneIndex());

		for (Car otherCar : cars) { 
			if (car == otherCar) {  
				continue;
			}

		    int otherCarPosition = otherCar.getPosition();
		    int thisCarPosition = car.getPosition();
	
		    int distance;
		    if (car instanceof CarForward && otherCarPosition > thisCarPosition) {
		    	  distance = otherCarPosition - thisCarPosition;
		    } 
		    else if (car instanceof CarBackward && otherCarPosition < thisCarPosition) {
		    	  distance = thisCarPosition - otherCarPosition;
		    } 
		    else {
		        //distance = otherCarPosition + (GetLength() - thisCarPosition);
		    	  distance = Integer.MAX_VALUE;
		    }
	
		    if (distance < closestDistance) {
		    	  closestDistance = distance;
		    }
		}
		return closestDistance;
	}

	//method for running an application
	//it passes to the PApplet class
	public void run (PApplet p) {

		  displayCars(lanes, p);
		  createCarAfterNIterations();
		  numberOfIterations++;
		  //System.out.println(numberOfIterationsX);


		  for (ArrayList<Car> cars : lanes) {
			  for (Car car : cars) {
				  car.move();
				  car.speedUp();
				  car.speedDown(this);
			  }
		  }
	  }
}
