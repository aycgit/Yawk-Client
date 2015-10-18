package me.riverhouse.candy.gui.screen.clickGui;

public class Component extends Interactable {

	private Theme theme;
	private ComponentType type;
	private Component parent;
	private String text;

	public Component(int xPos, int yPos, int width, int height, ComponentType type, Theme theme, Component parent, String text){
		super(xPos, yPos, width, height);
		this.type = type;
		this.theme = theme;
		this.parent = parent;
		this.text = text;
		
	}

	public void render(int x, int y){
		theme.getRenderers().get(type).drawComponent(this, x, y);
	}

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public Theme getTheme(){
		return theme;
	}

	public void setTheme(Theme theme){
		this.theme = theme;
	}

	public ComponentType getType(){
		return type;
	}

	public void setType(ComponentType type){
		this.type = type;
	}

	public Component getParent(){
		return parent;
	}

	public void setParent(Component parent){
		this.parent = parent;
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

}
