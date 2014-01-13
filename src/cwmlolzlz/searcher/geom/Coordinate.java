package cwmlolzlz.searcher.geom;

public class Coordinate extends Point{
	
	private double x = 0,y = 0,z = 0;

	public Coordinate(double x, double y){
		super(x,y);
	}
	
	public Coordinate(double x, double y, double z){
		super(x,y,z);
	}
	
	public Coordinate(double[] c){
		super(c);
	}
	
	
	
}