
package trabalho1;

public class Heuristicas {

    public float getDM(int inicioX, int inicioY, int destinoX, int destinoY) 
    {
        float DM = (destinoX - inicioX) + (destinoY - inicioY);
        return DM;
    }
}