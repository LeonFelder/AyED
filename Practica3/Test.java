package Practica3;

public class Test {

    public static void main(String[] args) {
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(87);
        ArbolBinario<Integer> a = new ArbolBinario<Integer>(20);
        arbol.setRetardo(87);
        a.setRetardo(20);
        arbol.agregarHijoIzquierdo(a);
        a = new ArbolBinario<Integer>(98);
        a.setRetardo(98);
        arbol.agregarHijoDerecho(a);
        a = new ArbolBinario<Integer>(15);
        a.setRetardo(15);
        arbol.getHijoIzquierdo().agregarHijoIzquierdo(a);
        a = new ArbolBinario<Integer>(24);
        a.setRetardo(24);
        arbol.getHijoIzquierdo().agregarHijoDerecho(a);
        a = new ArbolBinario<Integer>(84);
        a.setRetardo(84);
        arbol.getHijoDerecho().agregarHijoIzquierdo(a);
        a = new ArbolBinario<Integer>(102);
        a.setRetardo(102);
        arbol.getHijoDerecho().agregarHijoDerecho(a);
        //arbol.entreNiveles(1, 1);
        ContadorArbol c = new ContadorArbol(arbol);
        ListaEnlazadaGenerica<Integer> l = c.numerosPares();
        l.comenzar();
        //while (!l.fin())
        //    System.out.println(l.proximo());
        RedBinariaLlena red = new RedBinariaLlena(arbol);
        //System.out.println(red.retardoReenvio());
        ProfundidadDeArbolBinario prof = new ProfundidadDeArbolBinario(arbol);
        System.out.println(prof.sumaElementosProfundidad(2));
    }
    
}
