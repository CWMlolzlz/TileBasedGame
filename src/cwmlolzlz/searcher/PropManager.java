package cwmlolzlz.searcher;

import java.util.ArrayList;

public class PropManager extends Prop{

	public PropManager(World world, double x, double y){
		super(world, x,y,0,0,Align.NONE,false,false,false,null);
	}
	
	//acts as transform group for blocks hence it adds its offsets
	public void drawProps(double x, double y){
		for(Prop p : childProps){
			p.draw(x + this.x, y + this.y);
		}
	}
	
	public void addChildProp(Prop p){
		childProps.add(p);
	}
	
	public ArrayList<Prop> getChildProps(){return childProps;}
	
}
