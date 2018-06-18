/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author LN710Q
 */
public class Productos {
    private int codigo;
    private String nombre;
    private String tipo;
    private int cantidad;
    private int precio;
    private boolean disponibiliodad;
   
    public Productos(){}

    public Productos(int codigo, String nombre, String tipo, int cantidad, int precio, boolean disponibiliodad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibiliodad = disponibiliodad;
    }
     public Productos(String nombre, String tipo, int cantidad, int precio, boolean disponibiliodad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibiliodad = disponibiliodad;
    }
     public Productos(String tipo, int cantidad, int precio, boolean disponibiliodad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibiliodad = disponibiliodad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isDisponibiliodad() {
        return disponibiliodad;
    }

    public void setDisponibiliodad(boolean disponibiliodad) {
        this.disponibiliodad = disponibiliodad;
    }
    
    
    
    
}
