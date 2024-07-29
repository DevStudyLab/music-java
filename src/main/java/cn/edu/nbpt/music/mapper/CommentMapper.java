package cn.edu.nbpt.music.mapper;

import cn.edu.nbpt.music.pojo.entity.Comment;
import cn.edu.nbpt.music.pojo.vo.CommentSongVo;
import cn.edu.nbpt.music.pojo.vo.CommentUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/24 9:36
 * @Description:
 */
@Mapper
public interface CommentMapper {
    List<CommentUserVo> list(@Param("id") Integer id,
                             @Param("itId") Integer itId,
                             @Param("songId") Integer songId,
                             @Param("userId") Integer userId);

    List<CommentSongVo> mineList(@Param("id") Integer id,
                                 @Param("itId") Integer itId,
                                 @Param("songId") Integer songId,
                                 @Param("userId") Integer userId);

    Integer add(Comment comment);

    Integer update(Comment comment);

    Integer delete(@Param("ids") List<Integer> ids);
}
