package Practica2;
import java.util.Scanner;

public class TestBalanceo {
    public static boolean Prueba (String aux){
        PilaGenerica<Character> pila= new PilaGenerica<Character>();
        for (int i = 0;i < aux.length();i++){
            char c = aux.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                pila.apilar(c);
            } else if ((c == ')') && (pila.desapilar() != '(')){
                return false;
            } else if ((c == ']') && (pila.desapilar() != '[')){
                return false;
            } else if ((c == '}') && (pila.desapilar() != '{')){
                return false;
            }
        }
        return pila.esVacia();
    }
    public static void main(String[] args) {
        String str;
        try (Scanner s = new Scanner(System.in)) {
            str = s.nextLine();
        }
        System.out.println(Prueba(str));
    }
    
}
