package com.example.firstgame2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Block extends FrameLayout{
	private int bnumber;
	private String bnumstr;
	private TextView tv;
	
	
	public Block(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		tv=new TextView(context);
		tv.setTextSize(30.0f);
		tv.setBackgroundColor(0x33ffffff);
		tv.setGravity(Gravity.CENTER);
		//tv.setText(bnumstr);
		LayoutParams lp=new LayoutParams(-1,-1);
		lp.setMargins(10, 10, 0, 0);
		addView(tv,lp);
		//Toast.makeText(getContext(), "dada", 10).show();
	}
	
	
	public int getBnumber() {
		return bnumber;
	}
	public void setBnumber(int bnumber) {
		this.bnumber = bnumber;
		bnumstr=bnumber+"";
		if(bnumber!=0)
		tv.setText(bnumstr);
		else tv.setText("");
	}
	
	public boolean equals(Block o)
	{
		return getBnumber()==o.getBnumber();
	}
	
	
	protected void setColor(int num) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		//String bgColor="";
		switch (num) {
		case 0:
			tv.setBackgroundColor(0x33ffffff);
			break;
		case 2:
			tv.setBackgroundColor(0xffFFF68F);
			break;
		case 4:
			tv.setBackgroundColor(0xffFFEC8B);
			break;
		case 8:
			tv.setBackgroundColor(0xffFFD700);
			break;
		case 16:
			tv.setBackgroundColor(0xffFFC125);
			break;
		case 32:
			tv.setBackgroundColor(0xffFF890F);
			break;
		case 64:
			tv.setBackgroundColor(0xffFFA500);
			break;
		case 128:
			tv.setBackgroundColor(0xffFF8C00);
			break;
		case 256:
			tv.setBackgroundColor(0xffFF7F24);
			break;
		case 512:
			tv.setBackgroundColor(0xffFF4500);
			break;
		case 1024:
			tv.setBackgroundColor(0xffFF0000);
			break;
		case 2048:
			tv.setBackgroundColor(0xff7FF00);
			break;
		default:
			tv.setBackgroundColor(0x33ffffff);
			break;
		}
		
	}
	
	
}
