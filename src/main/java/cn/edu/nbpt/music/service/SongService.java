package cn.edu.nbpt.music.service;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.entity.Song;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:02
 * @Description:
 */
public interface SongService {
    Page<Song> list(Integer id, String name, String singer, String hot, Integer pageNum, Integer pageSize);

    Integer add(Song song);

    Integer update(Song song);

    Integer delete(List<Integer> ids);
}
