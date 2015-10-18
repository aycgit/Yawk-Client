package me.riverhouse.candy.gui.screen.clickGui.parts;

import me.riverhouse.candy.Candy;
import me.riverhouse.candy.gui.screen.clickGui.ClickGui;
import me.riverhouse.candy.gui.screen.clickGui.Component;
import me.riverhouse.candy.gui.screen.clickGui.ComponentType;
import me.riverhouse.candy.gui.screen.clickGui.Theme;

import org.lwjgl.input.Mouse;

public class Slider extends Component {

	public double min, max, value;
	public boolean dragging = false;
	public double percent = 0;

	public Slider(double min, double max, double value, Theme theme, Component parent, String text){
		super(0, 0, 100, 20, ComponentType.SLIDER, theme, parent, text);

		this.min = min;
		this.max = max;
		this.value = value;

		this.percent = value / (max - min);
	}

	public void onMousePress(int x, int y, int button){
		x -= this.getX();
		int x1 = (int) (getArea().getWidth());

		percent = (double) x / (double) x1;
		value = this.round(((max - min) * percent) + min, 2);

		dragging = true;
	}

	public void onMouseRelease(int x, int y, int button){
		dragging = false;
	}

	@Override
	public void onUpdate() {
		int[] mouse = Candy.getCandy().getClickGui().mouse;

		if(!this.isMouseOver(mouse[0], mouse[1])){
			this.dragging = false;
		}

		if(Mouse.isButtonDown(0) && this.isMouseOver(mouse[0], mouse[1])) this.dragging = true;
	}

	public void onMouseDrag(int x, int y){
		if(dragging){
			x -= this.getX();

			int x1 = (int) (getArea().getWidth());

			percent = (double) x / (double) x1;
			value = this.round(((max - min) * percent) + min, 2);
		}
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	private double round(double valueToRound, int numberOfDecimalPlaces)
	{
		double multipicationFactor = Math.pow(10, numberOfDecimalPlaces);
		double interestedInZeroDPs = valueToRound * multipicationFactor;
		return Math.round(interestedInZeroDPs) / multipicationFactor;
	}

}
