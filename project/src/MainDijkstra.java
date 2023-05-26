import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainDijkstra {
    public static void main(String[] args){




            // Pour accèder à la liste de tous les fichiers du dossier Graphes
            File dir = new File("./ressources/Graphes");
            File[] liste = dir.listFiles();

            Dijkstra d = new Dijkstra();

            for(File fichier : liste){
                if (fichier.isFile()) {
                    System.out.println(fichier.getName() + " : ");

                    // On crée un graphe à partir du nom du fichier courant
                    GrapheListe g = new GrapheListe(fichier.getPath());

                    // Cette chaine de caractère permet d'isoler le nombre du nom du fichier
                    // Ex : Graphe21.txt, cela isole le 21 du nom du fichier
                    String numberStr = fichier.getName().replaceAll("\\D+", "");

                    // Puis, si le nombre contenu dans le nom du fichier contient au moins deux chiffres
                    // Alors le chiffre des unités est remplacé par 0 (On a remarqué que dans les fichiers
                    // Graphe21.txt, il y a 20 graphes, pareil dans Graphe22 etc...

                    if(numberStr.length() > 1){
                        numberStr = numberStr.substring(0, numberStr.length() -1) + 0;
                    }else{
                        numberStr = numberStr + 0;
                    }
                    // On convertit la chaine en nombre
                    int number = Integer.parseInt(numberStr);


                    // Puis le noeud de départ et d'arrivée sont choisis aléatoirement entre les noeuds qui existent du graphe.
                    // Bien évidemment on exclu le fait qu'un des noeuds soit le noeud 0, d'où le + 1 à la fin
                    // La méthode nextInt permet de définir des bornes tandis que Math.random() aurait pu produire 0
                    Random random = new Random();
                    int depart = random.nextInt(number) + 1;
                    int fin = random.nextInt(number) + 1;

                    while(fin == depart){ // S'assurer que fin et depart ne soient pas le même noeud
                        depart = random.nextInt(number) + 1;
                    }

                    // Puis on les convertit en string
                    String noeudDepart = "" + depart;
                    String noeudFin = "" + fin;

                    System.out.print("Depart : " + noeudDepart + " Fin : " + noeudFin + "\n");

                    // Calcul des chemins les plus courts pour des noeuds donnés
                    Valeur v = d.resoudre(g, noeudDepart);


                    List<String> chemin = v.calculerChemin(noeudFin);
                    // Puis on affiche le chemin obtenu
                    for(int k = 0; k < chemin.size(); k++){
                        // Si c'est le dernier noeud on ne rajoute pas de flêche à la fin
                        System.out.print(chemin.get(k));
                        if(k != chemin.size() -1){
                            System.out.print(" -> ");
                        }
                    }
                    System.out.println("\n");
                    // Pour les noeuds donnés, choisir un nombre aléatoire entre le premier et le dernier noeud du fichier
                    // Pour avoir le nombre de noeuds du fichier, il faut pouvoir extraite le nombre du fichier

                }
            }

            /*
            // Notre liste qui contient les graphes
            ArrayList<Graphe> graphes = new ArrayList<Graphe>();

            // On parcourt notre tableau contenant les fichiers
            // Puis pour chaque fichier on va créer un graphe en utilisant
            // le constructeur de GrapheListe qui crée un graphe à partir d'un nom de fichier







        Dijkstra d = new Dijkstra();


        String nom = "./ressources/fichier.txt";
        GrapheListe gl = new GrapheListe(nom);
        BellmanFord bb = new BellmanFord();
        Valeur v1 = bb.resoudre(gl, "A");


        Dijkstra dj = new Dijkstra();
        Valeur v2 = dj.resoudre(gl, "A");

        System.out.println(v1.toString());
        System.out.println(v2.toString());

        */


    }
}
