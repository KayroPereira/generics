import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Temp {

    public static void findModifiedFields(Object oldObj, Object newObj, List<Field> modifiedFields) {
        findModifiedFieldsRecursive(oldObj, newObj, "", modifiedFields);
//        findModifiedFieldsRecursive(oldObj, newObj,  modifiedFields);
    }

        private static void findModifiedFieldsRecursive(Object oldObj, Object newObj, String fieldPath, List<Field> modifiedFields) {
        Class<?> oldClass = oldObj.getClass();
        Class<?> newClass = newObj.getClass();

        while (oldClass != null) {
            Field[] oldFields = oldClass.getDeclaredFields();
            Field[] newFields = newClass.getDeclaredFields();

            for (int i = 0; i < oldFields.length && i < newFields.length; i++) {
                Field oldField = oldFields[i];
                Field newField = newFields[i];

                oldField.setAccessible(true);
                newField.setAccessible(true);

                String currentFieldPath = fieldPath.isEmpty() ? oldField.getName() : fieldPath + "." + oldField.getName();

                try {
                    Object oldValue = oldField.get(oldObj);
                    Object newValue = newField.get(newObj);

                    if (!Objects.equals(oldValue, newValue)) {
                        modifiedFields.add(oldField);
                    }

                    if (!oldField.getType().isPrimitive() && !oldField.getType().getName().startsWith("java.")) {
                        findModifiedFieldsRecursive(oldValue, newValue, currentFieldPath, modifiedFields);
                    }

                } catch (IllegalAccessException e) {
                    System.out.println("Erro ao acessar o campo " + oldField.getName());
                }
            }

            oldClass = oldClass.getSuperclass();
        }
    }
}
