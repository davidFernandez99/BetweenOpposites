package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.*
import android.view.SurfaceHolder
import grup05.pis2018.ub.edu.betweenopposites.Model.*
import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.UnJugador

class GameEngine (paint:Paint,contexto:Context,holder:SurfaceHolder) {

    val MAX_TIEMPO_VELOCIDAD: Long = 5000
    val MAX_TIEMPO_INVISIBLE: Long = 5000
    val MAX_TIEMPO_VULNERABLE: Long = 1000
    var conv: Long = 1000

    var segundos: Long = 0
    val paint=paint
    val contexto=contexto
    val holder:SurfaceHolder=holder
    //Bitmap de los diferentes objetos que imprimiremos en el canvas
    var bitmapBorde: Bitmap ?= null
    var bitmapBordeSuperior: Bitmap ?= null
    var bitmapPausa: Bitmap ?= null
    var bitmapOrbeNegro: Bitmap ?= null
    var bitmapOrbeBlanco: Bitmap ?= null
    var bitmapTrampa: Bitmap ?= null
    var bitmapMuro: Bitmap ?= null
    var bitmapSuelo: Bitmap ?= null
    var bitmapTeleporActivado: Bitmap ?= null
    var bitmapTeleporDesactivado: Bitmap ?=  null
    var bitmapInv: Bitmap ?= null
    var bitmapCambio: Bitmap ?= null
    var bitmapAumentoVel: Bitmap ?= null
    var bitmapSumador: Bitmap ?= null
    var bitmapMultiplicador: Bitmap ?= null
    var bitmapBordeSimple: Bitmap ?= null
    var bitmapBordeDoble: Bitmap ?= null
    var bitmapPuerta: Bitmap ?= null
    var bitmapOrbeRaro: Bitmap ?= null

    //Bitmaps para pausa y fin del juego
    var bitmappausa:Bitmap?=null
    var bitmapResume:Bitmap?=null
    var bitmapHome:Bitmap?=null
    var bitmapfinjuego:Bitmap?=null
    var bitmapRestart:Bitmap?=null
    //Instanciamos un objeto vida para poder considerar cuantas vidas dibujar en el canvas
    var vida: Vida ?= null
    //Cogemos la instancia del único Lobo
    var lobo: Lobo ?= null
    var facade:Facade?=null
    var comprobar_vulnerabilidad: Boolean = false
    var compobar_aumento: Boolean = false
    //Pruebas

    var bando: Actor.Bando ?= null
    var bando2: Actor.Bando ?= null
    var orbe: Orbe ?= null
    var orbe2: Orbe ?= null
    var trampa: Trampa ?= null
    var trampa2: Trampa ?= null
    var suelo: Suelo? = null
    var muro: Muro? = null
    var aumentarVelocidad: AumentarVelocidad ?= null
    var puerta: Puerta ?= null
    var canvas: Canvas ?= null


     fun update(fps: Long) {  //Aqui actualizaremos el estado de cada objeto
        lobo!!.mover(fps)
        orbe!!.mover(fps)
        orbe2!!.mover(fps)
        if (orbe!!.es_visible) {
            orbe!!.detectarColision(lobo!!)

        }
        if (orbe2!!.es_visible) {
            orbe2!!.detectarColision(lobo!!)

        }

        comprobar_vulnerabilidad = trampa!!.detectarColision(lobo!!)
        trampa2!!.detectarColision(lobo!!)
        if (comprobar_vulnerabilidad == true) {
            //lobo!!.vulnerable = false
            //tirmpoVulnerable!!.start()
        }
        //if (tirmpoVulnerable!!.finish == true) {
            //lobo!!.vulnerable = true
            //tirmpoVulnerable!!.finish = false
        //}
        //if (tiempo!!.finish == true) {
          //  segundos++
        //}
        compobar_aumento = aumentarVelocidad!!.detectarColision(lobo!!)
        if (compobar_aumento == true) {
        //    tiempoVelocidad!!.start()
        }

        //if (tiempoVelocidad!!.finish == true) {
          //  lobo!!.restarurarVelocidad()
            //tirmpoVulnerable!!.finish = false
        //}
        //if (lobo!!.velocidad == 0f && tiempoVelocidad!!.finish == false) {
          //  lobo!!.velocidad = lobo!!.velocidadCambiada
        //}
        puerta!!.detectarColision(lobo!!)
        if (lobo!!.endgame() == true) {
            DisplayThread.paused=true

        }

    }

