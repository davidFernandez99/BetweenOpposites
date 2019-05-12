package grup05.pis2018.ub.edu.betweenopposites.Model

class AumentarVelocidad(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    ObjetoActivable(height, width, posicion) {
    // Maximo tiempo en segundos de objeto activo
    val AUMENTO_DE_VELOCIDAD: Int = 10
    var visible=true
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {//Si colisiona con un objeto lobo añadirá ese objeto a sus objetos activables
            var lobo: Lobo = objeto as Lobo
            lobo.objetoActivable = this
            activarEfecto(lobo)//ALERT!! ESTO ES PARA PROBAR
            visible=false
        }
    }


    /**
     * Aumenta la velocidad del lobo en AUMENTO_DE_VELOCIDAD.
     */

    override fun activarEfecto(lobo: Lobo) {
        lobo.velocidad += AUMENTO_DE_VELOCIDAD//augmentará la velocidad del lobo  durante un tiempo

    }


}