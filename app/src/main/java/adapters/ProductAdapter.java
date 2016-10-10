package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.miandrusic.ivan.com.consultorstock.ItemProdListener;
import app.miandrusic.ivan.com.consultorstock.R;
import model.Product;

/**
 * Created by Ivan on 5/10/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private final Context mainContext;
    private final List<Product> items;
    protected ArrayList<Product> checkedProd = new ArrayList<>();


    public ProductAdapter(Context mainContext, List<Product> items) {
        this.mainContext = mainContext;
        this.items = items;
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView articulo;
        protected TextView marca;
        protected TextView precio_compra;
        protected TextView precio_venta;
        protected ItemProdListener itemProdListener;
        protected CheckBox ckb;


        public ProductViewHolder(View v){
            super(v);
            this.articulo = (TextView) v.findViewById(R.id.txtArticulo);
            this.marca = (TextView) v.findViewById(R.id.txtMarca);
            this.precio_compra = (TextView) v.findViewById(R.id.txtPrecioCompra);
            this.precio_venta = (TextView) v.findViewById(R.id.txtPrecioVenta);
            this.ckb = (CheckBox) v.findViewById(R.id.cbProd);
            ckb.setOnClickListener(this);

        }

        public void setItemProdListener(ItemProdListener ic){
            this.itemProdListener = ic;
        }

        @Override
        public void onClick(View v) {
            this.itemProdListener.onItemClick(v, getAdapterPosition());
        }
    }

    /*Se Crea el Item o Tarjeta*/
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);


        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        Product item = items.get(position);
        holder.itemView.setTag(item);
        holder.articulo.setText("Art: " +item.getArticulo());
        holder.marca.setText("Marca: " +item.getMarca());
        holder.precio_compra.setText("Precio Compra: " +item.getPrecio_compra());
        holder.precio_venta.setText("Precio Venta: " +item.getPrecio_venta());
        holder.setItemProdListener(new ItemProdListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox ckb = (CheckBox) v;

                //CKB IS CHECK OR NOT?
                if(ckb.isChecked()){
                checkedProd.add(items.get(position));
                }else if(!(ckb.isChecked())){
                    checkedProd.remove(items.get(position));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public ArrayList<Product> getCheckedProd() {
        return checkedProd;
    }

    public void setCheckedProd(ArrayList<Product> checkedProd) {
        this.checkedProd = checkedProd;
    }
}
