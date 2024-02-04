import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Class1 c1 = new Class1();
        Class1 c2 = new Class1();
        Class1 c3 = new Class1();
        Class1 c4 = new Class1();
        Class1 c5 = new Class1();

        c1.setNome("Teste 1");
        c1.setIdade(1L);
        c1.setDataNascimento(new Date());
        c1.getClass2().setNomeClasse2("Teste 1_1");
        c1.getClass2().setIdadeClasse2(11L);
        c1.getClass2().setDataNascimentoClass2(new Date());

        c2.setNome("Teste 1");
        c2.setIdade(1L);
        c2.setDataNascimento(new Date());

        c3.setNome(null);
        c3.setIdade(null);
        c3.setDataNascimento(null);

        c4.setNome(null);
        c4.setIdade(null);
        c4.setDataNascimento(null);

        c5.setNome("Teste 1");
        c5.setIdade(5L);
        c5.setDataNascimento(null);
        c5.getClass2().setNomeClasse2("Teste 1_1");
        c5.getClass2().setIdadeClasse2(55L);
        c5.getClass2().setDataNascimentoClass2(new Date());

        System.out.println("\n\nc1 = c2: " + c1.equals(c2) + " c1 = c3: " + c1.equals(c3) + " c2 = c3: " + c2.equals(c3) + " c3 = c4: " + c3.equals(c4) + " c1 = c5: " + c1.equals(c5));

        List<Field> fields = new ArrayList<>();

//        String campoDesejado = "dataNascimento";
        String campoDesejado = "class2.nomeClasse2";




        //funciona em parte

//        try {
//            // Obter o valor do campo usando o caminho desejado
//            Object valorCampo = obterValorCampo(c1, campoDesejado);
//
//            // Imprimir o valor do campo
//            System.out.println("Valor do Campo: " + valorCampo);
//
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            System.out.println("Erro ao acessar o campo desejado: " + campoDesejado);
//        }



//        try {
//            // Obtendo a classe da instância
//            Class<?> classe = c1.getClass();
//
//            // Obtendo o campo usando o caminho desejado
//            Field campo = obterCampoPorCaminho(classe, campoDesejado);
//
//            campo.setAccessible(true);
//            // Agora você tem o objeto Field e pode fazer o que precisar com ele
//            System.out.println("Campo encontrado: " + campo.getName() + " -> valor: " + campo.get(c1));
//        } catch (IllegalAccessException | NoSuchFieldException e) {
//            System.out.println("Campo não encontrado: " + campoDesejado);
//        }

//        AtributoUtils.atributosAlterados(c1, c5, fields);

//        DebuggerUtils.printObj(c1);
//
//        System.out.println("-------------------");
//
////        DebuggerUtils.printObj(fields, "");
//
//        Temp.findModifiedFields(c1, c5, fields);
//        System.out.println("Modified Fields:");
//        for (Field field : fields) {
//            try {
//                Object oldValue = field.get(c1);
//                Object newValue = field.get(c5);
//                System.out.println(field.getName() + ":");
//                System.out.println("  Old Value: " + oldValue);
//                System.out.println("  New Value: " + newValue);
//                System.out.println(field.getName() + " -> " + field.get(c1));
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }

//        AtributoUtils.setAtributos(fields, c1, c5);
//
//        DebuggerUtils.printObj(c1);


//        AtributoUtils.getAtributos(fields, c1);


        //funciona
//        try {
//            // Caminho para acessar o atributo da Class2 a partir de Class1
////            String campoDesejado = "class2.nomeClasse2";
//
//            // Obter o valor do campo usando o caminho desejado
//            Object valorCampo = obterValorCampo(c1, campoDesejado);
//
//            // Imprimir o valor do campo
//            System.out.println("Valor do Campo: " + valorCampo);
//
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            System.out.println("Erro ao acessar o campo desejado: " + e.getMessage());
//        }



