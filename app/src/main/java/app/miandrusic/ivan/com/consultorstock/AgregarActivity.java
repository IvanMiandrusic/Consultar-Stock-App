package app.miandrusic.ivan.com.consultorstock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarActivity extends Activity implements View.OnClickListener{

    private Button boton_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        addViews();

    }

    @Override
    public void onClick(View v){
        String art = ((TextView) findViewById(R.id.edtArticulo)).getText().toString();
        String marca = ((TextView) findViewById(R.id.edtMarca)).getText().toString();
        String precio_compr = ((TextView) findViewById(R.id.edtPrec_Comp)).getText().toString();
        String precio_venta = ((TextView) findViewById(R.id.edtPrecio_Venta)).getText().toString();

            switch(v.getId()){
                case R.id.btnAgregar:
                    if(!(art.isEmpty()) && !(marca.isEmpty()) && !(precio_compr.isEmpty()) && !(precio_venta.isEmpty())){
                        MainActivity.managerProducts.insertar_parametros(null, art, marca, precio_compr, precio_venta);
                        Toast toast = Toast.makeText(getApplicationContext(), "Se añadio correctamente el producto", Toast.LENGTH_SHORT);
                        limpiarEntradas();
                        mostrarToast(toast);
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Debe completar todos los campos", Toast.LENGTH_SHORT);
                        mostrarToast(toast);
                    }
                    break;
               }
        }

    private void limpiarEntradas(){
        ((TextView) findViewById(R.id.edtArticulo)).setText("");
        ((TextView) findViewById(R.id.edtMarca)).setText("");
        ((TextView) findViewById(R.id.edtPrec_Comp)).setText("");
        ((TextView) findViewById(R.id.edtPrecio_Venta)).setText("");
    }

    private void mostrarToast(Toast t){

        t.show();
    }

    private void addViews(){
        boton_agregar = (Button) findViewById(R.id.btnAgregar);
        boton_agregar.setOnClickListener(this);
    }



    }



