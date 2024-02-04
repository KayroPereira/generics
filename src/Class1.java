import java.util.Date;

public class Class1 {
    private String nome;
    private Long idade;
    private Date dataNascimento;

    private Class2 class2 = new Class2();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Class2 getClass2() {
        return class2;
    }

    public void setClass2(Class2 class2) {
        this.class2 = class2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Class1 class1)) return false;

        if (getNome() != null ? !getNome().equals(class1.getNome()) : class1.getNome() != null) return false;
        if (getIdade() != null ? !getIdade().equals(class1.getIdade()) : class1.getIdade() != null) return false;
        if (getDataNascimento() != null ? !getDataNascimento().equals(class1.getDataNascimento()) : class1.getDataNascimento() != null)
            return false;
        return getClass2() != null ? getClass2().equals(class1.getClass2()) : class1.getClass2() == null;
    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (getIdade() != null ? getIdade().hashCode() : 0);
        result = 31 * result + (getDataNascimento() != null ? getDataNascimento().hashCode() : 0);
        result = 31 * result + (getClass2() != null ? getClass2().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Class1{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", dataNascimento=" + dataNascimento +
                ", class2=" + class2 +
                '}';
    }
}