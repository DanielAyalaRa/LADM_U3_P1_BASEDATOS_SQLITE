package mx.tecm.tepic.ladm_u3_p1_bd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import java.util.ArrayList

class Vehiculo (i:String, f:ByteArray?){
    var id = 0
    var id_conduc = i
    var fecha = f

    val nombre = "CONDUCTOR/PERSONAS"
    var puntero : Context?= null

    fun asignarPuntero(p: Context) {
        puntero = p
    }

    fun insertar():Boolean {
        try {
            var base = BaseDatos(puntero!!, nombre, null, 1)
            var insertar = base.writableDatabase
            var datos = ContentValues()

            datos.put("ID_CONDUCTOR", id_conduc.toInt())
            datos.put("FECHA", fecha)

            var res = insertar.insert("VEHICULOS", "ID_VEHICULO",datos)

            if (res.toInt() == -1) {
                return false
            }
        }catch (e: SQLiteException) {
            return false
        }
        return true
    }

    fun recuperarDatos(id:String): ArrayList<Vehiculo> {
        var data = ArrayList<Vehiculo>()
        try{
            var bd = BaseDatos(puntero!!,nombre,null,1 )
            var select = bd.readableDatabase
            var columnas = arrayOf("ID_VEHICULO,ID_CONDUCTOR")
            var conductor = arrayOf(id)

            var cursor  = select.query("VEHICULOS", columnas, "ID_CONDUCTOR = ?", conductor, null, null, null, null, null)
            if(cursor.moveToFirst()){
                do{
                    var temp = Vehiculo(cursor.getString(1), ByteArray(0))
                    temp.id = cursor.getInt(0)
                    data.add(temp)
                }while (cursor.moveToNext())
            }else{
            }
        }catch (e: SQLiteException){
        }
        return data
    }

    fun eliminar(id:String):Boolean{
        try{
            var base = BaseDatos(puntero!!, nombre,null,1)
            var eliminar = base.writableDatabase
            var eliminarID = arrayOf(id)

            var res = eliminar.delete("VEHICULOS","ID_VEHICULO = ?",eliminarID)
            if(res == 0){
                return false
            }
        }catch (e: SQLiteException){
            return false
        }
        return true
    }

    fun eliminarPorAct(id:String):Boolean{
        try{
            var base = BaseDatos(puntero!!, nombre,null,1)
            var eliminar = base.writableDatabase
            var eliminarID = arrayOf(id)

            var res = eliminar.delete("VEHICULOS","ID_CONDUCTOR = ?",eliminarID)
            if(res == 0){
                return false
            }
        }catch (e: SQLiteException){
            return false
        }
        return true
    }

    fun actualizar():Boolean{
        try{
            var base = BaseDatos(puntero!!, nombre,null,1)
            var actualizar = base.writableDatabase
            var datos = ContentValues()
            var actualizarID = arrayOf(id.toString())

            datos.put("ID_CONDUCTOR", id_conduc)
            datos.put("FECHA", fecha)

            var res = actualizar.update("VEHICULOS",datos,"ID_VEHICULO = ?", actualizarID)
            if(res == 0){
                return false
            }
        }catch (e: SQLiteException){
            return false
        }
        return true
    }
}