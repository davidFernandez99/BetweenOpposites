package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Objeto que hace Invisible/Indetectable por los orbes enemigos al LObo por un tiempo limitado cuando se activa
 */
class Invisibilidad(posicionInicial: Posicion) : ObjetoActivable(posicionInicial) {

    // Maximo tiempo en segundos de objeto activo
    val MAX_TIEMPO_ACTIVO: Double = 10.0

    /**
     * Cambia el estado del lobo de visible a invisible, y lo hace durante MAX_TIEMPO_ACTIVO
     */
    override fun activarEfecto(lobo: Lobo) {
        TODO("Poner el lobo en estado invisible") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Se encarga de almazenarse en la clase del Lobo
     */
    override fun tratarColision() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}