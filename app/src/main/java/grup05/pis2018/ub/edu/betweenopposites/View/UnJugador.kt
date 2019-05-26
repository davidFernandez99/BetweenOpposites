package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import com.dev2qa.gestureexample.DetectSwipeGestureListener
import grup05.pis2018.ub.edu.betweenopposites.Game.DisplayThread
import grup05.pis2018.ub.edu.betweenopposites.Game.GameView
import grup05.pis2018.ub.edu.betweenopposites.Model.Lobo
import grup05.pis2018.ub.edu.betweenopposites.Model.Maquina
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import android.content.res.AssetManager



class UnJugador : AppCompatActivity(), View {

    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyObservers(fuente: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var observers: ArrayList<Presenter>
    private var gameView: GameView? = null
    var context:Context= this
    // This is the gesture detector compat instance.
    private var gestureDetectorCompat: GestureDetectorCompat? = null
    val gestureListener: DetectSwipeGestureListener = DetectSwipeGestureListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags=(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        gestureDetectorCompat = GestureDetectorCompat(this, gestureListener)
        // Create a common gesture listener object.
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        DisplayThread.playing=true
        gameView = GameView(this, size)
        setContentView(gameView)


   }

    override fun onStart() {
        super.onStart()
        gameView!!.onStart()
    }
    override fun onResume() {
        super.onResume()
        gameView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        gameView!!.onPause()
    }

    override fun onStop() {
        super.onStop()
        gameView!!.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        //gameView?.onDestroy()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat!!.onTouchEvent(event)

        var x: Float = event.x
        var y: Float = event.y

        if(event.action == MotionEvent.ACTION_DOWN) {

            Log.i("finger postion", x.toString())
            Log.i("finger postion", y.toString())
           if ((x > 1700) && (y < 40f) && !DisplayThread.paused) {
               DisplayThread.paused = true
               DisplayThread.mostrar_Pause=true
            } else if ((x > 770f) && (x < 858f) && (y > 550f) && (y < 638f) && DisplayThread.paused && DisplayThread.fin_juego==false && DisplayThread.dando_opciones==false && DisplayThread.fallar==false) {
                DisplayThread.playing = false
                val intent = Intent(this,MainActivity::class.java)
               DisplayThread.mostrar_Pause=false
               this.startActivity(intent)
            }else if((x>  1100f) && (x<1188f )  && (y<638f) && (y> 550f) && DisplayThread.paused && DisplayThread.fin_juego==false && DisplayThread.dando_opciones==false&& DisplayThread.fallar==false){
                DisplayThread.paused = false
               DisplayThread.mostrar_Pause=false
           }else if ((x > 770f) && (x < 858f) && (y > 650f) && (y < 738f) && DisplayThread.paused && DisplayThread.fin_juego==true && DisplayThread.dando_opciones==false) {
               DisplayThread.playing = false
               val intent = Intent(this,MainActivity::class.java)
               this.startActivity(intent)
           }else if((x>  1100f) && (x<1188f )  && (y<738f) && (y> 650f) && DisplayThread.paused && DisplayThread.fin_juego==true && DisplayThread.dando_opciones==false){
               DisplayThread.playing = false
               val intent = Intent(this,MainActivity::class.java)
               this.startActivity(intent)
           }else if( (x > 770f )&& (x< 814f) && (y > 618f) && (y< 662f) && (DisplayThread.dando_opciones==true) && (DisplayThread.fallar==false)){//Opcion 1 opciones maquina
               DisplayThread.dando_opciones=false
               DisplayThread.comprobar_opcion=true
               DisplayThread.opcion=0

           }else if((x > 966f )&& (x< 1010f) && (y > 618f) && (y< 662f) && (DisplayThread.dando_opciones==true)&& (DisplayThread.fallar==false)){//Opcion 2 opciones maquina
               DisplayThread.dando_opciones=false
               DisplayThread.comprobar_opcion=true
               DisplayThread.opcion=1
           }else if((x > 1162f )&& (x< 1206f) && (y > 618f) && (y< 662f)&& (DisplayThread.dando_opciones==true)&& (DisplayThread.fallar==false)){//Opcion 3 opciones maquina
               DisplayThread.dando_opciones=false
               DisplayThread.comprobar_opcion=true
               DisplayThread.opcion=2
           }else if(x >64f && x< 124f  && y >1020f && y <1080f && DisplayThread.activar_efecto==false){
               if(Lobo.instance.objetoActivable!=null){
                   DisplayThread.activar_efecto=true

               }
           }//else if( DisplayThread.fallar==true){
               //DisplayThread.fallar==false
           //}
        }
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true
    }






}
