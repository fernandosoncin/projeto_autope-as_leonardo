package model.domain;

public class Cargo {

    private int cod_Cargo;
    private String nome_Cargo;
    
    public Cargo() {
        this.cod_Cargo = 0;
    }

    public int getCod_Cargo() {
        return cod_Cargo;
    }

    public void setCod_Cargo(int cod_Cargo) {
        this.cod_Cargo = cod_Cargo;
    }

    public String getNome_Cargo() {
        return nome_Cargo;
    }

    public void setNome_Cargo(String nome_Cargo) {
        this.nome_Cargo = nome_Cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cargo) {
            Cargo c = (Cargo) o;
            if (c.getCod_Cargo()== this.getCod_Cargo()) {
                return true;
            }
        }
        return false;
    }

}
