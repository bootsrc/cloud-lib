package io.github.bootsrc.cloud.auth.controller;

import io.github.bootsrc.cloud.api.auth.dto.LoginResult;
import io.github.bootsrc.cloud.api.auth.util.JWTUtil;
import io.github.bootsrc.cloud.api.common.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class AuthHomeController {

    @Value("${auth.secret-Key}")
    private String secretKey;

    @RequestMapping("login")
    public CommonResult<LoginResult> login(String mobile, String password) {
        CommonResult<LoginResult> result = new CommonResult<>();
        result.setCode(0);
        result.setMessage("SUCCESS");
        Long userId = 1L;
        String username = "Demo User";

        String jwt = JWTUtil.generateToken(String.valueOf(userId), username, secretKey);
        LoginResult loginResult = new LoginResult();
        loginResult.setAccessToken(jwt);
        loginResult.setRefreshToken("NO REFRESH TOKEN");
        result.setData(loginResult);
        return result;
    }
}
