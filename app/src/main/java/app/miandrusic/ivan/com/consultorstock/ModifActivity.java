package app.miandrusic.ivan.com.consultorstock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModifActivity extends Activity implements View.OnClickListener{
    private String id;
    private String art;
    private String marca;
    private String prec_compra;
    private String prec_venta;
    private EditText a;
    private EditText m;
    private EditText pv;
    private EditText pc;

    private static final boolean MODIFICADO = true;
    private static final boolean NO_MODIFICADO = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif);
        Intent intent = getIntent();
        Bundle datos = intent.getExtras();
        getDatosExtras(datos);
        setCampos();
    }

    private void getDatosExtras(Bundle datos){
            this.id = datos.getString("id");
            this.art = datos.getString("art");
            this.marca = datos.getString("marca");
            this.prec_compra = datos.getString("prec_compra");
            this.prec_venta = datos.getString("prec_venta");

    }

    private void setCampos(){
        this.a = (EditText) findViewById(R.id.edArt_mod);
        this.m = (EditText) findViewById(R.id.edMarca_mod);
        this.pc = (EditText) findViewById(R.id.edPrec_Compra_mod);
        this.pv = (EditText) findViewById(R.id.edPrecio_venta_mod);

        a.setText(art);
        m.setText(marca);
        pc.setText(prec_compra);
        pv.setText(prec_venta);
    }

    public void guardarModif(){
        MainActivity.managerProducts.actualizar_parametros(this.getId(), a.getText().toString(), m.getText().toString(),
                pc.getText().toString(), pv.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuardar_mod:
                //TODO: Chequear si hay modif o no..
                //TODO: hacer mensaje de validacion..
                this.guardarModif();
                generarRespuesta(MODIFICADO);
                Toast.makeText(this,"El producto se ha actualizado correctamente",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btnCancelar_mod:
                break;
        }
    }

    private void generarRespuesta(Boolean rsp){
        if(rsp){
            setResult(RESULT_OK);
        }else{
            setResult(RESULT_CANCELED);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPrec_compra() {
        return prec_compra;
    }

    public void setPrec_compra(String prec_compra) {
        this.prec_compra = prec_compra;
    }

    public String getPrec_venta() {
        return prec_venta;
    }

    public void setPrec_venta(String prec_venta) {
        this.prec_venta = prec_venta;
    }

    public EditText getA() {
        return a;
    }

    public void setA(EditText a) {
        this.a = a;
    }

    public EditText getM() {
        return m;
    }

    public void setM(EditText m) {
        this.m = m;
    }

    public EditText getPv() {
        return pv;
    }

    public void setPv(EditText pv) {
        this.pv = pv;
    }

    public EditText getPc() {
        return pc;
    }

    public void setPc(EditText pc) {
        this.pc = pc;
    }
}
