package cn.edu.nbpt.music.pojo.vo;

import cn.edu.nbpt.music.pojo.entity.Comment;
import cn.edu.nbpt.music.pojo.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/24 9:34
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentUserVo extends Comment {
    private User user;
    private List<CommentUserVo> childComments;
}
