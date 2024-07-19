package cn.edu.nbpt.music.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : wer
 * @Date: 2024/7/19 7:58
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private Integer id;
    private String name;
    private String singer;
    private String type;
    private String content;
    private String hot;
}
