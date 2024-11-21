package com.github.wrx886.shangting_apartment_server.web.admin.service.impl;

import com.github.wrx886.shangting_apartment_server.web.admin.custom.config.MinioConfiguration;
import com.github.wrx886.shangting_apartment_server.web.admin.service.FileService;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.SetBucketPolicyArgs;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfiguration minioConfiguration;

    // 上传文件到 minio 并返回文件的 url
    @Override
    public String upload(MultipartFile file) throws Exception {
        // 判断桶是否存在
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioConfiguration.getBucketName())
                .build());

        // 如果桶不存在，则创建
        if (!bucketExists) {
            // 创建桶
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioConfiguration.getBucketName())
                    .build());
            // 设置访问权限
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .config(policyConfig())
                    .build());
        }

        // 生成文件名
        String filename = generateFileName(file);

        // 上传文件
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioConfiguration.getBucketName())
                .stream(file.getInputStream(), file.getSize(), -1)
                .object(filename)
                .contentType(file.getContentType())
                .build());

        // 返回 url
        return String.join("/", minioConfiguration.getEndpoint(), minioConfiguration.getBucketName(), filename);
    }

    // 获取桶的权限配置文件
    private String policyConfig() {
        return """
                        {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Action": "s3:GetObject",
                            "Effect": "Allow",
                            "Principal": "*",
                            "Resource": "arn:aws:s3:::%s/*"
                        }
                    ]
                }
                        """.formatted(minioConfiguration.getBucketName());
    }

    // 文件名生成器
    private static String generateFileName(MultipartFile file) {
        // 当前日期（文件夹） + UUID（文件名）
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"
                + UUID.randomUUID();
    }
}
