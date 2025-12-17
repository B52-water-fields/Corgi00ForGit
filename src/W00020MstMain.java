import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class W00020MstMain{
	static int SetX;
	static int SetY;
	public static void MstMain(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,750,"Corgi00マスタメニュー","");
		
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		//荷主設定パネル
		JPanel PN_AboutClient = B00110FrameParts.JPanelSet(10,40,740,235,"White");
		JLabel LB_AboutClient = B00110FrameParts.JLabelSet(10,0,150,20,"荷主毎設定",11,0);
		PN_AboutClient.add(LB_AboutClient);
		
		//配車設定パネル
		JPanel PN_AboutHaisya = B00110FrameParts.JPanelSet(10,280,740,175,"White");
		JLabel LB_AboutHaisya = B00110FrameParts.JLabelSet(10,0,150,20,"配車設定",11,0);
		PN_AboutHaisya.add(LB_AboutHaisya);
		
		//全体設定パネル
		JPanel PN_AboutALL = B00110FrameParts.JPanelSet(10,460,740,200,"White");
		JLabel LB_AboutALL = B00110FrameParts.JLabelSet(10,0,150,20,"全体設定",11,0);
		PN_AboutALL.add(LB_AboutALL);

		//倉庫・事業所
		JButton WhMst = B00110FrameParts.BtnSet(20,25,130,20,"倉庫・事業所",11);
		PN_AboutALL.add(WhMst);
		
		//運送会社
		JButton ShippingCompanyMst = B00110FrameParts.BtnSet(160,25,130,20,"運送会社",11);
		PN_AboutALL.add(ShippingCompanyMst);
		
		//ユーザー
		JButton UserMst = B00110FrameParts.BtnSet(20,50,130,20,"ユーザー・乗務員",11);
		PN_AboutALL.add(UserMst);
		
		//車輛
		JButton CarMst = B00110FrameParts.BtnSet(160,50,130,20,"車輛",11);
		PN_AboutALL.add(CarMst);
		
		//荷主グループ
		JButton ClGpMst = B00110FrameParts.BtnSet(20,75,130,20,"荷主グループ",11);
		PN_AboutALL.add(ClGpMst);
		
		//荷主
		JButton ClMst = B00110FrameParts.BtnSet(160,75,130,20,"荷主",11);
		PN_AboutALL.add(ClMst);
		
		//郵便番号
		JButton PostMst = B00110FrameParts.BtnSet(600,175,130,20,"郵便番号",11);
		PN_AboutALL.add(PostMst);

		main_fm.add(PN_AboutClient);
		main_fm.add(PN_AboutHaisya);
		main_fm.add(PN_AboutALL);
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.setVisible(true);
		//倉庫・事業所
		WhMst.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00010WhMstSearch.WhMstSearch(0, 0);
			}
		});
		
		//運送会社
		ShippingCompanyMst.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00040ShippingCompanyMstSearch.ShippingCompanyMstSearch(0, 0);
			}
		});
		
		//ユーザー
		UserMst.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00030UserMstSearch.UserMstSearch(0, 0);
			}
		});
		
		//車輛
		CarMst.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00035CarMstSearch.CarMstSearch(0,0);
			}
		});
		
		//荷主グループ
		ClGpMst.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00020ClGlpMstSearch.ClGlpMstSearch(0,0);
			}
		});
		//荷主
		ClMst.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00025ClMstSearch.ClMstSearch(0,0) ;
			}
		});
		
		//郵便番号
		PostMst.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM10010PostMstSearch.PostMstSearch(0, 0);
			}
		});

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				W00010MainMenu.MainMenu(0, 0);
			}
		});
	}
}