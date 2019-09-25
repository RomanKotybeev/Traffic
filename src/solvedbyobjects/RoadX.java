package solvedbyobjects;

import java.util.ArrayList;

import processing.core.PApplet;


//concrete realization of Road.
//It displays horizontal roads and cars on its 
public class RoadX extends Road {
	
	RoadX (PApplet p) {
	    super(p);
	}
	
	  @Override
	public void displayCars(ArrayList<ArrayList<Car>> lanesX, PApplet p) {
		//to draw a horizontal road we need these loops. 
		//Moreover, It helps to erase a previous location of a car
		//rect(x_coord, y_coord, width, height) method draws a rectangle 
		//It draws one the horizontal road that's why the first argument of rect() is the same 
	    for (int positionY = 0; positionY < lanesX.size(); ++positionY) {
	    	for (int positionX = 0; positionX < getLength(); ++positionX) {
	    		p.fill(200);
	    		p.rect(positionX*scale, positionY*scale+p.height/2, scale, scale);
	    	}
	    }
	
	    //This loops draw a car on the vertical lanes.
  		//Number of lanes is lanesX.size()
  		//getPosition() takes a position of a car
	    for (int positionY = 0; positionY < lanesX.size(); ++positionY) {
	    	ArrayList<Car> carsX = lanesX.get(positionY);
	    	for (Car car : carsX) {
	    		p.fill(0, 0, 255);
	    		p.rect(car.getPosition()*scale, positionY*scale+p.height/2, scale, scale);
	    	}
	    }
	}

}
