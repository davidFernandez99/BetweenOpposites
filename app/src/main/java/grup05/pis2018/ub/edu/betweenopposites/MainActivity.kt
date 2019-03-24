package grup05.pis2018.ub.edu.betweenopposites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_un_jugador.view.*

/**
 * Projecte Integrat de Software
 * Creat per:
 *  Óscar Jiménez Muriano
 *  Joan Martín Martrus
 *  Albert Pérez Costa
 *  David Fernández Fernández
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.unJugadorButton)
        btn.setOnClickListener {
            val intent = Intent(this, UnJugador::class.java)
            startActivity(intent)
        }

    }
}
