/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ydh.weile.zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.ydh.weile.merchant.R;
import com.ydh.weile.zxing.camera.CameraManager;

import java.util.Collection;
import java.util.HashSet;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 * 
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {

	private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192,
			128, 64 };
	 /** 
     * 刷新界面的时间 
     */  
	private static final long ANIMATION_DELAY = 100L;
	private static final int OPAQUE = 0xFF;

	private final Paint paint;
	private Bitmap resultBitmap;
	private final int maskColor;
	private final int resultColor;
	private final int frameColor;
	private final int laserColor;
	private final int resultPointColor;
	private int scannerAlpha;
	private Collection<ResultPoint> possibleResultPoints;
	private Collection<ResultPoint> lastPossibleResultPoints;

	private Bitmap laserBitmap;
	private int laserCurrentPosion;
	
	
	// This constructor is used when the class is built from an XML resource.
	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// Initialize these once for performance rather than calling them every
		// time in onDraw().
		paint = new Paint();
		Resources resources = getResources();
		//控件遮罩颜色
		maskColor = resources.getColor(R.color.viewfinder_mask);
		//结果区域颜色
		resultColor = resources.getColor(R.color.result_view);
		//四边边框的颜色
		frameColor = resources.getColor(R.color.viewfinder_frame);
		//扫描器颜色
		laserColor = resources.getColor(R.color.viewfinder_laser);
		//结果点颜色
		resultPointColor = resources.getColor(R.color.possible_result_points);
		scannerAlpha = 0;
		possibleResultPoints = new HashSet<ResultPoint>(5);
		
		laserBitmap = BitmapFactory.decodeResource(resources, R.drawable.scanning_light);
		
	}

	@Override
	public void onDraw(Canvas canvas) {
		Rect frame = CameraManager.get().getFramingRect();
		if (frame == null) {
			return;
		}
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		 //画出扫描框外面的阴影部分，共四个部分，扫描框的上面到屏幕上面，扫描框的下面到屏幕下面  
        //扫描框的左边面到屏幕左边，扫描框的右边到屏幕右边  
		paint.setColor(resultBitmap != null ? resultColor : maskColor);
		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
				paint);
		canvas.drawRect(0, frame.bottom + 1, width, height, paint);
		
		
		

		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else {
			int linewidht = 10;
			 //画扫描框边上的角，总共8个部分  
			paint.setColor(frameColor);
			//左上角
			canvas.drawRect(frame.left - 15, frame.top -15 ,
					(linewidht + frame.left) -15, (50 + frame.top) - 15, paint);
			canvas.drawRect( frame.left - 15 , frame.top - 15,
					 (50 + frame.left) - 15 ,  (linewidht + frame.top) - 15, paint);
			
			//右上角
			canvas.drawRect(15 + ((0 - linewidht) + frame.right),
					-15 + frame.top, 15 + (1 + frame.right),
					-15 + (50 + frame.top), paint);
			canvas.drawRect(15 + (-50 + frame.right), -15 + frame.top, 15
					+ frame.right, -15 + (linewidht + frame.top), paint);
			
			//左下角
			canvas.drawRect(-15 + frame.left, 15 + (-49 + frame.bottom),
					-15 + (linewidht + frame.left), 15 + (1 + frame.bottom),
					paint);
			canvas.drawRect(-15 + frame.left, 15
					+ ((0 - linewidht) + frame.bottom), -15 + (50 + frame.left),
					15 + (1 + frame.bottom), paint);
			
			//右下角
			canvas.drawRect(15 + ((0 - linewidht) + frame.right), +15
					+ (-49 + frame.bottom), 15 + (1 + frame.right), +15
					+ (1 + frame.bottom), paint);
			canvas.drawRect(15 + (-50 + frame.right), 15
					+ ((0 - linewidht) + frame.bottom), 15 + frame.right, 15
					+ (linewidht - (linewidht - 1) + frame.bottom), paint);

			
			
			 //绘制中间的线,每次刷新界面  
			paint.setColor(laserColor);
			paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
			scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
			int ll = frame.bottom - frame.top ;
			if(laserCurrentPosion == 0){
				laserCurrentPosion = frame.top;
			}
			laserCurrentPosion  += ll/35 ;
			if(laserCurrentPosion > frame.bottom){
				laserCurrentPosion = frame.top;
			}
			
			Rect lineRect = new Rect();  
            lineRect.left = frame.left;  
            lineRect.right = frame.right;  
            lineRect.top = laserCurrentPosion;  
            lineRect.bottom = laserCurrentPosion + 18; 
			canvas.drawBitmap(laserBitmap, null , lineRect , paint);
			
			
			
			
			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty()) {
				lastPossibleResultPoints = null;
			} else {
				possibleResultPoints = new HashSet<ResultPoint>(5);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 6.0f, paint);
				}
			}
		/*	if (currentLast != null) {
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 3.0f, paint);
				}
			}*/

			// Request another update at the animation interval, but only
			// repaint the laser line,
			// not the entire viewfinder mask.
			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
					frame.right, frame.bottom);
		}
	}

	public void drawViewfinder() {
		resultBitmap = null;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point) {
		possibleResultPoints.add(point);
	}

}
