import java.lang.reflect.*;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.lang.String");
        Method[] methods = clazz.getDeclaredMethods();

        for (Method m : methods) {
            System.out.println(m.getName() + " - " + m.getParameterCount() + " params");
        }

        Method lengthMethod = clazz.getMethod("length");
        String str = "Hello";
        System.out.println("Length: " + lengthMethod.invoke(str));
    }
}
