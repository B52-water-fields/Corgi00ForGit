import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class A00000Main{
	//本番環境MySQL接続設定とMySqlのスキーマ設定
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
	public static String MySqlDefaultSchemaWANKO = "WANKOscjema";
	public static String MySqlDefaultSchemaNYANKO = "NYANKOscjema";
	public static String MySqlDefaultSchemaPOST = "POSTscjema";
	public static String MySqlDefaultSchemaOLD = "OLDscjema";
	
	public static int Div = 100;
	public static int Mul = 100;
	public static int WMul = 120;
	
	public static String DefaultFont = "ＭＳ ゴシック";
	
	//iniファイルの置き場所 フルパス
	public static String IniPth = "C:\\MIZUNO\\WMS\\Corgi00ini.txt";
	/*
	以下をコピーしてご自身の環境にあわせて記入してIniファイルに設定するか↑に直書きしてください
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
	
	*/
	
	private static int LoginCheckCount;
	
	public static String LoginUserWH = "";
	public static String LoginUserWhName = "";
	public static String LoginUserId = "";
	public static String LoginUserName = "";
	public static String LoginUserCompany = "";
	public static String LoginUserAuthorityFG = "";
	
	public static String ClWh = "";
	public static String ClCd = "";
	public static String ClName = "";
	public static String ClGp = "";
	
    public static void main(String[] args) {
    	SqlSetting();//MYSQLへの接続設定読込外部ファイルにMYSQLデータベースへの接続設定を読込
    	LoginCheckCount=0;
    	LogIn();
    }
    
    private static void LogIn() {
    	//データベースに接続しユーザーマスターを読み込んでログイン画面表示
  		//ログイン画面起動
		final JFrame login_fm = new JFrame();
		//ウィンドウタイトルの設定
		login_fm.setTitle("WANKログイン");
		//表示位置サイズの設定（表示横位置,表示縦位置,横幅,縦幅）
		login_fm.setBounds(200*A00000Main.Mul/A00000Main.Div, 200*A00000Main.Mul/A00000Main.Div, 400*A00000Main.Mul/A00000Main.Div, 300*A00000Main.Mul/A00000Main.Div);
		//レイアウト無効
		login_fm.setLayout(null);
		//ウィンドウの閉じるボタンでプログラム終了しない
		//閉じるボタンでDBのコネクション閉じてから終了させたい為
		login_fm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
		//ユーザーID入力BOX
		final JTextField WH_ID = new JTextField("");				//ユーザー所属倉庫ID入力BOX
		final JTextField U_ID = new JTextField("");					//ユーザーID入力BOX
		final JPasswordField U_Pass = new JPasswordField("");		//ユーザーパスワード入力BOX
	
		WH_ID.setFont(new Font(  A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		U_ID.setFont(new Font(   A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		U_Pass.setFont(new Font( A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
	
		WH_ID.setBounds( 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div, 150*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		U_ID.setBounds(  100*A00000Main.Mul/A00000Main.Div, 50*A00000Main.Mul/A00000Main.Div, 150*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		U_Pass.setBounds(100*A00000Main.Mul/A00000Main.Div, 80*A00000Main.Mul/A00000Main.Div, 150*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
	
		//入力BOX説明
		JLabel WH_ID_LB  = new JLabel("倉庫コード");
		JLabel U_ID_LB   = new JLabel("ユーザーID");
		JLabel U_Pass_LB = new JLabel("パスワード");
	
		WH_ID_LB.setFont(new Font(  A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		U_ID_LB.setFont(new Font(   A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		U_Pass_LB.setFont(new Font( A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
	
		WH_ID_LB.setBounds(  20*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div, 150*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		U_ID_LB.setBounds(   20*A00000Main.Mul/A00000Main.Div, 50*A00000Main.Mul/A00000Main.Div, 150*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		U_Pass_LB.setBounds( 20*A00000Main.Mul/A00000Main.Div, 80*A00000Main.Mul/A00000Main.Div, 150*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
	
	
		//EXITボタン
		JButton login_exit_btn=new JButton();
		login_exit_btn.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		login_exit_btn.setText("EXIT");
		login_exit_btn.setBounds(180*A00000Main.Mul/A00000Main.Div, 120*A00000Main.Mul/A00000Main.Div, 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
	
		//ENTRYボタン
		JButton login_entry_btn=new JButton();
		login_entry_btn.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		login_entry_btn.setText("ENTRY");
		login_entry_btn.setBounds(50*A00000Main.Mul/A00000Main.Div, 120*A00000Main.Mul/A00000Main.Div, 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
	
		final DecimalFormat df = new DecimalFormat("####");
		
		JLabel LB_Magn = new JLabel("表示倍率(%)");
		final JFormattedTextField TB_Magn=new JFormattedTextField(df);
		
		TB_Magn.setText(""+Mul);
		
		LB_Magn.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		TB_Magn.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		
		LB_Magn.setHorizontalAlignment(JLabel.RIGHT);
		TB_Magn.setHorizontalAlignment(JLabel.RIGHT);
		
		LB_Magn.setBounds( 10*A00000Main.Mul/A00000Main.Div, 200*A00000Main.Mul/A00000Main.Div, 150*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		TB_Magn.setBounds(160*A00000Main.Mul/A00000Main.Div, 200*A00000Main.Mul/A00000Main.Div, 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		

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
				WST = B00020ToolsTextControl.num_only_String(WST);
				if("".equals(WST)) {WST = "100";}
				
				WMul = Integer.parseInt(WST);
				
				//ユーザーzeusだった場合ユーザーマスタテーブル作成　zeusユーザー無ければ作る
				if(0==LoginCheckCount&&"zeus".equals(UserId)) {
					A00040TableCheck.UserMstCreate() ;
					ZeusCreate();
				}
				
				boolean LoginCheck = LoginCheck(WhCd,UserId,UserPass);
				if(LoginCheck) {
					login_fm.setVisible(false);
					login_fm.dispose();
					LoginStr() ;
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
				WST = B00020ToolsTextControl.num_only_String(WST);
				if("".equals(WST)) {WST = "100";}
				
				WMul = Integer.parseInt(WST);
				
				//ユーザーzeusだった場合ユーザーマスタテーブル作成　zeusユーザー無ければ作る
				if(0==LoginCheckCount&&"zeus".equals(UserId)) {
					A00040TableCheck.UserMstCreate() ;
					ZeusCreate();
				}
				
				boolean LoginCheck = LoginCheck(WhCd,UserId,UserPass);
				if(LoginCheck) {
					login_fm.setVisible(false);
					login_fm.dispose();
					LoginStr() ;
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

		login_exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				EndPg();
			}
		});
		
    	
    	
    }
    private static void ZeusCreate() {
    	//zeus 倉庫コード0000　パスワードLetThereBeLight
    	String tgt_table = "KM0020_USERMST";
		String[][] field_name = new String[25][3];
		String[][] entry_data = new String[1][25];
		String[] judg_field = new String[3];
		String[][] judg_data = new String[1][3];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		judg_field[0] = "WHCD";						//倉庫コード
		judg_field[1] = "ShippingCompanyCd";		//運送会社CD
		judg_field[2] = "UserCd";					//ユーザーCD
		
		field_name[0][0] = "WHCD";					//倉庫コード
		field_name[1][0] = "ShippingCompanyCd";		//運送会社CD
		field_name[2][0] = "UserCd";				//ユーザーCD
		field_name[3][0] = "PassWord";				//パスワード
		field_name[4][0] = "AuthorityFG";			//権限区分
		field_name[5][0] = "CarCd";					//標準車輛CD
		field_name[6][0] = "UserName01";			//ユーザー名1
		field_name[7][0] = "UserName02";			//ユーザー名2
		field_name[8][0] = "UserName03";			//ユーザー名3
		field_name[9][0] = "Post";					//郵便番号
		field_name[10][0] = "Add01";				//住所1
		field_name[11][0] = "Add02";				//住所2
		field_name[12][0] = "Add03";				//住所3
		field_name[13][0] = "Tel";					//電話番号
		field_name[14][0] = "Fax";					//FAX
		field_name[15][0] = "Mail";					//メールアドレス
		field_name[16][0] = "Com01";				//コメント1
		field_name[17][0] = "Com02";				//コメント2
		field_name[18][0] = "Com03";				//コメント3
		field_name[19][0] = "EntryDate";			//データ登録日時
		field_name[20][0] = "UpdateDate";			//データ更新日時
		field_name[21][0] = "EntryUser";			//登録者コード
		field_name[22][0] = "UpdateUser";			//更新者コード
		field_name[23][0] = "PTMSCD";				//基幹システムユーザーコード
		field_name[24][0] = "DelFg";				//削除区分
		
		field_name[0][1] = "1";		//倉庫コード
		field_name[1][1] = "1";		//運送会社CD
		field_name[2][1] = "1";		//ユーザーCD
		field_name[3][1] = "1";		//パスワード
		field_name[4][1] = "1";		//権限区分
		field_name[5][1] = "1";		//標準車輛CD
		field_name[6][1] = "1";		//ユーザー名1
		field_name[7][1] = "1";		//ユーザー名2
		field_name[8][1] = "1";		//ユーザー名3
		field_name[9][1] = "1";		//郵便番号
		field_name[10][1] = "1";	//住所1
		field_name[11][1] = "1";	//住所2
		field_name[12][1] = "1";	//住所3
		field_name[13][1] = "1";	//電話番号
		field_name[14][1] = "1";	//FAX
		field_name[15][1] = "1";	//メールアドレス
		field_name[16][1] = "1";	//コメント1
		field_name[17][1] = "1";	//コメント2
		field_name[18][1] = "1";	//コメント3
		field_name[19][1] = "1";	//データ登録日時
		field_name[20][1] = "1";	//データ更新日時
		field_name[21][1] = "1";	//登録者コード
		field_name[22][1] = "1";	//更新者コード
		field_name[23][1] = "1";	//基幹システムユーザーコード
		field_name[24][1] = "1";	//削除区分
		
		field_name[0][2] = "0";		//倉庫コード
		field_name[1][2] = "0";		//運送会社CD
		field_name[2][2] = "0";		//ユーザーCD
		field_name[3][2] = "0";		//パスワード
		field_name[4][2] = "0";		//権限区分
		field_name[5][2] = "0";		//標準車輛CD
		field_name[6][2] = "0";		//ユーザー名1
		field_name[7][2] = "0";		//ユーザー名2
		field_name[8][2] = "0";		//ユーザー名3
		field_name[9][2] = "0";		//郵便番号
		field_name[10][2] = "0";	//住所1
		field_name[11][2] = "0";	//住所2
		field_name[12][2] = "0";	//住所3
		field_name[13][2] = "0";	//電話番号
		field_name[14][2] = "0";	//FAX
		field_name[15][2] = "0";	//メールアドレス
		field_name[16][2] = "0";	//コメント1
		field_name[17][2] = "0";	//コメント2
		field_name[18][2] = "0";	//コメント3
		field_name[19][2] = "0";	//データ登録日時
		field_name[20][2] = "0";	//データ更新日時
		field_name[21][2] = "0";	//登録者コード
		field_name[22][2] = "0";	//更新者コード
		field_name[23][2] = "0";	//基幹システムユーザーコード
		field_name[24][2] = "0";	//削除区分
		
		judg_data[0][0] = "0000";		//倉庫コード
		judg_data[0][1] = "0000";		//運送会社CD
		judg_data[0][2] = "zeus";		//ユーザーCD
		
		entry_data[0][0] = "0000";				//倉庫コード
		entry_data[0][1] = "0000";				//運送会社CD
		entry_data[0][2] = "zeus";				//ユーザーCD
		entry_data[0][3] = "ThereBeLight";		//パスワード
		entry_data[0][4] = "9";			//権限区分
		entry_data[0][5] = "";			//標準車輛CD
		entry_data[0][6] = "神";		//ユーザー名1
		entry_data[0][7] = "";			//ユーザー名2
		entry_data[0][8] = "";			//ユーザー名3
		entry_data[0][9] = "";			//郵便番号
		entry_data[0][10] = "";			//住所1
		entry_data[0][11] = "";			//住所2
		entry_data[0][12] = "";			//住所3
		entry_data[0][13] = "";			//電話番号
		entry_data[0][14] = "";			//FAX
		entry_data[0][15] = "";			//メールアドレス
		entry_data[0][16] = "";			//コメント1
		entry_data[0][17] = "";			//コメント2
		entry_data[0][18] = "";			//コメント3
		entry_data[0][19] = now_dtm;	//データ登録日時
		entry_data[0][20] = now_dtm;	//データ更新日時
		entry_data[0][21] = "zeus";		//登録者コード
		entry_data[0][22] = "zeus";		//更新者コード
		entry_data[0][23] = "";			//基幹システムユーザーコード
		entry_data[0][24] = "0";		//削除区分

		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
    }
    
    private static boolean LoginCheck(String WhCd,String UserId,String UserPass){
    	boolean LoginCheck=false;
    	
    	//ログイン入力内容でログインチェック
    	A00010DbConnect.DB_CONN("NYANKO");
		ResultSet rset01 = null;
		PreparedStatement stmt01 = null;
    	
		String sql = "SELECT"
				+ " KM0020_USERMST.WHCD as WH_CD"
				+ ",KM0020_USERMST.UserCd as USER_CD"
				+ ",KM0020_USERMST.PassWord as USER_PASS"
				+ ",KM0020_USERMST.UserName01 as USER_NAME"
				+ ",KM0020_USERMST.ShippingCompanyCd as ShippingCompanyCd"
				+ ",KM0020_USERMST.AuthorityFG as AuthorityFG"
				+ " FROM KM0020_USERMST"
				+ " WHERE "
				+ "KM0020_USERMST.WHCD = ?"
				+ " AND "
				+ "KM0020_USERMST.UserCd = ?"
				+ " AND "
				+ "KM0020_USERMST.PassWord= ?"
				+ " AND "
				+ "(KM0020_USERMST.AuthorityFG is null or KM0020_USERMST.AuthorityFG='0' or KM0020_USERMST.AuthorityFG='2' or KM0020_USERMST.AuthorityFG='9')";
    	
		//System.out.println(sql);
		try {
			stmt01 = A00010DbConnect.conn.prepareStatement(sql);
			stmt01.setString(1, WhCd);
			stmt01.setString(2, UserId);
			stmt01.setString(3, UserPass);
			
			rset01 = stmt01.executeQuery();
			rset01.beforeFirst();
			while (rset01.next()) {
				LoginCheck = true;
				LoginUserWH    = rset01.getString("WH_CD");
				LoginUserId    = rset01.getString("USER_CD");
				LoginUserName  = rset01.getString("USER_NAME");
				LoginUserCompany = rset01.getString("ShippingCompanyCd");
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
		A00010DbConnect.Fullclose();
    	return LoginCheck;
    }
    
    private static void LoginStr() {
    	if(100>WMul) {WMul=100;}
    	Mul = WMul;
    	JOptionPane.showMessageDialog(null, "ログイン成功");
    }
    
    
    
    private static void SqlSetting() {
    	 //MYSQLへの接続設定読込外部ファイルにMYSQLデータベースへの接続設定を読込        
    	ArrayList<String> IniRead = B00010ToolsTextRead.TxtReadRtArray(IniPth,"UTF-8");
    	
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
    	}
    }
    
    public static void EndPg(){
    	//データベース接続・SSH接続切断→終了
    	A00010DbConnect.Fullclose();
		System.exit(0);
	}
}