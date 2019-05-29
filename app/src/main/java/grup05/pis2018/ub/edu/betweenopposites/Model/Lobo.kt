package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R


/**
 *
 */
class Lobo(
    vida: Vida,
    bando: Bando,
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicion: Posicion
) : Actor(height, width, velocidad, direccion, posicion) {
    var vida: Vida = vida
    var velocidadCambiada:Float=velocidad
    var puntuacion: Puntuacion = Puntuacion(0)
    var vulnerable: Boolean = true
    var bando: Bando = bando // Bando al que pertenece el Lobo (Blanco, Negro)
    var velocidadInicial: Float = velocidad
    var objetoActivable: ObjetoActivable? = null
    var multiplicador: Int = 1 //Multiplicador de la puntuación que obtiene de los orbes
    //Variable que nos dice si esta vivo
    var esta_vivo: Boolean = true //Variable para determinar si el Lobo esta vivo
    var final:Boolean=false //Variable para saber si ha acabado la partida por falta de vida
    var direccionChoque:Direccion?=null //Variable para guardar la dirección con la que colisiona con un muro
    var direccionIvalida:Direccion?=null //Variable para guardar la dirección que no podrás moverte ya que acabas de colisionar con un muro yendo a esa dirección
    /**
     * Contiene la única instancia Loco con la cual debemos trabajar.
     * Es lo mismo que crear un campo estatico en Java
     * De esta forma podemos tener una única instancia para esta clase.
     */
    companion object {
        var life: Vida = Vida()
        var bando: Bando = Bando.Neutro
        var instance = Lobo(life, bando, 32f, 32f, 120f, Direccion.PARADO, Posicion(100f, 860f))

    }

    /**
     * Funcion que devuelve la instancia única de la Facade
     */


    /**
     * Función que mueve al lobo según su dirección, velocidad y FPS
     */
    override fun mover(fps: Long) {

        if (direccion == Direccion.ABAJO) {
            if (posicion.y + height >= 1080f) {
                velocidad = 0f
            } else {
                posicion.y += velocidad / fps
            }
        }
        if (direccion == Direccion.ARRIBA && posicion.y > velocidad / fps) {
            if (posicion.y - height <= 20f) {
                velocidad = 0f
            } else {
                posicion.y -= velocidad / fps
            }
        }

        if (direccion == Direccion.IZQUIERDA && posicion.x > velocidad / fps) {
            if (posicion.x - width + 16f == 0f) {
                velocidad = 0f
            } else {
                posicion.x -= velocidad / fps
            }
        }
        if (direccion == Direccion.DERECHA) {
            if (posicion.x + width +16f == 1920f) {
                velocidad = 0f
            } else {
                posicion.x += velocidad / fps
            }
        }
        this.direccionIvalida=null

    }

    /**
     * EL lobo no deberia ser notificado nunca para detectar una colision, si fuese así seria diferente al resto de
     * objetos.
     */
    override fun tratarColision(objeto: Objeto) {
        //Este método desde la clase Lobo nunca será llamado
    }

    //MÉTODOS PROPIOS DE LA CLASE LOBO

    /**
     * Suma cierta puntuación teniendo en cuenta el multiplicador acumulado
     */
    fun sumarPuntuacion(valorSumadpr: Int) {
        this.puntuacion.puntuacion += valorSumadpr * this.multiplicador
        Facade.signo=1
        Facade.ultimaPuntuacion=valorSumadpr*this.multiplicador
    }

    /**
     * Resta cierta puntuación teniendo en cuenta el multiplicador acumulado TODO(al restar no se si se tiene en cuenta multiplicador supongo que si)
     */

    fun quitarPuntuacion(valorSumador: Int) {
        if (this.puntuacion.puntuacion < valorSumador*this.multiplicador) {
            this.puntuacion.puntuacion = valorSumador*this.multiplicador-this.puntuacion.puntuacion
            this.cambioBando()
        } else {
            this.puntuacion.puntuacion -= valorSumador*this.multiplicador
        }
        Facade.signo=2
        Facade.ultimaPuntuacion=valorSumador*this.multiplicador
    }

    /**
     * Aumenta el valor de multiplicador acumulado
     */
    fun sumarMultiplicador(valorMultiplicador: Int) {
        this.multiplicador += valorMultiplicador
    }

    /**
     * Quita una vida al Lobo. En el momento que se pierden todas las vidas el valor esta_vivo=false.
     */
    fun quitarVida() {
        // Si solo le queda una vida se quedarà con zero
        if (vida.numVide == 1) {
            vida.quitarVida()
            esta_vivo =
                false //Cada iteración del bucle después de comprobar las colisiones comprobaremos si el lobo esta vivo o no
        } else {
            vida.quitarVida()
        }
    }
    //Método para restaurar la velocidad a la del comienzo para el aumento de velocidad y para restablecer la velocidad al chocar con un muro y volver a moverse
    fun restarurarVelocidad() {
        this.velocidad = velocidadInicial
    }

    //Función que devuleve si la partida ha terminado o no
    fun endgame():Boolean{
        return final
    }

    //Método para retroceder de posición cuando colisiona con un muro hasta llegar a dejar de colisionar con este para volver a poder moverse
    fun returnPosicion(){
        if(this.direccionChoque==Direccion.ARRIBA){
            direccionIvalida=Direccion.ARRIBA
            this.posicion.y +=1f
        }
        if(this.direccionChoque==Direccion.DERECHA){
            direccionIvalida=Direccion.DERECHA
            this.posicion.x-=1f
        }
        if(this.direccionChoque==Direccion.ABAJO){
            direccionIvalida=Direccion.ABAJO
            this.posicion.y -=1f
        }
        if(this.direccionChoque==Direccion.IZQUIERDA){
            direccionIvalida=Direccion.IZQUIERDA
            this.posicion.x+=1f
        }
    }
    fun setVulnerabilidad(trampa:Trampa){
        if(this.vulnerable==false){
            if(trampa.comprobarColision(this)==false){
                vulnerable=true
            }
        }
    }

    fun cambioBando(){
        if(this.bando==Bando.Blanco){
            this.bando=Bando.Negro
        }
        else if(this.bando==Bando.Neutro){
            var rand:Int=(0..1).random()
            if(rand==0){
                this.bando=Bando.Blanco
            }
            else{
                this.bando= Bando.Negro
            }
        }
        else{
            this.bando=Bando.Blanco
        }
    }

}