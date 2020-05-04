package com.github.NynrahGhost.finalProject.database.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.NynrahGhost.finalProject.database.entities.Permission;
import com.github.NynrahGhost.finalProject.database.entities.PermissionEntity;
import com.github.NynrahGhost.finalProject.database.repositories.PermissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

	private final PermissionRepository permissionRepository;

    @Transactional
    public PermissionEntity createPermission(Permission permission) {
        PermissionEntity perm = new PermissionEntity();
        perm.setPermission(permission);

        return permissionRepository.saveAndFlush(perm);
    }
    
    public List<PermissionEntity> getPermissions() {
    	return permissionRepository.findAll();
    }
    
    public List<PermissionEntity> getPermission(int id) {
    	List<PermissionEntity> res = new ArrayList<PermissionEntity>();
    	res.add(permissionRepository.findById(id).get());
    	return res;
    }
    
    public List<PermissionEntity> getPermissions(Permission permission) {
    	return permissionRepository.findAllByPermission(permission);
    }

}
