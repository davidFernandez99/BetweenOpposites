package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
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
    lateinit var recompensa: Objeto
    // Flag que nos dice si debe devolverse el Objeto o no. Inicialmente a "false".
    var dar_recompensa: Boolean = false
    var dar_opciones: Boolean = false
    /**
     * En el caso de que se detecte la colisión con el Lobo, se trata la colisión de forma que se dan las opciones
     * y en el caso de acierto se da el premio.
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            dar_opciones=true
        }
    }
    fun darOpciones(lobo: Lobo) :ArrayList<Int> {
        var listaOpciones:ArrayList<Int> = ArrayList()
        var lobo:Lobo=Lobo.instance
        var opcion3:Int= lobo.puntuacion.puntuacion
        var opcion1:Int= (opcion3-50 .. opcion3+50).random()
        var opcion2:Int= (opcion3-50 .. opcion3+50).random()
        while(opcion1==opcion2){
            opcion2=(opcion3-50 .. opcion3+50).random()
        }
        listaOpciones.add(opcion1)
        listaOpciones.add(opcion2)
        listaOpciones.add(opcion3)
        dar_opciones=false
        return listaOpciones

    }

    fun darRecompensa():ObjetoActivable{
        var recompensa_maquina :Int= (0..3).random()
        if(recompensa_maquina==0){
            return Invisibilidad(16f,16f,Posicion(this.posicion.x,this.posicion.y+this.height+10f))
        }
        else if (recompensa_maquina==1){
            return CambioBando(16f,16f,Posicion(this.posicion.x,this.posicion.y+this.height+10f))
        }
        else{
            return AumentarVelocidad(16f,16f,Posicion(this.posicion.x,this.posicion.y+this.height+10f))
        }
    }


}