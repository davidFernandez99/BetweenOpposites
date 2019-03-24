package grup05.pis2018.ub.edu.betweenopposites

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_opcions.*

class IniciarSessio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sessio)

        val txtCorreu = findViewById<EditText>(R.id.txtCorreu)
        val txtContraseña = findViewById<EditText>(R.id.txtContra)
        val btnAcceptar = findViewById<Button>(R.id.btn_Acceptar)

        //Inicia la Sessio
        btnAcceptar.setOnClickListener {
            Toast.makeText(this, "Sessió Iniciada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}