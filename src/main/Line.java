package main;

import java.awt.image.BufferedImage;


public class Line {
	Point sourcePoint;
	Point destinationPoint;
	BufferedImage outputImage;

	public Line(Point source, Point destination, BufferedImage output) {
		sourcePoint = source;
		destinationPoint = destination;
		outputImage = output;
	}

	public void DDAAlgorithm() {
	
		int x_1 = sourcePoint.getX();
		int y_1 = sourcePoint.getY();
		int x_2 = destinationPoint.getX();
		int y_2 = destinationPoint.getY();
		
		
		int dx = x_2 - x_1 ;
		int dy = y_2 - y_1 ;
		int steps =   (Math.abs(dx) > Math.abs(dy)) ? Math.abs(dx):Math.abs(dy) ; 
	
		double x_inc = (double) dx / steps;
		double y_inc = (double) dy / steps;
		int x =  x_1 ; 
		int y = y_1 ; 
	
		Point temPoint  = new Point(x,y) ; 
		temPoint.drawPoint(outputImage);
	
		for (int i = 1; i <= steps; i++) {
			x = (int) Math.round( x + x_inc);
			y = (int) Math.round(y +y_inc) ; 
	
			temPoint.setXY(x, y);
			temPoint.drawPoint(outputImage);

		}

	}

	public void bresenhamAlgorithm() {
		int x_1 = sourcePoint.getX();
		int y_1 = sourcePoint.getY();

		int x_2 = destinationPoint.getX();
		int y_2 = destinationPoint.getY();

		int deltaX = Math.abs(x_2 - x_1);
		int deltaY = Math.abs(y_2 - y_1);
		int constant_1 = 2 * (deltaY);
		int constant_2 = 2 * (deltaY - deltaX);
		int p = 2 * deltaY - deltaX;
		int x, y, xMax, yMax ;

		if (x_1 > x_2) {
			x = x_2;
			y = y_2;
			xMax = x_1;
			yMax = y_1 ; 
		} else {
			x = x_1;
			y = y_1;
			xMax = x_2;
			yMax = y_2 ;
		}

		
		Point tempPoint = new Point(x,  y);
		tempPoint.drawPoint(outputImage);

		while (x < xMax) {
			x = x + 1;
			if (p < 0) {
				p += constant_1;

			} else {
				if(y<yMax) { 
					y = y + 1;
				} else { 
					y = y - 1 ; 
				}
				
				p += constant_2;
			}
			System.out.println(p);
			System.out.println(tempPoint);
			tempPoint.setXY(x,  y);
			tempPoint.drawPoint(outputImage);

		}
	}
}
