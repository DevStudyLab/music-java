package cn.edu.nbpt.music.mapper;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/18 13:04
 * @Description:
 */
@Mapper
public interface UserMapper {
    List<User> list(@Param("id") Integer id,
                    @Param("gender") String gender,
                    @Param("username") String username);

    Integer update(User user);

    Integer add(User user);

    Integer delete(@Param("ids") List<Integer> ids);
}
