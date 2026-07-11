import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class A00000_Main{
	//本番環境MySQL接続設定とMySqlのスキーマ設定　"C:\\MIZUNO\\WMS\\Corgi00ini.txt"に以下をコピーして、ご自身の環境にあわせて記入して設定してください
	/*
	DefaultSshHostName = "your.ssh.server.com";
	DefaultSshUserName = "ssh_user";
	DefaultSshKeyFld = "/path/to/key";
	DefaultSshKeyFileName = "KeyName.key";
	DefaultSshPass = "sshPassword";
	DefaultMySqlHostName = "your.mysql.server.com";
	DefaultMySqlUser = "MysqlUserName";
	DefaultMySqlPass = "MysqlUserPassWord";
	DefaultSshHostPort = 22;
	DefaultMySqlSverPort = 3306;
	MySqlDefaultSchemaWANKO = "WANKOscjema";
	MySqlDefaultSchemaNYANKO = "NYANKOscjema";
	MySqlDefaultSchemaPOST = "POSTscjema";
	MySqlDefaultSchemaOLD = "OLDscjema";
	PasswordExpireDays = 180;
	FileFldPth = "C:\MIZUNO\WMS";
	FontFilePath = "C:\MIZUNO\FONT\meiryo.ttc"
	*/
	//
	
	//iniファイルの置き場所 フルパス
	public static String IniPth = "C:\\MIZUNO\\WMS\\Corgi00ini.txt";
	public static String MainFLD = "C:\\MIZUNO\\WMS";							//ローカルのフォルダです無ければ作ります
	
	//画像等共有フォルダパス
	public static String FileFldPth = "C:\\MIZUNO\\WMS";						//共有フォルダ等指定してください
	
	public static boolean UseSSH= true;
	
	public static String DefaultSshHostName = "your.ssh.server.com";    		//SSH接続サーバアドレス
	public static String DefaultSshUserName = "ssh_user";			    	//SSH接続ユーザー名
	public static String DefaultSshKeyFld = "/path/to/key";			    	//SSHキーを置くフォルダパス
	public static String DefaultSshKeyFileName = "KeyName.key";		    	//SSHキーファイル名
	public static String DefaultSshPass = "sshPassword";			    		//SSH接続パスワード
	public static String DefaultMySqlHostName = "your.mysql.server.com"; 	//Mysqlサーバーアドレス
	public static String DefaultMySqlUser = "MysqlUserName";					//Mysqlユーザー名
	public static String DefaultMySqlPass = "MysqlUserPassWord";				//Mysqlユーザーパスワード
	
	public static int DefaultSshHostPort = 22;		//SSHでの接続ポート
	public static int DefaultMySqlSverPort = 3306;	//Mysqlサーバー接続ポート
	
	//MySQlに以下4つのスキーマ作ってください
	//同じスキーマに揃えても動くと思います（未検証）
	//但しOLDだけは古いデータ退避用なので別スキーマの方が良いと思います
	//中身のテーブルは後ほど自動生成します
	public static String MySqlDefaultSchemaWANKO = "WANKOscjema";		//在庫管理系データベース
	public static String MySqlDefaultSchemaNYANKO = "NYANKOscjema";		//配送管理系データベース
	public static String MySqlDefaultSchemaPOST = "POSTscjema";			//郵便番号データベース
	public static String MySqlDefaultSchemaOLD = "OLDscjema";			//古いトランザクションデータバックアップ先
	
	public static int Div = 100;
	public static int Mul = 100;
	public static int WMul = 120;
	
	public static String DefaultFont = "ＭＳ ゴシック";
	
	private static int LoginCheckCount;
	
	public static String LoginUserWH = "";
	public static String LoginUserWhName = "";
	public static String LoginUserId = "";
	public static String LoginUserName = "";
	public static String LoginUserCompany = "";
	public static String LoginUserAuthorityFG = "";
	public static String LoginUserClient="";
	public static String LoginUserLastUpDate="";
	
	public static String ClWh = "";
	public static String ClWhName = "";
	public static String ClCd = "";
	public static String ClName = "";
	public static String ClGp = "";
	public static String ClGpName = "";
	
	public static int PasswordExpireDays = 180;		//パスワード有効期限推奨日数（パスワード変更履歴ではなく、ユーザーマスタの最終更新日時で判定）0なら更新永遠に気にしない
	
	public static String FontFilePath = "";
	public static String FontFileName = "";	//ttcフォント呼び出し時にフォント名でつる為に必要
	
	/*===========================================================================================================================
	
 	main(String[] args)		:起動
 	SqlSetting()			:MYSQLへの接続設定読込外部ファイルにMYSQLデータベースへの接続設定を読込
 	
 	LogIn()					:ログイン画面
 	
 	ZeusCreate()			:zeusログイン時（KM0020_USERMST）ユーザーマスタにzeusが絶対に存在するようにzeusは登録
 							 ※ユーザーマスタテーブルが存在しない場合に備えてテーブルもこの段階で、なければ作られます
 	
 	LoginCheck(String WhCd,String UserId,String UserPass)
 							:ログイン入力情報とユーザーマスタ情報を比較OKなら通す
 	
 	LoginStr(String WhCd,String UserId,String UserPass) 
 							:ログインユーザーがzeusだった場合
 							 A100_TableCheck.TableCheck()と A100_OldDataTableCheck.OldDataTableCheck()起動して
 							 天地創造・世界の修復（各スキーマに必要テーブル自動生成＆各テーブルのフィールドチェック）
 							 
 							 ⇒ログインユーザーの権限に応じて先に進む（権限なければ強制終了）
 	
 	EndPg()					:システム強制終了
 	
 	
 	以下は別クラスから呼ばれます
 	LoginCheck()			:ユーザーがログイン中かどうかを判定、ログインできていなければ強制終了
 	
 	ClSelect()				:ログイン中のユーザーが選択可能な荷主を一覧表示⇒選択⇒業務画面へ
 	
 	
 	以下の変数はログイン時に決定された基本情報として、別クラスのあちこちで使います
 	public static String LoginUserWH = "";
	public static String LoginUserWhName = "";
	public static String LoginUserId = "";
	public static String LoginUserName = "";
	public static String LoginUserCompany = "";
	public static String LoginUserAuthorityFG = "";
	public static String LoginUserClient="";
	
	public static String ClWh = "";
	public static String ClWhName = "";
	public static String ClCd = "";
	public static String ClName = "";
	public static String ClGp = "";
	
	public static int Div = 100;
	public static int Mul = 100;
	public static int WMul = 120;
	
	帳票用フォントファイル
	FontFilePath
 	
	===========================================================================================================================*/
	
    public static void main(String[] args) {
    	SqlSetting();//MYSQLへの接続設定読込外部ファイルにMYSQLデータベースへの接続設定を読込
    	LoginCheckCount=0;
    	LogIn();
    }
    
    private static void LogIn() {
    	//データベースに接続しユーザーマスターを読み込んでログイン画面表示
  		//ログイン画面起動
		final JFrame login_fm = B100_FrameParts.FrameCreate(20,20,400,300,"Corgi00ログイン","");
	
		//ユーザーID入力BOX
		final JTextField 		WH_ID 	= B100_FrameParts.JTextFieldSet(		100,20,150,20,"",11,0);				//ユーザー所属倉庫ID入力BOX
		final JTextField 		U_ID 	= B100_FrameParts.JTextFieldSet(		100,50,150,20,"",11,0);				//ユーザーID入力BOX
		final JPasswordField 	U_Pass 	= B100_FrameParts.JPasswordFieldSet(	100,80,150,20,"",11,0);				//ユーザーパスワード入力BOX
	
		//入力BOX説明
		JLabel WH_ID_LB  = B100_FrameParts.JLabelSet(	20,20,150,20,"倉庫コード:",11,1);
		JLabel U_ID_LB   = B100_FrameParts.JLabelSet(	20,50,150,20,"ユーザーID:",11,1);
		JLabel U_Pass_LB = B100_FrameParts.JLabelSet(	20,80,150,20,"パスワード:",11,1);
	
	
		//EXITボタン
		JButton login_exit_btn=B100_FrameParts.BtnSet(50,120,100,20,"EXIT",11);
	
		//ENTRYボタン
		JButton login_entry_btn=B100_FrameParts.BtnSet(180,120,100,20,"ENTRY",11);
		
		//表示倍率
		JLabel LB_Magn = B100_FrameParts.JLabelSet(	10,200,150,20,"表示倍率(%);",11,1);
		final JFormattedTextField TB_Magn=B100_FrameParts.JFormattedTextFieldSet(	160,200,100,20,""+Mul,11,1,"####");	

		login_fm.add(login_exit_btn);
		login_fm.add(login_entry_btn);
		login_fm.add(WH_ID);
		login_fm.add(U_ID);
		login_fm.add(U_Pass);
		login_fm.add(WH_ID_LB);
		login_fm.add(U_ID_LB);
		login_fm.add(U_Pass_LB);
		login_fm.add(LB_Magn);
		login_fm.add(TB_Magn);
		login_fm.setVisible(true);
		WH_ID.requestFocusInWindow();
    	
		//ENTRYボタン押下時の挙動
		login_entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String WhCd = WH_ID.getText();
				String UserId = U_ID.getText();
				String UserPass = String.valueOf(U_Pass.getPassword());
				
				String WST = TB_Magn.getText();
				if(null==WST) {WST = "100";}
				WST = B100_TextControl.num_only_String(WST);
				if("".equals(WST)) {WST = "100";}
				
				WMul = Integer.parseInt(WST);
								
				if(0==LoginCheckCount) {
					//ログインに必要なマスタだけは無ければ作る
					A100_TableCheck.FirstTableCreate();
					//ユーザーzeusだった場合、zeusユーザー無ければ作る
					if("zeus".equals(UserId)) {
						ZeusCreate();
					}
				}
				
				boolean LoginCheck = LoginCheck(WhCd,UserId,UserPass);
				if(LoginCheck) {
					login_fm.setVisible(false);
					login_fm.dispose();
					
					LoginStr(WhCd,UserId,UserPass) ;
				}else {
					WH_ID.setText("");
					U_ID.setText("");
					U_Pass.setText("");
					WH_ID.requestFocusInWindow();
					
					LoginCheckCount = LoginCheckCount+1;
					
					if(3<LoginCheckCount) {
						JOptionPane.showMessageDialog(null, "ログイン出来ません");
			    		EndPg();
			    	}else {
			    		LoginCheckCount=LoginCheckCount+1;
			    	}
				}
			}
		});
		
		WH_ID.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				U_ID.requestFocusInWindow();
			}
		});
		
		U_ID.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				U_Pass.requestFocusInWindow();
			}
		});
		
		U_Pass.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String WhCd = WH_ID.getText();
				String UserId = U_ID.getText();
				String UserPass = String.valueOf(U_Pass.getPassword());
				
				String WST = TB_Magn.getText();
				if(null==WST) {WST = "100";}
				WST = B100_TextControl.num_only_String(WST);
				if("".equals(WST)) {WST = "100";}
				
				WMul = Integer.parseInt(WST);
				
				if(0==LoginCheckCount) {
					//ログインに必要なマスタだけは無ければ作る
					A100_TableCheck.FirstTableCreate();
					//ユーザーzeusだった場合、zeusユーザー無ければ作る
					if("zeus".equals(UserId)) {
						ZeusCreate();
					}
				}
				
				boolean LoginCheck = LoginCheck(WhCd,UserId,UserPass);
				if(LoginCheck) {
					login_fm.setVisible(false);
					login_fm.dispose();
					
					LoginStr(WhCd,UserId,UserPass) ;
				}else {
					WH_ID.setText("");
					U_ID.setText("");
					U_Pass.setText("");
					WH_ID.requestFocusInWindow();
					LoginCheckCount = LoginCheckCount+1;
					if(3<LoginCheckCount) {
						JOptionPane.showMessageDialog(null, "ログイン出来ません");
			    		EndPg();
			    	}else {
			    	}
				}
			}
		});

		login_exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				EndPg();
			}
		});
    }
    
    public static void LoginCheck() {
		//ログイン状態を確認してログインできていなければ終了
		if(null==LoginUserId||null==LoginUserWH
				||"".equals(LoginUserId)||"".equals(LoginUserWH)) {
			EndPg();
		}else {
			//なにかと邪魔になるので在庫数ゼロの在庫データを削除する
			if(null!=ClWh && null!=ClCd && !"".equals(ClWh) && !"".equals(ClCd)) {
				Tools100_StockZeroDelete.StockZeroDelete(ClCd,ClWh);
			}
		}
	}
    
    public static void ClSelect() {
    	//荷主選択
    	final JFrame main_fm = B100_FrameParts.FrameCreate(20,20,500,250,"Corgi荷主選択","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		Object[][] WorkClList = ClList();
		//荷主マスタ戻り値空だった場合、荷主グループClGp000配下に荷主を１件作る
		if(null==WorkClList||0==WorkClList.length) {
			B100_DefaultVariableWarehouse.DefaultClCreate(LoginUserWH);
			WorkClList = ClList();
		}
		
		Object[][] ClList = WorkClList;
		
		Object[] SelectCl = new Object[ClList.length];
		
		for(int i=0;i<ClList.length;i++) {
			SelectCl[i] = "("+ClList[i][M100_ClMstRt.Colcl_cd]+")"+ClList[i][M100_ClMstRt.ColCLName01];
		}
		
		JLabel LbCLList = B100_FrameParts.JLabelSet(	 20,80,100,20,"荷主選択:",11,1);
		final JComboBox TbCLList = B100_FrameParts.JComboBoxSet(120,80,300,20,SelectCl,11);
		
		if(null==ClCd||"".equals(ClCd)) {
			TbCLList.setSelectedIndex(0);
			for(int i=0;i<ClList.length;i++) {
				if((""+ClList[i][M100_ClMstRt.Colcl_cd]).equals(LoginUserClient)){
					TbCLList.setSelectedIndex(i);
				}
			}
		}else {
			for(int i=0;i<ClList.length;i++) {
				if((""+ClList[i][M100_ClMstRt.Colcl_cd]).equals(ClCd)){
					TbCLList.setSelectedIndex(i);
				}
			}
		}
		
		main_fm.add(LbCLList);
		main_fm.add(TbCLList);
		main_fm.add(entry_btn);
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.setVisible(true);

		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				main_fm.setVisible(false);
				main_fm.dispose();
				A00000_Main.EndPg();
			}
		});
		
		//Entryボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ClWh 		= ""+ClList[TbCLList.getSelectedIndex()][M100_ClMstRt.ColWHCD];
				ClWhName 	= ""+ClList[TbCLList.getSelectedIndex()][M100_ClMstRt.ColWHName];
				ClCd 		= ""+ClList[TbCLList.getSelectedIndex()][M100_ClMstRt.Colcl_cd];
				ClName 	= ""+ClList[TbCLList.getSelectedIndex()][M100_ClMstRt.ColCLName01];
				ClGp 		= ""+ClList[TbCLList.getSelectedIndex()][M100_ClMstRt.ColClGpCD];
				ClGpName 	= ""+ClList[TbCLList.getSelectedIndex()][M100_ClMstRt.ColClGpName];
				
				//荷主毎に決定する変数取得
				B100_DefaultVariable.DefaultVariable();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_MainMenu.MainMenu(0,0);
			}
		});
    }
    
    private static void ZeusCreate() {
    	//zeus 倉庫コード0000　パスワードLetThereBeLight
    	
		String[][] field_name = new String[25][3];
		String[][] entry_data = new String[1][25];
		String[] judg_field = new String[3];
		String[][] judg_data = new String[1][3];
		
		
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		Object[][] SetString = {
				 {"WHCD"				,"1","0","Key"	,"0000"}							//倉庫コード
				,{"ShippingCompanyCd"	,"1","0","Key"	,"SC00000"}							//運送会社CD
				,{"UserCd"				,"1","0","Key"	,"zeus"}							//ユーザーCD
				,{"PassWord"			,"1","0",""		,"LetThereBeLight"}					//パスワード
				,{"AuthorityFG"			,"1","0",""		,"9"}								//権限区分
				,{"CarCd"				,"1","0",""		,""}								//標準車輛CD
				,{"UserName01"			,"1","0",""		,"神"}								//ユーザー表記名
				,{"UserName02"			,"1","0",""		,""}								//ユーザー正式名
				,{"UserName03"			,"1","0",""		,""}								//ユーザー略名
				,{"Post"				,"1","0",""		,""}								//郵便番号
				,{"Add01"				,"1","0",""		,"Leof. Vasilisis Amalias 50,"}		//住所1
				,{"Add02"				,"1","0",""		,"Athina 105 58 Greece"}			//住所2
				,{"Add03"				,"1","0",""		,""}								//住所3
				,{"Tel"					,"1","0",""		,""}								//電話番号
				,{"Fax"					,"1","0",""		,""}								//FAX
				,{"Mail"				,"1","0",""		,""}								//メールアドレス
				,{"Com01"				,"1","0",""		,""}								//コメント1
				,{"Com02"				,"1","0",""		,""}								//コメント2
				,{"Com03"				,"1","0",""		,""}								//コメント3
				,{"EntryDate"			,"1","0",""		,now_dtm}							//データ登録日時
				,{"UpdateDate"			,"1","0",""		,now_dtm}							//データ更新日時
				,{"EntryUser"			,"1","0",""		,"(zeus)神"}						//登録者コード
				,{"UpdateUser"			,"1","0",""		,"(zeus)神"}						//更新者コード
				,{"PTMSCD"				,"1","0",""		,"0"}								//基幹システムユーザーコード
				,{"DelFg"				,"1","0",""		,"0"}								//削除区分
				};
		
		String tgt_table = "KM0020_USERMST";
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;
		
		A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
		
    }
    
    private static boolean LoginCheck(String WhCd,String UserId,String UserPass){
    	boolean LoginCheck=false;
    	
    	//ログイン入力内容でログインチェック
    	A100_DbConnect.DB_CONN("NYANKO");
		ResultSet rset01 = null;
		PreparedStatement stmt01 = null;
    	
		String sql = "SELECT"
				+ " KM0020_USERMST.WHCD as WH_CD"
				+ ",KM0010_WHMST.WHName as WHName"
				+ ",KM0020_USERMST.UserCd as USER_CD"
				+ ",KM0020_USERMST.PassWord as USER_PASS"
				+ ",KM0020_USERMST.UserName01 as USER_NAME"
				+ ",KM0020_USERMST.ShippingCompanyCd as ShippingCompanyCd"
				+ ",KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01 as ShippingCompanyName01"
				+ ",KM0020_USERMST.AuthorityFG as AuthorityFG"
				+ ",KM0020_USERMST.MainClient as MainClient"
				+ ",KM0020_USERMST.UpdateDate as UpdateDate"
				+ " FROM "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST"
				+ " LEFT OUTER JOIN "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST ON("
				+ " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD"
				+ ")"
				+ " LEFT OUTER JOIN "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST ON("
				+ " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.ShippingCompanyCd = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd"
				+ ")"
				+ " "
				+ " WHERE "
				+ ""+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.DelFg = 0"
				+ " AND "
				+ ""+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD = ?"
				+ " AND "
				+ ""+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.UserCd = ?"
				+ " AND "
				+ ""+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.PassWord= ?"
				+ " AND "
				+ "("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.AuthorityFG is null"
				+ " or "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.AuthorityFG='0'"
				+ " or "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.AuthorityFG='2'"
				+ " or "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.AuthorityFG='9'"
				+ ")";
    	
		//System.out.println(sql);
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
			stmt01.setString(1, WhCd);
			stmt01.setString(2, UserId);
			stmt01.setString(3, UserPass);
			
			rset01 = stmt01.executeQuery();
			rset01.beforeFirst();
			while (rset01.next()) {
				LoginCheck = true;
				if(null==rset01.getString("WH_CD")){LoginUserWH="";}else{LoginUserWH=rset01.getString("WH_CD");}	
				if(null==rset01.getString("WHName")){LoginUserWhName="";}else{LoginUserWhName=rset01.getString("WHName");}	
				if(null==rset01.getString("USER_CD")){LoginUserId="";}else{LoginUserId=rset01.getString("USER_CD");}	
				if(null==rset01.getString("USER_NAME")){LoginUserName="";}else{LoginUserName=rset01.getString("USER_NAME");}	
				if(null==rset01.getString("ShippingCompanyCd")){LoginUserCompany="";}else{LoginUserCompany=rset01.getString("ShippingCompanyCd");}	
				if(null==rset01.getString("MainClient")){LoginUserClient="";}else{LoginUserClient=rset01.getString("MainClient");}	
				if(null==rset01.getTimestamp("UpdateDate")) {LoginUserLastUpDate = "";}else{LoginUserLastUpDate=B100_DateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	
				
				if(null==rset01.getString("AuthorityFG")||"".equals(rset01.getString("AuthorityFG"))){LoginUserAuthorityFG = "0";}else{
					LoginUserAuthorityFG = rset01.getString("AuthorityFG");
				};
			}
			if(rset01!=null){rset01.close();}
			if(stmt01!=null){stmt01.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rset01!=null){rset01.close();}
				if(stmt01!=null){stmt01.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		A100_DbConnect.Fullclose();
    	return LoginCheck;
    }
    
    private static void LoginStr(String WhCd,String UserId,String UserPass) {
    	if(100>WMul) {WMul=100;}
    	Mul = WMul;
    	//B100_SoundCreate.SoundTest();
    	
    	//ログインユーザーがzeusだった場合天地創造（データベースの存在チェック・フィールドのカラムの不足チェック走らせる）
    	if(LoginUserWH.equals("0000") && LoginUserId.equals("zeus")) {
    		A100_TableCheck.TableCheck();
    		A100_OldDataTableCheck.OldDataTableCheck();
    		
    		B100_DefaultVariable.DefaultClGp();
    		B100_DefaultVariable.DefaultShippingCompany();
    		B100_DefaultVariable.DefaultWarehouse();
    		B100_DefaultVariable.Post0000000();
    		B100_DefaultVariable.ForcedShipmentCD();
    		LoginCheck(WhCd,UserId,UserPass);
    		B100_TableSelectSql.TableSelectSql();
    	}
    	//ユーザー情報の更新がパスワード有効期限推奨日数よりも前だったら警告
    	if(0<PasswordExpireDays) {
    		if("".equals(PasswordExpireDays)||0>B100_DateTimeControl.ddif(B100_DateTimeControl.ndate_before(B100_DateTimeControl.dtm()[0],PasswordExpireDays),B100_DateTimeControl.dtmTimestamp2(LoginUserLastUpDate)[0])) {
    			JOptionPane.showMessageDialog(null, "ユーザー情報の最終更新が"+PasswordExpireDays+"より前です\nパスワード更新しやがれください");
    		}
    	}
    	ClSelect() ;
    }
    
    private static Object[][] ClList(){
    	ArrayList<String> SearchClGpCD = new ArrayList<String>();
    	ArrayList<String> SearchCLCD = new ArrayList<String>();
    	ArrayList<String> SearchCLName = new ArrayList<String>();
    	ArrayList<String> SearchPost = new ArrayList<String>();
    	ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>(); 
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		switch (LoginUserAuthorityFG) {
			case "0"://一般ユーザー
				SearchWHCD.add(LoginUserWH);
				break;
			case "1"://乗務員
				JOptionPane.showMessageDialog(null, "利用権限がありません");
				EndPg();
				break;
			case "2"://荷主ユーザー
				SearchWHCD.add(LoginUserWH);
				SearchCLCD.add(LoginUserClient);
				break;
			case "9":
				AllSearch = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "利用権限がありません");
				EndPg();
				break;
				
		}
    	Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
    				SearchClGpCD,SearchCLCD, SearchCLName, SearchPost, searchAdd,
    				SearchTel, SearchFax, SearchMail,  SearchCom, SearchWHCD, AllSearch);
    	
    	return ClMstRt;
    }
    
    private static void SqlSetting() {
    	 //MYSQLへの接続設定読込外部ファイルにMYSQLデータベースへの接続設定を読込        
    	ArrayList<String> IniRead = B100_TextRead.TxtReadRtArray(IniPth,"UTF-8");
    	
    	for(int i=0;i<IniRead.size();i++) {
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshHostName", ""))) {
    			DefaultSshHostName = IniRead.get(i).replace("DefaultSshHostName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshUserName", ""))) {
    			DefaultSshUserName = IniRead.get(i).replace("DefaultSshUserName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshKeyFld", ""))) {
    			DefaultSshKeyFld = IniRead.get(i).replace("DefaultSshKeyFld", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshKeyFileName", ""))) {
    			DefaultSshKeyFileName = IniRead.get(i).replace("DefaultSshKeyFileName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshPass", ""))) {
    			DefaultSshPass = IniRead.get(i).replace("DefaultSshPass", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlHostName", ""))) {
    			DefaultMySqlHostName = IniRead.get(i).replace("DefaultMySqlHostName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlUser", ""))) {
    			DefaultMySqlUser = IniRead.get(i).replace("DefaultMySqlUser", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlPass", ""))) {
    			DefaultMySqlPass = IniRead.get(i).replace("DefaultMySqlPass", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshHostPort", ""))) {
    			DefaultSshHostPort = Integer.parseInt(IniRead.get(i).replace("DefaultSshHostPort", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "").replace("int", ""));
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlSverPort", ""))) {
    			DefaultMySqlSverPort = Integer.parseInt(IniRead.get(i).replace("DefaultMySqlSverPort", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "").replace("int", ""));
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaWANKO", ""))) {
    			MySqlDefaultSchemaWANKO = IniRead.get(i).replace("MySqlDefaultSchemaWANKO", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaNYANKO", ""))) {
    			MySqlDefaultSchemaNYANKO = IniRead.get(i).replace("MySqlDefaultSchemaNYANKO", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaPOST", ""))) {
    			MySqlDefaultSchemaPOST = IniRead.get(i).replace("MySqlDefaultSchemaPOST", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaOLD", ""))) {
    			MySqlDefaultSchemaOLD = IniRead.get(i).replace("MySqlDefaultSchemaOLD", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("UseSSH", ""))) {
    			String WST = IniRead.get(i).replace("UseSSH", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    			if("false".equals(WST)) {UseSSH	= false;}else {UseSSH	= true;}
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("PasswordExpireDays", ""))) {
    			String WST = B100_TextControl.num_only_String(IniRead.get(i));
    			if("".equals(WST)) {WST = "180";}
    			PasswordExpireDays = Integer.parseInt(WST);
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("FileFldPth", ""))) {
    			FileFldPth = IniRead.get(i).replace("FileFldPth", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("FontFilePath", ""))) {
    			FontFilePath = IniRead.get(i).replace("FontFilePath", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace("String", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("FontFileName", ""))) {
    			String[] WST = IniRead.get(i).split("\"");
    			if(2<WST.length) {
    				FontFileName = WST[1];
    			}
    		}
    		
    	}
    }
    
    public static void EndPg(){
    	//データベース接続・SSH接続切断→終了
    	A100_DbConnect.Fullclose();
		System.exit(0);
	}
}