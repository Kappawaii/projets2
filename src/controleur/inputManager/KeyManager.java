package controleur.inputManager;

import java.util.ArrayList;

import controleur.Input;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class KeyManager {

	private static final Input axenull = null;
	private ArrayList<Key> keystates = new ArrayList<Key>();
	private ArrayList<Input> inputs;

	public KeyManager(Scene scene) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			setKeyState(axenull,key.getCode().toString(), true);
		});
		scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			setKeyState(axenull,key.getCode().toString(), false);
		});
	}

	@Override
	public String toString() {
		String temp = "\n\n\n\n\n\n\n\n\n\n";		
		for (int i = 0; i < keystates.size(); i++) {
			temp += keystates.get(i).toString() + "\n";
		}
		return temp;
	}


	private void setKeyState(Input nom, String bind, boolean state) {
		bind = refactorNom(bind);
		if (isKeyUsed(axenull,bind))
			keystates.get(keystates.indexOf(new Key(nom,bind))).set(state);
		else if (state) {
			System.out.println("unmapped Key pressed :" + bind);
		}
		else {
			System.out.println("unmapped Key released :" + bind);
		}
	}

	/**
	 * ajoute une Key 
	 * @param nom
	 * @param bind
	 * @throws Key already used error si la Key est déjà utilisée
	 */
	public void addKey(Input nom, String bind) {
		bind = refactorNom(bind);
		if (!isKeyUsed(nom, bind)) {
			keystates.add(new Key(nom,bind));
		}
		else throw new Error("Key already used");
	}

	/**
	 * Retourne vrai si une key avec un des deux paramètres égaux existe, sinon retourne faux
	 * @param nom
	 * @param bind
	 */
	public boolean isKeyUsed(Input nom, String bind) {
		bind = refactorNom(bind);	
		Key temp = new Key(nom,bind);
		if (keystates.contains(temp))
			return true;
		return false;
	}

	public void updateInputs() {
		if(inputs != null) {
			inputs.clear();
			for (Key key : keystates) {
				if (key.isPressed()) {

					inputs.add(key.input);
				}
			}
		}
	}
	
	public ArrayList<Input> getInputList(){
		return inputs;
	}

	//	public Axe getMovementInputs(int a) {
	//		Axe inputs = Axe.EMPTY;
	//		inputs.clear();
	//		for (Key key : keystates) {
	//			if (key.isPressed())
	//				inputs.add(key.nom);
	//		}
	//		return inputs;
	//	}

	/**
	 * Retourne vrai si la Key est appuyée, sinon retourne faux
	 * @param key
	 * @throws Key not found error si la Key n'existe pas
	 */
	public boolean getKeyState(String bind) {
		bind = refactorNom(bind);
		if (isKeyUsed(axenull,bind))
			return keystates.get(keystates.indexOf(new Key(axenull,bind))).isPressed();
		else 
			throw new Error("getKeyState : key '" + bind + "' not found");
	}

	/**
	 * Retourne vrai si la Key est appuyée, sinon retourne faux
	 * @param key
	 * @throws Key not found error si la Key n'existe pas
	 */
	public boolean getKeyState(Input nom) {
		if (isKeyUsed(nom,""))
			return keystates.get(keystates.indexOf(new Key(nom,""))).isPressed();
		else 
			throw new Error("getKeyState : key '" + nom + "' not found");
	}

	private String refactorNom(String nom) {
		return nom.toLowerCase();
	}

	public void setInputList(ArrayList<Input> inputs2) {
		inputs = inputs2;
	}
}
