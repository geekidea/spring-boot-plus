package io.geekidea.springbootplus.framework.shiro.convert;

import io.geekidea.springbootplus.framework.shiro.jwt.JwtToken;
import io.geekidea.springbootplus.framework.shiro.vo.JwtTokenRedisVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-19T20:07:51+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class ShiroMapstructConvertImpl implements ShiroMapstructConvert {

    @Override
    public JwtTokenRedisVo jwtTokenToJwtTokenRedisVo(JwtToken jwtToken) {
        if ( jwtToken == null ) {
            return null;
        }

        JwtTokenRedisVo jwtTokenRedisVo = new JwtTokenRedisVo();

        jwtTokenRedisVo.setHost( jwtToken.getHost() );
        jwtTokenRedisVo.setUsername( jwtToken.getUsername() );
        jwtTokenRedisVo.setSalt( jwtToken.getSalt() );
        jwtTokenRedisVo.setToken( jwtToken.getToken() );
        jwtTokenRedisVo.setCreateDate( jwtToken.getCreateDate() );
        jwtTokenRedisVo.setExpireSecond( jwtToken.getExpireSecond() );
        jwtTokenRedisVo.setExpireDate( jwtToken.getExpireDate() );

        return jwtTokenRedisVo;
    }
}
