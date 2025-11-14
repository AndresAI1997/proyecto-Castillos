import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Crea grupos de castillos utilizando una cantidad aleatoria de elementos.
 * Puede generar tanto grupos generales como instancias de clases hijas de GrupoCastillos.
 */
public class GeneradorGruposCastillos {

    private final List<Castillo> disponibles;
    private final Random random;

    public GeneradorGruposCastillos(List<Castillo> castillos) {
        this(castillos, new Random());
    }

    public GeneradorGruposCastillos(List<Castillo> castillos, Random random) {
        Objects.requireNonNull(castillos, "La lista de castillos no puede ser nula");
        this.disponibles = new ArrayList<>(castillos);
        this.random = Objects.requireNonNull(random, "El generador aleatorio no puede ser nulo");
    }

    public GrupoGeneral crearGrupoGeneral(String idGrupo, int maxElementos) {
        return new GrupoGeneral(idGrupo, seleccionarAleatorios(maxElementos));
    }

    public Pais crearGrupoPais(String idGrupo, int maxElementos) {
        return new Pais(idGrupo, seleccionarAleatorios(maxElementos));
    }

    public Estilo crearGrupoEstilo(String idGrupo, int maxElementos) {
        return new Estilo(idGrupo, seleccionarAleatorios(maxElementos));
    }

    private List<Castillo> seleccionarAleatorios(int maxElementos) {
        if (disponibles.isEmpty()) {
            return Collections.emptyList();
        }

        int limite = maxElementos <= 0 ? disponibles.size() : Math.min(maxElementos, disponibles.size());
        int cantidad = random.nextInt(limite) + 1; // al menos 1 elemento

        List<Castillo> copia = new ArrayList<>(disponibles);
        Collections.shuffle(copia, random);
        return new ArrayList<>(copia.subList(0, cantidad));
    }
}
