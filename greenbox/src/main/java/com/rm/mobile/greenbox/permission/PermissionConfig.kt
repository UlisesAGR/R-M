package com.rm.mobile.greenbox.permission

import androidx.fragment.app.FragmentManager
import com.rm.mobile.utils.PermissionType

class PermissionConfig(
    val animUp: Int = 0,
    val animDown: Int = 0,
    val permission: PermissionType,
    val isGranted: () -> Unit,
) {
    private val ticketDialog: PermissionDialogFragment by lazy {
        PermissionDialogFragment.newInstance(this)
    }

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(ticketDialog, PermissionDialogFragment.PERMISSION_DIALOG_TAG)
            .commitAllowingStateLoss()
    }

    fun dismiss() {
        ticketDialog.dismiss()
    }

    fun setCancelable(isCancelable: Boolean) {
        ticketDialog.isCancelable = isCancelable
    }
}
