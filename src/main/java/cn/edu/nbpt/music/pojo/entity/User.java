package cn.edu.nbpt.music.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : wer
 * @Date: 2024/7/18 13:15
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private Integer age;
    private String avatar;
}
