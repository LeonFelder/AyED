package Practica4;
import Practica2.ListaGenerica;

public class RedAguaPotable {
    private ArbolGeneral<String> arbol = new ArbolGeneral<String>("Gonnet");
    
    public double minimoCaudal (double caudal){
        double min = Double.MAX_VALUE;
        if (!arbol.esVacio()){
            min = minimoCaudal(arbol,caudal);
        }
        return min;
    }
    
    private double minimoCaudal (ArbolGeneral<String> a,double caudal){
        double min = caudal;
        if (!a.esHoja()){ 
            ListaGenerica<ArbolGeneral<String>> hijos = a.getHijos();
            double caudalH = caudal / hijos.tamanio();
            hijos.comenzar();
            while (!hijos.fin())
                min = Math.min(min, minimoCaudal(hijos.proximo(),caudalH));
        }
        return min;
    }
}
