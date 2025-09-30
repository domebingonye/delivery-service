package com.miltech.deliveryservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "BOX")
public class BoxEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "TEXT_REFERENCE")
    private String txtReference;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "BATTERY_LEVEL")
    private Double batteryLevel;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "BOX_CODE")
    private String boxCode;
}
