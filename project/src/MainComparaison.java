import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.Arrays;

public class MainComparaison {

    public static void main(String[] args){

        // Dossier contenant les graphes que l'on va comparer
        File dossier = new File("./ressources/Graphes");
        File[] liste = dossier.listFiles();
        // Pour les comparaisons, et plus précisément sur le tableau des comparaisons algorithmiques.
        // Nous avons besoin que les graphes soient organisés de sorte à ce que la lecture se fasse
        // Des moins denses aux plus denses. On va donc trier le tableau qui en contient les fichiers
        // Pour ce faire, il faut redéfinir le comparateur utilisé par la méthode Array.sort pour que la comparaison
        // du nom de fichier ne se fasse pas de gauche à droite caractère par caractère mais en fonction
        // du numéro contenu dans le nom du fichier
        Arrays.sort(liste, (fichier1, fichier2) -> {
            String nom1 = fichier1.getName().replaceAll("\\D+", "");
            String nom2 = fichier2.getName().replaceAll("\\D+", "");

            int nb1 = Integer.parseInt(nom1);
            int nb2 = Integer.parseInt(nom2);

            return Integer.compare(nb1, nb2);
        });

        // Graphe fourni en exemple sur Arche
        GrapheListe grapheBoucle = new GrapheListe("./ressources/Graphe_boucle.txt");

        // Pour pouvoir utiliser les méthodes resoudre propre à chaque algorithme
        BellmanFord bF = new BellmanFord();
        Dijkstra d = new Dijkstra();
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("./ressources/hihi.csv"));

            // On écrit la première ligne du fichier pour les en têtes et noms de colonnes
            writer.println("Temps/Algorithme" + ";" + "BellmanFord (point fixe)"  + ";" + "Djikstra");


            // Pour chaque fichier de la liste faire
            for(File fichier : liste) {
                if (fichier.isFile()) { // Vérification que fichier ne soit pas autre chose qu'un fichier
                    System.out.println(fichier.getName());
                    // Création du graphe selon le fichier
                    GrapheListe g = new GrapheListe(fichier.getPath());

                    long debutBellman = System.nanoTime();
                    Valeur bellman = bF.resoudre(g, "1");
                    long finBellman = System.nanoTime();
                    long tempsBellman = finBellman - debutBellman;


                    long debutDijkstra = System.nanoTime();
                    Valeur dijkstra = d.resoudre(g, "1");
                    long finDijkstra = System.nanoTime();
                    long tempsDijkstra = finDijkstra - debutDijkstra;

                    writer.print(fichier.getName() + ";");
                    writer.printf("%.3f;", (double) tempsBellman / 1e6);
                    writer.printf("%.3f;", (double) tempsDijkstra / 1e6);
                    writer.println();

                }
            }
            writer.close();
        } catch(IOException err){
            err.printStackTrace();
        }

    }
}
