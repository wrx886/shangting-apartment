package com.github.wrx886.shangting_apartment_server.web.admin.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    // 上传文件到 minio 并返回文件的 url
    String upload(MultipartFile file) throws Exception;
}
