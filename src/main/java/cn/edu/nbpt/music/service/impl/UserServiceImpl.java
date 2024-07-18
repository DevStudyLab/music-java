package cn.edu.nbpt.music.service.impl;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.mapper.UserMapper;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.User;
import cn.edu.nbpt.music.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<User> list(Integer id, String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.list(id, username);
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

    @Override
    public Integer delete(List<Integer> ids) {
        Integer deleteRow = userMapper.delete(ids);
        if (deleteRow == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return deleteRow;
    }
}
