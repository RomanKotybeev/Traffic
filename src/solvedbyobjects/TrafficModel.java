package solvedbyobjects;

import processing.core.PApplet;

/* TrafficModel
 * An application with an interactive map displaying a traffic model
 * @author Roman Kotyubeev.
 * Date: March 17, 2019
 */

// To display cars was used processing library
// It needs to inherit PApplet
public class TrafficModel extends PApplet {
	
	// classes responsible for creating horizontal and vertical (X and Y) roads with cars 
	Road roadX;
	Road roadY;
	
	// in processing 3 needs to be in the main class. 
	// Another way is to add an argument "package.class" in the run configuration 
	public static void main(String[] args) {
        PApplet.main("solvedbyobjects.TrafficModel");
    	}
	
	// It needs to be here in new version of processing. 
	// The size method must be here not in the setup method 
	public void settings() {
		size(640, 640);
	}
	
	// setup method will be initialized just one time
	public void setup() {
		//background is white
		background(255);
		//the speed of interactivity
		frameRate(1);
		roadX = new RoadX(this);
		roadY = new RoadY(this);
	}

	// draw method will be always run until you don't stop by hand
	public void draw() {
		roadX.run(this);
		roadY.run(this);
	}
}
