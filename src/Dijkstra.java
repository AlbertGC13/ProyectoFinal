import java.util.*;

public class Dijkstra {

    public static ResultadoDijkstra ejecutar(Grafo grafo, Ubicacion origen) {
        ResultadoDijkstra resultado = new ResultadoDijkstra();
        PriorityQueue<Ubicacion> colaPrioridad = new PriorityQueue<>(
                Comparator.comparingInt(u -> resultado.getDistancias().getOrDefault(u, Integer.MAX_VALUE))
        );

        Map<Ubicacion, Integer> distancias = resultado.getDistancias();
        Map<Ubicacion, Ubicacion> predecesores = resultado.getPredecesores();

        distancias.put(origen, 0);
        colaPrioridad.add(origen);

        while (!colaPrioridad.isEmpty()) {
            Ubicacion actual = colaPrioridad.poll();

            for (Ubicacion adyacente : grafo.getAdyacentes(actual)) {
                int distanciaActual = distancias.get(actual) + grafo.getPesoDistancia(actual, adyacente);
                if (distanciaActual < distancias.getOrDefault(adyacente, Integer.MAX_VALUE)) {
                    distancias.put(adyacente, distanciaActual);
                    predecesores.put(adyacente, actual);
                    colaPrioridad.add(adyacente);
                }
            }
        }

        return resultado;
    }
}
