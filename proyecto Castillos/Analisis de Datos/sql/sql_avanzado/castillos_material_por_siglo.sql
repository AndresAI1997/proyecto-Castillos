CREATE OR REPLACE TABLE `proyectopruebas-474019.castillos.material_por_siglo` AS
SELECT
  CAST(anio_construccion / 100 AS INT64) * 100 AS siglo,
  tipo_material,
  COUNT(*) AS cantidad
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
GROUP BY siglo, tipo_material
ORDER BY siglo, cantidad DESC;