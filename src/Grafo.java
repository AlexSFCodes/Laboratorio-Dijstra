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

            String nombreActual = actual.getNombre();

            if (visitados.contains(nombreActual)) {
                continue;
            }

            visitados.add(nombreActual);

            /*
             * Recorremos los vecinos.
             */
            for (Arista arista : adyacencias.get(nombreActual)) {

                String vecino = arista.getDestino();

                int nuevoCosto = distancia.get(nombreActual)
                        + arista.getConsumo();

                if (nuevoCosto < distancia.get(vecino)) {
                    distancia.put(vecino, nuevoCosto);
                    anterior.put(vecino, nombreActual);
                    cola.add(new NodoPrioridad(vecino, nuevoCosto));
                }
            }
        }

        /*
         * Reconstrucción del camino.
         */

        LinkedList<String> ruta = new LinkedList<>();

        String nodo = destino;
        while (nodo != null) {
            ruta.addFirst(nodo);
            nodo = anterior.get(nodo);
        }

        return new ResultadoRuta(
                ruta,
                distancia.get(destino)
        );
    }
}