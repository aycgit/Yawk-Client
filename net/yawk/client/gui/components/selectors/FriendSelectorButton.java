package net.yawk.client.gui.components.selectors;

public class FriendSelectorButton extends SelectorButton{

	public FriendSelectorButton(String text, SelectorSystem system) {
		super(text, system);
	}
	
	@Override
	public String getText() {
		
		if(isFriend(text)){
			return text + " (Friend)";
		}else if(isEnemy(text)){
			return text + " (Enemy)";
		}else{
			return text;
		}
	}
	
	private boolean isFriend(String pl){
		return false;
	}
	
	private boolean isEnemy(String pl){
		return false;
	}
}
