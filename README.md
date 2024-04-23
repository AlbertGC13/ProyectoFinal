# Sistema de Gestión de Rutas

## Descripción
El Sistema de Gestión de Rutas es una aplicación desarrollada en Java que permite calcular y optimizar rutas entre diferentes ubicaciones. Utiliza algoritmos clásicos de teoría de grafos como Dijkstra, Prim, Kruskal y Floyd-Warshall para gestionar ubicaciones y encontrar las rutas más eficientes basadas en distancia o tiempo.

## Funcionalidades
El sistema ofrece las siguientes funcionalidades:

### Gestión de Ubicaciones
- **Agregar Ubicaciones**: Añade nuevas ubicaciones al sistema, que representan nodos en un grafo.
- **Editar Ubicaciones**: Modifica los detalles de las ubicaciones existentes.
- **Eliminar Ubicaciones**: Elimina ubicaciones del sistema.

### Cálculo de Rutas
- **Dijkstra**: Calcula la ruta más corta entre dos ubicaciones específicas, ideal para encontrar caminos rápidos en tiempo real.
- **Prim y Kruskal**: Implementa estos algoritmos para generar árboles de expansión mínima, útiles para planificar infraestructura y rutas base.

### Optimización de Rutas
- **Floyd-Warshall**: Encuentra las distancias más cortas entre todas las parejas de ubicaciones, permitiendo una visión global de las conexiones entre ubicaciones.
- **Planificación Personalizada**: Permite a los usuarios seleccionar si desean optimizar las rutas basadas en distancia o tiempo, adecuándose a diversas necesidades logísticas.

## Estructura del Proyecto
El proyecto se organiza en varios paquetes:
- `main`: Contiene la clase principal que ejecuta la aplicación.
- `grafos`: Incluye las clases que manejan la lógica de grafos, como los nodos (ubicaciones) y aristas.
- `algoritmos`: Aloja las implementaciones de los algoritmos de Dijkstra, Prim, Kruskal y Floyd-Warshall.
- `util`: Proporciona utilidades adicionales como manejadores de entrada/salida y formatos de datos.

## Cómo Usar
Para ejecutar el sistema:
1. Compilar el código fuente usando un IDE de Java o mediante línea de comandos.
2. Ejecutar la clase principal. El sistema pedirá interacciones a través de la consola para elegir entre cargar un grafo predeterminado o crear uno nuevo y para ejecutar operaciones sobre el grafo.
