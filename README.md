# Laboratorio Dijkstra — Optimización de consumo energético

## Origen del proyecto

| Campo | Detalle |
|-------|---------|
| **Repositorio original** | [https://github.com/AlexSFCodes/Laboratorio-Dijstra](https://github.com/AlexSFCodes/Laboratorio-Dijstra) |
| **Autor del repositorio** | AlexSFCodes |
| **Rama principal** | `main` |
| **Copia local** | `LabDjClone/Laboratorio-Dijstra` |
| **Lenguaje** | Java 18 |
| **IDE sugerido** | IntelliJ IDEA |
| **Dependencias externas** | Ninguna (solo `java.util`) |

### ¿De dónde viene este proyecto?

Este laboratorio fue publicado en GitHub por **AlexSFCodes** como plantilla académica para implementar el **algoritmo de Dijkstra** en Java. El repositorio se clonó localmente dentro del workspace `LabDjClone` con el nombre de carpeta `Laboratorio-Dijstra`.

El objetivo pedagógico del proyecto original es que el estudiante:

1. Modele una red vial de Quito como un **grafo ponderado no dirigido**.
2. Complete los bloques `TODO` en `Grafo.java` y `NodoPrioridad.java`.
3. Responda las **preguntas de reflexión** incluidas en el código fuente.
4. Aplique Dijkstra para encontrar la ruta de **menor consumo energético (Wh)** de un vehículo eléctrico.

### Escenario del laboratorio

**Problema:** Optimizar el consumo energético de un vehículo eléctrico.

**Origen del escenario:** definido en `Main.java` (comentario de cabecera, líneas 1–8).

**Ruta a calcular:** desde **Valle de los Chillos** hasta **UDLA Park**.

**Resultado esperado (con el código completado):**

```
Ruta: [Valle de los Chillos, El Trébol, La Marín, UDLA Park]
Consumo total: 350 Wh
```

---

## Estructura del proyecto

```
Laboratorio-Dijstra/
├── src/
│   ├── Main.java           → Punto de entrada y construcción del grafo
│   ├── Grafo.java          → Lista de adyacencia + algoritmo de Dijkstra
│   ├── Arista.java         → Conexión entre vértices con peso (Wh)
│   ├── NodoPrioridad.java  → Elemento de la PriorityQueue
│   └── ResultadoRuta.java  → Ruta final y consumo total
├── LabDjstra.iml           → Configuración del módulo IntelliJ
├── .idea/                  → Configuración del IDE
└── README.md               → Este documento
```

### Clases y su origen

| Clase | Archivo | Rol |
|-------|---------|-----|
| `Main` | `src/Main.java` | Construye el grafo de Quito y ejecuta Dijkstra |
| `Grafo` | `src/Grafo.java` | Almacena vértices/aristas e implementa el algoritmo |
| `Arista` | `src/Arista.java` | Representa una vía con destino y consumo en Wh |
| `NodoPrioridad` | `src/NodoPrioridad.java` | Par (nombre, costo) para la cola de prioridad |
| `ResultadoRuta` | `src/ResultadoRuta.java` | Encapsula la ruta óptima y el consumo total |

### Grafo del laboratorio

**Vértices:** Valle de los Chillos, El Trébol, La Marín, La Carolina, Granados, UDLA Park.

**Aristas y pesos (Wh):**

| Vía | Consumo |
|-----|---------|
| Valle de los Chillos ↔ El Trébol | 120 |
| El Trébol ↔ La Marín | 80 |
| El Trébol ↔ Granados | 160 |
| La Marín ↔ La Carolina | 100 |
| La Carolina ↔ UDLA Park | 70 |
| Granados ↔ UDLA Park | 90 |
| Valle de los Chillos ↔ Granados | 300 |
| La Marín ↔ UDLA Park | 150 |

---

## Compilación y ejecución

```bash
cd src
javac *.java
java Main
```

---

## Preguntas de reflexión con respuestas

Este documento recopila todas las **preguntas de reflexión**, **discusiones** y **preguntas para el informe** que aparecen en el código fuente, indicando la **clase de origen**, la **ubicación** y la **respuesta**.

---

### `Main.java`

#### Discusión — Construcción del grafo

| | |
|---|---|
| **Origen** | `Main.java` |
| **Ubicación** | Comentario al definir los pesos de las aristas (aprox. líneas 26–32) |

**Pregunta:** ¿Qué factores reales podrían modificar estos pesos?

**Respuesta:** En la vida real, el consumo energético de un tramo no es fijo. Factores que lo modifican incluyen:

- **Pendiente del terreno:** subidas aumentan el consumo; bajadas pueden reducirlo (o incluso permitir regeneración).
- **Tráfico y semáforos:** más paradas y aceleraciones frecuentes elevan el gasto.
- **Velocidad media:** consumo óptimo a velocidades moderadas; muy rápido o muy lento suele gastar más.
- **Clima:** lluvia, viento en contra o frío extremo afectan la eficiencia.
- **Carga del vehículo:** más pasajeros o peso extra incrementan el consumo.
- **Uso de climatización:** aire acondicionado o calefacción consumen energía adicional.
- **Estado de la batería y del vehículo:** eficiencia del motor, desgaste de neumáticos, etc.
- **Tipo y estado de la vía:** asfalto en buen estado vs. vías irregulares.

Por eso los pesos del grafo son **aproximaciones** que simplifican un escenario mucho más complejo.

---

#### Preguntas para el informe

| | |
|---|---|
| **Origen** | `Main.java` |
| **Ubicación** | Comentario al final del método `main` (aprox. líneas 92–107) |

---

**Pregunta 1:** ¿La ruta con menor consumo coincide con la más corta?

**Respuesta:** **No necesariamente.** “Más corta” puede significar menos tramos o menos kilómetros, pero Dijkstra en este laboratorio minimiza el **consumo total (Wh)**, no la cantidad de aristas.

En el grafo del proyecto:
- **Valle de los Chillos → Granados → UDLA Park** tiene solo **2 tramos**, pero consume **390 Wh**.
- **Valle de los Chillos → El Trébol → La Marín → UDLA Park** tiene **3 tramos**, pero consume **350 Wh**.

La ruta óptima en consumo **no es la que tiene menos aristas**. Depende de los pesos de cada vía.

---

**Pregunta 2:** ¿Por qué una Queue tradicional no serviría para implementar este algoritmo?

**Respuesta:** Una `Queue` (cola FIFO) atiende los elementos en el **orden en que llegaron**, sin importar su costo acumulado.

Dijkstra requiere procesar **siempre primero el nodo con menor distancia conocida desde el origen**. Si usáramos una cola normal, podríamos expandir primero un nodo lejano y costoso, actualizar distancias de forma incorrecta y obtener rutas subóptimas.

El algoritmo necesita una estructura que ordene por **prioridad (menor costo)**, no por orden de llegada.

---

**Pregunta 3:** ¿Qué papel cumple la PriorityQueue?

**Respuesta:** La `PriorityQueue` actúa como una **cola de mínima prioridad** (min-heap): siempre entrega el elemento con **menor `costoAcumulado`**.

Su papel en Dijkstra es garantizar que, al extraer un nodo de la cola, ese nodo ya tiene la **distancia mínima definitiva** desde el origen (cuando todos los pesos son no negativos). Así el algoritmo puede “fijar” ese nodo y relajar correctamente a sus vecinos.

Sin ella, no se cumpliría la estrategia greedy que hace correcto a Dijkstra.

---

**Pregunta 4:** ¿Qué ocurriría si algunas vías permitieran recuperar energía (pesos negativos)?

**Respuesta:** **Dijkstra dejaría de ser válido.**

El algoritmo asume que, una vez que un nodo se procesa con la menor distancia conocida, esa distancia ya no mejorará. Con pesos negativos (por ejemplo, una bajada larga que regenera batería), podría existir un camino posterior que **reduzca aún más** el costo hasta un nodo ya “cerrado”, invalidando decisiones anteriores.

En ese caso habría que usar algoritmos como **Bellman-Ford**, que toleran pesos negativos (y detectan ciclos negativos), o replantear el modelo del grafo.

---

**Pregunta 5:** ¿Cómo modificarían el programa para considerar simultáneamente consumo, tráfico y tiempo de viaje?

**Respuesta:** Algunas opciones:

1. **Peso combinado:** definir un único peso por arista, por ejemplo: `peso = α·consumo + β·tráfico + γ·tiempo`, con coeficientes que reflejen la importancia de cada factor.
2. **Criterios múltiples:** mantener varios valores por arista y usar optimización multiobjetivo (frente de Pareto) en lugar de un solo número.
3. **Restricciones:** minimizar consumo sujeto a `tiempo ≤ X` o `tráfico ≤ Y`.
4. **Varios grafos o capas:** un grafo por criterio y combinar resultados según preferencias del conductor.

La opción más simple para este proyecto sería extender `Arista` con más atributos y calcular un peso compuesto antes de ejecutar Dijkstra.

---

### `Grafo.java`

#### Pregunta de reflexión — Conjunto de visitados

| | |
|---|---|
| **Origen** | `Grafo.java` |
| **Ubicación** | Método `dijkstra`, antes del bucle principal (aprox. líneas 81–86) |

**Pregunta:** ¿Es necesario mantener un conjunto de nodos visitados? ¿Qué ocurriría si procesamos un nodo varias veces?

**Respuesta:**

- **¿Es necesario?** No es estrictamente obligatorio si se usa otra técnica equivalente: al sacar un nodo de la cola, se puede **ignorar** la extracción si su `costoAcumulado` es mayor que `distancia.get(nodo)` (entrada obsoleta). El conjunto `visitados` evita reprocesar vecinos de un nodo ya definitivamente resuelto.
- **¿Qué pasa si procesamos varias veces?** Si se vuelve a expandir un nodo que ya tenía su distancia mínima final, se hace **trabajo redundante** (se revisan vecinos sin mejorar resultados). No suele romper el resultado si se controlan entradas obsoletas en la cola, pero **afecta la eficiencia**.

En resumen: ayuda a claridad y rendimiento; la alternativa correcta es saltar nodos cuyo costo en cola ya no es el óptimo.

---

#### Pregunta de reflexión — Nodo ya procesado

| | |
|---|---|
| **Origen** | `Grafo.java` |
| **Ubicación** | Método `dijkstra`, dentro del bucle `while`, al extraer el nodo actual (aprox. líneas 94–98) |

**Pregunta:** ¿Qué debería ocurrir si el nodo actual ya fue procesado anteriormente?

**Respuesta:** Se debe **omitir** ese nodo y continuar con la siguiente iteración del `while` (`continue`).

Esto ocurre porque la `PriorityQueue` de Java **no actualiza** la prioridad de un elemento ya insertado. Cuando encontramos un camino mejor, insertamos una **nueva copia** del nodo con menor costo; la copia antigua puede quedar en la cola. Al sacarla, si el nodo ya fue procesado, **no hay que relajar sus vecinos otra vez**.

---

#### Pregunta de reflexión — Reinsertar en la PriorityQueue

| | |
|---|---|
| **Origen** | `Grafo.java` |
| **Ubicación** | Método `dijkstra`, dentro del bucle de vecinos, al actualizar distancias (aprox. líneas 126–128) |

**Pregunta:** ¿Por qué volvemos a insertar el nodo aunque ya pudiera estar en la cola?

**Respuesta:** Porque en Java la `PriorityQueue` **no permite cambiar** la prioridad de un elemento existente de forma eficiente (no hay `decrease-key` directo).

Cuando encontramos un camino **más barato** hacia un vecino, actualizamos `distancia` y **agregamos una nueva entrada** `(vecino, nuevoCosto)` a la cola. La entrada anterior queda **obsoleta**; cuando salga, se descartará al verificar si ya fue visitado o si su costo es mayor que la distancia registrada.

Reinsertar es la forma estándar de simular “actualizar prioridad” con `PriorityQueue`.

---

### `NodoPrioridad.java`

#### Reflexión — Costo acumulado en la cola

| | |
|---|---|
| **Origen** | `NodoPrioridad.java` |
| **Ubicación** | Comentario de cabecera de la clase (aprox. líneas 4–6) |

**Pregunta:** ¿Por qué la cola necesita conocer el costo acumulado y no solamente el nombre del vértice?

**Respuesta:** Porque la decisión de **qué nodo explorar primero** depende del **costo total para llegar hasta él**, no de su nombre.

Dos vértices distintos tienen costos distintos desde el origen; el mismo vértice puede aparecer **varias veces** en la cola con costos diferentes (caminos alternativos). La cola debe ordenar por **menor costo acumulado** para que Dijkstra elija correctamente el siguiente nodo a procesar.

Solo el nombre no indica prioridad: “La Marín” no es más urgente que “El Trébol” por su etiqueta, sino por cuánto Wh costó llegar hasta allí.

---

#### Pregunta de reflexión — Orden de la PriorityQueue

| | |
|---|---|
| **Origen** | `NodoPrioridad.java` |
| **Ubicación** | Método `compareTo` (aprox. líneas 26–28) |

**Pregunta:** ¿Por qué NO queremos que el mayor costo salga primero?

**Respuesta:** Porque Dijkstra es **greedy hacia el mínimo**: debe expandir primero los nodos **más cercanos (menor costo)** al origen.

Si saliera primero el mayor costo, se procesarían rutas caras antes de rutas baratas, se fijarían distancias incorrectas y el algoritmo **no garantizaría** el camino de menor consumo.

Por eso `compareTo` ordena de forma que el **menor** `costoAcumulado` tenga prioridad (min-heap):

```java
return Integer.compare(this.costoAcumulado, otro.costoAcumulado);
```

---

## Resumen de preguntas por clase de origen

| Clase de origen | Preguntas | Tipo |
|-----------------|-----------|------|
| `Main.java` | 1 | Discusión (factores que modifican pesos) |
| `Main.java` | 5 | Preguntas para el informe |
| `Grafo.java` | 3 | Preguntas de reflexión sobre Dijkstra |
| `NodoPrioridad.java` | 2 | Preguntas sobre la PriorityQueue |
| **Total** | **12** | |

Las clases `Arista.java` y `ResultadoRuta.java` **no contienen** preguntas de reflexión; provienen del mismo repositorio como clases de soporte del modelo de datos.

---

## Implementación completada

Los bloques `TODO` de `Grafo.java` y `NodoPrioridad.java` fueron completados. La lógica principal implementada:

**`Grafo.dijkstra`:**
- Omitir nodos ya visitados con `continue`.
- Calcular `nuevoCosto = distancia.get(nombreActual) + arista.getConsumo()`.
- Relajar aristas y reinsertar en la cola cuando hay mejora.
- Reconstruir la ruta retrocediendo desde el destino con el mapa `anterior`.

**`NodoPrioridad.compareTo`:**
- Ordenar por menor `costoAcumulado` usando `Integer.compare`.

---

## Referencias

- Repositorio original: [AlexSFCodes/Laboratorio-Dijstra](https://github.com/AlexSFCodes/Laboratorio-Dijstra)
- Algoritmo: [Dijkstra (Wikipedia)](https://es.wikipedia.org/wiki/Algoritmo_de_Dijkstra)
- Estructura de datos: [PriorityQueue (Java)](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/PriorityQueue.html)
