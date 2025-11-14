SELECT estilo_arquitectonico, COUNT(*) AS cantidad
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
GROUP BY estilo_arquitectonico
ORDER BY cantidad DESC;