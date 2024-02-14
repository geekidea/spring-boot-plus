package io.geekidea.boot.common.constant;

/**
 * @author geekidea
 * @date 2022/7/3
 **/
public interface AopConstant {

    /**
     * 项目下所有controller切点
     */
    String PROJECT_POINTCUT = "execution(public * " + CommonConstant.PACKAGE_NAME + "..*.controller..*.*(..))";

    /**
     * APP切点
     */
    String APP_POINTCUT = "execution(public * " + CommonConstant.PACKAGE_NAME + "..*.controller..App*Controller*.*(..))";

    /**
     * 管理后台切点
     */
    String ADMIN_POINTCUT = "execution(public * " + CommonConstant.PACKAGE_NAME + "..*.controller..*.*(..)) && !" + APP_POINTCUT;

    /**
     * 公共包切点
     */
    String COMMON_POINTCUT = "execution(public * " + CommonConstant.COMMON_PACKAGE_NAME + "..*.controller..*.*(..))";

}
