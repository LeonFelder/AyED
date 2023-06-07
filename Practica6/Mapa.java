package Practica6;
import Practica2.ListaEnlazadaGenerica;
import Practica2.ListaGenerica;

public class Mapa {
    Grafo<String> mapaCiudades;

    public ListaGenerica<String> devolverCamino (String ciudad1, String ciudad2){
        ListaGenerica<String> camino = new ListaEnlazadaGenerica<>();
        boolean [] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()+1];
        for (int i=0;i <= mapaCiudades.listaDeVertices().tamanio();i++)
            if(mapaCiudades.listaDeVertices().elemento(i).dato() == ciudad1)
                camino = devolverCamino(i,marca,ciudad2);
        if (camino == null)
            camino = new ListaEnlazadaGenerica<>();
        return camino;
    }

    private ListaGenerica<String> devolverCamino (int i, boolean [] marca,String destino){
        marca[i] = true;
        ListaGenerica<String> caminoFinal = null;
        Vertice<String> v = mapaCiudades.listaDeVertices().elemento(i);
        if(v.dato() == destino)
            caminoFinal = new ListaEnlazadaGenerica<>();
        else {
            ListaGenerica<Arista<String>> ady =  mapaCiudades.listaDeAdyacentes(v);
            ady.comenzar();
            while((caminoFinal == null) && (!ady.fin())){
                int j = ady.proximo().verticeDestino().getPosicion();
                if(!marca[j]){
                    caminoFinal = devolverCamino(j, marca, destino);
                }
            }
            if(caminoFinal != null)
                caminoFinal.agregarInicio(v.dato());
        }
        return caminoFinal;
    } 
}
