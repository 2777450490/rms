package top.ijiujiu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ijiujiu.entity.User;
import top.ijiujiu.service.IUserService;

@Api("UserController|用户操作相关控制器类")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="用户登录", notes="根据loginName、password登录系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录用户名" ,required = true ,dataType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码" ,required = true ,dataType = "String")
    })
    @PostMapping("find")
    public User find(@RequestParam(name = "loginName" ,required = true) String loginName,
                      @RequestParam(name = "password" ,required = true) String password){

        return null;
    }



}
