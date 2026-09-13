# Sistema de Turnos - Alquiler de Bicicletas del Valle
# Estructura: Cola Estática circular (lista de tamaño fijo con frente/final)

CAPACIDAD_FILA = 5
fila = [None] * CAPACIDAD_FILA
frente = 0
final_cola = 0
cantidad_en_fila = 0
bicicletas_disponibles = 3


def encolar(nombre):
    global final_cola, cantidad_en_fila
    if cantidad_en_fila == CAPACIDAD_FILA:
        return False
    fila[final_cola] = nombre
    final_cola = (final_cola + 1) % CAPACIDAD_FILA
    cantidad_en_fila += 1
    return True


def desencolar():
    global frente, cantidad_en_fila
    if cantidad_en_fila == 0:
        return None
    nombre = fila[frente]
    frente = (frente + 1) % CAPACIDAD_FILA
    cantidad_en_fila -= 1
    return nombre


def solicitar(nombre):
    global bicicletas_disponibles
    if bicicletas_disponibles > 0:
        bicicletas_disponibles -= 1
        print(f"Bicicleta asignada a {nombre}. Disponibles: {bicicletas_disponibles}")
    elif encolar(nombre):
        print(f"{nombre} entra a la fila de espera ({cantidad_en_fila}/{CAPACIDAD_FILA})")
    else:
        print(f"Fila llena. {nombre} no pudo registrarse.")


def devolver():
    global bicicletas_disponibles
    if cantidad_en_fila > 0:
        nombre = desencolar()
        print(f"Bicicleta asignada automáticamente a {nombre}")
    else:
        bicicletas_disponibles += 1
        print(f"Bicicleta devuelta. Disponibles: {bicicletas_disponibles}")


def ver_estado():
    print(f"Bicicletas disponibles: {bicicletas_disponibles}")
    print(f"Fila de espera ({cantidad_en_fila}/{CAPACIDAD_FILA}):")
    for i in range(cantidad_en_fila):
        print(f"{i + 1}. {fila[(frente + i) % CAPACIDAD_FILA]}")


def demostracion():
    global bicicletas_disponibles
    print("\n--- CASO NORMAL: bicicleta disponible ---")
    solicitar("Ana")  # hay disponibles -> se asigna directo

    print("\n--- CASO LÍMITE: fila de espera llena ---")
    bicicletas_disponibles = 0  # se agota la flota
    for i in range(CAPACIDAD_FILA):
        solicitar(f"Cliente{i}")  # llena la fila
    solicitar("Extra")  # debe rechazarse: fila llena
    ver_estado()


def main():
    opcion = 0
    while opcion != 5:
        print("\n1. Solicitar bicicleta  2. Devolver bicicleta  3. Ver estado  4. Demostración  5. Salir")
        opcion = int(input("Opción: "))
        if opcion == 1:
            nombre = input("Nombre: ")
            solicitar(nombre)
        elif opcion == 2:
            devolver()
        elif opcion == 3:
            ver_estado()
        elif opcion == 4:
            demostracion()


if __name__ == "__main__":
    main()
