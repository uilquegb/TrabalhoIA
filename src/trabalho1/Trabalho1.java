
package trabalho1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Trabalho1 {


    public static void main(String[] args) {
        Arquivo.ler("fase.txt");
        
        Janela janela = new Janela();
        janela.setVisible(true);
        
    }
}

