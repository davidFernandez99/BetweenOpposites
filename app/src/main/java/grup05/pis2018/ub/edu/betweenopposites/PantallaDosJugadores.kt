package grup05.pis2018.ub.edu.betweenopposites

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import grup05.pis2018.ub.edu.betweenopposites.databinding.ActivityPantallaDosJugadoresBinding
import kotlin.random.Random

class PantallaDosJugadores : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaDosJugadoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pantalla_dos_jugadores)

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
        }
    }

    private fun crearIDPartida(): Long {
        val id = Random.nextLong(10000000000)
        return id
    }
}
