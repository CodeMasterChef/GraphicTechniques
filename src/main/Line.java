package main;

import java.awt.image.BufferedImage;

public class Line {
	Point sourcePoint;
	Point destinationPoint;
	BufferedImage outputImage;

	public Line(BufferedImage outputImage) {
		this.outputImage = outputImage;
	}

	public Line(Point source, Point destination, BufferedImage output) {
		sourcePoint = source;
		destinationPoint = destination;
		outputImage = output;
	}

	public Point getSourcePoint() {
		return sourcePoint;
	}

	public void setSourcePoint(Point sourcePoint) {
		this.sourcePoint = sourcePoint;
	}

	public Point getDestinationPoint() {
		return destinationPoint;
	}

	public void setDestinationPoint(Point destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	public void setOutputImage(BufferedImage outputImage) {
		this.outputImage = outputImage;
	}

	public void DDAAlgorithm() {

		int x_1 = sourcePoint.getX();
		int y_1 = sourcePoint.getY();
		int x_2 = destinationPoint.getX();
		int y_2 = destinationPoint.getY();

		int dx = x_2 - x_1;
		int dy = y_2 - y_1;
		int steps = (Math.abs(dx) > Math.abs(dy)) ? Math.abs(dx) : Math.abs(dy);

		double x_inc = (double) dx / steps;
		double y_inc = (double) dy / steps;
		double x = x_1;
		double y = y_1;

		Point temPoint = new Point(outputImage);
		temPoint.setXY((int) Math.round(x), (int) Math.round(y));
		temPoint.drawPixel();

		for (int i = 1; i <= steps; i++) {
			x = x + x_inc;
			y = y + y_inc;
			temPoint.setXY((int) Math.round(x), (int) Math.round(y));
			temPoint.drawPixel();
		}

	}

	private void bresenham(int x_source, int y_source, int x_destination, int y_destination, int option) {
		int dy = Math.abs(y_destination - y_source);
		int dx = Math.abs(x_destination - x_source);
		int p = 2 * dy - dx;
		int c1 = 2 * dy;
		int c2 = 2 * (dy - dx);

		Point point = new Point(outputImage);
		if (option == 0) {
			point.setXY(x_source, y_source);
		} else {
			point.setXY(y_source, x_source);
		}

		point.drawPixel();
		while (x_source <= x_destination) {
			x_source++;
			if (p < 0)
				p += c1;
			else {
				p += c2;
				y_source  =  (y_source < y_destination) ? (y_source+1) : (y_source - 1) ; 
			}
			if (option == 0) {
				point.setXY(x_source, y_source);
			} else {
				point.setXY(y_source, x_source);
			}
			point.drawPixel();
		}

	}

	public void bresenhamAlgorithm() {
		int dx = Math.abs(destinationPoint.getX() - sourcePoint.getX());
		int dy = Math.abs(destinationPoint.getY() - sourcePoint.getY());
		int x1 = sourcePoint.getX();
		int y1 = sourcePoint.getY();
		int x2 = destinationPoint.getX();
		int y2 = destinationPoint.getY();
		int x_source;
		int y_source;
		int x_destination;
		int y_destination;

		if (dx >= dy) {
			// xác định điểm bắt đầu và điểm kết thúc
			if (x1 > x2) {
				x_source = x2;
				y_source = y2;
				x_destination = x1;
				y_destination = y1;
			} else {
				x_source = x1;
				y_source = y1;
				x_destination = x2;
				y_destination = y2;
			}
			bresenham(x_source, y_source, x_destination, y_destination, 0);

		} else {
			// xác định điểm bắt đầu và điểm kết thúc
			if (y1 > y2) {
				x_source = x2;
				y_source = y2;
				x_destination = x1;
				y_destination = y1;
			} else {
				x_source = x1;
				y_source = y1;
				x_destination = x2;
				y_destination = y2;
			}
			bresenham(y_source, x_source, y_destination, x_destination, 1);
		}
	}

	private void midPoint(int x_source, int y_source, int x_destination, int y_destination, int option) {
		int dx = Math.abs(x_destination - x_source);
		int dy = Math.abs(y_destination - y_source);
		int p = 2 * dy - dx;
		Point point = new Point(outputImage);
		if (option == 0) {
			point.setXY(x_source, y_source);
		} else {
			point.setXY(y_source, x_source);
		}

		point.drawPixel();
		while (x_source <= x_destination) {
			x_source++;
			if (p < 0) {
				p += 2 * dy;
			} else {
				y_source = (y_source < y_destination) ? (y_source + 1) : (y_source - 1);
				p += 2 * (dy - dx);
			}
			if (option == 0) {
				point.setXY(x_source, y_source);
			} else {
				point.setXY(y_source, x_source);
			}
			point.drawPixel();
		}
	}

	public void midPointAlgorithm() {
		int dx = Math.abs(destinationPoint.getX() - sourcePoint.getX());
		int dy = Math.abs(destinationPoint.getY() - sourcePoint.getY());
		int x1 = sourcePoint.getX();
		int y1 = sourcePoint.getY();
		int x2 = destinationPoint.getX();
		int y2 = destinationPoint.getY();
		int x_source;
		int y_source;
		int x_destination;
		int y_destination;

		if (dx >= dy) {
			// xác định điểm bắt đầu và điểm kết thúc
			if (x1 > x2) {
				x_source = x2;
				y_source = y2;
				x_destination = x1;
				y_destination = y1;
			} else {
				x_source = x1;
				y_source = y1;
				x_destination = x2;
				y_destination = y2;
			}
			midPoint(x_source, y_source, x_destination, y_destination, 0);

		} else {
			// xác định điểm bắt đầu và điểm kết thúc
			if (y1 > y2) {
				x_source = x2;
				y_source = y2;
				x_destination = x1;
				y_destination = y1;
			} else {
				x_source = x1;
				y_source = y1;
				x_destination = x2;
				y_destination = y2;
			}
			midPoint(y_source, x_source, y_destination, x_destination, 1);

		}
		
		
	}
}
