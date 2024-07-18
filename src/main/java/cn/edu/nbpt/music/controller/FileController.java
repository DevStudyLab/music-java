package cn.edu.nbpt.music.controller;

import cn.edu.nbpt.music.exception.BizException;
import cn.edu.nbpt.music.pojo.Constants;
import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.FileTypeEnum;
import cn.edu.nbpt.music.pojo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${music.storage.dir}")
    private String storageDir;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @PostMapping("upload")
    public Result<String> upload(MultipartFile file) {
        File storageDir = new File(this.storageDir);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        // 源文件名
        String sourceFileName = file.getOriginalFilename();
        // .位置
        int index = sourceFileName.lastIndexOf('.');
        // 文件扩展名
        String fileExtName = sourceFileName.substring(index + 1);
        // 图片版本控制，防止覆盖，UUID或时间戳实现
        String targetFileName = sourceFileName.substring(0, index) + "_" + UUID.randomUUID() + "." + fileExtName;
        // 目标文件
        File targetFile = new File(storageDir, targetFileName);
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            throw new BizException(ErrorCode.FILE_OPERATE_ERROR);
        }
        if (Arrays.asList("jpg", "jpeg", "gif", "png").contains(fileExtName)) {
            targetFileName = targetFileName + "/" + FileTypeEnum.IMAGE;
        } else {
            targetFileName = targetFileName + "/" + FileTypeEnum.DOCUMENT;
        }
        return Result.success(contextPath + "/file/download/" + targetFileName);
    }


    @GetMapping("download/{fileName}/{fileType}")
    public void download(
            @PathVariable String fileName,
            @PathVariable FileTypeEnum fileType,
            HttpServletResponse response
    ) {

        File targetFile = new File(this.storageDir, fileName);
        if (!targetFile.exists()) {
            throw new BizException(ErrorCode.FILE_OPERATE_ERROR);
        }
        if (FileTypeEnum.DOCUMENT.equals(fileType)) {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            // 以附件的形式下载
            response.setHeader(Constants.CONTENT_DISPOSITION,
                    Constants.ATTACHMENT_FILENAME + fileName);
        }
        // try resource 自动关闭连接
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(targetFile.toPath()));
             ServletOutputStream outputStream = response.getOutputStream()) {
            byte[] bytes = new byte[1024];
            int i = 0;
            while ((i = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, i);
            }
            outputStream.flush();
        } catch (Exception e) {
            throw new BizException(ErrorCode.FILE_OPERATE_ERROR);
        }
    }
}
