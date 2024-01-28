import java.util.*

fun main() {

    var tiempo = Tiempo(12, 30, 30)
    try {
        tiempo = Tiempo(pedirHora(), pedirMinuto(), pedirSegundo())
        println("Tiempo introducido: $tiempo")
    } catch (e: IllegalArgumentException) {
        println(e)
        println("Se ha generado un tiempo predefinido para poder continuar el programa.")
        println("Tiempo predefinido: $tiempo")
    }

    println("\nIntroduzca el tiempo para incrementar.")
    try {
        if (tiempo.incrementar(Tiempo(pedirHora(), pedirMinuto(), pedirSegundo()))) {
            println("Tiempo incrementado: $tiempo")
        } else {
            println("Error: Se ha incrementado fuera del formato 23:59:59.")
        }
    } catch (e: IllegalArgumentException) {
        println(e)
    }

    println("\nIntroduzca el tiempo para decrementar.")
    try {
        if (tiempo.decrementar(Tiempo(pedirHora(), pedirMinuto(), pedirSegundo()))) {
            println("Tiempo decrementado: $tiempo")
        } else {
            println("Error: Se ha decrementado fuera del formato 00:00:00.")
        }
    } catch (e: IllegalArgumentException) {
        println(e)
    }

    println("\nIntroduzca el tiempo para comparar.")
    try {
        when (tiempo.comparar(Tiempo(pedirHora(), pedirMinuto(), pedirSegundo()))) {
            -1 -> println("El tiempo original $tiempo es menor que el tiempo introducido.")
            0 -> println("El tiempo original $tiempo es igual al tiempo introducido.")
            1 -> println("El tiempo original $tiempo es mayor que el tiempo introducido.")
        }
    } catch (e: IllegalArgumentException) {
        println(e)
    }

    val copia = tiempo.copiar()
    println("\nCopia del tiempo original: $copia")

    val nuevoTiempo = Tiempo(15, 34)
    nuevoTiempo.copiar(tiempo)
    println("\nCopia del tiempo original como un nuevo objeto: $nuevoTiempo")

    println("\nIntroduzca la cantidad de tiempo para sumar.")
    try {
        val resultado = tiempo.sumar(Tiempo(pedirHora(), pedirMinuto(), pedirSegundo()))
        if (resultado != null) {
            println("Resultado de la suma: $resultado")
        } else {
            println("Error: Se ha sumado fuera del formato 23:59:59.")
        }
    } catch (e: IllegalArgumentException) {
        println(e)
    }

    println("\nIntroduzca la cantidad de tiempo para restar.")
    try {
        val resultado = tiempo.restar(Tiempo(pedirHora(), pedirMinuto(), pedirSegundo()))
        if (resultado != null) {
            println("Resultado de la resta: $resultado")
        } else {
            println("Error: Se ha restado fuera del formato 00:00:00.")
        }
    } catch (e: IllegalArgumentException) {
        println(e)
    }

    val tiempo2 = Tiempo(13)
    println("\nSe realizarán comparaciones del tiempo original con un tiempo predefinido: $tiempo2")
    if (tiempo.esMayorQue(tiempo2)) {
        println("El tiempo original es mayor que $tiempo2.")
    } else {
        println("El tiempo original no es mayor que $tiempo2.")
    }


    if (tiempo.esMenorQue(tiempo2)) {
        println("El tiempo original es menor que $tiempo2.")
    } else {
        println("El tiempo original no es menor que $tiempo2.")
    }

    println("\nCerrando programa...")
    println("Hasta pronto!")
}

/**
 * Solicita al usuario que introduzca las horas y devuelve el valor ingresado como un entero.
 * @return El valor de las horas introducidas por el usuario.
 *         Si se produce un error al convertir el valor a entero, se imprime un mensaje de error y se devuelve -1.
 */
fun pedirHora(): Int {
    try {
        print("Introduzca las horas: ")
        val input = readln().toInt()
        return input
    } catch (e: NumberFormatException) {
        println("Error: No ha introducido un número entero")
        return -1
    }
}

/**
 * Solicita al usuario que introduzca los minutos y devuelve el valor ingresado como un entero.
 * Si se introduce una cadena vacía, se devuelve 0.
 * @return El valor de los minutos introducidos por el usuario, o 0 si se introduce una cadena vacía.
 *         Si se produce un error al convertir el valor a entero, se imprime un mensaje de error y se devuelve -1.
 */
fun pedirMinuto(): Int {
    try {
        print("Introduzca los minutos: ")
        val input = readln()
        if (input.isBlank()){
            return 0
        } else {
            val minuto = input.toInt()
            return minuto
        }
    } catch (e: NumberFormatException) {
        println("Error: No ha introducido un número entero")
        return -1
    }
}

/**
 * Solicita al usuario que introduzca los segundos y devuelve el valor ingresado como un entero.
 * Si se introduce una cadena vacía, se devuelve 0.
 * @return El valor de los segundos introducidos por el usuario, o 0 si se introduce una cadena vacía.
 *         Si se produce un error al convertir el valor a entero, se imprime un mensaje de error y se devuelve -1.
 */
fun pedirSegundo(): Int {
    try {
        print("Introduzca los segundos: ")
        val input = readln()
        if (input.isBlank()){
            return 0
        } else {
            val segundo = input.toInt()
            return segundo
        }
    } catch (e: NumberFormatException) {
        println("Error: No ha introducido un número entero")
        return -1
    }
}