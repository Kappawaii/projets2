package controleur.inputManager;

import controleur.Input;

class Key {

	private boolean pressed;
	String bind;
	Input input;

	/**
	 * Crée une nouvelle Key
	 * @param input le nom de la Key
	 * @param bind la touche du clavier à lier à cette Key
	 */
	public Key(Input input, String bind) {
		this.input = input;
		this.bind = bind;
		pressed = false;
	}

	/**
	 * Retourne vrai si le bind ou le nom est le même que le bind ou le nom de obj
	 * Cette fonction doit uniquement être utilisée pour vérifier qu'il n'y à pas de doublon dans les Key, pas pour vérifier une stricte égalité entre les Keys.
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final Key other = (Key) obj;
		if (other.input != null && input != null) {
			if (input.equals(other.input))
				return true;
		}
		if (other.bind != null && bind != null)
			if (bind.equals(other.bind))		
				return true;
		return false;
	}

	/**
	 * Retourne l'état (pressé ou non) de la Key
	 */
	protected boolean isPressed() {
		return pressed;
	}

	/**
	 * Assigne la valeur à la Key
	 * @param value : valeur à assigner à la Key
	 */
	protected void set(boolean value) {
		pressed = value;
	}

	/**
	 * Retourne un résumé de la clé
	 */
	@Override
	public String toString() {
		return (input + " '" + bind + "' " + pressed);
	}
}
