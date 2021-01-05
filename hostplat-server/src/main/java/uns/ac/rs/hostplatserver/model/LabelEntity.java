package uns.ac.rs.hostplatserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "label")
@SequenceGenerator(name = "label_id_seq", sequenceName = "label_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelEntity {

    private long id;
    private String md5h;
    private String name;
    private Timestamp createDate;
//    private UserEntity user; update this later
    private StatusEntity status;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "label_id_seq")
    @Column(name = "label_id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMd5h() {
        return md5h;
    }

    public void setMd5h(String md5h) {
        this.md5h = md5h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
