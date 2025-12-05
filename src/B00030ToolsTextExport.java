import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class B00030ToolsTextExport{
	//テキスト出力を行うためのツール群
	// ==========================================================================
    //  B00030ToolsTextExport（プロメーテウス）
    // ==========================================================================
	
	//行ごとのArrayListを受け取って、指定されたファイルパスにテキストデータ出力する
	public static void txt_exp2(ArrayList<String> line_txt,String fp,String TxtType){
		//フルパスからフォルダ名取得 フォルダなければ作る
		String dirName = B00040ToolsFolderCheck.FILE_FLD(fp) ;
		B00040ToolsFolderCheck.FLD_CHECK(dirName);
		
		if(null==TxtType||"".equals(TxtType)) {TxtType="UTF-8";}
		try (BufferedWriter out01 = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(fp,false),TxtType));){
			//UTF-8ならBOM生成
			if("UTF-8".equals(TxtType)) {
				out01.write("\ufeff");
			}
			for(int i=0;i<line_txt.size();i++){
				if(line_txt.get(i) == null){line_txt.set(i, "");}
				out01.write(line_txt.get(i));
				out01.newLine();
			}
			JOptionPane.showMessageDialog(null, fp + "にデータ書き出ししました");
		    out01.close();
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "テキストデータの書き出しに失敗しました", "警告", 0);
			e2.printStackTrace();
		}
	}
	
	//行ごとのArrayListを受け取って、指定されたファイルパスにテキストデータ出力する
	//出力メッセージなし
	public static void txt_exp3(ArrayList<String> line_txt,String fp){
		//フルパスからフォルダ名取得 フォルダなければ作る
		String dirName = B00040ToolsFolderCheck.FILE_FLD(fp) ;
		B00040ToolsFolderCheck.FLD_CHECK(dirName);
		
		try (BufferedWriter out01 = new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(fp,false),"MS932"));){
			for(int i=0;i<line_txt.size();i++){
				//System.out.println(line_txt[i]);
				if(line_txt.get(i) == null){line_txt.set(i, "");}
				out01.write(line_txt.get(i));
				out01.newLine();
			}
	        out01.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void txt_exp4(ArrayList<String> line_txt,String fp,String TxtType){
		//フルパスからフォルダ名取得 フォルダなければ作る
		String dirName = B00040ToolsFolderCheck.FILE_FLD(fp) ;
		B00040ToolsFolderCheck.FLD_CHECK(dirName);
		
		if(null==TxtType||"".equals(TxtType)) {TxtType="UTF-8";}
		try (BufferedWriter out01 = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(fp,false),TxtType));){
			//UTF-8ならBOM生成
			if("UTF-8".equals(TxtType)) {
				out01.write("\ufeff");
			}
			for(int i=0;i<line_txt.size();i++){
				if(line_txt.get(i) == null){line_txt.set(i, "");}
				out01.write(line_txt.get(i));
				out01.newLine();
			}
		    out01.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	//配列要素受け取ってcsv出力する
	public static void create_csv(String[][] OUTPUT_DATA,String fp,String TxtType){
		//フルパスからフォルダ名取得 フォルダなければ作る
		String dirName = B00040ToolsFolderCheck.FILE_FLD(fp) ;
		B00040ToolsFolderCheck.FLD_CHECK(dirName);
		
		if(null==TxtType||"".equals(TxtType)) {TxtType="UTF-8";}
		try (BufferedWriter out01 = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(fp,false),TxtType));){
			//UTF-8ならBOM生成
			if("UTF-8".equals(TxtType)) {
				out01.write("\ufeff");
			}
			for(int i01=0;i01<OUTPUT_DATA.length;i01++){
				String WST = "";
				for(int i02=0;i02<OUTPUT_DATA[i01].length;i02++) {
					if(null==OUTPUT_DATA[i01][i02]) {OUTPUT_DATA[i01][i02]="";}
					//カンマもしくは改行が含まれていればダブルコーテーションでくくる
					if(OUTPUT_DATA[i01][i02].equals(OUTPUT_DATA[i01][i02].replace(",", ""))&&OUTPUT_DATA[i01][i02].equals(OUTPUT_DATA[i01][i02].replace("\n", ""))) {
						WST = WST+OUTPUT_DATA[i01][i02]+",";
					}else {
						WST = WST+"\""+OUTPUT_DATA[i01][i02]+"\",";
					}
				}
				out01.write(WST);
				out01.newLine();
			}
			JOptionPane.showMessageDialog(null, fp + "にデータ書き出ししました");
		    out01.close();
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "テキストデータの書き出しに失敗しました", "警告", 0);
			e2.printStackTrace();
		}
	}

}