package cn.edu.nbpt.music.service;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Comment;
import cn.edu.nbpt.music.pojo.vo.CommentUserVo;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/24 9:36
 * @Description:
 */
public interface CommentService {
    Page<CommentUserVo> list(Integer id, Integer itId, Integer songId, Integer userId, Integer pageNum, Integer pageSize);

    Page<Comment> mineList(Integer id, Integer itId, Integer songId, Integer userId, Integer pageNum, Integer pageSize);

    Integer add(Comment comment);

    Integer update(Comment comment);

    Integer delete(List<Integer> ids);
}
