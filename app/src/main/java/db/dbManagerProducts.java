package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Product;

/**
 * Created by Ivan on 3/10/2016.
 */
public class dbManagerProducts extends dbManager{

    private static final String NOMBRE_TABLA= "products";

    private static final String PR_ID="_id";
    private static final String PR_ART="articulo";
    private static final String PR_MARCA="marca";
    private static final String PR_PRECIO_COMPRA="precio_compra";
    private static  final String PR_PRECIO_VENTA="precio_venta";
    private static Context ctx = null;


    public dbManagerProducts(Context ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    public static final String CREATE_TABLE="CREATE TABLE "+NOMBRE_TABLA+"("
            + PR_ID+" integer PRIMARY KEY,"
            + PR_ART+" integer NOT NULL,"
            + PR_MARCA+" varchar(10) NULL,"
            + PR_PRECIO_COMPRA+" decimal(4,2) NULL,"
            + PR_PRECIO_VENTA+" decimal(5,2) NOT NULL"
            + ");";

    private ContentValues generarContentValues(String id, String articulo, String marca, String precio_compra, String precio_venta){
        ContentValues values = new ContentValues();
        values.put(PR_ID, id);
        values.put(PR_ART, articulo);
        values.put(PR_MARCA, marca);
        values.put(PR_PRECIO_COMPRA, precio_compra);
        values.put(PR_PRECIO_VENTA, precio_venta);

        return values;
    }


    @Override
    public void cerrar(){
        super.getDb().close();
    }

    @Override
    public void insertar_parametros(String id, String articulo, String marca, String precio_compra, String precio_venta) {
        Log.d("Insert producto", super.getDb().insert(NOMBRE_TABLA, null, this.generarContentValues(id, articulo, marca, precio_compra, precio_venta))+"");
    }

    @Override
    public void actualizar_parametros(String id, String articulo, String marca, String precio_compra, String precio_venta) {

        String[] args = new String[]{id};
        Log.d("Actualizar Producto", super.getDb().update(NOMBRE_TABLA, this.generarContentValues(id, articulo,marca,precio_compra, precio_venta), "_id=?",args)+"");
    }

    @Override
    public void eliminar(String id) {
        super.getDb().delete(NOMBRE_TABLA,PR_ID+" =?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {
        super.getDb().execSQL("DELETE FROM "+NOMBRE_TABLA+";");
        Log.d("Eliminar Tabla "+NOMBRE_TABLA, "Datos Borrados");
    }

    @Override
    public Cursor cargarCursor() {
        String[] columnas = new String[]{PR_ID, PR_ART, PR_MARCA, PR_PRECIO_COMPRA, PR_PRECIO_VENTA};

        return super.getDb().query(NOMBRE_TABLA,columnas,null,null,null,null,null,null);
    }

    @Override
    public Boolean comprobarRegistro(String id) {
        boolean esta = false;
        Cursor resultSet = super.getDb().rawQuery("SELECT * FROM "+NOMBRE_TABLA+" WHERE "+PR_ID+" = "+id, null);

        if(resultSet.getCount() <=0){
            esta=false;
        }else{
            esta=true;
        }
        return esta;
    }

    public List<Product> buscarItem(String dato, String campo){
        Cursor c = super.getDb().rawQuery("SELECT * FROM "+NOMBRE_TABLA+" WHERE "+campo+" = '"+dato+"'", null);
        return crearListaProdDeCursor(c);
    }



    public List<Product> getProductosList(){
        Cursor c = this.cargarCursor();
        return crearListaProdDeCursor(c);

    }

    public List<Product> crearListaProdDeCursor(Cursor c){
        List<Product> l = new ArrayList<>();
        while(c.moveToNext()){
            Product prod = new Product();

            prod.setId(c.getString(0));
            prod.setArticulo(c.getString(1));
            prod.setMarca(c.getString(2));
            prod.setPrecio_compra(c.getString(3));
            prod.setPrecio_venta(c.getString(4));

            l.add(prod);
        }
        return l;
    }
}
