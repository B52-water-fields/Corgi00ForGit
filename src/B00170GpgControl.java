public class B00170GpgControl{
	private static String DataDecrypt(String TgtFldPath,String PassWord) {
		//対象フォルダにある.gpgファイルを自分の秘密鍵で復号化するコマンド出力
		//複合化後元ファイルは同一フォルダ内のBKフォルダに移動させる
		String Rt = ""
				+ "cd "+TgtFldPath+"\n"
				+ "dir *.gpg /b >DecryptTgtList.txt\n"
				+ "for /f %%a in (DecryptTgtList.txt) do (\n"
				+ "  gpg --pinentry-mode loopback --decrypt-file --passphrase "+PassWord+" %%a\n"
				+ "  move %%a BK/%%a\n"
				+ ")\n"
				+ "exit\n";
		
		return Rt;
	}
	
	private static String DataEncrypt(String TgtFldPath,String Cryptography) {
		//対象フォルダにあるファイルを.gpg暗号化するコマンド出力
		//暗号化後元ファイルは同一フォルダ内のBKフォルダに移動させる
		//Cryptography←利用する公開鍵
		String Rt = ""
				+ "cd "+TgtFldPath+"\n"
				+ "dir *.gpg /b >EncryptTgtList.txt\n"
				+ "for /f %%a in (EncryptTgtList.txt) do (\n"
				+ "  gpg --encrypt-file --recipient "+Cryptography+" %%a\n"
				+ "  move %%a BK/%%a\n"
				+ ")\n"
				+ "exit\n";
		
		return Rt;
	}
}