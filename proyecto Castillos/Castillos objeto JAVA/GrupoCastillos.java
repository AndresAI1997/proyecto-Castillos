import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Clase base para cualquier agrupacion de castillos segun diferentes reglas.
 */
public abstract class GrupoCastillos implements Castillos.Elemento {

    private final String idGrupo;
    private final String paisObjetivo;
    private final String estiloObjetivo;
    private final boolean requiereUnesco;
    private final List<Castillo> castillos;

    protected GrupoCastillos(String idGrupo, List<Castillo> castillos) {
        if (idGrupo == null || idGrupo.isBlank()) {
            throw new IllegalArgumentException("El id del grupo no puede ser nulo o vacio");
        }
        if (castillos == null) {
            throw new IllegalArgumentException("La lista de castillos no puede ser nula");
        }
        this.idGrupo = idGrupo;
        this.castillos = new ArrayList<>(castillos);
        this.paisObjetivo = calcularMasFrecuente(this.castillos, Castillo::getPais);
        this.estiloObjetivo = calcularMasFrecuente(this.castillos, Castillo::getEstiloArquitectonico);
        this.requiereUnesco = calcularUnesco(this.castillos);
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    @Override
    public String getIdElemento() {
        return idGrupo;
    }

    public String getPaisObjetivo() {
        return paisObjetivo;
    }

    public String getEstiloObjetivo() {
        return estiloObjetivo;
    }

    public boolean requiereUnesco() {
        return requiereUnesco;
    }

    public List<Castillo> getCastillos() {
        return Collections.unmodifiableList(castillos);
    }

    protected List<Castillo> getCastillosMutables() {
        return castillos;
    }

    @Override
    public boolean cumple(FiltroCastillo filtro) {
        Objects.requireNonNull(filtro, "El filtro no puede ser nulo");
        return castillos.stream().anyMatch(filtro::aplica);
    }

    /**
     * Permite a las clases hijas reorganizar, filtrar o procesar los castillos
     * segun la logica que necesiten.
     */
    public abstract void organizar();

    private String calcularMasFrecuente(List<Castillo> lista, Function<Castillo, String> extractor) {
        if (lista.isEmpty()) {
            return "N/A";
        }
        Map<String, Integer> conteo = new LinkedHashMap<>();
        for (Castillo castillo : lista) {
            String clave = extractor.apply(castillo);
            if (clave == null || clave.isBlank()) {
                continue;
            }
            conteo.merge(clave, 1, Integer::sum);
        }
        return conteo.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    private boolean calcularUnesco(List<Castillo> lista) {
        return !lista.isEmpty() && lista.stream().allMatch(Castillo::isPatrimonioUnesco);
    }
}
