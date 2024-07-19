package cn.edu.nbpt.music.pojo.vo;

import cn.edu.nbpt.music.pojo.entity.Collected;
import cn.edu.nbpt.music.pojo.entity.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:52
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectedSongVo extends Collected {
    private Song song;
}
