package com.rm.mobile.ui.container

import android.app.Activity
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rm.mobile.R
import com.rm.mobile.databinding.ActivityContainerBinding
import com.rm.mobile.ui.container.viewmodel.ContainerUiState
import com.rm.mobile.ui.container.viewmodel.ContainerViewModel
import com.rm.mobile.utils.collect
import com.rm.mobile.utils.launchCallPhone
import com.rm.mobile.utils.materialDialog
import com.rm.mobile.utils.recognizeSpeechIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityContainerBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by viewModels()

    private val startForResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { list ->
                    containerViewModel.recognizeSpeech(list[0])
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInitUi()
    }

    private fun setInitUi() {
        initNavigation()
        setFlows()
    }

    private fun initNavigation() {
        binding.bottomNavigationView.setupWithNavController(
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        )
    }

    private fun setFlows() {
        collect(containerViewModel.container) { state ->
            when (state) {
                is ContainerUiState.CallPhone -> callPhone()
                is ContainerUiState.OpenRecognizeSpeech -> recognizeSpeech()
                else -> return@collect
            }
        }
    }

    private fun callPhone() {
        launchCallPhone(getString(R.string.app_phone_support))
    }

    private fun recognizeSpeech() {
        val intent = recognizeSpeechIntent {
            openDialog()
        }
        startForResult.launch(intent)
    }

    private fun openDialog() {
        materialDialog(
            title = getString(R.string.app_message),
            message = getString(R.string.app_your_device_doesnt_support_voice_input),
            textButton = getString(R.string.app_accept),
        )
    }
}
