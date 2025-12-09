import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WM10011PostMstRenewAndCreate{
	static int SetX;
	static int SetY;
	public static void PostMstRenewAndCreate(int x,int y,String TgtPost) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,250,"Corgi00郵便番号登録・修正","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.BtnSet(100,170,100,20,"登録",11);
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_Post  = B00110FrameParts.JLabelSet(	           	  0, 40,100,20,"郵便番号:"  ,11,1);
		JLabel LB_Prefecturs   = B00110FrameParts.JLabelSet(	  	  0, 65,100,20,"県:"        ,11,1);
		JLabel LB_Munic01   = B00110FrameParts.JLabelSet(	     	  0, 90,100,20,"市区町村:"  ,11,1);
		JLabel LB_Munic02   = B00110FrameParts.JLabelSet(			  0,115,100,20,"町丁目:"    ,11,1);
		JLabel LB_MuniciparytyCd   = B00110FrameParts.JLabelSet(	  0,140,100,20,"市区町村CD:",11,1);
		
		final JTextField TB_Post  = B00110FrameParts.JTextFieldSet(	     	  	100, 40,100,20,"",11,0);
		final JTextField TB_Prefecturs   = B00110FrameParts.JTextFieldSet( 	 	100, 65,100,20,"",11,0);
		final JTextField TB_Munic01   = B00110FrameParts.JTextFieldSet(		   	100, 90,200,20,"",11,0);
		final JTextField TB_Munic02   = B00110FrameParts.JTextFieldSet(			100,115,200,20,"",11,0);
		final JTextField TB_MuniciparytyCd   = B00110FrameParts.JTextFieldSet(	100,140,100,20,"",11,0);
		
		if(null==TgtPost) {TgtPost="";}
		TgtPost = B00020ToolsTextControl.num_only_String(TgtPost);
		TB_Post.setText(TgtPost);
		
		if(!"".equals(TgtPost)) {
			ArrayList SearchPOST = new ArrayList();
			ArrayList SearchAdd = new ArrayList();
			boolean AllSearch = false;
			
			SearchPOST.add(TgtPost);
			
			Object[][] PostRt = M10010PostMstRt.PostRt(SearchPOST,SearchAdd,AllSearch);
			
			if(0<PostRt.length) {
				TB_Post.setText(          ""+PostRt[0][0]);
				TB_Prefecturs.setText(    ""+PostRt[0][1]);
				TB_Munic01.setText(       ""+PostRt[0][2]);
				TB_Munic02.setText(       ""+PostRt[0][3]);
				TB_MuniciparytyCd.setText(""+PostRt[0][4]);
			}
		}
		
		main_fm.add(LB_Post);
		main_fm.add(LB_Prefecturs);
		main_fm.add(LB_Munic01);
		main_fm.add(LB_Munic02);
		main_fm.add(LB_MuniciparytyCd);
		
		main_fm.add(TB_Post);
		main_fm.add(TB_Prefecturs);
		main_fm.add(TB_Munic01);
		main_fm.add(TB_Munic02);
		main_fm.add(TB_MuniciparytyCd);
		
		TB_Prefecturs.requestFocusInWindow();
		
		main_fm.setVisible(true);
		
		//TB_Post(郵便番号)フォーカス消失時の挙動
		TB_Post.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				String GetPost = TB_Post.getText();	if(null==GetPost) {GetPost="";}
				if(!"".equals(GetPost)) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					
					PostMstRenewAndCreate(0,0,GetPost);
				}
			}
		});
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetPost = TB_Post.getText();						if(null==GetPost) {GetPost="";}
				String GetPrefecturs = TB_Prefecturs.getText();			if(null==GetPrefecturs) {GetPrefecturs="";}
				String GetMunic01 = TB_Munic01.getText();				if(null==GetMunic01) {GetMunic01="";}
				String GetMunic02 = TB_Munic02.getText();				if(null==GetMunic02) {GetMunic02="";}
				String GetMuniciparytyCd = TB_MuniciparytyCd.getText();	if(null==GetMuniciparytyCd) {GetMuniciparytyCd="";}
				
				GetPost = B00020ToolsTextControl.num_only_String(GetPost);
				GetMuniciparytyCd = B00020ToolsTextControl.num_only_String(GetMuniciparytyCd);
				boolean KickFg = false;
				
				if(!"".equals(GetPost)) {KickFg = true;}
				
				if(KickFg) {
					String tgt_table = "M0010_PostMst";
					String[][] field_name = new String[5][3];
					String[][] entry_data = new String[1][5];
					String[] judg_field = new String[1];
					String[][] judg_data = new String[1][1];
					String TgtDB = "POST";
					int non_msg_fg = 1;
					
					judg_field[0] = "POST";					//郵便番号
					
					field_name[0][0] = "POST";				//郵便番号
					field_name[1][0] = "PREFECTURES";		//県
					field_name[2][0] = "MUNICI01";			//市区町村
					field_name[3][0] = "MUNICI02";			//町丁目
					field_name[4][0] = "MUNICIPALITY_CD";	//市区町村CD

					field_name[0][1] = "1";	//郵便番号
					field_name[1][1] = "1";	//県
					field_name[2][1] = "1";	//市区町村
					field_name[3][1] = "1";	//町丁目
					field_name[4][1] = "1";	//市区町村CD

					field_name[0][2] = "1";	//郵便番号
					field_name[1][2] = "1";	//県
					field_name[2][2] = "1";	//市区町村
					field_name[3][2] = "1";	//町丁目
					field_name[4][2] = "1";	//市区町村CD
					
					judg_data[0][0] = B00020ToolsTextControl.Trim(GetPost);	//郵便番号
					
					entry_data[0][0] = B00020ToolsTextControl.Trim(GetPost);			//郵便番号
					entry_data[0][1] = B00020ToolsTextControl.Trim(GetPrefecturs);		//県
					entry_data[0][2] = B00020ToolsTextControl.Trim(GetMunic01);			//市区町村
					entry_data[0][3] = B00020ToolsTextControl.Trim(GetMunic02);			//町丁目
					entry_data[0][4] = B00020ToolsTextControl.Trim(GetMuniciparytyCd);	//市区町村CD
					
					A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					
					PostMstRenewAndCreate(0,0,GetPost);
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM10010PostMstSearch.PostMstSearch(0, 0);
			}
		});
	}
}