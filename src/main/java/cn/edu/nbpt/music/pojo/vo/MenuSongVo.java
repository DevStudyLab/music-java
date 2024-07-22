package cn.edu.nbpt.music.pojo.vo;

import cn.edu.nbpt.music.pojo.entity.Menu;
import cn.edu.nbpt.music.pojo.entity.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/22 10:21
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuSongVo extends Menu {
    private List<Song> songList;
}
