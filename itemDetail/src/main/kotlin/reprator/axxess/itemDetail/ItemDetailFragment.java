package reprator.axxess.itemDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import reprator.axxess.base_android.BackNavigator;
import reprator.axxess.base_android.SearchModal;
import reprator.axxess.itemDetail.databinding.FragmentItemDetailBinding;

@AndroidEntryPoint
public class ItemDetailFragment extends Fragment {

    private FragmentItemDetailBinding binding;
    private ItemCommentAdapter itemCommentAdapter;

    private ItemDetailViewModel model ;

    @Inject
    public BackNavigator backNavigator;

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentItemDetailBinding.inflate(inflater);

        model = new ViewModelProvider(this).get(ItemDetailViewModel.class);

        SearchModal searchModal = getArguments().getParcelable("selectedItem");

        binding.setImageUrl(searchModal.getImageUrl());
        binding.setTitle(searchModal.getTitle());
        binding.setViewModal(model);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemCommentAdapter = new ItemCommentAdapter();

        RecyclerView recyclerView = binding.itemDetailRecycler;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemCommentAdapter);

        model.commentModalList.observe(getViewLifecycleOwner(), new Observer<List<CommentModal>>() {
            @Override
            public void onChanged(List<CommentModal> commentModalList) {
                itemCommentAdapter.submitList(commentModalList);
            }
        });

        binding.itemDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backNavigator.navigateToBack();
            }
        });
    }
}
