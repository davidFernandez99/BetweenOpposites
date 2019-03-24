package grup05.pis2018.ub.edu.betweenopposites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinJuegoActivity : AppCompatActivity() {

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
