package com.project.libraryManagement.models.core.shelves;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.JoinColumn;

@Entity
@Getter
@Setter
public class Shelves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String label;

    @OneToMany()
    @JoinColumn(referencedColumnName = "id", name = "shelves_id")
    private List<Placement> placements;
}
