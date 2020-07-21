package reprator.axxess.itemDetail.repository;


import androidx.lifecycle.LiveData;

import java.util.List;

import reprator.axxess.itemDetail.data.CommentEntity;

public interface CommentRepository {

    LiveData<List<CommentEntity>> getAllById(String commentId);

    void insert(String commentId, String comment);
}