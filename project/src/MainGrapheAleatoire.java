import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Cette classe permet de comparer les deux algorithmes (BellmanFord et Dijkstra) sur des graphes fortement connectés
 * puisqu'ils sont générés aléatoirement grâce à la méthode genererGraphe(int taille) de GrapheListe
 * Cela donne un fichier csv pour que Power Bi puisse l'intepreter. A partir de ça, nous avons pu créer des visuels Power BI
 * Pour illustrer les différences entre ces deux algorithmes.
 */
public class MainGrapheAleatoire {
    public static void main(String[] args){

        // Question 27

        try{
            BellmanFord b = new BellmanFord();
            Dijkstra d = new Dijkstra();

            PrintWriter writer = new PrintWriter(new FileWriter("./ressources/comparaisonsGraphesGeneres.csv"));
            writer.println("Nombre de noeuds par Graphe" + ";" + "BellmanFord (point fixe)"  + ";" + "Djikstra");

            for(int i = 10; i <= 900; i+=10){

                // i va définir le nombre de noeuds pour le Graphe qu'on crée à chaque itération
                Graphe g = new GrapheListe(i);

                System.out.println(i);
                long debutBellman = System.nanoTime();
                Valeur bellman = b.resoudre(g, "1");
                long finBellman = System.nanoTime();
                long tempsBellman = finBellman - debutBellman;


                long debutDijkstra = System.nanoTime();
                Valeur dijkstra = d.resoudre(g, "1");
                long finDijkstra = System.nanoTime();
                long tempsDijkstra = finDijkstra - debutDijkstra;


                writer.print(g.listeNoeuds().size() + ";"); // Pour pouvoir comparer les algorithmes en fonction du nombre de noeuds
                writer.printf("%.3f;", (double) tempsBellman / 1e6);
                writer.printf("%.3f;", (double) tempsDijkstra / 1e6);
                writer.println();
            }
            writer.close();
        }catch(IOException err){
            err.printStackTrace();
        }

    }
}
