package model.domain;

public class cargoM {

    private int cod_Cargo;
    private String nome_Cargo;

    public cargoM(int cod_Cargo, String nome_Cargo) {
        this.cod_Cargo = cod_Cargo;
        this.nome_Cargo = nome_Cargo;
    }

    public cargoM(String nome_Cargo) {
        this.nome_Cargo = nome_Cargo;
    }

    public cargoM() {
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

    public String toString() {
        return this.nome_Cargo;
    }

}
