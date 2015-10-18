package me.riverhouse.candy.gui.screen.Sugar;

import me.riverhouse.candy.gui.screen.Sugar.blocks.SugarBlock;


public class Connector {

	private SugarBlock to;
	private Object value;
	
	private boolean exitConnector;

	public Connector(SugarBlock to, Object value, boolean exitConnector) {
		this.to = to;
		this.value = value;
		this.exitConnector = exitConnector;
	}

	public SugarBlock getTo() {
		return to;
	}

	public void setTo(SugarBlock to) {
		this.to = to;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isExitConnector() {
		return exitConnector;
	}

	public void setExitConnector(boolean exitConnector) {
		this.exitConnector = exitConnector;
	}
	
}
