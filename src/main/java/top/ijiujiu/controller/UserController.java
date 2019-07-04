package top.ijiujiu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.ijiujiu.entity.User;
import top.ijiujiu.service.IUserService;
import top.ijiujiu.utils.ResultUtil;
import top.ijiujiu.entity.WeChatResponse;

import java.util.HashMap;
import java.util.Map;

@Api(value = "UserController|用户操作相关控制器类",tags = {"UserController|用户操作相关控制器类"})
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${weChat.app.appId}")
    private String appId;

    @Value("${weChat.app.appSecret}")
    private String appSecret;

    @Value("${weChat.app.sessionUrl}")
    private String sessionUrl;


    @ApiOperation(value="获取微信openId", notes="获取微信openId")
    @PostMapping("weChatLogin")
    public ResultUtil<User> weChatLogin(@RequestParam("code") String code){
        LOGGER.info("Code:{}", code);
        ResultUtil<User> result = new ResultUtil<>();
        if (StringUtils.isEmpty(code)){
            return result.failed();
        }
        Map<String, String> params = new HashMap<>();
        params.put("appId", this.appId);
        params.put("secret", this.appSecret);
        params.put("jsCode", code);
        WeChatResponse weChatResponse = this.restTemplate.getForObject(sessionUrl, WeChatResponse.class, params);
        LOGGER.info("WeChatResponse:{}", weChatResponse);
        return null;
    }




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
