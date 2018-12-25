package io.vinson.framework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/20
 */
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;
}
