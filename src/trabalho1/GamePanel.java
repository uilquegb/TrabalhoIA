package trabalho1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
    
    public static final int LARGURA = 16;
    public static final int ALTURA = 16;

    private ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(GamePanel.this.caminho.size() > 0){
                Entidade entidade = GamePanel.this.caminho.remove(0);
                char t = entidade.getTipo();
                GamePanel.this.robo.setX(entidade.getX());
                GamePanel.this.robo.setY(entidade.getY());
                
                if(t == '1' || t == '2' || t == '3' || t == 'S')
                    entidade.setTipo('L');
                    
                //entidade.setTipo('1');
                GamePanel.this.paint(GamePanel.this.getGraphics());
            }
        }
    };
    private Entidade entidade = null,
            item1 = null,
            item2 = null,
            item3 = null,
            saida = null,
            robo = null;
    private ArrayList<Entidade> caminho;
    
    public GamePanel() {
        
        for(int i = 0;i < Global.getListaEntidades().size();i++)
        {
            entidade = Global.getListaEntidades().get(i);
            switch(entidade.getTipo()){
                case 'S':
                    saida = entidade;
                    break;
                case 'R':
                    robo = entidade;
                    break;
                case '1':
                    item1 = entidade;
                    break;
                case '2':
                    item2 = entidade;
                    break;
                case '3':
                    item3 = entidade;
                    break;
            }
        }
        Entidade[][] caminhos = {
            {item1, item2, item3, saida},
            {item1, item3, item2, saida},
            {item2, item1, item3, saida},
            {item2, item3, item1, saida},
            {item3, item1, item2, saida},
            {item3, item2, item1, saida}
        };
        
        Aestrela aEstrela = new Aestrela(robo, caminhos[0]);
        aEstrela.iniciarPesquisa();
        
        int custoEHeuristica = saida.getCustoAcumulado(),
            escolhido = 0;
        
        for(int _index = 1;_index < caminhos.length;_index++){
            aEstrela.setOrigemDestino(robo, caminhos[_index]);
            aEstrela.iniciarPesquisa();
            
            if(custoEHeuristica > saida.getCustoAcumulado()){
                custoEHeuristica = saida.getCustoAcumulado();
                escolhido = _index;
            }
        }
        
        aEstrela.setOrigemDestino(robo, caminhos[escolhido]);
        aEstrela.iniciarPesquisa();
        caminho = aEstrela.getListaCaminho();
        imprimeCaminho(caminho);
        Timer t = new Timer(200, this.action);
        t.start();
    }
    
    public void imprimeCaminho(ArrayList<Entidade> caminho){
        int i, quantidade = 0;
        char direcao = 'N', direcaoAtual = 'M';
        Entidade atual = null, anterior;
        System.out.print("Caminho = {");
        
        for(i = 0;i < caminho.size();i++){
            atual = caminho.get(i);
            
            if(i > 0){
                anterior = caminho.get(i - 1);
                direcaoAtual = direcao;
                direcao = (anterior == atual.getCima() ? 'B' :
                            (anterior == atual.getBaixo() ? 'C' :
                                (anterior == atual.getDireita() ? 'E' : 'D')
                            )
                          );
                if(direcao != direcaoAtual){
                    if(direcaoAtual != 'N' && direcaoAtual != 'M')
                        System.out.print(String.format("%c (%d), ", direcaoAtual, quantidade));
                    quantidade = 1;
                }
                else
                    quantidade++;
            }
        }
        
        if(direcao != 'N' && direcao != 'M')
            System.out.print(String.format("%c (%d)}, Custo = %d ", direcao, quantidade, atual != null ? atual.getCustoAcumulado() : 0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // desenha o fundo
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
        Image robo = Global.getImagemByTipoEntidade('R'),
              parede = Global.getImagemByTipoEntidade('P'),
              livre = Global.getImagemByTipoEntidade('L'),
              agua = Global.getImagemByTipoEntidade('A'),
              saidaImg = Global.getImagemByTipoEntidade('S'),
              item1 = Global.getImagemByTipoEntidade('1'),
              item2 = Global.getImagemByTipoEntidade('2'),
              item3 = Global.getImagemByTipoEntidade('3');
        // desenha o cenario
        for (Entidade e : Global.getListaEntidades()) {
            switch (e.getTipo()) {
                case 'P': // parede
                    if(parede != null)
                        g.drawImage(parede, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.black);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA);
                        g.setColor(Color.GRAY);
                        g.fillRect(e.getX()*LARGURA + 1, e.getY()*ALTURA + 1, LARGURA - 2, ALTURA - 2);
                    }
                    break;
                case 'L': //area livre
                    if(livre != null)
                        g.drawImage(livre, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.GREEN);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA);
                    }
                    break;
                case 'A': // agua
                    if(agua != null)
                        g.drawImage(agua, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.BLUE);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA);
                    }
                    break;
                case 'S': // saida
                    if(saidaImg != null)
                        g.drawImage(saidaImg, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.RED);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                        g.setColor(Color.BLACK);
                        g.drawString("S", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);
                    }
                    break;
                case 'R': // robo
                    if(robo != null)
                        g.drawImage(robo, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.MAGENTA);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                        g.setColor(Color.BLACK);
                        g.drawString("R", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);
                    }
                    break;                    
                case '1': // item 1
                    if(item1 != null)
                        g.drawImage(item1, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.YELLOW);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                        g.setColor(Color.BLACK);
                        g.drawString("1", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);
                    }
                    break;
                case '2': // item 2
                    if(item2 != null)
                        g.drawImage(item2, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.YELLOW);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                        g.setColor(Color.BLACK);
                        g.drawString("2", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);   
                    }               
                    break;
                 case '3': // item 3
                    if(item3 != null)
                        g.drawImage(item3, e.getX()*LARGURA, e.getY()*ALTURA, null);
                    else{
                        g.setColor(Color.YELLOW);
                        g.fillRect(e.getX()*LARGURA, e.getY()*ALTURA, LARGURA, ALTURA); 
                        g.setColor(Color.BLACK);
                        g.drawString("3", e.getX()*LARGURA + 5, e.getY()*ALTURA + 12);
                    }
                    break;
            }
        }
        
        if(this.robo != null){
            if(robo != null)
                g.drawImage(robo, this.robo.getX()*LARGURA, this.robo.getY()*ALTURA, null);
            else{
                g.setColor(Color.MAGENTA);
                g.fillRect(this.robo.getX()*LARGURA, this.robo.getY()*ALTURA, LARGURA, ALTURA); 
                g.setColor(Color.BLACK);
                g.drawString("R", this.robo.getX()*LARGURA + 5, this.robo.getY()*ALTURA + 12);
            }
        }
    }
    
}
