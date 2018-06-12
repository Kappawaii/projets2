package modele.Event;

import modele.Modele;

public class ChangeTextEvent extends Event {
	
	String text;
	
	public ChangeTextEvent(Modele modele, String text) {
		super(modele);
		this.text = text;
	}

	@Override
	public void execute() {
		//modele.setText(text);

	}

}
