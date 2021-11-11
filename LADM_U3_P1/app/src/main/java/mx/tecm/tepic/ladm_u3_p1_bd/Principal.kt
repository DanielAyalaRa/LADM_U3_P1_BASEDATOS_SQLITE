package mx.tecm.tepic.ladm_u3_p1_bd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_principal.*

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        btnConductor.setOnClickListener {
            var ventana = Intent(this,MainActivity::class.java)
            startActivity(ventana)
        }

        btnVehiculo.setOnClickListener {
            var ventana = Intent(this,MainActivity3::class.java)
            startActivity(ventana)
        }

        btnConsulta.setOnClickListener {
            var ventana = Intent(this,MainActivity2::class.java)
            startActivity(ventana)
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}