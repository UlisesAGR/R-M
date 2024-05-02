package com.rm.mobile.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rm.mobile.databinding.ActivityWelcomeBinding
import com.rm.mobile.greenbox.permission.PermissionConfig
import com.rm.mobile.ui.container.ContainerActivity
import com.rm.mobile.utils.PermissionType
import com.rm.mobile.utils.checkPermission
import com.rm.mobile.utils.nextActivity
import com.rm.mobile.utils.startAnimation

class WelcomeActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    private lateinit var permissionConfig: PermissionConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        splash.setKeepOnScreenCondition { false }
        setInitUi()
    }

    private fun setInitUi() {
        validatePermission()
        setListeners()
    }

    private fun setListeners() {
        binding.continueButton.setOnClickListener {
            nextActivity(ContainerActivity())
            finish()
        }
    }

    private fun validatePermission() {
        if (!checkPermission(PermissionType.PHONE.value)) {
            openPermissionConfigDialog()
        }
    }

    private fun openPermissionConfigDialog() {
        permissionConfig = PermissionConfig(
            permission = PermissionType.PHONE,
            isGranted = {
                permissionConfig.dismiss()
                setConfetti()
            }
        ).also { permissionConfig ->
            permissionConfig.apply {
                show(supportFragmentManager)
                setCancelable(false)
            }
        }
    }

    private fun setConfetti() {
        binding.confettiView.startAnimation()
    }
}
