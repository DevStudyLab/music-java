package cn.edu.nbpt.music.service.impl;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.mapper.CollectedMapper;
import cn.edu.nbpt.music.mapper.MenuMapper;
import cn.edu.nbpt.music.mapper.UserMapper;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.User;
import cn.edu.nbpt.music.pojo.vo.MenuUserVo;
import cn.edu.nbpt.music.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/18 13:05
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private CollectedMapper collectedMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public Page<User> list(Integer id, String gender, String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.list(id, gender, username);
        return Page.page(list);
    }

    @Override
    public Integer add(User user) {
        Integer addRow = userMapper.add(user);
        if (addRow == 0) throw new BizException(ErrorCode.INSERT_ERROR);
        return addRow;
    }

    @Override
    public Integer update(User user) {
        Integer updateRow = userMapper.update(user);
        if (updateRow == 0) throw new BizException(ErrorCode.UPDATE_ERROR);
        return updateRow;
    }

    @Transactional
    @Override
    public Integer delete(List<Integer> ids) {
        // 删除前将用户创建的数据删除
        collectedMapper.deleteAll();

        // 歌单保留但是用户id变0（佚名）
        List<MenuUserVo> list = menuMapper.list(null, null, null, null, null);
        list.forEach(vo -> {
            vo.setUserId(0);
            menuMapper.update(vo);
        });

        // 删除用户
        Integer deleteRow = userMapper.delete(ids);
        if (deleteRow == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return deleteRow;
    }
}
