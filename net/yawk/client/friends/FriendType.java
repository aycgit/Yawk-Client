package net.yawk.client.friends;

public enum FriendType {
	
	FRIEND("Friend"), ENEMY("Enemy"), TEAM_MEMBER("Team Member");
	
	private String name;
	
	private FriendType(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
