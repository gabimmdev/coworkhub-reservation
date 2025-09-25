package com.fiap.workspaces.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "workspace_id")
  private Workspace workspace;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private AppUser user;

  @NotNull
  private LocalDateTime startTime;

  @NotNull
  private LocalDateTime endTime;

  @NotBlank
  private String status;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Workspace getRoom() { return workspace; }
  public void setRoom(Workspace workspace) { this.workspace = workspace; }
  public AppUser getUser() { return user; }
  public void setUser(AppUser user) { this.user = user; }
  public LocalDateTime getStartTime() { return startTime; }
  public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
  public LocalDateTime getEndTime() { return endTime; }
  public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}