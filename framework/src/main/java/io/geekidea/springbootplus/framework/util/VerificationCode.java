/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author geekidea
 * @date 2018-11-08
 */
public class VerificationCode {
    /**
     * 验证码图片的长
     **/
    private int weight = 110;
    /**
     * 验证码图片的高
     */
    private int height = 38;
    /**
     * 用来保存验证码的文本内容
     **/
    private String text;
    /**
     * 获取随机数对象
     **/
    private Random r = new Random();
    /**
     * 字体数组
     **/
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    /**
     * 验证码数组
     **/
    private String codes = "23456789acdefghjkmnopqrstuvwxyzACDEFGHJKMNPQRSTUVWXYZ";
    /**
     * 生成的验证码的个数
     **/
    private int codeNum = 4;
    /**
     * 255
     **/
    private static int TWO_FIVE_FIVE = 255;

    /**
     * 获取随机的颜色
     */
    private Color randomColor() {
        //这里为什么是150，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int r = this.r.nextInt(150);
        int g = this.r.nextInt(150);
        int b = this.r.nextInt(150);
        //返回一个随机颜色
        return new Color(r, g, b);
    }

    /**
     * 获取随机字体
     */
    private Font randomFont() {
        //获取随机的字体
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int style = r.nextInt(4);
        //随机获取字体的大小
        int size = r.nextInt(5) + 24;
        //返回一个随机的字体
        return new Font(fontName, style, size);
    }

    /**
     * 获取随机字符
     */
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     * 画干扰线，验证码干扰线用来防止计算机解析图片
     */
    private void drawLine(BufferedImage image) {
        int num = 155;
        //定义干扰线的数量
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x = r.nextInt(weight);
            int y = r.nextInt(height);
            int xl = r.nextInt(weight);
            int yl = r.nextInt(height);
            g.setColor(getRandColor(160, 200));
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    /**
     * 创建图片的方法
     */
    private BufferedImage createImage() {
        //创建图片缓冲区
        BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设定图像背景色(因为是做背景，所以偏淡)
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, weight, height);
        //返回一个图片
        return image;
    }

    /**
     * 获取验证码图片的方法
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        drawLine(image);
        //画四个字符即可
        for (int i = 0; i < codeNum; i++) {
            //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
            String s = randomChar() + "";
            //添加到StringBuilder里面
            sb.append(s);
            //定义字符的x坐标
            float x = i * 1.0F * weight / 4;
            //设置字体，随机
            g.setFont(randomFont());
            //设置颜色，随机
            g.setColor(randomColor());
            g.drawString(s, x, height - 5);
        }
        this.text = sb.toString();
        return image;
    }

    /**
     * 给定范围获得随机颜色
     */
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > TWO_FIVE_FIVE) {
            fc = TWO_FIVE_FIVE;
        }

        if (bc > TWO_FIVE_FIVE) {
            bc = TWO_FIVE_FIVE;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 获取验证码文本的方法
     */
    public String getText() {
        return text;
    }
}
