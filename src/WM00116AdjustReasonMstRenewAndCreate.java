import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WM00116AdjustReasonMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void AdjustReasonMstRenewAndCreate(int x,int y,String TgtClCd,String TgtWhCd,String TgtAdjustReasonCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==TgtClCd			) {TgtClCd				= "";}
		if(null==TgtWhCd			) {TgtWhCd				= "";}
		if("".equals(TgtClCd)		) {TgtClCd				= A00000Main.ClCd;}
		if("".equals(TgtWhCd)		) {TgtWhCd				= A00000Main.ClWh;}
		if(null==TgtAdjustReasonCd	) {TgtAdjustReasonCd	= "";}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,400,"Corgi00在庫調整理由マスタ登録・修正","");
		JLabel userinfo 	= B00110FrameParts.UserInfo();
		JButton exit_btn 	= B00110FrameParts.ExitBtn();
		JButton entry_btn 	= B00110FrameParts.EntryBtn();
		
		JLabel LB_ClCd				= B00110FrameParts.JLabelSet(	  0, 50,130,20,"荷主コード:"		,11,1);
		JLabel LB_WhCd				= B00110FrameParts.JLabelSet(	  0, 75,130,20,"倉庫コード:"		,11,1);
		JLabel LB_AdjustReasonCd	= B00110FrameParts.JLabelSet(	  0,100,130,20,"調整理由コード:"	,11,1);
		JLabel LB_AdjustReasonName	= B00110FrameParts.JLabelSet(	  0,125,130,20,"調整理由名:"		,11,1);
		JLabel LB_EntryDate			= B00110FrameParts.JLabelSet(	  0,150,130,20,"登録日:"			,11,1);
		JLabel LB_UpdateDate		= B00110FrameParts.JLabelSet(	  0,175,130,20,"更新日:"			,11,1);
		JLabel LB_EntryUser			= B00110FrameParts.JLabelSet(	  0,200,130,20,"登録者:"			,11,1);
		JLabel LB_UpdateUser		= B00110FrameParts.JLabelSet(	  0,225,130,20,"更新者:"			,11,1);
		
		final JComboBox   TB_ClWh				= B00110FrameParts.JComboBoxSet(	130, 50,250,20,B00100DefaultVariable.WhList[0],11);		//倉庫コード
		final JComboBox   TB_ClCd				= B00110FrameParts.JComboBoxSet(	130, 75,250,20,B00100DefaultVariable.ClList[0],11);		//荷主コード
		final JTextField  TB_AdjustReasonCd		= B00110FrameParts.JTextFieldSet( 130,100,100,20,TgtAdjustReasonCd,11,0);						//調整理由コード
		final JTextField  TB_AdjustReasonName	= B00110FrameParts.JTextFieldSet( 130,125,100,20,"",11,0);	//調整理由名
		final JTextField  TB_EntryDate			= B00110FrameParts.JTextFieldSet( 130,150,300,20,"",11,0);	//登録日
		final JTextField  TB_UpdateDate			= B00110FrameParts.JTextFieldSet( 130,175,300,20,"",11,0);	//更新日
		final JTextField  TB_EntryUser			= B00110FrameParts.JTextFieldSet( 130,200,300,20,"",11,0);	//登録者
		final JTextField  TB_UpdateUser			= B00110FrameParts.JTextFieldSet( 130,225,300,20,"",11,0);	//更新者
		
		for(int i=0;i<B00100DefaultVariable.ClList[1].length;i++) {
			if(B00100DefaultVariable.ClList[1][i].equals(A00000Main.ClCd)) {
				TB_ClCd.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
			if(B00100DefaultVariable.WhList[1][i].equals(A00000Main.ClWh)) {
				TB_ClWh.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B00100DefaultVariable.ClList[1].length;i++) {
			if(B00100DefaultVariable.ClList[1][i].equals(TgtClCd)) {
				TB_ClCd.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
			if(B00100DefaultVariable.WhList[1][i].equals(TgtWhCd)) {
				TB_ClWh.setSelectedIndex(i);
			}
		}
		
		if(!"".equals(TgtAdjustReasonCd)) {
			TB_AdjustReasonCd.setEnabled(false);
			
			ArrayList<String> SearchClCd 				= new ArrayList<String>();	//荷主コード
			ArrayList<String> SearchWhCd 				= new ArrayList<String>();	//倉庫コード
			ArrayList<String> SearchAdjustReasonCd		= new ArrayList<String>();	//調整理由コード
			ArrayList<String> SearchAdjustReasonName	= new ArrayList<String>();	//調整理由名
			boolean AllSearch = false;
			
			SearchClCd.add(TgtClCd);
			SearchWhCd.add(TgtWhCd);
			SearchAdjustReasonCd.add(TgtAdjustReasonCd);
			
			Object[][] AdjustReasonRt	= M00110AdjustReasonRt.AdjustReasonRt(
																		SearchClCd,				//荷主コード
																		SearchWhCd,				//倉庫コード
																		SearchAdjustReasonCd,	//調整理由コード
																		SearchAdjustReasonName,	//調整理由名
																		AllSearch);
			if(1==AdjustReasonRt.length) {
				TB_AdjustReasonName.setText(	(String)AdjustReasonRt[0][M00110AdjustReasonRt.ColAdjustReasonName]);	//調整理由名
				TB_EntryDate.setText(			(String)AdjustReasonRt[0][M00110AdjustReasonRt.ColEntryDate]);			//登録日
				TB_UpdateDate.setText(			(String)AdjustReasonRt[0][M00110AdjustReasonRt.ColUpdateDate]);			//更新日
				TB_EntryUser.setText(			(String)AdjustReasonRt[0][M00110AdjustReasonRt.ColEntryUser]);			//登録者
				TB_UpdateUser.setText(			(String)AdjustReasonRt[0][M00110AdjustReasonRt.ColUpdateUser]);			//更新者
			}
		}
		
		TB_ClWh.setEnabled(false);
		TB_ClCd.setEnabled(false);
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		main_fm.add(LB_AdjustReasonCd);
		main_fm.add(LB_AdjustReasonName);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		
		main_fm.add(TB_ClWh);
		main_fm.add(TB_ClCd);
		main_fm.add(TB_AdjustReasonCd);
		main_fm.add(TB_AdjustReasonName);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					String GetClWh				= B00100DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];
					String GetClCd				= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];
					String GetAdjustReasonCd	= TB_AdjustReasonCd.getText();
					String GetAdjustReasonName	= TB_AdjustReasonName.getText();
					
					ArrayList<String> ErrMsg	= ErrCheck(
														GetClWh,
														GetClCd,
														GetAdjustReasonCd,
														GetAdjustReasonName
														); 
					if(null!=ErrMsg && 0<ErrMsg.size()) {
						ErrView(ErrMsg);
					}else {
						GetAdjustReasonCd		= DataEntry(
														GetClWh,
														GetClCd,
														GetAdjustReasonCd,
														GetAdjustReasonName
														);
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						AdjustReasonMstRenewAndCreate(0,0,GetClCd,GetClWh,GetAdjustReasonCd);
					}
					
					RenewFg = true;
				}
			}
		});
		//在庫調整理由コードフォーカス消失時の挙動
		TB_AdjustReasonCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetClWh				= B00100DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];
					String GetClCd				= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];
					String GetAdjustReasonCd	= TB_AdjustReasonCd.getText();
					String GetAdjustReasonName	= TB_AdjustReasonName.getText();
					
					String[] TxtTrim = TxtTrim(
							GetClWh,
							GetClCd,
							GetAdjustReasonCd,
							GetAdjustReasonName
							);
	
	
					GetClWh				= TxtTrim[0];
					GetClCd				= TxtTrim[1];
					GetAdjustReasonCd	= TxtTrim[2];
					GetAdjustReasonName	= TxtTrim[3];
					
					if(!"".equals(GetAdjustReasonCd)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						AdjustReasonMstRenewAndCreate(0,0,GetClCd,GetClWh,GetAdjustReasonCd);
					}
					RenewFg = true;
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
				WM00105AdjustReasonMstSearch.AdjustReasonMstSearch(0, 0);
			}
		});
	}
	
	private static String[] TxtTrim(
								String GetClWh,
								String GetClCd,
								String GetAdjustReasonCd,
								String GetAdjustReasonName
								) {
		String[] rt = new String[4];
		if(null==GetClWh){				GetClWh				= "";}
		if(null==GetClCd){				GetClCd				= "";}
		if(null==GetAdjustReasonCd){	GetAdjustReasonCd	= "";}
		if(null==GetAdjustReasonName){	GetAdjustReasonName	= "";}
		
		GetClWh				= B00020ToolsTextControl.Trim(GetClWh);
		GetClCd				= B00020ToolsTextControl.Trim(GetClCd);
		GetAdjustReasonCd	= B00020ToolsTextControl.Trim(GetAdjustReasonCd);
		GetAdjustReasonName	= B00020ToolsTextControl.Trim(GetAdjustReasonName);
		
		rt[ 0]= GetClWh;
		rt[ 1]= GetClCd;
		rt[ 2]= GetAdjustReasonCd;
		rt[ 3]= GetAdjustReasonName;
		
		return rt;
	}
	
	private static ArrayList<String> ErrCheck(
			String GetClWh,
			String GetClCd,
			String GetAdjustReasonCd,
			String GetAdjustReasonName
			) {
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		String[] TxtTrim = TxtTrim(
								GetClWh,
								GetClCd,
								GetAdjustReasonCd,
								GetAdjustReasonName
								);
		
		
		GetClWh				= TxtTrim[0];
		GetClCd				= TxtTrim[1];
		GetAdjustReasonCd	= TxtTrim[2];
		GetAdjustReasonName	= TxtTrim[3];
		
		if("".equals(GetAdjustReasonName)) {
			ErrMsg.add("調整理由名は必須です");
		}
		
		return ErrMsg;
	}
	
	private static String DataEntry(
			String GetClWh,
			String GetClCd,
			String GetAdjustReasonCd,
			String GetAdjustReasonName
			) {
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String[] TxtTrim = TxtTrim(
				GetClWh,
				GetClCd,
				GetAdjustReasonCd,
				GetAdjustReasonName
				);

		GetClWh				= TxtTrim[0];
		GetClCd				= TxtTrim[1];
		GetAdjustReasonCd	= TxtTrim[2];
		GetAdjustReasonName	= TxtTrim[3];
		
		if("".equals(GetAdjustReasonCd)) {GetAdjustReasonCd=M00110AdjustReasonRt.NewAdjustReasonCdGet(1)[0];}
		
		Object[][] SetString = {
				 {"ClCd"				,"1","1","Key"	,GetClCd}					//荷主コード
				,{"WhCd"				,"1","1","Key"	,GetClWh}					//倉庫コード
				,{"AdjustReasonCd"		,"1","1","Key"	,GetAdjustReasonCd}			//調整理由コード
				,{"AdjustReasonName"	,"1","1",""		,GetAdjustReasonName}		//調整理由名
				,{"EntryDate"			,"1","0",""		,now_dtm}					//登録日
				,{"UpdateDate"			,"1","1",""		,now_dtm}					//更新日
				,{"EntryUser"			,"1","0",""		,"(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}					//登録者
				,{"UpdateUser"			,"1","1",""		,"(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}					//更新者
				};
	
		String tgt_table = "WM0020AdjustReason";
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		A00020InsertUdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
	
		return GetAdjustReasonCd;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\AdjustReasonMst";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\AdjustReasonMst\\Err";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\AdjustReasonMst\\BK";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\AdjustReasonMst\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B00030ToolsTextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B00040ToolsFolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B00100DefaultVariable.ErrTxtDelete);
		
		//ファイル開く
		File file = new File(ErrFP);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}