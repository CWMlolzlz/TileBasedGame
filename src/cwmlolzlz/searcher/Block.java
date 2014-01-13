package cwmlolzlz.searcher;

public class Block extends Prop{

	public static int blockSize = 16;
	private BlockType blockType;
	
	public Block(World world,Prop parent,double x, double y, BlockType blockType, double noiseColor) {
		super(world,parent,x, y, blockSize, blockSize, !blockType.isSameAs(BlockType.AIR),!blockType.isSameAs(BlockType.AIR), false);
		this.setColor(blockType.getBlockType(),0,0,1);
		this.blockType = blockType;		
	}
	
	public BlockType getBlockType(){
		return this.blockType;
	}
	
	public void setBlockType(BlockType blockType){
		this.blockType = blockType;
		this.setCollidable(this.blockType.isCollidableBlockType());
		this.setDrawable(this.blockType.isDrawableBlockType());
		this.setColor(blockType.getBlockType(), 0,0, 1);
	}
	public void setBlockType(int blockTypeID){
		this.setBlockType(new BlockType(blockTypeID));
	}

}