package com.ydh.weile.entity;

import android.graphics.Bitmap;

import java.io.Serializable;


public class CaptureEntity implements Serializable{
    
	private String result;
	private Bitmap bitmap;
	
	
	
	public CaptureEntity(String result, Bitmap bitmap) {
		super();
		this.result = result;
		this.bitmap = bitmap;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
	
	
}
