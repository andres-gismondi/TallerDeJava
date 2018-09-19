package model;

public class Cotizacion {

    private String marca;
    private String modelo;
    private String añoVehiculo;
    private String usoEnKilometros;
    private String dueño;
    private String sexo;
    private String nacimiento;
    private String telefono;
    private String email;
    private String tipoCobertura;

    public void calcularCotizacion(){

    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(String tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAñoVehiculo() {
        return añoVehiculo;
    }

    public void setAñoVehiculo(String añoVehiculo) {
        this.añoVehiculo = añoVehiculo;
    }

    public String getUsoEnKilometros() {
        return usoEnKilometros;
    }

    public void setUsoEnKilometros(String usoEnKilometros) {
        this.usoEnKilometros = usoEnKilometros;
    }
}
