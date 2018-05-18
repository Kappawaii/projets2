package controleur;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import modele.Coordonnee.Axe;

 class KeyManager {
	 
	
	 
	private static final Axe axenull = null;
private ArrayList<Key> keystates = new ArrayList<Key>();
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
	
	
	private void setKeyState(Axe nom, String bind, boolean state) {
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
	public void addKey(Axe nom, String bind) {
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
	public boolean isKeyUsed(Axe nom, String bind) {
		bind = refactorNom(bind);	
		Key temp = new Key(nom,bind);
		if (keystates.contains(temp))
			return true;
		return false;
	}
	
	/**
	 * Retourne vrai si la Key est appuyée, sinon retourne faux
	 * @param key
	 * @throws Key not found error si la Key n'existe pas
	 */
	public boolean getKeyState(Axe nom) {
		if (isKeyUsed(nom,""))
				return keystates.get(keystates.indexOf(new Key(nom,""))).get();
		else 
			throw new Error("getKeyState : key '" + nom + "' not found");
	}
	
	public Axe getMovementInputs() {
		Axe inputs = Axe.EMPTY;
		for (Key key : keystates) {
			if (key.get())
				inputs.add(key.nom);
		}
		return inputs;
		
	}
	/**
	 * Retourne vrai si la Key est appuyée, sinon retourne faux
	 * @param key
	 * @throws Key not found error si la Key n'existe pas
	 */
	public boolean getKeyState(String bind) {
		bind = refactorNom(bind);
		if (isKeyUsed(axenull,bind))
			return keystates.get(keystates.indexOf(new Key(axenull,bind))).get();
		else 
			throw new Error("getKeyState : key '" + bind + "' not found");
	}
	
	private String refactorNom(String nom) {
		return nom.toLowerCase();
	}
}
