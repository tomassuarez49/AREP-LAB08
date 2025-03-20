# Escuela Colombiana de Ingeniería
# Arquitecturas Empresariales

### Integrantes
* Tomas Suarez Piratova
* Ricardo Andrés Villamizar
* Andrés Felipe Rodríguez

## Taller 7 Microservicios

## Descripción del laboratorio
El propósito de este laboratorio es diseñar, desarrollar y desplegar una aplicación web que permita a los usuarios crear y visualizar publicaciones de hasta 140 caracteres, similar a Twitter. Se iniciará con una arquitectura monolítica en Spring Boot y luego se refactorizará en una arquitectura de microservicios, 
desplegando cada componente en AWS Lambda. Además, se implementará autenticación con JWT utilizando Amazon Cognito 

---
### Prerrequisitos 🧰

* [Maven](https://maven.apache.org/): Es una herramienta de comprensión y gestión de proyectos de software. Basado en el concepto de modelo de objetos de proyecto (POM), Maven puede gestionar la construcción, los informes y la documentación de un proyecto desde una pieza de información central.
* [Git](https://learn.microsoft.com/es-es/devops/develop/git/what-is-git): Es un sistema de control de versiones distribuido, lo que significa que un clon local del proyecto es un repositorio de control de versiones completo. Estos repositorios locales plenamente funcionales permiten trabajar sin conexión o de forma remota con facilidad.
* [Java JSK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) :Entorno de desarrollo necesario para ejecutar aplicaciones en Spring Boot.
* [Spring boot](https://spring.io/projects/spring-boot): Framework para la creación de aplicaciones backend en Java.Simplifica la configuración y despliegue de servicios web y APIs REST.


---
 
### Tecnologías usadas 👨‍💻

Para el desarrollo y despliegue de la aplicación, se utilizarán las siguientes tecnologías:

Frontend 🎨
* HTML, CSS y JavaScript → Interfaz de usuario simple y responsiva.

* Fetch API → Para realizar peticiones HTTP al backend.

* Amazon S3 → Almacenamiento y despliegue del frontend como un sitio web estático.

Backend ⚙️

* Spring Boot → Framework para la creación de APIs RESTful.
* JWT (JSON Web Token) → Autenticación segura con Amazon Cognito

Despliegue y Servicios en la Nube ☁️

* AWS Lambda → Ejecución de los microservicios sin necesidad de servidores.

* Amazon API Gateway → Exposición de los endpoints del backend.

* Amazon S3 → Hosting del frontend.

* AWS Cognito → Servicio de autenticación y gestión de usuarios.

---
---

### Arquitectura Inicial💻

Al inicio, la aplicación se implementa como un monolito con las siguientes capas:

Capa de Presentación (Frontend)

* Aplicación web en HTML, CSS y JavaScript.
  
  * Utiliza Fetch API para comunicarse con el backend.
  * Desplegado en Amazon S3 como un sitio web estático.

* Capa de Aplicación (Backend en Spring Boot)
  * API RESTful con Spring Boot y Spring Data JPA.
  * Controladores para manejar peticiones HTTP (usuarios, posts, hilo).

* Despliegue
  * Backend alojado en EC2 con un balanceador de carga opcional.
  * Frontend en Amazon S3, con acceso público.
  * Seguridad mediante AWS Cognito
 
### Arquitectura Final💻
Tras la separación del monolito, la aplicación se compone de tres microservicios independientes, ejecutados en AWS Lambda, y expuestos a través de Amazon API Gateway:

Microservicios

1) Servicio de Usuarios
   * Registra nuevos usuarios y gestiona autenticación.
   * Utiliza AWS Cognito para autenticación y emisión de JWT.

2) Servicio de Posts
   * Maneja la creación de publicaciones (máximo 140 caracteres).
   * Guarda los posts en Amazon DynamoDB o MySQL en RDS.

3) Servicio de Hilo (Stream)
   * Recupera y muestra posts en orden cronológico.
   * Optimizado con Amazon ElastiCache (Redis) para mejorar rendimiento.

### Componentes en AWS

* Frontend → Desplegado en Amazon S3 y distribuido con CloudFront.

* API Gateway → Expone los endpoints de los microservicios.

* AWS Lambda → Ejecuta los microservicios sin necesidad de servidores.

* Amazon Cognito → Autenticación y gestión de usuarios con JWT.

