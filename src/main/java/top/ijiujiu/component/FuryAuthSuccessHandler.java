package top.ijiujiu.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.ijiujiu.entity.User;
import top.ijiujiu.utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登录验证成功的类
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/20 17:32
 */
@Component
public class FuryAuthSuccessHandler implements AuthenticationSuccessHandler {

    /**Json转化工具*/
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        System.out.println("管理员 " + user.getUsername() + " 登录");
        ResultUtil<User> result = new ResultUtil<>();
        result.success("登录成功",user);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
