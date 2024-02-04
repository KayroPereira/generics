import java.lang.reflect.Field;
import java.util.List;

public class DebuggerUtils {

    private DebuggerUtils(){}

    public static <T> void printObj(List<T> obj, String text){
        obj.stream().forEach(o -> printObj(o));
    }

    public static void printObj(Object obj){
        Class<?> classe = obj.getClass();

        while (classe != null) {
            Field[] atributos = classe.getDeclaredFields();


            if(!classe.getName().startsWith("java.lang.Object")){
                System.out.println("\n\n<------------- printObj - class: " + classe.getCanonicalName() + " ------------------>");

                for (Field atributo : atributos) {
                    atributo.setAccessible(true);

                    try {
                        Object o = atributo.get(obj);

                        if(!atributo.getType().isPrimitive() && !atributo.getType().getName().startsWith("java.")){
                            printObj(o);
                        }else {
                            System.out.println(atributo.getName() + " = " + o);
                        }
                    } catch (IllegalAccessException e) {
                        System.out.println("Erro ao acessar o campo " + atributo.getName());
                    }
                }
            }
            classe = classe.getSuperclass();
        }
    }
}