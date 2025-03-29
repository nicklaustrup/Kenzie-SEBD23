package com.kenzie.unit.two.iam.models;

import com.kenzie.unit.two.iam.entities.Roles;

import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;

import java.util.List;

@ExcludeFromJacocoGeneratedReport
public class FunctionalRole {

    // Roles required to do this action
    private List<Roles> roles;
    private List<Role> roleName;

    public FunctionalRole(List<Roles> roles) {

        this.roles = roles;
    }

    // Compare incoming list to the ones required for this class.
    public Boolean matches(List<Role> roleList) {
        for (Roles role: roles) {
            for (Role rolesList: roleList) {
                if (role.getRoleName().contains(rolesList.getRoleName())){
                    return true;
                }
            }
        }
        return false;
    }
}
