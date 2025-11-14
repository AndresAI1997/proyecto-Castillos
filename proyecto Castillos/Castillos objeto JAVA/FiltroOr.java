import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FiltroOr implements FiltroCastillo {

    private final List<FiltroCastillo> filtros;

    public FiltroOr(FiltroCastillo... filtros) {
        if (filtros == null || filtros.length == 0) {
            throw new IllegalArgumentException("Se requiere al menos un filtro para combinar con OR");
        }
        this.filtros = Arrays.stream(filtros)
                .map(f -> Objects.requireNonNull(f, "Los filtros no pueden contener valores nulos"))
                .collect(Collectors.toList());
    }

    @Override
    public boolean aplica(Castillo castillo) {
        Objects.requireNonNull(castillo, "El castillo no puede ser nulo");
        for (FiltroCastillo filtro : filtros) {
            if (filtro.aplica(castillo)) {
                return true;
            }
        }
        return false;
    }
}
