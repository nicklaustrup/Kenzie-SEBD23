package com.kenzie.unit.two.iam.service;

import com.kenzie.unit.two.employee.service.UserOrRoleNotFoundException;
import com.kenzie.unit.two.iam.lambda.models.AssignUserToRoleRequest;
import com.kenzie.unit.two.iam.lambda.models.GetUserRolesRequest;
import com.kenzie.unit.two.iam.models.Role;
import com.kenzie.unit.two.iam.models.User;
import com.kenzie.unit.two.iam.models.UserRoles;
import com.kenzie.unit.two.iam.storage.Storage;

import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;

import java.util.List;

@ExcludeFromJacocoGeneratedReport
public class UserRoleService {
    private final Storage storage;

    public UserRoleService(Storage storage) {
        this.storage = storage;
    }

    public UserRoles createUserRole(AssignUserToRoleRequest request) {
        User user = storage.getUserByUsername(request.getUserName());
        Role role = storage.getRoleByRoleName(request.getRoleName());
        return storage.storeUserRole(user, role);
    }

    public UserRoles getUserRoles(GetUserRolesRequest request) {
        User user = storage.getUserByUsername(request.getUserName());
        return storage.getUserRoles(user);
    }

    public UserRoles getUserRoles(String userName) {
        User user = storage.getUserByUsername(userName);
        return storage.getUserRoles(user);
    }

    public boolean doesUserHaveRole(User user, Role role) {
        if (user == null || role == null) {
            throw new UserOrRoleNotFoundException("User or Role does not exist!");
        }
        UserRoles userRoles = storage.getUserRoles(user);
        List<Role> userRolesList = userRoles.getRoles();

        if (userRolesList == null) {
            return false;
        }
        if (userRolesList.contains(role)) {
            return true;
        } else {
            return false;
        }
    }
}

//     UserRoles userRoles = storage.getUserRoles(user);
//        if (userRoles != null && userRoles.getRoles() != null && role != null) {
//            for (Role userRole : userRoles.getRoles()) {
//                if (role.getRoleName().equalsIgnoreCase(userRole.getRoleName())) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    }

