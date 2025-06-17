public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Shoes", "Fashion"),
                new Product(2, "Laptop", "Electronics"),
                new Product(3, "Watch", "Accessories"),
                new Product(4, "Phone", "Electronics"),
                new Product(5, "T-Shirt", "Fashion")
        };

        Product found1 = SearchConsole.LinearSearchProduct(products, "Watch");
        System.out.println(found1 != null ? found1 : "Product not found");

        System.out.println("Binary Search (sorted) for implementation purpose");
        SearchConsole.sortProducts(products);
        Product found2 = SearchConsole.BinarySearchProduct(products, "Watch");
        System.out.println(found2 != null ? found2 : "Product not found");

        //3 - Watch (Accessories)
        //Binary Search (sorted):
        //3 - Watch (Accessories)
    }
}