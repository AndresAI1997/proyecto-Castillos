import java.util.Objects;

public class FiltroUnesco implements FiltroCastillo {

    private final boolean requiereUnesco;

    public FiltroUnesco(boolean requiereUnesco) {
        this.requiereUnesco = requiereUnesco;
    }

    @Override
    public boolean aplica(Castillo castillo) {
        Objects.requireNonNull(castillo, "El castillo no puede ser nulo");
        return castillo.isPatrimonioUnesco() == requiereUnesco;
    }
}
