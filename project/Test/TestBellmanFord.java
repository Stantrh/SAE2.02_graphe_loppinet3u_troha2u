import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBellmanFord {

    @Test
    public void testAlgorithmeBellmanFord(){

        // Préparation des données
        GrapheListe gL = new GrapheListe();
        // Ajout des noeuds
        gL.ajouterArc("A", "B", 3);
        gL.ajouterArc("B", "C", 2);
        gL.ajouterArc("F", "C", 1);
        gL.ajouterArc("C", "D", 4);
        gL.ajouterArc("G", "D", 2);
        gL.ajouterArc("D", "E", 1);
        gL.ajouterArc("A", "F",3);
        gL.ajouterArc("C", "G", 1);
        gL.ajouterArc("F", "G", 3);
        gL.ajouterArc("D", "H", 1);
        gL.ajouterArc("G", "H", 4);
        gL.ajouterArc("E", "I", 1);
        gL.ajouterArc("H", "I", 2);
        gL.ajouterArc("E", "J", 4);
        gL.ajouterArc("I", "J", 2);

        BellmanFord bF = new BellmanFord();


        // Méthode testée
        Valeur v = bF.resoudre(gL, "A");

        // Vérification des données selon l'exercice 5 du TD Problèmes de Cheminement
        // On sait que le type TreeMap trie ses clés par ordre naturel, ainsi les noeuds sont triés alphabétiquement
        // selon leur nom. On sait qu'A est au premier indice, B au deuxième etc..



        assertEquals(0, (int)v.getValeur("A"));
        assertEquals(3, (int) v.getValeur("B"));
        assertEquals(4, (int)v.getValeur("C"));
        assertEquals(7, (int)v.getValeur("D"));
        assertEquals(8, (int)v.getValeur("E"));
        assertEquals(3, (int)v.getValeur("F"));
        assertEquals(5, (int)v.getValeur("G"));
        assertEquals(8, (int)v.getValeur("H"));
        assertEquals(9, (int)v.getValeur("I"));
        assertEquals(11, (int)v.getValeur("J"));

    }
}
