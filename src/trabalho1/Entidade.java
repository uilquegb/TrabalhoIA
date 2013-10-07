package trabalho1;

import java.awt.Image;

public class Entidade {

    private Image imagem = null;
    // A = Águar, P = Parede, L = Área Livre
    // 1 = Item 1, 2 = item 2, 3 = item, S = Saída
    private char tipo = 0;
    private int x;
    private int y;
    private Entidade cima = null;
    private Entidade direita = null;
    private Entidade baixo = null;
    private Entidade esquerda = null;
    private Entidade anterior = null;
    private int heuristica;
    private int custoEntidade;
    private int custoAcumulado;

    public Entidade(Image imagem, char tipo) {
        this.imagem = imagem;
        this.tipo = tipo;
    }
    
    public Entidade(char tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        
        switch(tipo){
            case 'A':
                this.custoEntidade = 5;
                break;
            case 'P':
                this.custoEntidade = 987654;
                break;
            case 'L':
            case '1':
            case '2':
            case '3':
            default:
                this.custoEntidade = 1;
                break;
        }
    }
    
    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Entidade getCima(){
        return this.cima;
    }
    public void setCima(Entidade cima){
        if(cima != null && cima != this.cima)
            this.cima = cima;
    }
    
    public Entidade getDireita(){
        return this.direita;
    }
    public void setDireita(Entidade direita){
        if(direita != null && direita != this.direita)
            this.direita = direita;
    }
    
    public Entidade getBaixo(){
        return this.baixo;
    }
    public void setBaixo(Entidade baixo){
        if(baixo != null && baixo != this.baixo)
            this.baixo = baixo;
    }
    
    public Entidade getEsquerda(){
        return this.esquerda;
    }
    public void setEsquerda(Entidade esquerda){
        if(esquerda != null && esquerda != this.esquerda)
            this.esquerda = esquerda;
    }
    
    public Entidade getAnterior(){
        return this.anterior;
    }
    public void setAnterior(Entidade anterior){
        if(anterior != this.anterior)
            this.anterior = anterior;
    }
    
    public int getCustoAcumulado(){
        return this.custoAcumulado + this.custoEntidade;
    }
    public void setCustoAcumulado(int custoAcumulado){
        this.custoAcumulado = custoAcumulado;
    }
    
    public int getHeuristita(){
        return this.heuristica;
    }
    public void setHeuristica(int heuristica){
        this.heuristica = heuristica;
    }
    
    public int getCustoAcumuladoEHeuristica(){
        return this.getCustoAcumulado() + this.heuristica;
    }
}
