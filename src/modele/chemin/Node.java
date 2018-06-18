package modele.chemin;

import modele.cellule.Cellule;

public class Node {


    Node avant;
    Cellule cord;
    boolean dejaVisite;
    
    
    public Node(Node avant, Cellule cord) {
        this.avant=avant;
        this.cord=cord;
        this.dejaVisite = false;
    }
    
    
    public Node getAvant() {
        return avant;
    }


    public Cellule getCord() {
        return cord;
    }
    
    public void setVisite() {
    	this.dejaVisite = true;
    }
    
    public boolean getDejaVisite() {
    	return this.dejaVisite;
    }
    
    public boolean equals(Node other) {
    	if(this.cord.getPosition()==other.cord.getPosition()) {
    		return true;
    	}
    	return false;
    }


    
}
