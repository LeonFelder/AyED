package Practica2;

public class ColaGenerica<T>{
    ListaEnlazadaGenerica<T> lista = new ListaEnlazadaGenerica<>();
    
    public void encolar(T obj){
        this.lista.agregarFinal(obj);
    }
    
    public T desencolar (){
        T obj = this.lista.elemento(1);
        this.lista.eliminarEn(1);
        return obj;
    }
    
    public T tope (){
        return this.lista.elemento(1);
    }
    
    public boolean esVacia (){
        return lista.esVacia();
    }
}
