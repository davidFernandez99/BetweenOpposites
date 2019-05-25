package grup05.pis2018.ub.edu.betweenopposites.View

import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R
import kotlinx.android.synthetic.main.activity_ranking.*

class Ranking : AppCompatActivity(), View {

    lateinit var observers: ArrayList<Presenter>
    //lateinit var dataSnapshot: DataSnapshot

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

        var user = firebaseAuth.currentUser

        if(user == null){
            img_puntuacion_1.setImageResource(R.drawable.interrogante)
            img_puntuacion_2.setImageResource(R.drawable.interrogante)
            img_puntuacion_3.setImageResource(R.drawable.interrogante)
            img_puntuacion_4.setImageResource(R.drawable.interrogante)
            img_puntuacion_5.setImageResource(R.drawable.interrogante)
        }else{
            nombreJugador_rank_1.text= user.displayName
            Picasso.with(this).load(user.photoUrl).into(img_puntuacion_1)
        }


        //val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("message")

        //myRef.setValue("Hello, World!")

        // Read from the database
       /* myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                Log.d("TAG", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })*/

    }

}
