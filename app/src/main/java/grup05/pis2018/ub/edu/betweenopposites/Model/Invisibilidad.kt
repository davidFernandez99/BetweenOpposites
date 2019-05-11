package grup05.pis2018.ub.edu.betweenopposites.Model


/**
 * Objeto que hace Invisible/Indetectable por los orbes enemigos al LObo por un tiempo limitado cuando se activa
 */
class Invisibilidad(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    ObjetoActivable(height, width, posicion) {

    // Maximo tiempo en segundos de objeto activo

    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {//Si colisiona con un objeto lobo añadirá ese objeto a sus objetos activables
            var lobo: Lobo = objeto as Lobo
            lobo.objetoActivable = this
        }
    }


    /**
     * Cambia el estado del lobo de visible a invisible, y lo hace durante MAX_TIEMPO_ACTIVO
     */
    override fun activarEfecto(lobo: Lobo) {
        lobo.es_visible = false

    }


}