package cn.edu.nbpt.music.mapper;

import cn.edu.nbpt.music.pojo.entity.Collected;
import cn.edu.nbpt.music.pojo.vo.CollectedSongVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:42
 * @Description:
 */
@Mapper
public interface CollectedMapper {
    List<CollectedSongVo> list(@Param("id") Integer id, @Param("songId") Integer songId);

    Integer add(Collected collected);

    Integer delete(@Param("ids") List<Integer> ids);
}
