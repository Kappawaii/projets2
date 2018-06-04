package modele.chemin;

import modele.coordonnee.Coordonnee;

public class Node {


    Node avant;
    Coordonnee cord;
    
    
    public Node(Node avant, Coordonnee cord) {
        this.avant=avant;
        this.cord=cord;
    }
    
    public Node getAvant() {
        return avant;
    }


    public Coordonnee getCord() {
        return cord;
    }


    
}

