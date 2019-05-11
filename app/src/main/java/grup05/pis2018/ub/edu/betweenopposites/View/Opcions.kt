package grup05.pis2018.ub.edu.betweenopposites.View

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import kotlinx.android.synthetic.main.activity_opcions.*


import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult

import com.google.android.gms.common.api.GoogleApiClient

import android.os.Vibrator
import grup05.pis2018.ub.edu.betweenopposites.R


const val RC_SIGN_IN = 123



    class Opcions : AppCompatActivity(), grup05.pis2018.ub.edu.betweenopposites.View.View,
        GoogleApiClient.OnConnectionFailedListener {



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
            val btnTancarSessio = findViewById<Button>(R.id.btn_logOut)

            btnTancarSessio.visibility = View.INVISIBLE //Inicialment esta invisible fins que s'inicia sessio

            val intent = Intent(this, MainActivity::class.java)
            intent.flags=(Intent.FLAG_ACTIVITY_CLEAR_TOP) //T'envia sempre a la pantalla principal quan li dones enrere


            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            // Build a GoogleSignInClient with the options specified by gso.
            var googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()


            sign_in_button.setSize(SignInButton.SIZE_STANDARD) //Mostra el boto iniciar sesio amb l'estil epr defecte
            //Botó per inciar sesio amb google
            sign_in_button.setOnClickListener {
                val signInIntent= Auth.GoogleSignInApi.getSignInIntent(googleApiClient) //Obre el selector de comptes de google
                startActivityForResult(signInIntent, RC_SIGN_IN)
                sign_in_button.visibility=View.INVISIBLE

            }

            //Botó per tancar sessio
            btnTancarSessio.setOnClickListener {
                if (googleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(googleApiClient);
                    Toast.makeText(this, "Sesión Cerrada", Toast.LENGTH_SHORT).show()
                    sign_in_button.visibility=View.VISIBLE
                    btnTancarSessio.visibility=View.GONE
                    txt_email.visibility=View.GONE
                    txt_nomUsuari.visibility=View.GONE

                }
            }


            //Switch per controlar la música
            swtMusica.setOnCheckedChangeListener { SwitchView, isChecked ->
                if (swtMusica.isChecked) {
                    Toast.makeText(this, "Música ON", Toast.LENGTH_SHORT).show()
                    //Agafem el valor actual del switch
                    if(!MainActivity.player.isPlaying){
                        MainActivity.player.start()
                    }
                    val editor: SharedPreferences.Editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                    editor.putBoolean("swtMusica", true)
                    editor.commit()

                } else {
                    Toast.makeText(this, "Música OFF", Toast.LENGTH_SHORT).show()
                    //Agafem el valor actual del switch
                    if(MainActivity.player.isPlaying){
                        MainActivity.player.stop()
                    }
                    val editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                    editor.putBoolean("swtMusica", false)
                    editor.commit()
                }
            }

            //Switch per controlar els efectes
            swtEfectos.setOnCheckedChangeListener { SwitchView, isChecked ->
                if (swtEfectos.isChecked) {
                    Toast.makeText(this, "Efectos ON", Toast.LENGTH_SHORT).show()
                    //Agafem el valor actual del switch
                    val editor: SharedPreferences.Editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                    editor.putBoolean("swtEfectos", true)
                    editor.commit()
                } else {
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
                    Toast.makeText(this, "Vibración ON", Toast.LENGTH_SHORT).show()
                    //Agafem el valor actual del switch
                    val editor: SharedPreferences.Editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                    editor.putBoolean("swtVibracion", true)
                    editor.commit()

                    val v:Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator //Fem vibar el mbl al activar l'opcio
                    v.vibrate(500)
                } else {
                    Toast.makeText(this, "Vibración OFF", Toast.LENGTH_SHORT).show()
                    //Agafem el valor actual del switch
                    val editor = getSharedPreferences("opcions", Context.MODE_PRIVATE).edit()
                    editor.putBoolean("swtVibracion", false)
                    editor.commit()

                }
            }

        }


        public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                handleSignInResult(result)
            }
        }

        private fun handleSignInResult(result: GoogleSignInResult) {  //Decimos que queremos hacer al iniciar sesion
            if(result.isSuccess){
                Toast.makeText(this,"Sesión Iniciada", Toast.LENGTH_SHORT).show()
                sign_in_button.visibility=View.GONE //El boton de inicio se deshabilita
                btn_logOut.visibility=View.VISIBLE  //Se muestra el de cerrar sesion

                //Mostrem les dades del usuari loguejat
                val account = result.signInAccount
                txt_email.text=account?.email
                txt_nomUsuari.text=account?.displayName
                txt_email.visibility=View.VISIBLE
                txt_nomUsuari.visibility=View.VISIBLE

            }else{
                Toast.makeText(this, "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show()
                sign_in_button.visibility=View.VISIBLE
            }
        }

    }