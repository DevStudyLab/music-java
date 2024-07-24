package cn.edu.nbpt.music.controller;

import cn.edu.nbpt.music.pojo.Page;
import cn.edu.nbpt.music.pojo.Result;
import cn.edu.nbpt.music.pojo.entity.Comment;
import cn.edu.nbpt.music.pojo.vo.CommentUserVo;
import cn.edu.nbpt.music.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : wer
 * @Date: 2024/7/24 9:36
 * @Description:
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping
    public Result<Page<CommentUserVo>> list(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer itId,
            @RequestParam(required = false) Integer songId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        return Result.success(commentService.list(id, itId, songId, userId, pageNum, pageSize));
    }

    @PostMapping
    public Result<Integer> add(@RequestBody Comment comment) {
        return Result.success(commentService.add(comment));
    }

    @PutMapping
    public Result<Integer> update(@RequestBody Comment comment) {
        return Result.success(commentService.update(comment));
    }

    @DeleteMapping("/{ids}")
    public Result<Integer> delete(@PathVariable List<Integer> ids) {
        return Result.success(commentService.delete(ids));
    }
}
