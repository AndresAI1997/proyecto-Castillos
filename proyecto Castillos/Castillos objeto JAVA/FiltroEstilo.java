import java.util.Objects;

public class FiltroEstilo implements FiltroCastillo {

    private final String estiloObjetivo;

    public FiltroEstilo(String estiloObjetivo) {
        if (estiloObjetivo == null || estiloObjetivo.isBlank()) {
            throw new IllegalArgumentException("El estilo objetivo no puede ser nulo o vacio");
        }
        this.estiloObjetivo = estiloObjetivo;
    }

    @Override
    public boolean aplica(Castillo castillo) {
        Objects.requireNonNull(castillo, "El castillo no puede ser nulo");
        return estiloObjetivo.equalsIgnoreCase(castillo.getEstiloArquitectonico());
    }
}
