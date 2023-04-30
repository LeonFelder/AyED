package Practica2;

public class PilaGenerica<T>{
    ListaEnlazadaGenerica<T> lista = new ListaEnlazadaGenerica<>();
    
    public PilaGenerica(){
        
    }
    
    public void apilar(T obj){
        this.lista.agregarInicio(obj);
    }
    
    public T desapilar (){
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
