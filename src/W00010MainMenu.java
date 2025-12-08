import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class W00010MainMenu{
	static int SetX;
	static int SetY;
	public static void MainMenu(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,700,750,"Corgiメインメニュー","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.setVisible(true);
		
		//業務メニューボタン
		JButton Work_btn=B00110FrameParts.BtnSet(    10,40,120,20,"業務メニュー",11);
		main_fm.add(Work_btn);
		//マスタボタン
		JButton Mst_btn=B00110FrameParts.BtnSet(    150,40,120,20,"マスタメニュー",11);
		main_fm.add(Mst_btn);
		//荷主選択ボタン
		JButton ClSelect_btn=B00110FrameParts.BtnSet(290,40,120,20,"荷主選択",11);
		main_fm.add(ClSelect_btn);
		
		//業務メニューボタン押下時の挙動
		Work_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				W00030WorkMain.WorkMain(0,0);
			}
		});
		
		//マスタボタン押下時の挙動
		Mst_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				W00020MstMain.MstMain(0,0);
			}
		});
		
		//荷主選択ボタン押下時の挙動
		ClSelect_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				
				A00000Main.ClSelect();
			}
		});

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00000Main.EndPg();
			}
		});
	}
}