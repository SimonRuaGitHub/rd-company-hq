package com.rapid.stock.model.v2;

import lombok.*;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;

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
    @GeneratedValue
    private Long id;
    @NotBlank(message = "rack should have some name")
    private String name;
    private String description;
    @NotBlank(message = "companyId can't be empty")
    private String companyId;

    @ManyToMany(mappedBy = "rack", fetch = FetchType.LAZY)
    private List<ParentProduct> products;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_rack_id", referencedColumnName = "id")
    private List<Rack> childRacks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_rack_id", referencedColumnName = "id")
    private Rack parentRack;

    @Builder
    public Rack(String name, String description, List<ParentProduct> products, List<Rack> childRacks, String companyId) {
        this.name = name;
        this.description = description;
        this.products = products;
        this.childRacks = childRacks;
        this.companyId = companyId;
    }
}
