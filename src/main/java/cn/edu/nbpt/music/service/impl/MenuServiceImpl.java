package cn.edu.nbpt.music.service.impl;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.mapper.MenuMapper;
import cn.edu.nbpt.music.mapper.UserMapper;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Menu;
import cn.edu.nbpt.music.pojo.entity.User;
import cn.edu.nbpt.music.pojo.vo.MenuUserVo;
import cn.edu.nbpt.music.service.MenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/21 15:02
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<MenuUserVo> list(Integer id, String name, Integer userId, Date startTime, Date endTime, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MenuUserVo> list = menuMapper.list(id, name, userId, startTime, endTime);
        list.forEach(item -> item.getUser().setPassword("保密"));

        return Page.page(list);
    }

    @Override
    public Integer add(Menu menu) {
        // 校验用户是否存在
        Integer userId = menu.getUserId();
        userExists(userId);

        // 设置创建时间
        menu.setCreateTime(new Date());

        Integer add = menuMapper.add(menu);
        if (add == 0) throw new BizException(ErrorCode.INSERT_ERROR);
        return add;
    }

    @Override
    public Integer update(Menu menu) {
        // 校验用户是否存在
        Integer userId = menu.getUserId();
        userExists(userId);

        Integer update = menuMapper.update(menu);
        if (update == 0) throw new BizException(ErrorCode.UPDATE_ERROR);
        return update;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer delete = menuMapper.delete(ids);
        if (delete == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return delete;
    }

    /**
     * 校验用户是否存在
     */
    private void userExists(Integer userId) {
        List<User> list = userMapper.list(userId, null);
        if (list.isEmpty()) throw new BizException(ErrorCode.USER_NOT_EXIST);
    }
}
