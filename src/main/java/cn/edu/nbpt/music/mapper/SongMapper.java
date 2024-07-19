package cn.edu.nbpt.music.mapper;

import cn.edu.nbpt.music.pojo.entity.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:03
 * @Description:
 */
@Mapper
public interface SongMapper {
    List<Song> list(@Param("id") Integer id, @Param("name") String name, @Param("singer") String singer, @Param("hot") String hot);

    Integer add(Song song);

    Integer update(Song song);

    Integer delete(@Param("ids") List<Integer> ids);
}
