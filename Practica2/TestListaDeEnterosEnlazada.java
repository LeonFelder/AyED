package P2E1;
import tp02.ejercicio1.*;
import java.util.Scanner;

public class TestListaDeEnterosEnlazada {
        public static void impInv (ListaDeEnteros lis){
            if(! lis.fin()){
                int ele = lis.proximo();
                impInv(lis);
                System.out.println(ele);
            }
        }
	public static void main(String[] args) {
		Scanner s;
                s = new Scanner(System.in);
		ListaDeEnterosEnlazada list= new ListaDeEnterosEnlazada();
		int elem = s.nextInt();
		while(elem != 0) {
			list.agregarFinal(elem);
			elem = s.nextInt();
		}
		list.comenzar();
		while(! list.fin()) {
			System.out.println(list.proximo());
		}
		s.close();
                list.comenzar();
                impInv(list);
	}
}
