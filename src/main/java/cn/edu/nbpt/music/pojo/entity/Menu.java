package cn.edu.nbpt.music.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author : wer
 * @Date: 2024/7/21 13:15
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;
    private String name;
    private String image;
    private String description;
    private Date createTime;
    private Integer userId;
}
