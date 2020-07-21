package reprator.axxess.itemDetail.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentDao {
    @Query("SELECT * FROM commententity")
    List<CommentEntity> getAll();

    @Query("SELECT * FROM commententity WHERE commentId=:commentId")
    LiveData<List<CommentEntity>> loadAllByIds(String commentId);

    @Insert
    void insertAll(CommentEntity... commentEntities);

    @Delete
    void delete(CommentEntity user);
}
