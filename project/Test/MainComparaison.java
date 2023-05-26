public class MainComparaison {

    public static void main(String[] args){

        // Graphe fourni en exemple sur Arche
        GrapheListe grapheBoucle = new GrapheListe("./ressources/Graphe_boucle.txt");

        // Graphe dans le dossier Graphe qui est très dense
        GrapheListe grapheDense = new GrapheListe("./ressources/Graphes/Graphe203.txt");
        // Graphe21 Graphe34 Graphe61
        BellmanFord bF = new BellmanFord();
        Dijkstra d = new Dijkstra();



        System.out.println("Méthode BellmanFord");
        double debutBF = System.nanoTime();
        Valeur v1 = bF.resoudre(grapheDense, "1");
        double finBF = System.nanoTime();
        double resultBF = finBF - debutBF;


        System.out.println("Méthode Dijkstra");
        double debutD = System.nanoTime();
        Valeur v2 = d.resoudre(grapheDense, "1");
        double finD = System.nanoTime();
        double resultD = finD - debutD;



        System.out.println("Méthode BellmanFord, graphe résolu en : " + resultBF / 1000000000 + " secondes / " + resultBF / 1000000 + " millisecondes.");

        System.out.println("Méthode Dijkstra, graphe résolu en : " + resultD / 1000000000 + " secondes / " + resultD / 1000000 + " millisecondes.");

        if(resultD < resultBF) {
            System.out.println("Méthode Dijkstra plus rapide");
        }else{
            System.out.println("Méthode BellmanFord plus rapide");
        }
    }
}
