package grup05.pis2018.ub.edu.betweenopposites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

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
            val intent = Intent(this, Opcions::class.java)
            startActivity(intent)
        }

}
}
