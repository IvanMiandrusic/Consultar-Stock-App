package app.miandrusic.ivan.com.consultorstock;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.ProductAdapter;
import db.dbManagerProducts;
import model.Product;

public class ConsultarAct extends Activity implements View.OnClickListener {

    private static final int ID = 1;

    private Button btnBuscar, btnModif, btnEliminar;
    private RecyclerView recycler;

    private ProductAdapter prodAdpt;
    private RecyclerView.LayoutManager lManager;
    private List<Product> listItemsProd;

    protected StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        addViews();
        inicializarRecicler();

    }

    private void addViews() {
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this);

        btnModif = (Button) findViewById(R.id.btnModif);
        btnModif.setOnClickListener(this);

        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(this);
    }

    private void buscarProd(){
        EditText edt = (EditText) findViewById(R.id.edtBuscar_Cons);
        RadioButton rbM = (RadioButton) findViewById(R.id.rbmarca);
        RadioButton rbA = (RadioButton) findViewById(R.id.rbArtic);
        String dato = edt.getText().toString();
        if(!(dato.isEmpty())){
            if(rbM.isChecked()){
                List<Product> listP = MainActivity.managerProducts.buscarItem(dato, "marca");
                cargarRecycler(listP);
            }else if(rbA.isChecked()){
                List<Product> listP = MainActivity.managerProducts.buscarItem(dato, "articulo");
                cargarRecycler(listP);
            }else{
                Toast.makeText(this, "Debe seleccionar el tipo de busqueda", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "El campo de busqueda esta vacio!", Toast.LENGTH_SHORT).show();
        }
    }

    private void inicializarRecicler() {
        //Obtener Lista de Productos
        listItemsProd = MainActivity.managerProducts.getProductosList();

        //Obtener Recycler
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        //Usar un administrador para el LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        //Crear nuevo adaptador
        prodAdpt = new ProductAdapter(this, listItemsProd);
        recycler.setAdapter(prodAdpt);
        recycler.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnModif:
                if(prodAdpt.getCheckedProd().size()>1){
                    Toast.makeText(this, "Solo se puede seleccionar 1 producto para ser modificado", Toast.LENGTH_SHORT).show();
                }else if(prodAdpt.getCheckedProd().size()>0){

                    Product prodAMod = prodAdpt.getCheckedProd().get(0);
                    Intent i = new Intent(this, ModifActivity.class);
                    cargarIntent(i, prodAMod);

                }else{
                    Toast.makeText(this, "Seleccione un elemento para modificar", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnEliminar:
                for(Product p : prodAdpt.getCheckedProd())
                {
                    MainActivity.managerProducts.eliminar(p.getId());
                    listItemsProd = MainActivity.managerProducts.getProductosList();
                    cargarRecycler(listItemsProd);
                    Toast.makeText(this, "Los Productos han sido eliminados exitosamente", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnBuscar:
                buscarProd();
                break;
        }
    }

    public void cargarRecycler(List<Product> list){
        prodAdpt = new ProductAdapter(this, list);
        recycler.setAdapter(prodAdpt);
    }

    private void cargarIntent(Intent i, Product p){
        i.putExtra("id", p.getId());
        i.putExtra("art", p.getArticulo());
        i.putExtra("marca", p.getMarca());
        i.putExtra("prec_compra", p.getPrecio_compra());
        i.putExtra("prec_venta", p.getPrecio_venta());
        startActivityForResult(i, ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == ID){
            listItemsProd = MainActivity.managerProducts.getProductosList();
            this.cargarRecycler(listItemsProd);
        }
    }
}
