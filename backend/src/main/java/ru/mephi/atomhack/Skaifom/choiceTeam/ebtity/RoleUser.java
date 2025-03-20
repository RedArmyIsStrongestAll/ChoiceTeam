package ru.mephi.atomhack.Skaifom.choiceTeam.ebtity;

public enum RoleUser {
    MASTER(1),
    LIBRARIAN(2);

    private final int roleCode;

    RoleUser(int roleCode) {
        this.roleCode = roleCode;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public static boolean isValidRole(int roleCode) {
        for (RoleUser role : RoleUser.values()) {
            if (role.getRoleCode() == roleCode) {
                return true;
            }
        }
        return false;
    }

    public static RoleUser fromCode(int roleCode) {
        for (RoleUser r : RoleUser.values()) {
            if (r.getRoleCode() == roleCode) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role code: " + roleCode);
    }
}

