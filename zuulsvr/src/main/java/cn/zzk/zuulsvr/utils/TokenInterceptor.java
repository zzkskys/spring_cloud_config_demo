package cn.zzk.zuulsvr.utils;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 将调用链传递到服务的实际调用者上
 * Create Time : 2019/09/23
 *
 * @author zzk
 */
public class TokenInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        return execution.execute(request, body);
    }
}
