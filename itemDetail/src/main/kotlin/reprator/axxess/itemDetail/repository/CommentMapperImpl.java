package reprator.axxess.itemDetail.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import reprator.axxess.itemDetail.CommentModal;
import reprator.axxess.itemDetail.data.CommentEntity;

public class CommentMapperImpl implements CommentMapper<CommentEntity, CommentModal> {

    @Inject
    public CommentMapperImpl(){

    }

    public CommentEntity mapFrom(String commentId, String comment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.comment = comment;
        commentEntity.commentId = commentId;
        return commentEntity;
    }

    @Override
    public List<CommentModal> mapTo(List<CommentEntity> inputList) {

        List<CommentModal> commentModalList = new ArrayList<>();

        for (CommentEntity commentEntity : inputList) {
            CommentModal commentModal = new CommentModal();
            commentModal.setComment(commentEntity.comment);

            commentModalList.add(commentModal);
        }

        return commentModalList;
    }

}
