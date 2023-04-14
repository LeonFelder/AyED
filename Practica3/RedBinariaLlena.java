package Practica3;

public class RedBinariaLlena {
    public ArbolBinario arbol;
    
    public RedBinariaLlena(ArbolBinario arbol){
        this.arbol = arbol;
    }
    public int retardoReenvio() {
        return retardo(arbol);
    }
    
    private int retardo(ArbolBinario a){
        int ret = 0;
        if(!a.esHoja()){
            int hi = retardo(a.getHijoIzquierdo());
            int hd = retardo(a.getHijoDerecho());
            ret = Math.max(hi, hd);
        }
        return a.getRetardo() + ret;
    }
}
