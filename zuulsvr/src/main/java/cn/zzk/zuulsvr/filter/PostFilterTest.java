package cn.zzk.zuulsvr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * Create Time : 2019/09/23
 *
 * @author zzk
 */
@Component
public class PostFilterTest extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(PostFilterTest.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("执行后置调用");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        response.addHeader("x-auth-token", "1111");
        return null;
    }
}
