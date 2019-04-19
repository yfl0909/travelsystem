package cn.yfl.travel.web.servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int width = 80;
		int height = 26;
		int num = 4;

		//1、创建一对象 在内存中图片（验证码图片对象）
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		//2、美化图片

		//2.1 填充背景色
		//画笔对象
		Graphics g = image.createGraphics();
		//设置画笔颜色
		g.setColor(Color.PINK);
		g.fillRect(0, 0, width, height);

		//2.2 画边框
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width-1, height-1);

		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
		//生成随机角标
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			int index = ran.nextInt(str.length());
			//获取字符
			//随机字符
			char ch = str.charAt(index);
			sb.append(ch);
			//2.3 写验证码
			Font font = new Font("宋体", 0, 26);
			g.setFont(font);
			g.drawString(ch+"", width/5*i+8, height-4);
		}
		//将验证码放入session
		request.getSession().setAttribute("checkCode_session", sb.toString());

		//2.4画干扰线
		g.setColor(Color.GREEN);
		for (int i = 0; i < 6; i++) {
			//随机生成坐标点
			int x1 = ran.nextInt(width);
			int x2 = ran.nextInt(width);

			int y1 = ran.nextInt(height);
			int y2 = ran.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}

		//3、将图片输出到页面
		ImageIO.write(image, "jpg", response.getOutputStream());

	}
}



