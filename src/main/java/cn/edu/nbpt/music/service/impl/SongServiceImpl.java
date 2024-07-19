package cn.edu.nbpt.music.service.impl;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.mapper.SongMapper;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Song;
import cn.edu.nbpt.music.service.SongService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:02
 * @Description:
 */
@Service
public class SongServiceImpl implements SongService {

    @Resource
    private SongMapper songMapper;

    @Override
    public Page<Song> list(Integer id, String name, String singer, String hot, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Song> list = songMapper.list(id, name, singer, hot);
        return Page.page(list);
    }

    @Override
    public Integer add(Song song) {
        Integer addRow = songMapper.add(song);
        if (addRow == 0) throw new BizException(ErrorCode.INSERT_ERROR);
        return addRow;
    }

    @Override
    public Integer update(Song song) {
        Integer updateRow = songMapper.update(song);
        if (updateRow == 0) throw new BizException(ErrorCode.UPDATE_ERROR);
        return updateRow;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer deleteRow = songMapper.delete(ids);
        if (deleteRow == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return deleteRow;
    }
}
