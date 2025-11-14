SELECT pais, COUNT(*) AS cantidad
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
GROUP BY pais
ORDER BY cantidad DESC;