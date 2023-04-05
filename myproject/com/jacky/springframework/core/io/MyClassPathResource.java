package com.jacky.springframework.core.io;

import cn.hutool.core.lang.Assert;
import org.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取ClassPath 下的文件信息，具体的读取过程主要是：
 * classLoader.getResourceAsStream(path)
 */
public class MyClassPathResource implements MyResource {

    private final String path;

    private ClassLoader classLoader;

    public MyClassPathResource(String path) {
        this(path, null);
    }

    public MyClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist");
        }
        return is;
    }

}