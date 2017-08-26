package com.nowcoder.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public interface AliyunService {

    public String saveImage(MultipartFile file ) throws IOException;
}
