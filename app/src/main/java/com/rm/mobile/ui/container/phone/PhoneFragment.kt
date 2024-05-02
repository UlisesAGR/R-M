package com.rm.mobile.ui.container.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rm.mobile.R
import com.rm.mobile.databinding.FragmentPhoneBinding
import com.rm.mobile.ui.container.viewmodel.ContainerViewModel
import com.rm.mobile.utils.launchCallPhone
import com.rm.mobile.utils.materialDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneFragment : Fragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPhoneBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setInitUi()
    }

    private fun setInitUi() {
        setListeners()
    }

    private fun setListeners() {
        binding.callPhoneButton.setOnClickListener {
            openCallPhoneDialog()
        }
    }

    private fun openCallPhoneDialog() {
        requireContext().materialDialog(
            title = getString(R.string.app_message),
            message = getString(R.string.app_you_want_to_dial_support_by_phone),
            textPositiveButton = getString(R.string.app_accept),
            textNegativeButton = getString(R.string.app_cancel),
            onClick = {
                containerViewModel.openCallPhone()
            },
        )
    }
}
