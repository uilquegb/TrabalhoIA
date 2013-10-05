package trabalho1;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    public static final int LARGURA = 16;
    public static final int ALTURA = 16;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // desenha o fundo
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // desenha o cenario
        for (Entidade e : Global.getListaEntidades()) {
            switch (e.getTipo()) {
                case 'P': // parede
                    g.setColor(Color.black);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA);
                    g.setColor(Color.GRAY);
                    g.fillRect(e.getX()*LARGURA + 1, e.getY()*ALTURA + 1, LARGURA - 2, ALTURA - 2);
                    break;
                case 'L': //area livre
                    g.setColor(Color.GREEN);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA);
                    break;
                case 'A': // agua
                    g.setColor(Color.BLUE);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA);
                    break;
                case 'S': // saida
                    g.setColor(Color.RED);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                    g.setColor(Color.BLACK);
                    g.drawString("S", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);break;
                case 'R': // item 1
                    g.setColor(Color.MAGENTA);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                    g.setColor(Color.BLACK);
                    g.drawString("R", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);
                    break;                    
                case '1': // item 1
                    g.setColor(Color.YELLOW);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                    g.setColor(Color.BLACK);
                    g.drawString("1", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);
                    break;
                case '2': // item 2
                    g.setColor(Color.YELLOW);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                    g.setColor(Color.BLACK);
                    g.drawString("2", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);                  
                    break;
                 case '3': // item 3
                    g.setColor(Color.YELLOW);
                    g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                    g.setColor(Color.BLACK);
                    g.drawString("3", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);
                    break;                   
                default: // desenha
                    g.drawImage(e.getImagem(), e.getX()*LARGURA, e.getY()*ALTURA, null);
            }
            
        }
    }
    
}
