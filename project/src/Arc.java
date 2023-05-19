public class Arc {
    /*
    Attribut correspondant au nom du noeud de destination de l'arc
     */
    private String dest;

    /*
    Cout/Poids de l'arc
     */
    private double cout;

    /*
    Constructeur d'un objet Arc à partir d'un nom et d'un réel
     */
    public Arc(String n, double c){
        if(n == null){
            throw new IllegalArgumentException("Le nom ne doit pas être nul");
        }else if(c <= 0){
            throw new IllegalArgumentException("Le cout de l'arc doit être strictement positif");
        }else{
            this.dest = n;
            this.cout = c;
        }
    }
}
