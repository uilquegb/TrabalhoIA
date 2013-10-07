
package trabalho1;

import java.awt.BorderLayout;
import javax.swing.*;


public class Janela extends JFrame {
    
    public Janela(){
        super("Arquivo");   
        GamePanel pnl_game = new GamePanel();
        setLayout(new BorderLayout());
        add(pnl_game, BorderLayout.CENTER);
        setSize(464, 407);
        setLocation(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
