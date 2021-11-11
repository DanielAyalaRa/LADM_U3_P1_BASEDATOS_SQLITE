package mx.tecm.tepic.ladm_u3_p1_bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos (
    context: Context?,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CONDUCTORES(ID_CONDUCTOR INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(200), DOMICILIO VARCHAR(200), NOLICENCIA VARCHAR(200), " +
                "VENCE DATE);")
        db?.execSQL("CREATE TABLE VEHICULOS(ID_VEHICULO INTEGER PRIMARY KEY AUTOINCREMENT, PLACA VARCHAR(200), MARCA VARCHAR(200), MODELO VARCHAR(200), " +
                "AÑO INTEGER, FOREIGN KEY(ID_CONDUCTOR) REFERENCES CONDUCTORES(ID_CONDUCTOR)));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}