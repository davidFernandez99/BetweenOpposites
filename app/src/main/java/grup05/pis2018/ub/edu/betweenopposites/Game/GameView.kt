package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceView
import grup05.pis2018.ub.edu.betweenopposites.Model.*


import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.FinJuegoActivity
import grup05.pis2018.ub.edu.betweenopposites.View.UnJugador


class GameView (context: Context,private val size: Point) : SurfaceView(context), Runnable {
    private val MAX_TIEMPO_VELOCIDAD: Long = 5000
    private val MAX_TIEMPO_INVISIBLE: Long = 5000
    private val MAX_TIEMPO_VULNERABLE: Long = 1000
    private val gameThread = Thread(this)
    private var playing = true
    var paused = false
    private var conv: Long = 1000
    private var tiempo: Tiempo = Tiempo(10000, conv)
    private var tirmpoVulnerable: Tiempo = Tiempo(MAX_TIEMPO_VULNERABLE, 1000)
    private var tiempoVelocidad: Tiempo = Tiempo(MAX_TIEMPO_VELOCIDAD, 1000)
    private var tiempoInvisibilidad: Tiempo = Tiempo(MAX_TIEMPO_INVISIBLE, 1000)
    private var segundos: Long = 0
    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()

    //Bitmap de los diferentes objetos que imprimiremos en el canvas
    var bitmapBorde: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.borde)
    var bitmapBordeSuperior: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.borde_superior)
    var bitmapPausa: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.boton_pausa)
    var bitmapOrbeNegro: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbe_negro)
    var bitmapOrbeBlanco: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbes)
    var bitmapTrampa: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.trampa)
    var bitmapMuro: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.muro)
    var bitmapSuelo: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.suelo)
    var bitmapTeleporActivado: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.boton_tp_activado)
    var bitmapTeleporDesactivado: Bitmap =  BitmapFactory.decodeResource(context.resources, R.drawable.boton_tp_desactivado)
    var bitmapInv: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.objeto_invisibilidad)
    var bitmapCambio: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.objeto_cambiobando)
    var bitmapAumentoVel: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.objeto_velocidad)
    var bitmapSumador: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sumador)
    var bitmapMultiplicador: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.multiplicador)
    var bitmapBordeSimple: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.borde_singular)
    var bitmapBordeDoble: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.borde_doble)
    var bitmapPuerta: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.puerta)
    var bitmapOrbeRaro: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.orbe_raro)
    //Instanciamos un objeto vida para poder considerar cuantas vidas dibujar en el canvas
    var vida: Vida = Vida()
    //Cogemos la instancia del único Lobo
    var lobo: Lobo = Lobo.instance
    var comprobar_vulnerabilidad: Boolean = false
    var compobar_aumento: Boolean = false


    //Pruebas

    var bando: Bando = Bando.Blanco
    var bando2: Bando = Bando.Negro
    var orbe: Orbe = Orbe(bando, 32f, 32f, 50f, Direccion.IZQUIERDA, Posicion(200f, 200f))
    var orbe2: Orbe = Orbe(bando2, 32f, 32f, 10f, Direccion.DERECHA, Posicion(300f, 500f))
    var trampa: Trampa = Trampa(32f, 32f, Posicion(500f, 550f))
    var trampa2: Trampa = Trampa(32f, 32f, Posicion(800f, 550f))
    var suelo: Suelo? = null
    var muro: Muro? = null
    var aumentarVelocidad: AumentarVelocidad = AumentarVelocidad(32f, 32f, Posicion(300f, 600f))
    var puerta: Puerta = Puerta(60f, 60f, Posicion(700f, 310f))

    private fun prepareLevel() { // Aqui inicializaremos los objetos del juego

    }

    override fun run() {

        var fps: Long = 1
        //tiempo.start()
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

    private fun update(fps: Long) {  //Aqui actualizaremos el estado de cada objeto
        lobo.mover(fps)
        orbe.mover(fps)
        orbe2.mover(fps)
        if (orbe.es_visible) {
            orbe.detectarColision(lobo)

        }
        if (orbe2.es_visible) {
            orbe2.detectarColision(lobo)

        }

        comprobar_vulnerabilidad = trampa.detectarColision(lobo)
        trampa2.detectarColision(lobo)
        if (comprobar_vulnerabilidad == true) {
            lobo.vulnerable = false
            tirmpoVulnerable.start()
        }
        if (tirmpoVulnerable.finish == true) {
            lobo.vulnerable = true
            tirmpoVulnerable.finish = false
        }
        if (tiempo.finish == true) {
            segundos++
        }
        compobar_aumento = aumentarVelocidad.detectarColision(lobo)
        if (compobar_aumento == true) {
            tiempoVelocidad.start()
        }

        if (tiempoVelocidad.finish == true) {
            lobo.restarurarVelocidad()
            tirmpoVulnerable.finish = false
        }
        if (lobo.velocidad == 0f && tiempoVelocidad.finish == false) {
            lobo.velocidad = lobo.velocidadCambiada
        }
        puerta.detectarColision(lobo)
        if (lobo.endgame() == true) {
            pause()

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
            for (i in 0..30) {
                y = 0f
                for (j in 0..20) {
                    suelo = Suelo(32f, 32f, Posicion(x, y))
                    suelo!!.draw(canvas, bitmapSuelo)

                    y += 64f

                }
                x += 64f
            }
            i = 0
            j = 0
            x = 0f
            y = 0f
            for (i in 0..30) {
                muro = Muro(32f, 32f, Posicion(x, 20f))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                x += 64f

            }
            i = 0
            x = 0f
            for (i in 0..30) {
                muro = Muro(32f, 32f, Posicion(x, 1024f))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                x += 64f

            }
            i = 0
            x = 64f
            for (i in 0..23) {
                muro = Muro(32f, 32f, Posicion(x, 696f))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                x += 64f

            }
            i = 0
            x = 256f
            for (i in 0..21) {
                muro = Muro(32f, 32f, Posicion(x, 310f))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                x += 64
            }

            i = 0
            x = 0f
            for (i in 0..20) {
                muro = Muro(32f, 32f, Posicion(0f, y))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                y += 64f
            }
            y = 0f
            i = 0
            for (i in 0..20) {
                muro = Muro(32f, 32f, Posicion(1914f, y))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                y += 64f
            }

            y = 300f
            i = 0
            for (i in 0..6) {
                muro = Muro(32f, 32f, Posicion(1596f, y))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                y += 64f
            }
            y = 300f
            i = 0
            for (i in 0..6) {
                muro = Muro(32f, 32f, Posicion(1532f, y))
                muro!!.draw(canvas, bitmapMuro)
                muro!!.detectarColision(lobo)
                muro!!.detectarColision(orbe)
                muro!!.detectarColision(orbe2)
                y += 64f
            }

            //Now draw the player wolf
            trampa.draw(canvas, bitmapTrampa)
            trampa2.draw(canvas, bitmapTrampa)
            if (orbe.es_visible) {
                orbe.draw(canvas, bitmapOrbeBlanco)
            }
            if (orbe2.es_visible) {
                orbe2.draw(canvas, bitmapOrbeNegro)
            }
            if (aumentarVelocidad.visible == true) {
                aumentarVelocidad.draw(canvas, bitmapAumentoVel)
            }

            //Draw all the game objects here
            lobo.draw(canvas, bitmapOrbeRaro)
            puerta.draw(canvas, bitmapPuerta)
            canvas.drawBitmap(bitmapBordeSuperior, 0f, 0f, paint)
            canvas.drawBitmap(bitmapBorde, 0f, 1080f, paint)
            canvas.drawBitmap(bitmapPausa, 1860f, 0f, paint)

            canvas.drawText(segundos.toString(), 700f, 0f, paint)
            if (lobo.vida.numVide == 3) {
                vida.draw(canvas, 860f, 0f, context)
                vida.draw(canvas, 960f, 0f, context)
                vida.draw(canvas, 1060f, 0f, context)

            } else if (lobo.vida.numVide == 2) {
                vida.draw(canvas, 860f, 0f, context)
                vida.draw(canvas, 960f, 0f, context)

            } else if (lobo.vida.numVide == 1) {
                vida.draw(canvas, 860f, 0f, context)

            }


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

    fun onDestroy() {
        gameThread.destroy()
    }

    fun onStop() {
        pause()
        gameThread.stop()

    }

    fun obtenerBitmap(objeto: Objeto): Bitmap? {
        if (objeto is Lobo) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.orbe_raro)
        } else if (objeto is Orbe) {
            var orbe: Orbe = objeto as Orbe
            if (orbe.bando == Bando.Negro) {
                return BitmapFactory.decodeResource(context.resources, R.drawable.orbe_negro)
            } else {
                return BitmapFactory.decodeResource(context.resources, R.drawable.orbes)
            }
        } else if (objeto is Muro) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.muro)
        } else if (objeto is Suelo) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.suelo)
        } else if (objeto is Sumador) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.sumador)
        } else if (objeto is Multiplicador) {
            BitmapFactory.decodeResource(context.resources, R.drawable.multiplicador)
        } else if (objeto is Maquina) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.maquina)
        } else if (objeto is Invisibilidad) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.objeto_invisibilidad)
        } else if (objeto is AumentarVelocidad) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.objeto_velocidad)
        } else if (objeto is CambioBando) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.objeto_cambiobando)
        } else if (objeto is Puerta) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.puerta)
        } else if (objeto is Trampa) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.trampa)
        }
        return null
    }

}