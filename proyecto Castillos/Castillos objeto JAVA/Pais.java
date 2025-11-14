import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Grupo que mantiene solo castillos del mismo pais que el primer registro de la lista.
 */
public class Pais extends GrupoCastillos {

    public Pais(String idGrupo, List<Castillo> castillos) {
        super(idGrupo, filtrarPorPaisReferencia(castillos));
    }

    private static List<Castillo> filtrarPorPaisReferencia(List<Castillo> castillos) {
        if (castillos == null || castillos.isEmpty()) {
            return Collections.emptyList();
        }

        String paisReferencia = castillos.get(0).getPais();
        List<Castillo> filtrados = new ArrayList<>();
        for (Castillo castillo : castillos) {
            if (castillo.getPais().equals(paisReferencia)) {
                filtrados.add(castillo);
            }
        }
        return filtrados;
    }

    @Override
    public void organizar() {
        // Sin logica adicional por ahora; la restriccion se aplica al construir.
    }
}
