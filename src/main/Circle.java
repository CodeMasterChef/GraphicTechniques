package main;

import java.awt.image.BufferedImage;

public class Circle {
	private Point centralPoint ; 
	private int radius ;
	private BufferedImage outputImage ; 
	public Circle(Point centralPoint, int radius , BufferedImage outputImage) {
		super();
		this.centralPoint = centralPoint;
		this.radius = radius;
		this.outputImage = outputImage; 
	} 
	private void drawCicle(int x, int y) { 
		Point point = new Point(outputImage) ; 
		int x0 = centralPoint.getX() ; 
		int y0 = centralPoint.getY() ; 
		
		point.setXY(x0 + x, y0 + y);
		point.drawPixel();
		
		point.setXY(x0 -  x, y0 + y);
		point.drawPixel();
		
		point.setXY(x0 + x, y0 -  y);
		point.drawPixel();
		
		point.setXY(x0 - x, y0 -  y);
		point.drawPixel();
		
		point.setXY(x0 + y , y0 + x);
		point.drawPixel();
		
		point.setXY(x0 - y , y0 + x);
		point.drawPixel();
		
		point.setXY(x0 + y , y0 - x);
		point.drawPixel();
		
		point.setXY(x0 - y , y0 - x);
		point.drawPixel();
		
	}
	public void bresenhamAlgorithm() {
		int x = 0 ; 
		int y = radius ; 
		int p =3 - 2*radius;
		while(x<=y) { 
			drawCicle(x, y);
			if(p<0) {
				p = p + 4*x + 6 ; 
			}else {
				p = p + 4*(x-y) + 10 ; 
				y-- ; 
			}
			x++ ; 
		}
	}
	public void midPointAlgorithm() { 
		int x = 0 ; 
		int y = radius ; 
		double p = 5/4 - radius ; 
		while(x<=y) { 
			drawCicle(x, y);
			if(p<0) { 
				p = p + 2*x + 3 ; 
			}else {
				p = p + 2 *(x-y) + 5 ;
				y-- ; 
			}
			x++ ; 
		}
	}
	
	
}
