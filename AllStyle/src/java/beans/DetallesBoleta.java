/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author sebas
 */
public class DetallesBoleta {
    String IDBoleta;
    String IDProducto;
    int Cantidad;
    double Descuento;
    double Importe;

    public DetallesBoleta() {
    }

    public DetallesBoleta(String IDBoleta, String IDProducto, int Cantidad, double Descuento, double Importe) {
        this.IDBoleta = IDBoleta;
        this.IDProducto = IDProducto;
        this.Cantidad = Cantidad;
        this.Descuento = Descuento;
        this.Importe = Importe;
    }

    public String getIDBoleta() {
        return IDBoleta;
    }

    public void setIDBoleta(String IDBoleta) {
        this.IDBoleta = IDBoleta;
    }

    public String getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(String IDProducto) {
        this.IDProducto = IDProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public double getImporte() {
        return Importe;
    }

    public void setImporte(double Importe) {
        this.Importe = Importe;
    }
    
    
}
