package cn.zzk.zuulsvr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Create Time : 2019/09/23
 *
 * @author zzk
 */
@Component
public class TrackingFilter extends ZuulFilter {


    private static Logger log = LoggerFactory.getLogger(TrackingFilter.class);

    /**
     * 告诉 Zuul, 该过滤器的类型 ，默认的 Zuul 的类型是 pre,route,post,error
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 判断该过滤器是否被执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行的内容
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("x-token");
        if (token != null) {
            log.info("x-token is {}", token);
        } else {
            log.info("without x-token");
            requestContext.addZuulRequestHeader("x-token", UUID.randomUUID().toString());
        }
        return null;
    }
}
