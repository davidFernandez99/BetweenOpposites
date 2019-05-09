package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R

/**
 * Projecte Integrat de Software
 * Creat per:
 *  Óscar Jiménez Muriano
 *  Joan Martín Martrus
 *  Albert Pérez Costa
 *  David Fernández Fernández
 */

class MainActivity : AppCompatActivity(),View {

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
        setContentView(R.layout.activity_main)
        val btn_unjugador = findViewById<ImageButton>(R.id.btn_unjugador)
        btn_unjugador.setOnClickListener {
            val intent = Intent(this, UnJugador::class.java)
            startActivity(intent)
        }

        val btn_dosjugadores = findViewById<ImageButton>(R.id.btn_dosjugadors)
        btn_dosjugadores.setOnClickListener {
            val intent = Intent(this, PantallaDosJugadores::class.java)
            startActivity(intent)
        }

        val btn_ranking = findViewById<ImageButton>(R.id.btn_ranking)
        btn_ranking.setOnClickListener {
            val intent = Intent(this, Ranking::class.java)
            startActivity(intent)
        }
        val btn_opciones = findViewById<ImageButton>(R.id.btn_opciones)
        btn_opciones.setOnClickListener {
            //val intent = Intent(this, Opcions::class.java)
            //startActivity(intent)
        }

    }
}
