package controleur;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

 class KeyManager {

	private ArrayList<Key> keystates = new ArrayList<Key>();
	
	public KeyManager(Scene scene) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			setKeyState("",key.getCode().toString(), true);
		});
		scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			setKeyState("",key.getCode().toString(), false);
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
	
	
	private void setKeyState(String nom, String bind, boolean state) {
		nom = refactorNom(nom);
		bind = refactorNom(bind);
		if (isKeyUsed(nom,bind))
			keystates.get(keystates.indexOf(new Key(nom,bind))).set(state);
		else if (state) {
			System.out.println("Debug : Key not used pressed :" + bind);
		}
		else {
			System.out.println("Debug : Key not used released :" + bind);
		}
			
	}
	
	/**
	 * ajoute une Key 
	 * @param nom
	 * @param bind
	 * @throws Key already used error si la Key est déjà utilisée
	 */
	public void addKey(String nom, String bind) {
		nom = refactorNom(nom);
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
	public boolean isKeyUsed(String nom, String bind) {
		nom = refactorNom(nom);
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
	public boolean getKeyState(String key) {
		key = refactorNom(key);
		if (isKeyUsed(key,""))
				return keystates.get(keystates.indexOf(new Key(key,""))).get();
		else 
			throw new Error("getKeyState : key '" + key + "' not found");
	}
	
	private String refactorNom(String nom) {
		return nom.toLowerCase();
	}
}