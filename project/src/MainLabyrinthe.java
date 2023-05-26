import java.sql.SQLOutput;
import java.util.List;

public class MainLabyrinthe {

    public static void main(String[] args) throws Exception{

        Dijkstra d = new Dijkstra();


        // Méthode genererGraphe() de Labyrinthe // Question 31, le chemin est bien le chemin le plus petit
        System.out.println("METHODE UNIQUEMENT LABYRINTHE ");
        Labyrinthe l = new Labyrinthe("./ressources/labySimple/laby2.txt");
        Graphe g = l.genererGraphe();
        Valeur v = d.resoudre(g, "(1,1)");
        List<String> chemin = v.calculerChemin("(13,12)");
        for(int i = 0; i < chemin.size(); i++){
            System.out.print(chemin.get(i));
            if(i != g.listeNoeuds().size()-1){
                System.out.print(" -> ");
            }
        }

        System.out.println("\n####################################");

        System.out.println("METHODE ADAPTEUR");
        GrapheLabyrinthe gL = new GrapheLabyrinthe(l);
        Valeur v2 = d.resoudre(gL, "(1,1)");
        List<String> chemin2 = v2.calculerChemin("(13,12)");
        for(int i = 0; i < chemin2.size(); i++){
            System.out.print(chemin2.get(i));
            if(i != gL.listeNoeuds().size()-1){
                System.out.print(" -> ");
            }
        }


    }
}
