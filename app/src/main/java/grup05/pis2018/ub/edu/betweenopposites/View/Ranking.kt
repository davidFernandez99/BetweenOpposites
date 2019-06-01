package grup05.pis2018.ub.edu.betweenopposites.View

import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import android.widget.ScrollView
import android.widget.Toast
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
    lateinit var usersList : ArrayList<UserData> //Llista de usuaris
    lateinit var listView : ListView

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

        val user = auth.currentUser
        database = FirebaseDatabase.getInstance().getReference()  //Referencia de la nostre database

        listView= findViewById(R.id.listView)


        if(!(user?.email.toString().equals(database.child(Opcions.userId).child("email")))){
            //Carregem a la base de dades
            val userE : String = user?.displayName.toString() //Nom de l'usari guardara la seva maxima puntuació
            val emailE = user?.email.toString()
            val puntuacio : Int = 50

            userData=UserData(Opcions.userId,userE,emailE,puntuacio)
            usersList = arrayListOf()//Inicialitzem llista

            //Guardem a firebase les dades de l'usuari
            database.child(Opcions.userId).setValue(userData)

            database.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0.exists()){
                        usersList.clear()
                        //Guardem les dades de firebase a la nostre llista d'usuaris
                        for(h in p0.children) {
                            val user = h.getValue(UserData::class.java)
                            if(userNotInList(user!!)) {
                                //Afegim a la llista l'usuari
                                usersList.add(user)
                            }


                        }

                        //Ordenamos la lista
                        ordenarLista()

                        //Pasamos la usersLists al Adapter, y mostramos todos los usuarios por orden
                        val adapter = UserAdapter(applicationContext, R.layout.users, usersList)
                        listView.adapter = adapter

                    }
                }

            })

        }

    }

    fun userNotInList(userData: UserData) : Boolean { //Comprova si l'usuari ja esta a la llista i si la nova puntuació és més alta

        for (i in usersList) {
            if (i.userE.equals(userData.userE)) {
               if(i.puntuacion < userData.puntuacion) {
                    i.puntuacion = userData.puntuacion
                }
                return false
            }
        }
        return true
    }


    fun ordenarLista(){
        for (i in usersList.indices) {
            for (j in usersList.indices) {
                if (usersList[i].puntuacion > usersList[j].puntuacion) {
                    val userProv = usersList[i]
                    usersList.set(i, usersList[j])
                    usersList.set(j, userProv)

                }
            }
        }
    }

}
