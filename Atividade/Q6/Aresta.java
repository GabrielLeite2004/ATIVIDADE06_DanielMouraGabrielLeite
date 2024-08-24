package Atividade.Q6;

class Aresta implements Comparable<Aresta> {
    String vertice1;
    String vertice2;
    int peso;

    public Aresta(String vertice1, String vertice2, int peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta outraAresta) {
        return this.peso - outraAresta.peso;
    }

    @Override
    public String toString() {
        return "(" + vertice1 + " -- " + vertice2 + " : " + peso + ")";
    }
}
