package reprator.axxess.itemDetail.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import reprator.axxess.itemDetail.AppExecutor;
import reprator.axxess.itemDetail.data.CommentDao;
import reprator.axxess.itemDetail.data.CommentEntity;

public class CommentDataSource implements CommentRepository {

    private CommentDao commentDao;
    private CommentMapperImpl commentMapper;
    private AppExecutor executors;

    @Inject
    public CommentDataSource(CommentDao commentDao, CommentMapperImpl commentMapper,
                             AppExecutor executors) {
        this.commentDao = commentDao;
        this.commentMapper = commentMapper;
        this.executors = executors;
    }

    @Override
    public LiveData<List<CommentEntity>> getAllById(String commentId) {
        return commentDao.loadAllByIds(commentId);
    }

    @Override
    public void insert(final String commentId, final String comment) {
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                commentDao.insertAll(commentMapper.mapFrom(commentId, comment));
            }
        });
    }
}
