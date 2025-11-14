# Castillos Objeto Java

Proyecto de consola para cargar un TSV de castillos europeos, generar subconjuntos balanceados y experimentar con filtros y agrupaciones similares al proyecto "Viajes".

## Funcionalidades principales
- **Carga balanceada**: Lee castillos_europa_10000.tsv, descarta el encabezado y toma los primeros 50 castillos distribuidos equitativamente por pais (logica implementada con asistencia de IA).
- **Modelo reutilizable**: La clase Castillo encapsula cada registro del TSV, mientras que Castillos actua como contenedor de castillos y grupos.
- **Agrupaciones**: GrupoCastillos es abstracta; sus hijas Pais, Estilo y GrupoGeneral permiten agrupar por criterios especificos. GeneradorGruposCastillos crea grupos aleatorios con limites configurables.
- **Filtros combinables**: FiltroCastillo define el contrato de filtrado. Existen filtros concretos por pais, estilo y UNESCO, ademas de combinadores FiltroAnd y FiltroOr.
- **Visualizacion**: main imprime tablas resumidas (ID, pais, estilo, UNESCO) para los filtros de pais, estilo y la combinacion AND.

## Requisitos
- Java 17 o superior (probado con la version por defecto del sistema).

## Como compilar y ejecutar
```
javac -d bin *.java
java -cp bin main
```

## Notas
- Los archivos `.class` compilados se almacenan dentro de la carpeta `bin/` para mantener limpio el directorio raiz.
- Esta base sirve como ejemplo para el portafolio del autor, mostrando manejo de colecciones, filtros combinados y generacion de grupos.
