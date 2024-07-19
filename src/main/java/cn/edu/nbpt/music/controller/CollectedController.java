package cn.edu.nbpt.music.controller;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.Result;
import cn.edu.nbpt.music.pojo.entity.Collected;
import cn.edu.nbpt.music.pojo.vo.CollectedSongVo;
import cn.edu.nbpt.music.service.CollectedService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:37
 * @Description:
 */
@RestController
@RequestMapping("/collected")
public class CollectedController {

    @Resource
    private CollectedService collectedService;

    @GetMapping
    public Result<Page<CollectedSongVo>> list(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer songId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        return Result.success(collectedService.list(id, songId, pageNum, pageSize));
    }

    @PostMapping
    public Result<Integer> add(@RequestBody Collected collected) {
        return Result.success(collectedService.add(collected));
    }

    @DeleteMapping("/{ids}")
    public Result<Integer> delete(@PathVariable List<Integer> ids) {
        return Result.success(collectedService.delete(ids));
    }
}
