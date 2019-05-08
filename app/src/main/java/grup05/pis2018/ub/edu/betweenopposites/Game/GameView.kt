package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import android.view.SurfaceView
import grup05.pis2018.ub.edu.betweenopposites.Model.Lobo

class GameView (context: Context,private val size: Point) : SurfaceView(context), Runnable {
    private val gameThread = Thread(this)

    private var playing = false
    private var paused = true

    private var canvas : Canvas = Canvas()
    private val paint : Paint = Paint()

    //Players
    private var playerWolf: Lobo = Lobo() //El cosntructor està incpmpleto

    private fun prepareLevel() { // Aqui inicializaremos los objetos del juego

    }

    override fun run() {
        var fps: Long = 0
        

        while (playing) {

            // Capture the current time
            val startFrameTime = System.currentTimeMillis()

            // Update the frame
            if (!paused) {
                update(fps)
            }

            // Draw the frame
            draw()

            // Calculate the fps rate this frame
            val timeThisFrame = System.currentTimeMillis() - startFrameTime
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame
            }
        }
    }

    private fun update(fps: Long){  //Aqui actualizaremos el estado de cada objeto

        //Move the player's wolf
        playerWolf.update(fps)
    }

    private fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
            canvas = holder.lockCanvas()

            // Draw the background color
            canvas.drawColor(Color.argb(255, 0, 255, 0))

            //Draw all the game objects here

            //Now draw the player wolf
            canvas.drawBitmap(playerWolf.bitmap, playerWolf.position.left,
                playerWolf.position.top, paint)

            //Draw the score and the remaining lives
            canvas.drawText("Puntacion:     Vidas:    ", 20f, 70f, paint)

            // Draw everything to the screen
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun resume(){
        playing = true
        prepareLevel()
        gameThread.start()

    }

    fun pause(){
        playing = false
    }



}