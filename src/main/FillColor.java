package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class FillColor {
	private Point startPoint;
	private BufferedImage outputImage;
	private Color color;

	public FillColor(Point startPoint, BufferedImage outputImage, Color color) {
		super();
		this.startPoint = startPoint;
		this.outputImage = outputImage;
		this.color = color;
	}

	private int getRGBValue(int x, int y) {
		return outputImage.getRGB(x, y);
	}

	private boolean isInArea(int x, int y) {
		Point tempPoint = new Point(x, y, outputImage);
		int pixelColor = tempPoint.getPixelColor();
		if (pixelColor != MainFrame.drawColor.getRGB()) {
			return true;
		} else {
			return false;
		}
	}

	private Stack<Point> myStack = new Stack<>();

	public void oilSpillWithStack() {
		myStack.push(startPoint);
		while (!myStack.isEmpty()) {
			Point tempPoint = myStack.pop();
			tempPoint.setColor(color);
			tempPoint.setOutputImage(outputImage);
			int x = tempPoint.getX();
			int y = tempPoint.getY();
			if (isInArea(x, y) && getRGBValue(x, y) != color.getRGB()) {
				tempPoint.drawPixel();
				if (x + 1 <= MainFrame.width - 1) {
					myStack.push(new Point(x + 1, y));
				}
				if (x - 1 >= 0) {
					myStack.push(new Point(x - 1, y));
				}
				if (y + 1 <= MainFrame.height - 1) {
					myStack.push(new Point(x, y + 1));
				}
				if (y - 1 >= 0) {
					myStack.push(new Point(x, y - 1));
				}
			}
		}
	}


}
