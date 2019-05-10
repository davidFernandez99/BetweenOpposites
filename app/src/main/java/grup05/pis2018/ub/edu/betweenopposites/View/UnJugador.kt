package grup05.pis2018.ub.edu.betweenopposites.View

import android.graphics.Point
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
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

    /*override fun notifyObservers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override lateinit var observers: ArrayList<Presenter>*/

    private var gameView: GameView? = null
    // This is the gesture detector compat instance.
    private var gestureDetectorCompat: GestureDetectorCompat? = null
    val gestureListener: DetectSwipeGestureListener = DetectSwipeGestureListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gestureDetectorCompat = GestureDetectorCompat(this, gestureListener)
        // Create a common gesture listener object.
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        gameView = GameView(this, size)
        setContentView(gameView)


        /* setContentView(R.layout.activity_un_jugador)
         val btn_pausa = findViewById<ImageButton>(R.id.btn_pausa)
         btn_pausa.setOnClickListener {
             val intent = Intent(this, PausaActivity::class.java)
             startActivity(intent)
         }

 */
    }

    override fun onResume() {
        super.onResume()
        gameView?.resume()
    }

    override fun onPause() {
        super.onPause()
        gameView?.pause()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat!!.onTouchEvent(event)
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true
    }
}
