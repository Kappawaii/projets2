package modele.chemin;

import modele.cellule.Cellule;

public class Node {


    Node avant;
    Cellule cord;
    boolean dejaVisité;
    
    
    public Node(Node avant, Cellule cord) {
        this.avant=avant;
        this.cord=cord;
        this.dejaVisité = false;
    }
    
    
    public Node getAvant() {
        return avant;
    }


    public Cellule getCord() {
        return cord;
    }
    
    public void setVisite() {
    	this.dejaVisité = true;
    }
    
    public boolean getDejaVisité() {
    	return this.dejaVisité;
    }
    
    public boolean equals(Node other) {
    	if(this.cord.getPos()==other.cord.getPos()) {
    		return true;
    	}
    	return false;
    }


    
}
