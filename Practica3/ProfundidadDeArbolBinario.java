package Practica3;
import Practica2.ColaGenerica;

public class ProfundidadDeArbolBinario {
    ArbolBinario<Integer> arbol;
    
    public ProfundidadDeArbolBinario (ArbolBinario<Integer> arbol){
        this.arbol = arbol;
    }
    public int sumaElementosProfundidad(int p){
        int profundidad = 0;
        int suma = 0;
        ArbolBinario<Integer> aux;
        ColaGenerica<ArbolBinario<Integer>> cola = new ColaGenerica<>();
        cola.encolar(arbol);
        cola.encolar(null);
        while (!cola.esVacia()){
            aux = cola.desencolar();
            if (aux != null){
                if (profundidad == p)
                    suma += aux.getDato();
                if (aux.tieneHijoIzquierdo())
                    cola.encolar(aux.getHijoIzquierdo());
                if (aux.tieneHijoDerecho())
                    cola.encolar(aux.getHijoDerecho());
            } else if (!cola.esVacia()){
                cola.encolar(null);
                profundidad++;
            }
        }
        return suma;
    }
}
