package trabalho1;

// comentario de teste



import java.util.ArrayList;

public class Aestrela {

    private posicao[][] mapa;
    private posicao origem;
    private posicao destino;
    private ArrayList<posicao> listaAberta;
    private ArrayList<posicao> listaFechada;
    private ArrayList<posicao> listaCaminho;
    private ArrayList<posicao> listaParedes;

    public Aestrela(posicao mapa[][], posicao origem, posicao destino) {
        this.mapa = mapa;
        this.origem = origem;
        this.destino = destino;
        listaAberta = new ArrayList<posicao>();
        listaFechada = new ArrayList<posicao>();
        listaCaminho = new ArrayList<posicao>();
        listaParedes = new ArrayList<posicao>();
    }

    public boolean iniciarPesquisa() {
        if (getOrigem() == getDestino()) {
            return true;
        }

        listaAberta.add(getOrigem());

        if (pesquisar()) {
            return salvarCaminho();
        }
        return false;
    }

    private boolean pesquisar() {
        //procura o vizinho com menor custo na lista aberta
        posicao atual = listaAberta.get(0);

        for (int i = 1; i < listaAberta.size(); i++) {
            if (atual.getCustoF() > listaAberta.get(i).getCustoF()) {
                atual = listaAberta.get(i);
            }
        }

        // remove a posicao atual da lista aberta e insere na lista fechada
        listaFechada.add(atual);
        listaAberta.remove(atual);

        // se o atual for o destino, retorna verdadeiro
        if (atual == destino) {
            return true;
        }

        int x;
        int y;

        x = atual.getX();
        y = atual.getY();

        int dir = x + 1;
        int esq = x - 1;
        int cima = y - 1;
        int baixo = y + 1;

        // direita
        if (dir < mapa[0].length) {
            posicao direita = mapa[dir][y];
            if (!listaFechada.contains(direita) && !listaParedes.contains(direita)) {
                int custoG = atual.getCustoG() + 1;
                int custoH = getDM(destino.getX(), destino.getY(), direita.getX(), direita.getY());

                if (!listaAberta.contains(direita)) {
                    direita.setPai(atual);
                    listaAberta.add(direita);
                    direita.setCustoG(custoG);
                    direita.setCustoH(custoH);
                } else {
                    if (direita.getCustoH() > custoH) {
                        direita.setPai(atual);
                        direita.setCustoG(custoG);
                        direita.setCustoH(custoH);
                    }
                }
            }
        }

        // esquerda
        if (esq >= 0) {
            posicao esquerda = getGrade()[esq][y];
            if (!listaFechada.contains(esquerda) && !listaParedes.contains(esquerda)) {
                int custoG = atual.getCustoG() + 1;
                int custoH = getDM(destino.getX(), destino.getY(), esquerda.getX(), esquerda.getY());

                if (!listaAberta.contains(esquerda)) {
                    esquerda.setPai(atual);
                    listaAberta.add(esquerda);
                    esquerda.setCustoG(custoG);
                    esquerda.setCustoH(custoH);
                } else {
                    if (esquerda.getCustoH() > custoH) {
                        esquerda.setPai(atual);
                        esquerda.setCustoG(custoG);
                        esquerda.setCustoH(custoH);
                    }
                }
            }
        }

        //cima
        if (cima >= 0) {
            posicao acima = getGrade()[x][cima];
            if (!listaFechada.contains(acima) && !listaParedes.contains(acima)) {
                int custoG = atual.getCustoG() + 1;
                int custoH = getDM(destino.getX(), destino.getY(), acima.getX(), acima.getY());

                if (!listaAberta.contains(acima)) {
                    acima.setPai(atual);
                    listaAberta.add(acima);
                    acima.setCustoG(custoG);
                    acima.setCustoH(custoH);
                } else {
                    if (acima.getCustoH() > custoH) {
                        acima.setPai(atual);
                        acima.setCustoG(custoG);
                        acima.setCustoH(custoH);
                    }
                }
            }
        }

        //baixo
        if (baixo < mapa.length) {
            posicao abaixo = mapa[x][baixo];
            if (!listaFechada.contains(abaixo) && !listaParedes.contains(abaixo)) {
                int custoG = atual.getCustoG() + 1;
                int custoH = getDM(destino.getX(), destino.getY(), abaixo.getX(), abaixo.getY());

                if (!listaAberta.contains(abaixo)) {
                    abaixo.setPai(atual);
                    listaAberta.add(abaixo);
                    abaixo.setCustoG(custoG);
                    abaixo.setCustoH(custoH);
                } else {
                    if (abaixo.getCustoH() > custoH) {
                        abaixo.setPai(atual);
                        abaixo.setCustoG(custoG);
                        abaixo.setCustoH(custoH);
                    }
                }
            }
        }

        // se a lista aberta ficou vazia, não existem caminhos
        if (listaAberta.isEmpty()) {
            return false;
        }

        //pesquisa recursivamente, até obter um retorno
        return pesquisar();
    }

    //salva o caminho
    private boolean salvarCaminho() {
        posicao atual = getDestino();

        if (atual == null) {
            return false;
        }

        while (atual != null) {
            listaCaminho.add(atual);
            atual = atual.getPai();
        }
        return true;

    }

    public posicao[][] getGrade() {
        return mapa;
    }

    public void setGrade(posicao[][] grade) {
        this.mapa = grade;
    }

    public posicao getOrigem() {
        return origem;
    }

    public void setOrigem(posicao origem) {
        this.origem = origem;
    }

    public posicao getDestino() {
        return destino;
    }

    public void setDestino(posicao destino) {
        this.destino = destino;
    }

    public ArrayList<posicao> getListaCaminho() {
        return listaCaminho;
    }

    public ArrayList<posicao> getListaBloqueios() {
        return listaParedes;
    }

    private int getDM(int p1X, int p1Y, int p2X, int p2Y) {
        int DM = Math.abs(p2X - p1X) + Math.abs(p2Y - p1Y);
        return DM;
    }

    private static class posicao {

        private int custoG = 0;
        private int custoH = 9999;
        private int x = 0;
        private int y = 0;
        private posicao pai = null;

        public posicao(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getCustoF() {
            return custoG + custoH;
        }

        public int getCustoG() {
            return custoG;
        }

        public void setCustoG(int custoG) {
            this.custoG = custoG;
        }

        public int getCustoH() {
            return custoH;
        }

        public void setCustoH(int custoH) {
            this.custoH = custoH;
        }

        public int getX() {
            return x;
        }

        public void setPosicaoX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setPosicaoY(int y) {
            this.y = y;
        }

        public posicao getPai() {
            return pai;
        }

        public void setPai(posicao pai) {
            this.pai = pai;
        }
    }
}
