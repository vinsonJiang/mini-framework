package io.vinson.framework.core.io;

import io.vinson.framework.core.util.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Description: url资源
 *
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public class UrlResource extends AbstractResource {

    private URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "url 不能为空");
        this.url = url;
    }

    @Override
    public URL getURL() throws IOException {
        return this.url;
    }

    @Override
    public File getFile() throws IOException {
        if (!"file".equals(this.url.getProtocol())) {
            throw new FileNotFoundException(
                    getDescription() + " cannot be resolved to absolute file path " +
                            "because it does not reside in the file system: " + this.url);
        }
        return new File(this.url.getFile());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = this.url.openConnection();
        try {
            return connection.getInputStream();
        } catch (IOException e) {
            if(connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).disconnect();
            }
            throw e;
        }
    }

    @Override
    public String getDescription() {
        return "URL [" + this.url + "]";
    }
}
