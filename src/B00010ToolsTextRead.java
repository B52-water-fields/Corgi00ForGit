import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.mozilla.universalchardet.UniversalDetector;

public class B00010ToolsTextRead{
	//.csvファイル　.txtファイルなどテキストファイルを読み込むためのクラス
	
	//行ごとの配列を受け取って、指定されたファイルパスにテキストデータ出力する
	//テキストファイル読み取って行単位のArrayListにして返却する
	public static ArrayList<String> TxtReadRtArray(String fp,String TxtType) {
		ArrayList<String> rt=new ArrayList<String>();
		BufferedReader in =null;
		//TxtType "MS932","UTF-8","SHIFT-JIS"等でテキストデータの文字タイプを設定
		//基本は設定することを推奨
	    
		try {
			boolean HasBomFg = false;
			InputStream inputStream = new FileInputStream(fp);
			BOMInputStream bomIn = new BOMInputStream(inputStream);
			 if (bomIn.hasBOM()) {
				 ByteOrderMark bom = bomIn.getBOM();
					TxtType =  bom.getCharsetName();
			 }	
			if(null==TxtType||"".equals(TxtType)) {				
				 java.io.FileInputStream fis = new java.io.FileInputStream(fp);
				 byte[] buf = new byte[4096];
				     UniversalDetector detector = new UniversalDetector(null);

				     int nread;
				     while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				       detector.handleData(buf, 0, nread);
				     }

				     detector.dataEnd();
				     TxtType =  detector.getDetectedCharset();
				if(null==TxtType||"".equals(TxtType)) {
					in =new BufferedReader(
							new InputStreamReader(
								new FileInputStream(fp)));
				}else {
					in =new BufferedReader(
							new InputStreamReader(
								new FileInputStream(fp),TxtType));
				}
				
			}else {
				in =new BufferedReader(
						new InputStreamReader(
							new FileInputStream(fp),TxtType));
			}
			String s ="";
			while((s=in.readLine()) !=null){
				rt.add(s);
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return rt;
	}
	//CSV明細行を配列要素として返却　ダブルコーテンションでくくられた文字列内改行を除去後のデータを引き渡す前提
	public static String[] CsvSplit(String TgtString) {
		String[] rt=new String[1];
		if(null!=TgtString) {
			rt[0]=TgtString;
			//カンマ区切りデータかどうかを確認
			if(!TgtString.equals(TgtString.replace(",", ""))) {
				//","区切りの存在を疑う ",又は,"が発生しているはず
				if(!TgtString.equals(TgtString.replace("\",", ""))||!TgtString.equals(TgtString.replace(",\"", ""))) {
					//最終がカンマで終わる場合、最終文字列を,""にする
					if(",".equals(TgtString.substring(TgtString.length()-1,TgtString.length()))) {
						TgtString=TgtString+"\"\"";
					}
					//まずはカンマでSpritする
					String[] WST=TgtString.split(",");
					
					int counter=0;
					ArrayList<String> WArray=new ArrayList<String>();
					while(counter<WST.length) {
						if(2<=WST[counter].length()&&"\"".equals(WST[counter].substring(0,1))&&"\"".equals(WST[counter].substring(WST[counter].length()-1,WST[counter].length()))) {
							//2文字以上で自分自身が"で始まり"で終わる⇒自分自身の中にカンマを含まない単純な文字列
							//先頭と最終の"を除去
							WST[counter]=WST[counter].substring(1,WST[counter].length()-1);
							WArray.add(WST[counter]);
						}else if(1<=WST[counter].length()&&"\"".equals(WST[counter].substring(0,1)))  {
							//自分自身が"で始まり"で終わらない⇒自分以降の文字列に終了が"で終わる文字列を見つけるまでカンマを加えて結合を繰り返し
							
							for(int i01=counter+1;i01<WST.length;i01++) {
								if(0==WST[i01].length()) {
									WST[counter]=WST[counter]+","+WST[i01];
								}else if("\"".equals(WST[i01].substring(WST[i01].length()-1,WST[i01].length()))) {
									WST[counter]=WST[counter]+","+WST[i01];
									//先頭と最終の"を除去
									WST[counter]=WST[counter].substring(1,WST[counter].length()-1);
									WArray.add(WST[counter]);
									//次の判別文字列は結合した文字列の次の文字列が対象
									counter=i01;
									i01=WST.length+1;
								}else {
									WST[counter]=WST[counter]+","+WST[i01];
								}
							}
						}else {
							//自分自身が"で始まらない時は何もしない
							WArray.add(WST[counter]);
						}
						
						counter=counter+1;
					}
					rt=new String[WArray.size()];
					for(int i=0;i<WArray.size();i++) {
						rt[i]=WArray.get(i);
					}
				}else {
					//単純なカンマ区切りの場合普通にSprit
					rt=TgtString.split(",");
				}
			}
		}
		return rt;
	}
	
	//CSVデータを配列要素として読込
	public static String[][] CSVRead(String fp,String TxtType){
		String[][] rt=new String[0][0];
		ArrayList<String> TgtArray = TxtReadRtArray(fp,TxtType);
		if(null!=TgtArray&&0<TgtArray.size()) {
			//","区切りの存在を疑う ",又は,"が発生しているはず
			boolean DoubleQuotationFg=false;
			for(int i=0;i<TgtArray.size();i++) {
				if((""+TgtArray.get(i)).equals((""+TgtArray.get(i)).replace("\",", ""))||(""+TgtArray.get(i)).equals((""+TgtArray.get(i)).replace(",\'", ""))) {
					DoubleQuotationFg=true;
				}
			}
			ArrayList<String> SetArray = new ArrayList<String>();
			if(DoubleQuotationFg) {
				int DoubleQuotationStrCount = 0;
				int DoubleQuotationEndCount = 0;
				
				//行の中の文字列くくり先頭の数と終了の数を数える
				int counter=0;
				while(counter<TgtArray.size()) {
					DoubleQuotationStrCount = 0;
					DoubleQuotationEndCount = 0;
					String WST=""+TgtArray.get(counter);
					if(0<WST.length() &&"\"".equals(WST.substring(0,1))){
						DoubleQuotationStrCount=1;
					}
					if(1<WST.length() &&"\"".equals(WST.substring(WST.length()-1,WST.length()))){
						DoubleQuotationEndCount = 1;
					}
					DoubleQuotationStrCount=DoubleQuotationStrCount+(WST.length()-WST.replace(",\"", "").length())/2;
					DoubleQuotationEndCount=DoubleQuotationEndCount+(WST.length()-WST.replace("\",", "").length())/2;
					
					if(DoubleQuotationStrCount==DoubleQuotationEndCount) {
						SetArray.add(WST);
					}else {
						for(int i01=counter+1;i01<TgtArray.size();i01++) {
							DoubleQuotationStrCount = 0;
							DoubleQuotationEndCount = 0;
							WST = WST + TgtArray.get(i01);
							
							if(0<WST.length() &&"\"".equals(WST.substring(0,1))){
								DoubleQuotationStrCount=1;
							}
							if(1<WST.length() &&"\"".equals(WST.substring(WST.length()-1,WST.length()))){
								DoubleQuotationEndCount = 1;
							}
							DoubleQuotationStrCount=DoubleQuotationStrCount+(WST.length()-WST.replace(",\"", "").length())/2;
							DoubleQuotationEndCount=DoubleQuotationEndCount+(WST.length()-WST.replace("\",", "").length())/2;
							if(DoubleQuotationStrCount==DoubleQuotationEndCount) {
								SetArray.add(WST);
								counter=i01;
								i01=TgtArray.size()+1;
							}
						}
					}
					counter=counter+1;
				}
			}else {
				SetArray=TgtArray;
			}
			
			//返却する配列の列数を決定するためにカンマ区切り⇒配列へ
			int MaxClmCount=0;
			for(int i=0;i<SetArray.size();i++) {
				String[] Split00 = CsvSplit(""+SetArray.get(i));
				if(MaxClmCount<Split00.length) {MaxClmCount=Split00.length;}
			}
			rt = new String[SetArray.size()][MaxClmCount];
			for(int i=0;i<SetArray.size();i++) {
				for(int i01=0;i01<rt[i].length;i01++) {
					rt[i][i01]="";
				}
				
				String[] Split00 = CsvSplit(""+SetArray.get(i));
				for(int i01=0;i01<Split00.length;i01++) {
					rt[i][i01]=Split00[i01];
				}
			}
		}
		return rt;
	}
}