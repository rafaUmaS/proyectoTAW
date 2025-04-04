package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EntityCastId implements java.io.Serializable {
    private static final long serialVersionUID = 3276960957831265928L;
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Crew_id", nullable = false)
    private Integer crewId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntityCastId entity = (EntityCastId) o;
        return Objects.equals(this.crewId, entity.crewId) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crewId, id);
    }

}