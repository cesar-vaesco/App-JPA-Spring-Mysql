# Getting Started


### Consola H2

- Acceder a consola de H2
	- Habilitar en application.properties: 
			- spring.h2.console.enabled=true
- Ya habilitada y con el proyecto levantado buscar en el navegador la consola de h2
		- http://localhost:8080/h2-console

- En caso de no configurar usuario y contraseña en la consola identificar en los logs de spring al levantar el proyecto el nombre que h2 le asigna a URL JDBC para ingresar a la consola
	- Ejemplo: jdbc:h2:mem:c1d03adb-b3b6-4ce6-a719-b79e51b0a906 <- Asignado por H2