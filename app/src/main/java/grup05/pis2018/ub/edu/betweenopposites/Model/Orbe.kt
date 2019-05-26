package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R
import java.lang.Math.*
import java.util.*
import kotlin.math.atan
import kotlin.random.Random.Default.nextInt

class Orbe(
    bando: Bando,
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicion: Posicion

) : Actor(height, width, velocidad, direccion, posicion) {

    var darPuntuacion: Int = 10
    var bando: Bando = bando; //Esto habra que hacerlo aleatorio
    var direccionChoque:Direccion?=null //Variable para almacenar la dirección con la que colisiona con el muro

    override fun mover(fps: Long) { //Método para el movimiento del orbe según su dirección, y fps
        if (this.direccion == Direccion.ABAJO) {
            if (this.posicion.y + this.height >= 1080f) {
                canviarDireccion()

            } else {
                this.posicion.y += this.velocidad / fps
            }

        }
        if (this.direccion == Direccion.ARRIBA) {
            if (this.posicion.y - this.height <= 20f) {
                canviarDireccion()

            } else {
                this.posicion.y -= velocidad / fps
            }

        }

        if (this.direccion == Direccion.IZQUIERDA) {
            if (this.posicion.x - this.width + 16f == 0f) {
                canviarDireccion()
            } else {
                this.posicion.x -= this.velocidad / fps
            }

        }
        if (this.direccion == Direccion.DERECHA) {
            if (posicion.x + width +16f == 1920f) {
                canviarDireccion()
            } else {
                this.posicion.x += this.velocidad / fps
            }

        }

    }

    /**
     *
     * Método con tal de detectar si el orbe está en colision conun objeto tanto si colisiona con un muro como si colisiona con un objeto Lobo
     * Si el orbe es del mismo lado del  lobo seguirá un patrón de movimiento de: DiagonaArribalIzquierda, DiagonalAbajoIzquierda,DigonalAbajoDerecha y DiagonalArribaDerecha
     * Esos cuatro movimiento del orbe irán cambiando cuando colisione con alún objeto muro
     *
     * Si el orbe colisiona con un objeto Lobo ,si es del bando contrario a éste le quitara puntuación a éste y desaparecerá este orbe
     * Si el orbe es del mismo bando del lobo le augmentará puntos según el multiplicador que lleve en ese momento
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            var lobo: Lobo = objeto as Lobo
            if(lobo.bando==Bando.Neutro){
                lobo.bando=this.bando
                lobo.sumarPuntuacion(darPuntuacion)
                es_visible=false
            }
            else if (lobo.bando == this.bando) {
                //Le da puntos al lobo
                lobo.sumarPuntuacion(darPuntuacion)
                es_visible=false
            } else {
                //Le quita puntos al lobo
                lobo.quitarPuntuacion(darPuntuacion)
                es_visible=false
            }
        }

    }

    fun canviarDireccion() { //Método para canviar la dirección del orbe cuando colisiona con un muro para que no siempre se mueva de forma lineal
        if (this.bando == Lobo.instance.bando){
            calcularDireccion()

        }else {
            var listAux = (1..100)
            if (listAux.random() > 98){
                this.direccion = listOf(Direccion.ABAJO,
                    Direccion.ARRIBA,
                    Direccion.IZQUIERDA,
                    Direccion.DERECHA).random()
            }else{
                this.direccion = this.direccion
            }
        }
        
    }

    fun returnPosicion(){ //Método usado como auxiliar para retornar a posiciones anteriores a la colision de un muro
        if(this.direccionChoque==Direccion.ARRIBA){
            this.posicion.y +=1f
        }
        if(this.direccionChoque==Direccion.DERECHA){
              this.posicion.x-=1f
        }
        if(this.direccionChoque==Direccion.ABAJO){
            this.posicion.y -=1f
        }
        if(this.direccionChoque==Direccion.IZQUIERDA){
            this.posicion.x+=1f
        }
    }

    private fun calcularDireccion() {
        if (this.direccion in posiblesDirecciones()){
            this.direccion = this.direccion
        }else {
            this.direccion = posiblesDirecciones().random()
        }
    }

    private fun posiblesDirecciones(): List<Direccion> {
        if (this.posicion.x < Lobo.instance.posicion.x && this.posicion.y == Lobo.instance.posicion.y){
            return listOf(Direccion.DERECHA)
        }else if (this.posicion.x < Lobo.instance.posicion.x && this.posicion.y < Lobo.instance.posicion.y){
            return listOf(Direccion.DERECHA,Direccion.ABAJO)
        }else if (this.posicion.x == Lobo.instance.posicion.x && this.posicion.y < Lobo.instance.posicion.y){
            return listOf(Direccion.ABAJO)
        }else if (this.posicion.x > Lobo.instance.posicion.x && this.posicion.y < Lobo.instance.posicion.y){
            return listOf(Direccion.ABAJO,Direccion.IZQUIERDA)
        }else if (this.posicion.x > Lobo.instance.posicion.x && this.posicion.y == Lobo.instance.posicion.y){
            return listOf(Direccion.IZQUIERDA)
        }else if (this.posicion.x > Lobo.instance.posicion.x && this.posicion.y > Lobo.instance.posicion.y) {
            return listOf(Direccion.ARRIBA, Direccion.IZQUIERDA)
        }else if (this.posicion.x == Lobo.instance.posicion.x && this.posicion.y > Lobo.instance.posicion.y) {
            return listOf(Direccion.ARRIBA)
        }else {
            return listOf(Direccion.ARRIBA,Direccion.DERECHA)
        }
    }

    fun canviarDireccionMuro() {
        if (this.bando == Lobo.instance.bando) {
            var posiblesDirecciones: MutableList<Direccion> = posiblesDirecciones() as MutableList<Direccion>
            for (direccion in posiblesDirecciones) {
                if (direccion != this.direccionChoque) {
                    this.direccion = direccion
                }
            }
        } else {
            when (this.direccionChoque) {
                Direccion.IZQUIERDA -> this.direccion = Direccion.DERECHA
                Direccion.DERECHA -> this.direccion = Direccion.IZQUIERDA
                Direccion.ARRIBA -> this.direccion = Direccion.ABAJO
                Direccion.ABAJO -> this.direccion = Direccion.ARRIBA
            }
        }
    }


    fun printOrbe() {
        println("Orbe ${bando.name} POSICION: [${posicion.x_sala},${posicion.y_sala}]")

    }
}