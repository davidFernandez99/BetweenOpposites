package grup05.pis2018.ub.edu.betweenopposites.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R

class IniciarSessio : AppCompatActivity(), View {

    //override lateinit var observers: ArrayList<Presenter>

    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyObservers(fuente: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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