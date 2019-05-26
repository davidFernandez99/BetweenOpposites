package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.SurfaceHolder
import grup05.pis2018.ub.edu.betweenopposites.Model.Lobo
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
        var segundos:Int=0
        var tiempoInvisible= false
        var tiempoVel=false
        val tiempo=object : CountDownTimer (1000,1000){
            override fun onTick(millisUntilFinished:Long){

            }
            override fun onFinish(){
                if(paused!=true){
                    segundos++
                }
            }
        }
        val tiempoVelocidad=object : CountDownTimer (MAX_TIEMPO_VELOCIDAD,1000){
            override fun onTick(millisUntilFinished:Long){
                if (Lobo.instance!!.velocidad == 0f ) {
                    Lobo.instance!!.velocidad = Lobo.instance!!.velocidadCambiada
                }
            }
            override fun onFinish(){
                tiempoVel=true
            }
        }
        val tiempoInvisibilidad=object : CountDownTimer (MAX_TIEMPO_INVISIBLE,1000){
            override fun onTick(millisUntilFinished:Long){

            }
            override fun onFinish(){
                tiempoInvisible=true
            }
        }
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



            // Update the frame
            if (!paused) {
                tiempo.start()
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