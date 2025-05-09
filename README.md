# Hospital - Sistema de Gestión de Citas Médicas

Este proyecto es una aplicación web desarrollada con **Spring Boot + Thymeleaf**, que permite la **gestión de doctores, consultorios y citas médicas** en un hospital.

---

## 🧩 Funcionalidades principales

- Agendar nuevas citas médicas.
- Consultar citas por fecha, doctor y consultorio.
- Editar o cancelar citas existentes.
- Validaciones importantes:
  - No se permite agendar una cita para un **mismo doctor** o **mismo consultorio** a la misma hora.
  - Un **paciente no puede tener dos citas el mismo día con menos de 2 horas de diferencia**.
  - Un **doctor no puede tener más de 8 citas en un día**.

---

## 🚀 Tecnologías usadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf
- H2 Database (modo memoria)
- Maven

---

## 📂 Estructura del proyecto

```
src/
├── main/
│   ├── java/com/evaluacion/hospital/
│   │   ├── controller/        # Controladores web
│   │   ├── entity/            # Entidades JPA (Cita, Doctor, Consultorio)
│   │   ├── repository/        # Interfaces de acceso a datos
│   │   └── service/           # Lógica de negocio y validaciones
│   └── resources/
│       ├── templates/         # Vistas Thymeleaf (formulario-cita, editar-cita, consulta-citas)
│       ├── application.properties
└── test/                      # Pruebas unitarias (no incluido por defecto)
```

---

## 🛠️ Cómo ejecutar

1. Clona este repositorio o descomprime el ZIP:

   ```bash
   git clone https://github.com/t3otl/api-evaluacion-hospital.git
   cd evaluacion-hospital
   ```

2. Ejecuta la aplicación con Maven:

   ```bash
   mvn spring-boot:run
   ```

3. Abre el navegador en:

   ```
   http://localhost:8080/citas
   http://localhost:8080/citas/consultar
   ```

---

## ⚙️ Configuración (por defecto)

- Base de datos H2 en memoria
- URL de consola H2: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`, contraseña: *(vacía)*

---

## 📸 Capturas de pantalla

> Puedes agregar aquí screenshots del formulario de cita, consulta, validaciones, etc.

---

## ✍️ Autor

**Teotl Rosas Lozano**

---

## 📝 Licencia

Este proyecto es solo para fines evaluativos o educativos. Puedes modificarlo libremente para prácticas académicas.
