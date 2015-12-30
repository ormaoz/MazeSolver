import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


public class Segmentation {

	private DisplayImage displayImage;
	private UnionFind unionFind;
	private ArrayList<Point> redPoints;

	public static void main (String args[]) {
		
		// Goes over the files list
		for (String fileName : args) {
			
			// Create a new segmentation for each file
			Segmentation segmentation = new Segmentation(fileName);
			
			// and print the following: file name, red points coordinates,
			// number of components and w3hether or not the maze has a solution
			System.out.print(fileName);
			System.out.print(" maze start: " + segmentation.redPoints.get(0));
			System.out.print(" mid: " + segmentation.redPoints.get(1));
			System.out.println(" end: " + segmentation.redPoints.get(1));
			System.out.println("Number of components: " + segmentation.getNumComponents());
			System.out.println("maze has solution: " + segmentation.mazeHasSolution());
		}
		
	}
	
	/**
	 * Accepts the name of an image file and reads it using the DisplayImage. 
	 * Then creates an instance of UnionFind, and uses it to segment the image 
	 * into connected components.
	 * @param fileName - the name of the file
	 */
	public Segmentation (String fileName) {
		
		// Initialize DisplayImage and UnionFind
		displayImage = new DisplayImage(fileName);
		int imageSize = displayImage.height() * displayImage.width();
		unionFind = new UnionFind(imageSize);
		redPoints = new ArrayList<Point>();
		
		// Goes over the image, pixel after pixel starting the corner
		for (int x = 0; x < displayImage.width(); x++) {
			for (int y = 0; y < displayImage.height(); y++) {
				
				// Find the red points
				if (displayImage.isRed(x, y)) {
					
					// Save the red points
					redPoints.add(new Point(x, y));
					
					// Color the red points white
					displayImage.set(x, y, new Color (0xFF, 0xFF, 0xFF));
				}

				// Connect each pixel to its neighbor on the right 
				if (y < (displayImage.height() - 1)) {
					connect(x, y, x, y + 1);
				}

				// Connect the pixel to its neighbor below him
				if (x < (displayImage.width() - 1)) {
					connect(x, y, x + 1, y);
				}
			}
		}
	}
	
	public void connect (int x1, int y1, int x2, int y2) {
		if (displayImage.isOn(x1, y1) == displayImage.isOn(x2, y2)) {
			
			// Find the pixel leader
			int leaderPixel1 = unionFind.find(pixelId(x1, y1));
			int leaderPixel2 = unionFind.find(pixelId(x2, y2));
			
			// Union the leaders
			unionFind.union(leaderPixel1, leaderPixel2);
		}
	}
	
	/**
	 * generate unique key from (x, y) coordinates
	 * @param x
	 * @param y
	 * @return unique ID number
	 */
	private int pixelId (int x, int y) {
		return y * displayImage.width() + x;
		
	}
	
	/**
	 * checks whether or not the two given pixels belong to the same component
	 * @param x1 x coordinate of point 1
	 * @param y1 y coordinate of point 1
	 * @param x2 x coordinate of point 2
	 * @param y2 y coordinate of point 2
	 * @return
	 */
	public boolean areConnected (int x1, int y1, int x2, int y2) {
		
		// checks and return if two points are in the same set (have the same leader)
		return (unionFind.find(pixelId(x1, y1)) == unionFind.find(pixelId(x2, y2)));
	}
	
	/**
	 * returns the current number of components
	 * @return
	 */
	public int getNumComponents() {
		return unionFind.getNumSets();
	}
	
	public boolean mazeHasSolution() {
		
		// Collecting the 3 red points from memory and give them an ID
		int redPoint1 = pixelId((int)redPoints.get(0).getX(), (int)redPoints.get(0).getY());
		int redPoint2 = pixelId((int)redPoints.get(1).getX(), (int)redPoints.get(1).getY());
		int redPoint3 = pixelId((int)redPoints.get(2).getX(), (int)redPoints.get(2).getY());
		
		// Check if random 2 are have the same component
		if (unionFind.find(redPoint1) == unionFind.find(redPoint2)) {
			
			// if they do, check the same with the third point
			return unionFind.find(redPoint2) == unionFind.find(redPoint3);		
		} else {
			return false;
		}
	}
}
