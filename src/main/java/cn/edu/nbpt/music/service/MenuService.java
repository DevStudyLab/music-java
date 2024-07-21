package cn.edu.nbpt.music.service;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Menu;
import cn.edu.nbpt.music.pojo.vo.MenuUserVo;

import java.util.Date;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/21 15:02
 * @Description:
 */
public interface MenuService {
    Page<MenuUserVo> list(Integer id, String name, Integer userId, Date startTime, Date endTime, Integer pageNum, Integer pageSize);

    Integer add(Menu menu);

    Integer update(Menu menu);

    Integer delete(List<Integer> ids);
}
