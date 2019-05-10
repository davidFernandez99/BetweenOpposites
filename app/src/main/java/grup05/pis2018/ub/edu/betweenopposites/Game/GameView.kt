package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.*
import android.view.SurfaceView
import grup05.pis2018.ub.edu.betweenopposites.Model.Lobo
import grup05.pis2018.ub.edu.betweenopposites.Model.Vida
import grup05.pis2018.ub.edu.betweenopposites.R


class GameView(context: Context, private val size: Point) : SurfaceView(context), Runnable {
    private val gameThread = Thread(this)
    private var playing = true
    private var paused = false

    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()

    //Players
    var bitmapVida: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.corazon_activo)
    var vida: Vida = Vida()

    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbe_blanco)
    var playerWolf: Lobo = Lobo.instance

    private fun prepareLevel() { // Aqui inicializaremos los objetos del juego

    }

    override fun run() {
        var fps: Long = 1


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

    private fun update(fps: Long) {  //Aqui actualizaremos el estado de cada objeto

        //Move the player's wolf

        playerWolf.mover(fps)


    }

    private fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
            canvas = holder.lockCanvas()

            // Draw the background color
            canvas.drawColor(Color.argb(0, 0, 0, 0))
            //Draw all the game objects here
            if (playerWolf.vida.numVide == 3) {
                vida.draw(canvas, 800f, 0f, bitmapVida)
                vida.draw(canvas, 900f, 0f, bitmapVida)
                vida.draw(canvas, 1000f, 0f, bitmapVida)

            } else if (playerWolf.vida.numVide == 2) {
                vida.draw(canvas, 800f, 0f, bitmapVida)
                vida.draw(canvas, 900f, 0f, bitmapVida)
            } else {
                vida.draw(canvas, 800f, 0f, bitmapVida)
            }

            //Now draw the player wolf
            playerWolf.draw(canvas, bitmap)
            canvas.drawColor(Color.argb(0, 0, 0, 0))

            // Draw everything to the screen
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun resume() {
        playing = true
        prepareLevel()
        gameThread.start()

    }

    fun pause() {
        playing = false
    }


}