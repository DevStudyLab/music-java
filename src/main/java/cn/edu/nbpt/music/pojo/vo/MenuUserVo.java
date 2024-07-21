package cn.edu.nbpt.music.pojo.vo;

import cn.edu.nbpt.music.pojo.entity.Menu;
import cn.edu.nbpt.music.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author : wer
 * @Date: 2024/7/21 15:04
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuUserVo extends Menu {
    private User user;
}
