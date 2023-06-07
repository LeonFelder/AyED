package Practica6;
import Practica2.ListaEnlazadaGenerica;
import Practica2.ListaGenerica;

public class Recorridos<T>{
    
    public ListaGenerica<T> dfs(Grafo<T> grafo){
        ListaGenerica<T> lista = new ListaEnlazadaGenerica<>();
        boolean [] marca = new boolean [grafo.listaDeVertices().tamanio()];
        for (int i=1; i <= grafo.listaDeVertices().tamanio();i++)
            if (!marca[i])
                dfs(i,grafo,marca,lista);
        return lista;
    }

    private void dfs (int i,Grafo<T> grafo, boolean [] marca, ListaGenerica<T> l){
        marca[i] = true;
        Vertice<T> v = grafo.listaDeVertices().elemento(i);
        l.agregarFinal(v.dato());
        ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
        ady.comenzar();
        while(!ady.fin()){
            int j = ady.proximo().verticeDestino().getPosicion();
            if(!marca[j]){
                dfs(j,grafo,marca,l);
            }
        }
    }
}
