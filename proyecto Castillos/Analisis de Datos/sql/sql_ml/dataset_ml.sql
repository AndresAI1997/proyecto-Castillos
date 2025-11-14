CREATE OR REPLACE TABLE `proyectopruebas-474019.castillos.castillos_ml` AS
SELECT
  pais,
  anio_construccion,
  visitantes_anuales,
  altura_castillo_m,
  IF(patrimonio_unesco = TRUE, 1, 0) AS unesco_label,
  CASE
    WHEN estilo_arquitectonico LIKE '%Gotico%' THEN 1
    WHEN estilo_arquitectonico LIKE '%Medieval%' THEN 2
    WHEN estilo_arquitectonico LIKE '%Renacentista%' THEN 3
    ELSE 0
  END AS estilo_label
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`;