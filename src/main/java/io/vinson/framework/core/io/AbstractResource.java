package io.vinson.framework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public abstract class AbstractResource implements Resource {

    /**
     * 判断文件是否存在，如果发生异常，则检查是否可以获取文件流
     * @return
     */
    @Override
    public boolean isExist() {
        try {
            return getFile().exists();
        } catch (IOException e) {
            try {
                InputStream inputStream = getInputStream();
                inputStream.close();
                return true;
            } catch (IOException e1) {
                return false;
            }
        }
    }

    @Override
    public boolean isReadable() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public URL getURL() throws IOException {
        throw new FileNotFoundException(getDescription() + "找不到URL资源");
    }

    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription() + "找不到文件");
    }

    @Override
    public long getContentLength() throws IOException {
        InputStream inputStream = getInputStream();
        try {
            long size = 0;
            byte[] buf = new byte[255];
            int read;
            while((read = inputStream.read(buf)) != -1) {
                size += read;
            }
            return size;
        } finally {
            inputStream.close();
        }
    }

    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * 判断description是否相等
     * @see #getDescription()
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if(object == this) {
            return true;
        }
        if(object instanceof Resource && ((Resource) object).getDescription().equals(getDescription())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }
}
