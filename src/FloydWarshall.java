import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FloydWarshall {
    public static final int INF = 99999;

    public static int[][] floydWarshallDistancias(Grafo grafo) {
        return floydWarshall(grafo, true);
    }

    public static int[][] floydWarshallTiempos(Grafo grafo) {
        return floydWarshall(grafo, false);
    }

    private static int[][] floydWarshall(Grafo grafo, boolean esDistancia) {
        int V = grafo.getAdjList().size(); // Número de vértices en el grafo
        int[][] dist = new int[V][V];
        Map<Ubicacion, Integer> ubicacionIndex = new HashMap<>();

        // Mapear cada ubicación a un índice de array
        int index = 0;
        for (Ubicacion ubicacion : grafo.getAdjList().keySet()) {
            ubicacionIndex.put(ubicacion, index++);
        }

        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // Distancia de cada vértice a sí mismo es 0
        }

        // Llenar la matriz con los pesos existentes de las aristas
        for (Ubicacion origen : grafo.getAdjList().keySet()) {
            int idOrigen = ubicacionIndex.get(origen);
            for (Ubicacion destino : grafo.getAdyacentes(origen)) {
                int idDestino = ubicacionIndex.get(destino);
                int peso = esDistancia ? grafo.getPesoDistancia(origen, destino) : grafo.getPesoTiempo(origen, destino);
                dist[idOrigen][idDestino] = peso;
            }
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }
}
