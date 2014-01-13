package cwmlolzlz.searcher;

import java.awt.Point;

import cwmlolzlz.searcher.geom.Rectangle;

public class Intersect {

	public static double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(distanceSquared(x1,x2,y1,y2));
	}
	
	public static double distanceSquared(double x1, double y1, double x2, double y2){
		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
	}
	
	public static boolean AABB(Rectangle rect1, Rectangle rect2){
		boolean thisright = rect1.getRight() > rect2.getLeft(); 
		boolean thisleft = rect1.getLeft() < rect2.getRight();
		boolean thisbottom = rect1.getBottom() > rect2.getTop();
		boolean thistop = rect1.getTop() < rect2.getBottom();
	
		boolean xintersect = thisright && thisleft;
		boolean yintersect = thistop && thisbottom;
		return xintersect && yintersect;
	}
	
	public static boolean pointInRectangle(Point p,Rectangle r){
		return pointInRectangle(p.getX(),p.getY(),r);
	}
	
	public static boolean pointInRectangle(double[] p, Rectangle r){
		return pointInRectangle(p[0],p[1],r);
	}
	
	public static boolean pointInRectangle(double x, double y, Rectangle r){
		boolean withinX = r.getLeft() <= x && x <= r.getRight();
		boolean withinY = r.getTop() <= y && y <= r.getBottom();
		return withinX && withinY;
	}
	
	
}
