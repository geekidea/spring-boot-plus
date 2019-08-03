rem Copyright 2019-2029 geekidea(https://github.com/geekidea)
rem
rem Licensed under the Apache License, Version 2.0 (the "License");
rem you may not use this file except in compliance with the License.
rem You may obtain a copy of the License at
rem
rem     http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS,
rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
rem See the License for the specific language governing permissions and
rem limitations under the License.

rem ======================================================================
rem  windows启动脚本
rem
rem  author: geekidea
rem  date: 2018-12-2
rem ======================================================================

rem startup jar
java -jar ../lib/spring-boot-plus.jar --spring.config.location=../config/ --springbootplus.isEnableAnsi=false

pause