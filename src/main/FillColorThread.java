package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.ImageIcon;

public class FillColorThread extends Thread {
	private Point startPoint;
	private BufferedImage outputImage;
	private Color color;

	public FillColorThread(Point startPoint, BufferedImage outputImage, Color color) {
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
	@Override
	public void run() {
		oilSpillWithStack();
		
		
	}

	public void loadImageToLabel() {
		Image image = outputImage.getScaledInstance(outputImage.getWidth(), outputImage.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(image);
		MainFrame.mainLabel.setIcon(imageIcon);
	}

	private Stack<Point> myStack = new Stack<>();
	
	public void oilSpillWithStack() {
		myStack.push(startPoint);
		while (!myStack.isEmpty()) {
			Point tempPoint = myStack.pop();
			tempPoint.setColor(color);
			tempPoint.setOutputImage(outputImage);
			loadImageToLabel();
			int x = tempPoint.getX();
			int y = tempPoint.getY();
			if(MainFrame.radioFillColorSlowSpeed.isSelected()) { 
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			} else { 
				FillColor fillColor = new FillColor(new Point(x,y), outputImage, color);
				fillColor.oilSpillWithStack();
				loadImageToLabel();
				return ; 
		
			}
		
		
			if (isInArea(x, y) && getRGBValue(x, y) != color.getRGB()) {
				tempPoint.drawPixel();
		
				if (y - 1 >= 0 && isInArea(x, y - 1)) {
					myStack.push(new Point(x, y - 1));
				}
				if (x - 1 >= 0 && isInArea(x - 1, y)) {
					myStack.push(new Point(x - 1, y));
				}
				if (y + 1 <= MainFrame.height - 1 && isInArea(x, y + 1)) {
					myStack.push(new Point(x, y + 1));
				}
				if (x + 1 <= MainFrame.width - 1 && isInArea(x + 1, y)) {
					myStack.push(new Point(x + 1, y));
				}
				
			}
		}
		 
		
	}


}
