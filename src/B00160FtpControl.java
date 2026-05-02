import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class B00160FtpControl{
	//Ftp接続してファイル操作を行うクラス
	public static FTPClient FtpCd(FTPClient client,String CdTgt) {
		//対象フォルダに移動
		try {
			if(null!=CdTgt&&!"".equals(CdTgt)) {
				client.changeWorkingDirectory(CdTgt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public static void FtpPut(FTPClient client,String PutDataPath) {
		String FileName = B00040ToolsFolderCheck.FILENAME(PutDataPath);
		try {
			client.storeFile(FileName, new FileInputStream(PutDataPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void FtpGet(FTPClient client,String GetDataName,String SetDataFld,String SetDataName) {
		try {
			FileOutputStream Fos	= new FileOutputStream(SetDataFld+"\\"+SetDataName);
			client.retrieveFile(GetDataName,Fos);
			Fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void FtpDelete(FTPClient client,String TgtFileName) {
		try {
			client.deleteFile(TgtFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void FtpReName(FTPClient client,String TgtDataName,String NewName) {
		try {
			client.rename(TgtDataName,NewName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String[][] FtpList(FTPClient client) {
		//FTP接続して対象ディレクトリ内のディレクトリとファイル一覧を取得
		//対象がファイルなら　		Rt[i][0] = "File"
		//対象がディレクトリなら　	Rt[i][0] = "Dir"
		//対象がそれ以外なら　	　　Rt[i][0] = "Other"
		//生ファイル名はRt[i][1]に格納
		//対象名称をUTF-8にエンコードしてRt[i][2]に格納
		//対象がファイルだった場合ファイルサイズをRt[i][3]に格納　それ以外では0
		
		String[][] Rt = new String[0][4];
		//ファイル一覧を取得
		FTPFile[] files;
		try {
			files = client.listFiles();
			int RtCount = 0;
			for(int i=0;i<files.length;i++) {
				FTPFile TgtFile = files[i];
				if(null!=TgtFile) {
					RtCount = RtCount+1;
				}
			}
			Rt = new String[RtCount][4];
			RtCount = 0;
			for(int i=0;i<files.length;i++) {
				FTPFile TgtFile = files[i];
				if(null!=TgtFile) {
					Rt[RtCount][1]	= TgtFile.getName();
					Rt[RtCount][2]  = new String(Rt[RtCount][0].getBytes("ISO-8859-1"), "UTF-8");
					if(TgtFile.isFile()) {
						Rt[RtCount][0]	= "File";
						Rt[RtCount][3]	= ""+TgtFile.getSize();
					}else if(TgtFile.isDirectory()) {
						Rt[RtCount][0]	= "Dir";
						Rt[RtCount][3]	= "0"; 
					}else {
						Rt[RtCount][0]	= "Other";
						Rt[RtCount][3]	= "0";
					}
					RtCount = RtCount+1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Rt;
	}
	
	public static FTPClient FtpConnect(
			String ServerAdd,		//FTP接続先サーバーアドレス
			String UserName,		//FTP接続ユーザー名
			String PassWord,		//FTP接続ユーザーパスワード
			int PortNo
			) {
		//Ftp接続ログイン後対象ディレクトリに移動する
		// FTP CLIENT 作成
		FTPClient client = new FTPClient();
		//FTP接続
		try {
			client.connect(ServerAdd, PortNo);
			//ログイン
			Object result = client.login(UserName, PassWord);
			//転送モード設定：PASV モード
			client.enterLocalPassiveMode();
			//BINARY モード
			client.setFileType(FTP.BINARY_FILE_TYPE);
			
			//client.setControlEncoding("UTF-8");エンコードするとかえってファイル名を壊すのでオミット
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public static void FtpDisconnect(FTPClient client) {
		//Ftp接続終了
		try {
			if (client != null && client.isConnected()) {
				client.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}