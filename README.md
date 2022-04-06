### Despliegue de nuestra aplicación 

1.- Empaquetado del proyecto
	- En la raíz del proyecto ejecutar el comado maven install
	- El empaquetado del proyecto se genera y al terminar de construise se guarda en la carpeta target
	
2.- Desplegando la aplicación (TomCat embebido)
	- Se ejecuta el siguiente comando:  
	
	'''
	java -jar .\target\spring-boot-boot-jpa-0.0.1-SNAPSHOT.jar
	
	'''
	- este comando se ejecuta en línea de comando con permisos de administrador
	
	<p align="center">
      <img src="src/main/resources/static/images/comando.png" width="500" height="300" title="post-token"> <br/>
      <img src="src/main/resources/static/images/ejecucion.png" width="500" height="300" title="gett-token">
	</p>
	