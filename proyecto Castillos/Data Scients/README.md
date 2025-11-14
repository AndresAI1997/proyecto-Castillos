# 🏰 Predicción de Interés Turístico en Castillos Europeos (ML)

Este proyecto utiliza una base de datos sintética de 10.000 castillos
europeos para **predecir el interés turístico** de cada castillo
mediante un modelo de Machine Learning (*Random Forest Regressor*).

El objetivo es construir una **variable objetivo realista** y entrenar
un modelo capaz de identificar los factores que influyen en la cantidad
estimada de visitantes anuales.

------------------------------------------------------------------------

## 📌 Objetivo del proyecto

-   Crear una métrica realista de visitantes anuales
    (`visitantes_modelado`) basada en:
    -   Patrimonio UNESCO\
    -   Estado del castillo (Museo, Restaurado, Ruinas...)\
    -   País\
    -   Estilo arquitectónico\
    -   Antigüedad\
    -   Material\
-   Entrenar un modelo supervisado para predecir esta métrica.
-   Obtener las **variables con mayor impacto turístico**.

------------------------------------------------------------------------

## 🧠 Metodología

1.  **Limpieza del dataset**
    -   Se eliminan columnas irrelevantes (`id`, `nombre`).
2.  **Creación del target sintético**
    -   Se modela el interés turístico combinando reglas basadas en
        lógica realista.
3.  **One-Hot Encoding** de variables categóricas.
4.  **Train/Test Split**
    -   80% para entrenamiento, 20% para prueba.
5.  **Entrenamiento del modelo**
    -   Se utiliza un **Random Forest Regressor** con 400 árboles.
6.  **Evaluación**
    -   RMSE ≈ **18.284**
    -   R² ≈ **0.987**
    -   Resultados altamente estables y consistentes.
7.  **Interpretabilidad**
    -   La importancia de variables revela los factores turísticos más
        relevantes.

------------------------------------------------------------------------

## ⭐ Principales resultados

### 🔝 Factores que más influyen en el turismo

1.  **Patrimonio UNESCO** --- factor dominante (\~73%).\
2.  **País (España, Francia, Reino Unido)**\
3.  **Estado del castillo (Museo / Restaurado)**\
4.  **Estilo arquitectónico (Medieval, Gótico, Neogótico)**\
5.  **Antigüedad**

### 🎯 Rendimiento del modelo

-   **R²: 0.987**\
-   **RMSE: 18.283**

------------------------------------------------------------------------

## 🗂 Archivos incluidos

-   `EDA_Castillos.ipynb`
-   `castillos_europa_10000.tsv`

------------------------------------------------------------------------

## 🔗 Parte de un proyecto mayor

Este trabajo forma parte de un **conjunto de 4 proyectos** que analizan
la misma base de castillos desde diferentes enfoques:

1.  Machine Learning (este proyecto)
2.  Tableau Dashboard
3.  Paradigma Orientado a Objetos (POO)
4.  Desarrollo Web

------------------------------------------------------------------------

## 📬 Autor

**Andrés Álvarez** Desarrollador en formación \| Data Analyst \| Data
Scientist Jr.
