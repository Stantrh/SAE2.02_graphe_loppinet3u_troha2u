import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDijkstra {

    private GrapheListe gL;
    private Dijkstra dijkstra;

    @BeforeEach
    public void setup() {
        // Création d'un graphe
        gL = new GrapheListe();

        gL.ajouterArc("A", "B", 10);
        gL.ajouterArc("A", "C", 15);
        gL.ajouterArc("B", "D", 12);
        gL.ajouterArc("B", "E", 15);
        gL.ajouterArc("C", "E", 10);
        gL.ajouterArc("D", "F", 1);
        gL.ajouterArc("E", "F", 5);

        // Création de l'objet Dijkstra (pour lancer la méthode resoudre ensuite)
        dijkstra = new Dijkstra();
    }

    @Test
    public void testResoudre() {
        // Appel de la méthode
        Valeur valeur = dijkstra.resoudre(gL, "A");

        // Vérification
        assertEquals(0, valeur.getValeur("A"));  // La valeur du noeud de départ est toujours 0
        assertEquals(10, valeur.getValeur("B")); // Distance minimale de A à B
        assertEquals(22, valeur.getValeur("D"));
        assertEquals(15, valeur.getValeur("C"));
        assertEquals(23, valeur.getValeur("F"));
        assertEquals(25, valeur.getValeur("E"));
    }

    @Test
    public void testChemin() {
        // Appel de la méthode
        Valeur valeur = dijkstra.resoudre(gL, "A");

        // Vérification
        assertEquals("[A, B, D, F]", valeur.calculerChemin("F").toString());  // Chemin de A à F
    }

    @Test
    public void testCheminInexistant() {
        // Appel de la méthode
        Valeur valeur = dijkstra.resoudre(gL, "A");

        // Vérification
        assertEquals("[G]",valeur.calculerChemin("G").toString()); // Chemin de F à G (inexistant)
    }
}
