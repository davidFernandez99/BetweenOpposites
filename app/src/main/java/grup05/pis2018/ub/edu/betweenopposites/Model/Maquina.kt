package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R


/**
 * Objeto en el juego que se encuentra en las salas especiales, cuando chocamos con ella nos da la oportunidad de
 * ganar un objeto, sumadro, multiplicador, o objeto activo, tras adivinar una pregunta
 * ¿Cual crees que es tu puntuación?.
 * La parte matematica del juego te obliga a llevar la cuenta de tu puntuación para poder ganar objetos extras y
 * poder sacar mayores puntuaciones.
 * La maquina da opciones para posibles respuestas y si se acierta se obtiene la recompensa.
 */
class Maquina(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    Objeto(height, width, posicion) {

    // Contiene la recompensa que se da al jugador en caso de que se adivine la respuesta correcta
    lateinit var recompensa: ObjetoActivable
    // Flag que nos dice si debe devolverse el Objeto o no. Inicialmente a "false".
    var dar_recompensa: Boolean = false
    var dar_opciones: Boolean = true
    /**
     * En el caso de que se detecte la colisión con el Lobo, se trata la colisión de forma que se dan las opciones
     * y en el caso de acierto se da el premio.
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            var lobo: Lobo = objeto as Lobo
            lobo.velocidad=0f
            lobo.direccionChoque=lobo.direccion

            while(comprobarColision(lobo)==true){ //Si detecta una colision le hace retroceder hasta que deje de detectarla
                lobo.returnPosicion()
            }
            lobo.returnPosicion()
            lobo.direccion=Direccion.PARADO //Establece que la dirección al colisionar con el muro es PARADO
        }
    }
    fun darOpciones(lobo: Lobo) :ArrayList<Int> {
        var listaOpciones:ArrayList<Int> = ArrayList()
        var lobo:Lobo=Lobo.instance
        var opcion3:Int= lobo.puntuacion
        var opcion1:Int=0
        var opcion2:Int=0
        var primero:Int=0
        var segundo:Int=0
        if(opcion3-50<0){
            opcion1 = (opcion3+1 .. opcion3+100).random()
            opcion2 = (opcion3+1 .. opcion3+100).random()
        }
        else{
            opcion1= (opcion3-50 .. opcion3+50).random()
            opcion2= (opcion3-50.. opcion3+50).random()
        }
        while(opcion1==opcion2 || opcion1==opcion3 || opcion2==opcion3){
            opcion2=(opcion3-50 .. opcion3+50).random()
            opcion1=(opcion3-50.. opcion3+50 ).random()
        }

        primero = (0..2).random()
        if(primero==0){
            listaOpciones.add(opcion1)
            segundo=(0..1).random()
            if(segundo==0){
                listaOpciones.add(opcion2)
                listaOpciones.add(opcion3)
            }
            else{
                listaOpciones.add(opcion3)
                listaOpciones.add(opcion2)

            }
        }
        else if(primero==1){
            listaOpciones.add(opcion2)
            segundo=(0..1).random()
            if(segundo==0){
                listaOpciones.add(opcion1)
                listaOpciones.add(opcion3)
            }
            else{
                listaOpciones.add(opcion3)
                listaOpciones.add(opcion1)
            }
        }
        else{
            listaOpciones.add(opcion3)
            segundo=(0..1).random()
            if(segundo==0){
                listaOpciones.add(opcion1)
                listaOpciones.add(opcion2)
            }
            else{
                listaOpciones.add(opcion2)
                listaOpciones.add(opcion1)
            }
        }

        dar_opciones=false
        return listaOpciones

    }

    fun darRecompensa():ObjetoActivable{
        var recompensa_maquina :Int= (0..2).random()
        if(recompensa_maquina==0){
            return Invisibilidad(16f,16f,Posicion(this.posicion.x,this.posicion.y+200f))
        }
        else if (recompensa_maquina==1){
            return CambioBando(16f,16f,Posicion(this.posicion.x,this.posicion.y+200f))

        }
        else{
            return AumentarVelocidad(16f,16f,Posicion(this.posicion.x,this.posicion.y+200f))
        }
    }

    companion object{
        fun comprobarRespuestaMaquina(puntuacio:Int): Boolean {

            if (puntuacio == Lobo.instance.puntuacion) {
                return true
            }
            return false
        }
    }

    override fun draw(canvas: Canvas, image: Bitmap){
        canvas.drawBitmap(image,this.posicion.x-40f,this.posicion.y-45f,paint)
    }
    fun comprobarColision(objeto:Objeto):Boolean{ //Método para detectar si hay o no colision entre un muro y un actor

        if (this.posicion.x - this.width < objeto.posicion.x + objeto.width
            && this.posicion.x + this.width > objeto.posicion.x - objeto.width
            && this.posicion.y - this.height < objeto.posicion.y + objeto.height
            && this.posicion.y + this.height > objeto.posicion.y - objeto.height
        ) {
            return true
        }
        //Devuelve si ha colisionado o no con ese objeto
        return false
    }
}