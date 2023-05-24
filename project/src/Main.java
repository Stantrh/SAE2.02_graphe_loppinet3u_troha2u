import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création du graphe
        GrapheListe gL = new GrapheListe();

        // Ajout des arcs selon l'exerice 5
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

        // Appliquer l'algorithme du point fixe pour trouver le chemin le plus court
        BellmanFord bF = new BellmanFord();
        // On crée un objet Valeur qui contiendra à la fin, le chemin le plus rapide du graphe (via la méthode BellmanFord)
        Valeur v = bF.resoudre(gL, "A");

        // Puis on peut comparer le résultat avec le résultat de l'exercice papier.
        // On peut constater que pour chaque lettre(Noeud) on trouve bien la même valeur de point fixe
        System.out.println(v.toString());

        // On stocke dans une liste le chemin le plus rapide
        List<String> l = v.calculerChemin("J");
        System.out.println("Le chemin le plus rapide est représenté tel que : ");
        for(String s : l){

            System.out.print(s + " -> ");
        }

    }
}
