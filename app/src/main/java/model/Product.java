package model;

/**
 * Created by Ivan on 4/10/2016.
 */
public class Product {
    private String id;
    private String articulo;
    private String marca;
    private String precio_compra;
    private String precio_venta;

    public Product() {
    }

    public Product(String id, String articulo) {
        this.id = id;
        this.articulo = articulo;
    }

    public Product(String id, String articulo, String marca, String precio_compra, String precio_venta) {
        this.id = id;
        this.articulo = articulo;
        this.marca = marca;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(String precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(String precio_venta) {
        this.precio_venta = precio_venta;
    }
}
