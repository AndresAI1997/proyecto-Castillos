-- Ver primeras filas
SELECT *
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
LIMIT 100;

-- Total de castillos
SELECT COUNT(*) AS total_castillos
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`;

-- Lista de paises
SELECT DISTINCT pais
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
ORDER BY pais;