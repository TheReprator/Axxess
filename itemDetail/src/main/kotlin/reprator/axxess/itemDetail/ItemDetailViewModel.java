package reprator.axxess.itemDetail;

import androidx.arch.core.util.Function;
import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import reprator.axxess.base_android.SearchModal;
import reprator.axxess.itemDetail.data.CommentEntity;
import reprator.axxess.itemDetail.repository.CommentMapperImpl;
import reprator.axxess.itemDetail.repository.CommentRepository;

public class ItemDetailViewModel extends ViewModel {

    private SavedStateHandle savedStateHandle;
    private CommentRepository commentRepository;
    private CommentMapperImpl commentMapper;

    private SearchModal searchModal;

    public MutableLiveData<String> comment = new MutableLiveData<>();

    LiveData<List<CommentModal>> commentModalList;

    @ViewModelInject
    public ItemDetailViewModel(@Assisted SavedStateHandle savedStateHandle,
                               CommentRepository commentRepository, CommentMapperImpl comentMapper) {
        this.commentMapper = comentMapper;

        this.savedStateHandle = savedStateHandle;
        this.commentRepository = commentRepository;

        this.savedStateHandle = savedStateHandle;
        searchModal = savedStateHandle.get("selectedItem");

        commentModalList = Transformations.
                switchMap(commentRepository.getAllById(searchModal.id),
                        new Function<List<CommentEntity>, LiveData<List<CommentModal>>>() {

                            @Override
                            public LiveData<List<CommentModal>> apply(List<CommentEntity> input) {
                                MutableLiveData<List<CommentModal>> data = new MutableLiveData<List<CommentModal>>();
                                data.setValue(commentMapper.mapTo(input));
                                return data;
                            }
                        });
    }

    public void insertComment() {
        if (comment.getValue() == null || comment.getValue().trim().length() <= 0)
            return;

        commentRepository.insert(searchModal.id, comment.getValue());
    }
}
