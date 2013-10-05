package trabalho1;

import java.awt.Image;

public class Entidade {

    private Image imagem = null;
    private char tipo = 0;
    private int x;
    private int y;

    public Entidade(Image imagem, int x, int y) {
        this.imagem = imagem;
        this.x = x;
        this.y = y;
    }
    
    public Entidade(char tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
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
    
}
