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
import grup05.pis2018.ub.edu.betweenopposites.Game.GameView
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter

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
    var context:Context=this
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
        gameView = GameView(this, size)
        setContentView(gameView)


   }

    override fun onResume() {
        super.onResume()
        gameView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        //gameView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        //gameView?.onStop()
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
           /* if ((x < 100f) && (y < 100f) && !gameView!!.paused) {
                gameView!!.paused = true
            } else if ((x < 100f) && (y < 100f) && gameView!!.paused) {
                gameView!!.paused = false
            }*/
        }
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true
    }






}
