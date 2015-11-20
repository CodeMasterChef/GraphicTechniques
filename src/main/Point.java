package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Point {
	private int x;
	private int y;
	private Color color = new Color(0, 0, 0, 255); // default is black
	private BufferedImage outputImage  ; 
	public Point(BufferedImage outputImage) {
		this.x = 0;
		this.y = 0;
		this.outputImage = outputImage ; 
	}

	public Point(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public String getPointRGB(BufferedImage iImage) {
		int pixel = iImage.getRGB(x, y);
		// int alpha = (pixel >> 24) & 0xff;
		int r = (pixel >> 16) & 0xff;
		int g = (pixel >> 8) & 0xff;
		int b = (pixel) & 0xff;
		return (r + " ; " + g + " ; " + b);
	}

	public void drawPixel() {
		try {
			int k = color.getRGB();
			outputImage.setRGB(x, y, k);
		} catch (Exception e) {
			//System.out.println("Error: " +x + " " +  y);
		}

	}

	public static void putPixel(int x, int y, int color, BufferedImage outputImage) {

		outputImage.setRGB(x, y, color);
	}

	@Override
	public String toString() {
		return "Point in Math's axis [x=" + (x + MainFrame.width / 2) + ", y=" + (MainFrame.height / 2 - y) + "]";
	}

}
