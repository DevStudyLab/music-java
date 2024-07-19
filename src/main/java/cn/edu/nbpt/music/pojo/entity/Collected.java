package cn.edu.nbpt.music.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:35
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collected {
    private Integer id;
    private Integer songId;
    private Date createTime;
}
