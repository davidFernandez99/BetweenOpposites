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
 *  TODO: Que cada uno ponga aqui su nombre para hacer comprovar que nos funciona a todos.
 */

/*TODO: PARA HACER LOS COMITS INTENTAD PONER EL #"numero tasca" DONDE el numero de la tasca SE IDENTIFICA CON LA TASCA QUE ESTÀ ASSIGNADA ej; #2 -> es Crear Proyecto*/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn= findViewById<Button>(R.id.unJugadorButton)
        btn.setOnClickListener {
            val intent= Intent(this,UnJugador::class.java)
            startActivity(intent)
        }

    }
}
