package com.utn.tpPersistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends BaseEntidad{

    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente-id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente-id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi){

        domicilios.add(domi);
    }

    public void agregarPedido(Pedido ped){

        pedidos.add(ped);
    }


    public void mostrarDomicilios() {
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero());
        }
    }

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + nombre + " " + apellido + ":");
        int i = 1;
        for (Pedido pedido : pedidos) {
            System.out.println("---------------------Pedido " + i + "---------------------");
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("---------------------Detalle---------------------");
            int j = 1;
            for (DetallePedido detalle : pedido.getDetallePedidos()){
                System.out.println("---------- Detalle: " + j);
                System.out.println("Producto: " + detalle.getProducto().getDenominacion());
                System.out.println("Cantidad: " + detalle.getCantidad());
                System.out.println("Subtotal: " + detalle.getSubtotal());
                j++;
            }
            System.out.println("#################################");
            System.out.println("TOTAL: " + pedido.getTotal());
            i++;
        }
    }

}
