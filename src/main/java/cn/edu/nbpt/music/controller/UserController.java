package cn.edu.nbpt.music.controller;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.Result;
import cn.edu.nbpt.music.pojo.entity.User;
import cn.edu.nbpt.music.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/18 13:04
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public Result<Page<User>> list(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String username,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        return Result.success(userService.list(id, gender, username, pageNum, pageSize));
    }

    @PostMapping
    public Result<Integer> add(@RequestBody User user) {
        return Result.success(userService.add(user));
    }

    @PutMapping
    public Result<Integer> update(@RequestBody User user) {
        return Result.success(userService.update(user));
    }

    @DeleteMapping("/{ids}")
    public Result<Integer> delete(@PathVariable List<Integer> ids) {
        return Result.success(userService.delete(ids));
    }

}
