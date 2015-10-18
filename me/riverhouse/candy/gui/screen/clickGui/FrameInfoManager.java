package me.riverhouse.candy.gui.screen.clickGui;

import java.util.ArrayList;

public class FrameInfoManager {

	private ArrayList<FrameInfo> info = new ArrayList<FrameInfo>();
	
	
	public ArrayList<FrameInfo> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<FrameInfo> info) {
		this.info = info;
	}
	
	public boolean hasInfo(FrameInfo f){
		return info.contains(f);
	}

}
