package cn.edu.nbpt.music.controller;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.Result;
import cn.edu.nbpt.music.pojo.entity.Menu;
import cn.edu.nbpt.music.pojo.vo.MenuUserVo;
import cn.edu.nbpt.music.service.MenuService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/21 15:01
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping
    public Result<Page<MenuUserVo>> list(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        return Result.success(menuService.list(id, name, userId, startTime, endTime, pageNum, pageSize));
    }

    @PostMapping
    public Result<Integer> add(@RequestBody Menu menu) {
        return Result.success(menuService.add(menu));
    }

    @PutMapping
    public Result<Integer> update(@RequestBody Menu menu) {
        return Result.success(menuService.update(menu));
    }

    @DeleteMapping("/{ids}")
    public Result<Integer> delete(@PathVariable List<Integer> ids) {
        return Result.success(menuService.delete(ids));
    }

}
