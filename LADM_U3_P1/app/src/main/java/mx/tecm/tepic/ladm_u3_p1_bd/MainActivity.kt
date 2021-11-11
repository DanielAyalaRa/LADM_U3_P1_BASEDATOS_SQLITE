package mx.tecm.tepic.ladm_u3_p1_bd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var listaID = ArrayList<String>()
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsertarCon.setOnClickListener {
            if (nombre.text.length == 0 || domicilio.text.length == 0 || nolicencia.text.length == 0 || vence.text.length == 0) {
                Toast.makeText(this,"RELLENE TODOS LOS CAMPOS (es obligatorio)", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            var cond = Conductor(
                nombre.text.toString(),
                domicilio.text.toString(),
                nolicencia.text.toString(),
                vence.text.toString()
            )

            cond.asignarPuntero(this)

            var res = cond.insertar()

            if(res == true) {
                mensaje("SE INSERTÓ EL CONDUCTOR CON EXITO")
                nombre.setText("")
                domicilio.setText("")
                nolicencia.setText("")
                vence.setText("")
                cargaInformacion()
            } else {
                mensaje("¡ERROR! NO SE PUDO AGREGAR EL CONDUCTOR")
            }
        }

        buscar.setOnClickListener {
            var ventana = Intent(this,MainActivity2::class.java)
            startActivityForResult(ventana,0)
        }

        cargaInformacion()
    }

    private fun mensaje(s: String) {
        AlertDialog.Builder(this).setTitle("ATENCION!!").setMessage(s)
            .setPositiveButton("OK"){
                    d,i-> d.dismiss()
            }
            .show()
    }

    private fun cargaInformacion(){
        try {
            var c = Conductor("","","","")
            c.asignarPuntero(this)
            var datos = c.recuperarDatos()

            var tamaño = datos.size-1
            var v = Array<String>(datos.size,{""})

            listaID = ArrayList<String>()
            (0..tamaño).forEach {
                var conductor = datos[it]
                var item = "nombre: "+conductor.nom+"\n"+"domicilio: "+conductor.dom+"\n"+"nolicencia: "+conductor.nol+"vence: "+conductor.ven
                v[it] = item
                listaID.add(conductor.id.toString())
            }

            lista.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,v)

            lista.setOnItemClickListener{ adapterView, view, i, l ->
                mostrarAlertEliminarActualizar(i)
            }
        }catch (e: Exception){
            mensaje(e.message.toString())
        }
    }

    private fun mostrarAlertEliminarActualizar(posicion: Int) {
        var idLista = listaID.get(posicion)

        AlertDialog.Builder(this)
            .setTitle("ATENCION!!")
            .setMessage("¿QUE DESEA HACER?")
            .setPositiveButton("INSERTAR Vehiculo") {d,i-> llamarVentanaVehiculo(idLista)}
            .setNeutralButton("ACTUALIZAR") {d,i-> llamarVentanaActualizar(idLista)}
            .setNegativeButton("CANCELAR") {d,i-> }
            .show()
    }

    private fun llamarVentanaActualizar(idLista: String) {
        var ventana = Intent(this,MainActivity3::class.java)
        var c = Conductor("","","","")
        c.asignarPuntero(this)

        var nombre = c.consultaID(idLista).nom
        var domicilio = c.consultaID(idLista).dom
        var nolicencia = c.consultaID(idLista).nol
        var vence = c.consultaID(idLista).ven

        ventana.putExtra("id",idLista)
        ventana.putExtra("nombre",nombre)
        ventana.putExtra("domicilio",domicilio)
        ventana.putExtra("nolicencia",nolicencia)
        ventana.putExtra("vence",vence)

        startActivityForResult(ventana,0)
    }

    private fun llamarVentanaVehiculo(idLista: String) {
        var ventana = Intent(this,InsertarVehiculos::class.java)

        ventana.putExtra("id",idLista)

        startActivityForResult(ventana,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cargaInformacion()
    }
}