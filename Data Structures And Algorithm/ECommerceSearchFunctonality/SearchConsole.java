import java.util.Arrays;
import java.util.Comparator;

public class SearchConsole {


//    o(n)
    public static Product LinearSearchProduct(Product[] products, String name) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

//    O(nLogn)
    public static Product BinarySearchProduct(Product[] products, String name) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compareIndex = products[mid].getProductName().compareToIgnoreCase(name);
            if (compareIndex == 0) {
                return products[mid];
            } else if (compareIndex < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void sortProducts(Product[] products) {
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
    }
}
