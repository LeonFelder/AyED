package Practica3;
import Practica2.ListaEnlazadaGenerica;

public class ContadorArbol {
    private ArbolBinario<Integer> arbol;
    
    public ContadorArbol (ArbolBinario<Integer> arbol){
        this.arbol = arbol;
    }
    public ListaEnlazadaGenerica<Integer> numerosPares (){
        ListaEnlazadaGenerica<Integer> l = new ListaEnlazadaGenerica<Integer>();
        inOrden(arbol,l);
        //PostOrden(arbol,l);
        return l;
    }
    
    private void inOrden (ArbolBinario<Integer> a,ListaEnlazadaGenerica<Integer> l) {
        if (a.tieneHijoIzquierdo())
            inOrden(a.getHijoIzquierdo(),l);
        if ((a.getDato() % 2) == 0)
            l.agregarFinal(a.getDato());
        if (a.tieneHijoDerecho())
            inOrden(a.getHijoDerecho(),l);
    }
    
    private void PostOrden (ArbolBinario<Integer> a,ListaEnlazadaGenerica<Integer> l) {
        if (a.tieneHijoIzquierdo())
            PostOrden(a.getHijoIzquierdo(),l);
        if (a.tieneHijoDerecho())
            PostOrden(a.getHijoDerecho(),l);
        if (a.getDato() % 2 == 0)
            l.agregarFinal(a.getDato());
    }
}
