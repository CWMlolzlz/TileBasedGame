package cwmlolzlz.searcher;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import cwmlolzlz.searcher.geom.Rectangle;

public class DynamicProp extends Prop {

	private double dx,dy;
	private double yspeed = 1, xspeed = 1;
	private double speedMult = 1;
	private double oldx,oldy;

	public DynamicProp(World world, Prop parent, double x, double y, double width, double height, Align a, boolean collide, boolean draw,boolean perish, Texture texture){
		super(world, parent, x,y,width,height,a,collide,draw,perish,texture);
		this.dx = 0;
		this.dy = 0;
	}

	@Override
	public void update(int delta){
		oldx = getLocalX();
		oldy = getLocalY();
		
		setX(this.getX() + delta * dx * xspeed * speedMult);
		setY(this.getY() - delta * dy * yspeed * speedMult);
		
		checkCollisions();
		
		super.update(delta);
		
	}
	
	public boolean checkCollisions(){
		boolean collided = false;
		ArrayList<Prop> thisProps = this.getCollidableProps();
		ArrayList<Prop> worldProps = world.getCollidableProps();
				
		for(Prop thisP : thisProps){ //this props composite shapes
			for(Prop worldP : worldProps){ //all props in world
				if(Intersect.AABB(thisP.getGlobalShape(),worldP.getGlobalShape())){
					this.intersects(worldP);
					collided = true;
				}
			}
		}
		return collided;
	}
	
	public ArrayList<Prop> getCollidableProps(){
		ArrayList<Prop> props = new ArrayList<Prop>();
		for(Prop p : childProps){
			if(p.collidable){
				props.add(p);
			}
		}
		if(this.collidable){
			props.add(this);
		}
		return props;
	}
	
	public double getDX(){return dx;}
	public double getDY(){return dy;}
	public void setDX(double dx){this.dx = dx;}
	public void setDY(double dy){this.dy = dy;}
	
	public double getSpeedMult(){return speedMult;}
	public void setSpeedMult(double val){this.speedMult = val;}
	
	public double getXSpeed(){return xspeed;}
	public double getYSpeed(){return yspeed;}
	public void setXSpeed(double val){this.xspeed = val;}
	public void setYSpeed(double val){this.yspeed = val;}
	public void setSpeed(double val){this.setXSpeed(val);this.setYSpeed(val);}
	
	public void intersects(Prop b) {
		if(oldy < b.getGlobalY()+b.getHeight() && oldy+getHeight() > b.getGlobalY()){
			if(oldx > b.getGlobalX()){
				//right side
				this.setX(b.getGlobalX()+b.getWidth() + this.getXAlign());
			}else{
				//left
				this.setX(b.getGlobalX()-this.getWidth() + this.getXAlign());
			}
		}
		if(oldx < b.getGlobalX()+b.getWidth() && oldx+getWidth() > b.getGlobalX()){
			if(oldy > b.getGlobalY()){
				//bottom
				this.setY(b.getGlobalY()+b.getHeight() + this.getYAlign());
			}else{
				//top
				this.setY(b.getGlobalY()-this.getHeight() + this.getYAlign());
			}
		}
		if(perishable){
			delete();
		}
		if(b.perishable){
			b.delete();
		}
				
	}
}