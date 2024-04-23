import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Grafo grafo;
    public static void main(String[] args) {

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nBienvenido al Sistema de Gestión de Rutas.");
            System.out.println("1. Cargar grafo predeterminado.");
            System.out.println("2. Crear un nuevo grafo.");
            System.out.println("3. Gestionar ubicaciones.");
            System.out.println("4. Calcular ruta más corta entre dos ubicaciones.");
            System.out.println("5. Encontrar arbol de expansion minima.");
            System.out.println("6. Optimizacion de rutas.");
            System.out.println("7. Salir.");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    grafo = cargarGrafoPredeterminado();
                    break;
                case 2:
                    grafo = new Grafo(false);
                    crearGrafoInteractivamente();
                    break;
                case 3:
                    if (grafo != null) {
                        gestionarUbicaciones();
                    } else {
                        System.out.println("\nPrimero debe cargar o crear un grafo.");
                    }
                    break;
                case 4:
                    if (grafo != null) {
                        ejecutarDijkstra();
                    } else {
                        System.out.println("\nPrimero debe cargar o crear un grafo.");
                    }
                    break;
                case 5:
                    if (grafo != null) {
                        ejecutarAlgoritmosMST();
                    } else {
                        System.out.println("\nPrimero debe cargar o crear un grafo.");
                    }
                    break;
                case 6:
                    if (grafo != null) {
                        ejecutarFloydWarshall();
                    } else {
                        System.out.println("\nPrimero debe cargar o crear un grafo.");
                    }
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Gracias por usar el sistema. Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private static void ejecutarAlgoritmosMST() {
        System.out.println("\nSeleccione el algoritmo para encontrar el Árbol de Expansión Mínima:");
        System.out.println("1. Algoritmo de Prim");
        System.out.println("2. Algoritmo de Kruskal");
        System.out.print("Elija una opción: ");
        int eleccion = scanner.nextInt();
        switch (eleccion) {
            case 1:
                ejecutarPrim();
                break;
            case 2:
                ejecutarKruskal();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void ejecutarDijkstra() {
        System.out.println("Ingrese el ID de la ubicación de inicio:");
        int idInicio = scanner.nextInt();
        System.out.println("Ingrese el ID de la ubicación de destino:");
        int idDestino = scanner.nextInt();

        Ubicacion inicio = obtenerUbicacionPorId(grafo, idInicio);
        Ubicacion destino = obtenerUbicacionPorId(grafo, idDestino);

        if (inicio != null && destino != null) {
            ResultadoDijkstra resultado = Dijkstra.ejecutar(grafo, inicio);
            int distancia = resultado.getDistancias().getOrDefault(destino, Integer.MAX_VALUE);
            if (distancia == Integer.MAX_VALUE) {
                System.out.println("No hay ruta disponible entre " + inicio.getNombre() + " y " + destino.getNombre());
            } else {
                System.out.println("La distancia más corta entre " + inicio.getNombre() + " y " + destino.getNombre() + " es " + distancia + "km");
                mostrarRuta(resultado.getPredecesores(), destino);
            }
        } else {
            System.out.println("Una o ambas ubicaciones no existen.");
        }
    }
    private static void ejecutarPrim() {
        System.out.println("Ingrese el ID de la ubicación inicial para el árbol de expansión mínima:");
        int idInicio = scanner.nextInt();
        Ubicacion inicio = obtenerUbicacionPorId(grafo, idInicio);

        if (inicio != null) {
            Set<Ubicacion> mst = Prim.prim(grafo, inicio);
            System.out.println("Árbol de expansión mínima usando Prim desde " + inicio.getNombre() + ":");
            for (Ubicacion u : mst) {
                System.out.println(u.getNombre());
            }
        } else {
            System.out.println("Ubicación no encontrada.");
        }
    }

    private static void ejecutarKruskal() {
        List<Arista> mst = Kruskal.kruskal(grafo);
        System.out.println("\nÁrbol de expansión mínima usando Kruskal:");
        for (Arista arista : mst) {
            System.out.println(arista.getOrigen().getNombre() + " - " + arista.getDestino().getNombre() + " (" + arista.getPesoDistancia() + ")");
        }
    }

    private static void ejecutarFloydWarshall() {
        System.out.println("Seleccione la métrica para optimizar las rutas:");
        System.out.println("1. Distancia");
        System.out.println("2. Tiempo");
        System.out.print("Elija una opción: ");
        int metrica = scanner.nextInt();

        int[][] distancias;
        if (metrica == 1) {
            distancias = FloydWarshall.floydWarshallDistancias(grafo);
        } else {
            distancias = FloydWarshall.floydWarshallTiempos(grafo);
        }

        System.out.println("Matriz de distancias mínimas entre todas las ubicaciones:");
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias[i].length; j++) {
                System.out.print((distancias[i][j] == FloydWarshall.INF ? "INF" : distancias[i][j]) + " ");
            }
            System.out.println();
        }
    }



    private static void mostrarRuta(Map<Ubicacion, Ubicacion> predecesores, Ubicacion destino) {
        if (!predecesores.containsKey(destino) || predecesores.get(destino) == null) {
            System.out.println("No se puede alcanzar el destino desde el inicio especificado.");
            return;
        }
        List<Ubicacion> ruta = new ArrayList<>();
        for (Ubicacion at = destino; at != null; at = predecesores.get(at)) {
            ruta.add(at);
        }
        Collections.reverse(ruta);
        System.out.print("Ruta: ");
        ruta.forEach(u -> System.out.print(u.getNombre() + " -> "));
        System.out.println("fin.");
    }


    public static void crearGrafoInteractivamente() {
        boolean agregarMas = true;
        while (agregarMas) {
            System.out.println("Agregar Ubicación(1), Arista(2), Volver al menú principal(3)");
            int eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1:
                    agregarUbicacionManualmente(grafo, scanner);
                    break;
                case 2:
                    agregarAristaManualmente(grafo, scanner);
                    break;
                case 3:
                    agregarMas = false; // Esto permitirá volver al menú principal
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public static void agregarUbicacionManualmente(Grafo grafo, Scanner scanner) {
        System.out.print("Ingrese el ID de la nueva ubicación: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume la línea nueva restante
        System.out.print("Ingrese el nombre de la nueva ubicación: ");
        String nombre = scanner.nextLine();
        grafo.agregarUbicacion(new Ubicacion(id, nombre));
    }

    public static void agregarAristaManualmente(Grafo grafo, Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación de origen: ");
        int origenId = scanner.nextInt();
        System.out.print("Ingrese el ID de la ubicación de destino: ");
        int destinoId = scanner.nextInt();
        System.out.print("Ingrese el peso de la arista (distancia): ");
        int pesoDistancia = scanner.nextInt();
        System.out.print("Ingrese el peso de la arista (tiempo): ");
        int pesoTiempo = scanner.nextInt();

        Ubicacion origen = obtenerUbicacionPorId(grafo, origenId);
        Ubicacion destino = obtenerUbicacionPorId(grafo, destinoId);
        if (origen != null && destino != null) {
            grafo.agregarAristaConPeso(origen, destino, pesoDistancia, pesoTiempo);
            System.out.println("Arista agregada entre " + origen.getNombre() + " y " + destino.getNombre() + " con peso distancia: " + pesoDistancia + " y peso tiempo: " + pesoTiempo);
        } else {
            System.out.println("Una de las ubicaciones especificadas no existe.");
        }
    }

    public static void gestionarUbicaciones() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nGestión de Ubicaciones:");
            System.out.println("1. Ver ubicaciones");
            System.out.println("2. Agregar nueva ubicación");
            System.out.println("3. Editar ubicación");
            System.out.println("4. Eliminar ubicación");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1:
                    visualizarUbicaciones(grafo);
                    break;
                case 2:
                    agregarUbicacionManualmente(grafo, scanner);
                    break;
                case 3:
                    editarUbicacion(grafo, scanner);
                    break;
                case 4:
                    eliminarUbicacion(grafo, scanner);
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public static Ubicacion obtenerUbicacionPorId(Grafo grafo, int id) {

        for (Ubicacion ubicacion : grafo.getAdjList().keySet()) {
            if (ubicacion.getId() == id) {
                return ubicacion;
            }
        }
        return null;
    }

    public static void visualizarUbicaciones(Grafo grafo) {
        System.out.println("\n\nUbicaciones actuales en el grafo:");
        for (Ubicacion ubicacion : grafo.getAdjList().keySet()) {
            System.out.println("ID: " + ubicacion.getId() + ", Nombre: " + ubicacion.getNombre());
        }
    }

    public static void editarUbicacion(Grafo grafo, Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Ubicacion ubicacion = obtenerUbicacionPorId(grafo, id);
        if (ubicacion != null) {
            System.out.print("Ingrese el nuevo nombre para la ubicación '" + ubicacion.getNombre() + "': ");
            String nuevoNombre = scanner.nextLine();
            ubicacion.setNombre(nuevoNombre);
            System.out.println("Ubicación actualizada exitosamente.");
        } else {
            System.out.println("Ubicación no encontrada.");
        }
    }

    public static void eliminarUbicacion(Grafo grafo, Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación a eliminar: ");
        int id = scanner.nextInt();
        Ubicacion ubicacion = obtenerUbicacionPorId(grafo, id);
        if (ubicacion != null) {
            grafo.removerUbicacion(ubicacion);
            System.out.println("Ubicación eliminada exitosamente.");
        } else {
            System.out.println("Ubicación no encontrada.");
        }
    }


    public static Grafo cargarGrafoPredeterminado() {
        Grafo grafo = new Grafo(true); // false si es un grafo no dirigido, true si es dirigido

        // Crear ubicaciones (nodos)
        Ubicacion ubicacion1 = new Ubicacion(1, "Casa");
        Ubicacion ubicacion2 = new Ubicacion(2, "Negocio");
        Ubicacion ubicacion3 = new Ubicacion(3, "Universidad");
        Ubicacion ubicacion4 = new Ubicacion(4, "Bravo");
        Ubicacion ubicacion5 = new Ubicacion(5, "Casa Abuela");
        Ubicacion ubicacion6 = new Ubicacion(6, "Gym");
        Ubicacion ubicacion7 = new Ubicacion(7, "Parque");
        Ubicacion ubicacion8 = new Ubicacion(8, "Zoologico");
        Ubicacion ubicacion9 = new Ubicacion(9, "Estadio Cibao");
        Ubicacion ubicacion10 = new Ubicacion(10, "Plaza Internacional");
        Ubicacion ubicacion11 = new Ubicacion(11,"Square One");

        // Agregar ubicaciones al grafo
        grafo.agregarUbicacion(ubicacion1);
        grafo.agregarUbicacion(ubicacion2);
        grafo.agregarUbicacion(ubicacion3);
        grafo.agregarUbicacion(ubicacion4);
        grafo.agregarUbicacion(ubicacion5);
        grafo.agregarUbicacion(ubicacion6);
        grafo.agregarUbicacion(ubicacion7);
        grafo.agregarUbicacion(ubicacion8);
        grafo.agregarUbicacion(ubicacion9);
        grafo.agregarUbicacion(ubicacion10);
        grafo.agregarUbicacion(ubicacion11);


        // Crear aristas entre ubicaciones
        grafo.agregarAristaConPeso(ubicacion1, ubicacion4, 13,20);
        grafo.agregarAristaConPeso(ubicacion1, ubicacion7, 2, 5);
        grafo.agregarAristaConPeso(ubicacion2, ubicacion1, 1, 5);
        grafo.agregarAristaConPeso(ubicacion3, ubicacion2, 2, 5);
        grafo.agregarAristaConPeso(ubicacion3, ubicacion1, 25, 45);
        grafo.agregarAristaConPeso(ubicacion3, ubicacion5, 30,50);
        grafo.agregarAristaConPeso(ubicacion5, ubicacion6, 4, 10);
        grafo.agregarAristaConPeso(ubicacion5, ubicacion2, 5, 10);
        grafo.agregarAristaConPeso(ubicacion5, ubicacion8, 14,20);
        grafo.agregarAristaConPeso(ubicacion6, ubicacion3, 11,20);
        grafo.agregarAristaConPeso(ubicacion6, ubicacion9, 9, 15);
        grafo.agregarAristaConPeso(ubicacion7, ubicacion4, 12, 20);
        grafo.agregarAristaConPeso(ubicacion7, ubicacion10, 8, 15);
        grafo.agregarAristaConPeso(ubicacion7, ubicacion6, 17, 25);
        grafo.agregarAristaConPeso(ubicacion8, ubicacion9, 3, 5);
        grafo.agregarAristaConPeso(ubicacion8, ubicacion11, 6, 10);
        grafo.agregarAristaConPeso(ubicacion9, ubicacion5, 15,25);
        grafo.agregarAristaConPeso(ubicacion10, ubicacion9, 8,15);
        grafo.agregarAristaConPeso(ubicacion11, ubicacion10, 7, 15);


        return grafo;
    }


}
