Máster en Inteligencia de Negocio y Big Data en Entornos Seguros

Repositorio: https://github.com/BungisaBeto/tfm-bungisa-beto

En la carpeta practica se encuentra el desarrollo realizado:

1. jupyter-notebook: Esta carpeta contiene el analisis exploratorio y el modelado. Para utilizarlo necesitas instalar jupyter notebook

Instalacion jupyter:

1. Primero tienes que instalar python desde este enlace: https://www.python.org/
2. Una vez instalado python tiene que instalar juypter notebook siguiendo las siguientes instrucciones de este enlace: https://jupyter.org/install


Ejecutar el codigo:

1. Primero tiene que abrir el programa jupyter notebook.

2. En jupyter notebook busca la ruta donde se encuentran los ficheros:

- 1_limpieza_de_datos_1
- 2_limpieza_de_datos_2
- 3_analisis_exploratorio_de_datos
- 4_modelo_prediccion_viviendas


Aplicación de Java para la recopilacion de datos de la API de Idealista

   1. java: Esta parte es la aplicacion de JAVA y se encarga de recopilar los datos de viviendas
   
   Version de Java: 11
   config.properties: fichero de configuración para acceder a la API pero por seguridad he quitado mis clave de acceso a la Api.
   
   Para ejecutar el jar de java de la carpeta /java/codigo/jar tienes que realizar lo siguiente:
   
   1. Desde el simbolo del sistema tiene que escribir:
    
	java -jar data-collector-api.jar config.properties
