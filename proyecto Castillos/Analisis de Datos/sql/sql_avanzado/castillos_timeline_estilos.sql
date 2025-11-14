CREATE OR REPLACE TABLE `proyectopruebas-474019.castillos.timeline_estilos` AS
SELECT
  CAST(anio_construccion / 100 AS INT64) * 100 AS siglo,
  estilo_arquitectonico,
  COUNT(*) AS cantidad
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
GROUP BY siglo, estilo_arquitectonico
ORDER BY siglo;