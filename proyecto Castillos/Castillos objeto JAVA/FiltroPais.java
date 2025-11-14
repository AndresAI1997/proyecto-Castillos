import java.util.Objects;

public class FiltroPais implements FiltroCastillo {

    private final String paisObjetivo;

    public FiltroPais(String paisObjetivo) {
        if (paisObjetivo == null || paisObjetivo.isBlank()) {
            throw new IllegalArgumentException("El pais objetivo no puede ser nulo o vacio");
        }
        this.paisObjetivo = paisObjetivo;
    }

    @Override
    public boolean aplica(Castillo castillo) {
        Objects.requireNonNull(castillo, "El castillo no puede ser nulo");
        return paisObjetivo.equalsIgnoreCase(castillo.getPais());
    }
}
