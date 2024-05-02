package com.rm.mobile.ui.container.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.rm.mobile.R
import com.rm.mobile.databinding.FragmentCharactersBinding
import com.rm.mobile.ui.container.characters.adapter.characters.CharactersAdapter
import com.rm.mobile.ui.container.characters.adapter.loading.LoadStatesAdapter
import com.rm.mobile.ui.container.viewmodel.ContainerViewModel
import com.rm.mobile.utils.animationIn
import com.rm.mobile.utils.clearItemAnimation
import com.rm.mobile.utils.collect
import com.rm.mobile.utils.materialDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCharactersBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by activityViewModels()

    private lateinit var charactersAdapter: CharactersAdapter

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
        setAdapter()
        setRecyclerView()
        setLoadStates()
        setFlows()
    }

    private fun setAdapter() {
        charactersAdapter = CharactersAdapter { character ->
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToDetailActivity(character.id),
            )
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(false)
            clearItemAnimation()
            clearAnimation()
            animationIn()
            adapter = charactersAdapter.withLoadStateHeaderAndFooter(
                header = LoadStatesAdapter { charactersAdapter.retry() },
                footer = LoadStatesAdapter { charactersAdapter.retry() },
            )
        }
    }

    private fun setLoadStates() =
        with(binding) {
            charactersAdapter.addLoadStateListener { loadState ->
                recyclerView.isVisible = loadState.refresh is LoadState.NotLoading
                progressLayout.isVisible = loadState.refresh is LoadState.Loading
                if (loadState.append is LoadState.Error) {
                    showErrorDialog((loadState.append as LoadState.Error).error)
                }
            }
        }

    private fun setFlows() {
        collect(containerViewModel.getCharacters()) { list ->
            charactersAdapter.submitData(list)
        }
    }

    private fun showErrorDialog(error: Throwable) {
        requireContext().materialDialog(
            title = getString(R.string.app_message),
            message = error.message.toString(),
            textButton = getString(R.string.app_accept),
        )
    }
}
