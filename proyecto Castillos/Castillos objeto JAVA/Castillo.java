import java.util.Objects;

/**
 * Representa un castillo individual presente en el TSV.
 */
public class Castillo implements Castillos.Elemento {
    private final String id;
    private final String nombre;
    private final String pais;
    private final String region;
    private final double latitud;
    private final double longitud;
    private final int anioConstruccion;
    private final String estiloArquitectonico;
    private final String tipoMaterial;
    private final String estadoActual;
    private final int visitantesAnuales;
    private final boolean patrimonioUnesco;

    public Castillo(String id, String nombre, String pais, String region,
                    double latitud, double longitud, int anioConstruccion,
                    String estiloArquitectonico, String tipoMaterial,
                    String estadoActual, int visitantesAnuales, boolean patrimonioUnesco) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.region = region;
        this.latitud = latitud;
        this.longitud = longitud;
        this.anioConstruccion = anioConstruccion;
        this.estiloArquitectonico = estiloArquitectonico;
        this.tipoMaterial = tipoMaterial;
        this.estadoActual = estadoActual;
        this.visitantesAnuales = visitantesAnuales;
        this.patrimonioUnesco = patrimonioUnesco;
    }

    @Override
    public String toString() {
        return "Castillo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", region='" + region + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", anioConstruccion=" + anioConstruccion +
                ", estiloArquitectonico='" + estiloArquitectonico + '\'' +
                ", tipoMaterial='" + tipoMaterial + '\'' +
                ", estadoActual='" + estadoActual + '\'' +
                ", visitantesAnuales=" + visitantesAnuales +
                ", patrimonioUnesco=" + patrimonioUnesco +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public String getEstiloArquitectonico() {
        return estiloArquitectonico;
    }

    public boolean isPatrimonioUnesco() {
        return patrimonioUnesco;
    }

    @Override
    public String getIdElemento() {
        return id;
    }

    @Override
    public boolean cumple(FiltroCastillo filtro) {
        Objects.requireNonNull(filtro, "El filtro no puede ser nulo");
        return filtro.aplica(this);
    }
}