* Amazon CloudWatch → Monitoreo y logs del sistema.


### Instalación e instrucciones de despliegue 🚀​🌐​

1) Debemos clonar el repositorio
```
https://github.com/andres3455/Lab6_Arep.git
```
2) Una vez clonamos, accedemos al directorio
```
cd Lab6_Arep
```

## Evidencias

1) Despligue y correcto funcionamiento del monolito

https://github.com/user-attachments/assets/3b9a6070-0ea8-4636-a451-ee24ea7b5519


2) Implementación de la capa de seguridad con AWS cognito

https://github.com/user-attachments/assets/faf59d5e-6e5d-4681-a9d7-d36b0b01812a

3) Separación del monolito en microservicios independientes

![Captura de pantalla 2025-03-19 123816](https://github.com/user-attachments/assets/07da88bd-ecca-4e9e-b625-eb9964a49434)

Hacemos varios test en la función lambda antes de implementar el API gateway

![Captura de pantalla 2025-03-19 111905](https://github.com/user-attachments/assets/e721d71a-0b2f-48b3-b240-a572a3e9acc0)

![Captura de pantalla 2025-03-19 112119](https://github.com/user-attachments/assets/601dc6a2-2c7c-4a59-9caf-f3b73f44bd0f)

![Captura de pantalla 2025-03-19 224302](https://github.com/user-attachments/assets/fc9b7ea6-1cd5-4ee3-9811-f1a1bb6c5d3a)


Creamos los metodos correspondientes a cada recurso (posts, users, streams)

![Captura de pantalla 2025-03-19 235351](https://github.com/user-attachments/assets/0a429558-52f4-4c31-b9b3-6ee71512695d)


En el API Rest, creamos un evento de prueba mas 

![Captura de pantalla 2025-03-19 235948](https://github.com/user-attachments/assets/6bae8b6e-a495-4db7-b040-683e7e57638f)


Videos de la implementación de la API

https://github.com/user-attachments/assets/80e11149-6212-4ca1-8b77-8c460f923eb3



https://github.com/user-attachments/assets/e60c66a0-9412-4ed4-b77e-fd1ebbf3672a




### Construido con

* [Maven](https://maven.apache.org/): Es una herramienta de comprensión y gestión de proyectos de software. Basado en el concepto de modelo de objetos de proyecto (POM), Maven puede gestionar la construcción, los informes y la documentación de un proyecto desde una pieza de información central.

* [Git](https://learn.microsoft.com/es-es/devops/develop/git/what-is-git): Es un sistema de control de versiones distribuido, lo que significa que un clon local del proyecto es un repositorio de control de versiones completo. Estos repositorios locales plenamente funcionales permiten trabajar sin conexión o de forma remota con facilidad.

* [GitHub](https://platzi.com/blog/que-es-github-como-funciona/): Es una plataforma de alojamiento, propiedad de Microsoft, que ofrece a los desarrolladores la posibilidad de crear repositorios de código y guardarlos en la nube de forma segura, usando un sistema de control de versiones llamado Git.

* [Java -17](https://www.cursosaula21.com/que-es-java/): Es un lenguaje de programación y una plataforma informática que nos permite desarrollar aplicaciones de escritorio, servidores, sistemas operativos y aplicaciones para dispositivos móviles, plataformas IoT basadas en la nube, televisores inteligentes, sistemas empresariales, software industrial, etc.

* [JavaScript](https://universidadeuropea.com/blog/que-es-javascript/): Es un lenguaje de programación de scripts que se utiliza fundamentalmente para añadir funcionalidades interactivas y otros contenidos dinámicos a las páginas web.

* [HTML](https://aulacm.com/que-es/html-significado-definicion/): Es un lenguaje de marcado de etiquetas que se utiliza para crear y estructurar contenido en la web. Este lenguaje permite definir la estructura y el contenido de una página web mediante etiquetas y atributos que indican al navegador cómo mostrar la información.

* [CSS](https://www.hostinger.co/tutoriales/que-es-css): Es un lenguaje que se usa para estilizar elementos escritos en un lenguaje de marcado como HTML.

* [Visual Studio Code](https://openwebinars.net/blog/que-es-visual-studio-code-y-que-ventajas-ofrece/): Es un editor de código fuente desarrollado por Microsoft. Es software libre y multiplataforma, está disponible para Windows, GNU/Linux y macOS.


