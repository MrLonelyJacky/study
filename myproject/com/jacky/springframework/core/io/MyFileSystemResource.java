package com.jacky.springframework.core.io;

import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 指定文件路径的方式读取文件信息
 * @author: jacky
 * @create: 2023-04-02 12:56
 **/
@Data
public class MyFileSystemResource implements MyResource{
    private final File file;

    private final String path;

    public MyFileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
