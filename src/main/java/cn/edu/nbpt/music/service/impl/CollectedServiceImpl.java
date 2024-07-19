package cn.edu.nbpt.music.service.impl;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.mapper.CollectedMapper;
import cn.edu.nbpt.music.mapper.SongMapper;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Collected;
import cn.edu.nbpt.music.pojo.entity.Song;
import cn.edu.nbpt.music.pojo.vo.CollectedSongVo;
import cn.edu.nbpt.music.service.CollectedService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:38
 * @Description:
 */
@Service
public class CollectedServiceImpl implements CollectedService {

    @Resource
    private CollectedMapper collectedMapper;

    @Resource
    private SongMapper songMapper;

    @Override
    public Page<CollectedSongVo> list(Integer id, Integer songId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CollectedSongVo> collectedSongVo = collectedMapper.list(id, songId);
        return Page.page(collectedSongVo);
    }

    @Override
    public Integer add(Collected collected) {
        // 校验歌曲是否存在
        Integer songId = collected.getSongId();
        List<Song> list = songMapper.list(songId, null, null, null);
        if (list.isEmpty()) throw new BizException(ErrorCode.INSERT_NOT_FIND_ERROR);

        // 校验是否已经收藏
        List<CollectedSongVo> already = collectedMapper.list(null, songId);
        if (!already.isEmpty()) throw new BizException(ErrorCode.INSERT_EXIST_ERROR);

        collected.setCreateTime(new Date());
        Integer add = collectedMapper.add(collected);
        if (add == 0) throw new BizException(ErrorCode.INSERT_ERROR);
        return add;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer deleteRow = collectedMapper.delete(ids);
        if (deleteRow == 0) throw new BizException(ErrorCode.DELETE_ERROR);
        return deleteRow;
    }
}
