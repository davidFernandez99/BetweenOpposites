package grup05.pis2018.ub.edu.betweenopposites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class PausaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pausa)
        val btn_renaudar= findViewById<ImageButton>(R.id.btn_renaudar)
        btn_renaudar.setOnClickListener {
            val intent= Intent(this,UnJugador::class.java)
            startActivity(intent)
        }
        val btn_exit= findViewById<ImageButton>(R.id.btn_exit)
        btn_exit.setOnClickListener {
            val intent2= Intent(this,MainActivity::class.java)
            startActivity(intent2)
        }
    }
}
