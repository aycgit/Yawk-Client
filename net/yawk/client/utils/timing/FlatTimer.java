package net.yawk.client.utils.timing;

public class FlatTimer extends MillisecondTimer{
	
	private int delay;
	
	public FlatTimer(){
		this.delay = 1000;
	}
	
	public FlatTimer(int delay){
		this.delay = 1000;
	}
	
	public MillisecondTimer setDelay(int delay) {
		this.delay = delay;
		return this;
	}

	@Override
	public int getDelay() {
		return delay;
	}
}
