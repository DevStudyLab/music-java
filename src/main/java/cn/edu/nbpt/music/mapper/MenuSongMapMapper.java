package cn.edu.nbpt.music.mapper;

import cn.edu.nbpt.music.pojo.entity.MenuSongMap;
import cn.edu.nbpt.music.pojo.vo.MenuSongVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/22 12:52
 * @Description:
 */
@Mapper
public interface MenuSongMapMapper {
    List<MenuSongMap> list(@Param("menuId") Integer menuId, @Param("songId") Integer songId);

    Integer add(MenuSongMap menuSongMap);

    Integer delete(@Param("ids") List<Integer> ids, @Param("menuId") Integer menuId);

    Integer deleteAll(@Param("menuId") Integer menuId);
}
