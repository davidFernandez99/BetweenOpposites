package grup05.pis2018.ub.edu.betweenopposites.Model

class AumentarVelocidad(height: Float, width: Float, posicionInicial: Posicion, posicion: Posicion) :
    ObjetoActivable(height, width, posicionInicial, posicion) {
    // Maximo tiempo en segundos de objeto activo
    val MAX_TIEMPO_ACTIVO: Double = 10.0
    val AUMENTO_DE_VELOCIDAD: Int = 10

    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {//Si colisiona con un objeto lobo añadirá ese objeto a sus objetos activables
            var lobo: Lobo = objeto as Lobo
            lobo.objetoActivable = this
        }
    }


    /**
     * Aumenta la velocidad del lobo en AUMENTO_DE_VELOCIDAD.
     */

    override fun activarEfecto(lobo: Lobo) {
        lobo.velocidad += AUMENTO_DE_VELOCIDAD//augmentará la velocidad del lobo  durante un tiempo
        TODO("falta deicrle que cuando pase un tiempo vuelva a la normalidad")
    }


}