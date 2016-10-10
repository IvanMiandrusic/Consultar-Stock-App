package db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ivan on 3/10/2016.
 */
public abstract class dbManager {

    private dbHelper helper;
    private SQLiteDatabase db;

    public dbManager(Context ctx){
        this.helper = new dbHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void cerrar(){
        db.close();
    }

    abstract void insertar_parametros(String id, String articulo, String marca, String precio_compra, String precio_venta);
    abstract void actualizar_parametros(String id, String articulo, String marca, String precio_compra, String precio_venta);
    abstract public void eliminar(String id);
    abstract public void eliminarTodo();
    abstract public Cursor cargarCursor();
    abstract public Boolean comprobarRegistro(String id);


    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public dbHelper getHelper() {
        return helper;
    }

    public void setHelper(dbHelper helper) {
        this.helper = helper;
    }
}
