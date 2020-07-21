package reprator.axxess.itemDetail;

public class CommentModal {
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentModal that = (CommentModal) o;
        return comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return comment.hashCode();
    }
}
