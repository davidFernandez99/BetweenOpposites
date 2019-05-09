package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R

class FinJuegoActivity : AppCompatActivity(), View {

    //override lateinit var observers: ArrayList<Presenter>

    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*override fun notifyObservers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fin_juego)

        val btn_home = findViewById<Button>(R.id.button_home)
        btn_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btn_retry = findViewById<Button>(R.id.button_retry)
        btn_retry.setOnClickListener {
            val intent = Intent(this, UnJugador::class.java)
            startActivity(intent)
        }

    }
}
