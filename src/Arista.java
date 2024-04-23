public class Arista implements Comparable<Arista> {
    Ubicacion origen;
    Ubicacion destino;
    Integer pesoDistancia;  // Peso para la distancia
    Integer pesoTiempo;     // Peso para el tiempo

    public Arista(Ubicacion origen, Ubicacion destino, int pesoDistancia, int pesoTiempo) {
        this.origen = origen;
        this.destino = destino;
        this.pesoDistancia = pesoDistancia;
        this.pesoTiempo = pesoTiempo;
    }

    public int compareTo(Arista otra) {
        return this.pesoDistancia.compareTo(otra.pesoDistancia);
    }

    public void setOrigen(Ubicacion origen) {
        this.origen = origen;
    }

    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    public int getPesoDistancia() {
        return pesoDistancia;
    }

    public void setPesoDistancia(int pesoDistancia) {
        this.pesoDistancia = pesoDistancia;
    }

    public int getPesoTiempo() {
        return pesoTiempo;
    }

    public void setPesoTiempo(int pesoTiempo) {
        this.pesoTiempo = pesoTiempo;
    }

    public Ubicacion getOrigen() {
        return origen;
    }

    public Ubicacion getDestino() {
        return destino;
    }

}
