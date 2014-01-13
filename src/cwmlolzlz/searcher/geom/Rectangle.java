package cwmlolzlz.searcher.geom;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3d;
import cwmlolzlz.searcher.visual.Color;

public class Rectangle extends Shape{
	
	private double x,width,y,height;
	private static double z = 0;
	
	public Rectangle(double x, double y, double width, double height){
		super();
		this.x = x;
		this.width = width;
		this.y = y;
		this.height = height;
	}
	
	public double getLeft(){return x;}
	public double getRight(){return x+width;}
	public double getTop(){return y;}
	public double getBottom(){return y+height;}
	
	public void glDraw(Color c){
		super.glDraw(c);
		glBegin(GL_QUADS);
			glVertex3d(x,y,z);
			glVertex3d(x+width,y,z);
			glVertex3d(x+width,y+height,z);
			glVertex3d(x,y+height,z);
		glEnd();
	}
	
}
