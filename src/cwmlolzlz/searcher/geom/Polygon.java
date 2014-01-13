package cwmlolzlz.searcher.geom;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3d;

import java.util.ArrayList;

import cwmlolzlz.searcher.visual.Color;

public class Polygon extends Shape{

	ArrayList<Point> points = new ArrayList<Point>();
	
	public Polygon(Point[] pArray){
		for(Point p : pArray){
			this.addPoint(p);
		}
	}
	
	public void addPoint(Point p){
		points.add(p);
	}
	
	@Override
	public void glDraw(Color c){
		super.glDraw(c);
		glBegin(GL_POLYGON);
			for(Point p : this.points){
				glVertex3d(p.getX(),p.getY(),p.getZ());
			}
		glEnd();
	}
	
}
