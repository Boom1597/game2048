package com.example.firstgame2048;

import java.util.ArrayList;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

 public class Gamemap extends GridLayout{
	
	
	private Block[][] blocks=new Block[4][4];
	private List<Point> emptyPoints=new ArrayList<Point>();
	
	private boolean ismerge=false;
	private boolean ismove=false;
	
	
	public Gamemap(Context context) {
		// TODO Auto-generated constructor stub
		super(context,null);
		initview();
	}
	public Gamemap(Context context ,AttributeSet attrs)
	{
		// TODO Auto-generated constructor stub
		super(context, attrs);
		initview();
	}
	public Gamemap(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		Log.i("list", "lill");
		initview();
	}
	
	
	public void initview()
	{
		setColumnCount(4);
		setBackgroundColor(0xffbbada1);
		addBlock(180, 180);
		setOnTouchListener(new OnTouchListener() 
		{
			float endx,endy,startx,starty;
			
			@Override
			public boolean onTouch(View v, MotionEvent mov) {
				// TODO Auto-generated method stub
				//Toast.makeText(getContext(), "click", 10).show();
				switch (mov.getAction()) {
				case MotionEvent.ACTION_DOWN:
					//Log.i("hehe", "down");
					Toast.makeText(getContext(), "pushdown", 10).show();
					startx=mov.getX();
					starty=mov.getY();
					break;
				case MotionEvent.ACTION_UP:
					Toast.makeText(getContext(), "pushup", 10).show();
					endx=mov.getX()-startx;
					endy=mov.getY()-starty;
					
					if(Math.abs(endx)>Math.abs(endy)){
						//向左滑动
						if(endx<-5){
							swipeLeft();
							//System.out.println("left");
							Log.i("hehe", "left");
							//Toast.makeText(getContext(), "left", 10).show();
						//向右滑动
						}else if(endx>5){
							swipeRight();
							//System.out.println("right");
							Log.i("hehe", "right");
							//Toast.makeText(getContext(), "right", 10).show();
						}
					}else{
						//向上滑动
						if(endy<-5){
							swipeUp();
							//System.out.println("up");
							Log.i("hehe", "up");
							//Toast.makeText(getContext(), "up", 10).show();
						//向下滑动
						}else if(endy>5){
							swipeDown();
							//System.out.println("down");
							Log.i("hehe", "down");
							//Toast.makeText(getContext(), "down", 10).show();
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});
		startgame();
	}
	
	public void addBlock(int bwidth,int bheight)
	{
		Block b;
		for(int j=0;j<4;j++)
		{
			for(int i=0;i<4;i++)
			{
				b=new Block(getContext());
				b.setBnumber(0);
				b.setColor(b.getBnumber());
				addView(b, bwidth, bheight);
				blocks[i][j]=b;
			}
		}
		//Toast.makeText(getContext(), "true", 10).show();
	}
	
	public void addRandomNum()
	{
		Toast.makeText(getContext(), "true", 10).show();
		emptyPoints.clear();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(blocks[i][j].getBnumber()<=0){
					emptyPoints.add(new Point(i,j));
				}
			}
		}
    	Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
    	blocks[p.x][p.y].setBnumber(Math.random()>0.1?2:4);
    	blocks[p.x][p.y].setColor(blocks[p.x][p.y].getBnumber());
	}
	
	public void startgame()
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				blocks[i][j].setBnumber(0);
			}
		}
		addRandomNum();
		addRandomNum();
		//Toast.makeText(getContext(), "hehe", 10).show();
	}
	
	private void swipeLeft(){
    	boolean merge = false;
    	for (int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++){				
				for(int x1 = x+1; x1 < 4; x1++){
					//当前位置上的值不为0
					if(blocks[x1][y].getBnumber()>0){
						if(blocks[x][y].getBnumber()<=0){
							blocks[x][y].setBnumber(blocks[x1][y].getBnumber());
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x1][y].setBnumber(0);
							blocks[x1][y].setColor(blocks[x1][y].getBnumber());
							x--;
							merge = true;
						//左边卡片的值不为空且与当前值当等
						}else if(blocks[x][y].equals(blocks[x1][y])){
							blocks[x][y].setBnumber(blocks[x][y].getBnumber()*2);
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x1][y].setBnumber(0);
							blocks[x1][y].setColor(blocks[x1][y].getBnumber());
					        merge = true;
						}
						break;
					}
				}
			}			
		}

    	if(merge){
    		addRandomNum();
    		checkComplete();
    	}
    }

    
	private void swipeRight(){
    	boolean merge = false;
    	for (int y = 0; y < 4; y++) {
			for(int x = 3; x >= 0; x--){				
				for(int x1 = x-1; x1 >= 0; x1--){
					//当前位置上的值不为0
					if(blocks[x1][y].getBnumber()>0){
						//当前卡片左边的卡片为空，则将当前卡片的值传到左边
						if(blocks[x][y].getBnumber()<=0){
							blocks[x][y].setBnumber(blocks[x1][y].getBnumber());
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x1][y].setBnumber(0);
							blocks[x1][y].setColor(blocks[x1][y].getBnumber());
							x++;
							merge = true;
						//左边卡片的值不为空且与当前值当等
						}else if(blocks[x][y].equals(blocks[x1][y])){
							blocks[x][y].setBnumber(blocks[x][y].getBnumber()*2);
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x1][y].setBnumber(0);
							blocks[x1][y].setColor(blocks[x1][y].getBnumber());
					        merge = true;
						}
						break;
					}
				}
			}			
		}

    	if(merge){
    		addRandomNum();
    		checkComplete();
    	}
    }

    private void swipeUp(){
    	boolean merge = false;
    	for (int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++){
				for(int y1 = y+1; y1 < 4; y1++){
					if(blocks[x][y1].getBnumber()>0){
						//左边的卡片为空，则将当前卡片的值传到左边
						if(blocks[x][y].getBnumber()<=0){
							blocks[x][y].setBnumber(blocks[x][y1].getBnumber());
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x][y1].setBnumber(0);
							blocks[x][y1].setColor(blocks[x][y1].getBnumber());
							y--;
							merge = true;

						//左边卡片的值不为空且与当前值当等

						}else if(blocks[x][y].equals(blocks[x][y1])){
							blocks[x][y].setBnumber(blocks[x][y].getBnumber()*2);
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x][y1].setBnumber(0);
							blocks[x][y1].setColor(blocks[x][y1].getBnumber());
							//MainActivity.getMainActivity().addScore(blocks[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}			
		}
    	if(merge){
    		addRandomNum();
    		checkComplete();
    	}
    }
    
    private void swipeDown(){
    	boolean merge = false;
    	for (int x = 0; x < 4; x++) {
			for(int y = 3; y >= 0; y--){
				for(int y1 = y-1; y1 >= 0; y1--){
					if(blocks[x][y1].getBnumber()>0){
						//左边的卡片为空，则将当前卡片的值传到左边
						if(blocks[x][y].getBnumber()<=0){
							blocks[x][y].setBnumber(blocks[x][y1].getBnumber());
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x][y1].setBnumber(0);
							blocks[x][y1].setColor(blocks[x][y1].getBnumber());
							y++;
							merge = true;

						//左边卡片的值不为空且与当前值当等

						}else if(blocks[x][y].equals(blocks[x][y1])){
							blocks[x][y].setBnumber(blocks[x][y].getBnumber()*2);
							blocks[x][y].setColor(blocks[x][y].getBnumber());
							blocks[x][y1].setBnumber(0);
							blocks[x][y1].setColor(blocks[x][y1].getBnumber());
							//MainActivity.getMainActivity().addScore(blocks[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}			
		}
    	if(merge){
    		addRandomNum();
    		checkComplete();
    	}
    }
    
    

    private void checkComplete(){
    	boolean complete = true;
    	ALL:
    	for (int y = 0; y < 4; y++) {

			for(int x = 0; x < 4; x++){

				if(blocks[x][y].getBnumber()==0||

						(x>0&&blocks[x][y].equals(blocks[x-1][y]))||

						(x<3&&blocks[x][y].equals(blocks[x+1][y]))||

						(y>0&&blocks[x][y].equals(blocks[x][y-1]))||

						(y<3&&blocks[x][y].equals(blocks[x][y+1]))){
					complete = false;
					break ALL;
				}
			}
		}

    	if(complete){

    		new AlertDialog.Builder(getContext()).setTitle("2048").

    		setMessage("游戏结束").setPositiveButton("重来", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					startgame();
				}
			}).show();
    	}
    }

	/*@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		Log.i("hehe", "eeee");
		int blockwidth=(Math.min(w, h)-10)/4;
		addBlock(blockwidth, blockwidth);
		Toast.makeText(getContext(), "li"+blockwidth, 10).show();
		startgame();
		
	}*/
}
