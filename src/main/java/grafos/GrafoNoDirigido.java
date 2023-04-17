package grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GrafoNoDirigido {
    private ArrayList<Puerto> puertos;
    private ArrayList<Arista> aristas;

    public GrafoNoDirigido() {
        this.puertos = new ArrayList<Puerto>();
        this.aristas = new ArrayList<Arista>();
    }

    public void agregarPuerto(Puerto puerto) {
        this.puertos.add(puerto);
    }

    public void agregarArista(Puerto origen, Puerto destino, int distancia) {
        Arista arista = new Arista(origen, destino, distancia);
        this.aristas.add(arista);
        origen.agregarArista(destino, distancia);
        destino.agregarArista(origen, distancia);
    }

    public void eliminarPuertoConMasAristas() {
        int maxAristas = 0;
        Puerto puertoMaxAristas = null;

        for (Puerto puerto : this.puertos) {
            if (puerto.getAristas().size() > maxAristas) {
                maxAristas = puerto.getAristas().size();
                puertoMaxAristas = puerto;
            }
        }

        for (Arista arista : puertoMaxAristas.getAristas()) {
            Puerto otroPuerto = arista.getOrigen() == puertoMaxAristas ? arista.getDestino() : arista.getOrigen();
            otroPuerto.getAristas().remove(arista);
            this.aristas.remove(arista);
        }


        this.puertos.remove(puertoMaxAristas);
    }

    public void barridoEnProfundidad(Puerto origen) {
        Stack<Puerto> pila = new Stack<Puerto>();
        ArrayList<Puerto> visitados = new ArrayList<Puerto>();

        visitados.add(origen);
        pila.push(origen);

        while (!pila.empty()) {
            Puerto actual = pila.pop();
            System.out.println(actual.getNombre());

            for (Arista arista : actual.getAristas()) {
                Puerto siguiente = arista.getOrigen() == actual ? arista.getDestino() : arista.getOrigen();
                if (!visitados.contains(siguiente)) {
                    visitados.add(siguiente);
                    pila.push(siguiente);
                }
            }
        }
    }

    public void caminoMasCorto(Puerto origen, Puerto destino) {
        // Inicializar variables
        ArrayList<Puerto> visitados = new ArrayList<Puerto>();
        ArrayList<Integer> distancias = new ArrayList<Integer>();
        ArrayList<Puerto> antecesores = new ArrayList<Puerto>();

        for (Puerto puerto : this.puertos) {
            visitados.add(null);
            distancias.add(Integer.MAX_VALUE);
            antecesores.add(null);
        }

        visitados.set(this.puertos.indexOf(origen), origen);
        distancias.set(this.puertos.indexOf(origen), 0);
        antecesores.set(this.puertos.indexOf(origen), origen);

        Queue<Puerto> cola = new LinkedList<Puerto>();
        cola.add(origen);
        while (!cola.isEmpty()) {
            Puerto actual = cola.remove();
            for (Arista arista : actual.getAristas()) {
                Puerto siguiente = arista.getOrigen() == actual ? arista.getDestino() : arista.getOrigen();
                if (visitados.get(this.puertos.indexOf(siguiente)) == null) {
                    visitados.set(this.puertos.indexOf(siguiente), siguiente);
                    distancias.set(this.puertos.indexOf(siguiente), distancias.get(this.puertos.indexOf(actual)) + arista.getDistancia());
                    antecesores.set(this.puertos.indexOf(siguiente), actual);
                    cola.add(siguiente);
                }
            }
        }

        System.out.println("Camino más corto desde " + origen.getNombre() + " hasta " + destino.getNombre() + ":");
        System.out.println("Distancia: " + distancias.get(this.puertos.indexOf(destino)));
        System.out.print("Camino: ");
        Stack<Puerto> pila = new Stack<Puerto>();
        Puerto actual = destino;
        while (actual != origen) {
            pila.push(actual);
            actual = antecesores.get(this.puertos.indexOf(actual));
        }
        pila.push(origen);
        while (!pila.empty()) {
            System.out.print(pila.pop().getNombre());
            if (!pila.empty()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}