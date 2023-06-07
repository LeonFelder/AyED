package Practica6;
import Practica2.ColaGenerica;
import Practica2.ListaEnlazadaGenerica;
import Practica2.ListaGenerica;

public class Recorridos<T>{
    
    public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo){
        ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<>();
        boolean [] marca = new boolean [grafo.listaDeVertices().tamanio()+1];
        for (int i=1; i <= grafo.listaDeVertices().tamanio();i++)
            if (!marca[i])
                dfs(i,grafo,marca,lista);
        return lista;
    }

    private void dfs (int i,Grafo<T> grafo, boolean [] marca, ListaGenerica<Vertice<T>> l){
        marca[i] = true;
        Vertice<T> v = grafo.listaDeVertices().elemento(i);
        l.agregarFinal(v);
        ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
        ady.comenzar();
        while(!ady.fin()){
            int j = ady.proximo().verticeDestino().getPosicion();
            if(!marca[j]){
                dfs(j,grafo,marca,l);
            }
        }
    }

    public ListaGenerica<Vertice<T>> bfs (Grafo<T> grafo){
        ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<>();
        boolean [] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        for(int i=1;i<=marca.length;i++){
            if(!marca[i])
                bfs(i,grafo,marca,lista);
        }
        return lista;
    }

    private void bfs (int i,Grafo<T> grafo,boolean[] marca,ListaGenerica<Vertice<T>> l){
        ColaGenerica<Vertice<T>> cola = new ColaGenerica<>();
        cola.encolar(grafo.listaDeVertices().elemento(i));
        marca[i] = true;
        while(!cola.esVacia()){
            Vertice<T> v = cola.desencolar();
            l.agregarFinal(v);
            ListaGenerica<Arista<T>>ady = grafo.listaDeAdyacentes(v);
            ady.comenzar();
            while (!ady.fin()){
                Arista<T> arista = ady.proximo();
                int j = arista.verticeDestino().getPosicion();
                if(!marca[j]){
                    marca[j] = true;
                    cola.encolar(arista.verticeDestino());
                }
            }
        }
    }
}
