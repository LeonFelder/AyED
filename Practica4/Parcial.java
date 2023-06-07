package Practica4;
import Practica2.ListaGenerica;
import Practica2.ListaEnlazadaGenerica;
import Practica3.ArbolBinario;

public class Parcial {
    public ArbolBinario<Integer> a;
    public ArbolGeneral<Integer> ag;
    
    public Parcial (ArbolBinario<Integer> a){
        this.a = a;
    }
    
    public int caminosPares2 (ArbolGeneral<Integer> arbol){
        int caminos = -1;
        if (!arbol.esVacio())
            caminos = caminosPares2(arbol,1);
        return caminos;
    }
    
    private int caminosPares2 (ArbolGeneral<Integer> arbol,int nodos){
        int caminos = 0;
        if (arbol.esHoja()){
            if ((nodos + 1) % 2 == 0)
                caminos =+ 1;
        }else{
            ListaGenerica<ArbolGeneral<Integer>> hijos = arbol.getHijos();
            hijos.comenzar();
            while(!hijos.fin())
                caminos = caminos + caminosPares2(hijos.proximo(),nodos + 1);        
        }
        return caminos;
    }
    
    public ListaEnlazadaGenerica<ListaEnlazadaGenerica<Character>> caminosPares (ArbolGeneral<Character> arbol){
        ListaEnlazadaGenerica<ListaEnlazadaGenerica<Character>> lista = new ListaEnlazadaGenerica<>();
        ListaEnlazadaGenerica<Character> lista2 = new ListaEnlazadaGenerica<>();
        caminosPares(lista,lista2,arbol);
        return lista;
    }
    private void caminosPares (ListaEnlazadaGenerica<ListaEnlazadaGenerica<Character>> listaGeneral,ListaEnlazadaGenerica<Character> l,ArbolGeneral<Character> a){
        l.agregarFinal(a.getDato());
        if (a.esHoja()){
            if (l.tamanio() % 2 == 0)
                listaGeneral.agregarFinal(l);
        }else{
            ListaGenerica<ArbolGeneral<Character>> hijos = a.getHijos();
            hijos.comenzar();
            while (!hijos.fin())
                caminosPares(listaGeneral,l,hijos.proximo());
        }
        l.eliminarEn(l.tamanio() -1);
    }
    
    public boolean resolver (int k){
        boolean es = false;
        if (!a.esVacio()){
            es = resolver(a,k,1);
        }
        return es;
    }
    
    public boolean resolver (ArbolBinario<Integer> a,int k,int nodos){
        boolean es = false;
        if (!a.esHoja()){
            if (a.tieneHijoIzquierdo()){
                es = resolver(a.getHijoIzquierdo(),k,nodos + 1);
            }
            if (es && a.tieneHijoDerecho()){
                es = resolver(a.getHijoDerecho(),k,nodos + 1);
            }
        } else if (nodos == k){
            es = true;
        }
        return es;
    }
    
    public Integer promedioAcotado (ArbolGeneral<Integer> arbol,Integer min, Integer max){
        ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<>();
        Integer prom = 0;
        if (!arbol.esVacio()){
            promedioAcotado(arbol,min,max,lista);
            while (!lista.fin())
                prom =+ lista.proximo();
            prom = prom/lista.tamanio();
        }
        return prom;
    }
    
    public void promedioAcotado (ArbolGeneral<Integer> a,Integer min, Integer max, ListaGenerica<Integer> l){
        ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
        if (!hijos.esVacia()){
            hijos.comenzar();
            promedioAcotado(hijos.proximo(),min,max,l);
        }
        Integer dato = a.getDato();
        if ((dato > min) && (dato < max))
            l.agregarFinal(dato);
        while (!hijos.fin())
            promedioAcotado(hijos.proximo(),min,max,l);
    }
    
    public ListaGenerica<Integer []> valoresMayores(int valor, ArbolGeneral<Integer> arbol){
        ListaGenerica<Integer []> lista = new ListaEnlazadaGenerica<>();
        if (!arbol.esVacio()){
            valoresMayores(valor,arbol,lista,0);
        }
        return lista;
    }
    
    public void valoresMayores (int valor, ArbolGeneral<Integer> a,ListaGenerica<Integer []> l,int nivel){
         ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
        if (a.tieneHijos()){
            hijos.comenzar();
            valoresMayores(valor,hijos.proximo(),l,nivel+1);
        }
        Integer dato = a.getDato();
        if (dato > valor){
            Integer [] v = new Integer[2];
            v[0] = dato;
            v[1] = nivel;
            l.agregarFinal(v);
        }
        if (a.tieneHijos())
            while(!hijos.fin())
                valoresMayores(valor,hijos.proximo(),l,nivel+1);
    }
    
    public ListaGenerica<String> valoresEntre (int menor, int mayor, ArbolGeneral<Integer> arbol){
        ListaGenerica<String> lista = new ListaEnlazadaGenerica<>();
        if (!arbol.esVacio())
            valoresEntre(menor,mayor,arbol,lista,0);
        return lista;
    }
    
    private void valoresEntre (int menor, int mayor, ArbolGeneral<Integer> a,ListaGenerica<String> l,int nivel){
        if (a.tieneHijos()){
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            hijos.comenzar();
            while(!hijos.fin())
                valoresEntre(menor,mayor,a,l,nivel+1);
        }
        Integer dato = a.getDato();
        if ((dato >= menor)&& (dato <= mayor))
            l.agregarFinal(dato+" nivel "+nivel);
    }
    
    public ListaEnlazadaGenerica<Integer> frontera (){
        ListaEnlazadaGenerica<Integer> lista = new ListaEnlazadaGenerica<>();
        if (!ag.esVacio())
            frontera(ag,lista);
        return lista;
    }
    
    private void frontera (ArbolGeneral<Integer> a,ListaEnlazadaGenerica<Integer> l){
        if (!a.esHoja()){
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            hijos.comenzar();
            while (!hijos.fin())
                frontera(hijos.proximo(),l);
        } else if (a.getDato() % 2 == 0){
            l.agregarFinal(a.getDato());
        }
    }
}
