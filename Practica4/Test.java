package Practica4;
import Practica2.ListaEnlazadaGenerica;
import Practica2.ListaGenerica;

public class Test {

    public static void main(String[] args) {
        ListaGenerica<String> lista = new ListaEnlazadaGenerica<>();
        Integer dato = 45;
        Integer nivel = 80;
        lista.agregarFinal(dato+" nivel "+nivel);
        lista.comenzar();
        System.out.println(lista.proximo());
    }
    
}
