package io.geekidea.boot.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.user.dto.UserDto;
import io.geekidea.boot.user.entity.User;
import io.geekidea.boot.user.query.UserAppQuery;
import io.geekidea.boot.user.query.UserQuery;
import io.geekidea.boot.user.vo.UserAppVo;
import io.geekidea.boot.user.vo.UserVo;


/**
 * 用户信息 服务接口
 *
 * @author geekidea
 * @since 2023-11-25
 */
public interface UserService extends IService<User> {

    /**
     * 根据微信openid获取用户
     *
     * @param openid
     * @return
     * @throws Exception
     */
    User getUserByOpenid(String openid) throws Exception;

    /**
     * 根据账号获取用户
     * @param username
     * @return
     * @throws Exception
     */
    User getUserByUsername(String username) throws Exception;

    /**
     * 添加用户信息
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    boolean addUser(UserDto userDto) throws Exception;

    /**
     * 修改用户信息
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    boolean updateUser(UserDto userDto) throws Exception;

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteUser(Long id) throws Exception;

    /**
     * 用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserVo getUserById(Long id) throws Exception;

    /**
     * 用户信息分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<UserVo> getUserPage(UserQuery query) throws Exception;

    /**
     * App用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserAppVo getAppUserById(Long id) throws Exception;

    /**
     * App用户信息分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<UserAppVo> getAppUserPage(UserAppQuery query) throws Exception;

}
