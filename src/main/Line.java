package main;

import java.awt.image.BufferedImage;

public class Line {
	Point sourcePoint;
	Point destinationPoint;
	BufferedImage outputImage;

	public Line (BufferedImage outputImage) {
		this.outputImage = outputImage ; 
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

	public void bresenhamAlgorithm() {
		int dx = Math.abs(destinationPoint.getX() - sourcePoint.getX());
		int dy = Math.abs(destinationPoint.getY() - sourcePoint.getY());
		int x1 = sourcePoint.getX();
		int y1 = sourcePoint.getY();
		int x2 = destinationPoint.getX();
		int y2 = destinationPoint.getY();
		if (dx >= dy) {
			int p = 2 * dy - dx;
			int c1 = 2 * dy;
			int c2 = 2 * (dy - dx);

			int x = x1;
			int y = y1;
			// hoán đổi 2 điểm nếu điểm sau có hoành độ lớn hơn
			if (x1 > x2) {
				x = x2;
				y = y2;
				x2 = x1;
				y2 = y1;
			}
			Point point = new Point(outputImage);
			point.setXY(x, y);
			point.drawPixel();
			while (x <= x2) {
				x++;
				if (p < 0)
					p += c1;
				else {
					p += c2;
					if (y < y2) {
						y++;
					} else {
						y--;
					}
				}
				point.setXY(x, y);
				point.drawPixel();
			}

		} else {
			int p = 2 * dx - dy;
			int c1 = 2 * dx;
			int c2 = 2 * (dx - dy);

			int x = x1;
			int y = y1;
			// hoán đổi 2 điểm nếu điểm sau có hoành độ lớn hơn
			if (y1 > y2) {
				x = x2;
				y = y2;
				x2 = x1;
				y2 = y1;
			}

			Point point = new Point(outputImage);
			point.setXY(x, y);
			point.drawPixel();
			while (y <= y2) {
				y++;
				if (p < 0)
					p += c1;
				else {
					p += c2;

					if (x < x2) {
						x++;
					} else {
						x--;
					}
				}
				point.setXY(x, y);
				point.drawPixel();
			}

		}
	}

	public void midPointAlgorithm() {
		int x1 = sourcePoint.getX();
		int y1 = sourcePoint.getY();
		int x2 = destinationPoint.getX();
		int y2 = destinationPoint.getY();

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		if (dx > dy) {
			int p = 2 * dy - dx;
			int x = x1;
			int y = y1;
			if (x2 < x1) {
				x = x2;
				y = y2;
				x2 = x1;
				y2 = y1;
			}
			Point point = new Point(outputImage);
			point.setXY(x, y);
			point.drawPixel();
			while (x <= x2) {
				x++;
				if (p < 0) {
					p += 2 * dy;
				} else {
					y = (y < y2) ? (y + 1) : (y - 1);
					p += 2 * (dy - dx);
				}
				point.setXY(x, y);
				point.drawPixel();
			}
		} else {
			int p = 2 * dx - dy;
			int x = x1;
			int y = y1;
			if (y2 < y1) {
				x = x2;
				y = y2;
				x2 = x1;
				y2 = y1;
			}
			Point point = new Point(outputImage);
			point.setXY(x, y);
			point.drawPixel();
			while (y <= y2) {
				y++;
				if (p < 0) {
					p += 2 * dx;
				} else {
					x = (x < x2) ? (x + 1) : (x - 1);
					p += 2 * (dx - dy);
				}
				point.setXY(x, y);
				point.drawPixel();

			}

		}

	}
}
