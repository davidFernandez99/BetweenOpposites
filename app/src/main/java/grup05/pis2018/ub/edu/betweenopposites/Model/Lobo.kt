package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Intent
import android.graphics.Bitmap


/**
 *
 */
class Lobo(
    var vida: Vida,
    bando: Int,
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicionInicial: Posicion,
    posicion: Posicion,
    image: Bitmap?
) : Actor(height, width, velocidad, direccion, posicionInicial,posicion, image) {

    var puntuacion:Puntuacion = Puntuacion(0)

    /**
     * Contiene la única instancia Loco con la cual debemos trabajar.
     * Es lo mismo que crear un campo estatico en Java
     * De esta forma podemos tener una única instancia para esta clase.
     */
    companion object {
        lateinit var uniqueLobo: Lobo
            private set
    }

    /**
     * Crea el objeto único Lobo al ser llamado por primera vez y siempre es devuelto el mismo.
     */
    fun onCreate(): Lobo {
        uniqueLobo = this
        return uniqueLobo
    }

    /**
     * Funcion que devuelve la instancia única de la Facade
     */
    public fun getInstance(): Lobo {
        return uniqueLobo
    }


    // Bando al que pertenece el Lobo (Blanco, Negro)
    var bando: Int = 0; //TODO: EN PRINCIPIO AL PRINCIPIO PODEMOS ESCOGER EL BANDO EN EL QUE ESTA EL LOBO,
                        //TODO: EN CASO DE QUE SE COMPLIQUE, ES MEJOR HACERLO RANDOM.


    enum class Bando(val id: Int){
        Blanco(0),
        Negro(1)
    }

    var objetoActivable: ObjetoActivable? = null

    var multiplicador: Int = 1

    //Variable que nos dice si es visible
    var es_visible: Boolean = true

    //Variable que nos dice si esta vivo
    var esta_vivo : Boolean = true

    /**
     * TODO: ¿Lo que hace esta clase es devolver la siguiente posición del lobo donde debe ser dibujado
     *  TODO: en función de velocidad, dirección y conociendo los fps... o es mejor que de eso se encarge el gameEngine?
     */
    override fun mover(): Posicion {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * TODO: Método en principio no utilizado por Lobo
     * QUIZÀ PARA LOS MUROS
     * EL lobo no deberia ser notificado nunca para detectar una colision, si fuese así seria diferente al resto de
     * objetos.
    */
    override fun tratarColision(objeto: Objeto) {
        //Este método desde la clase Lobo nunca será llamado
    }

    //MÉTODOS PROPIOS DE LA CLASE LOBO

    /**
     * Suma cierta puntuación teniendo en cuenta el multiplicador acumulado
     * TODO: DECIDIR DONDE SE ENCUANTRA LA PUNTUACION DEL JUEGO GUARDADA Y COMO PODEMOS MODIFICARLA
     */
    fun sumarPuntuacion(valorSumadpr:Int){
        this.puntuacion.puntuacion+=valorSumadpr
    }

    /**
     * Aumenta el valor de multiplicador acumulado
     */
    fun sumarMultiplicador(valorMultiplicador:Int){
        this.multiplicador+= valorMultiplicador
    }

    /**
     * Quita una vida al Lobo. En el momento que se pierden todas las vidas el valor esta_vivo=false.
     * TODO: EXTRAS En este metodo podriamos hacer cosas extras en el caso de que se la quiten, realentizar, o hacer inmortal por un tiempo corto
     */
    fun quitarVida(){
        // Si solo le queda una vida se quedarà con zero
        if(vida.numVide==1){
            vida.quitarVida()
            endGame()
        }else{
            vida.quitarVida()
        }
    }

    /**
     * Se encarga de acabar el juego si el Lobo se ha quedado sin vidas.
     * TODO: NO SE SI ESTO DEBE SER SINCRONO O LO DETECTA DE FORMA ASINCRONA EL GAME
     */
    fun endGame(){

    }

}