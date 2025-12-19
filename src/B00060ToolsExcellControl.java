import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class B00060ToolsExcellControl{
	//エクセルとの連携用のクラス
	
	public static void EXCELL_EXISTENCE(String FP){
		//FP:ファイルのフルパスを受け取って　対象エクセルが存在すれば何もしない
		//存在しないときはエクセルファイル作成
		//エクセルファイルの処理なのでフルパスの最終5文字が.xlsxであることが起動条件
		if(".xlsx".equals(FP.substring(FP.length()-5, FP.length()))){
			//存在チェック
			File file = new File(FP);
			if (file.exists()){
			}else{
				try {
					Workbook book = new XSSFWorkbook();
					FileOutputStream out = new FileOutputStream(FP);
					Sheet sheet = book.createSheet("create_book_wook");
					//A1セルだけ作って値をセット
				    Row row = sheet.createRow(0);
				    Cell a1 = row.createCell(0);
				    a1.setCellValue("エクセル新規作成初期シート");
				    book.write(out);
					out.close();
					book.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}
			}
		}
	}
	
	public static String[] ExcellSheetList(String FP){
		//対象エクセルのシート名を一覧で返却する
		String[] rt = new String[0];
		EXCELL_EXISTENCE(FP);
		try {
			Workbook book = WorkbookFactory.create(new FileInputStream(FP));
			rt = new String[book.getNumberOfSheets()];
			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				Sheet sheet = book.getSheetAt(i);
				rt[i] = sheet.getSheetName();
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}	
		return rt;
	}

	public static boolean EXCELL_EXISTENCE_SHEET(String FP,String Sheet_name){
		boolean rt = false;
		//FP:ファイルのフルパスを受け取って対象シートの存在チェック
		EXCELL_EXISTENCE(FP);
		//対象シートが存在する場合、何もしないでtrue返す
		//対象シートが存在しない場合、シート作成してfalse返す
		if(Sheet_name!=null&&!("".equals(Sheet_name))){
			try {
				Workbook book = WorkbookFactory.create(new FileInputStream(FP));
				for (int i = 0; i < book.getNumberOfSheets(); i++) {
					Sheet sheet = book.getSheetAt(i);
					String sname = sheet.getSheetName();
					if(Sheet_name.equals(sname)){
						rt = true;
					}
					if("create_book_wook".equals(sname)){
						//エクセルファイルを新規作成した際に強制的に作ったシートは削除
						FileOutputStream out = new FileOutputStream(FP);
						book.removeSheetAt(i);
						book.write(out);
						out.close();
					}
				}
				if(rt){
				}else{
					FileOutputStream out = new FileOutputStream(FP);
					Sheet sheet = book.createSheet(Sheet_name);
					//A1セルだけ作って値をセット
				    Row row = sheet.createRow(0);
				    Cell a1 = row.createCell(0);
				    a1.setCellValue("シート存在チェック初期作成シート");
				    book.write(out);
					out.close();
				}
				book.close();
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		return rt;
	}
	public static boolean EXCELL_SHEET_REFRESH(String FP,String Sheet_name){
		boolean rt = false;
		//FP:ファイルのフルパスを受け取って対象シートの存在チェック
		EXCELL_EXISTENCE(FP);
		//対象シートが存在する場合、シートリネーム
		//対象シートを新規作成
		//対象シートが存在していた場合true 存在せずに新規作成した場合false
		if(Sheet_name!=null&&!("".equals(Sheet_name))){
			//System.out.println(FP+":"+Sheet_name);
			try {
				Workbook book = WorkbookFactory.create(new FileInputStream(FP));
				for (int i = 0; i < book.getNumberOfSheets(); i++) {
					Sheet sheet = book.getSheetAt(i);
					String sname = sheet.getSheetName();
					if(Sheet_name.equals(sname)){
						rt = true;
						//対象シートが見つかった場合、現在の日付時刻を付与してリネーム

						Calendar cal= Calendar.getInstance();
						Timestamp ps=new Timestamp(cal.getTimeInMillis());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
						String DS = sdf.format(ps);

						FileOutputStream out = new FileOutputStream(FP);
						book.setSheetName(i,Sheet_name+DS+"BK");
						book.write(out);
						out.close();
					}
					if("create_book_wook".equals(sname)){
						//エクセルファイルを新規作成した際に強制的に作ったシートは削除
						FileOutputStream out = new FileOutputStream(FP);
						book.removeSheetAt(i);
						book.write(out);
						out.close();
					}
				}
				//新規に対象シートを作成
				FileOutputStream out = new FileOutputStream(FP);
				Sheet sheet = book.createSheet(Sheet_name);
				//A1セルだけ作るって値をセット
			    Row row = sheet.createRow(0);
			    Cell a1 = row.createCell(0);
			    a1.setCellValue("シート存在チェック初期作成シート");
			    book.write(out);
				out.close();
				book.close();
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		return rt;
	}

	public static void EXCELL_DATA_SET(String FP,String Sheet_name,String[][] SET ,int MFG,int OPFG){
		//対象エクセルの各セルに値をセット
		//元データの値は除去する
		//元も格納データも 1列目は空白禁止！！
		//MFG=0 なら数字意識せず生のデータ格納
		//OPFG==1なら出力後ファイルオープン
		//BlankNull=true なら　空白値には"null"セット
		EXCELL_EXISTENCE(FP);
		EXCELL_SHEET_REFRESH(FP,Sheet_name);
		if(SET.length>0){
			if(SET[0].length>0){
				Workbook book;
				try {
					book = WorkbookFactory.create(new FileInputStream(FP));
					Sheet sheet = book.getSheet(Sheet_name);
					FileOutputStream out = new FileOutputStream(FP);
					int counter = 0;
					//対象シートのセルを除去
					for (Row row : sheet) {
						counter = counter+1;
					}
					for(int i=0;i<counter;i++){
						Row row = sheet.getRow(counter-1-i);
						sheet.removeRow(row);
					}
					//対象シートの各セルに値をセット
					for(int i01=0;i01<SET.length;i01++){
						Row row = sheet.createRow(i01);
						for(int i02=0;i02<SET[i01].length;i02++){
						    Cell set_cell = row.createCell(i02);
						    if(SET[i01][i02]==null){SET[i01][i02]="";}
					    	DataFormat format = book.createDataFormat();
					    	CellStyle  style = book.createCellStyle();
					    	if(null==SET[i01][i02]) {SET[i01][i02]="";}
						    if(MFG==0){
						    	set_cell.setCellValue(SET[i01][i02]);
						    }else{
						    	//数字のみの文字列"."一個までは数字とみなす
							    //数字のみの文字列に変換後、文字列長に変化なければ数値とみなして値セット
							    String WS = B00020ToolsTextControl.num_only_String02(SET[i01][i02]);
							    if(SET[i01][i02].length()>1){
								    if("-".equals(SET[i01][i02].substring(0,1))){
						    			WS = "-"+B00020ToolsTextControl.num_only_String02(SET[i01][i02]);
						    		}
							    }
							    if(SET[i01][i02].length()==WS.length()&&!("".equals(SET[i01][i02]))){
							    	Double WD = Double.parseDouble(WS);
							    	//Double WD = Double.parseDouble(SET[i01][i02]);
							    	//セルの書式を数値として扱わせるために数式として格納
							    	set_cell.setCellFormula(""+WD);
							    }else{
							    	//計算式の文字列（※1文字目が=で始まる場合は計算式として処理）
							    	if(SET[i01][i02].length()>0){
							    		if("=".equals(SET[i01][i02].substring(0,1))){
									    	set_cell.setCellFormula(SET[i01][i02].substring(1, SET[i01][i02].length()));
									    }else{
									    	set_cell.setCellValue(SET[i01][i02]);
									    }
							    	}else{
							    		set_cell.setCellValue(SET[i01][i02]);
							    	}
							    }
						    }
						    //セル結合
							//sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 4));
						    //↑2行目～3行目　4列目～5列目を結合する　※1行目1列目　0で表現されるため上記表現
						}
					}
	
					book.write(out);
					out.close();
					book.close();
					System.gc();
					System.gc();
	
					if(OPFG==1){
						File file = new File(FP);
				        Desktop desktop = Desktop.getDesktop();
				        try {
							desktop.open(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void EXCELL_STYLE(String FP,String NEW_FP,String Sheet_name,String[][] SET ,int MFG,int OPFG){
		//SET[i01][i02] =セル名,格納値を受け取り　EX)B12　セルに　"TEST" という値を格納する場合は　SET[0][0] = "A1" SET[0][1] = "TEST" というデータを渡す
		//FPのエクセルBOOK内のSheet_nameのシートの対象セルに値格納
		//NEW_FPのエクセルBOOKとして保存する
		//エクセルbookとsheetが既に存在していることが条件になるので存在しない場合は作る
		EXCELL_EXISTENCE_SHEET(FP,Sheet_name);

		//poi が判断できるようにA2⇒1 0 TEST という配列にする
		Object[][] SET_DATA = new Object[SET.length][3];
		for(int i=0;i<SET.length;i++){
			String get_st = "";
			if(null!=SET[i][0]){get_st = SET[i][0];}
			int HIT_COUNT = 0;
			int SET_COL = 0;
			for(int i01=0;i01<get_st.length();i01++){
				String ws = get_st.substring(get_st.length()-1-i01,get_st.length()-i01);
				try {
					ws = ""+Integer.parseInt(ws);
				} catch (NumberFormatException nfex) {
					int wi01 = ABC_INT_RT(ws)+1;
					int wi02 = 1;
					for(int i02=0;i02<HIT_COUNT;i02++){
						wi02 = 26*wi02;
					}
					SET_COL = SET_COL + wi02*wi01;

					HIT_COUNT = HIT_COUNT+1;
				}
			}
			if(SET_COL>0){
				SET_COL = SET_COL-1;
			}
			int SET_ROW = 0;
			if(!("".equals(B00020ToolsTextControl.num_only_String(get_st)))){
				SET_ROW = Integer.parseInt(B00020ToolsTextControl.num_only_String(get_st));
			}
			if(SET_ROW>0){
				SET_ROW = SET_ROW-1;
			}

			String SET_STRING = "";
			if(null!=SET[i][1]){SET_STRING = SET[i][1];}

			SET_DATA[i][0] = SET_ROW;
			SET_DATA[i][1] = SET_COL;
			SET_DATA[i][2] = SET_STRING;
		}

		Workbook book;
		try {
			book = WorkbookFactory.create(new FileInputStream(FP));
			Sheet sheet = book.getSheet(Sheet_name);
			FileOutputStream out = new FileOutputStream(NEW_FP);


			for(int i=0;i<SET_DATA.length;i++){
				Row row1 = sheet.getRow((int)SET_DATA[i][0]);
				if(null==row1){row1 = sheet.createRow((int)SET_DATA[i][0]);}
				Cell set_cell = row1.getCell((int)SET_DATA[i][1]);
				if(null==set_cell){set_cell = row1.createCell((int)SET_DATA[i][1]);}

				if(MFG==0){
					set_cell.setCellValue((String)SET_DATA[i][2]);
				}else{
					if(0<((String)SET_DATA[i][2]).length()){
						//セットデータの1文字目が"="なら数式とみなす
						if("=".equals(((String)SET_DATA[i][2]).substring(0,1))){
							set_cell.setCellFormula(((String)SET_DATA[i][2]).substring(1, ((String)SET_DATA[i][2]).length()));
						}else{
							//数字だけの文字列の場合は0+対象データの数式として格納　※値を格納すると書式無視するため
							//数字のみの文字列"."一個までは数字とみなす
						    //数字のみの文字列に変換後、文字列長に変化なければ数値とみなして値セット
						    String WS = B00020ToolsTextControl.num_only_String02((String)SET_DATA[i][2]);
						    if(((String)SET_DATA[i][2]).length()==WS.length()){
						    	set_cell.setCellFormula("" + WS);
						    }else{
						    	set_cell.setCellValue((String)SET_DATA[i][2]);
						    }
						}
					}else{
						set_cell.setCellValue("");
					}
				}
			}
			book.write(out);
			out.close();
			book.close();
			System.gc();
			System.gc();
			if(OPFG==1){
				File file = new File(NEW_FP);
		        Desktop desktop = Desktop.getDesktop();
		        try {
					desktop.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

	}

	public static int ABC_INT_RT(String get_st){
		int get_int = 0;
		switch(get_st){
			case "A":get_int =  0;break;
			case "B":get_int =  1;break;
			case "C":get_int =  2;break;
			case "D":get_int =  3;break;
			case "E":get_int =  4;break;
			case "F":get_int =  5;break;
			case "G":get_int =  6;break;
			case "H":get_int =  7;break;
			case "I":get_int =  8;break;
			case "J":get_int =  9;break;
			case "K":get_int = 10;break;
			case "L":get_int = 11;break;
			case "M":get_int = 12;break;
			case "N":get_int = 13;break;
			case "O":get_int = 14;break;
			case "P":get_int = 15;break;
			case "Q":get_int = 16;break;
			case "R":get_int = 17;break;
			case "S":get_int = 18;break;
			case "T":get_int = 19;break;
			case "U":get_int = 20;break;
			case "V":get_int = 21;break;
			case "W":get_int = 22;break;
			case "X":get_int = 23;break;
			case "Y":get_int = 24;break;
			case "Z":get_int = 25;break;
			default:break;
		}
		return get_int;
	}
	
	public static Object[][] ExcellRead(String FP,String SheetName,int[] ColumnType,boolean HdFg){
		//FPで指定されたエクセルファイルのSheetNameシートの値をオブジェクトに格納して返却する
		//HdFg true ならヘッダ行ありとして１行目無視
		//想定される列分のColumnTypeで各列の属性指定
		//ColumnType[i]=0:数値
		//ColumnType[i]=1:文字列
		//ColumnType[i]=2:日付時刻
		//※日付時刻型はyyyy/mm/dd hh:mm:ss 又は空白文字列を返却する
		Object[][] rt = new Object[0][0];
		int MaxRow = 1048576;
		int MaxCal = 16383;
		int TgtEndRow = 0;
		int TgtEndcal = 0;
		try {
			Workbook book = WorkbookFactory.create(new FileInputStream(FP));
			Sheet sheet = book.getSheet(SheetName);
			/* シートの定義の最初の行 */
		    int firstRow = sheet.getFirstRowNum();
		    /* シートの定義の最終行 */
		    int lastRow = sheet.getLastRowNum();
		    
		    if(HdFg) {firstRow=firstRow+1;}
		    
		    rt = new Object[lastRow+1-firstRow][ColumnType.length];
		    int wint = lastRow+1-firstRow;
		    int counter = 0;
		    for(int i=firstRow;i<lastRow+1;i++) {
		    	Row row = sheet.getRow(i);
		    	for(int i01=0;i01<ColumnType.length;i01++) {
		    		Cell cell = row.getCell(i01);
		    		if(null!=cell) {
		    			Object value = null;
		    			switch(cell.getCellType()) {
							case 0:
								switch(ColumnType[i01]) {
				    				case 0 :
				    					//数値として取得→数値
				    					value = cell.getNumericCellValue();
				    					break;
				    				case 1 :
				    					//数値として取得→文字列
				    					value = (long)cell.getNumericCellValue();
			    						break;
				    				case 2 :
				    					//数値ではなく日付時刻として取得→日付時刻の型合わせ
				    					value = cell.getDateCellValue();
										String dtm = null;
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
										dtm = sdf.format(value);
										value = dtm;
			    						break;
				    				default:value="";
			    						break;
								}
								break;
							case 1:value = cell.getStringCellValue();
								switch(ColumnType[i01]) {
				    				case 0 :
				    					//文字列として取得→数値
				    					value = ""+cell.getStringCellValue();
				    					String Wst = ""+cell.getStringCellValue();
				    					Wst = B00020ToolsTextControl.num_only_String02(Wst);
				    					if("".equals(Wst)) {Wst="0";}
				    					value = Wst;
				    					break;
				    				case 1 :
				    					//文字列として取得→文字列
				    					value = cell.getStringCellValue();
			    						break;
				    				case 2 :
				    					value = cell.getStringCellValue();
			    						break;
				    				default:value="";
			    						break;
								}
								break;
							case 2:	//Cell.CELL_TYPE_FORMULA
								switch(ColumnType[i01]) {
			    				case 0 :
			    					//数値として取得→数値
			    					value = cell.getNumericCellValue();
			    					break;
			    				case 1 :
			    					//文字列として取得→文字列
			    					value = cell.getStringCellValue();
		    						break;
			    				case 2 :
			    					//数値ではなく日付時刻として取得→日付時刻の型合わせ
			    					value = cell.getDateCellValue();
									String dtm = null;
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
									dtm = sdf.format(value);
									value = dtm;
		    						break;
			    				default:value="";
		    						break;
							}
								break;
							case 3:	//Cell.CELL_TYPE_BLANK
								value="";
								break;
							case 4:	//Cell.CELL_TYPE_BOOLEAN
								value="";
								break;
							case 5:	//Cell.CELL_TYPE_ERROR
								value="";
								break;
							default:
								value = cell.getStringCellValue();
								break;
		    			}
		    			rt[counter][i01]=""+value ;
		    		}else {
		    			switch(ColumnType[i01]) {
		    				case 0 :rt[counter][i01]=0;
		    					break;
		    				case 1 :rt[counter][i01]="";
	    						break;
		    				case 2 :rt[counter][i01]="";
	    						break;
		    				default:rt[counter][i01]="";
	    						break;
		    			}
		    		}
		    	}
		    	counter = counter+1;
		    }
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rt;
	}
	
	public static Object[][] ExcellRead2(String FP,String SheetName){
		//配列要素としてエクセルのデータを返却
		Object[][] rt = new Object[0][0];
		int MaxRow = 1048576; 	//エクセルの最大行数
		int MaxCal = 16383;		//エクセルの最大の列数
		int TgtEndRow = 0;
		int TgtEndcal = 0;
		try {
			Workbook book = WorkbookFactory.create(new FileInputStream(FP));
			Sheet sheet = book.getSheet(SheetName);
			//NULLを返さない=情報が格納されている行の最大値を取得
			for(int i=0;i<MaxRow;i++) {
				 Row row = sheet.getRow(i);
				 if(null!=row) {
					 TgtEndRow=TgtEndRow+1;
				 }
			}
			//NULLを返さない=情報が格納されている列の最大値を格納
			if(TgtEndRow>0) {
				for(int i01=0;i01<TgtEndRow;i01++) {
					for(int i02=0;i02<MaxCal;i02++) {
						Row row = sheet.getRow(i01);
						Cell cell = row.getCell(i02);
						if(null!=cell) {
							if(TgtEndcal<i02+1) {
								TgtEndcal=i02+1;
							}
						}
					}
				}
			}

			rt = new Object[TgtEndRow][TgtEndcal];
			
			for(int i01=0;i01<TgtEndRow;i01++) {
				Row row = sheet.getRow(i01);
				for(int i02=0;i02<TgtEndcal;i02++) {
					Cell cell = row.getCell(i02);
					try {
						Object value = null;
						if(null!=cell){
							switch(cell.getCellType()) {
								case 0:value = cell.getNumericCellValue();
									//value = cell.getStringCellValue();
									break;
								case 1:value = cell.getStringCellValue();
									break;
								default:
									value = cell.getStringCellValue();
									break;
							}
						}else {
							value="";
						}
						//
						if("null".equals(value)) {value="";}
						rt[i01][i02] = B00020ToolsTextControl.Trim(""+value);
					} catch (IllegalStateException e) {
						rt[i01][i02] = "";
					}
					
				}
			}
			book.close();
			System.gc();
			System.gc();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rt;
	}
}