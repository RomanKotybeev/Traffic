package solvedbyobjects;

import java.util.ArrayList;

import processing.core.PApplet;

//concrete realization of Road.
//It displays vertical roads and cars on its 
public class RoadY extends Road {
	
	RoadY (PApplet p) {
	    super(p);
	}
	  
	@Override
	public void displayCars(ArrayList<ArrayList<Car>> lanesY, PApplet p) {	
		//to draw a vertical road we need these loops. 
		//Moreover, It helps to erase a previous location of a car
		//rect(x_coord, y_coord, width, height) method draws a rectangle 
		//It draws one the vertical road that's why the first argument of rect() is the same 
		for (int positionX = 0; positionX < lanesY.size(); ++positionX) {
			for (int positionY = 0; positionY < getLength(); ++positionY) {
				//fill() is color 
				p.fill(200);
				p.rect(positionX*scale + p.width/2 , positionY*scale, scale, scale);
			}
	    }

		//This loops draw a car on the vertical lanes.
		//Number of lanes is lanesY.size()
		//getPosition() takes a position of a car
		for (int positionY = 0; positionY < lanesY.size(); ++positionY) {
			ArrayList<Car> carsY = lanesY.get(positionY);
			for (Car car : carsY) {
				p.fill(0, 255, 0);
				p.rect( positionY*scale + p.height/2, car.getPosition()*scale, scale, scale);
			}
	    }
	}
}
