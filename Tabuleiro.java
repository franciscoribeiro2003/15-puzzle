import java.util.*;

class Tabuleiro{
    int[] tabuleiro; //representacao do tabuleiro
    int posEspaco; // posicao do espaco vazio

    //construtor
    Tabuleiro(){
        tabuleiro = new int[16];
    }

    //le o tabuleiro
    public void read(Scanner in){
        for(int i = 0; i < 16; i++) tabuleiro[i] = in.nextInt();
        posEspaco = findIndex(tabuleiro,0);
    }

    //imprime o tabuleiro
    public String toString() {
        String res = "";
        for(int i = 0; i < 16; i++) {
            if(i%4 == 0) {
                if (i==0) res = "\n";
                else res += "|\n"; res += "+---------------+\n";}
            if (tabuleiro[i]<10){res += "|"+ tabuleiro[i] + "  ";}
            else{res += "|"+tabuleiro[i] + " ";}
        }
        res+="|"+"\n+---------------+";
        res += "\n";
        return res;
    }

    //metodo que retorna uma copia do tabuleiro atual
    public Tabuleiro clone(){
        Tabuleiro novo = new Tabuleiro();
        for (int i = 0; i < 16; i++) novo.tabuleiro[i] = tabuleiro[i];
        novo.posEspaco = posEspaco;
        return novo;
    }

    // move o espaço vazio para cima
    public boolean moveUp() {
        if (posEspaco >= 4) {
            int temp = tabuleiro[posEspaco-4];
            tabuleiro[posEspaco-4] = 0;
            tabuleiro[posEspaco] = temp;
            posEspaco -= 4;
            return true;
        }
        return false;
    }

    // move o espaço vazio para baixo
    public boolean moveDown() {
        if (posEspaco <= 11) {
            int temp = tabuleiro[posEspaco+4];
            tabuleiro[posEspaco+4] = 0;
            tabuleiro[posEspaco] = temp;
            posEspaco += 4;
            return true;
        }
        return false;
    }

    // move o espaço vazio para a esquerda
    public boolean moveLeft() {
        if (posEspaco % 4 != 0) {
            int temp = tabuleiro[posEspaco-1];
            tabuleiro[posEspaco-1] = 0;
            tabuleiro[posEspaco] = temp;
            posEspaco--;
            return true;
        }
        return false;
    }

    // move o espaço vazio para a direita
    public boolean moveRight() {
        if (posEspaco % 4 != 3) {
            int temp = tabuleiro[posEspaco+1];
            tabuleiro[posEspaco+1] = 0;
            tabuleiro[posEspaco] = temp;
            posEspaco++;
            return true;
        }
        return false;
    }

    // verifica se o tabuleiro atual é igual ao tabuleiro do parametro
    public boolean equals(Tabuleiro t) {
        if (t == null) return false;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] != t.tabuleiro[i]) return false;
        }
        return true;
    }

    // compara a distancia do 0 da sua posicao com o 0 de outro tabuleiro
    public int compareDis(Tabuleiro t,int num) {
        int pos0 = 0;
        int pos0t = 0;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] == num) pos0 = i;
            if (t.tabuleiro[i] == num) pos0t = i;
        }
        int dist = Math.abs(pos0/4 - pos0t/4) + Math.abs(pos0%4 - pos0t%4);
        return dist;
    }

    //verifica se o tabuleiro tem solucao a comparar com outro tabuleiro
    public boolean hasSolution(Tabuleiro t) {
        int[] temp=this.clone().tabuleiro;
        int inv = 0;
        int dist_brancos = compareDis(t,0);
        for (int i = 0; i < 16; i++) {
            if(temp[i] != t.tabuleiro[i]) {
                for(int j = findIndex(temp, t.tabuleiro[i]); j > i; j--) {
                    int temporario = temp[j - 1];
                    temp[j - 1] = temp[j];
                    temp[j] = temporario;
                    inv++;
                }
        
            }
        }
        if (inv%2 == dist_brancos%2) return true;
        return false;
    }

    //contar as peças fora de posicao
    public int countMisplaced(Tabuleiro t) {
        int count = 0;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] != t.tabuleiro[i]) count++;
        }
        return count;
    }

    //contar a distancia de manhattan
    public int countManhattan(Tabuleiro t) {
        int count = 0;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] != t.tabuleiro[i]) {
                int pos = findIndex(t.tabuleiro, tabuleiro[i]);
                count += Math.abs(pos/4 - i/4) + Math.abs(pos%4 - i%4);
            }
        }
        return count;
    }

    //encontra o indice da lista de um numero num tabuleiro
    public int findIndex(int[] t,int num){
        for (int i = 0; i < 16; i++) {
            if (t[i] == num) return i;
        }
        return -1;
    }

    // para hashing
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.tabuleiro);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tabuleiro)) {
            return false;
        }
        Tabuleiro other = (Tabuleiro) obj;
        return Arrays.equals(this.tabuleiro, other.tabuleiro);
    }

}