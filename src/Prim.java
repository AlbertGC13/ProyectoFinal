import java.util.*;

public class Prim {
    public static Set<Ubicacion> prim(Grafo grafo, Ubicacion inicio) {
        Set<Ubicacion> mstVertices = new HashSet<>();
        Map<Ubicacion, Integer> clave = new HashMap<>();
        PriorityQueue<Ubicacion> pq = new PriorityQueue<>(Comparator.comparing(clave::get));
        Map<Ubicacion, Ubicacion> predecesores = new HashMap<>();


        for (Ubicacion u : grafo.getAdjList().keySet()) {
            clave.put(u, Integer.MAX_VALUE);
            predecesores.put(u, null);
        }
        clave.put(inicio, 0);
        pq.add(inicio);

        while (!pq.isEmpty()) {
            Ubicacion actual = pq.poll();
            mstVertices.add(actual);
            for (Ubicacion adyacente : grafo.getAdyacentes(actual)) {
                int peso = grafo.getPesoDistancia(actual, adyacente);
                if (!mstVertices.contains(adyacente) && peso < clave.get(adyacente)) {
                    clave.put(adyacente, peso);
                    pq.add(adyacente);
                    predecesores.put(adyacente, actual);
                }
            }
        }


        return mstVertices;
    }
}
