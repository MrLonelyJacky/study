package com.jacky.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取ClassPath 下的文件信息，具体的读取过程主要是：
 * classLoader.getResourceAsStream(path)
 */
public class MyClassPathResource implements MyResource{

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

}
