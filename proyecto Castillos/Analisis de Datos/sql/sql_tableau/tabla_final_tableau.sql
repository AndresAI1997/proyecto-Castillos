CREATE OR REPLACE TABLE `proyectopruebas-474019.castillos.castillos_tableau` AS
SELECT
  id,
  nombre,
  pais,
  region,
  CAST(SPLIT(punto_geografico, ',')[OFFSET(0)] AS FLOAT64) AS latitud,
  CAST(SPLIT(punto_geografico, ',')[OFFSET(1)] AS FLOAT64) AS longitud,
  anio_construccion,
  estilo_arquitectonico,
  tipo_material,
  estado_actual,
  visitantes_anuales,
  patrimonio_unesco
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`;