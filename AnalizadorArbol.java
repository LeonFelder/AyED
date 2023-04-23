package Practica4;

public class AnalizadorArbol {
    public int devolverMaximoPromedio (ArbolGeneral<AreaEmpresa>arbol){
        int max = 0;
        if (!arbol.esVacio()){
            int cant = 0;
            int prom = 0;
            ColaGenerica<ArbolGeneral<AreaEmpresa>> cola = new ColaGenerica<>();
            ArbolGeneral<AreaEmpresa> aux;
            cola.encolar(arbol);
            cola.encolar(null);
            while (!cola.esVacia()){
                aux = cola.desencolar();
                if (aux != null){
                    cant++;
                    prom += aux.getDato().getTardanza();
                    if (aux.tieneHijos()){
                        ListaGenerica<ArbolGeneral<AreaEmpresa>> hijos = aux.getHijos();
                        hijos.comenzar();
                        while (!hijos.fin())
                             cola.encolar(hijos.proximo());
                    }
                } else {
                    prom = prom / cant;
                    max = Math.max(max, prom);
                    if (!cola.esVacia()){
                        cola.encolar(null);
                        prom = 0;
                    }
                }
            }
        }
        return max;
    }
}
