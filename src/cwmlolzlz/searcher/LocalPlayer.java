package cwmlolzlz.searcher;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class LocalPlayer extends Player {
	
	double movementDirection = 0;
	int health = 10000;
	private boolean fire = false;
	private double aimingAngle = 0;
	
	Prop aimArrow;
	
	Input input = new Input();
	
	public LocalPlayer(World world, double x, double y) {
		super(world);
		aimArrow = new Prop(world,this,getLocalX(),getLocalY(),4,4,Align.CENTRE,true);
		childProps.add(aimArrow);
	}
	
	public void setMovementDirection(float degrees){
		movementDirection = Math.toRadians(degrees);
		this.setDX(Math.cos(movementDirection));
		this.setDY(Math.sin(movementDirection));
	}

	@Override
	public void update(int delta){
		input.checkInput();
		if(health > 10000){
			health = 10000;
		}else if(health <= 0){
			//death
		}
	
		//weapon.update(delta);
			
		this.aimingAngle = input.getAngleToMouse(this.getCentreX(),this.getCentreY());
		
		aimArrow.setLocation(this.getXAlign() + 20*Math.cos(aimingAngle), this.getYAlign() + 20*Math.sin(aimingAngle));
		
		if(input.isShiftDown()){
			this.setSpeedMult(2);
		}else{
			this.setSpeedMult(1);
		}
		
		if(input.mouse(0)){
			//clicked world
			Chunk currentChunk = world.getChunkAt(aimArrow.getGlobalX(),aimArrow.getGlobalY());
			if(currentChunk != null){
				Block clickedBlock = currentChunk.getBlock(aimArrow.getGlobalX(), aimArrow.getGlobalY());
				System.out.println("Found Clicked Chunk");
				if(clickedBlock != null){
					clickedBlock.setBlockType(BlockType.AIR);
					System.out.println("Found clicked block at " + aimArrow.getGlobalX() + ", " + aimArrow.getGlobalY());
				}
			}
				
			
		}
		
//		if(input.key(Keyboard.KEY_LEFT)){
//			Chunk.phase += (double)1;
//			world.generate();
//		}
//		if(input.key(Keyboard.KEY_RIGHT)){
//			Chunk.phase -= (double)1;
//			world.generate();
//		}
		
		if(fire){
			//if(weapon.canFire()){
			//	//World.bullets.add(new Bullet(this, weapon.getDamage(),getCentreX(),getCentreY(),aimingDirection));
				
			//}
			//fire = false;
		}
		
		
		
		super.update(delta);
	}
	
	public void fireNextUpdate(boolean buttonDown){fire = buttonDown;}
	public double getAimingDirection(){return aimingDirection;}
	public void killedByPlayer(Player p){}
	
	class Input {

		private int getMouseX(){
			return Mouse.getX();
		}
		
		private int getMouseY(){
			return Mouse.getY();
		}
		
		private double getAngleToMouse(double x, double y){
			return Math.atan2(Searcher.displayHeight/2 - getMouseY(), - Searcher.displayWidth/2 + getMouseX());	
		}
		
		private boolean isShiftDown(){return key(Keyboard.KEY_LSHIFT);}
		
		private void checkInput(){
			int up = Keyboard.KEY_W;
			int down = Keyboard.KEY_S;
			int left = Keyboard.KEY_A;
			int right = Keyboard.KEY_D;
			
			float direction = -1;
			if(key(up)){
				if(key(left)){
					direction = 1.5f;
				}else if(key(right)){
					direction = 0.5f;
				}else{
					direction = 1;
				}
			}else if(key(down)){
				if(key(left)){
					direction = 2.5f;
				}else if(key(right)){
					direction = 3.5f;
				}else{
					direction = 3;
				}
			}else if(key(left)){
				direction = 2;
			}else if(key(right)){
				direction = 4;
			}
			if(direction != -1){
				setMovementDirection(direction*90);
			}else{
				setDX(0);
				setDY(0);
			}
			
			fireNextUpdate(Mouse.isButtonDown(0));
					
		}
		
		private boolean key(int key){
			return Keyboard.isKeyDown(key);
		}
		
		private boolean mouse(int button){
			return Mouse.isButtonDown(button);
		}
	}
}
