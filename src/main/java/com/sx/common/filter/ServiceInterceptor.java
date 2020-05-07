package com.sx.common.filter;

import com.sx.common.utils.http.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author yinyihang
 * @Description: 请求拦截器 记录请求耗时、请求url、请求类型、客户端IP等、后期扩展请求日志记录
 * @date 2020年05月6日
 */
public class ServiceInterceptor extends HandlerInterceptorAdapter {
    private final NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");
    private final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    @Override
    //请求进来执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        return true;
    }

    @Override
    //返回请求执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间

        String ip = IpUtils.getIpAddr(request); //4、clientIp
        String method = request.getMethod(); //5、http method
        Integer code = response.getStatus(); //6、 response code

        HandlerMethod handlerMethod = (HandlerMethod) handler; //7、 handler
        String ClassName = handlerMethod.getMethod().getDeclaringClass().getName(); //8、 class Name
        String Name = handlerMethod.getMethod().getName(); //9、 method Name
        String classPath = ClassName + "." +Name;

        logger.info(String.format("code = %s | time = %dms | ip = %s | url = %s %s | class = %s", code, consumeTime, ip, method, request.getRequestURI(), classPath));
    }
}
