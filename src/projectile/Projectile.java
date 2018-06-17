package projectile;

import controleur.Input;
import modele.Entity.Entity;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;

public class Projectile extends Entity {

	Animation anim;
	Input direction;
	int velocity;

	public Projectile(Coordonnee position, Input direction, int speed, Animation animation) {
		super(position);
		if(!direction.isMovement())
			throw new Error("l'Input n'est pas un Input de mouvement");
		velocity = speed;
		anim = animation;
	}

	public void executer() {
		position.setX(position.getX()+(direction.x()*velocity));
		position.setY(position.getY()+(direction.y()*velocity));
		updateAnimation();
	}

	private void updateAnimation() {
		if(direction != null) {
			int[] orientation = direction.directiontoArray();
			orientation[0] = orientation[0]*10 + orientation[1];

			switch (orientation[0]) {
			case 10: case 11: case 9:
				anim.animate(0);
				break;
			case 1:
				anim.animate(1);
				break;
			case -10: case -11: case -9:
				anim.animate(2);
				break;			
			case -1:
				anim.animate(3);
				break;
			}
		}
		anim.nextFrame();
	}
}
