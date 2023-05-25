public class MainComparaison {

    public static void main(String[] args){

        GrapheListe gL = new GrapheListe("./ressources/fichier.txt");
        BellmanFord bF = new BellmanFord();
        Dijkstra d = new Dijkstra();



        System.out.println("Méthode BellmanFord");
        long debutBF = System.nanoTime();
        Valeur v1 = bF.resoudre(gL, "A");
        long finBF = System.nanoTime();
        long resultBF = finBF - debutBF;

        System.out.println("Résolu en : " + finBF / 1000000000 + " secondes / " + finBF / 1000000 + " millisecondes.");


        System.out.println("Méthode Dijkstra");
        long debutD = System.nanoTime();
        Valeur v2 = d.resoudre(gL, "A");
        long finD = System.nanoTime();
        long resultD = finD - debutD;

        System.out.println("Résolu en : " + finD / 1000000000 + " secondes / " + finD / 1000000 + " millisecondes.");

    }
}
