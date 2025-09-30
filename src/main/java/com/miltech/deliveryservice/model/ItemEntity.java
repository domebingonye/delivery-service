package com.miltech.deliveryservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "ITEM")
public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "BOX_CODE")
    private String boxCode;

    @Version
    @Column(name = "VERSION")
    private Integer version;
}
