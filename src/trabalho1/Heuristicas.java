
package trabalho1;

public class Heuristicas {

    public float getDM(int inicioX, int inicioY, int destinoX, int destinoY) 
    {
        float distX = destinoX > inicioX ? destinoX - inicioX : inicioX - destinoX,
              distY = destinoY > inicioY ? destinoY - inicioY : inicioY - destinoY;
        float DM = distX + distY;//(destinoX - inicioX) + (destinoY - inicioY);
        return DM;
    }
}