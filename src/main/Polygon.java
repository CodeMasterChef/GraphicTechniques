package main;

import java.awt.image.BufferedImage;

public class Polygon {
	private Point startPoint ; 
	private Point middlePoint ; 
	private BufferedImage outputImage ;
	public Point getMiddlePoint() {
		return middlePoint;
	}
	public Polygon(BufferedImage outputImage) { 
		this.outputImage = outputImage ;
	}
	
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Polygon(Point startPoint, BufferedImage outputImage) {
		super();
		this.startPoint = startPoint;
		this.outputImage = outputImage;
		this.middlePoint = startPoint ; 
	}

	public void setMiddlePoint(Point middlePoint) {
		this.middlePoint = middlePoint ;
		//this.middlePoint.setXY(middlePoint.getX(), middlePoint.getY());
	} 
	public void drawDestinationPoint(Point destinationPoint) { 
		Line line = new Line(middlePoint, destinationPoint, outputImage) ; 
		line.bresenham(); 
		setMiddlePoint(destinationPoint);
	}
	public void endDrawing() { 
		drawDestinationPoint(startPoint);
	}
	
	
}
