package Practica4;
import Practica2.ListaGenerica;
import Practica2.ListaEnlazadaGenerica;

public class Parcial {
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
}
