import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainDijkstra {
    public static void main(String[] args){




            // Pour accèder à la liste de tous les fichiers du dossier Graphe
            File dos = new File("./ressources/Graphes");
            File[] fichiers = dos.listFiles();
            System.out.println(fichiers[0].getName());


            // Notre liste qui contient les graphes
            ArrayList<Graphe> graphes = new ArrayList<Graphe>();

            // On parcourt notre tableau contenant les fichiers
            // Puis pour chaque fichier on va créer un graphe en utilisant
            // le constructeur de GrapheListe qui crée un graphe à partir d'un nom de fichier

            int i = 0; // pour retrouver le graphe qu'on vient d'ajouter à notre liste de graphes
            // Pour pouvoir appeler la méthode resoudre de Dijkstra
            Dijkstra d = new Dijkstra();
            String depart = "1";
            for(File fich : fichiers){
                if (fich.isFile()) { // Vérifier si le fichier existe bien
                    String nomFich = "./ressources/Graphes/" + fich.getName();
                    graphes.add(new GrapheListe(nomFich));

                    Valeur v = d.resoudre(graphes.get(i), depart);
                    System.out.println("Pour le fichier : " + nomFich + " le chemin le plus court pour les noeuds.");
                    System.out.println(v.toString());

                    i++;
                }
            }


        String nom = "./ressources/fichier.txt";
        GrapheListe gl = new GrapheListe(nom);
        BellmanFord bb = new BellmanFord();
        Valeur v1 = bb.resoudre(gl, "A");


        Dijkstra dj = new Dijkstra();
        Valeur v2 = dj.resoudre(gl, "A");

        System.out.println(v1.toString());
        System.out.println(v2.toString());




    }
}
