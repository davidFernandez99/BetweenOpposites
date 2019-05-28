package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import grup05.pis2018.ub.edu.betweenopposites.R

class Tutorial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val aceptar  = findViewById<Button>(R.id.btn_accept)

        aceptar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.flags=(Intent.FLAG_ACTIVITY_CLEAR_TOP) //T'envia sempre a la pantalla principal quan li dones enrere
    }
}
