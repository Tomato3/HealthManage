package com.example.healthmanage.utils;

import android.content.Context;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionUtil {

    private Context context;
    private String[] permission;

    public PermissionUtil(Context context, String[] permission) {
        this.context = context;
        this.permission = permission;
    }

    private boolean checkPermission(String[] permission) {
        return EasyPermissions.hasPermissions(context, permission);
    }

    private void addPermission(String[] permission) {
        for (int i = 0; i < permission.length; i++) {
//            EasyPermissions.requestPermissions(context, "", 0, permission[i]);
        }

    }
}
