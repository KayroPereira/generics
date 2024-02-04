import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AtributoUtilsV2 {

    public static <T> boolean compare(Class<T> obj1, Class<T> obj2){
        return obj1.equals(obj2);
    }

//    public static List<Field> atributosAlteradosRetornaNovos(Object oldObj, Object newObj){
//        List<Field> fields = new ArrayList<>();
//        atributosAlterados(oldObj, newObj, fields);
//
//        System.out.println("\n\nFields.size: " + fields.size());
//
//        return fields;
//    }

    public static void getAtributos(List<Field> fields, Object obj){
        for(Field f: fields){
            f.setAccessible(true);

            System.out.println("\n\n------------------- getAtributos ---------------------------\n");

            try {
                Object o = f.get(obj);
                System.out.println("oldValue: " + f.getName() + " = " + o);
            } catch (IllegalAccessException e) {
                System.out.println("Erro ao acessar o campo " + f.getName());
            }
        }
    }

    public static void setAtributos(List<Field> fields, Object oldObj, Object newObj){

        System.out.println("\n\n------------------- setAtributos ---------------------------\n");

        for(Field f: fields){
            f.setAccessible(true);



            try {
                Object o = f.get(newObj);
                f.set(oldObj, o);
            } catch (IllegalAccessException e) {
                System.out.println("Erro ao acessar o campo " + f.getName());
            }
        }
    }

    public static void getCampos(Object obj){
        System.out.println(obj.getClass().getName());
    }

    public static void atributosAlterados(Object oldObj, Object newObj, List<String> fields, String path){
        Class<?> OldClasse = oldObj.getClass();
        Class<?> newClasse = newObj.getClass();

        while (OldClasse != null) {
            Field[] oldAtributos = OldClasse.getDeclaredFields();
            Field[] newAtributos = newClasse.getDeclaredFields();

            if(!OldClasse.getName().startsWith("java.lang.Object")){
                System.out.println("\n\n<------------- printObj - class: " + OldClasse.getCanonicalName() + " ------------------>");

                Iterator<Field> oldIterator = Arrays.stream(oldAtributos).iterator();
                Iterator<Field> newIterator = Arrays.stream(newAtributos).iterator();

                while (oldIterator.hasNext() && newIterator.hasNext()){

                    Field oldAtt = oldIterator.next();
                    Field newAtt = newIterator.next();

                    oldAtt.setAccessible(true);
                    newAtt.setAccessible(true);

//                    String currentFieldPath = fieldPath.isEmpty() ? oldField.getName() : fieldPath + "." + oldField.getName();

                    try {
                        Object oldValue = oldAtt.get(oldObj);
                        Object newValue = newAtt.get(newObj);

                        if(!oldAtt.getType().isPrimitive() && !oldAtt.getType().getName().startsWith("java.")){
                            atributosAlterados(oldValue, newValue, fields, oldAtt.getName()+".");
                        }else {
                            if(!oldValue.equals(newValue)){
                                fields.add(path+oldAtt.getName());
                            }
                            System.out.println("oldValue: " + oldAtt.getName() + " = " + oldValue);
                            System.out.println("newValue: " + newAtt.getName() + " = " + newValue);
                        }
                    } catch (IllegalAccessException e) {
                        System.out.println("Erro ao acessar o campo " + oldAtt.getName());
                    }
                }
            }
            OldClasse = OldClasse.getSuperclass();
        }
    }
}
