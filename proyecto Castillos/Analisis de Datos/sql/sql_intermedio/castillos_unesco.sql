SELECT patrimonio_unesco, COUNT(*) AS cantidad
FROM `proyectopruebas-474019.castillos.CASTILLOS_EUROPA`
GROUP BY patrimonio_unesco;