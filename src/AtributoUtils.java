import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AtributoUtils {

    public static void atributosAlterados(Object oldObj, Object newObj, List<String> fields, String path){
        Class<?> OldClasse = oldObj.getClass();
        Class<?> newClasse = newObj.getClass();

        while (OldClasse != null) {
            Field[] oldAtributos = OldClasse.getDeclaredFields();
            Field[] newAtributos = newClasse.getDeclaredFields();

            if(!OldClasse.getName().startsWith("java.lang.Object")){

                Iterator<Field> oldIterator = Arrays.stream(oldAtributos).iterator();
                Iterator<Field> newIterator = Arrays.stream(newAtributos).iterator();

                while (oldIterator.hasNext() && newIterator.hasNext()){

                    Field oldAtt = oldIterator.next();
                    Field newAtt = newIterator.next();

                    oldAtt.setAccessible(true);
                    newAtt.setAccessible(true);

                    try {
                        Object oldValue = oldAtt.get(oldObj);
                        Object newValue = newAtt.get(newObj);

                        if(!oldAtt.getType().isPrimitive() && !oldAtt.getType().getName().startsWith("java.")){
                            atributosAlterados(oldValue, newValue, fields, oldAtt.getName()+".");
                        }else {
                            if(!oldValue.equals(newValue)){
                                fields.add(path+oldAtt.getName());
                            }
                        }
                    } catch (IllegalAccessException e) {
                        System.out.println("Erro ao acessar o campo " + oldAtt.getName());
                    }
                }
            }
            OldClasse = OldClasse.getSuperclass();
        }
    }

    public static void setValor(Object objectMain, String fullPath, Object value) {
        String[] pathSplit = fullPath.split("\\.");

        Object objectTarget = objectMain;
        Field field;

        for (String path : pathSplit) {
            try {
                field = getField(objectTarget.getClass(), path);
                field.setAccessible(true);

                if(!field.get(objectTarget).getClass().getCanonicalName().startsWith("java.")) {
                    objectTarget = field.get(objectTarget);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return;
            }
        }

        try {
            field = getField(objectTarget.getClass(), pathSplit[pathSplit.length - 1]);
            field.setAccessible(true);
            field.set(objectTarget, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException(fieldName);
    }

    public static Object obterValorCampo(Object objectMain, String fullPath) {
        String[] pathSplit = fullPath.split("\\.");

        Object objectTarget = objectMain;
        for (String path : pathSplit) {
            try {
                Field field = getField(objectTarget.getClass(), path);
                field.setAccessible(true);
                objectTarget = field.get(objectTarget);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                objectTarget = null;
            }
        }
        return objectTarget;
    }

//    private static Object obterValorCampo(Object objeto, String caminho, List<Field> fieldsList) throws NoSuchFieldException, IllegalAccessException {
//        String[] partesCaminho = caminho.split("\\.");
//
//        Object valorAtual = objeto;
//        for (String parte : partesCaminho) {
//            Field campo = getField(valorAtual.getClass(), parte);
//            campo.setAccessible(true);
//            valorAtual = campo.get(valorAtual);
//            System.out.println("--------------" + campo.getName());
//
//            System.out.println(valorAtual.getClass().getCanonicalName());
//
//            if(valorAtual.getClass().getCanonicalName().startsWith("java.")){
//                fieldsList.add(campo);
//            }
//
////            if(campo.get(objeto).getClass().getCanonicalName().startsWith("java.")){
////                fieldsList.add(campo);
////            }
//        }
//
//        return valorAtual;
//    }

    public static <T> boolean compare(Class<T> obj1, Class<T> obj2){
        return obj1.equals(obj2);
    }

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

    public static void atributosAlterados(Object oldObj, Object newObj, List<Field> fields){
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
                            atributosAlterados(oldValue, newValue, fields);
                        }else {
                            if(!oldValue.equals(newValue)){
                                fields.add(oldAtt);
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
