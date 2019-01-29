package io.vinson.framework.core.io;

import io.vinson.framework.core.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2019/1/29
 */
public class FileSystemResource extends AbstractResource implements WritableResource {

    private final File file;

    public FileSystemResource(File file) {
        Assert.notNull(file, "file must not be null");
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public OutputStream getOutputStream() {
        return null;
    }
}
