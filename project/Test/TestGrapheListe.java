import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGrapheListe {


    /**
     * Vérifier qu'avec ajouterArc, les noeuds se créent et s'ajoutent bien
     */
    @Test
    public void testAjouterArc_DeuxListes() {
        // Préparation des éléments nécéssaires au test
        GrapheListe gL = new GrapheListe();

        // On crée nos listes de String et de Noeud qu'on doit avoir en résultat après
        // avoir appelé la méthode ajouterArc
        ArrayList<String> l = new ArrayList<String>();
        // Si on ajoute B avant A, c'est parce que
        // l'ordre des noeuds (alphabétiquemet) n'importe pas
        // Et dans la méthode ajouterArc de GrapheListe
        // la vérification est déjà effectuée sur le noeud de destination

        l.add("A");
        l.add("B");



        // Méthode testée
        gL.ajouterArc("A", "B", 10);

        // Vérification
        assertEquals(gL.listeNoeuds(), l);
    }


    /**
     * L'ajout de deux fois le même arc ne doit pas marcher
     * On ne peut pas avoir 2 arcs reliant A à B
     */
    @Test
    public void testAjouterDeuxMemesArcs() {
        // Préparation des données
        GrapheListe gL = new GrapheListe();
        gL.ajouterArc("A", "B", 10);
        gL.ajouterArc("A", "B", 10);

        // Méthode testée
        String res = gL.toString();

        assertEquals("A -> B(10) \n", res);
    }
    /**
    Dans ce test on ajoute déjà le noeud A dans les deux listes
     * Mais on ajoutera un arc à ce noeud A avec un noeud qui n'existe pas (le noeud b)
     */
    @Test
    public void testAjouterNoeudExistant(){
        // Préparation des données
        Noeud A = new Noeud("A");
        GrapheListe gL = new GrapheListe();
        gL.listeNoeuds().add("A");
        gL.getListeNoeuds().add(A);

        // la liste qu'on doit avoir
        ArrayList<String> l = new ArrayList<String>();
        l.add("A");
        l.add("B");

        // Méthode testée
        gL.ajouterArc("A", "B", 10);

        // Résultat attendu
        assertEquals(l, gL.listeNoeuds());
    }

    @Test
    public void testToString(){
        // Préparation des données

        GrapheListe gL = new GrapheListe();

        gL.ajouterArc("A", "B", 12);
        gL.ajouterArc("C", "A", 19);
        gL.ajouterArc("A", "D", 87);

        gL.ajouterArc("B", "E", 11);

        gL.ajouterArc("D", "B", 23);
        gL.ajouterArc("D", "C", 10);

        gL.ajouterArc("E", "D", 43);

        // Affichage que l'on doit obtenir avec un toString

        String aff = "A -> B(12) D(87) \n" +
                "B -> E(11) \n" +
                "C -> A(19) \n" +
                "D -> B(23) C(10) \n" +
                "E -> D(43) \n";

        // Résultat attendu
        assertEquals(aff, gL.toString());
    }

    @Test
    public void test_toGraphviz(){
        // Préparation des données
        GrapheListe gL = new GrapheListe();

        gL.ajouterArc("A", "B", 12);
        gL.ajouterArc("C", "A", 19);
        gL.ajouterArc("A", "D", 87);

        gL.ajouterArc("B", "E", 11);



        gL.ajouterArc("D", "B", 23);
        gL.ajouterArc("D", "C", 10);

        gL.ajouterArc("E", "D", 43);

        String aff = "digraph G{\n" +
                "A -> B [label = 12]\n" +
                "A -> D [label = 87]\n" +
                "B -> E [label = 11]\n" +
                "C -> A [label = 19]\n" +
                "D -> B [label = 23]\n" +
                "D -> C [label = 10]\n" +
                "E -> D [label = 43]\n" +
                "}";

        assertEquals(aff, gL.toGraphviz());
    }


}
