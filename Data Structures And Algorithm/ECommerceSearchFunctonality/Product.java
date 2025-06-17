public class Product {
    private int productId;
    private String productName;
    private String productCategory;

    public Product(int productId, String productName, String productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    @Override
    public String toString() {
        return productId + " - " + productName + " (" + productCategory + ")";
    }
}
