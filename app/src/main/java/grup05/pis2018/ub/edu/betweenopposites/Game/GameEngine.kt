package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.*
import android.view.SurfaceHolder
import grup05.pis2018.ub.edu.betweenopposites.Model.*
import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.UnJugador

class GameEngine (paint:Paint,contexto:Context,holder:SurfaceHolder) {


    var mapa:Int=1
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
    var bitmapMaquina:Bitmap?=null
    var bitmapOpcionesMaquina:Bitmap?=null
    var bitmapFallarOpcionMaquina:Bitmap?=null
    //Instanciamos un objeto vida para poder considerar cuantas vidas dibujar en el canvas
    var vida: Vida ?= null
    //Cogemos la instancia del único Lobo
    var lobo: Lobo ?= null
    var facade:Facade?=null
    var comprobar_vulnerabilidad: Boolean = false
    var comprobar_vulnerabilidad2: Boolean = false
    var compobar_aumento: Boolean = false
    var comprobar_colision_maquina:Boolean=false
    var comprobar_colision_puerta:Boolean=false
    var comprobar_opcion_corecta:Boolean=false
    //Pruebas

    var bando: Actor.Bando ?= null
    var bando2: Actor.Bando ?= null
    var orbe: Orbe ?= null
    var orbe2: Orbe ?= null
    var trampa: Trampa ?= null
    var trampa2: Trampa ?= null
    var ultimaTrampaColisionada:Trampa ?= null
    var suelo: Suelo? = null
    var muro: Muro? = null
    var puerta: Puerta ?= null
    var puerta2:Puerta?=null
    var canvas: Canvas ?= null
    var maquina:Maquina?=null
    var objetoMaquina:ObjetoActivable?=null
    var opciones:ArrayList<Int> ?= null
    var multiplicador:Multiplicador?=null
    var tiempoFinal:Long=0
    var tiempoIncialVelocidad:Long=0
    var tiempoInicialInvisibilidad:Long=0

     fun update(fps: Long) {  //Aqui actualizaremos el estado de cada objeto
        if(DisplayThread.activar_efecto==true){

            lobo!!.objetoActivable!!.activarEfecto(lobo!!)
            lobo!!.objetoActivable=null
            DisplayThread.activar_efecto=false
            if(lobo!!.objetoActivable is AumentarVelocidad){
                DisplayThread.tiempoVelocidad=true
                tiempoIncialVelocidad=System.currentTimeMillis()
            }
            if(lobo!!.objetoActivable is Invisibilidad){
                DisplayThread.tiempoInvisibilidad=true
                lobo!!.es_visible=false
                tiempoInicialInvisibilidad=System.currentTimeMillis()
            }

        }

         //if (tirmpoVulnerable!!.finish == true) {
         //lobo!!.vulnerable = true
         //tirmpoVulnerable!!.finish = false

         if(DisplayThread.tiempoVelocidad==true){
             if (System.currentTimeMillis() - tiempoIncialVelocidad >= DisplayThread.MAX_TIEMPO_VELOCIDAD) {
                 lobo!!.restarurarVelocidad()
                 DisplayThread.tiempoVelocidad = false
             }
             if (lobo!!.velocidad == 0f ) {
                 lobo!!.velocidad = lobo!!.velocidadCambiada
             }
         }

         if(DisplayThread.tiempoInvisibilidad==true){
             if(System.currentTimeMillis() - tiempoInicialInvisibilidad >= DisplayThread.MAX_TIEMPO_INVISIBLE) {
                 lobo!!.es_visible = true
                 DisplayThread.tiempoInvisibilidad = false
             }
         }
         if(mapa==1){
            updateMapa1(fps)
        }
         if(mapa==2){
            updateMapa2(fps)
        }

    }

     fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
           canvas = holder.lockCanvas()

            // Draw the background color

            if(mapa==1){
                drawMapa1()
            }
            if(mapa==2){
                drawMapa2()
            }

