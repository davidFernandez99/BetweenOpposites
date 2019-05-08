package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 * Objeto que hace Invisible/Indetectable por los orbes enemigos al LObo por un tiempo limitado cuando se activa
 */
class Invisibilidad(height: Float, width: Float, posicionInicial: Posicion,posicion: Posicion, image: Bitmap?) :
    ObjetoActivable(height, width, posicionInicial,posicion, image) {
    // Maximo tiempo en segundos de objeto activo
    val MAX_TIEMPO_ACTIVO: Double = 10.0


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
        lobo.visible = false
        TODO("falta deicrle que cuando pase un tiempo vuelva a la normalidad")
    }


}