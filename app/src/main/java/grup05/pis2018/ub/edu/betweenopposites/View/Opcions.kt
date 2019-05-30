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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import grup05.pis2018.ub.edu.betweenopposites.Model.UserData
import grup05.pis2018.ub.edu.betweenopposites.R


const val RC_SIGN_IN = 123

lateinit var auth: FirebaseAuth
lateinit var mGoogleSignInClient: GoogleSignInClient
lateinit var mGoogleSignInOptions: GoogleSignInOptions


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

    lateinit var observers: ArrayList<Presenter>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opcions)


        //Mantenim el valor dels switches tot i cambiar de activities
        val sharedPrefs = getSharedPreferences("opcions", Context.MODE_PRIVATE)
        switch_musica.setChecked(sharedPrefs.getBoolean("swtMusica", true))
        switch_efectos.setChecked(sharedPrefs.getBoolean("swtEfectos", true))
        switch_vibracion.setChecked(sharedPrefs.getBoolean("swtVibracion", true))

        val swtMusica = findViewById<Switch>(R.id.switch_musica)
        val swtEfectos = findViewById<Switch>(R.id.switch_efectos)
        val swtVibracion = findViewById<Switch>(R.id.switch_vibracion)

        auth = FirebaseAuth.getInstance()

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

                val editor: SharedPreferences.Editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                editor.putBoolean("swtMusica", true)
                editor.commit()

            } else {
                Toast.makeText(this, "Música OFF", Toast.LENGTH_SHORT).show()
                //Agafem el valor actual del switch
                if(MainActivity.player.isPlaying){
                    MainActivity.player.pause()
                }
                val editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                editor.putBoolean("swtMusica", false)
                editor.commit()
            }
        }

        //Switch per controlar els efectes
        swtEfectos.setOnCheckedChangeListener { SwitchView, isChecked ->
            if (swtEfectos.isChecked) {
                Opcions.efectos=true
                Toast.makeText(this, "Efectos ON", Toast.LENGTH_SHORT).show()
                //Agafem el valor actual del switch
                val editor: SharedPreferences.Editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                editor.putBoolean("swtEfectos", true)
                editor.commit()
            } else {
                Opcions.efectos=false
                Toast.makeText(this, "Efectos OFF", Toast.LENGTH_SHORT).show()
                //Agafem el valor actual del switch
                val editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                editor.putBoolean("swtEfectos", false)
                editor.commit()

            }
        }

        //Switch per controlar la vibració
        swtVibracion.setOnCheckedChangeListener { SwitchView, isChecked ->
            if (swtVibracion.isChecked) {
                Opcions.vibracion=true
                Toast.makeText(this, "Vibración ON", Toast.LENGTH_SHORT).show()
                //Agafem el valor actual del switch
                val editor: SharedPreferences.Editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                editor.putBoolean("swtVibracion", true)
                editor.commit()

                val v: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator //Fem vibar el mbl al activar l'opcio
                v.vibrate(500)
            } else {
                Opcions.vibracion=false
                Toast.makeText(this, "Vibración OFF", Toast.LENGTH_SHORT).show()
                //Agafem el valor actual del switch
                val editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                editor.putBoolean("swtVibracion", false)
                editor.commit()

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

    companion object {
        var efectos= true
        var vibracion = true

    }
}
