package grafos;

import java.util.ArrayList;

public class Puerto {
    private String nombre;
    private ArrayList<Arista> aristas;

    public Puerto(String nombre) {
        this.nombre = nombre;
        this.aristas = new ArrayList<Arista>();
    }

    public void agregarArista(Puerto destino, int distancia) {
        Arista arista = new Arista(this, destino, distancia);
        this.aristas.add(arista);
        destino.aristas.add(arista);
    }

    public ArrayList<Arista> getAristas() {
        return aristas;
    }

    public String getNombre() {
        return nombre;
    }
}
