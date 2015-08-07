package net.yawk.client.gui.maps;

public class ChunkData {
	
	private int x, z;
	
	public ChunkData(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public boolean containsPoint(int xPos, int zPos){
		return xPos > x && xPos <= x+16 && zPos > z && zPos <= z+16;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ChunkData){
			ChunkData chunk = (ChunkData)obj;
			return chunk.getX() == this.x && chunk.getZ() == this.z;
		}else{
			return false;
		}
	}
}
