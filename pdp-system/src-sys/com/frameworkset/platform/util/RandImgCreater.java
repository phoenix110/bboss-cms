/*
 *  Copyright 2008 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.frameworkset.platform.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Title: RandImgCreater.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * bboss workgroup
 * </p>
 * <p>
 * Copyright (c) 2008
 * </p>
 * 
 * @Date 2010-11-13
 * @author biaoping.yin
 * @version 1.0
 */
public class RandImgCreater {
	public static final String[] CODE_LIST = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"
													,"R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j",
													"k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1",
													"2","3","4","5","6","7","8","9","0"};
	private HttpServletResponse response = null;
	private static final int HEIGHT = 36;
	public static final int FONT_NUM = 4;
	private int width = 0;
	private int iNum = FONT_NUM;
	private String[] codeList ;
	private boolean drawBgFlag = false;

	private int rBg = 0;
	private int gBg = 0;
	private int bBg = 0;

	public RandImgCreater(HttpServletResponse response) {
		this.response = response;
		this.width = 20 * FONT_NUM + 100;
		this.iNum = FONT_NUM;
		this.codeList = CODE_LIST;
	}

	public RandImgCreater(HttpServletResponse response, int iNum,
			String[] codeList) {
		this.response = response;
		if(iNum > 0)
			this.iNum = iNum;
		this.width = 20 * this.iNum + 100;
		
		if(codeList != null)
			this.codeList = codeList;
	}

	public String createRandImage() {
		int height = 65;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		
		Random random = new Random();

		if (drawBgFlag) {
			g.setColor(new Color(rBg, gBg, bBg));
			g.fillRect(0, 0, width, height);
		} else {
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			for (int i = 0; i < 155; i++) {
				g.setColor(getRandColor(140, 200));
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(20);
				int yl = random.nextInt(20);
				g.drawLine(x, y, x + xl, y + yl);
			}
		}

		g.setFont(new Font("Times New Roman", Font.PLAIN, 60));

		String sRand = "";
		int interval = 8;
		int total = 0;
		for (int i = 0; i < iNum; i++) {
			int rand = random.nextInt(codeList.length);
			String strRand = codeList[rand];
			sRand += strRand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			total = total +interval + i*8;
			
			g.drawString(strRand, 18 * i + total, 50);
//			interval = interval + i * interval;
			
		}
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {

		}

		return sRand;
	}

	public void setBgColor(int r, int g, int b) {
		drawBgFlag = true;
		this.rBg = r;
		this.gBg = g;
		this.bBg = b;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
