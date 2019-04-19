package cn.yfl.travel.web.servlets;

import cn.yfl.travel.entity.ResultInfo;
import cn.yfl.travel.entity.User;
import cn.yfl.travel.service.UserService;
import cn.yfl.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        String checkCode = request.getParameter("check");
        String checkCode_session = (String) request.getSession().getAttribute("checkCode_session");

        //删除使用过的验证码
        request.getSession().removeAttribute("checkCode_session");
        //校验验证码
        if(checkCode == null || !checkCode.equalsIgnoreCase(checkCode_session)){
            ResultInfo info = new ResultInfo();
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码输入有误");
            ObjectMapper mapper = new ObjectMapper();
            //将结果序列化为json
            String json = mapper.writeValueAsString(info);
            //将json写回客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service完成登陆
        UserService service = new UserServiceImpl();
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        if(u == null){
            //用户名或者密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或者密码错误");
        }else {
            //登陆成功
            request.getSession().setAttribute("loginUserName", u.getName());
            info.setFlag(true);
        }

        ObjectMapper mapper = new ObjectMapper();
        //将结果序列化为json
        String json = mapper.writeValueAsString(info);
        //将json写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
