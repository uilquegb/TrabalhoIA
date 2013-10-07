
package trabalho1;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Global {
    
    private static ArrayList<Entidade> listaEntidades = new ArrayList<>();
    private static ArrayList<Entidade> listaEntidadesImagens = new ArrayList<>();

    public static ArrayList<Entidade> getListaEntidades() {
        return listaEntidades;
    }
    
    public static ArrayList<Entidade> getEntidadesComImagens() {
        return listaEntidadesImagens;
    }
    
    public static Image getImagemByTipoEntidade(char tipo){
        for(int i = 0;i < listaEntidadesImagens.size();i++)
            if(listaEntidadesImagens.get(i).getTipo() == tipo)
                return listaEntidadesImagens.get(i).getImagem();
        return null;
    }
    
}
