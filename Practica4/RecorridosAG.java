package Practica4;

public class RecorridosAG {
    public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden (ArbolGeneral<Integer> a, Integer n){
        ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<>();
        if (!a.esVacio())
            numerosImparesMayoresQuePreOrdenAux(a,n,lista);
        return lista;
    }
    private void numerosImparesMayoresQuePreOrdenAux (ArbolGeneral<Integer> a, Integer n,ListaGenerica<Integer> l) {
        if ((a.getDato() % 2 != 0) && (a.getDato() > n))
            l.agregarFinal(a.getDato());
        if (a.tieneHijos()){
            ListaGenerica hijos = a.getHijos();
            while (!hijos.fin())
                numerosImparesMayoresQuePreOrdenAux((ArbolGeneral) hijos.proximo(),n,l);
        }
    }
    
    public ListaGenerica<Integer> numerosImparesMayoresQueInOrden (ArbolGeneral<Integer> a, Integer n){
        ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<>();
        if (!a.esVacio())
            numerosImparesMayoresQueInOrdenAux(a,n,lista);
        return lista;
    }
    private void numerosImparesMayoresQueInOrdenAux (ArbolGeneral<Integer> a, Integer n,ListaGenerica<Integer> l) {
        ListaGenerica hijos = a.getHijos();
        if (!hijos.esVacia())
            numerosImparesMayoresQueInOrdenAux((ArbolGeneral) hijos.proximo(),n,l);
        if ((a.getDato() % 2 != 0) && (a.getDato() > n))
            l.agregarFinal(a.getDato());
        if (!hijos.esVacia())
            while (!hijos.fin())
                numerosImparesMayoresQueInOrdenAux((ArbolGeneral) hijos.proximo(),n,l);
    }
    
    public ListaGenerica<Integer> numerosImparesMayoresQuePostOrden (ArbolGeneral<Integer> a, Integer n){
        ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<>();
        if (!a.esVacio())
            numerosImparesMayoresQuePostOrdenAux(a,n,lista);
        return lista;
    }
    private void numerosImparesMayoresQuePostOrdenAux (ArbolGeneral<Integer> a, Integer n,ListaGenerica<Integer> l) {
        if (a.tieneHijos()){
            ListaGenerica hijos = a.getHijos();
            while (!hijos.fin())
                numerosImparesMayoresQuePostOrdenAux((ArbolGeneral) hijos.proximo(),n,l);
        }
        if ((a.getDato() % 2 != 0) && (a.getDato() > n))
            l.agregarFinal(a.getDato());
    }
    
    public ListaGenerica<Integer> numerosImparesMayoresQuePorNiveles(ArbolGeneral<Integer> a, Integer n){
        ListaGenerica<Integer> l = new ListaEnlazadaGenerica();
        if (!a.esVacio()){
            ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<>();
            ArbolGeneral<Integer> aux;
            cola.encolar(a);
            cola.encolar(null);
            while (!cola.esVacia()){
                aux = cola.desencolar();
                if (aux != null){
                    if ((aux.getDato() % 2 != 0) && (aux.getDato() > n))
                        l.agregarFinal(aux.getDato());
                    if (aux.tieneHijos()){
                        ListaGenerica<ArbolGeneral<Integer>> hijos = aux.getHijos();
                        hijos.comenzar();
                        while (!hijos.fin())
                            cola.encolar(hijos.proximo());
                    }
                } else if (!cola.esVacia()){
                    cola.encolar(null);
                }
            }
        }
        return l;
    }
}
