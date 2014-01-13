package cwmlolzlz.searcher;


public class Chunk extends Prop {

	int chunkSeed;
	
	private static final int blockSize = Block.blockSize;
	private static final int blocksInChunk = 16;
	
	public static int phase = 2;
	
	int chunkX, chunkY;
	
	public Chunk(World world,Prop parent,int chunkX, int chunkY, int seed){
		super(world,parent,chunkX*blockSize*blocksInChunk,chunkY*blockSize*blocksInChunk,blocksInChunk*blockSize,blocksInChunk*blockSize,false);
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		this.chunkSeed = seed;
		
		generateChunk();
	}
	
	public void generateChunk(){
		for(int row = 0; row < blocksInChunk; row++){
			for(int col = 0; col < blocksInChunk; col++){
				double noise = cleanNoise(row + chunkX*blockSize, col + chunkY * blockSize);
				//System.out.println(noise);
				BlockType blockType = BlockType.generateBlockType(noise);
				this.childProps.add(new Block(this.world,this,row*blockSize, col*blockSize, blockType,noise));
			}
		}
		System.out.println("Generated Chunk");
	};
				
	private double psuedoNoise(int blockX, int blockY){
		
		double x = blockX/phase;
		double y = blockY/phase;
		
		return (Math.sin(x*y + chunkSeed) * Math.cos(y*x + chunkSeed)) * (Math.sin(x + chunkSeed) + Math.cos(y + chunkSeed)) / (Math.cos(y+x) + Math.sin(y*x));
	}
	
	private double cleanNoise(int x, int y){
		double cornerBlocks = (psuedoNoise(x-1,y-1) + psuedoNoise(x+1,y-1) + psuedoNoise(x-1,y+1) + psuedoNoise(x+1,y+1))/8;
		double adjBlocks = (psuedoNoise(x-1,y) + psuedoNoise(x+1,y) + psuedoNoise(x,y+1) + psuedoNoise(x,y-1))/4;
		return cornerBlocks + 
				adjBlocks + 
				psuedoNoise(x,y)/2;
	}
	
	public Block getBlock(double x, double y){
		Prop block = this.getPropAt(x, y);
		if(block instanceof Block){
			return (Block) block;
		}
		return null;
	}
	
	public int[] getChunkCoordinates(){
		return new int[]{chunkX,chunkY};
	}
}
