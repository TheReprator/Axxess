package reprator.axxess.itemDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import reprator.axxess.itemDetail.databinding.FragmentItemDetailBinding

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentItemDetailBinding.bind(view).also {
            //it.viewModel = viewModel
        }

    }

}