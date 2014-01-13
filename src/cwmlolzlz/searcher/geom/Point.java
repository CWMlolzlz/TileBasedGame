package cwmlolzlz.searcher.geom;

import static org.lwjgl.opengl.GL11.GL_POINT;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPointSize;

import org.lwjgl.opengl.GL11;

import cwmlolzlz.searcher.visual.Color;

public class Point extends Shape{
	
	private final static int pointSize = 5;
	private double x = 0,y = 0,z = 0;

	public Point(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	
	public Point(double x, double y, double z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public Point(double[] c){
		switch(c.length){
			case(1):
				this.setX(c[0]);
			case(2):
				this.setY(c[1]);
			case(3):
				this.setZ(c[2]);
		}
	}
	
	public double[] getCoordinates(){return new double[]{this.x,this.y,this.z};}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	@Override
	public void glDraw(Color c){
		super.glDraw(c);
		glPointSize(pointSize);
		glBegin(GL_POINT);
			GL11.glVertex3d(x, y, z);
		glEnd();
	}
	
}