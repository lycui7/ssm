package albert.cui.shiro;

import albert.cui.entity.BaseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 继承SimpleMappingExceptionResolver，自定义异常处理程序
 * 实现 未认证、未授权 不是跳转到相应页面，而是返回结果给客户端
 * @author albert.cui
 * @date 2019/3/10 22:41
 */
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            // Expose ModelAndView for chosen error view.
            BaseResult result = new BaseResult();
            if (ex instanceof UnauthorizedException) {
                result.setMsg("未授权");
                result.setStatus("100");
            } else if (ex instanceof UnauthenticatedException) {
                result.setMsg("未认证");
                result.setStatus("222");
            } else {
                result.setMsg(ex.getMessage());
                result.setStatus("111");
            }
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            //将对象转为字符串
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
