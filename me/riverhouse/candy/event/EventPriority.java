package me.riverhouse.candy.event;

public enum EventPriority {

	LOWEST,
	LOW,
	NORMAL,
	HIGH,
	HIGHEST;

	public int getPriority() {
		switch (this){
		case LOWEST:
			return 0;
		case LOW:
			return 1;   
		case NORMAL:
			return 2;
		case HIGH:
			return 3;
		case HIGHEST:
			return 4;	
		default://NORMAL
			return 2;
		}
	}

}
