package Practica6;
import Practica2.ListaGenerica;
import Practica2.ListaEnlazadaGenerica;

public class VisitaOslo {

    public ListaGenerica<String> paseoEnBici(Grafo<String> lugares, String destino, int maxTiempo, 
    ListaGenerica<String> lugaresRestringidos){
        ListaGenerica<String> camino = new ListaEnlazadaGenerica<>();
        if(! lugares.esVacio()){
            Vertice<String> v = buscarVertice(lugares,"Ayuntamiento");
            if(v != null){
                boolean [] marca = new boolean[lugares.listaDeVertices().tamanio()+1];
                marcarExceptuados(lugares,lugaresRestringidos,marca);
                paseoEnBici(v.getPosicion(),lugares, destino, maxTiempo,marca,camino);
            }
        }
        return camino;
    }

    private boolean paseoEnBici(int i, Grafo<String> g, String destino,int tiempo, boolean[] marca,ListaGenerica<String> camino){
        boolean enc = false;
        marca[i] = true;
        Vertice<String> v = g.vertice(i);
        camino.agregarFinal(v.dato());
        if (v.dato().equals(destino)){
            enc = true;
        }else{
            ListaGenerica<Arista<String>> ady = g.listaDeAdyacentes(v);
            ady.comenzar();
            while ((!enc) && (!ady.fin())){
                Arista<String> a = ady.proximo();
                int j = a.verticeDestino().getPosicion();
                int peso = a.peso();
                if((tiempo - peso > 0) && (!marca[j]))
                    enc = paseoEnBici(j, g, destino, tiempo-peso, marca, camino);
            }
        }
        if (!enc){
            camino.eliminarEn(camino.tamanio());
            marca[i] = false;
        }
        return enc;
    }

    private Vertice<String> buscarVertice(Grafo<String> grafo,String dato){
        ListaGenerica<Vertice<String>> vertices = grafo.listaDeVertices();
        Vertice<String> busc = null;
        vertices.comenzar();
        while(!vertices.fin()){
            Vertice<String> v = vertices.proximo();
            if(v.dato() == dato)
                busc = v;
        }
        return busc;
    }

    private void marcarExceptuados (Grafo<String> grafo, ListaGenerica<String> l,boolean [] marca){
        l.comenzar();
        while(!l.fin()){
            Vertice<String> v = buscarVertice(grafo, l.proximo());
            marca[v.getPosicion()] = true;
        }
    } 
}
