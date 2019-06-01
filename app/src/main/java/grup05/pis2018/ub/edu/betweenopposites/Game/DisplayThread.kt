package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.os.Handler
import android.view.SurfaceHolder
import grup05.pis2018.ub.edu.betweenopposites.Model.Lobo
import grup05.pis2018.ub.edu.betweenopposites.Model.Tiempo
import grup05.pis2018.ub.edu.betweenopposites.Model.TiempoObjeto

class DisplayThread (gameThread:Thread,contexto: Context,holder:SurfaceHolder){
    val gameThread= gameThread
    val contexto=contexto
    var holder:SurfaceHolder=holder
    val paint= Paint()
    var game:GameEngine?=null
    var segundos:Int=0
    var fps:Long=1
    companion object{
        var playing = true
        var paused = false
        var fin_juego=false
        var mostrar_Pause=false
        var activar_efecto=false
        var TIEMPO_SEGUNDOS:Long=1000
        var conv: Long = 1000
        var tiempo:Tiempo=Tiempo(TIEMPO_SEGUNDOS,conv)
        var segundos:Long=0
        var activar_efecto_inv:Boolean=false
        var activar_efecto_vel:Boolean=false
        var segundos_inv:Int=0
        var segundos_vel:Int=0
        //Timers
        var MAX_TIEMPO_VELOCIDAD: Long = 1000
        var MAX_TIEMPO_INVISIBLE: Long = 1000
        var tiempoInvisibilidad: TiempoObjeto=TiempoObjeto(MAX_TIEMPO_INVISIBLE,conv)
        var tiempoVelocidad: TiempoObjeto=TiempoObjeto(MAX_TIEMPO_VELOCIDAD,conv)


    }


    fun starts() {
        game=GameEngine(paint,contexto,holder)

        //tiempo.start()
        paint.setColor(Color.WHITE)
        game!!.inicializarVariable()
        tiempo.start()
        while (playing) {

            // Capture the current time
            val startFrameTime = System.currentTimeMillis()

            // Update the frame
            if (!paused) {

                game!!.update(fps)
            }
            if(tiempo.finish==true &&!paused){
                DisplayThread.segundos++
                tiempo.finish=false
                tiempo.start()
            }
            if(tiempoInvisibilidad.finish==true &&!paused){
                segundos_inv++
                tiempoInvisibilidad.finish=false
                tiempoInvisibilidad.start()
            }
            if(tiempoVelocidad.finish==true &&!paused){
                segundos_vel++
                tiempoVelocidad.finish=false
                tiempoVelocidad.start()
            }
            if(segundos_vel>=10){
                Lobo.instance.restarurarVelocidad()
                tiempoVelocidad.finish=false
                segundos_vel=0
            }
            if(segundos_inv>=10){
                Lobo.instance.es_visible = true
                tiempoInvisibilidad.finish=false
                segundos_inv=0
            }

            // Draw the frame
            game!!.draw()

            // Calculate the fps rate this frame
            val timeThisFrame = System.currentTimeMillis() - startFrameTime
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame
            }
        }
    }

    fun stops() {
        paused = true
        playing = false
    }

    fun sleep() {
        paused = true
        playing = false

    }
    fun resume(){
        paused = false
        playing = true

    }

}