package com.jacky.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description: 通过 HTTP 的方式读取云服务的文件，我们也可以把配置文件放到 GitHub 或者 Gitee 上。
 * @author: jacky
 * @create: 2023-04-02 13:12
 **/
public class MyUrlResource implements MyResource{
    private final URL url;

    public MyUrlResource(URL url) {
        Assert.notNull(url,"URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = this.url.openConnection();
        try {
            return connection.getInputStream();
        }
        catch (IOException ex){
            if (connection instanceof HttpURLConnection){
                ((HttpURLConnection) connection).disconnect();
            }
            throw ex;
        }
    }
}
