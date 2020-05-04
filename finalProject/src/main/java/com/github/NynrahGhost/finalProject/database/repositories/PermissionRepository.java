package com.github.NynrahGhost.finalProject.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.NynrahGhost.finalProject.database.entities.Permission;
import com.github.NynrahGhost.finalProject.database.entities.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
	List<PermissionEntity> findAll();
	
	List<PermissionEntity> findAllByPermission(Permission permission);
}