# Sistema de Gestión de Stock de Plantas

Este es un sistema de gestión de stock diseñado para el seguimiento de plantas en un vivero o cualquier otro entorno similar. Permite llevar un registro de las plantas disponibles, las ventas realizadas y generar informes de stock.

## Instalación

1. Clona el repositorio desde GitHub.
2. Configura tu base de datos utilizando los comandos SQL proporcionados en la sección "Estructura de la Base de Datos".
3. Ejecuta la aplicación y sigue las instrucciones en pantalla para empezar a utilizarla.

## Uso

- Agrega nuevas plantas utilizando el formulario correspondiente.
- Realiza ventas de plantas para actualizar el stock.
- Genera informes de stock para mantener un registro actualizado de la disponibilidad de plantas.

## Estructura de la Base de Datos

### Tabla `plantas`

| Columna       | Tipo       | Descripción                           |
|---------------|------------|---------------------------------------|
| id            | INT        | Identificador único de la planta.     |
| fechaIngreso  | DATE       | Fecha en que se ingresó la planta.    |
| codigo        | INT        | Código identificador de la planta.    |
| nombrePlanta  | VARCHAR(70)| Nombre de la planta.                  |
| cantidad      | INT        | Cantidad disponible de la planta.     |
| precioCosto   | DOUBLE     | Precio de costo de la planta.         |
| precioVenta   | DOUBLE     | Precio de venta de la planta.         |

### Tabla `ventas`

| Columna       | Tipo       | Descripción                           |
|---------------|------------|---------------------------------------|
| id            | INT        | Identificador único de la venta.      |
| planta_id     | INT        | ID de la planta vendida.              |
| cantidad      | INT        | Cantidad de plantas vendidas.         |
| fecha_venta   | DATE       | Fecha de la venta.                    |

## Ejemplo de Configuración de la Base de Datos

1. Crea una base de datos llamada `gestion_plantas`.
2. Ejecuta los siguientes comandos SQL para crear las tablas:

```sql
CREATE TABLE plantas(
    id INT NOT NULL AUTO_INCREMENT,
    fechaIngreso DATE,
    codigo INT NOT NULL,
    nombrePlanta VARCHAR(70) NOT NULL,
    cantidad INT NOT NULL,
    precioCosto DOUBLE,
    precioVenta DOUBLE,
    PRIMARY KEY(id)
);

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    planta_id INT,
    cantidad INT,
    fecha_venta DATE,
    FOREIGN KEY (planta_id) REFERENCES plantas(id)
);
