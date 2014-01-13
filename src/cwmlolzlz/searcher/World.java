package cwmlolzlz.searcher;

import static org.lwjgl.opengl.GL11.glTranslated;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;

public class World {

	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	public ArrayList<InfoEntity> infoents = new ArrayList<InfoEntity>();
	public ArrayList<Prop> props = new ArrayList<Prop>();
	public ArrayList<Player> players = new ArrayList<Player>();
	//public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public ArrayList<Entity> disposed = new ArrayList<Entity>();
	
	public ArrayList<String> playerstoadd = new ArrayList<String>(); //makes creating players thread-safe over creating players from seperate thread
		
	//public static ArrayList<GUIElement> gui = new ArrayList<GUIElement>();
	public LocalPlayer localPlayer = new LocalPlayer(this,0,0);
	Camera camera = new Camera(this,0,0);
	static Random random = new Random();
	
	public void startWorld(){
		
		//World.addSelf("Connor");
		
	}
	
	public void draw(){
		//glTranslated(-camera.getX()+Display.getWidth()/2, -camera.getY() + Display.getHeight()/2,-1000);
		glTranslated(-camera.getX(),-camera.getY(),-100);
		
						
		for(Prop prop : props){
			prop.draw(0,0);
		}
		
		for(Chunk c : chunks){
			c.draw(0,0);
		}
		
		localPlayer.draw(0, 0);
		
		/*
		for(Player p : players){
			p.draw(0,0);
		}
		
		for(Bullet b : bullets){
			b.draw(0,0);
		}
		
		glLoadIdentity();
		for(GUIElement guie : gui){
			guie.draw(0,0);
		}
		*/
		
	}
	
	public ArrayList<Prop> getCollidableProps(){
		ArrayList<Prop> props = new ArrayList<Prop>();
		for(Chunk c : chunks){
			for(Prop p : c.getChildProps()){
				if(p.collidable){
					props.add(p);
				}
			}
		}
		return props;
	}
	
	public Chunk getChuckAt(int x, int y){
		for(Chunk c : chunks){
			int[] chunkCoord = c.getChunkCoordinates();
			if(chunkCoord[0] == x && chunkCoord[1] == y){
				return c;
			}
		}
		return null;
	}
	
	
	public void addProp(Prop prop){
		if(prop instanceof Player){
			players.add((Player)prop);
		}else{
			props.add(prop);
		}
	}
	
	public void addInfoEntity(InfoEntity info){
		infoents.add(info);
	}
	
	public void spawnPlayer(Player p){
		Spawn s = getRandomSpawn();
		p.setLocation(s.getX(), s.getY());
	}
	
	public void update(int delta){
		localPlayer.update(delta);
		camera.setLocation(localPlayer.getCoordinate());
		
		//if camera is getting close to world chunk boundary
		
		//get world limits 
		// 		or
		//get edge chunk coordinates
		
		double[] worldLimits = getWorldLimits(camera.getX(),camera.getY());
		
		//get upper left corner of screen
			
		
		
	}
	
	public double[] getWorldLimits(double centreX, double centreY){
		
		double leftLimit = 0;
		double rightLimit = 0;
		double upperLimit = 0;
		double lowerLimit = 0;
		for(Chunk c : chunks){ //check all chunks for their coordinates
			double chunkX = c.getX();
			double chunkY = c.getY();
			double chunkWidth = c.getWidth();
			double chunkHeight = c.getHeight();
			
			//########################need to check if it isnt actually reducing limit from chunks further out
			
			//checking left side
			if(centreX - Searcher.displayWidth/2 < chunkX + chunkWidth){
				leftLimit = chunkX;
			}else if(centreX + Searcher.displayWidth/2 > chunkX){ // checking right side
				rightLimit = chunkX + chunkWidth;
			}
			
			//checking top side
			if(centreY - Searcher.displayHeight/2 < chunkY + chunkHeight){
				upperLimit = chunkY;
			}else if(centreY + Searcher.displayHeight/2 > chunkY){ // checking right side
				lowerLimit = chunkY + chunkHeight;
			}
			
		}
		return new double[]{leftLimit,rightLimit,upperLimit,lowerLimit};
	}

	private Spawn getRandomSpawn(){
		ArrayList<Spawn> spawns = new ArrayList<Spawn>();
		for(InfoEntity ient : infoents){
			if(ient instanceof Spawn){
				spawns.add((Spawn) ient);
			}
		}
		int sidx;
		if(spawns.size() == 1){
			sidx = 0;
		}else{
			sidx = random.nextInt(spawns.size());
		}
		return spawns.get(sidx);
	}

	public Chunk getChunkAt(double x, double y){
		for(Chunk c : chunks){
			if(Intersect.pointInRectangle(x, y, c.getGlobalShape())){
				return c;
			}
		}
		return null;
	}
	
		
	public void removeProp(Prop prop) {
		props.remove(prop);
	}
	
	public void addNewPlayer(Player p) {
		players.add(p);
		spawnPlayer(p);
	}

	public void dispose(Entity obj){
		if(obj instanceof Prop){
			props.remove((Prop)obj);
		}else if(obj instanceof Player){
			players.remove((Player)obj);
		}
	}
	
	public void generate() {
		this.chunks.clear();
		this.chunks.add(new Chunk(this,null,0,0,2));
		this.chunks.add(new Chunk(this,null,1,1,2));
		this.chunks.add(new Chunk(this,null,1,0,2));
		this.chunks.add(new Chunk(this,null,0,1,2));
	}
}