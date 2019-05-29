package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Canvas

/**
 * Clase fachada del modelo, que crea u objeto único (Singletone) para poder proporcionar los servicios
 * del modelo de forma simple a aquellos que quieresn acceder a este, en este caso solo los Presenter.
 * Contiene todas las funciones visibles del modelo desde el exteriór, englobando multiples funciones que deben ser
 * ejecutadas cuando se produce una acción o ocurre un fenomeno especifico.
 * Implementa el Patrón de Diseño Singleton que procura la creaciń de una única instancia para utilizar la facade
 */
class Facade : Model {

    /**
     * Contiene la única instancia Facade con la cual debemos trabajar.
     * Es lo mismo que crear un campo estatico en Java
     * De esta forma podemos tener una única instancia para esta clase.
     */
    lateinit var gameData:GameData

    /**
     * Guarda si la partida ha sido iniciada
     */
    var partidaIniciada=false

    /**
     * Singleton Facade
     */
    companion object {
        val instance: Facade=Facade()
        var dando_opciones=false
        var comprobar_opcion=false
        var opcion:Int=0
        var fallar=false
        var opciones:ArrayList<Int> ?= null
        var nivel:Int = 0
        var mapa:Int =0
        var ultimaPuntuacion:Int=0
        var signo:Int=0 //si es 1 es +, y 2 es -
    }

    /**
     * Función para inicializar la partida. Crea el conjunto de niveles y dentro de este las salas y objetos.
     */
    fun iniciarPartida(contexto: Context){
        // Creo la partida
        gameData = GameData(contexto)

        partidaIniciada=true
    }


    override fun setLocalData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocalData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun uploadServerData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun downloadServerData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun draw(canvas: Canvas, contexto: Context){
        gameData.draw(canvas,contexto)
    }
    fun update(fps:Long){
        gameData.update(fps)
    }

    fun traspasarPuerta(puerta: Puerta) {
        if(partidaIniciada){
            gameData.traspasarPuerta(puerta)
        }
    }
}