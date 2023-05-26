import java.util.List;

public class MainLabyrinthe {

    public static void main(String[] args) throws Exception{

        Labyrinthe l = new Labyrinthe("./ressources/labySimple/laby2.txt");
        Graphe g = l.genererGraphe();
        Dijkstra d = new Dijkstra();
        Valeur v = d.resoudre(g, "(1,1)");
        System.out.println(v.toString());
        List<String> chemin = v.calculerChemin("(17,12)");
        for(String s : chemin){
            System.out.print(s + " -> ");
        }
    }
}
