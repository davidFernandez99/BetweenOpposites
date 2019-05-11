package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R

class PausaActivity : AppCompatActivity(), View {

    lateinit var observers: ArrayList<Presenter>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pausa)
        val btn_renaudar = findViewById<ImageButton>(R.id.btn_renaudar)
        btn_renaudar.setOnClickListener {
            val intent = Intent(this, UnJugador::class.java)
            startActivity(intent)
        }
        val btn_exit = findViewById<ImageButton>(R.id.btn_exit)
        btn_exit.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }
    }

    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyObservers(fuente: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
