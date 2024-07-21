package cn.edu.nbpt.music.mapper;

import cn.edu.nbpt.music.pojo.entity.Menu;
import cn.edu.nbpt.music.pojo.vo.MenuUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/21 15:01
 * @Description:
 */
@Mapper
public interface MenuMapper {
    List<MenuUserVo> list(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("userId") Integer userId,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    Integer add(Menu menu);

    Integer update(Menu menu);

    Integer delete(@Param("ids") List<Integer> ids);
}
