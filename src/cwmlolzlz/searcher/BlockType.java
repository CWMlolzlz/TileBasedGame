package cwmlolzlz.searcher;

public class BlockType {
	
	public final static int AIR = 0;
	public final static int WALL = 100;
	
	private int blockTypeVal;
	private boolean collidable, drawable;
	
	public BlockType(int blockTypeVal){
		this.blockTypeVal = blockTypeVal;
		this.collidable = this.blockTypeVal >= 100;
		this.drawable = this.blockTypeVal >= 100;
	}
	
	public boolean isSameAs(BlockType b){
		return b.getBlockType() == this.getBlockType();
	}
	
	public boolean isSameAs(int i){
		return i == this.getBlockType();
	}
	
	public int getBlockType(){
		return this.blockTypeVal;
	}
	
	public boolean isCollidableBlockType(){return collidable;}
	public boolean isDrawableBlockType(){return drawable;}
	
	public static BlockType generateBlockType(double noise){
		int blockVal = AIR;
		if(noise < .20){
			blockVal = WALL;
		}
		return new BlockType(blockVal);
	}
	
	
}
