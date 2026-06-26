import java.util.List;

/**
 * Almacena el resultado final del algoritmo.
 */
public class ResultadoRuta {

    private List<String> ruta;
    private int consumoTotal;

    public ResultadoRuta(List<String> ruta, int consumoTotal) {
        this.ruta = ruta;
        this.consumoTotal = consumoTotal;
    }

    public List<String> getRuta() {
        return ruta;
    }

    public int getConsumoTotal() {
        return consumoTotal;
    }

    @Override
    public String toString() {
        return "Ruta: " + ruta +
                "\nConsumo total: " +
                consumoTotal + " Wh";
    }
}