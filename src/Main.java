/**
 * Laboratorio:
 * Optimización del consumo energético de un vehículo eléctrico.
 *
 * Escenario:
 * Encontrar la ruta de menor consumo desde el
 * Valle de los Chillos hasta UDLA Park.
 */
public class Main {

    public static void main(String[] args) {

        Grafo ciudad = new Grafo();

        // ===============================
        // Construcción del grafo
        // ===============================

        ciudad.agregarVertice("Valle de los Chillos");
        ciudad.agregarVertice("El Trébol");
        ciudad.agregarVertice("La Marín");
        ciudad.agregarVertice("La Carolina");
        ciudad.agregarVertice("Granados");
        ciudad.agregarVertice("UDLA Park");

        /*
         * Los pesos representan el consumo aproximado
         * en Wh para recorrer el tramo.
         *
         * Discusión:
         * ¿Qué factores reales podrían modificar estos pesos?
         */

        ciudad.agregarArista(
                "Valle de los Chillos",
                "El Trébol",
                120);

        ciudad.agregarArista(
                "El Trébol",
                "La Marín",
                80);

        ciudad.agregarArista(
                "El Trébol",
                "Granados",
                160);

        ciudad.agregarArista(
                "La Marín",
                "La Carolina",
                100);

        ciudad.agregarArista(
                "La Carolina",
                "UDLA Park",
                70);

        ciudad.agregarArista(
                "Granados",
                "UDLA Park",
                90);

        ciudad.agregarArista(
                "Valle de los Chillos",
                "Granados",
                300);

        ciudad.agregarArista(
                "La Marín",
                "UDLA Park",
                150);

        ciudad.mostrarGrafo();

        // ===============================
        // Ejecución del algoritmo
        // ===============================

        System.out.println(
                "\nCalculando la ruta de menor consumo...\n"
        );

        ResultadoRuta resultado =
                ciudad.dijkstra(
                        "Valle de los Chillos",
                        "UDLA Park"
                );

        System.out.println(resultado);

        /*
         * Preguntas para el informe:
         *
         * 1. ¿La ruta con menor consumo coincide con la más corta?
         *
         * 2. ¿Por qué una Queue tradicional no serviría para
         *    implementar este algoritmo?
         *
         * 3. ¿Qué papel cumple la PriorityQueue?
         *
         * 4. ¿Qué ocurriría si algunas vías permitieran recuperar
         *    energía (pesos negativos)?
         *
         * 5. ¿Cómo modificarían el programa para considerar
         *    simultáneamente consumo, tráfico y tiempo de viaje?
         */
    }
}