package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R
import android.support.v4.app.FragmentTransaction


class UnJugador : AppCompatActivity(),View {
    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyObservers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override lateinit var observers: ArrayList<Presenter>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_un_jugador)
        val btn_pausa = findViewById<ImageButton>(grup05.pis2018.ub.edu.betweenopposites.R.id.btn_pausa)

        btn_pausa.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment_pausa).commit()

        }

        val btn_morir = findViewById<Button>(R.id.btn_Morir)
        btn_morir.setOnClickListener {
            val intent = Intent(this, FinJuegoActivity::class.java)
            startActivity(intent)
        }

        val btn_ganar = findViewById<Button>(R.id.btn_Ganar)
        btn_ganar.setOnClickListener {
            val intent = Intent(this, FinJuegoActivity::class.java)
            startActivity(intent)
        }

    }
}
