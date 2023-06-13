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

    public ListaGenerica<String> devolverCaminoExceptuando (String ciudad1, String ciudad2, ListaGenerica<String> ciudades){
        ListaGenerica<String> camino = new ListaEnlazadaGenerica<>();
        if(!mapaCiudades.esVacio()){
            Vertice<String> v = buscarVertice(ciudad1);
            if (v != null){
                boolean [] marca = new boolean[this.mapaCiudades.listaDeVertices().tamanio()+1]; 
                marcarExceptuados(ciudades,marca);
                devolverCaminoExceptuando(v.getPosicion(),ciudad2,camino,marca);
            }
        }
        return camino;
    }

    private boolean devolverCaminoExceptuando(int i, String destino,ListaGenerica<String> cam,boolean [] marca){
        boolean enc = false;
        marca[i] = true;
        Vertice<String> v = this.mapaCiudades.vertice(i);
        cam.agregarFinal(v.dato());
        if(v.dato().equals(destino))
            enc = true;
        else{
            ListaGenerica<Arista<String>> ady= this.mapaCiudades.listaDeAdyacentes(v);
            ady.comenzar();
            while((!enc) && (!ady.fin())){
                Arista<String> a= ady.proximo();
                int j = a.verticeDestino().getPosicion();
                if(!marca[j])
                    devolverCaminoExceptuando(j, destino, cam, marca);
            }
            if(!enc)
                cam.eliminarEn(cam.tamanio());
        }
        return enc;
    } 

    private void marcarExceptuados (ListaGenerica<String> l, boolean [] marca){
        l.comenzar();
        while(!l.fin()){
            Vertice<String> v = buscarVertice(l.proximo());
            marca[v.getPosicion()] = true;

        }
    }

    private Vertice<String> buscarVertice (String dato){
        ListaGenerica<Vertice<String>> vertices = this.mapaCiudades.listaDeVertices();
        Vertice<String> ciudad = null;
        vertices.comenzar();
        while(!vertices.fin()){
            Vertice<String> v = vertices.proximo();
            if (v.dato() == dato)
                ciudad = v;
        }
        return ciudad;
    }

    public ListaGenerica<String> caminoMasCorto (String ciudad1, String ciudad2){
        ListaGenerica<String> caminoFinal = new ListaEnlazadaGenerica<>();
        if (!this.mapaCiudades.esVacio()){
            Vertice<String> v = buscarVertice(ciudad1);
            if(v != null){
                ListaGenerica<String> camino = new ListaEnlazadaGenerica<>();
                boolean [] marca = new boolean[this.mapaCiudades.listaDeVertices().tamanio()+1];
                caminoMasCorto(v.getPosicion(),ciudad2,camino,0,caminoFinal,Integer.MAX_VALUE,marca);
            }
        }
        return caminoFinal;
    }

    private int caminoMasCorto (int i, String destino, ListaGenerica<String> cam,int dist,ListaGenerica<String> camF,int distF,boolean [] marca){
        marca[i] = true;
        Vertice<String> v = this.mapaCiudades.vertice(i);
        cam.agregarFinal(v.dato());
        if(v.dato().equals(destino)){
            if(distF > dist){
                while(!camF.esVacia()){
                    camF.eliminarEn(0);
                }
                cam.comenzar();
                while(cam.fin())
                    camF.agregarFinal(cam.proximo());
            }
            distF = camF.tamanio(); 
        }else{
            ListaGenerica<Arista<String>> ady = this.mapaCiudades.listaDeAdyacentes(v);
            ady.comenzar();
            while(!ady.fin()){
                Arista<String> a= ady.proximo();
                int j = a.verticeDestino().getPosicion();
                if (!marca[j])
                    distF = caminoMasCorto(j,destino,cam,dist+a.peso(),camF,distF,marca);
            }
        }
        marca[i] = false;
        return distF;
    }

    public ListaGenerica<String> caminoSinCargarCombustible (String ciudad1, String ciudad2, int tanqueAuto){
        ListaGenerica<String> camino = new ListaEnlazadaGenerica<>();
        if(!this.mapaCiudades.esVacio()){
            Vertice<String> v = buscarVertice(ciudad1);
            if (v != null){
                boolean [] marca = new boolean[this.mapaCiudades.listaDeVertices().tamanio()+1]; 
                caminoSinCargarCombustible(v.getPosicion(),ciudad2,camino,tanqueAuto,marca);
            }
        }
        return camino;
    }

    private boolean caminoSinCargarCombustible (int i, String destino,ListaGenerica<String> cam, int tanque, boolean [] marca){
        boolean enc = false;
        marca[i] = true;
        Vertice<String> v = this.mapaCiudades.vertice(i);
        cam.agregarFinal(v.dato());
        if(tanque > 0) {
            if (v.dato().equals(destino)){
                enc = true;
            }else{
                ListaGenerica<Arista<String>> ady = this.mapaCiudades.listaDeAdyacentes(v);
                ady.comenzar();
                while(ady.fin()){
                    Arista<String> a = ady.proximo();
                    int j = a.verticeDestino().getPosicion();
                    if(!enc && !marca[j])
                        enc = caminoSinCargarCombustible(j,destino,cam,tanque-a.peso(),marca);
                }
            }
        }
        marca[i] = false;
        if(!enc)
            cam.eliminarEn(cam.tamanio());
        return enc;    
    }

    public ListaGenerica<String> caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto){
        ListaGenerica<String> caminoFinal = new ListaEnlazadaGenerica<>();
        if (!this.mapaCiudades.esVacio()){
            Vertice<String> v = buscarVertice(ciudad1);
            if (v != null){
                boolean [] marca = new boolean[this.mapaCiudades.listaDeVertices().tamanio()+1];
                ListaGenerica<String> camino = new ListaEnlazadaGenerica<>();
                caminoConMenorCargaDeCombustible(v.getPosicion(), ciudad2, tanqueAuto,tanqueAuto,marca,camino,0,caminoFinal,Integer.MAX_VALUE);
            }
        }
        return caminoFinal;
    }

    private int caminoConMenorCargaDeCombustible(int i, String destino, int tanque,int tanqueActual,boolean [] marca,
    ListaGenerica<String> camino,int cargas,ListaGenerica<String> caminoFinal, int cargasFinal){
        marca[i] = true;
        Vertice<String> v = this.mapaCiudades.vertice(i);
        camino.agregarFinal(v.dato());
        if (v.dato() == destino){
            if(cargasFinal> cargas){
                while(!caminoFinal.esVacia())
                    caminoFinal.eliminarEn(caminoFinal.tamanio());
                camino.comenzar();
                while(!camino.fin())
                    caminoFinal.agregarFinal(camino.proximo());
                cargasFinal = cargas;
            }
        }else{
            ListaGenerica<Arista<String>> ady = this.mapaCiudades.listaDeAdyacentes(v);
            ady.comenzar();
            while(ady.fin()){
                Arista<String> a = ady.proximo();
                int peso = a.peso();
                int j = a.verticeDestino().getPosicion();
                if (!marca[j])
                    if (tanqueActual - peso > 0)
                        cargasFinal = caminoConMenorCargaDeCombustible(j,destino,tanque,tanqueActual-peso,marca,camino,cargas,caminoFinal,cargasFinal);
                    else
                        cargasFinal = caminoConMenorCargaDeCombustible(j,destino,tanque,tanque,marca,camino,cargas+1,caminoFinal,cargasFinal);
             }
        }
        camino.eliminarEn(camino.tamanio());
        return cargasFinal;
    }
}
