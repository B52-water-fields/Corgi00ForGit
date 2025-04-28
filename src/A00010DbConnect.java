import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class A00010DbConnect {
	//データベースに接続する
	public static Connection conn;
	public static Session session;
	
	public static void DB_CONN(String TgtDB){
		//データベース接続設定引継ぎ
		String SshHostName = A00000Main.DefaultSshHostName;
		int SshHostPort = A00000Main.DefaultSshHostPort;
		String SshUserName = A00000Main.DefaultSshUserName;
		String SshKeyFld = A00000Main.DefaultSshKeyFld;
		String SshKeyFileName = A00000Main.DefaultSshKeyFileName;
		String SshKeyFile = A00000Main.DefaultSshKeyFld + "\\" + A00000Main.DefaultSshKeyFileName;
		String SshPass = A00000Main.DefaultSshPass;
		String MySqlHostName = A00000Main.DefaultMySqlHostName;
		int MySqlSverPort = A00000Main.DefaultMySqlSverPort;
		String MySqlUser = A00000Main.DefaultMySqlUser;
		String MySqlPass = A00000Main.DefaultMySqlPass;
		String MySqlDefaultSchema = "";
		
		if("WANKO".equals(TgtDB)) {
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaWANKO;
		}else if("NANKO".equals(TgtDB)){
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaNYANKO;
			
		}else if("POST".equals(TgtDB)){
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaPOST;
			
		}else if("NYANKO".equals(TgtDB)){
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaNYANKO;
			
		}else if("OLD".equals(TgtDB)) {
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaOLD;
			
		}else {
			
		}
		if(!"".equals(MySqlDefaultSchema)) {
			boolean SSHCick = false;
			
			if(null==session) {
				SSHCick = true;
			}else {
				SSHCick=MySqlConnect(
						SshHostName,
						SshHostPort,
						SshUserName,
						SshKeyFld,
						SshKeyFileName,
						SshKeyFile,
						SshPass,
						MySqlHostName,
						MySqlSverPort,
						MySqlUser,
						MySqlPass,
						MySqlDefaultSchema
							);
			}
			
			if(SSHCick) {
				MySqlSSH(
						SshHostName,
						SshHostPort,
						SshUserName,
						SshKeyFld,
						SshKeyFileName,
						SshKeyFile,
						SshPass,
						MySqlHostName,
						MySqlSverPort,
						MySqlUser,
						MySqlPass,
						MySqlDefaultSchema
							);
			}
		}
	}
	private static boolean MySqlConnect(
			String SshHostName,
			int SshHostPort,
			String SshUserName,
			String SshKeyFld,
			String SshKeyFileName,
			String SshKeyFile,
			String SshPass,
			String MySqlHostName,
			int MySqlSverPort,
			String MySqlUser,
			String MySqlPass,
			String MySqlDefaultSchema
				) {
		//既にSSH接続されている前提でつなぎに行く。※タイムアウトしている場合エラーになる
		boolean SSHKick = false;
		
		// JBBCドライバクラスのロード
	    try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "JDBCドライバの取得に失敗しました");
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	    // Connectionの作成
	    try {
	    	/**/            
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1" +"" + ":"+SshHostPort+""+ "/"+MySqlDefaultSchema+"?verifyServerCertificate=false&useSSL=false"
					,MySqlUser
					,MySqlPass
					);
	    } catch (SQLException e1) {
	    	SSHKick = true;
		} 
		return SSHKick;
	}
	
	
	
	private static void MySqlSSH(
		String SshHostName,
		int SshHostPort,
		String SshUserName,
		String SshKeyFld,
		String SshKeyFileName,
		String SshKeyFile,
		String SshPass,
		String MySqlHostName,
		int MySqlSverPort,
		String MySqlUser,
		String MySqlPass,
		String MySqlDefaultSchema
			) {
		//SSH接続→データベース接続
		JSch jsch = new JSch();
		try {
			if(conn!=null){conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(session!=null) {session.disconnect();}
	    try {
	        jsch.addIdentity(SshKeyFile,SshPass);
	        //jsch.setKnownHosts(SshKnownHosts);
	        session = jsch.getSession(SshUserName, SshHostName, SshHostPort);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        session.setPortForwardingL(SshHostPort, MySqlHostName, MySqlSverPort);
		} catch (JSchException e) {
			JOptionPane.showMessageDialog(null, "SSH接続エラーになりました");
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	    // JBBCドライバクラスのロード
	    try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "JDBCドライバの取得に失敗しました");
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	    // Connectionの作成
	    try {
	    	/**/
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1" +"" + ":"+SshHostPort+""+ "/"+MySqlDefaultSchema+"?verifyServerCertificate=false&useSSL=false"
					,MySqlUser
					,MySqlPass
					);
			/**/
	    } catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "データベース接続に失敗しました");
			JOptionPane.showMessageDialog(null, e1);
			e1.printStackTrace();
		}
		
	}
	public static void close() {
		//データベース接続だけ切る
		try {
			if(conn!=null){conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.gc();
	}
	public static void Fullclose() {
		//データベース接続とSSH接続　両方切る
		try {
			if(conn!=null){conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(session!=null) {session.disconnect();}
		System.gc();
	}
	
}