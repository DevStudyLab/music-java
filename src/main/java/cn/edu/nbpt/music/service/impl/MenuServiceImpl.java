package cn.edu.nbpt.music.service.impl;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.mapper.MenuMapper;
import cn.edu.nbpt.music.mapper.MenuSongMapMapper;
import cn.edu.nbpt.music.mapper.SongMapper;
import cn.edu.nbpt.music.mapper.UserMapper;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Menu;
import cn.edu.nbpt.music.pojo.entity.MenuSongMap;
import cn.edu.nbpt.music.pojo.entity.Song;
import cn.edu.nbpt.music.pojo.entity.User;
import cn.edu.nbpt.music.pojo.vo.MenuSongVo;
import cn.edu.nbpt.music.pojo.vo.MenuUserVo;
import cn.edu.nbpt.music.service.MenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private SongMapper songMapper;
    @Resource
    private MenuSongMapMapper menuSongMapMapper;
    private List<Song> songs1;

    @Override
    public Page<MenuUserVo> list(Integer id, String name, Integer userId, Date startTime, Date endTime, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MenuUserVo> list = menuMapper.list(id, name, userId, startTime, endTime);
        list.forEach(item -> item.getUser().setPassword("保密"));

        return Page.page(list);
    }

    @Override
    public Page<MenuSongVo> detail(Integer menuId, Integer songId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        // 校验歌单是否存在
        List<MenuUserVo> menus = menuMapper.list(menuId, null, null, null, null);
        if (menus.isEmpty()) throw new BizException(ErrorCode.MENU_NOT_EXIST);

        // 校验歌曲是否存在
        songExists(songId);

        // 查询歌曲和歌单的关联关系
        List<MenuSongMap> list = menuSongMapMapper.list(menuId, songId);

        // 查询歌曲信息
        List<Song> songs1 = new ArrayList<>();
        list.forEach(item -> songs1.add(songMapper.list(item.getSongId(), null, null, null).get(0)));
        MenuSongVo menuSongVo = new MenuSongVo();

        // 设置歌单信息
        BeanUtils.copyProperties(menus.get(0), menuSongVo);
        menuSongVo.setSongList(songs1);

        // 返回分页结果
        List<MenuSongVo> result = new ArrayList<>();
        result.add(menuSongVo);
        return Page.page(result);
    }

    @Override
    public Integer add(Menu menu) {
        // 校验用户是否存在
        Integer userId = menu.getUserId();
        userExists(userId);

        // 设置创建时间
        menu.setCreateTime(new Date());

        Integer addRow = menuMapper.add(menu);
        if (addRow == 0) throw new BizException(ErrorCode.INSERT_ERROR);
        return addRow;
    }

    @Transactional
    @Override
    public Integer addSongs(List<Integer> songIds, Integer menuId) {
        // 校验歌单是否存在
        List<MenuUserVo> menus = menuMapper.list(menuId, null, null, null, null);
        if (menus.isEmpty()) throw new BizException(ErrorCode.MENU_NOT_EXIST);

        songIds.forEach(item -> {
            // 校验歌曲是否存在
            songExists(item);

            // 校验歌曲是否已存在歌单
            List<MenuSongMap> list = menuSongMapMapper.list(menuId, item);
            if (!list.isEmpty()) throw new BizException(ErrorCode.INSERT_EXIST_ERROR);

            MenuSongMap menuSongMap = new MenuSongMap();
            menuSongMap.setMenuId(menuId);
            menuSongMap.setSongId(item);
            menuSongMapMapper.add(menuSongMap);
        });
        return songIds.size();
    }

    @Override
    public Integer update(Menu menu) {
        // 校验用户是否存在
        Integer userId = menu.getUserId();
        userExists(userId);

        Integer updateRow = menuMapper.update(menu);
        if (updateRow == 0) throw new BizException(ErrorCode.UPDATE_ERROR);
        return updateRow;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer deleteRow = menuMapper.delete(ids);
        if (deleteRow == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return deleteRow;
    }

    @Override
    public Integer deleteSongs(List<Integer> ids, Integer menuId) {
        // 校验歌单是否存在
        List<MenuUserVo> menus = menuMapper.list(menuId, null, null, null, null);
        if (menus.isEmpty()) throw new BizException(ErrorCode.MENU_NOT_EXIST);

        Integer deleteRow = menuSongMapMapper.delete(ids, menuId);
        if (deleteRow == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return deleteRow;
    }

    /**
     * 校验用户是否存在
     */
    private void userExists(Integer userId) {
        List<User> list = userMapper.list(userId, null);
        if (list.isEmpty()) throw new BizException(ErrorCode.USER_NOT_EXIST);
    }

    /**
     * 校验歌曲是否存在
     */
    private void songExists(Integer songId) {
        List<Song> songs = songMapper.list(songId, null, null, null);
        if (songs.isEmpty()) throw new BizException(ErrorCode.SONG_NOT_EXIST);
    }
}
