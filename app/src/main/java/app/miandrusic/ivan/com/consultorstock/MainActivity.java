package app.miandrusic.ivan.com.consultorstock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import db.dbManagerProducts;

public class MainActivity extends AppCompatActivity {

    protected static dbManagerProducts managerProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managerProducts = new dbManagerProducts(this);
        setContentView(R.layout.activity_main);
    }

    public void onClickConsultar(View v){
        Intent i = new Intent(this, ConsultarAct.class);
        startActivity(i);

        }

    public void onClickAgregar(View v){
        Intent i = new Intent(this, AgregarActivity.class);
        startActivity(i);
    }

    }


