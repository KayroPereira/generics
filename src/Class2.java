import java.util.Date;

public class Class2 {
    private String nomeClasse2;
    private Long idadeClasse2;
    private Date dataNascimentoClass2;

    public String getNomeClasse2() {
        return nomeClasse2;
    }

    public void setNomeClasse2(String nomeClasse2) {
        this.nomeClasse2 = nomeClasse2;
    }

    public Long getIdadeClasse2() {
        return idadeClasse2;
    }

    public void setIdadeClasse2(Long idadeClasse2) {
        this.idadeClasse2 = idadeClasse2;
    }

    public Date getDataNascimentoClass2() {
        return dataNascimentoClass2;
    }

    public void setDataNascimentoClass2(Date dataNascimentoClass2) {
        this.dataNascimentoClass2 = dataNascimentoClass2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Class2 class2)) return false;

        if (getNomeClasse2() != null ? !getNomeClasse2().equals(class2.getNomeClasse2()) : class2.getNomeClasse2() != null)
            return false;
        if (getIdadeClasse2() != null ? !getIdadeClasse2().equals(class2.getIdadeClasse2()) : class2.getIdadeClasse2() != null)
            return false;
        return getDataNascimentoClass2() != null ? getDataNascimentoClass2().equals(class2.getDataNascimentoClass2()) : class2.getDataNascimentoClass2() == null;
    }

    @Override
    public int hashCode() {
        int result = getNomeClasse2() != null ? getNomeClasse2().hashCode() : 0;
        result = 31 * result + (getIdadeClasse2() != null ? getIdadeClasse2().hashCode() : 0);
        result = 31 * result + (getDataNascimentoClass2() != null ? getDataNascimentoClass2().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Class2{" +
                "nomeClasse2='" + nomeClasse2 + '\'' +
                ", idadeClasse2=" + idadeClasse2 +
                ", dataNascimentoClass2=" + dataNascimentoClass2 +
                '}';
    }
}