public class Ubicacion {
    private int id;
    private String nombre;


    public Ubicacion(int id,String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString para imprimir información de la ubicación fácilmente
    @Override
    public String toString() {
        return nombre;
    }
}