    fun drew(){

    }
    fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
           canvas = holder.lockCanvas()

            // Draw the background color


            //Dibujamos los suelos de la sala
            var i: Int = 0
            var j: Int = 0
            var x: Float = 0f
            var y: Float = 0f
            for (i in 0..30) {
                y = 0f
                for (j in 0..20) {
                    suelo = Suelo(32f, 32f, Posicion(x, y))
                    suelo!!.draw(canvas!!, bitmapSuelo!!)

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
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                x += 64f

            }
            i = 0
            x = 0f
            for (i in 0..30) {
                muro = Muro(32f, 32f, Posicion(x, 1024f))
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                x += 64f

            }
            i = 0
            x = 64f
            for (i in 0..23) {
                muro = Muro(32f, 32f, Posicion(x, 696f))
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                x += 64f

            }
            i = 0
            x = 256f
            for (i in 0..21) {
                muro = Muro(32f, 32f, Posicion(x, 310f))
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                x += 64
            }

            i = 0
            x = 0f
            for (i in 0..20) {
                muro = Muro(32f, 32f, Posicion(0f, y))
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                y += 64f
            }
            y = 0f
            i = 0
            for (i in 0..20) {
                muro = Muro(32f, 32f, Posicion(1914f, y))
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                y += 64f
            }

            y = 300f
            i = 0
            for (i in 0..6) {
                muro = Muro(32f, 32f, Posicion(1596f, y))
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                y += 64f
            }
            y = 300f
            i = 0
            for (i in 0..6) {
                muro = Muro(32f, 32f, Posicion(1532f, y))
                muro!!.draw(canvas!!, bitmapMuro!!)
                muro!!.detectarColision(lobo!!)
                muro!!.detectarColision(orbe!!)
                muro!!.detectarColision(orbe2!!)
                y += 64f
            }

            //Now draw the player wolf
            trampa!!.draw(canvas!!, bitmapTrampa!!)
            trampa2!!.draw(canvas!!, bitmapTrampa!!)
            if (orbe!!.es_visible) {
                orbe!!.draw(canvas!!, bitmapOrbeBlanco!!)
            }
            if (orbe2!!.es_visible) {
                orbe2!!.draw(canvas!!, bitmapOrbeNegro!!)
            }
            if (aumentarVelocidad!!.visible == true) {
                aumentarVelocidad!!.draw(canvas!!, bitmapAumentoVel!!)
            }

            //Draw all the game objects here
            lobo!!.draw(canvas!!, bitmapOrbeRaro!!)
            puerta!!.draw(canvas!!, bitmapPuerta!!)
            canvas!!.drawBitmap(bitmapBordeSuperior, 0f, 0f, paint)
            canvas!!.drawBitmap(bitmapBorde, 0f, 1080f, paint)
            canvas!!.drawBitmap(bitmapPausa, 1860f, 0f, paint)

            canvas!!.drawText(segundos.toString(), 700f, 0f, paint)
            if (lobo!!.vida.numVide == 3) {
                vida!!.draw(canvas!!, 860f, 0f, contexto)
                vida!!.draw(canvas!!, 960f, 0f, contexto)
                vida!!.draw(canvas!!, 1060f, 0f, contexto)

            } else if (lobo!!.vida.numVide == 2) {
                vida!!.draw(canvas!!, 860f, 0f, contexto)
                vida!!.draw(canvas!!, 960f, 0f, contexto)

            } else if (lobo!!.vida.numVide == 1) {
                vida!!.draw(canvas!!, 860f, 0f, contexto)

            }
            else{
                DisplayThread.paused=true
                DisplayThread.fin_juego=true
            }
            if(DisplayThread.paused==true &&DisplayThread.fin_juego==false){
                dibujarPausa()
            }
            if(DisplayThread.paused==true && DisplayThread.fin_juego==true){
                dibujarFinJuego()
            }

            // Draw everything to the screen
            holder.unlockCanvasAndPost(canvas)
        }

    }

    fun inicializarVariable(){

        lobo=Lobo.instance
        facade= Facade.uniqueFacade
        vida=Vida()
        bando=Actor.Bando.Blanco
        bando2=Actor.Bando.Negro
        orbe= Orbe(bando!!, 32f, 32f, 50f, Actor.Direccion.IZQUIERDA, Posicion(200f, 200f))
        orbe2 = Orbe(bando2!!, 32f, 32f, 10f, Actor.Direccion.DERECHA, Posicion(300f, 500f))
        trampa = Trampa(32f, 32f, Posicion(500f, 550f))
        trampa2 = Trampa(32f, 32f, Posicion(800f, 550f))
        aumentarVelocidad= AumentarVelocidad(32f, 32f, Posicion(300f, 600f))
        puerta= Puerta(60f, 60f, Posicion(700f, 310f))
        canvas=Canvas()
        bitmapBorde= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde)
        bitmapBordeSuperior= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde_superior)
        bitmapPausa= BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_pausa)
        bitmapOrbeNegro= BitmapFactory.decodeResource(contexto.resources, R.drawable.orbe_negro)
        bitmapOrbeBlanco= BitmapFactory.decodeResource(contexto.resources, R.drawable.orbes)
        bitmapTrampa= BitmapFactory.decodeResource(contexto.resources, R.drawable.trampa)
        bitmapMuro= BitmapFactory.decodeResource(contexto.resources, R.drawable.muro)
        bitmapSuelo= BitmapFactory.decodeResource(contexto.resources, R.drawable.suelo)
        bitmapTeleporActivado= BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_tp_activado)
        bitmapTeleporDesactivado=  BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_tp_desactivado)
        bitmapInv= BitmapFactory.decodeResource(contexto.resources, R.drawable.objeto_invisibilidad)
        bitmapCambio= BitmapFactory.decodeResource(contexto.resources, R.drawable.objeto_cambiobando)
        bitmapAumentoVel= BitmapFactory.decodeResource(contexto.resources, R.drawable.objeto_velocidad)
        bitmapSumador= BitmapFactory.decodeResource(contexto.resources, R.drawable.sumador)
        bitmapMultiplicador= BitmapFactory.decodeResource(contexto.resources, R.drawable.multiplicador)
        bitmapBordeSimple= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde_singular)
        bitmapBordeDoble= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde_doble)
        bitmapPuerta= BitmapFactory.decodeResource(contexto.resources, R.drawable.puerta)
        bitmapOrbeRaro= BitmapFactory.decodeResource(contexto.resources, R.drawable.orbe_raro)

    }

    fun dibujarPausa(){
        bitmappausa= BitmapFactory.decodeResource(contexto.resources, R.drawable.fondo_pausa)
        bitmapResume= BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_resume)
        bitmapHome= BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_home)
        canvas!!.drawBitmap(bitmappausa,700f,300f, paint)
        canvas!!.drawBitmap(bitmapHome,770f,550f, paint)
        canvas!!.drawBitmap(bitmapResume,1100f,550f, paint)
    }

    fun dibujarFinJuego(){
        bitmapHome=BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_home)
        bitmapfinjuego=BitmapFactory.decodeResource(contexto.resources, R.drawable.game_over)
        bitmapRestart=BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_retry)
        canvas!!.drawBitmap(bitmapfinjuego,700f,300f, paint)
        canvas!!.drawBitmap(bitmapHome,770f,650f, paint)
        canvas!!.drawBitmap(bitmapRestart,1100f,650f, paint)
    }
}