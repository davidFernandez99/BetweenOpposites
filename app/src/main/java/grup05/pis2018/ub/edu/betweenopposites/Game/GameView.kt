package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.*
import android.os.Looper
import android.view.SurfaceView
import grup05.pis2018.ub.edu.betweenopposites.Model.*
import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.MainActivity



open class GameView (context: Context, private val size: Point) : SurfaceView(context), Runnable {

    var gameThread = Thread(this)
    val contexto:Context=context
    var display:DisplayThread ?=null

    private fun prepareLevel() { // Aqui inicializaremos los objetos del juego

    }
    override fun run() {
        if(DisplayThread.paused==false){
            Looper.prepare()
            display= DisplayThread(gameThread,contexto,holder)
            display!!.starts()
        }


    }
    fun onStart(){
        gameThread.start()

    }
    fun onResume(){
        //gameThread.resume()
        MainActivity.player.start()
        display!!.resume()

    }
    fun onPause(){
        MainActivity.player.pause()
        display!!.sleep()
    }

    fun onStop(){

        MainActivity.player.stop()
        display!!.stops()

    }

}