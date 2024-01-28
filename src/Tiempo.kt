/**
 * Clase que representa un instante de tiempo, con horas, minutos y segundos.
 *
 * @property hora La hora del instante (en formato de 24 horas).
 * @property minuto El minuto del instante (0 a 59).
 * @property segundo El segundo del instante (0 a 59).
 * @constructor Crea un nuevo objeto Tiempo con los valores proporcionados para hora, minuto y segundo.
 */
class Tiempo(private var hora: Int, private var minuto: Int = 0, private var segundo: Int = 0) {

    /**
     * Ajusta los valores de hora, minuto y segundo para asegurar que estén dentro del rango válido.
     */
    init {
        require(hora in 0..23) { "Hora debe ser un valor entre 0 y 23" }
        require(minuto >= 0) { "Minuto no puede ser menor que 0" }
        require(segundo >= 0) { "Segundo no puede ser menor que 0" }
        ajustarTiempo()
    }

    /**
     * Ajusta el tiempo para asegurarse de que los valores de minuto y segundo estén dentro del rango válido.
     */
    private fun ajustarTiempo() {
        if (segundo >= 60) {
            minuto += segundo / 60
            segundo %= 60
        }

        if (minuto >= 60) {
            hora += minuto / 60
            minuto %= 60
        }
    }

    /**
     * Devuelve una representación en formato de cadena del objeto Tiempo.
     */
    override fun toString(): String {
        return "%02dh %02dm %02ds".format(hora, minuto, segundo)
    }

    /**
     * Incrementa este tiempo por el tiempo proporcionado.
     * @param t El tiempo para incrementar.
     * @return `true` si el incremento fue exitoso, `false` de lo contrario.
     */
    fun incrementar(t: Tiempo): Boolean {
        val antiguaHora = listOf(hora, minuto, segundo)
        segundo += t.segundo
        minuto += t.minuto
        hora += t.hora

        ajustarTiempo()

        if (hora < 24) {
            return true
        } else {
            hora = antiguaHora[0]
            minuto = antiguaHora[1]
            segundo = antiguaHora[2]

            return false
        }
    }

    /**
     * Decrementa este tiempo por el tiempo proporcionado.
     * @param t El tiempo para decrementar.
     * @return `true` si el decremento fue exitoso, `false` de lo contrario.
     */
    fun decrementar(t: Tiempo): Boolean {
        val antiguaHora = listOf(hora, minuto, segundo)
        segundo -= t.segundo
        minuto -= t.minuto
        hora -= t.hora

        while (segundo < 0) {
            segundo += 60
            minuto--
        }

        while (minuto < 0) {
            minuto += 60
            hora--
        }

        if (hora >= 0) {
            return true
        } else {
            hora = antiguaHora[0]
            minuto = antiguaHora[1]
            segundo = antiguaHora[2]
            return false
        }
    }

    /**
     * Compara este tiempo con el tiempo proporcionado.
     * @param t El tiempo con el que se compara.
     * @return -1 si este tiempo es anterior a t, 0 si son iguales, o 1 si este tiempo es posterior a t.
     */
    fun comparar(t: Tiempo): Int {
        return when {
            hora < t.hora || (hora <= t.hora && minuto < t.minuto) || (hora <= t.hora && minuto <= t.minuto && segundo < t.segundo) -> -1
            hora == t.hora && minuto == t.minuto && segundo == t.segundo -> 0
            else -> 1
        }
    }

    /**
     * Crea y devuelve una copia de este objeto Tiempo.
     * @return Una copia de este objeto Tiempo.
     */
    fun copiar(): Tiempo {
        return Tiempo(hora, minuto, segundo)
    }

    /**
     * Copia el tiempo proporcionado a este tiempo.
     * @param t El tiempo para copiar.
     */
    fun copiar(t: Tiempo) {
        hora = t.hora
        minuto = t.minuto
        segundo = t.segundo
    }

    /**
     * Suma el tiempo proporcionado a este tiempo y devuelve el resultado.
     * @param t El tiempo para sumar.
     * @return El resultado de la suma como un nuevo objeto Tiempo, o null si el resultado supera 24 horas.
     */
    fun sumar(t: Tiempo): Tiempo? {
        val resultado = Tiempo(hora, minuto, segundo)

        if (resultado.incrementar(t)) {
            return resultado
        } else {
            return null
        }
    }

    /**
     * Resta el tiempo proporcionado a este tiempo y devuelve el resultado.
     * @param t El tiempo para restar.
     * @return El resultado de la resta como un nuevo objeto Tiempo, o null si el resultado es negativo.
     */
    fun restar(t: Tiempo): Tiempo? {
        val resultado = Tiempo(hora, minuto, segundo)

        if (resultado.decrementar(t)) {
            return resultado
        } else {
            return null
        }
    }

    /**
     * Verifica si este tiempo es mayor que el tiempo proporcionado.
     * @param t El tiempo para comparar.
     * @return `true` si este tiempo es mayor que t, `false` de lo contrario.
     */
    fun esMayorQue(t: Tiempo): Boolean {
        return comparar(t) == 1
    }

    /**
     * Verifica si este tiempo es menor que el tiempo proporcionado.
     * @param t El tiempo para comparar.
     * @return `true` si este tiempo es menor que t, `false` de lo contrario.
     */
    fun esMenorQue(t: Tiempo): Boolean {
        return comparar(t) == -1
    }
}