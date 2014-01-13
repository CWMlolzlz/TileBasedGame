package cwmlolzlz.searcher;

import cwmlolzlz.searcher.geom.Coordinate;


public class Entity {
	
	Coordinate coord;
	World world;
	
	public Entity(World world,double x, double y){
		this.coord = new Coordinate(x,y);
		this.world = world;
	}
	
	public void setLocation(double x, double y){
		setLocation(new Coordinate(x,y));
	}
	
	public void setLocation(Coordinate coord){
		this.coord = coord;
	}
	
	public void setLocation(double[] coords){
		setLocation(new Coordinate(coords));
	}
	public void delete(){
		world.dispose(this);
	}
	
	public void setX(double x){this.coord.setX(x);}
	public void setY(double y){this.coord.setY(y);}
	public void setZ(double z){this.coord.setZ(z);}
	
	public double getX(){return this.coord.getX();}
	public double getY(){return this.coord.getY();}
	public double getZ(){return this.coord.getZ();}
	
	public Coordinate getCoordinate(){
		return coord;
	}
}
