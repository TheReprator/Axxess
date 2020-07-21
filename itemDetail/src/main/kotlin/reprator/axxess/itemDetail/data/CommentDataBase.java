package reprator.axxess.itemDetail.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CommentEntity.class}, version = 1)
public abstract class CommentDataBase extends RoomDatabase {
    public abstract CommentDao commentDao();
}