import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class B00040ToolsFolderCheck{
	//フォルダ存在チェック・ファイル一覧取得など
	//ファイル操作系のツール群
	// ==========================================================================
    //  B00040ToolsFolderCheck（ヘルメスの開かれた書庫を建立）
    // ==========================================================================
	
	//フォルダ存在チェック→なければ作る　作成失敗したらfalse返す
	public static boolean FLD_CHECK(String FLD_PATH){
		File file_dir = new File(FLD_PATH);
		boolean rt = true;
        if (file_dir.exists()) {
        } else {
        	if (file_dir.mkdirs()){
        	}else{
        	    rt = false;
        	}
        }
        return rt;
	}
	//フォルダ存在チェックのみ→なければfalse返す
	public static boolean FLD_CHECK_ONRY(String FLD_PATH){
		File file_dir = new File(FLD_PATH);
		boolean rt = true;
        if (file_dir.exists()) {
        } else {
        	rt = false;
        }
        return rt;
	}
	//フルパスからファイル名取得
	public static String FILENAME(String path) {
        File file = new File(path);
        return file.getName();
    }

	//フルパスからフォルダ名取得
	public static String FILE_FLD(String path) {
        String dirName = new File(path).getParent();
        return dirName;
    }

	//フルパスから拡張子取得
	public static String FILE_TYPE(String path) {
		 File file = new File(path);
		 String FileType = "";

		 if (file.exists()) {
			 String FileName = file.getName();
			 boolean UnHitFg=true;
			 for(int i=0;i<FileName.length();i++) {
				 if(UnHitFg) {
					 if(".".equals(FileName.substring(FileName.length()-1-i,FileName.length()-i))) {
						 UnHitFg=false;
					 }else {
						 FileType=FileName.substring(FileName.length()-1-i,FileName.length()-i)+FileType;
					 }
				 }
			 }
			 FileType = ""+FileType;
	     }
        return FileType;
    }

	//ファイルバックアップ
	//元ファイル情報格納フォルダ　バックアップファイル格納フォルダ　ファイル名を受け取りデータ移動する
	//移動時に（yyyymmddhhmmss付加）
	public static void FILE_BACKUP(String FP,String BFP,String FN){
		//FP：元ファイルディレクトリ　BFP：先ファイルディレクトリ　FN：対象ファイル名
		//現在の日付時刻取得
		Calendar cal= Calendar.getInstance();
		Timestamp ps=new Timestamp(cal.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String DS = sdf.format(ps);

		//バックアップフォルダ存在チェック　なければ作る
		FLD_CHECK(BFP);

		//バックアップフォルダにファイル移動
		File file1 = new File(FP+"/"+FN);
        File file2 = new File(BFP+"/"+FN);
        try {
            file1.renameTo(file2);
        } catch (SecurityException e) {
        } catch (NullPointerException e) {
        }

		//FNファイル名最終から"."の位置を特定し拡張子を取得
		//拡張子の取得に失敗したら.bakで保存
		String ext = ".bak";
		int cfg=0;
		int i=FN.length();
		while(cfg==0||i==0){
			if(".".equals(FN.substring(i-1,i))){
				cfg = 1;
				ext = FN.substring(i-1,FN.length());
				FN = FN.substring(0,i-1)+DS+ext;
			}
			i=i-1;
			if(cfg==0&&i==0){
				FN=FN+DS+ext;
			}
		}

		//ファイル名リネーム
		file1 = file2;
        file2 = new File(BFP+"/"+FN);
        try {
            file1.renameTo(file2);
        } catch (SecurityException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
	}

	//フォルダ内ファイル名（フルパスで）取得
	public static String[] FILE_NAME(String FP){
		//ファイル名の一覧を取得する
        File file = new File(FP);
        File files[] = file.listFiles();
        String[] file_list = new String[files.length];
        //取得した一覧を文字列に格納する
        for (int i=0; i<files.length; i++) {
        	file_list[i] = ""+files[i];
        }
        return file_list;
	}

	//フォルダ内ファイル名取得サブフォルダ内のファイル名も（フルパスで）取得
	public static String[] FILE_NAMES(String FP){
		//ファイル名の一覧を取得する
        File file = new File(FP);
        File files[] = file.listFiles();
        ArrayList<String> file_list_ar = new ArrayList<String>();
        //取得した一覧を文字列に格納する
        for (int i=0; i<files.length; i++) {
        	file_list_ar.add(""+files[i]);
        	if (files[i].isFile()){
        	}else if (files[i].isDirectory()){
        		//対象がディレクトリなら、そのディレクトリを対象に自分を呼び出して結果格納
        		String[] sb_work = FILE_NAMES(""+files[i]);
        		for(int i01=0;i01<sb_work.length;i01++){
        			file_list_ar.add(sb_work[i01]);
        		}
        	}
        }
        String[] file_list = new String[file_list_ar.size()];
        for (int i=0; i<file_list_ar.size(); i++) {
        	if(file_list_ar.get(i)==null){file_list_ar.set(i,"");}
        	file_list[i] = (String)file_list_ar.get(i);
        }
        return file_list;
	}
	//フォルダ内ファイル名取得　取得したファイルがファイルかディレクトリかも合わせて返す
	//ファイルの場合　　　file_list[i][1] = "FILE";
	//ディレクトリの場合　file_list[i][1] = "DIR";
	public static String[][] FileNameDIR(String FP){
		//ファイル名の一覧を取得する
        File file = new File(FP);
        File files[] = file.listFiles();
        String[][] file_list = new String[files.length][2];
        //取得した一覧を格納する
        for (int i=0; i<files.length; i++) {
        	file_list[i][0] = ""+files[i];
        	file_list[i][1] = "";
        	if (files[i].isFile()){
        		file_list[i][1] = "FILE";
        	}else if (files[i].isDirectory()){
        		file_list[i][1] = "DIR";
        	}
        }
        return file_list;
	}
	//ファイル削除
	public static void FILE_DEL(String FILE_PATH){
		//System.out.println(FILE_PATH);
		File file_dir = new File(FILE_PATH);
        file_dir.delete();
        System.gc();
	}
	//ファイルコピー
	public static void FILE_COPY(String FROM_FILE_PATH,String TO_FILE_PATH){
		Path inputPath = FileSystems.getDefault().getPath(FROM_FILE_PATH);
		Path outputPath = FileSystems.getDefault().getPath(TO_FILE_PATH);
		try {
			Files.copy(inputPath, outputPath);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		System.gc();
	}
	
	public static void ToolsOldFileDelete(String FP ,String FileType,int DelDate) {
		//フォルダにファイルパスと拡張子（Ex：csv pdf）を受け取って DelDate日数以前のファイルを削除する
		if(".".equals(FileType.substring(0,1))) {FileType=FileType.substring(1,FileType.length());}
		
		String BatText = "cd " +FP+"\n"
				+"forfiles /p "+FP+" /d -"+DelDate+" /m \"*."+FileType+"\" /c \"cmd /c del @file\""+"\n"
				+"exit\n";
		
		ArrayList<String> OutPutTxt = new ArrayList<String>();
		
		OutPutTxt.add(BatText);
		
		String FilePath=FP+"\\"+FileType+"FileDelBat.bat";
		
		B00030ToolsTextExport.txt_exp3(OutPutTxt,FilePath);
		
		String bat_path = "cmd.exe /c start "+FilePath;
		
		try {
	      Runtime.getRuntime().exec(bat_path);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	public static void zip_unzip(String INPUT_FP,String OUTPUT_FP){
		//ZIPファイルを解凍する
		//INPUT_FP:対象ZIPファイルフルパス
		//OUTPUT_FP:出力先フォルダ
		//アウトプットフォルダはOUTPUT_FP\ZIPファイル名とする
		//フルパスからファイル名取得
		if(INPUT_FP !=null && OUTPUT_FP!=null && !("".equals(OUTPUT_FP)) && !("".equals(OUTPUT_FP))){
			String ENTRY_OUTPUT_FP = OUTPUT_FP +"/"+ B00040ToolsFolderCheck.FILENAME(INPUT_FP).replace(".zip","");
			//アウトプットフォルダ存在チェック　なければ作る
			B00040ToolsFolderCheck.FLD_CHECK(ENTRY_OUTPUT_FP);

	        File file = new File(INPUT_FP);
	        File outDir = new File(OUTPUT_FP);
	        ZipFile zipFile;
			try {
		        zipFile = new ZipFile(INPUT_FP, "Windows-31J");
		        @SuppressWarnings("unchecked")
		        Enumeration<? extends ZipEntry> entries = zipFile.getEntries();
		        while (entries.hasMoreElements()) {
		            ZipEntry entry = entries.nextElement();
		            if( entry.isDirectory() ){
		            	//System.out.println(entry.getName());
	                    //------------------------------
	                    // ディレクトリだった場合は、
	                    // 出力先ディレクトリを作成する
	                    //------------------------------
		            	B00040ToolsFolderCheck.FLD_CHECK(OUTPUT_FP+"/"+entry.getName());
	                }
		        }
		        entries = zipFile.getEntries();
		        FileOutputStream fileOut = null;
		        while (entries.hasMoreElements()) {
		        	ZipEntry entry = entries.nextElement();
		        	if( entry.isDirectory() ){

		        	}else{
		        		//System.out.println(entry.getName());
		        		//------------------------------
		                // ファイルの場合は出力する
		                //------------------------------
		        		File outFile = new File(OUTPUT_FP+"/"+entry.getName() );
		        		fileOut = new FileOutputStream(outFile);
		        		byte[] buf  = new byte[1024];
	                    int    size = 0;
	                    InputStream is = zipFile.getInputStream(entry);
	                    while( ( size = is.read( buf ) ) > 0 ){
	                        fileOut.write( buf, 0, size );
	                    }
	                    fileOut.close();
	                    fileOut = null;
		        	}
	            }
		        zipFile.close();
			} catch (ZipException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			FileInputStream  fileIn  = null;
			FileOutputStream fileOut = null;
			//能動的にガベージコレクションキック
			System.gc();
		}
	}
}