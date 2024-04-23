import java.util.HashMap;
import java.util.Map;

public class ResultadoDijkstra {
    private Map<Ubicacion, Integer> distancias = new HashMap<>();
    private Map<Ubicacion, Ubicacion> predecesores = new HashMap<>();

    public Map<Ubicacion, Integer> getDistancias() {
        return distancias;
    }

    public Map<Ubicacion, Ubicacion> getPredecesores() {
        return predecesores;
    }

    public void setDistancias(Map<Ubicacion, Integer> distancias) {
        this.distancias = distancias;
    }

    public void setPredecesores(Map<Ubicacion, Ubicacion> predecesores) {
        this.predecesores = predecesores;
    }
}
