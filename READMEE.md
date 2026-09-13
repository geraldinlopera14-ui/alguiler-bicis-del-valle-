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

| Criterio | Cola (elegida) | Vector (alternativa) |
|---|---|---|
| Organización | Por orden de llegada (FIFO) | Por índice, sin orden fijo |
| Acceso | Solo al frente | Directo a cualquier posición |
| Inserción/eliminación | En extremos, O(1) | Simple al final; en medio requiere desplazar |
| Uso de memoria | Fija y predecible | Igual de fija |
| Implementación | Requiere manejo circular | Más directa, pero sin orden automático |
| Limitación | No prioriza fuera de turno | No refleja el orden de llegada sin lógica extra |

**Conclusión:** con un vector se perdería la garantía automática de atención por orden de llegada.

## 7. Instrucciones para ejecutar
```bash
javac Main.java
java Main
```

## 8. Casos de prueba (opción 4 del menú)
- Caso normal: cliente solicita bicicleta disponible, se asigna directo.
- Caso límite: fila de espera llena, se rechaza un nuevo cliente.

## 9. Limitaciones y posibles mejoras
Fila con capacidad fija, sin prioridad fuera de turno, sin persistencia de datos. Mejora: guardar el estado en un archivo.

## 10. Enlace al video
[Agregar aquí]
