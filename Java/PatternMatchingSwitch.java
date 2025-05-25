public class PatternMatchingSwitch {
    public static void printType(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("Integer: " + i);
            case String s -> System.out.println("String: " + s);
            case Double d -> System.out.println("Double: " + d);
            case null -> System.out.println("Null object");
            default -> System.out.println("Unknown type: " + obj);
        }
    }

    public static void main(String[] args) {
        printType(42);
        printType("Java");
        printType(3.14);
        printType(null);
    }
}
