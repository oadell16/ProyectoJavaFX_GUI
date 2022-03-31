package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable, Identificable, Comparable <Product>{
    private static final long serialversionUID = 1234567890l;
    private Integer idProduct;
    private String name;
    private int salePrice;
    private int stock;
    private LocalDate initialCatalogDate;
    private LocalDate endCatalogDate;

    public Product(Integer idProduct, String name, int salePrice, int stock, LocalDate initialCatalogDate, LocalDate endCatalogDate) {
        this.idProduct = idProduct;
        this.name = name;
        this.salePrice = salePrice;
        this.stock = stock;
        this.initialCatalogDate = initialCatalogDate;
        this.endCatalogDate = endCatalogDate;
    }

    public int getId() {
        return idProduct;
    }

    public LocalDate getInitialCatalogDate() {
        return initialCatalogDate;
    }

    public void setInitialCatalogDate(LocalDate initialCatalogDate) {
        this.initialCatalogDate = initialCatalogDate;
    }

    public LocalDate getEndCatalogDate() {
        return endCatalogDate;
    }

    public void setEndCatalogDate(LocalDate endCatalogDate) {
        this.endCatalogDate = endCatalogDate;
    }

    public void setIdProduct(int IdProduct) {
        this.idProduct = IdProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void putStock(int addStock) {
        this.stock=this.stock+addStock;
    }

    public void takeStock(int subtractStock) {
        this.stock=this.stock-subtractStock;
    }

    @Override
    public String toString() {
        return "Producto [Id del producto=" + idProduct + ", nombre del producto=" + name + ", Precio del producto=" + salePrice + ", stock del producto=" + stock + ", Fecha inicial catalogo="+initialCatalogDate+", Fecha final catalogo="+endCatalogDate+"]";
    }

    @Override
    public boolean equals(Object obj) {
        Product p = (Product)obj;
        
        return this.name.equals(p.name);
    }

    @Override
    public int compareTo(Product product) {
        return this.idProduct.compareTo(product.idProduct);
    }
}
