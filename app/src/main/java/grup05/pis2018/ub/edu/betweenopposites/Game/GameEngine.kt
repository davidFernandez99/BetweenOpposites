package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.*
import android.view.SurfaceHolder
import grup05.pis2018.ub.edu.betweenopposites.Model.*
import grup05.pis2018.ub.edu.betweenopposites.R

class GameEngine (paint:Paint,contexto:Context,holder:SurfaceHolder) {


    val paint=paint
    val contexto=contexto
    val holder:SurfaceHolder=holder
    //Bitmap de los diferentes objetos que imprimiremos en el canvas
    var bitmapBorde: Bitmap ?= null
    var bitmapBordeSuperior: Bitmap ?= null
    var bitmapPausa: Bitmap ?= null
    var bitmapTeleporActivado: Bitmap ?= null
    var bitmapTeleporDesactivado: Bitmap ?=  null
    var bitmapBordeSimple: Bitmap ?= null
    var bitmapBordeDoble: Bitmap ?= null
    //Bitmaps para pausa y fin del juego
    var bitmappausa:Bitmap?=null
    var bitmapResume:Bitmap?=null
    var bitmapHome:Bitmap?=null
    var bitmapfinjuego:Bitmap?=null
    var bitmapRestart:Bitmap?=null
    var bitmapFallarOpcionMaquina:Bitmap?=null
    //Instanciamos un objeto vida para poder considerar cuantas vidas dibujar en el canvas
    var vida: Vida ?= null
    //Cogemos la instancia del único Lobo
    var lobo: Lobo ?= null
    var facade:Facade?=null

    //Pruebas
    var canvas: Canvas ?= null
    var tiempoFinal:Long=0
    var tiempoIncialVelocidad:Long=0
    var tiempoInicialInvisibilidad:Long=0


     fun update(fps: Long) {  //Aqui actualizaremos el estado de cada objeto
        if(DisplayThread.activar_efecto==true){

            lobo!!.objetoActivable!!.activarEfecto(lobo!!)
            lobo!!.objetoActivable=null
            DisplayThread.activar_efecto=false
            if(lobo!!.objetoActivable is AumentarVelocidad){
                DisplayThread.tiempoVelocidad.start()
            }
            if(lobo!!.objetoActivable is Invisibilidad){

                DisplayThread.tiempoInvisibilidad.start()

            }

        }

         //if (tirmpoVulnerable!!.finish == true) {
         //lobo!!.vulnerable = true
         //tirmpoVulnerable!!.finish = false

         if(DisplayThread.tiempoVel==true){
             lobo!!.restarurarVelocidad()
             DisplayThread.tiempoVel=false


         }

         if(DisplayThread.tiempoInvisible==true){
             if(System.currentTimeMillis() - tiempoInicialInvisibilidad >= DisplayThread.MAX_TIEMPO_INVISIBLE) {
                 lobo!!.es_visible = true
                 DisplayThread.tiempoInvisible = false
             }
         }


         facade!!.update(fps)

    }

     fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
           canvas = holder.lockCanvas()

            // Draw the background color


