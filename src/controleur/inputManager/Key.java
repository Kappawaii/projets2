package controleur;

import modele.Coordonnee.Axe;

class Key {
	
	private boolean pressed;
	String bind;
	Axe nom;
	
	/**
	 * Crée une nouvelle Key
	 * @param nom le nom de la Key
	 * @param bind la touche du clavier à lier à cette Key
	 */
	public Key(Axe nom, String bind) {
		this.nom = nom;
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
		if (!Key.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Key other = (Key) obj;
		if (other.nom != null && nom != null) {
			if (nom.equals(other.nom))
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
	protected boolean get() {
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
		return (nom + " '" + bind + "' " + pressed);
	}
}
