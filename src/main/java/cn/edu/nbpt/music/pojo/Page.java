package cn.edu.nbpt.music.pojo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Page<T> {
    private List<T> list;

    private Long count;

    private Integer pageSize;

    private Integer pageNum;

    private Integer pageCount;

    public static <E> Page<E> page(List<E> list) {
        PageInfo<E> ePageInfo = new PageInfo<>(list);
        return new Page<>(ePageInfo.getList(), ePageInfo.getTotal(), ePageInfo.getPageSize(),
                ePageInfo.getPageNum(), ePageInfo.getPages());
    }
}
