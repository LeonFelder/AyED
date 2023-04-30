package Practica3;
import Practica2.ColaGenerica;

public class ArbolBinario<T> {
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;   
	private ArbolBinario<T> hijoDerecho; 
        private int retardo;
	
	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * Preguntar antes de invocar si tieneHijoIzquierdo()
	 * @return
	 */
	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}
        
        public int getRetardo(){
            return this.retardo;
        }
        
        public void setRetardo(int retardo){
            this.retardo = retardo;
        }
        
	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	 
	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo!=null;
	}

	 
	public boolean tieneHijoDerecho() {
		return this.hijoDerecho!=null;
	}

	public int contarHojas() {
		return 0;
	}
	

        public ArbolBinario<T> espejo() {
		
		return null;
	}


	public void entreNiveles(int n, int m){
            int nivel = 0;
            ArbolBinario<T> arbol = null;
            ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
            cola.encolar(this) ;
            cola.encolar(null);
            while (!cola.esVacia()) {
                arbol = cola.desencolar();
                if (arbol != null) {
                    if (nivel >= n  && nivel <= m) 
                        System.out.print(arbol.getDato());
                    if (arbol.tieneHijoIzquierdo())
                        cola.encolar (arbol.getHijoIzquierdo());
                    if (arbol.tieneHijoDerecho())
                        cola.encolar(arbol.getHijoDerecho());
                } else if (!cola.esVacia()) {
                    System.out.println();
                    cola.encolar(null) ;
                    nivel++;
                }
            }
        }

}
