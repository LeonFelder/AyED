package Practica4;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}
	
	public ListaEnlazadaGenerica<T> preOrden() {
		return null;
	}
	
	public int altura() {
            int altura = 0;
            if (!this.esVacio()){
                ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<>();
                ArbolGeneral<T> aux;
                cola.encolar(this);
                cola.encolar(null);
                while (!cola.esVacia()){
                    aux = cola.desencolar();
                    if (aux != null){
                        if (aux.tieneHijos()){
                            ListaGenerica<ArbolGeneral<T>> hijos = aux.getHijos();
                            hijos.comenzar();
                            while (!hijos.fin())
                                cola.encolar(hijos.proximo());
                        }
                    } else if (!cola.esVacia()){
                        cola.encolar(null);
                        altura++;
                    }
                }
            }
            return altura;
	}

	public int nivel(T dato) {
            int altura = 0;
            int alturaDato = -1;
            if (!this.esVacio()){
                ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<>();
                ArbolGeneral<T> aux;
                cola.encolar(this);
                cola.encolar(null);
                while (!cola.esVacia()){
                    aux = cola.desencolar();
                    if (aux != null){
                        if (aux.getDato() == dato)
                            alturaDato = altura;
                        if (aux.tieneHijos()){
                            ListaGenerica<ArbolGeneral<T>> hijos = aux.getHijos();
                            hijos.comenzar();
                            while (!hijos.fin())
                                cola.encolar(hijos.proximo());
                        }
                    } else if (!cola.esVacia()){
                        cola.encolar(null);
                        altura++;
                    }
                }
            }
            return alturaDato;
	}

	public int ancho() {
            int ancho = 0;
            if (!this.esVacio()){
                int anchoNivel = 0;
                ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<>();
                ArbolGeneral<T> aux;
                cola.encolar(this);
                cola.encolar(null);
                while (!cola.esVacia()){
                    aux = cola.desencolar();
                    if (aux != null){
                        anchoNivel++;
                        if (aux.tieneHijos()){
                            ListaGenerica<ArbolGeneral<T>> hijos = aux.getHijos();
                            hijos.comenzar();
                            while (!hijos.fin())
                                cola.encolar(hijos.proximo());
                        }
                    } else {
                        ancho = Math.max(ancho, anchoNivel);
                        if (!cola.esVacia()){
                            cola.encolar(null);
                            anchoNivel = 0;
                        }
                    }
                }
            }
            return ancho;
	}

}