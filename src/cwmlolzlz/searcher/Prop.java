package cwmlolzlz.searcher;

import static org.lwjgl.opengl.GL11.glColor4d;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import cwmlolzlz.searcher.geom.Rectangle;
import cwmlolzlz.searcher.visual.Color;

public class Prop extends Entity {

	protected ArrayList<Prop> childProps = new ArrayList<Prop>();
	Prop parent;
	
	Color color = new Color(0,0.5,0);
	
	public enum Align {
		NONE,
		CENTRE
	}
	
	boolean collidable, perishable, textured, drawable;
	protected Texture texture = null;
	
	private Align alignment = Align.NONE;
	
	private double width,height, halfHeight, halfWidth;
	private double xAlign, yAlign;
	
	public Prop(World world, Prop parent, double x, double y, double width, double height, boolean draw){
		//invisible prop, not drawn, collidable or perishable
		super(world,x,y);
		this.parent = parent;
		this.height = height;
		this.width = width;
		halfWidth = width/2;
		halfHeight = height/2;
		this.setCollidable(false);
		this.setPerishable(false);
		this.setTextured(false);
		this.setDrawable(draw);
	}
	
	public Prop(World world, Prop parent, double x, double y, double width, double height, Align align, boolean draw){
		//visible, may be collidable or perishable and alignable, no texture
		super(world,x,y);
		this.parent = parent;
		this.height = height;
		this.width = width;
		halfWidth = width/2;
		halfHeight = height/2;
		this.alignment = align;
		this.setCollidable(false);
		this.setPerishable(false);
		this.setTextured(false);
		this.setDrawable(draw);
	}
	
	public Prop(World world,Prop parent,double x, double y, double width, double height, boolean collide, boolean draw ,boolean perish){
		//aligned NONE, may be collidable,perishable and drawable,, not textured
		super(world,x,y);
		this.parent = parent;
		this.height = height;
		this.width = width;
		halfWidth = width/2;
		halfHeight = height/2;
		this.setCollidable(collide);
		this.setPerishable(perish);
		this.setTextured(false);
		this.setDrawable(draw);
	}
	
	public Prop(World world,Prop parent,double x, double y, double width, double height, boolean collide, boolean draw ,boolean perish, Texture t){
		//textured, no alignment, can be collidable, drawable, and perishable
		super(world,x,y);
		this.parent = parent;
		this.height = height;
		this.width = width;
		halfWidth = width/2;
		halfHeight = height/2;
		this.setCollidable(collide);
		this.setPerishable(perish);
		this.setTexture(t);
		this.setDrawable(draw);
	}
	
	public Prop(World world,Prop parent,double x, double y, double width, double height, Align a, boolean collide, boolean draw ,boolean perish, Texture t){
		// is textured, may be aligned, collidable,drawable,and perishable
		super(world,x,y);
		this.parent = parent;
		this.height = height;
		this.width = width;
		halfWidth = width/2;
		halfHeight = height/2;
		alignment = a;
		this.setCollidable(collide);
		this.setPerishable(perish);
		this.setTexture(t);
		this.setDrawable(draw);
	}
		
	public void draw(double xoffset, double yoffset) {
		for(Prop p : childProps){
			p.draw(this.getLocalX(),this.getLocalX());
		}
				
		switch(alignment){
		case NONE:
			xAlign = 0;
			yAlign = 0;
			break;
		case CENTRE:
			xAlign = halfWidth;
			yAlign = halfHeight;
			break;
		}
		
		if(textured){
			//DrawPNG.drawPNG(drawMinX, drawMinY, width, height, texture);
		}else if(drawable){
			this.getGlobalShape().glDraw(color);
		}
	}
	
	public void update(int delta){
		for(Prop p : childProps){
			p.update(delta);
		}
		//updateDimensions();
	}
	
	//          !GETTERS!
	
	public Prop getPropAt(double x, double y){
		for(Prop p : childProps){
			if(Intersect.pointInRectangle(x, y, p.getGlobalShape())){
				return p;
			}
		}
		return null;
	}
	
	public Color getColor(){return color;}
	
	public Rectangle getLocalShape(){return new Rectangle(this.getLocalX(),this.getLocalX(),width,height);}
	public Rectangle getGlobalShape(){return new Rectangle(this.getGlobalX(),this.getGlobalY(),width,height);}

	public ArrayList<Prop> getChildProps(){return childProps;}
		
	public Prop getPropAt(double[] coords){return getPropAt(coords[0],coords[1]);}
	
	public double getHeight() {return height;}	
	public double getWidth() {return width;}
	
	public double getXAlign(){return xAlign;}
	public double getYAlign(){return yAlign;}
	
	public double getCentreX(){return this.getLocalX()-halfWidth;}
	public double getCentreY(){return this.getLocalY()-halfHeight;}
	
	public double getLocalX(){return this.getX() - xAlign;}
	public double getLocalY(){return this.getY() - yAlign;}
	
	public double getGlobalX(){ //recursion WOOOOOOO!!!!!
		if(parent != null){
			return getLocalX() + parent.getGlobalX();
		}else{
			return getLocalX();
		}
		
	}
	
	public double getGlobalY(){ //recursion WOOOOOOO!!!!!
		if(parent != null){
			return getLocalY() + parent.getGlobalY();
		}else{
			return getLocalY();
		}
	}
	
	//          !SETTERS!
	public void setColor(double r, double g, double b, double a){color = new Color(r,g,b,a);}
	public void setWidth(double width) {this.width = width;}	
	public void setHeight(double height) {this.height = height;}
	public void setDrawable(boolean draw){this.drawable = draw;}
	public void setCollidable(boolean collide){this.collidable = collide;}
	public void setPerishable(boolean perish){this.perishable = perish;}
	public void setTextured(boolean textured){this.textured = textured;}
	public void setTexture(Texture t){
		if(t != null && t instanceof Texture){
			this.texture = t;
			this.setTextured(true);
		}
	}
}
