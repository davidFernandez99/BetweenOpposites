package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.SignInButton
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import kotlinx.android.synthetic.main.activity_opcions.*


import com.google.android.gms.common.ConnectionResult

import com.google.android.gms.common.api.GoogleApiClient

import android.os.Vibrator
import android.util.Log
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*
import grup05.pis2018.ub.edu.betweenopposites.Model.Facade
import grup05.pis2018.ub.edu.betweenopposites.Model.UserAdapter
import grup05.pis2018.ub.edu.betweenopposites.Model.UserData
import grup05.pis2018.ub.edu.betweenopposites.R


const val RC_SIGN_IN = 123

lateinit var auth: FirebaseAuth
lateinit var mGoogleSignInClient: GoogleSignInClient
lateinit var mGoogleSignInOptions: GoogleSignInOptions
lateinit var database : DatabaseReference
lateinit var usersList : ArrayList<UserData>

class Opcions : AppCompatActivity(), grup05.pis2018.ub.edu.betweenopposites.View.View,
    GoogleApiClient.OnConnectionFailedListener{


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

    }
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyObservers(fuente: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {
        var musica=true
        var efectos= true
        var vibracion = true
        lateinit var userId : String
        var auth2:FirebaseAuth?=null
        var loguejat:Boolean=false
        lateinit var listaUsuarios:ArrayList<UserData>
    }


    lateinit var observers: ArrayList<Presenter>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opcions)

        usersList = arrayListOf()
        //Mantenim el valor dels switches tot i cambiar de activities
        //Controlamos estado del switch musica
        val swtMusica = findViewById<Switch>(R.id.switch_musica)
        val swtEfectos = findViewById<Switch>(R.id.switch_efectos)
        val swtVibracion = findViewById<Switch>(R.id.switch_vibracion)

        if(musica == true){
            swtMusica.isChecked = true

        }else {
            swtMusica.isChecked = false
        }

        //Controlamos estado del switch efectos
        if(efectos == true){
            swtEfectos.isChecked = true
        }else{
            swtEfectos.isChecked = false
        }

        //Controlamos estado del switch vibracion
        if(vibracion == true){
            swtVibracion.isChecked = true
        }else{
            swtVibracion.isChecked = false
        }




        auth = FirebaseAuth.getInstance()
        Opcions.auth2=FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference()

        btn_logOut.visibility = View.INVISIBLE //Inicialment esta invisible fins que s'inicia sessio

        val intent = Intent(this, MainActivity::class.java)
        intent.flags=(Intent.FLAG_ACTIVITY_CLEAR_TOP) //T'envia sempre a la pantalla principal quan li dones enrere

        sign_in_button.setSize(SignInButton.SIZE_STANDARD) //Mostra el boto iniciar sesio amb l'estil epr defecte

        //Request the user data
        configureGoogleSignIn()
        setupUI()

        //Botó per tancar sessio
        btn_logOut.setOnClickListener {
            signOut()
            Toast.makeText(this, "Sesión Cerrada", Toast.LENGTH_SHORT).show()
            sign_in_button.visibility=View.VISIBLE
            btn_logOut.visibility=View.GONE
            txt_email.visibility=View.GONE
            Opcions.loguejat=true
            txt_nomUsuari.visibility=View.GONE
        }

        //Switch per controlar la música
        swtMusica.setOnCheckedChangeListener { SwitchView, isChecked ->
            if(!MainActivity.player.isPlaying){
                MainActivity.player.start()
            }
            if (swtMusica.isChecked) {
                Toast.makeText(this, "Música ON", Toast.LENGTH_SHORT).show()
                //Agafem el valor actual del switch
                Opcions.musica=true


            } else {
                Toast.makeText(this, "Música OFF", Toast.LENGTH_SHORT).show()

                //Agafem el valor actual del switch
                if(MainActivity.player.isPlaying){
                    MainActivity.player.pause()
                }
                Opcions.musica=false

            }
        }

        //Switch per controlar els efectes
        swtEfectos.setOnCheckedChangeListener { SwitchView, isChecked ->
            if (swtEfectos.isChecked) {
                Opcions.efectos=true
                Toast.makeText(this, "Efectos ON", Toast.LENGTH_SHORT).show()
                Facade.efectos_activados=true


            } else {
                Opcions.efectos=false
                Toast.makeText(this, "Efectos OFF", Toast.LENGTH_SHORT).show()
                Facade.efectos_activados=false


            }
        }

        //Switch per controlar la vibració
        swtVibracion.setOnCheckedChangeListener { SwitchView, isChecked ->
            if (swtVibracion.isChecked) {
                Opcions.vibracion=true
                Toast.makeText(this, "Vibración ON", Toast.LENGTH_SHORT).show()
                Facade.vibracion_activada=true


                val v: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator //Fem vibar el mbl al activar l'opcio
                v.vibrate(500)
            } else {
                Opcions.vibracion=false
                Toast.makeText(this, "Vibración OFF", Toast.LENGTH_SHORT).show()
                Facade.vibracion_activada=false


            }
        }

    }


    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }

    private fun setupUI() {
        sign_in_button.setOnClickListener {
            userId  = database.push().key!!
            signIn()
        }
    }

    //Obre el intent de google per iniciar sessio
    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            }catch (e: ApiException){
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Authentication with firebase
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        //Log.d("TAG", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(this) {task->
            if (task.isSuccessful) {
                //Log.d("TAG", "signInWithCredential:success")
                Toast.makeText(this, "Sesión Iniciada", Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                sign_in_button.visibility=View.GONE
                txt_email.text=user?.email
                txt_nomUsuari.text=user?.displayName
                txt_email.visibility=View.VISIBLE
                txt_nomUsuari.visibility=View.VISIBLE
                btn_logOut.visibility=View.VISIBLE
                UserData.instance.userName=user!!.displayName.toString()
                UserData.instance.userE=user!!.email.toString()
                UserData.instance.userID= userId
                Opcions.loguejat=true
                llenarListaUsuarios()

            } else {
                //Log.w("TAG", "signInWithCredential:failure", task.exception)
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    //Check if the user is signed in already
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            sign_in_button.visibility=View.GONE
            txt_email.text=user.email
            txt_nomUsuari.text=user.displayName
            txt_email.visibility=View.VISIBLE
            txt_nomUsuari.visibility=View.VISIBLE
            btn_logOut.visibility=View.VISIBLE

        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
        //FirebaseAuth.getInstance().signOut();
    }

    fun llenarListaUsuarios(){

        database.addValueEventListener(object : ValueEventListener {
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
                    Opcions.listaUsuarios=usersList

                }
            }

        })
    }

    fun userNotInList(userData: UserData) : Boolean { //Comprova si l'usuari ja esta a la llista i si la nova puntuació és més alta

        if(Opcions.auth2!=null){
            for (i in usersList) {
                if (i.userE.equals(userData.userE)) {
                    if(i.puntuacion < userData.puntuacion) {
                        i.puntuacion = userData.puntuacion
                    }
                    return false
                }
            }
        }
        return true
    }

}