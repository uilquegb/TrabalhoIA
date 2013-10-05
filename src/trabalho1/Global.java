
package trabalho1;

import java.util.ArrayList;

public abstract class Global {
    
    private static ArrayList<Entidade> listaEntidades = new ArrayList<>();

    public static ArrayList<Entidade> getListaEntidades() {
        return listaEntidades;
    }
    
}
