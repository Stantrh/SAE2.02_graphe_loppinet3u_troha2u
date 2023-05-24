/**
 * Classe représentant la relation entre un noeud et le noeud auquel il est relié (adjacent), via un cout (valeur)
 */
public class Arc {
    /**
    Nom du noeud de destination de l'arc
     */
    private String dest;

    /**
    Cout/Poids de l'arc (selon le contexte)
     */
    private double cout;

    /**
    Construit un objet Arc à partir d'un nom et d'un réel
    Vérifie que le nom du noeud de destination ne soit pas null et le cout bien strictement
    positif
     @param d nom du noeud de destination
     @param c cout reliant les deux noeuds
     */
    public Arc(String d, double c){
        if(d == null){
            throw new IllegalArgumentException("Le nom ne doit pas être nul");
        }else if(c <= 0){
            throw new IllegalArgumentException("Le cout de l'arc doit être strictement positif");
        }else{
            this.dest = d;
            this.cout = c;
        }
    }

    /**
     * Renvoie le noeud de destination de l'arc
     * @return le noeud de destination
     */
    public String getDest(){
        return this.dest;
    }

    /**
     * Renvoie le cout entre le noeud de départ et son noeud adjacent
     * @return le cout reliant les deux noeuds
     */
    public double getCout(){
        return this.cout;
    }
}
