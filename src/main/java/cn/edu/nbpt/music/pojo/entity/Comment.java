package cn.edu.nbpt.music.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : wer
 * @Date: 2024/7/24 9:33
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private String content;
    private Integer itId;
    private Integer songId;
    private Integer userId;
}
