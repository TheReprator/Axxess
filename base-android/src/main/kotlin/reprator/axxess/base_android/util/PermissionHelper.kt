package reprator.axxess.base_android.util

import android.content.Context

interface PermissionHelper {
    fun hasPermissions(context: Context, permission: List<String>): Boolean

    fun hasPermissions(context: Context, permission: String): Boolean

    fun hasPermissions(context: Context, vararg perms: String): Boolean


    fun requestPermissions(
        objectReference: Context, rationale: String,
        requestCode: Int, perms: String
    )

    fun requestPermissions(
        objectReference: Context, rationale: String,
        requestCode: Int, perms: String, permissionListener: PermissionListener?
    )
}

interface PermissionListener {
    fun permissionCancelled()
    fun permissionAllowed()
}