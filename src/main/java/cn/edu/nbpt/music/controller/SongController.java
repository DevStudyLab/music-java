package cn.edu.nbpt.music.controller;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.Result;
import cn.edu.nbpt.music.pojo.entity.Song;
import cn.edu.nbpt.music.service.SongService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/19 8:00
 * @Description:
 */

@RestController
@RequestMapping("/song")
public class SongController {

    @Resource
    private SongService songService;

    @GetMapping
    public Result<Page<Song>> list(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String singer,
            @RequestParam(required = false) String hot,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        return Result.success(songService.list(id, name, singer, hot, pageNum, pageSize));
    }

    @PostMapping
    public Result<Integer> add(@RequestBody Song song) {
        return Result.success(songService.add(song));
    }

    @PutMapping
    public Result<Integer> update(@RequestBody Song song) {
        return Result.success(songService.update(song));
    }

    @DeleteMapping("/{ids}")
    public Result<Integer> delete(@PathVariable List<Integer> ids) {
        return Result.success(songService.delete(ids));
    }
}
