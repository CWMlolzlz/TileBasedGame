package cwmlolzlz.searcher.geom;

import static org.lwjgl.opengl.GL11.glColor4d;

import cwmlolzlz.searcher.visual.Color;

public class Shape {

	public void glDraw(Color c){
		glColor4d(c.red(),c.green(),c.blue(),c.alpha());
	}
	
}
