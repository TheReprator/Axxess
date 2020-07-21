package reprator.axxess.itemDetail.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CommentEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String commentId;

    @ColumnInfo(name = "first_name")
    public String comment;
}
