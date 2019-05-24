package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import grup05.pis2018.ub.edu.betweenopposites.Model.Tiempo

class DisplayThread (gameThread:Thread,contexto: Context,holder:SurfaceHolder){
    val gameThread= gameThread
    val contexto=contexto
    var holder:SurfaceHolder=holder
    val paint= Paint()
    var game:GameEngine?=null
    companion object{
        val MAX_TIEMPO_VELOCIDAD: Long = 5000
        val MAX_TIEMPO_INVISIBLE: Long = 5000
        val MAX_TIEMPO_VULNERABLE: Long = 1000
        var conv: Long = 1000
        var playing = true
        var paused = false
        var fin_juego=false
        var dando_opciones=false
        var comprobar_opcion=false
        var opcion:Int=0
        var fallar=false
        var mostrar_Pause=false
        var activar_efecto=false
        var tiempoVelocidad:Boolean= false
        var tiempoInvisibilidad:Boolean=false
        var tiempoInicial:Long=0
    }


    fun starts() {
        game=GameEngine(paint,contexto,holder)
        var fps: Long = 1
        //tiempo.start()
        paint.setColor(Color.WHITE)
        game!!.inicializarVariable()
        while (playing) {

            // Capture the current time
            val startFrameTime = System.currentTimeMillis()
            tiempoInicial=System.currentTimeMillis()

            // Update the frame
            if (!paused) {
                game!!.update(fps)
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
        sleep()
        //gameThread.stop()
    }

    fun sleep() {
        playing = false

    }
    fun resume(){
        playing = true
        //gameThread.start()
    }

}