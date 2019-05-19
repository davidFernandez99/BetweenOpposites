package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder

class DisplayThread (gameThread:Thread,contexto: Context,holder:SurfaceHolder){
    val gameThread= gameThread
    val contexto=contexto
    var holder:SurfaceHolder=holder
    val paint= Paint()
    var game:GameEngine?=null
    companion object{
        var playing = true
        var paused = false
        var fin_juego=false
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