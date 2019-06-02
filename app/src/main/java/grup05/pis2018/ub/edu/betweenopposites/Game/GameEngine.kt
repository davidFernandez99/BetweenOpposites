package grup05.pis2018.ub.edu.betweenopposites.Game

import android.content.Context
import android.graphics.*
import android.view.SurfaceHolder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import grup05.pis2018.ub.edu.betweenopposites.Model.*
import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.Opcions
import grup05.pis2018.ub.edu.betweenopposites.View.Ranking

class GameEngine (paint:Paint,contexto:Context,holder:SurfaceHolder) {

    var resetear:Boolean=false
    val paint=paint
    val contexto=contexto
    val holder:SurfaceHolder=holder
    //Bitmap de los diferentes objetos que imprimiremos en el canvas
    var bitmapBorde: Bitmap ?= null
    var bitmapBordeSuperior: Bitmap ?= null
    var bitmapPausa: Bitmap ?= null
    var bitmapTeleporActivado: Bitmap ?= null
    var bitmapTeleporDesactivado: Bitmap ?=  null
    var bitmapVelBorde: Bitmap ?= null
    var bitmapCambioBorde: Bitmap ?= null
    var bitmapInvBorde: Bitmap ?=  null
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
    //Timers
    lateinit var database : DatabaseReference
    var userData: UserData?=null
    var escrito:Boolean=false


     fun update(fps: Long) {  //Aqui actualizaremos el estado de cada objeto
        if(DisplayThread.activar_efecto==true){

            lobo!!.objetoActivable!!.activarEfecto(lobo!!)

            DisplayThread.activar_efecto=false
            if(lobo!!.objetoActivable is AumentarVelocidad){
                DisplayThread.tiempoVelocidad.start()
            }
            if(lobo!!.objetoActivable is Invisibilidad){
                DisplayThread.tiempoInvisibilidad.start()

            }
            lobo!!.objetoActivable=null

        }



         facade!!.update(fps)

    }

     fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
           canvas = holder.lockCanvas()
            facade!!.draw(canvas!!,contexto)
            if(Facade.dando_opciones==true){
                dibujarOpcionesMaquina()
            }
            dibujarBordes()

