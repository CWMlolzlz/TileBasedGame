package cwmlolzlz.searcher;


public class Player extends DynamicProp{

	double aimingDirection = 0;
	
	private float playerSpeed = 0.1f;
			
	String username;
	
	public Player(World world){ //should Player have parenting prop????
		super(world,null,0,0,12,12,Align.CENTRE, true, true, false, null);
		this.setSpeed(playerSpeed);
		setColor(0,0,1,0.8f);
	}
	
	public String getUsername() {
		return username;
	}
}
