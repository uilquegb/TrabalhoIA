package trabalho1;

import java.util.ArrayList;

public class Aestrela {
    private Entidade origem;
    private ArrayList<Entidade> destinos;
    private ArrayList<Entidade> listaAberta;
    private ArrayList<Entidade> listaFechada;
    private ArrayList<Entidade> listaCaminho;
    private int heuristicaParede = 987654;
    
    public Aestrela(Entidade origem, Entidade[] destinos) {
        this.origem = origem;
        this.resetar();
        
        for(int i = 0;i < destinos.length;i++)
            this.addDestino(destinos[i]);
    }

    public Aestrela(Entidade origem, Entidade destino) {
        this(origem, new Entidade[]{destino});
    }
    
    private void resetar(){
        if(this.destinos == null)
            this.destinos = new ArrayList<>();
        if(this.listaAberta == null)
            this.listaAberta = new ArrayList<Entidade>();
        if(this.listaFechada == null)
            this.listaFechada = new ArrayList<Entidade>();
        if(this.listaCaminho == null)
            this.listaCaminho = new ArrayList<Entidade>();
        
        this.destinos.clear();
        this.listaAberta.clear();
        this.listaFechada.clear();
        this.listaCaminho.clear();
        
        for(int i = 0;i < Global.getListaEntidades().size();i++){
            Entidade item = Global.getListaEntidades().get(i);
            item.setCustoAcumulado(0);
        }
    }

    public boolean iniciarPesquisa() {
        if (getOrigem() == getDestino()) {
            return true;
        }
        
        this.listaAberta.clear();
        this.listaAberta.add(getOrigem());
        this.listaFechada.clear();

        if (pesquisar()) {
            boolean salvou = salvarCaminho();
            
            if(this.destinos.size() > 1){
                this.setOrigem(this.destinos.remove(0));
                return salvou && this.iniciarPesquisa();
            }
            
            return salvou;
        }
        return false;
    }
    
    private boolean pesquisar() {
        while(!this.listaAberta.isEmpty()){
            //procura o vizinho com menor custo na lista aberta
            Entidade atual = this.listaAberta.get(0);

            for (int i = 1; i < this.listaAberta.size(); i++) {
                if (atual.getCustoAcumuladoEHeuristica() > this.listaAberta.get(i).getCustoAcumuladoEHeuristica()) {
                    atual = this.listaAberta.get(i);
                }
            }

            // remove a posicao atual da lista aberta e insere na lista fechada
            this.listaFechada.add(atual);
            this.listaAberta.remove(atual);

            // se o atual for o destino, retorna verdadeiro
            if (atual == this.getDestino()) {
                return true;
            }

            ArrayList<Entidade> vizinhosDoAtual = new ArrayList<Entidade>();
            vizinhosDoAtual.add(atual.getCima());
            vizinhosDoAtual.add(atual.getDireita());
            vizinhosDoAtual.add(atual.getBaixo());
            vizinhosDoAtual.add(atual.getEsquerda());

            for(int i = 0;i < vizinhosDoAtual.size();i++){
                Entidade vizinho = vizinhosDoAtual.get(i);
                if(this.ehEntidadeValida(vizinho)){
                    boolean naoTaNaListaAberta = !this.listaAberta.contains(vizinho);
                    int heuristica = getDM(this.getDestino().getX(), this.getDestino().getY(), vizinho.getX(), vizinho.getY());

                    if (naoTaNaListaAberta || vizinho.getHeuristita() > heuristica) {
                        if(naoTaNaListaAberta)
                            this.listaAberta.add(vizinho);
                        int custoAcumulado = atual.getCustoAcumulado();
                        vizinho.setAnterior(atual);
                        vizinho.setCustoAcumulado(custoAcumulado);
                        vizinho.setHeuristica(heuristica);
                    }
                }
            }
        }

        // se a lista aberta ficou vazia, não existem caminhos
        return false;
        //pesquisa recursivamente, até obter um retorno
        //return pesquisar();
    }

    private boolean ehEntidadeValida(Entidade entidade){
        return entidade != null && !this.listaFechada.contains(entidade) && entidade.getTipo() != 'P';
    }
    
    //salva o caminho
    private boolean salvarCaminho() {
        Entidade atual = getDestino();

        if (atual == null) {
            return false;
        }
        
        Entidade aux = null;
        int index = this.listaCaminho.size();
        
        while (atual != null) {
            this.listaCaminho.add(index, atual);
            aux = atual.getAnterior();
            atual.setAnterior(null);
            atual = aux;
        }
        return true;

    }
    
    public void setOrigemDestino(Entidade origem, Entidade[] destinos){
        this.resetar();
        this.setOrigem(origem);
        
        for(int i = 0;i < destinos.length;i++)
            this.addDestino(destinos[i]);
    }
    
    public void setOrigemDestino(Entidade origem, Entidade destino){
        if(origem != null && destino != null && origem != destino){
            this.setOrigemDestino(origem, new Entidade[]{destino});
        }
    }
    
    public Entidade getOrigem() {
        return this.origem;
    }

    public void setOrigem(Entidade origem) {
        this.origem = origem;
    }

    public Entidade getDestino() {
        if(this.destinos.size() > 0)
            return this.destinos.get(0);
        return null;
    }

    public void setDestino(Entidade destino) {
        this.destinos.clear();
        this.addDestino(destino);
    }
    public void addDestino(Entidade destino){
        this.destinos.add(destino);
    }

    public ArrayList<Entidade> getListaCaminho() {
        return this.listaCaminho;
    }

    private int getDM(int p1X, int p1Y, int p2X, int p2Y) {
        int DM = Math.abs(p2X - p1X) + Math.abs(p2Y - p1Y);
        return DM;
    }
}
