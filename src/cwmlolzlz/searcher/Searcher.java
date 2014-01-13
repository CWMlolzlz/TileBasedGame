package cwmlolzlz.searcher;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.lwjgl.util.glu.GLU;

public class Searcher {

	private static final String VERSION = "1.00 Pre-Alpha";
	private long lastFrame;
	World world;
	
	private long getTime(){
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public int getDelta(){
		long current = getTime();
		int delta = (int) (current - lastFrame);
		lastFrame = current;
		return delta;
	}
	
	public static int displayWidth = 800, displayHeight = 600;
		
	public Searcher(){
		try {
			Display.setDisplayMode(new DisplayMode(displayWidth,displayHeight));
			
			Display.setTitle("Searcher " + VERSION);
			Display.create();
		} catch (LWJGLException e) {
			Display.destroy();
			System.exit(0);
		}
		
		
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_3D_COLOR);
		//glShadeModel(GL_SMOOTH);
		//glDisable(GL_DEPTH_TEST);
		//glDisable(GL_LIGHTING);
		
		//glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//glClearDepth(1);
		
		//glEnable(GL_BLEND);
		//glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				
		//glViewport(0,0,Display.getWidth(),Display.getHeight());
		
		
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(-90f,Display.getWidth()/-Display.getHeight(),0.001f,1000);
		//glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		
		
		lastFrame = getTime();
		
		world = new World();
		world.generate();
		world.startWorld();
		
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	// GL_BUFFER_DEPTH for 3D || GL_COLOR_BUFFER_BIT for 2D
			glLoadIdentity();
			//glColor3f(0,0,0);
			glRotatef(10, 1, 0,0);
			//glTranslated(0,0,-100);
			
					
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
						
			int delta = getDelta();
			
				
			world.update(delta);
			world.draw();
					
			Display.update();
			Display.sync(60);
			
			
			
		}
		Display.destroy();
		System.exit(0);
	}
	
	
	
	public static void main(String[] args) {
		new Searcher();
	}

}
