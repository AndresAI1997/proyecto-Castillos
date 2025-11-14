SELECT estado_actual, COUNT(*) AS cantidad
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
GROUP BY estado_actual
ORDER BY cantidad DESC;