public class Main {
    public static void main(String[] args) {
        // Création du graphe
        GrapheListe gL = new GrapheListe();
        gL.ajouterArc("A", "B", 12);
        gL.ajouterArc("C", "A", 19);
        gL.ajouterArc("A", "D", 87);

        gL.ajouterArc("B", "E", 11);



        gL.ajouterArc("D", "B", 23);
        gL.ajouterArc("D", "C", 10);

        gL.ajouterArc("E", "D", 43);

        // Appliquer l'algorithme du point fixe pour trouver le chemin le plus court
        BellmanFord bF = new BellmanFord();
        Valeur v = bF.resoudre(gL, "B");

        System.out.println(v.toString());

    }
}
