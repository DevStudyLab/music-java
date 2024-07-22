package cn.edu.nbpt.music.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : wer
 * @Date: 2024/7/22 10:17
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuSongMap {
    private Integer id;
    private Integer menuId;
    private Integer songId;
}
