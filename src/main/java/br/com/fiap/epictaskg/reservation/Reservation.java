package br.com.fiap.epictaskg.reservation;

import br.com.fiap.epictaskg.workspace.Workspace;
import br.com.fiap.epictaskg.user.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservations",
       indexes = {
           @Index(name="idx_res_room_start_end", columnList = "workspace_id,start_at,end_at"),
           @Index(name="idx_res_status", columnList = "status")
       })
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="workspace_id", nullable=false,
            foreignKey = @ForeignKey(name="fk_res_room"))
    private Workspace workspace;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false,
            foreignKey = @ForeignKey(name="fk_res_user"))
    private User user;

    @Column(name="start_at", nullable=false)
    private OffsetDateTime startAt;

    @Column(name="end_at", nullable=false)
    private OffsetDateTime endAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ReservationStatus status = ReservationStatus.PENDING;

    @Column(name="created_at", nullable=false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Reservation() {}

    public Reservation(Workspace workspace, User user, OffsetDateTime startAt, OffsetDateTime endAt) {
        this.workspace = workspace;
        this.user = user;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = ReservationStatus.PENDING;
        this.createdAt = OffsetDateTime.now();
    }

    public Long getId() { return id; }
    public Workspace getRoom() { return workspace; }
    public User getUser() { return user; }
    public OffsetDateTime getStartAt() { return startAt; }
    public OffsetDateTime getEndAt() { return endAt; }
    public ReservationStatus getStatus() { return status; }
    public OffsetDateTime getCreatedAt() { return createdAt; }

    public void setRoom(Workspace workspace) { this.workspace = workspace; }
    public void setUser(User user) { this.user = user; }
    public void setStartAt(OffsetDateTime startAt) { this.startAt = startAt; }
    public void setEndAt(OffsetDateTime endAt) { this.endAt = endAt; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
