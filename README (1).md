# Turnos de Alquiler de Bicicletas del Valle

## 1. Nombre de la aplicación
Turnos de Alquiler de Bicicletas del Valle

## 2. Descripción no técnica del problema
En el pueblo el transporte es limitado y el taxi es caro para recorrer el valle. Hay un punto de alquiler de bicicletas, pero son pocas. Cuando se acaban, los siguientes clientes deben esperar su turno.

## 3. Descripción de la solución
Programa de consola que asigna bicicletas disponibles de inmediato. Si no hay, el cliente entra a una fila de espera. Al devolverse una bicicleta, se asigna automáticamente al primero de la fila.

## 4. Estructura de datos seleccionada
Cola estática circular (arreglo fijo `Cliente[] fila` con índices `frente` y `final`).

## 5. Justificación técnica
- Almacena a los clientes en espera en orden de llegada (FIFO).
- Operaciones: encolar (entra a esperar) y desencolar (se atiende al primero).
- Inserción/eliminación en los extremos, O(1), con índices circulares.
- Ventaja: refleja exactamente el orden real de atención.
- Limitación: capacidad fija; no permite atender fuera de turno.

## 6. Análisis con otra estructura (Vector)

Si en vez de la cola usara un vector, esto cambiaría:

- Organización: la cola guarda a los clientes en el orden en que llegan. El vector los guardaría por posición, sin importar el orden de llegada.
- Acceso: en la cola solo se puede ver al primero de la fila. En el vector se puede ver cualquier posición directamente.
- Insertar y eliminar: en la cola siempre se agrega al final y se quita del primero. En el vector, quitar a alguien de en medio es más complicado porque hay que mover los demás datos.
- Memoria: las dos usan un arreglo de tamaño fijo, así que gastan memoria parecida.
- Facilidad: la cola es un poco más difícil de programar porque hay que manejar los índices de frente y final. El vector es más simple de escribir, pero no respeta el orden de llegada solo.
- Problema si se usara el vector: se perdería el orden automático de atención, y habría que agregarle a cada cliente una fecha para saber quién llegó primero.

Por eso se usó la cola, porque encaja mejor con la idea de una fila de espera.

## 7. Instrucciones para ejecutar
```bash
python3 main.py
```

## 8. Casos de prueba (opción 4 del menú)
- Caso normal: cliente solicita bicicleta disponible, se asigna directo.
- Caso límite: fila de espera llena, se rechaza un nuevo cliente.

## 9. Limitaciones y posibles mejoras
Fila con capacidad fija, sin prioridad fuera de turno, sin persistencia de datos. Mejora: guardar el estado en un archivo.

## 10. Enlace al video
[Agregar aquí]
