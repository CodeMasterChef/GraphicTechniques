package main;

import java.awt.image.BufferedImage;

public class Ellipse {
	private Point centralPoint ; 
	private int a ;
	private int b;
	private BufferedImage outputImage ; 
	public Ellipse(Point centralPoint, int aAxis , int bAxis , BufferedImage outputImage) {
		super();
		this.centralPoint = centralPoint;
		this.a = aAxis;
		this.b = bAxis ; 
		this.outputImage = outputImage; 
	}
	private void drawEllipse(int x, int y) { 
		Point point = new Point(outputImage) ; 
		int x0 = centralPoint.getX() ; 
		int y0 = centralPoint.getY() ; 
		
		point.setXY(x0 + x, y0 + y);
		point.drawPixel();
		
		point.setXY(x0 -  x, y0 + y);
		point.drawPixel();
		
		point.setXY(x0 - x, y0 -  y);
		point.drawPixel();
		
		point.setXY(x0 + x, y0 -  y);
		point.drawPixel();
		
	}
	public void bresenham() { 
		double a2 = a * a  ; 
		double b2 = b * b ;
		// nhanh 1 
		int x = 0 ; 
		int y = b ; 
		double p = 2 * b2/a2 - 2*b + 1 ;
		
		while ( ( b2/a2 *  (double) x/y ) < 1.0) { 
			drawEllipse(x, y);
			if(p<0) {
				p = p + 2 *  (b2/a2) * (2*x+3) ; 
			} else { 
				p = p - 4*y + 2 * ( b2/a2) * (2*x+3) ; 
				y = y -1 ; 
			}
			x++ ; 
		}
		// nhanh 2
		y = 0 ; 
		x = a ; 
		p = 2 *(a2/b2) - 2 * a + 1 ;
		while( ( a2/b2 * (double)y/x  ) <=1 ) { 
			drawEllipse(x, y);
			if(p<0)  {
				p = p + 2* (a2/b2) * (2*y+3) ; 
			} else { 
				p = p - 4*x + 2* (a2/b2) * (2*y+3) ; 
				x = x -1 ; 
			}
			y++ ; 
		}
	}
	public void midPointAlgorithm() { 
		double a2 = a * a  ; 
		double b2 = b * b ;
		// nhanh 1 
		int x = 0 ; 
		int y = b ; 
		double p = b2 - a2 * b + 0.25 *a2 ; 
		
		while ( ( b2/a2 *  (double) x/y ) < 1) { 
			x++ ; 
			if(p<0) {
				p = p + b2 + 2*b2*x; 
			} else { 
				y = y -1 ; 
				p = p +b2 + 2*b2*x - 2*a2*y ; 
			}
			
			drawEllipse(x, y);
		}
		// nhanh 2
		p = b2 *(x+0.5) * (x+0.5) + a2 *(y-1)*(y-1) - a2*b2 ; ;
		while( y > 0 ) { 
			y-- ; 
			if(p>0)  {
				p = p +  a2 - 2*a2*y ; 
			} else { 
				x++ ; 
				p = p + a2 + 2*b2*x - 2*a2*y; 
			}
			drawEllipse(x, y);
		}
	}
}
