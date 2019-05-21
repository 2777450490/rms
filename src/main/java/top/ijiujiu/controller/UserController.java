package top.ijiujiu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.ijiujiu.entity.User;
import top.ijiujiu.service.IUserService;

@Api("UserController|用户操作相关控制器类")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 新增用户
     * @param user
     * @return
     */
    @ApiOperation(value="新增用户", notes="增加新用户")
    @ApiImplicitParam(name = "user", value = "用户信息" ,required = true ,dataType = "User")
    @PostMapping("addUser")
    public User addOne(@RequestBody User user){
        return this.userService.add(user);
    }

}
