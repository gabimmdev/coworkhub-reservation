package com.fiap.workspaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.workspaces.model.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> { }