package br.com.fiap.epictaskg.workspace;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "workspaces", uniqueConstraints = {
        @UniqueConstraint(name = "uk_rooms_name", columnNames = "name")
})
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private Integer capacity;
    private String location;

    @Column(nullable=false)
    private Boolean active = true;

    public Workspace() {}

    public Workspace(String name, Integer capacity, String location) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.active = true;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getCapacity() { return capacity; }
    public String getLocation() { return location; }
    public Boolean getActive() { return active; }

    public void setName(String name) { this.name = name; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public void setLocation(String location) { this.location = location; }
    public void setActive(Boolean active) { this.active = active; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workspace workspace)) return false;
        return Objects.equals(id, workspace.id);
    }

    @Override public int hashCode() { return Objects.hash(id); }
}
