package cn.edu.nbpt.music.service;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Collected;
import cn.edu.nbpt.music.pojo.vo.CollectedSongVo;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:38
 * @Description:
 */
public interface CollectedService {
    Page<CollectedSongVo> list(Integer id, Integer songId, Integer pageNum, Integer pageSize);

    Integer add(Collected collected);

    Integer delete(List<Integer> ids);
}
