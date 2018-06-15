package vue;

import javafx.scene.control.Label;

public class NarrationInterface {
	Label label;
	
	public NarrationInterface() {
		
	}
	
	public void setLabel(Label label) {
		this.label = label;
	}
	
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public String getText() {
		return label.getText();
	}

}