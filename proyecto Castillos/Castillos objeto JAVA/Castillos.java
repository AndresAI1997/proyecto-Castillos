import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Contenedor que puede almacenar tanto castillos individuales como grupos.
 */
public class Castillos {

    public interface Elemento {
        // Identificador textual que se mostrara en las tablas resumidas
        String getIdElemento();

        // Evalua si el elemento satisface un filtro dado
        boolean cumple(FiltroCastillo filtro);
    }

    private final List<Elemento> elementos = new ArrayList<>();

    public void agregarCastillo(Castillo castillo) {
        elementos.add(Objects.requireNonNull(castillo, "El castillo no puede ser nulo"));
    }

    public void agregarGrupo(GrupoCastillos grupo) {
        elementos.add(Objects.requireNonNull(grupo, "El grupo no puede ser nulo"));
    }

    public List<Elemento> getElementos() {
        return Collections.unmodifiableList(elementos);
    }

    public boolean estaVacio() {
        return elementos.isEmpty();
    }
}
