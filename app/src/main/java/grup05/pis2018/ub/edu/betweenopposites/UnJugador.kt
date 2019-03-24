package grup05.pis2018.ub.edu.betweenopposites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_un_jugador.*

class UnJugador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_un_jugador)
        val btn_pausa = findViewById<ImageButton>(R.id.btn_pausa)
        btn_pausa.setOnClickListener {
            val intent = Intent(this, PausaActivity::class.java)
            startActivity(intent)
        }
    }
}