            facade!!.draw(canvas!!,contexto)
            if(Facade.dando_opciones==true){
                dibujarOpcionesMaquina()
            }
            dibujarBordes()
            if(DisplayThread.paused==true && DisplayThread.mostrar_Pause==true){
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
        paint.textSize= 30f
        lobo=Lobo.instance
        facade= Facade.instance
        facade!!.iniciarPartida(contexto)
        vida=Vida()
        canvas=Canvas()
        bitmapBorde= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde)
        bitmapBordeSuperior= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde_superior)
        bitmapPausa= BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_pausa)
        GameEngine.bitmapOrbeNegro= BitmapFactory.decodeResource(contexto.resources, R.drawable.orbe_negro)
        GameEngine.bitmapOrbeBlanco= BitmapFactory.decodeResource(contexto.resources, R.drawable.orbes)
        GameEngine.bitmapTrampa= BitmapFactory.decodeResource(contexto.resources, R.drawable.trampa)
        GameEngine.bitmapMuro= BitmapFactory.decodeResource(contexto.resources, R.drawable.muro)
        GameEngine.bitmapSuelo= BitmapFactory.decodeResource(contexto.resources, R.drawable.suelo)
        bitmapTeleporActivado= BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_tp_activado)
        bitmapTeleporDesactivado=  BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_tp_desactivado)
        GameEngine.bitmapInv= BitmapFactory.decodeResource(contexto.resources, R.drawable.objeto_invisibilidad)
        GameEngine.bitmapCambio= BitmapFactory.decodeResource(contexto.resources, R.drawable.objeto_cambiobando)
        GameEngine.bitmapAumentoVel= BitmapFactory.decodeResource(contexto.resources, R.drawable.objeto_velocidad)
        GameEngine.bitmapSumador= BitmapFactory.decodeResource(contexto.resources, R.drawable.sumador)
        GameEngine.bitmapMultiplicador= BitmapFactory.decodeResource(contexto.resources, R.drawable.multiplicador)
        bitmapBordeSimple= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde_singular)
        bitmapBordeDoble= BitmapFactory.decodeResource(contexto.resources, R.drawable.borde_doble)
        GameEngine.bitmapPuerta= BitmapFactory.decodeResource(contexto.resources, R.drawable.puerta)
        GameEngine.bitmapOrbeRaro= BitmapFactory.decodeResource(contexto.resources, R.drawable.orbe_raro)
        GameEngine.bitmapMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.maquina)
        GameEngine.bitmapOpcionesMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.opciones_maquina)
        bitmapFallarOpcionMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.fallar_opcion_maquina)
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
        tiempoFinal=System.currentTimeMillis()
        canvas!!.drawBitmap(bitmapfinjuego,700f,300f, paint)
        canvas!!.drawBitmap(bitmapHome,770f,650f, paint)
        canvas!!.drawBitmap(bitmapRestart,1100f,650f, paint)
        canvas!!.drawText( lobo!!.puntuacion.toString(), 1020f,495f, paint)
        canvas!!.drawText( DisplayThread.segundos.toString(), 930f,560f, paint)
    }

    fun dibujarBordes(){
        canvas!!.drawBitmap(bitmapBordeSuperior, 0f, 0f, paint)
        canvas!!.drawBitmap(bitmapBorde, 0f, 1020f, paint)
        canvas!!.drawBitmap(bitmapBordeSimple, 64f , 1020f ,paint)
        canvas!!.drawText("X", 1820f, 1060f,paint)
        if(Facade.signo==1){
            canvas!!.drawText(" + ",980f, 1060f , paint)
            canvas!!.drawText(Facade.ultimaPuntuacion.toString(),1030f, 1060f , paint)
        }
        if(Facade.signo==2){
            canvas!!.drawText(" - ",980f, 1060f , paint)
            canvas!!.drawText(Facade.ultimaPuntuacion.toString(),1030f, 1060f , paint)
        }
        canvas!!.drawText(lobo!!.multiplicador.toString(), 1856f, 1060f,paint)
        canvas!!.drawBitmap(bitmapPausa, 1860f, 0f, paint)
        if(lobo!!.objetoActivable!=null){
            if(lobo!!.objetoActivable is Invisibilidad){
                canvas!!.drawBitmap(bitmapInv!!,65f,1025f,paint)
            }
            else if(lobo!!.objetoActivable is AumentarVelocidad){
                canvas!!.drawBitmap(bitmapAumentoVel!!,65f,1025f,paint)
            }
            else{
                canvas!!.drawBitmap(bitmapCambio!!,65f,1025f,paint)
            }
        }
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
        var nivel:Int=Facade.nivel+1
        canvas!!.drawText("NIVEL :  ",350f, 30f,paint)
        canvas!!.drawText(nivel.toString(),500f, 30f,paint)
        canvas!!.drawText("  -  ",550f, 30f,paint)
        canvas!!.drawText(Facade.mapa.toString(),600f, 30f,paint)


        if(lobo!!.bando== Bando.Negro){
            canvas!!.drawText("BANDO :  OSCURIDAD " ,1300f, 30f,paint)
        }
        if(lobo!!.bando== Bando.Blanco){
            canvas!!.drawText("BANDO :  LUZ " ,1300f, 30f,paint)
        }
        if(lobo!!.bando== Bando.Neutro){
            canvas!!.drawText("BANDO :  NEUTRO " ,1300f, 30f,paint)
        }

    }
    fun dibujarOpcionesMaquina(){
        canvas!!.drawBitmap(bitmapOpcionesMaquina!!,700f,300f,paint)
        canvas!!.drawText(opciones!!.get(0).toString(),792f,640f,paint)
        canvas!!.drawText(opciones!!.get(1).toString(),988f,640f,paint)
        canvas!!.drawText(opciones!!.get(2).toString(),1184f,640f,paint)
    }
    companion object{
        var bitmapOrbeNegro: Bitmap ?= null
        var bitmapOrbeBlanco: Bitmap ?= null
        var bitmapTrampa: Bitmap ?= null
        var bitmapMuro: Bitmap ?= null
        var bitmapSuelo: Bitmap ?= null
        var bitmapInv: Bitmap ?= null
        var bitmapCambio: Bitmap ?= null
        var bitmapAumentoVel: Bitmap ?= null
        var bitmapSumador: Bitmap ?= null
        var bitmapMultiplicador: Bitmap ?= null
        var bitmapPuerta: Bitmap ?= null
        var bitmapOrbeRaro: Bitmap ?= null
        var bitmapMaquina:Bitmap?=null
        var bitmapOpcionesMaquina:Bitmap?=null
        var opciones:ArrayList<Int> ?= null
    }
}