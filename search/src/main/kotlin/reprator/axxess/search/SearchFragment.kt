package reprator.axxess.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import reprator.axxess.base_android.extensions.hideSoftInput
import reprator.axxess.base_android.extensions.snackBar
import reprator.axxess.base_android.util.event.EventObserver
import reprator.axxess.search.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SearchView.OnQueryTextListener {

    private lateinit var searchAdapter: SearchAdapter

    private val viewModel: SearchViewModel by viewModels()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.setSearchQuery(query)
        hideSoftInput()
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        viewModel.setSearchQuery(query)
        return true
    }

    override fun onStop() {
        viewModel.clearQuery()
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchBinding.bind(view).also {
            it.state = viewModel
            it.lifecycleOwner =viewLifecycleOwner
        }

        initializeObserver()
        initializeRecycler()

        binding.searchSearchview.setOnQueryTextListener(this)
    }

    private fun initializeRecycler() {
        searchAdapter = SearchAdapter {
            viewModel.positionClicked(it)
        }

        with(binding.searchRecycler) {

            layoutManager = GridLayoutManager(requireContext(), 3)
            addItemDecoration(
                ItemOffsetDecoration(requireContext(), R.dimen.search_padding_item)
            )
            adapter = searchAdapter
        }
    }

    private fun initializeObserver() {

        viewModel.isError.observe(viewLifecycleOwner, EventObserver {
            binding.searchRecycler.snackBar(it)
        })

        viewModel.searchItemList.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it.toList())
        }
    }
}