//        List<String> atributos = new ArrayList<>();
//
//        List<Field> fields1 = new ArrayList<>();
//
//        getCampos(c1, atributos, "");
//
////        atributos.stream().forEach(att -> {
////            System.out.println(att);
////        });
//
//        atributos.stream().forEach(att -> {
//            try {
//                System.out.println(obterValorCampo(c1, att, fields1));
//
//            } catch (NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        fields1.stream().forEach(f -> {
//
//            String path = !f.getDeclaringClass().getName().equals("Class1") ? f.getDeclaringClass().getName().toLowerCase() + "." : "";
//            path += f.getName();
//            System.out.println("**************: " + obterValorCampo(c1, path));
//
////            System.out.println("*************: " + f.getName());
//
////            //c1.getClass().getDeclaredField(f.getDeclaringClass().getName() + "." + f.getName());
////
////            try {
////                Class<?> classe = f.getDeclaringClass();
////
////                f.setAccessible(true);
////
////                Object o = f.get(c1);
////
////                System.out.println(classe.getName());
////                System.out.println("==============: " + o);
////
////            } catch (IllegalAccessException e) {
////                throw new RuntimeException(e);
////            }
//
//
//        });

        List<String> atributos1 = new ArrayList<>();

        AtributoUtils.atributosAlterados(c1, c5, atributos1, "");

        atributos1.stream().forEach(a -> {
            System.out.println("1- @@@@@@@@@ campo: " + a + " valor: " + AtributoUtils.obterValorCampo(c1, a));
//            System.out.println("2- @@@@@@@@@ campo: " + a + " valor: " + obterValorCampo(c2, a));
//            System.out.println("3- @@@@@@@@@ campo: " + a + " valor: " + obterValorCampo(c3, a));
//            System.out.println("4- @@@@@@@@@ campo: " + a + " valor: " + obterValorCampo(c4, a));
//            System.out.println("5- @@@@@@@@@ campo: " + a + " valor: " + obterValorCampo(c5, a));
        });

        AtributoUtils.setValor(c1, atributos1.get(0), 12L);
        AtributoUtils.setValor(c1, atributos1.get(1), null);
        AtributoUtils.setValor(c1, atributos1.get(2), 23L);

        System.out.println("################: " + c1);
    }



//    public static <T> T instancia(Class<T> classe){
//        try {
//            return classe.getDeclaredConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            e.printStackTrace(); // Trate ou propague a exceção conforme necessário
//            return null;
//        }
//    }


//    public static void getCampos(Object obj, List<String> atributos, String caminho){
//
//        Class<?> c = obj.getClass();
//        Field[] fields = c.getDeclaredFields();
//
//        for(Field f : fields) {
//            try {
//                f.setAccessible(true);
//
//                if(f.get(obj).getClass().getCanonicalName().startsWith("java.")){
//                    atributos.add(caminho + f.getName());
//                }else{
//                    getCampos(f.get(obj), atributos, f.getName() + ".");
//                }
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }






    // Método para obter o valor de um campo usando um objeto Field
    // Método para obter o valor de um campo usando um caminho específico


    // Método para obter um campo usando um caminho específico




    // Método para obter um campo usando um caminho específico
    private static Field obterCampoPorCaminho(Class<?> classe, String caminho) throws NoSuchFieldException {
        String[] partesCaminho = caminho.split("\\.");

        Field campo = null;
        for (String parte : partesCaminho) {
            // Obtendo o campo atual
            campo = classe.getDeclaredField(parte);

            // Mudando a classe para a classe do tipo do campo atual
            classe = campo.getType();
        }

        return campo;
    }



//    private static Field obterCampoPorCaminho(Class<?> classe, String caminho) throws NoSuchFieldException {
//        String[] partesCaminho = caminho.split("\\.");
//
//        Field campo = null;
//        for (String parte : partesCaminho) {
//            // Obtendo o campo atual
//            campo = classe.getDeclaredField(parte);
//
//            // Mudando a classe para a classe do tipo do campo atual
//            classe = campo.getType();
//        }
//
//        return campo;
//    }
}