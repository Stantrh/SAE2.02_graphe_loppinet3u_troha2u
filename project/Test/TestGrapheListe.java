import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    /**
     * Test pour voir si un ajout d'un arc se fait bien
     */
    @Test
    public void testAjouterArc_AjoutUnique() {
        GrapheListe gL = new GrapheListe();
        gL.ajouterArc("A", "B", 10);
        assertEquals("A -> B(10) \n", gL.toString());
    }

    /**
     * Test pour voir si un ajout d'un arc se fait bien avec la même origine
     */
    @Test
    public void testAjouterArc_MemeOrigineDifferentesDestinations() {
        GrapheListe gL = new GrapheListe();
        gL.ajouterArc("A", "B", 10);
        gL.ajouterArc("A", "C", 15);
        assertEquals("A -> B(10) C(15) \n", gL.toString());
    }

    /**
     * Test pour voir si un ajout d'un arc se fait si il y a la même origine et la même destination (ça doit pas se faire)
     */
    @Test
    public void testAjouterArc_MemeOrigineMemeDestinationDifferentCouts() {
        GrapheListe gL = new GrapheListe();
        gL.ajouterArc("A", "B", 10);
        gL.ajouterArc("A", "B", 15);
        assertEquals("A -> B(10) \n", gL.toString());
    }

    /**
     * Test pour voir si un ajout d'un arc se fait si la destination est inexistante
     */
    @Test
    public void testAjouterArc_DestinationInexistante() {
        GrapheListe gL = new GrapheListe();
        gL.ajouterArc("A", "B", 10);
        assertEquals("A -> B(10) \n", gL.toString());
    }

    /**
     * Test pour voir si uun ajout d'un arc se fait si l'origine est inexistante
     */
    @Test
    public void testAjouterArc_OrigineInexistante() {
        GrapheListe gL = new GrapheListe();
        gL.ajouterArc("A", "B", 10);
        gL.ajouterArc("B", "C", 15);
        assertEquals("A -> B(10) \nB -> C(15) \n", gL.toString());
    }

    /**
     * Test pour voir si l'affichage d'un graphe non vide se fait bien
     */
    @Test
    public void testToString_GrapheNonVide(){
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

    /**
     * Test pour voir l'affichage d'un graphe vide ne provoque pas d'erreur et ne renvoit qu'une chaine vide.
     */
    @Test
    public void testToString_GrapheVide() {
        // Vérifier la représentation sous forme de chaîne d'un graphe vide
        GrapheListe gL = new GrapheListe();

        // Méthode testée
        String result = gL.toString();

        // Vérification
        assertEquals("", result);
    }

    /**
     * Test pour voir si l'affichage d'un graphe non vide au format viz se fait bien
     */
    @Test
    public void testToGraphviz_GrapheNonVide(){
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

    /**
     * Test pour voir l'affichage d'un graphe vide au format viz ne provoque pas d'erreur et ne renvoit que la strcture sans aucun sommet
     */
    @Test
    public void testToGraphviz_GrapheVide() {
        // Vérifier la représentation au format Graphviz d'un graphe vide
        GrapheListe gL = new GrapheListe();

        // Méthode testée
        String result = gL.toGraphviz();

        // Vérification
        assertEquals("digraph G{\n}", result);
    }


}
