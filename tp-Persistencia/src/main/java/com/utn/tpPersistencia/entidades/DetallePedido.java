package com.utn.tpPersistencia.entidades;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
public class DetallePedido extends BaseEntidad{

    private int cantidad;
    private double subtotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto-id")
    private Producto producto;
}
