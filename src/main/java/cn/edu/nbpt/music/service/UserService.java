package cn.edu.nbpt.music.service;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.Result;
import cn.edu.nbpt.music.pojo.entity.User;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/18 13:05
 * @Description:
 */
public interface UserService {
    Page<User> list(Integer id, String gender, String username, Integer pageNum, Integer pageSize);

    Integer add(User user);

    Integer update(User user);

    Integer delete(List<Integer> ids);
}
