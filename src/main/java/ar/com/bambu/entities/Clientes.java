package ar.com.bambu.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Clientes {

    private Short status;
    @Id
    private Integer codCliente;
    private String nombre;
    private String domicilio;
    private Short condIva;
    private String ingBrutos;
    private String CUIT;
    private String mail;
    private String localidad;
    private char empleado;
    @Transient
    private String condIvaString;


    public char getEmpleado() {
        return empleado;
    }

    public void setEmpleado(char empleado) {
        this.empleado = empleado;
    }

    public Clientes(Clientes cl, String condIva) {
        this.status = cl.status;
        this.codCliente = cl.codCliente;
        this.nombre = cl.nombre;
        this.domicilio = cl.domicilio;
        this.condIva = cl.condIva;
        this.ingBrutos = cl.ingBrutos;
        this.CUIT = cl.CUIT;
        this.mail = cl.mail;
        this.localidad = cl.localidad;
        this.condIvaString = condIva;
        this.empleado = cl.empleado;
        if (this.empleado == 'M')
            this.condIvaString = "Responsable Monotributo";

    }

    public Clientes() {
    }

    public Clientes(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getCondIvaString() {
        return condIvaString;
    }

    public void setCondIvaString(String condIvaString) {
        this.condIvaString = condIvaString;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Short getCondIva() {
        return condIva;
    }

    public void setCondIva(Short condIva) {
        this.condIva = condIva;
    }

    public String getIngBrutos() {
        return ingBrutos;
    }

    public void setIngBrutos(String ingBrutos) {
        this.ingBrutos = ingBrutos;
    }

    public String getCUIT() {
        return String.valueOf(CUIT).trim();

    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
