import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Grupo que mantiene castillos que comparten el mismo estilo que el primer registro.
 */
public class Estilo extends GrupoCastillos {

    public Estilo(String idGrupo, List<Castillo> castillos) {
        super(idGrupo, filtrarPorEstiloReferencia(castillos));
    }

    private static List<Castillo> filtrarPorEstiloReferencia(List<Castillo> castillos) {
        if (castillos == null || castillos.isEmpty()) {
            return Collections.emptyList();
        }

        String estiloReferencia = castillos.get(0).getEstiloArquitectonico();
        List<Castillo> filtrados = new ArrayList<>();
        for (Castillo castillo : castillos) {
            if (castillo.getEstiloArquitectonico().equals(estiloReferencia)) {
                filtrados.add(castillo);
            }
        }
        return filtrados;
    }

    @Override
    public void organizar() {
        // Sin logica adicional; el filtrado se realiza al construir la instancia.
    }
}
