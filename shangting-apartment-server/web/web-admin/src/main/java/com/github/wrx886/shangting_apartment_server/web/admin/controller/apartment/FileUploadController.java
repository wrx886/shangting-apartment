package com.github.wrx886.shangting_apartment_server.web.admin.controller.apartment;

import com.github.wrx886.shangting_apartment_server.common.result.Result;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件管理")
@RequestMapping("/admin/file")
@RestController
public class FileUploadController {
    @Autowired
    private MinioClient minioClient;

    @Operation(summary = "上传文件")
    @PostMapping("upload")
    public Result<String> upload(@RequestParam MultipartFile file) {
        return Result.ok();
    }
}
