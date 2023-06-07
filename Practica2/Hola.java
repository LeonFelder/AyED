package Practica2;

public class Hola {
    public static ListaDeEnterosEnlazada calcularSucesion (int n) {
        ListaDeEnterosEnlazada l;
        if (n != 1){
            if ((n % 2) == 0){
                l = calcularSucesion(n/2);
            }else{
                l = calcularSucesion((3*n)+1);
            }
            l.agregarInicio(n);
            return  l;
        } 
        else{
            l = new ListaDeEnterosEnlazada();
            return l;
        }
    }
    public static void main (String[] args){
        ListaDeEnterosEnlazada f = calcularSucesion(9);
        f.comenzar();
        while(! f.fin()){
            System.out.println(f.proximo());
        }
    }
}