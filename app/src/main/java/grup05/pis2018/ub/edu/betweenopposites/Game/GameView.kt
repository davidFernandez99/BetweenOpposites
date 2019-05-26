package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.*
import android.view.SurfaceView
import grup05.pis2018.ub.edu.betweenopposites.Model.*
import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.MainActivity



open class GameView (context: Context, private val size: Point) : SurfaceView(context), Runnable {

    val gameThread = Thread(this)
    val contexto:Context=context
    var display:DisplayThread ?=null

    private fun prepareLevel() { // Aqui inicializaremos los objetos del juego

    }
    override fun run() {
        display= DisplayThread(gameThread,contexto,holder)
        display!!.starts()

    }
    fun onStart(){
        gameThread.start()
        if(!MainActivity.player.isPlaying){
            MainActivity.player.start()
        }
    }
    fun onResume(){
        //gameThread.resume()
        MainActivity.player.start()
        DisplayThread.paused=false
        DisplayThread.playing=true
    }
    fun onPause(){
        MainActivity.player.pause()
        DisplayThread.paused=true
        DisplayThread.playing=false
    }

    fun onStop(){

        MainActivity.player.stop()
        DisplayThread.paused=true
        DisplayThread.playing=false
    }

}