import java.time.LocalDate;
import java.util.TreeSet;

public final class Pack extends Product{
    private TreeSet<Product> ids;
    private int percentageDiscount;

    public Pack(int idProduct, String name, int salePrice, int stock, LocalDate initialCatalogDate, LocalDate endCatalogDate, TreeSet<Product> ids, int percentageDiscount) {
        super(idProduct, name, salePrice, stock, initialCatalogDate, endCatalogDate);
        this.ids = ids;
        this.percentageDiscount = percentageDiscount;
    }

    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }
    public void addProduct(Product p){
        this.ids.add(p);
    }
    public void delProduct(Product p){
        this.ids.remove(p);
    }

    @Override
    public String toString() {
        return super.toString()+"Pack [Productos del pack=" + ids + ", Descuento del pack=" + percentageDiscount + "]";
    }
    @Override
    public boolean equals(Object obj) {
        Pack pack = (Pack)obj;

        return this.ids.equals(pack.ids);
    }

}
