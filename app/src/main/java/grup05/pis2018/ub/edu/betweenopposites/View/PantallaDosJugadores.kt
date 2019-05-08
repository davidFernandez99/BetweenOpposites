package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R
import kotlin.random.Random

class PantallaDosJugadores : AppCompatActivity(), grup05.pis2018.ub.edu.betweenopposites.View.View {
    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyObservers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var binding: ActivityPantallaDosJugadoresBinding
    override lateinit var observers: ArrayList<Presenter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_pantalla_dos_jugadores
        )

        binding.btnCancelarDosJ.setOnClickListener {
            finish()
        }

        binding.dosJBtnCrearPartida.setOnClickListener {
            val crearPartidaLayout = binding.dosJLayoutCrearPartida

            // Alterna la visibilitat de la funcio crear
            if (crearPartidaLayout.visibility == View.VISIBLE) {
                crearPartidaLayout.visibility = View.GONE
            } else if (crearPartidaLayout.visibility == View.GONE) {
                //Crea la ID d'una partida Multijugador
                binding.darIdDosJ.text = crearIDPartida().toString()
                crearPartidaLayout.visibility = View.VISIBLE

            }

            val intent = Intent(this, UnJugador::class.java)
            startActivity(intent)

        }

        binding.dosJBtnUnirsePartida.setOnClickListener {
            val unirsePartidaLayout = binding.dosJLayoutUnirsePartida

            // Alterna la visibilitat de la funcio crear
            if (unirsePartidaLayout.visibility == View.VISIBLE) {
                binding.ponerIdDosJ.setText("")
                unirsePartidaLayout.visibility = View.GONE
            } else if (unirsePartidaLayout.visibility == View.GONE) {
                //Crea la ID d'una partida Multijugador
                unirsePartidaLayout.visibility = View.VISIBLE
            }

            val intent = Intent(this, UnJugador::class.java)
            startActivity(intent)
        }
    }

    private fun crearIDPartida(): Long {
        val id = Random.nextLong(10000000000)
        return id
    }
}
