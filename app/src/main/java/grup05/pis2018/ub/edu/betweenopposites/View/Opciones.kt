import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.IniciarSessio
import grup05.pis2018.ub.edu.betweenopposites.View.View

class Opciones : AppCompatActivity(), View {
    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /*override fun notifyObservers(fuente: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opcions)

        val swtMusica = findViewById<Switch>(R.id.switch_musica)
        val swtEfectos = findViewById<Switch>(R.id.switch_efectos)
        val swtVibracion = findViewById<Switch>(R.id.switch_vibracion)
        val btnIniciaSesio = findViewById<Button>(R.id.btn_Google)
        val btnTancarSessio = findViewById<Button>(R.id.btn_Tancar)

        //btnTancarSessio.visibility = View.INVISIBLE //Inicialment esta invisible fins que s'inicia sessio

        //Switch per controlar la música
        swtMusica.setOnCheckedChangeListener { SwitchView, isChecked ->
            if (swtMusica.isChecked) {
                Toast.makeText(this, "Música ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Música OFF", Toast.LENGTH_SHORT).show()
            }
        }

        //Switch per controlar els efectes
        swtEfectos.setOnCheckedChangeListener { SwitchView, isChecked ->
            if (swtEfectos.isChecked) {
                Toast.makeText(this, "Efectos ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Efectos OFF", Toast.LENGTH_SHORT).show()
            }
        }

        //Switch per controlar la vibració
        swtVibracion.setOnCheckedChangeListener { SwitchView, isChecked ->
            if (swtVibracion.isChecked) {
                Toast.makeText(this, "Vibración ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vibración OFF", Toast.LENGTH_SHORT).show()
            }
        }

        //Obre una activity per inciar sessio al polsar el botó
        btnIniciaSesio.setOnClickListener {
            val intent = Intent(this, IniciarSessio::class.java)
            startActivity(intent)
            //btnIniciaSesio.visibility = View.INVISIBLE
            //btnTancarSessio.visibility = View.VISIBLE
        }

        //Tanca la sessio
        btnTancarSessio.setOnClickListener {
            Toast.makeText(this, "Sessió Tancada", Toast.LENGTH_SHORT).show()
            //btnTancarSessio.visibility = View.INVISIBLE
            //btnIniciaSesio.visibility = View.VISIBLE
        }


    }


}