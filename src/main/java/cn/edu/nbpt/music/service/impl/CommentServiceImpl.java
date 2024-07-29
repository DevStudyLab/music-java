package cn.edu.nbpt.music.service.impl;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.mapper.CommentMapper;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Comment;
import cn.edu.nbpt.music.pojo.vo.CommentSongVo;
import cn.edu.nbpt.music.pojo.vo.CommentUserVo;
import cn.edu.nbpt.music.service.CommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/24 9:37
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Page<CommentUserVo> list(Integer id, Integer itId, Integer songId, Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentUserVo> list = commentMapper.list(id, 0, songId, userId);
        list.forEach(item -> {
            item.getUser().setPassword("保密");
            item.setChildComments(getChildComment(item.getId()));
        });
        return Page.page(list);
    }

    @Override
    public Page<CommentSongVo> mineList(Integer id, Integer itId, Integer songId, Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentSongVo> list = commentMapper.mineList(id, itId, songId, userId);
        return Page.page(list);
    }

    @Override
    public Integer add(Comment comment) {
        Integer addRow = commentMapper.add(comment);
        if (addRow == 0) throw new BizException(ErrorCode.INSERT_ERROR);
        return addRow;
    }

    @Override
    public Integer update(Comment comment) {
        Integer updateRow = commentMapper.update(comment);
        if (updateRow == 0) throw new BizException(ErrorCode.UPDATE_ERROR);
        return updateRow;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer deleteRow = commentMapper.delete(ids);
        if (deleteRow == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return deleteRow;
    }

    private List<CommentUserVo> getChildComment(Integer id) {
        List<CommentUserVo> list = commentMapper.list(null, id, null, null);
        list.forEach(item -> {
            item.getUser().setPassword("保密");
            item.setChildComments(getChildComment(item.getId()));
        });
        return list.isEmpty() ? null : list;
    }
}
