package grup05.pis2018.ub.edu.betweenopposites.Model

class AumentarVelocidad(posicionInicial: Posicion) : ObjetoActivable(posicionInicial) {
    // Maximo tiempo en segundos de objeto activo
    val MAX_TIEMPO_ACTIVO: Double = 10.0
    val AUMENTO_DE_VELOCIDAD: Int = 10

    /**
     * Aumenta la velocidad del lobo en AUMENTO_DE_VELOCIDAD.
     */
    override fun activarEfecto(lobo: Lobo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun tratarColision() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}