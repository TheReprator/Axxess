package reprator.axxess.itemDetail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import reprator.axxess.itemDetail.databinding.RowCommentsBinding;

public class ItemCommentAdapter extends ListAdapter<CommentModal, ItemCommentAdapter.VHComment> {

    public static final DiffUtil.ItemCallback<CommentModal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CommentModal>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull CommentModal commentModal, @NonNull CommentModal newCommentModal) {
                    return commentModal.getComment() == newCommentModal.getComment();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull CommentModal oldUser, @NonNull CommentModal newUser) {
                    return oldUser.equals(newUser);
                }
            };

    ItemCommentAdapter() {
        super(ItemCommentAdapter.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public VHComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowCommentsBinding rowCommentsBinding = RowCommentsBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new VHComment(rowCommentsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VHComment holder, int position) {
        holder.bind(getItem(position));
    }

    class VHComment extends RecyclerView.ViewHolder {

        private RowCommentsBinding rowCommentsBinding;

        public VHComment(RowCommentsBinding rowCommentsBinding) {
            super(rowCommentsBinding.getRoot());

            this.rowCommentsBinding = rowCommentsBinding;
        }

        void bind(CommentModal commentModal) {
            rowCommentsBinding.setComment(commentModal.getComment());
        }
    }
}