package cn.edu.nbpt.music.pojo.vo;

import cn.edu.nbpt.music.pojo.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author : wer
 * @Date: 2024/7/29 10:30
 * @Description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSongVo extends Comment {
    private String name;
}
