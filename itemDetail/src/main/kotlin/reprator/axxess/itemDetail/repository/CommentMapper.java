package reprator.axxess.itemDetail.repository;

import java.util.List;

interface CommentMapper<Input, Output> {
    List<Output> mapTo(List<Input> inputList);
}

