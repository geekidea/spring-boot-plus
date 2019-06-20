/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.test;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

/**
 * <p>
 *
 * </p>
 * @auth geekidea
 * @date 2019-05-23
 **/
public class TestUesrAgent {

    public static void main(String[] args) {
        String u;
        u = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36";
//        u = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.51 Safari/537.36";
        // ipad
//        u = "Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1";

        // 努比亚 微信
        //        u = "Mozilla/5.0 (Linux; Android 7.1.1; NX563J Build/NMF26X) AppleWebKit/537.36(KHTML,like Gecko)Version/4.0 Chrome/57.0.2987.108 Mobile Safari/537.36";

        // 华为手机谷歌浏览器
//        u = "Mozilla/5.0 (Linux; Android 8.1.0; INE-TL00) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Mobile Safari/537.36";

        // 华为手机微信
//        u = "Mozilla/5.0 (Linux; Android 8.1.0; INE-TL00 Build/HUAWEIINE-TL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.126 MQQBrowser/6.2 TBS/044704 Mobile Safari/537.36 MMWEBID/9017 MicroMessenger/7.0.4.1420(0x2700043B) Process/tools NetType/WIFI Language/zh_CN";

        UserAgent userAgent = UserAgentUtil.parse(u);
        System.out.println("userAgent = " + userAgent);
        System.out.println(userAgent.getBrowser().getName());

        System.out.println(userAgent.getEngine().getName());
        System.out.println(userAgent.getEngineVersion());

        System.out.println(userAgent.getOs().getName());

        System.out.println(userAgent.getPlatform().getName());

        System.out.println(userAgent.getVersion());

        System.out.println(userAgent.isMobile());

    }
}
