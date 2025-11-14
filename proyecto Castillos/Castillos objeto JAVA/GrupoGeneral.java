import java.util.List;

/**
 * Grupo generico que solo agrupa los castillos recibidos sin aplicar filtros adicionales.
 */
public class GrupoGeneral extends GrupoCastillos {

    public GrupoGeneral(String idGrupo, List<Castillo> castillos) {
        super(idGrupo, castillos);
    }

    @Override
    public void organizar() {
        // Este grupo no aplica ninguna organizacion adicional.
    }
}
