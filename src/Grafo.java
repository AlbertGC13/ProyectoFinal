import java.util.*;

public class Grafo {
    private Map<Ubicacion, List<Arista>> adjList;
    private boolean esDirigido;

    public Grafo(boolean esDirigido) {
        this.adjList = new HashMap<>();
        this.esDirigido = esDirigido;
    }

    public void agregarUbicacion(Ubicacion ubicacion) {
        adjList.putIfAbsent(ubicacion, new ArrayList<>());
    }

    public List<Ubicacion> getAdyacentes(Ubicacion ubicacion) {
        List<Ubicacion> adyacentes = new ArrayList<>();
        List<Arista> aristas = adjList.getOrDefault(ubicacion, new ArrayList<>());
        for (Arista arista : aristas) {
            adyacentes.add(arista.getDestino());
        }
        return adyacentes;
    }

    public void agregarAristaConPeso(Ubicacion origen, Ubicacion destino, int pesoDistancia, int pesoTiempo) {
        Arista arista = new Arista(origen, destino, pesoDistancia, pesoTiempo);
        adjList.get(origen).add(arista);
        if (!esDirigido) {
            Arista aristaInversa = new Arista(destino, origen, pesoDistancia, pesoTiempo);
            adjList.get(destino).add(aristaInversa);
        }
    }

    public void removerUbicacion(Ubicacion ubicacion) {
        adjList.keySet().removeIf(u -> u.equals(ubicacion));
        adjList.values().forEach(list -> list.removeIf(arista -> arista.getOrigen().equals(ubicacion) || arista.getDestino().equals(ubicacion)));
    }

    public void removerArista(Ubicacion origen, Ubicacion destino) {
        adjList.getOrDefault(origen, new ArrayList<>()).removeIf(arista -> arista.getDestino().equals(destino));
        if (!esDirigido) {
            adjList.getOrDefault(destino, new ArrayList<>()).removeIf(arista -> arista.getDestino().equals(origen));
        }
    }

    public List<Arista> getAristasOrdenadasPorPeso() {
        List<Arista> aristas = new ArrayList<>();
        for (List<Arista> list : adjList.values()) {
            aristas.addAll(list);
        }
        aristas.sort(Comparator.comparingInt(Arista::getPesoDistancia));
        return aristas;
    }

    public Map<Ubicacion, List<Arista>> getAdjList() {
        return adjList;
    }

    public int getPesoDistancia(Ubicacion origen, Ubicacion destino) {
        return adjList.getOrDefault(origen, new ArrayList<>()).stream()
                .filter(arista -> arista.getDestino().equals(destino))
                .findFirst()
                .map(Arista::getPesoDistancia)
                .orElse(Integer.MAX_VALUE);
    }

    public int getPesoTiempo(Ubicacion origen, Ubicacion destino) {
        return adjList.getOrDefault(origen, new ArrayList<>()).stream()
                .filter(arista -> arista.getDestino().equals(destino))
                .findFirst()
                .map(Arista::getPesoTiempo)
                .orElse(Integer.MAX_VALUE);
    }
}
