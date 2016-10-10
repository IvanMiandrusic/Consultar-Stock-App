package app.miandrusic.ivan.com.consultorstock;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import adapters.ProductAdapter;
import db.dbManagerProducts;
import model.Product;

public class ConsultarAct extends Activity implements View.OnClickListener {

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
                    /*TODO: TIRAR UN EXTRA A LA ACTIVITY DE MODIFICACION*/
                    Toast.makeText(this, "Modificacion en Construccion :)", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Seleccione un elemento para modificar", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnEliminar: /*TODO:*/
                for(Product p : prodAdpt.getCheckedProd())
                {
                    MainActivity.managerProducts.eliminar(p.getId());
                    cargarRecycler();
                    Toast.makeText(this, "Los Productos han sido eliminados exitosamente", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnBuscar:
                cargarRecycler();
                break;
        }
    }

    private void cargarRecycler(){
        listItemsProd = MainActivity.managerProducts.getProductosList();
        prodAdpt = new ProductAdapter(this, listItemsProd);
        recycler.setAdapter(prodAdpt);
    }
}
