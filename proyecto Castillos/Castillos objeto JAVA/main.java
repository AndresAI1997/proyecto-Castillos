import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class main {

    private static final int LIMITE_CASTILLOS = 50; // tamaño de muestra balanceada
    private static final int TAMANO_MAX_GRUPO = 10; // limite superior para la generacion de grupos

    public static void main(String[] args) {
        String rutaArchivo = "castillos_europa_10000.tsv";
        List<Castillo> castillos = cargarCastillos(rutaArchivo); // carga balanceada de registros

        if (castillos.isEmpty()) {
            System.out.println("No se pudieron cargar castillos desde el archivo.");
            return;
        }

        System.out.println("Total de castillos cargados: " + castillos.size());

        Castillos contenedor = new Castillos(); // almacena castillos individuales y grupos
        castillos.forEach(contenedor::agregarCastillo);

        GeneradorGruposCastillos generador = new GeneradorGruposCastillos(castillos);
        GrupoGeneral grupoGeneral = generador.crearGrupoGeneral("GRP-GEN", TAMANO_MAX_GRUPO);
        Pais grupoPais = generador.crearGrupoPais("GRP-PAIS", TAMANO_MAX_GRUPO);
        Estilo grupoEstilo = generador.crearGrupoEstilo("GRP-ESTILO", TAMANO_MAX_GRUPO);

        contenedor.agregarGrupo(grupoGeneral);
        contenedor.agregarGrupo(grupoPais);
        contenedor.agregarGrupo(grupoEstilo);

        Castillo referencia = castillos.get(0);
        FiltroCastillo filtroPais = new FiltroPais(referencia.getPais()); // mismo pais que el primero
        FiltroCastillo filtroEstilo = new FiltroEstilo(referencia.getEstiloArquitectonico()); // mismo estilo
        FiltroCastillo filtroCombinado = new FiltroAnd(filtroPais, filtroEstilo); // interseccion

        imprimirTablaFiltrada("Filtro por pais: " + referencia.getPais(), contenedor, filtroPais);
        imprimirTablaFiltrada("Filtro por estilo: " + referencia.getEstiloArquitectonico(), contenedor, filtroEstilo);
        imprimirTablaFiltrada("Filtro combinado (pais + estilo)", contenedor, filtroCombinado);
    }

    private static List<Castillo> cargarCastillos(String rutaArchivo) {
        Map<String, Queue<Castillo>> castillosPorPais = new LinkedHashMap<>();
        Path path = Path.of(rutaArchivo);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String linea = reader.readLine(); // Sección generada con asistencia de IA: descarta encabezado y prepara parsing

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\t");
                if (partes.length != 12) {
                    continue;
                }

                String pais = partes[2];
                Castillo castillo = new Castillo(
                        partes[0],
                        partes[1],
                        pais,
                        partes[3],
                        Double.parseDouble(partes[4]),
                        Double.parseDouble(partes[5]),
                        Integer.parseInt(partes[6]),
                        partes[7],
                        partes[8],
                        partes[9],
                        Integer.parseInt(partes[10]),
                        Boolean.parseBoolean(partes[11])
                );

                castillosPorPais
                        .computeIfAbsent(pais, p -> new ArrayDeque<>())
                        .add(castillo);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return seleccionarBalanceados(castillosPorPais, LIMITE_CASTILLOS);
    }

    private static List<Castillo> seleccionarBalanceados(Map<String, Queue<Castillo>> castillosPorPais,
                                                         int limite) {
        List<Castillo> seleccionados = new ArrayList<>(limite); // almacenamiento circular estilo round-robin
        boolean agregoEnRonda = true;

        while (seleccionados.size() < limite && agregoEnRonda) { // recorre paises agregando de a uno
            agregoEnRonda = false;

            for (Map.Entry<String, Queue<Castillo>> entry : castillosPorPais.entrySet()) {
                if (seleccionados.size() >= limite) {
                    break;
                }

                Queue<Castillo> cola = entry.getValue();
                Castillo castillo = cola.poll();
                if (castillo != null) {
                    seleccionados.add(castillo);
                    agregoEnRonda = true;
                }
            }
        }

        return seleccionados;
    }

    private static void imprimirTablaFiltrada(String titulo, Castillos contenedor, FiltroCastillo filtro) {
        System.out.println("\n" + titulo); // encabezado de cada tabla

        List<Castillos.Elemento> filtrados = new ArrayList<>();
        for (Castillos.Elemento elemento : contenedor.getElementos()) {
            if (elemento.cumple(filtro)) {
                filtrados.add(elemento);
            }
        }

        imprimirTablaResumen(filtrados);
    }

    private static void imprimirTablaResumen(List<Castillos.Elemento> elementos) {
        String separador = "+------------+--------------+----------------------+--------+";
        String formato = "| %-10s | %-12s | %-20s | %-6s |%n";

        System.out.println(separador);
        System.out.printf(formato, "ID", "Pais", "Estilo", "UNESCO");
        System.out.println(separador); // separador superior

        for (Castillos.Elemento elemento : elementos) {
            System.out.printf(
                    formato,
                    elemento.getIdElemento(),
                    truncar(obtenerPais(elemento), 12),
                    truncar(obtenerEstilo(elemento), 20),
                    obtenerUnesco(elemento) ? "Si" : "No"
            );
        }

        System.out.println(separador); // separador inferior
    }

    private static String obtenerPais(Castillos.Elemento elemento) {
        if (elemento instanceof Castillo castillo) {
            return castillo.getPais();
        }
        if (elemento instanceof GrupoCastillos grupo) {
            return grupo.getPaisObjetivo();
        }
        return "N/A";
    }

    private static String obtenerEstilo(Castillos.Elemento elemento) {
        if (elemento instanceof Castillo castillo) {
            return castillo.getEstiloArquitectonico();
        }
        if (elemento instanceof GrupoCastillos grupo) {
            return grupo.getEstiloObjetivo();
        }
        return "N/A";
    }

    private static boolean obtenerUnesco(Castillos.Elemento elemento) {
        if (elemento instanceof Castillo castillo) {
            return castillo.isPatrimonioUnesco();
        }
        if (elemento instanceof GrupoCastillos grupo) {
            return grupo.requiereUnesco();
        }
        return false;
    }

    private static String truncar(String texto, int maxCaracteres) {
        if (texto == null) {
            return "N/A";
        }
        if (texto.length() <= maxCaracteres) {
            return texto;
        }
        return texto.substring(0, Math.max(0, maxCaracteres - 3)) + "...";
    }
}
