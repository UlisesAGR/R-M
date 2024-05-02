package com.rm.mobile.greenbox.permission

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.rm.mobile.greenbox.R
import com.rm.mobile.greenbox.databinding.FragmentPermissionDialogBinding
import com.rm.mobile.utils.PermissionType
import com.rm.mobile.utils.checkPermission
import com.rm.mobile.utils.launchSettings
import com.rm.mobile.utils.materialDialog
import com.rm.mobile.utils.setAnimations
import com.rm.mobile.utils.setImageViewAnimation
import com.rm.mobile.utils.setOnSafeClickListener

class PermissionDialogFragment : DialogFragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPermissionDialogBinding.inflate(layoutInflater)
    }

    private lateinit var permissionConfig: PermissionConfig

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                permissionConfig.isGranted()
            } else {
                showMaterialDialog()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView(permissionConfig)
    }

    private fun setView(permissionConfig: PermissionConfig) {
        permissionConfig.apply {
            setPermissionText(permission)
            setListener()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setPermissionText(type: PermissionType) =
        with(binding) {
            when (type) {
                PermissionType.CAMERA -> {
                    titleTextView.text = getString(R.string.camera_permit)
                    subTitleTextView.text = getString(R.string.access_your_camera)
                }

                PermissionType.PHONE -> {
                    titleTextView.text = getString(R.string.call_phone_permit)
                    subTitleTextView.text = getString(R.string.access_your_calls_phone)
                }

                PermissionType.STORAGE -> {
                    titleTextView.text = getString(R.string.storage_permit)
                    subTitleTextView.text = getString(R.string.access_your_storage)
                }

                PermissionType.LOCATION -> {
                    titleTextView.text = getString(R.string.location_permit)
                    subTitleTextView.text = getString(R.string.access_your_precise_location)
                }
            }
        }

    private fun setListener() =
        with(binding) {
            confirmButton.setOnSafeClickListener {
                validatePermission(permissionConfig.permission)
            }
            permissionCheckBox.setOnCheckedChangeListener { _, isChecked ->
                confirmButton.isEnabled = isChecked
            }
        }

    private fun validatePermission(permission: PermissionType) {
        if (requireContext().checkPermission(permission.value)) {
            permissionConfig.isGranted()
        } else {
            requestPermissionLauncher.launch(permission.value)
        }
    }

    private fun showMaterialDialog() {
        requireContext().materialDialog(
            title = getString(R.string.allow_access_to_the_permission),
            message = getString(R.string.it_is_very_important_to_configure_this_permission),
            textButton = getString(R.string.set_permission),
            onClick = {
                requireActivity().apply {
                    launchSettings(packageName)
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        dialog?.setAnimations(permissionConfig.animUp)
    }

    override fun dismiss() {
        view?.setImageViewAnimation(
            permissionConfig.animDown,
            onAnimationEnd = {
                callDismiss()
            },
        )
    }

    private fun callDismiss() {
        super.dismiss()
    }

    companion object {
        const val PERMISSION_DIALOG_TAG = "PermissionDialogFragment"
        fun newInstance(
            permissionConfig: PermissionConfig,
        ): PermissionDialogFragment {
            return PermissionDialogFragment().apply {
                this.permissionConfig = permissionConfig
            }
        }
    }
}
