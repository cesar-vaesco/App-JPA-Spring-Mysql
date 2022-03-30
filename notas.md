# Getting Started

### Ejemplos de estilos en excel
https://poi.apache.org/components/spreadsheet/examples.html

### Spring security db-JDBC autorización

- Tablas para almacenar los perfiles de autorización

CREATE TABLE `db_springboot`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
  
CREATE TABLE `db_springboot`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_authority_unique` (`user_id` ASC, `authority` ASC) VISIBLE,
  CONSTRAINT `fk_auhtorities_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_springboot`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
 - Creando usuarios - las constraseñas es la encriptacion de una cadena de caracteres recuperada de los logs de spring <br/><br/>
 
    - insert into users(username, password, enabled) Values ('cesar','$2a$10$LQ5H5KcEwWjMtx9H0/loLe/BbubKkD3gs3bt0NVUytDFh8Mz6RJ0K', 1);
    - insert into users(username, password, enabled) Values ('admin','$2a$10$nsMER4SnQQaodR.JZlPK3.5y7X7EAqFcPvbunl62FPjeMWJVJTSHS', 1);  
    
- Asignación de roles a un usuario <br/><br/>    
    - insert into authorities (user_id, authority) values(1, 'ROLE_USER');
    - insert into authorities (user_id, authority) values(2, 'ROLE_ADMIN');
    - insert into authorities (user_id, authority) values(2, 'ROLE_USER');

### Spring security

Al agregar las dependencias de forma automatica nuestra aplicación va a cintar con una página de acceso(login)
* Dependencias:
    - thymeleaf-extras-springsecurity5
    - spring-boot-starter-security
    <br/>
* Configurar la clase SpringSecurityConfig
	- En raíz de proyecto
	- Se habilita perfil y permisos para el perfil habilitado
	
	
### Paginador 

Pasos:
  
* repository debe de extender de PagingAndSortingRepository
* service - (clase e interfaz) : agregar método que va a leer los elementos que vana contener en el paginador 
* controlador:
	- Modificar el método que lista clientes utilizando el método que página los registros de clientes
	    - @RequestParam(name = "page", defaultValue = "0") int page, 
	    - Pageable pageRequest = PageRequest.of(page, 4); //cantidad de páginas a mostrar
		- Page<Cliente> clientes = clienteService.findAll(pageRequest);// Obtenemos la lista página de registros
		- model.addAttribute("clientes",clientes); //pasar lista a la vista ya páginada 
* util.paginador clases que se van encargar de calcular rangos para mostrar registros 
* estilos para mostrar el paginador

### Consola H2

- Acceder a consola de H2
	- Habilitar en application.properties: 
			- spring.h2.console.enabled=true
- Ya habilitada y con el proyecto levantado buscar en el navegador la consola de h2
		- http://localhost:8080/h2-console

- En caso de no configurar usuario y contraseña en la consola identificar en los logs de spring al levantar el proyecto el nombre que h2 le asigna a URL JDBC para ingresar a la consola
	- Ejemplo: jdbc:h2:mem:c1d03adb-b3b6-4ce6-a719-b79e51b0a906 <- Asignado por H2
	
	
