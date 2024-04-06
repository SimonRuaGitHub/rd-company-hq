package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "RACKS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Profile("rational-db")
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "rack should have some name")
    private String name;

    private String description;

    @NotBlank(message = "companyId can't be empty")
    @Column(name = "company_id")
    private String companyId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTS_RACKS",
            joinColumns = {
                    @JoinColumn(name = "rack_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id")
            }
    )
    @JsonBackReference
    private List<ParentProduct> products;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_rack_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Rack> childRacks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_rack_id", referencedColumnName = "id")
    @JsonBackReference
    private Rack parentRack;

    @Builder
    public Rack(String name, String description, String companyId, List<ParentProduct> products, List<Rack> childRacks, Rack parentRack) {
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.products = products;
        this.childRacks = childRacks;
        this.parentRack = parentRack;
    }
}
