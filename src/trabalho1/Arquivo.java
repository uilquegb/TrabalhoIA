
package trabalho1;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public abstract class Arquivo {
    private static Entidade getEntidade(int x, int y, int colunas){
        int index = y > 0 ? ((y * colunas) + x) : x;
        
        if(x >= 0 && y >= 0 && index < Global.getListaEntidades().size()){
            return Global.getListaEntidades().get(index);
        }
        
        return null;
    }
    
    public static void ler(String filename){
        try{
        
            Scanner scan = new Scanner (new File(filename));
            int y = 0;
            boolean arquivos = false;
            while (scan.hasNextLine()){
                
                String line = scan.nextLine().trim();
                
                //trata linhas vazias
                if (line.isEmpty() || line.trim().charAt(0) == '#')
                    continue;
                
                if (line.equals("ARQUIVOS")){
                    arquivos = true;
                    continue;
                }
                
                for (int i = 0; !arquivos && i < line.length(); i++){
                    Entidade e = new Entidade(line.charAt(i), i, y);
                    e.setCima(Arquivo.getEntidade(i, y - 1, line.length()));
                    e.setEsquerda(Arquivo.getEntidade(i - 1, y, line.length()));
                    
                    if(e.getCima() != null)
                        e.getCima().setBaixo(e);
                    
                    if(e.getEsquerda() != null)
                        e.getEsquerda().setDireita(e);
                    
                    
                    if(e.getTipo() == 'e')
                        Global.getListaEntidades().add(new Entidade('L', i, y));
                    Global.getListaEntidades().add(e);
                }
                
                //lendo as imagens
                if(arquivos){
                    String arquivoDados[] = new String [3];
                    
                    for (int i = 0 ; i < arquivoDados.length ; i++)
                        arquivoDados[i] = "";
                    
                    int campo = 0;
                    for (int i = 0 ; i < line.length() ; i++){
                        if ( line.charAt(i) == ' '){
                            campo++;
                            continue;
                        }
                        arquivoDados[campo] += line.charAt(i);
                    }
                
                    Image imagem = ImageIO.read(new File(arquivoDados[0]));
                    Entidade e = new Entidade( imagem, arquivoDados[1].charAt(0));
                    Global.getEntidadesComImagens().add(e);                    
                }
                
                y++;
            }

            scan.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo " + filename + " não existe");
        } catch (IOException ex) {
            System.out.println("Arquivo de imagem não existe: " + ex.getMessage());
        }
   }
    
}
