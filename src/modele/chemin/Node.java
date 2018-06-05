package modele.chemin;

import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;

public class Node {


    Node avant;
    Cellule cord;
    
    
    public Node(Node avant, Cellule cord) {
        this.avant=avant;
        this.cord=cord;
    }
    
    public Node getAvant() {
        return avant;
    }


    public Cellule getCord() {
        return cord;
    }


    
}

