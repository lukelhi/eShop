package com.eShop.client.user.handler;

import com.eShop.client.user.service.IUserService;
import com.eShop.commons.bean.User;
import com.eShop.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/client/user")
public class UserHandler {
    @Autowired
    IUserService userService;
    @RequestMapping("/register")
    public String register(User user, String checkCode, HttpSession session, HttpServletRequest request){
//        System.err.println("用户："+user);
//        System.err.println(checkCode);
        //设置激活码
        user.setActiveCode(IdUtils.getUUID());
        //判断校验码是否正确
        String checkcode_session = (String) session.getAttribute("checkcode_session");
        if(checkcode_session.equals(checkCode)){
            int rows = userService.addUser(user,request);
            if(rows > 0){
                //redirect:解决图片路径问题
                return "redirect:/client/registersuccess.jsp";
            }else{
                request.setAttribute("fail","新用户注册失败，请重试！");
                return "/client/register.jsp";
            }
        }else{
            request.setAttribute("check_error","校验码错误，请重新输入");
            return "/client/register.jsp";
            //加上redirect后域对象中的提示信息就不显示了；到前台讲图片位置改为绝对路径，保证图片显示；
        }
    }
    @RequestMapping("/activeUser")
    public String activeUser(String activeCode){
//        System.err.println("激活码："+activeCode);
        int rows = userService.activeUser(activeCode);
        if(rows > 0){
            return "redirect:/client/activesuccess.jsp";
        }else{
            return "redirect:/client/activefail.jsp";
        }
    }
    @RequestMapping("/findEmail")
    @ResponseBody
    public String findEmail(String email){//用户注册校验
//        System.err.println("邮箱："+email);
        User user = userService.findEmail(email);
//        System.err.println(user);
        if(user != null){
            return "EXIST";
        }else{
            return "OK";
        }
    }
    @RequestMapping("/findUsername")
    @ResponseBody
    public String findUsername(String username){
        User user = userService.findUsername(username);
        if(user != null){
            return "EXIST";
        }else{
            return "OK";
        }
    }
    @RequestMapping("/myAccount")
    public String myAccount(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("login_user");
        if(user != null){
            return "/client/myAccount.jsp";
        } else {
            //判断自动登录能否成功
            user = autoLogin(request);
            if(user != null){//能够自动登录
                //需要保存用户到session中,不然自动调用后调用session会出现问题。
                session.setAttribute("login_user",user);
                return "/client/myAccount.jsp";
            }
            return "/client/login.jsp";
        }
    }

    private User autoLogin(HttpServletRequest request) {
        String username = null;
        String password = null;
        Cookie[] cookies = request.getCookies();//获取cookie数组
        for(Cookie c : cookies){
            if("eShop_username".equals(c.getName())){
                username = c.getValue();
            }
            if("eShop_password".equals(c.getName())){
                password = c.getValue();
            }
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userService.findUserByLogin(user);//防止修改密码，以错误的密码登录；修改密码后查询不到用户，返回登录页面
        return user;
    }

    @RequestMapping("/login")
    public String login(User user, String remember, String autoLogin,HttpServletRequest request, HttpServletResponse response,HttpSession session){
        //System.err.println(remember);
        User login_user = userService.findUserByLogin(user);
        if(login_user != null){
            if(login_user.getState() == 1 ){//如果用户成功登陆保存用户到cookie
                if("1".equals(autoLogin)){
                    addCookie(autoLogin,user,request,response);
                } else if("1".equals(remember)){
                    //将值保存到cookie中
                    addCookie(autoLogin,user,request,response);
                }
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            }else{
                request.setAttribute("login_error","用户未激活，请激活后在重新登陆！！");
                return "/client/login.jsp";
            }
        }else{
            request.setAttribute("login_error","用户名或密码错误，请重新输入！");
            return "/client/login.jsp";
        }
    }

    /**
     * 添加cookie操作
     * @param autoLogin
     * @param user
     * @param request
     * @param response
     */
    public void addCookie(String autoLogin, User user, HttpServletRequest request, HttpServletResponse response){

        //记住用户名的代码

        //定义User对象
        Cookie cookie1 = new Cookie("eShop_username",user.getUsername());
        //设置保存时间
        cookie1.setMaxAge(60*60*24);
        //设置cookie路径
        cookie1.setPath(request.getContextPath()+"/");
        //响应
        response.addCookie(cookie1);

        //如果autoLogin为1，需要记住密码
        //判断是否需要保存密码
        if("1".equals(autoLogin)){
            Cookie cookie2 = new Cookie("eShop_password",user.getPassword());
            //设置保存时间
            cookie2.setMaxAge(60*60*24);
            //设置cookie路径
            cookie2.setPath(request.getContextPath()+"/");
            //响应
            response.addCookie(cookie2);
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
        //结束会话
        session.removeAttribute("login_user");
        //删除cookie,防止点击我的账户还可以登录。
        Cookie cookie1 = new Cookie("eShop_username",null);
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath()+"/");
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("eShop_password",null);
        cookie2.setMaxAge(0);
        cookie2.setPath(request.getContextPath()+"/");
        response.addCookie(cookie2);

        model.addAttribute("login_error","用户退出成功，请重新登录！");
        return "/client/login.jsp";
    }
    @RequestMapping("/modifyUser")
    public String modifyUser(User user,HttpSession session,Model model){
        //System.err.println(user);
        //从session获取用户id，按照id进行修改
        User login_user = (User) session.getAttribute("login_user");
        user.setId(login_user.getId());
        int rows = userService.modifyUser(user);
        if(rows > 0){
            model.addAttribute("login_error","用户修改成功，请重新登录！！");
            return "/client/login.jsp";
        }else{
            model.addAttribute("fail","用户信息修改失败，请重试");
            return "/client/modifyuserinfo.jsp";
        }
    }
    @RequestMapping("/removeOrderById")
    public String removeOrderById(String id,String flag){
        userService.removeOrderById(id,flag);
        return "redirect:/client/order/findOrderByUser";
    }
}
