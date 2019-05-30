package grup05.pis2018.ub.edu.betweenopposites.View

import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import android.widget.ScrollView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import grup05.pis2018.ub.edu.betweenopposites.Model.Puntuacion
import grup05.pis2018.ub.edu.betweenopposites.Model.UserAdapter
import grup05.pis2018.ub.edu.betweenopposites.Model.UserData
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R
import kotlinx.android.synthetic.main.activity_ranking.*
import java.util.*
import kotlin.collections.ArrayList

class Ranking : AppCompatActivity(), View {

    lateinit var observers: ArrayList<Presenter>
    lateinit var database : DatabaseReference
    lateinit var userData: UserData //Guardarem l'informació de l'usuari registrat
    lateinit var usersList : MutableList<UserData> //Llista de usuaris
    lateinit var listView : ListView

    //TODO: Array de usuaris i ordenarlo segons puntuació

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
        setContentView(R.layout.activity_ranking)

        var user = auth.currentUser
        database = FirebaseDatabase.getInstance().getReference()
        usersList = mutableListOf() //Inicialitzem llista
        listView= findViewById(R.id.listView)

        //Carregem a la base de dades
        var userE : String = user?.displayName.toString() //Nom de l'usari guardara la seva maxima puntuació
        var emailE = user?.email.toString()
        var puntuacio : Int = 0

        userData=UserData(userE,emailE,puntuacio)
        //Fer mètode que guardi la puntuació més alta. (Fer que agafi la puntuacio d'aquell usuari de firebase i compari amb la pntuacio que acaba de fer a la partida i es quedi amb la més alta)
        database.child("usuario").child(userE).setValue(puntuacio)



           /* nombreJugador_rank.text= user.displayName
            Picasso.with(this).load(user.photoUrl).into(img_puntuacion_1)
*/
            database.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0.exists()){
                        for(h in p0.children) {
                            val user = h.getValue(UserData::class.java)
                            //Afegim a la llista l'usuari
                            usersList.add(user!!)

                            /*val puntuacion = p0.getValue()
                            txt_puntuacion_1.text = puntuacion.toString()*/
                        }

                        val adapter = UserAdapter(applicationContext, R.layout.users, usersList)
                        listView.adapter = adapter

                    }
                }

            })




    }

}
