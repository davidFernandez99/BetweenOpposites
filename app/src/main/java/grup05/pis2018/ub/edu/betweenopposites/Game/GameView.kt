package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.SurfaceView
import grup05.pis2018.ub.edu.betweenopposites.Model.*


import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.FinJuegoActivity


class GameView (context: Context,private val size: Point) : SurfaceView(context), Runnable {
    private val gameThread = Thread(this)
    private var playing = true
    private var paused = false
    private var conv:Long=1000
    private var tiempo:Long =0
    private var segundos:Long =0
    private var canvas : Canvas = Canvas()
    private val paint : Paint = Paint()


    //Bitmap y Objetos de panel superior e inferior
    var bitmapVida: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.corazon_activo)
    var vida: Vida =Vida()
    var bitmapBorde:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.borde)
    var bitmapBordeSuperior:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.borde_superior)
    var bitmapPausa:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.boton_pausa)

    //Pruebas
    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbe_negro)
    var playerWolf:Lobo=Lobo.instance
    var life:Vida= Vida()
    var bando: Actor.Bando = Actor.Bando.Negro
    var orbe:Orbe= Orbe(bando,32f,32f,10f, Actor.Direccion.IZQUIERDA,Posicion(100f,100f))
    var trampa:Trampa=Trampa(16f,16f,Posicion(500f,500f))
    var bitmapOrbe: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbes)
    var bitmapTrampa: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.trampa)
    var suelo:Suelo?=null
    var bitmapMuro:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.muro)
    var bitmapSuelo:Bitmap=BitmapFactory.decodeResource(context.resources, R.drawable.suelo)
    private fun prepareLevel() { // Aqui inicializaremos los objetos del juego

    }

    override fun run() {

        var fps: Long = 1
        paint.setColor(Color.WHITE)
        tiempo = System.currentTimeMillis()

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
        playerWolf.mover(fps)
        orbe.mover(fps)
        //Move the player's wolf
        orbe.detectarColision(playerWolf)
        trampa.detectarColision(playerWolf)
        if(tiempo == conv){
            segundos++
            tiempo=0
        }



    }

    private fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
            canvas = holder.lockCanvas()

            // Draw the background color
            canvas.drawColor(Color.argb(0, 0, 0, 0))

            //Dibujamos los suelos de la sala
            var i:Int=0
            var j:Int=0
            var x:Float=0f
            var y:Float=0f
            for(i in 0..20){
                y=0f
                for(j in 0..10){
                    suelo= Suelo(32f,32f,Posicion(x,y))
                    suelo!!.draw(canvas,bitmapSuelo)
                    y+=96f

                }
                x+=96f
            }


            //Now draw the player wolf

            trampa.draw(canvas,bitmapTrampa)
            orbe.draw(canvas,bitmapOrbe)

            //Draw all the game objects here
            playerWolf.draw(canvas,bitmap)
            canvas.drawBitmap(bitmapBordeSuperior, 0f, 0f, paint)
            canvas.drawBitmap(bitmapBorde, 0f, 1080f, paint)
            canvas.drawBitmap(bitmapPausa, 1920f,1080f,paint)

            canvas.drawText(segundos.toString(), 700f,0f,paint)
            if(playerWolf.vida.numVide==3){
                vida.draw(canvas, 860f,0f,bitmapVida)
                vida.draw(canvas, 960f,0f,bitmapVida)
                vida.draw(canvas, 1060f,0f,bitmapVida)

            }
            else if(playerWolf.vida.numVide==2){
                vida.draw(canvas, 860f,0f,bitmapVida)
                vida.draw(canvas, 960f,0f,bitmapVida)

            }
            else if(playerWolf.vida.numVide==1){
                vida.draw(canvas, 860f,0f,bitmapVida)

            }


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

    fun onDestroy(){
        gameThread.destroy()
    }

    fun onStop(){
        pause()
        gameThread.stop()

    }


}