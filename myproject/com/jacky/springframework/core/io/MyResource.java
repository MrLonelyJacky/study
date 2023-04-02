package com.jacky.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源加载
 */
public interface MyResource {
    /**
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
