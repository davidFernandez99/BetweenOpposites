package grup05.pis2018.ub.edu.betweenopposites.View

import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import grup05.pis2018.ub.edu.betweenopposites.Model.Puntuacion
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R
import kotlinx.android.synthetic.main.activity_ranking.*
import java.util.*
import kotlin.collections.ArrayList

class Ranking : AppCompatActivity(), View {

    lateinit var observers: ArrayList<Presenter>
    lateinit var database : DatabaseReference

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

        //Carregem a la base de dades
        var userE : String = user?.displayName.toString() //Nom de l'usari guardara la seva maxima puntuació
        var emailE = user?.email.toString()
        var puntuacio : Int = 10
        //Fer mètode que guardi la puntuació més alta. (Fer que agafi la puntuacio d'aquell usuari de firebase i compari amb la pntuacio que acaba de fer a la partida i es quedi amb la més alta)
        database.child("usuario").child(userE).setValue(puntuacio)

        //val puntuacion = database.child("usuario").child(userE)


        if(user == null){
            img_puntuacion_1.setImageResource(R.drawable.interrogante)
            img_puntuacion_2.setImageResource(R.drawable.interrogante)
            img_puntuacion_3.setImageResource(R.drawable.interrogante)
            img_puntuacion_4.setImageResource(R.drawable.interrogante)
            img_puntuacion_5.setImageResource(R.drawable.interrogante)
        }else{
            nombreJugador_rank_1.text= user.displayName
            Picasso.with(this).load(user.photoUrl).into(img_puntuacion_1)
            database.child("usuario").child(userE).addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0!!.exists()){
                        var puntuacion = p0.getValue()
                        txt_puntuacion_1.text=puntuacion.toString()
                    }
                }

            })


        }

    }

}