            if(Facade.acabar_juego==true){
                DisplayThread.paused=true
                dibujarAcabarJuego()
            }
            else if(DisplayThread.paused==true && DisplayThread.mostrar_Pause==true){
                dibujarPausa()
            }
            else if(DisplayThread.paused==true && DisplayThread.fin_juego==true){
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
        GameEngine.bitmapLoboOscuro= BitmapFactory.decodeResource(contexto.resources, R.drawable.lobo_oscuro)
        GameEngine.bitmapLoboNeutro= BitmapFactory.decodeResource(contexto.resources, R.drawable.lobo_neutro)
        GameEngine.bitmapLoboLuz= BitmapFactory.decodeResource(contexto.resources, R.drawable.lobo_blanco)
        GameEngine.bitmapLoboOscuroInv= BitmapFactory.decodeResource(contexto.resources, R.drawable.lobo_oscuro_invisibilidad)
        GameEngine.bitmapLoboNeutroInv= BitmapFactory.decodeResource(contexto.resources, R.drawable.lobo_neutro_invisible)
        GameEngine.bitmapLoboLuzInv= BitmapFactory.decodeResource(contexto.resources, R.drawable.lobo_blanco_invisibilidad)
        GameEngine.bitmapMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.maquina)
        GameEngine.bitmapOpcionesMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.opciones_maquina)
        bitmapFallarOpcionMaquina=BitmapFactory.decodeResource(contexto.resources,R.drawable.fallar_opcion_maquina)
        bitmapVelBorde= BitmapFactory.decodeResource(contexto.resources,R.drawable.obj_velocidad_borde)
        bitmapCambioBorde= BitmapFactory.decodeResource(contexto.resources,R.drawable.obj_cambio_bando_borde)
        bitmapInvBorde=  BitmapFactory.decodeResource(contexto.resources,R.drawable.obj_invisibilidad_borde)


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
        var segundos:Long= DisplayThread.segundos
        var segundosFinal: String = segundos.toString().plus(" ").plus("segundos")
        var puntuacion:Int=lobo!!.puntuacion.puntuacion
        var puntuacionFinal:String=puntuacion.toString().plus(" ").plus("puntos")
        canvas!!.drawBitmap(bitmapfinjuego,700f,300f, paint)
        canvas!!.drawBitmap(bitmapHome,770f,650f, paint)
        canvas!!.drawBitmap(bitmapRestart,1100f,650f, paint)
        canvas!!.drawText( puntuacionFinal, 1020f,495f, paint)
        canvas!!.drawText( segundosFinal, 930f,560f, paint)
        var puntos:Int = lobo!!.puntuacion.puntuacion
        //Escribir en firebase
        if(escrito==false){
            if(Opcions.loguejat==true){
                UserData.instance.puntuacion=puntos
                if(userNotInList(UserData.instance)==true){

                    database = FirebaseDatabase.getInstance().getReference()
                    database.child((Opcions.userId)).removeValue()
                    database.child(Opcions.userId).setValue(UserData.instance)
                }

            }
            escrito=true
        }


    }

    fun dibujarAcabarJuego(){
        //Escribir puntuación en firebase
        bitmapHome=BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_home)
        bitmapfinjuego=BitmapFactory.decodeResource(contexto.resources, R.drawable.game_over)
        bitmapRestart=BitmapFactory.decodeResource(contexto.resources, R.drawable.boton_retry)
        var segundos:Long= DisplayThread.segundos
        var segundosFinal: String = segundos.toString().plus(" ").plus("segundos")
        var puntuacion:Int=lobo!!.puntuacion.puntuacion
        var puntuacionFinal:String=puntuacion.toString().plus(" ").plus("puntos")
        canvas!!.drawBitmap(bitmapfinjuego,700f,300f, paint)
        canvas!!.drawBitmap(bitmapHome,770f,650f, paint)
        canvas!!.drawBitmap(bitmapRestart,1100f,650f, paint)
        canvas!!.drawText( puntuacionFinal, 1020f,495f, paint)
        canvas!!.drawText( segundosFinal, 930f,560f, paint)
        var puntos:Int = lobo!!.puntuacion.puntuacion
        //Escribir en firebase
        //Escribir en firebase
        if(escrito==false){
            if(Opcions.loguejat==true){
                UserData.instance.puntuacion=puntos
                if(userNotInList(UserData.instance)==true){
                    database = FirebaseDatabase.getInstance().getReference()
                    database.child(Opcions.userId).setValue(UserData.instance)
                }

            }
            escrito=true
        }

    }

    fun dibujarBordes(){
        canvas!!.drawBitmap(bitmapBordeSuperior, 0f, 0f, paint)
        canvas!!.drawBitmap(bitmapBorde, 0f, 1020f, paint)
        canvas!!.drawBitmap(bitmapBordeSimple, 64f , 1020f ,paint)
        var multiplicador:String= "X ".plus(lobo!!.multiplicador.toString())
        canvas!!.drawText(multiplicador, 1820f, 1060f,paint)
        if(Facade.signo==1){
            var puntuacion:String= " + ".plus(Facade.ultimaPuntuacion.toString())
            canvas!!.drawText(puntuacion,980f, 1060f , paint)

        }
        if(Facade.signo==2){
            var puntuacion:String= " - ".plus(Facade.ultimaPuntuacion.toString())
            canvas!!.drawText(puntuacion,980f, 1060f , paint)
        }
        canvas!!.drawBitmap(bitmapPausa, 1860f, 0f, paint)
        if(lobo!!.objetoActivable!=null){
            if(lobo!!.objetoActivable is Invisibilidad){
                canvas!!.drawBitmap(bitmapInv!!,67f,1022f,paint)
            }
            else if(lobo!!.objetoActivable is AumentarVelocidad){
                canvas!!.drawBitmap(bitmapAumentoVel!!,67f,1022f,paint)
            }
            else{
                canvas!!.drawBitmap(bitmapCambio!!,67f,1022f,paint)
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
        var nivel:String= "NIVEL :   ".plus(Facade.nivel.toString()).plus("  -  ").plus(Facade.mapa.toString())
        canvas!!.drawText(nivel,350f, 30f,paint)
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
        canvas!!.drawText(Facade.opciones!!.get(0).toString(),785f,640f,paint)
        canvas!!.drawText(Facade.opciones!!.get(1).toString(),980f,640f,paint)
        canvas!!.drawText(Facade.opciones!!.get(2).toString(),1178f,640f,paint)
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
        var bitmapLoboOscuro:Bitmap?=null
        var bitmapLoboNeutro:Bitmap?=null
        var bitmapLoboLuz:Bitmap?=null
        var bitmapLoboOscuroInv:Bitmap?=null
        var bitmapLoboNeutroInv:Bitmap?=null
        var bitmapLoboLuzInv:Bitmap?=null

    }
    fun userNotInList(userData: UserData) : Boolean { //Comprova si l'usuari ja esta a la llista i si la nova puntuació és més alta


        for (i in Opcions.listaUsuarios) {
            if (i.userE.equals(userData.userE)) {
                if(i.puntuacion > userData.puntuacion) {
                    return false
                }
                else{
                    //Borrar
                    database = FirebaseDatabase.getInstance().getReference()
                    database.child(i.userID).removeValue()
                    return true
                }


            }
        }

        return true
    }
}