            canvas!!.drawBitmap(bitmapBordeSuperior, 0f, 0f, paint)
            canvas!!.drawBitmap(bitmapBorde, 0f, 1020f, paint)
            canvas!!.drawBitmap(bitmapBordeSimple, 64f , 1020f ,paint)
            canvas!!.drawText("X", 1820f, 1060f,paint)
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
            if(mapa==1){
                canvas!!.drawText("1  -  1" ,350f, 30f,paint)
            }
            if(mapa==2){
                canvas!!.drawText("1  -  2" ,350f, 30f,paint)
            }
            if(lobo!!.bando== Actor.Bando.Negro){
                canvas!!.drawText("BANDO :  OSCURIDAD " ,1300f, 30f,paint)
            }
            if(lobo!!.bando== Actor.Bando.Blanco){
                canvas!!.drawText("BANDO :  LUZ " ,1300f, 30f,paint)
            }
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
        facade= Facade.uniqueFacade
        vida=Vida()
        bando=Actor.Bando.Blanco
        bando2=Actor.Bando.Negro
        orbe= Orbe(bando!!, 32f, 32f, 50f, Actor.Direccion.IZQUIERDA, Posicion(200f, 200f))
        orbe2 = Orbe(bando2!!, 32f, 32f, 10f, Actor.Direccion.DERECHA, Posicion(300f, 500f))
        trampa = Trampa(32f, 32f, Posicion(500f, 550f))
        trampa2 = Trampa(32f, 32f, Posicion(800f, 550f))
        puerta= Puerta(32f, 32f, Posicion(1404f, 500f))
        puerta2= Puerta(32f,32f, Posicion(1700f,860f))
        canvas=Canvas()
        multiplicador= Multiplicador(1, 16f,16f, Posicion(200f,200f))
        maquina= Maquina(64f,40f,Posicion(1500f,412f))
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
        bitmapMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.maquina)
        bitmapOpcionesMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.opciones_maquina)
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
        segundos=(tiempoFinal-DisplayThread.tiempoInicial)
        canvas!!.drawBitmap(bitmapfinjuego,700f,300f, paint)
        canvas!!.drawBitmap(bitmapHome,770f,650f, paint)
        canvas!!.drawBitmap(bitmapRestart,1100f,650f, paint)
        canvas!!.drawText( lobo!!.puntuacion.puntuacion.toString(), 1020f,495f, paint)
        canvas!!.drawText( segundos.toString(), 930f,560f, paint)
    }

    fun drawMapa1(){
        //Dibujamos los suelos de la sala
        var i: Int = 0
        var j: Int = 0
        var x: Float = 0f
        var y: Float = 0f
        for (i in 0..30) {
            y = 0f
            for (j in 0..17) {
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
            muro = Muro(32f, 32f, Posicion(x, 40f))
            muro!!.draw(canvas!!, bitmapMuro!!)
            muro!!.detectarColision(lobo!!)
            muro!!.detectarColision(orbe!!)
            muro!!.detectarColision(orbe2!!)
            x += 64f

        }
        i = 0
        x = 0f
        for (i in 0..30) {
            muro = Muro(32f, 32f, Posicion(x, 976f))
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

        //Draw all the game objects here
        lobo!!.draw(canvas!!, bitmapOrbeRaro!!)
        puerta!!.draw(canvas!!, bitmapPuerta!!)
    }

    fun drawMapa2(){
        //Dibujamos los suelos de la sala
        var i: Int = 0
        var j: Int = 0
        var x: Float = 0f
        var y: Float = 0f
        for (i in 0..30) {
            y = 0f
            for (j in 0..17) {
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
            muro = Muro(32f, 32f, Posicion(x, 40f))
            muro!!.draw(canvas!!, bitmapMuro!!)
            muro!!.detectarColision(lobo!!)
            muro!!.detectarColision(orbe!!)
            muro!!.detectarColision(orbe2!!)
            x += 64f

        }
        i = 0
        x = 0f
        for (i in 0..30) {
            muro = Muro(32f, 32f, Posicion(x, 976f))
            muro!!.draw(canvas!!, bitmapMuro!!)
            muro!!.detectarColision(lobo!!)
            muro!!.detectarColision(orbe!!)
            muro!!.detectarColision(orbe2!!)
            x += 64f

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
        maquina!!.draw(canvas!!,bitmapMaquina!!)
        if(DisplayThread.dando_opciones==true){
            dibujarOpcionesMaquina()
        }
        if(objetoMaquina!=null){
            if(objetoMaquina is AumentarVelocidad){
                if(objetoMaquina!!.es_visible==true){
                    objetoMaquina!!.draw(canvas!!,bitmapAumentoVel!!)
                }

            }
            else if (objetoMaquina is Invisibilidad){
                if(objetoMaquina!!.es_visible==true){
                    objetoMaquina!!.draw(canvas!!,bitmapInv!!)
                }
            }
            else{
                if(objetoMaquina!!.es_visible==true){
                    objetoMaquina!!.draw(canvas!!,bitmapCambio!!)
                }

            }

        }
        if(multiplicador!!.es_visible==true){
            multiplicador!!.draw(canvas!!,bitmapMultiplicador!!)
        }
        //if(DisplayThread.fallar==true){
          //  canvas!!.drawBitmap(bitmapFallarOpcionMaquina!!,700f,300f,paint)

        //}
        puerta2!!.draw(canvas!!,bitmapPuerta!!)
        lobo!!.draw(canvas!!, bitmapOrbeRaro!!)



    }

    fun updateMapa1(fps:Long){
        lobo!!.mover(fps)
        orbe!!.mover(fps)
        orbe2!!.mover(fps)
        if (orbe!!.es_visible) {
            orbe!!.detectarColision(lobo!!)

        }
        if (orbe2!!.es_visible) {
            orbe2!!.detectarColision(lobo!!)

        }

        if(lobo!!.vulnerable==true){
            comprobar_vulnerabilidad = trampa!!.detectarColision(lobo!!)
            comprobar_vulnerabilidad2 = trampa2!!.detectarColision(lobo!!)
        }

        if(comprobar_vulnerabilidad==true ){
            ultimaTrampaColisionada=trampa
            lobo!!.vulnerable=false
        }
        if(comprobar_vulnerabilidad2==true ){
            ultimaTrampaColisionada=trampa2
            lobo!!.vulnerable=false
        }
        if(ultimaTrampaColisionada!= null){
            lobo!!.setVulnerabilidad(ultimaTrampaColisionada!!)
        }
        comprobar_colision_puerta=puerta!!.comprobarColision(lobo!!)
        if(comprobar_colision_puerta==true){
            mapa=2
            DisplayThread.playing=true
            DisplayThread.paused=false
            lobo!!.posicion=Posicion(200f, 900f)
            lobo!!.velocidad=0f
            lobo!!.direccion=Actor.Direccion.PARADO
        }

    }

    fun updateMapa2(fps:Long){
        if(DisplayThread.dando_opciones!=true){
            lobo!!.mover(fps)
        }
        comprobar_colision_maquina=maquina!!.detectarColision(lobo!!)
        if(comprobar_colision_maquina==true){
            //Abrir opciones si no se ha hecho antes
            if(maquina!!.dar_opciones==true){
                DisplayThread.dando_opciones=true
                opciones=maquina!!.darOpciones(lobo!!)

            }

        }
        if(DisplayThread.comprobar_opcion==true){

            comprobar_opcion_corecta=Maquina.comprobarRespuestaMaquina(opciones!!.get(DisplayThread.opcion))
            if(comprobar_opcion_corecta==true){
                objetoMaquina=maquina!!.darRecompensa()
                DisplayThread.comprobar_opcion=false

            }
            else if(comprobar_opcion_corecta==false){
                DisplayThread.fallar=true
                DisplayThread.comprobar_opcion=false
            }
        }
        if(multiplicador!!.es_visible==true){
            multiplicador!!.detectarColision(lobo!!)
        }

        if(objetoMaquina!=null){
            if(objetoMaquina!!.es_visible==true){
                objetoMaquina!!.detectarColision(lobo!!)
            }

        }
        comprobar_colision_puerta=puerta2!!.comprobarColision(lobo!!)
        if(comprobar_colision_puerta==true){
            lobo!!.velocidad=0f
            lobo!!.direccion=Actor.Direccion.PARADO
            DisplayThread.fin_juego=true
            DisplayThread.paused=true

        }

    }

    fun dibujarOpcionesMaquina(){
            canvas!!.drawBitmap(bitmapOpcionesMaquina!!,700f,300f,paint)
            canvas!!.drawText(opciones!!.get(0).toString(),792f,640f,paint)
            canvas!!.drawText(opciones!!.get(1).toString(),988f,640f,paint)
            canvas!!.drawText(opciones!!.get(2).toString(),1184f,640f,paint)
    }
}