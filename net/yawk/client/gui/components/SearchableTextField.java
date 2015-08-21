package net.yawk.client.gui.components;

import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.components.scrolling.FilterableScrollPane;

public class SearchableTextField extends TextField{

	private FilterableScrollPane scrollPane;

	public SearchableTextField(String placeholder, FilterableScrollPane scrollPane) {
		super(placeholder);
		this.scrollPane = scrollPane;
	}

	@Override
	protected void removeLastCharacter() {
		super.removeLastCharacter();
		applyFilter();
	}

	@Override
	protected void addCharacter(char c) {
		super.addCharacter(c);
		applyFilter();
	}

	@Override
	protected void addText(String text) {
		super.addText(text);
		applyFilter();
	}
	
	private void applyFilter(){
		scrollPane.applyFilter(contents);
	}
}
