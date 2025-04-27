import java.util.ArrayList;

public class A00000Main{
	//本番環境MySQL接続設定とMySqlのスキーマ設定
	private static String DefaultSshHostName = "your.ssh.server.com";    	//SSH接続サーバアドレス
	private static String DefaultSshUserName = "ssh_user";			    	//SSH接続ユーザー名
	private static String DefaultSshKeyFld = "/path/to/key";			    	//SSHキーを置くフォルダパス
	private static String DefaultSshKeyFileName = "KeyName.key";		    	//SSHキーファイル名
	private static String DefaultSshPass = "sshPassword";			    		//SSH接続パスワード
	private static String DefaultMySqlHostName = "your.mysql.server.com"; 	//Mysqlサーバーアドレス
	private static String DefaultMySqlUser = "MysqlUserName";					//Mysqlユーザー名
	private static String DefaultMySqlPass = "MysqlUserPassWord";				//Mysqlユーザーパスワード
	
	private static int DefaultSshHostPort = 22;		//SSHでの接続ポート
	private static int DefaultMySqlSverPort = 3306;	//Mysqlサーバー接続ポート
	
	//MySQlに以下4つのスキーマ作ってください
	//同じスキーマに揃えても動くと思います（未検証）
	//但しOLDだけは古いデータ退避用なので別スキーマの方が良いと思います
	//中身のテーブルは後ほど自動生成します
	public static String MySqlDefaultSchemaWANKO = "WANKOscjema";
	public static String MySqlDefaultSchemaNYANKO = "NYANKOscjema";
	public static String MySqlDefaultSchemaPOST = "POSTscjema";
	public static String MySqlDefaultSchemaOLD = "OLDscjema";
	
	//iniファイルの置き場所 フルパス
	public static String IniPth = "C:\\MIZUNO\\WMS\\ini.txt";
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
	
    public static void main(String[] args) {
        //MYSQLへの接続設定読込外部ファイルにMYSQLデータベースへの接続設定を読込        
    	ArrayList<String> IniRead = B00010ToolsTextRead.TxtReadRtArray(IniPth,"UTF-8");
    	
    	for(int i=0;i<IniRead.size();i++) {
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshHostName", ""))) {
    			DefaultSshHostName = IniRead.get(i).replace("DefaultSshHostName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshUserName", ""))) {
    			DefaultSshUserName = IniRead.get(i).replace("DefaultSshUserName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshKeyFld", ""))) {
    			DefaultSshKeyFld = IniRead.get(i).replace("DefaultSshKeyFld", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshKeyFileName", ""))) {
    			DefaultSshKeyFileName = IniRead.get(i).replace("DefaultSshKeyFileName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshPass", ""))) {
    			DefaultSshPass = IniRead.get(i).replace("DefaultSshPass", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlHostName", ""))) {
    			DefaultMySqlHostName = IniRead.get(i).replace("DefaultMySqlHostName", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlUser", ""))) {
    			DefaultMySqlUser = IniRead.get(i).replace("DefaultMySqlUser", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlPass", ""))) {
    			DefaultMySqlPass = IniRead.get(i).replace("DefaultMySqlPass", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultSshHostPort", ""))) {
    			DefaultSshHostPort = Integer.parseInt(IniRead.get(i).replace("DefaultSshHostPort", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", ""));
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("DefaultMySqlSverPort", ""))) {
    			DefaultMySqlSverPort = Integer.parseInt(IniRead.get(i).replace("DefaultMySqlSverPort", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", ""));
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaWANKO", ""))) {
    			MySqlDefaultSchemaWANKO = IniRead.get(i).replace("MySqlDefaultSchemaWANKO", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaNYANKO", ""))) {
    			MySqlDefaultSchemaNYANKO = IniRead.get(i).replace("MySqlDefaultSchemaNYANKO", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaPOST", ""))) {
    			MySqlDefaultSchemaPOST = IniRead.get(i).replace("MySqlDefaultSchemaPOST", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    		if(!IniRead.get(i).equals(IniRead.get(i).replace("MySqlDefaultSchemaOLD", ""))) {
    			MySqlDefaultSchemaOLD = IniRead.get(i).replace("MySqlDefaultSchemaOLD", "").replace(" ", "").replace("=", "").replace("\"", "").replace(";", "");
    		}
    	}
    }
}