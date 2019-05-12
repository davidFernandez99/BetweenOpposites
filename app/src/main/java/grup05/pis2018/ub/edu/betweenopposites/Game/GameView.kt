package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.SurfaceView
import grup05.pis2018.ub.edu.betweenopposites.Model.*


import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.FinJuegoActivity


class GameView (context: Context,private val size: Point) : SurfaceView(context), Runnable {
    private val MAX_TIEMPO_VELOCIDAD: Long = 10000
    private val MAX_TIEMPO_INVISIBLE: Long = 5000
    private val MAX_TIEMPO_VULNERABLE: Long = 2000
    private val gameThread = Thread(this)
    private var playing = true
    private var paused = false
    private var conv:Long=1000
    private var tiempo:Tiempo  = Tiempo(conv,conv)
    private var tirmpoVulnerable:Tiempo=Tiempo(1000,MAX_TIEMPO_VULNERABLE)
    private var tiempoVelocidad:Tiempo=Tiempo(5000,MAX_TIEMPO_VELOCIDAD)
    private var tiempoInvisibilidad:Tiempo=Tiempo(4000,MAX_TIEMPO_INVISIBLE)
    private var segundos:Long =0
    private var canvas : Canvas = Canvas()
    private val paint : Paint = Paint()


    //Bitmap de los diferentes objetos que imprimiremos en el canvas
    var bitmapVida: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.corazon_activo)
    var bitmapBorde:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.borde)
    var bitmapBordeSuperior:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.borde_superior)
    var bitmapPausa:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.boton_pausa)
    var bitmapOrbeNegro: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbe_negro)
    var bitmapOrbeBlanco: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbes)
    var bitmapTrampa: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.trampa)
    var bitmapMuro:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.muro)
    var bitmapSuelo:Bitmap=BitmapFactory.decodeResource(context.resources, R.drawable.suelo)
    var bitmapTeleporActivado:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.boton_tp_activado)
    var bitmapTeleporDesactivado:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.boton_tp_desactivado)
    var bitmapInv:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.objeto_invisibilidad)
    var bitmapCambio:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.objeto_cambiobando)
    var bitmapAumentoVel:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.objeto_velocidad)
    var bitmapSumador:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.sumador)
    var bitmapMultiplicador:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.multiplicador)
    var bitmapBordeSimple:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.borde_singular)
    var bitmapBordeDoble:Bitmap=BitmapFactory.decodeResource(context.resources,R.drawable.borde_doble)

    //Instanciamos un objeto vida para poder considerar cuantas vidas dibujar en el canvas
    var vida: Vida =Vida()
    //Cogemos la instancia del único Lobo
    var lobo:Lobo=Lobo.instance
    var comprobar_vulnerabilidad:Boolean=false
    var compobar_aumento:Boolean=false

    //Pruebas
    //Pruebas

    var bando: Actor.Bando = Actor.Bando.Negro
    var orbe:Orbe= Orbe(bando,32f,32f,10f, Actor.Direccion.IZQUIERDA,Posicion(500f,500f))
    var trampa:Trampa=Trampa(32f,32f,Posicion(500f,500f))
    var suelo:Suelo?=null
    var muro:Muro?=null
    var aumentarVelocidad:AumentarVelocidad= AumentarVelocidad(32f,32f, Posicion(300f,300f))
    private fun prepareLevel() { // Aqui inicializaremos los objetos del juego

    }

    override fun run() {

        var fps: Long = 1
        tiempo.start()
        paint.setColor(Color.WHITE)


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
        lobo.mover(fps)
        orbe.mover(fps)
        //Move the player's wolf
        if(orbe.es_visible){
            orbe.detectarColision(lobo)
        }

        comprobar_vulnerabilidad=trampa.detectarColision(lobo)
        if(comprobar_vulnerabilidad==true){
            lobo.vulnerable=false
            tirmpoVulnerable.start()
        }
        if(tirmpoVulnerable.finish==true){
            lobo.vulnerable=true
            tirmpoVulnerable.finish=false
        }
        if(tiempo.finish==true){
            segundos++
        }
        compobar_aumento=aumentarVelocidad.detectarColision(lobo)
        if(compobar_aumento==true){
            tiempoVelocidad.start()
        }
        if(tiempoVelocidad.finish==true){
            lobo.restarurarVelocidad()
            tirmpoVulnerable.finish=false
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
            var i: Int = 0
            var j: Int = 0
            var x: Float = 0f
            var y: Float = 0f
            for (i in 0..20) {
                y = 0f
                for (j in 0..10) {
                    suelo = Suelo(16f, 16f, Posicion(x, y))
                    suelo!!.draw(canvas, bitmapSuelo)

                    y += 96f

                }
                x += 96f
            }
            i= 0
            j= 0
            x = 0f
            y= 0f
            for(i in 0..30){
                muro=Muro(64f,64f,Posicion(x,20f))
                muro!!.draw(canvas,bitmapMuro)
                muro!!.detectarColision(lobo)
                orbe.detectarColision(muro!!)
                x+=64f
            }
            i=0
            x=0f
            for(i in 0..30){
                muro=Muro(64f,64f,Posicion(x,960f))
                muro!!.draw(canvas,bitmapMuro)
                muro!!.detectarColision(lobo)
                orbe.detectarColision(muro!!)
                x+=64f
            }
            i=0
            x=0f
            for(i in 0..15){
                muro=Muro(64f,64f,Posicion(0f,y))
                muro!!.draw(canvas,bitmapMuro)
                muro!!.detectarColision(lobo)
                orbe.detectarColision(muro!!)
                y+=64f
            }
            y=0f
            i=0
            for(i in 0..15){
                muro=Muro(64f,64f,Posicion(1850f,y))
                muro!!.draw(canvas,bitmapMuro)
                muro!!.detectarColision(lobo)
                orbe.detectarColision(muro!!)
                y+=64f
            }

            //Now draw the player wolf

            trampa.draw(canvas, bitmapTrampa)
            if(orbe.es_visible){
                orbe.draw(canvas, bitmapOrbeBlanco)
            }
            if(aumentarVelocidad.visible==true){
                aumentarVelocidad.draw(canvas,bitmapAumentoVel)
            }

            //Draw all the game objects here
            lobo.draw(canvas, bitmapOrbeNegro)
            canvas.drawBitmap(bitmapBordeSuperior, 0f, 0f, paint)
            canvas.drawBitmap(bitmapBorde, 0f, 1080f, paint)
            canvas.drawBitmap(bitmapPausa, 1900f, 0f, paint)

            canvas.drawText(segundos.toString(), 700f, 0f, paint)
            if (lobo.vida.numVide == 3) {
                vida.draw(canvas, 860f, 0f, bitmapVida)
                vida.draw(canvas, 960f, 0f, bitmapVida)
                vida.draw(canvas, 1060f, 0f, bitmapVida)

            } else if (lobo.vida.numVide == 2) {
                vida.draw(canvas, 860f, 0f, bitmapVida)
                vida.draw(canvas, 960f, 0f, bitmapVida)

            } else if (lobo.vida.numVide == 1) {
                vida.draw(canvas, 860f, 0f, bitmapVida)

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

    fun onDestroy() {
        gameThread.destroy()
    }

    fun onStop() {
        pause()
        gameThread.stop()

    }


}