import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface FiltroCastillo {

    boolean aplica(Castillo castillo);

    default List<Castillo> filtrar(List<Castillo> castillos) {
        Objects.requireNonNull(castillos, "La lista de castillos no puede ser nula");
        return castillos.stream()
                .filter(this::aplica)
                .collect(Collectors.toList());
    }
}
