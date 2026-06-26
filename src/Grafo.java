import java.util.*;

/**
 * Grafo ponderado no dirigido.
 *
 * Cada vértice representa un punto importante de Quito.
 * Cada arista representa una vía.
 * El peso representa el consumo energético aproximado.
 */
public class Grafo {

    // Lista de adyacencia
    private Map<String, List<Arista>> adyacencias;

    public Grafo() {
        adyacencias = new HashMap<>();
    }

    /**
     * Agrega un nuevo vértice al grafo.
     */
    public void agregarVertice(String nombre) {
        adyacencias.putIfAbsent(nombre, new ArrayList<>());
    }

    /**
     * Agrega una arista bidireccional.
     */
    public void agregarArista(String origen,
                              String destino,
                              int consumo) {

        adyacencias.get(origen)
                .add(new Arista(destino, consumo));

        adyacencias.get(destino)
                .add(new Arista(origen, consumo));
    }

    /**
     * Muestra el grafo.
     */
    public void mostrarGrafo() {

        System.out.println("\n=== RED VIAL ===");

        for (String nodo : adyacencias.keySet()) {
            System.out.println(nodo + " -> " +
                    adyacencias.get(nodo));
        }
    }

    /**
     * Implementación del algoritmo de Dijkstra.
     */
    public ResultadoRuta dijkstra(String origen,
                                  String destino) {

        // Distancia mínima conocida a cada nodo.
        Map<String, Integer> distancia = new HashMap<>();

        // Permite reconstruir el camino.
        Map<String, String> anterior = new HashMap<>();

        // Cola de prioridad.
        PriorityQueue<NodoPrioridad> cola =
                new PriorityQueue<>();

        /*
         * Inicialización.
         */

        for (String nodo : adyacencias.keySet()) {
            distancia.put(nodo, Integer.MAX_VALUE);
        }

        distancia.put(origen, 0);

        cola.add(new NodoPrioridad(origen, 0));

        /*
         * Pregunta de reflexión:
         *
         * ¿Es necesario mantener un conjunto de nodos visitados?
         * ¿Qué ocurriría si procesamos un nodo varias veces?
         */

        Set<String> visitados = new HashSet<>();

        while (!cola.isEmpty()) {

            NodoPrioridad actual = cola.poll();

            /*
             * TODO:
             * ¿Qué debería ocurrir si el nodo actual
             * ya fue procesado anteriormente?
             */

            String nombreActual = actual.getNombre();

            visitados.add(nombreActual);

            /*
             * Recorremos los vecinos.
             */
            for (Arista arista : adyacencias.get(nombreActual)) {

                String vecino = arista.getDestino();

                /*
                 * TODO:
                 * Calcular el costo alternativo para llegar
                 * al vecino pasando por el nodo actual.
                 */

                int nuevoCosto = 0; // Reemplazar.

                /*
                 * TODO:
                 * Si encontramos una ruta mejor:
                 *   1. Actualizar la distancia.
                 *   2. Registrar el predecesor.
                 *   3. Insertar el vecino en la PriorityQueue.
                 *
                 * Pregunta:
                 * ¿Por qué volvemos a insertar el nodo
                 * aunque ya pudiera estar en la cola?
                 */

            }
        }

        /*
         * Reconstrucción del camino.
         */

        LinkedList<String> ruta = new LinkedList<>();

        /*
         * TODO:
         * Reconstruir la ruta utilizando el mapa
         * 'anterior'.
         *
         * Pista:
         * Comienza desde el destino y vaya retrocediendo
         * hasta llegar al origen.
         */

        return new ResultadoRuta(
                ruta,
                distancia.get(destino)
        );
    }
}