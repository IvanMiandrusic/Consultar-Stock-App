package db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ivan on 3/10/2016.
 */
public class dbHelper extends SQLiteOpenHelper{

    private static final String DB_NOMBRE = "stock.sqlite";
    private static final int DB_VERSION = 1;

    public dbHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbManagerProducts.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IS EXISTS "+DB_NOMBRE);
        this.onCreate(db);
    }
}
