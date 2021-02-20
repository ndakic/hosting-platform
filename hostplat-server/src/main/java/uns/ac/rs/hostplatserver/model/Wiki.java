package uns.ac.rs.hostplatserver.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "wiki")
@SequenceGenerator(name = "wiki_id_seq", sequenceName = "wiki_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wiki {
    private long id;
    private String text;
    private Project project;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wiki_id_seq")
    @Column(name = "wiki_id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text", length = 1000)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    @OneToOne(fetch = FetchType.LAZY)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
