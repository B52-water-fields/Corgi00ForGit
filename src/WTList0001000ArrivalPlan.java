import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class WTList0001000ArrivalPlan{
	public static void ArrivalPlanList0001(String TgtWhCd,String TgtClCd,ArrayList<String> ArrNoList,boolean NewPrintOnly) {
		//入荷予定リストを発行し、印刷済みとして登録する
		
		if(null!=ArrNoList && 0<ArrNoList.size()) {
			String OutPutPath = OutPutPath();												//ファイル保存先確保・ファイル名決定
			Object[][] PrintControlRt 	= PrintDataGet(TgtWhCd,TgtClCd,ArrNoList);		//印刷済み情報を取得
			ArrayList<String> TgtArrNo = new ArrayList<String>();
			if(NewPrintOnly) {
				for(int i=0;i<ArrNoList.size();i++) {
					boolean UnHitFg = true;
					for(int i01=0;i01<PrintControlRt.length;i01++) {
						if(((String)PrintControlRt[i01][T00020PrintControlRt.ColOkuriNo]).equals(ArrNoList.get(i))) {
							UnHitFg = false;
							i01=PrintControlRt.length+1;
						}
					}
					if(UnHitFg) {
						TgtArrNo.add(ArrNoList.get(i));
					}
				}
			}else {
				TgtArrNo = ArrNoList;
			}
			if(null!=TgtArrNo&&0<TgtArrNo.size()) {
				Object[][] ArrivalPlanHdRt 	= PlanHdDataGet(TgtWhCd,TgtClCd,TgtArrNo);	//対象データヘッダ
				Object[][] ArrivalPlanMsRt 	= PlanMsDataGet(TgtWhCd,TgtClCd,TgtArrNo);	//対象データ明細
				
				//PDF生成
				String FP = OutPutPath();
				 try {
					boolean PageRotateFg = true;
					PDDocument document = new PDDocument();
			    	float HeightStart 	= 820;
			    	float MaxWide 		= 550;
			    	if(PageRotateFg) {
			    		/*用紙を横向きにする場合*/
						HeightStart = 570;
						MaxWide 	= 800;
			    	}//※A4横で12ポイント　34行66文字　A4縦で49行44文字程度とれます
			        
			        /******************************************************************
			         * フォント設定ここから ttcフォント設定ミスった場合MSゴシック
			        ******************************************************************/
			        String FontPath = "C:/Windows/Fonts/msgothic.ttc";
			        String FontName = "MS-Gothic";
			        
			        if(B00040ToolsFolderCheck.FLD_CHECK_ONRY(A00000Main.FontFilePath)) {
			        	FontPath = A00000Main.FontFilePath;
			        	FontName = A00000Main.FontFileName;
			        }
			        
			        String FileName = B00040ToolsFolderCheck.FILENAME(FontPath);
			        
			        File FontFile = new File(FontPath);
			        PDFont font = null;
			        TrueTypeCollection collection = null;
			        if(!FileName.equals(FileName.replace(".ttc", ""))) {
			        	ArrayList<String> ttfTgtList	= B00180PdfControl.ttcFontttfListCheck(FontPath);
			        	if(null!=ttfTgtList&&0<ttfTgtList.size()) {
			        		boolean UnHitFg = true;
			        		for(int i=0;i<ttfTgtList.size();i++) {
			        			if(FontName.equals(ttfTgtList.get(i))) {
			        				collection = new TrueTypeCollection(FontFile);
			    		        	font = PDType0Font.load(document, collection.getFontByName(FontName), true);
			        			}
			        		}
			        		if(UnHitFg) {
			        			collection = new TrueTypeCollection(FontFile);
		    		        	font = PDType0Font.load(document, collection.getFontByName(ttfTgtList.get(0)), true);
			        		}
			        	}else {
			        		FontPath = "C:/Windows/Fonts/msgothic.ttc";
			    	        FontName = "MS-Gothic";
			    	        
			    	        collection = new TrueTypeCollection(FontFile);
				        	font = PDType0Font.load(document, collection.getFontByName(ttfTgtList.get(0)), true);
			        	}
			        }else{
			        	font = PDType0Font.load(document, FontFile);
			        }
			        /******************************************************************
			         * フォント設定ここまで※後でcollectionクローズしてます
			        ******************************************************************/
			        
			        /******************************************************************
			         * 入荷予定ヘッダ～明細で帳票生成　明細行数10行(2行×10)で設計
			        ******************************************************************/
			        int MaxRowCount = 10;
			        int TotalPageCount = 0;
			        
			        int PageCount = 0;
			        int MsCount = 0;
			        
			        int FontSize = 12;
			        String CreateDtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
			        
			        Object[][] HdPageCount = new Object[ArrivalPlanHdRt.length][4];
			        
			        for(int i01=0;i01<ArrivalPlanHdRt.length;i01++) {
			        	HdPageCount[i01][0] = (String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColClWh];
			        	HdPageCount[i01][1] = (String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColClCd];
			        	HdPageCount[i01][2] = (String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColArrNo];
			        	HdPageCount[i01][3] = (int)0;
			        	
			        	MsCount = 0;
			        	for(int i02=0;i02<ArrivalPlanMsRt.length;i02++) {
			        		if(
		        				((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColClWh]).equals((String)ArrivalPlanMsRt[i02][T00015ArrivalPlanMsRt.ColClWh])
		        				&&((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColClCd]).equals((String)ArrivalPlanMsRt[i02][T00015ArrivalPlanMsRt.ColClCd])
		        				&&((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColArrNo]).equals((String)ArrivalPlanMsRt[i02][T00015ArrivalPlanMsRt.ColArrNo])
		        				) {
			        			if(0==MsCount%MaxRowCount) {
			        				TotalPageCount = TotalPageCount+1;
			        				HdPageCount[i01][3] = (int)HdPageCount[i01][3]+(int)1;
			     			        PageCount = PageCount+1;
			        			}
			        			MsCount=MsCount+1;
			        		}
			        	}
			        }
			        
			        PageCount = 0;
			        MsCount = 0;
			        PDPageContentStream contentStream = null;
			        for(int i01=0;i01<ArrivalPlanHdRt.length;i01++) {
			        	MsCount = 0;
			        	int HdPage = 0;
			        	for(int i02=0;i02<ArrivalPlanMsRt.length;i02++) {
			        		if(
		        				((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColClWh]).equals((String)ArrivalPlanMsRt[i02][T00015ArrivalPlanMsRt.ColClWh])
		        				&&((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColClCd]).equals((String)ArrivalPlanMsRt[i02][T00015ArrivalPlanMsRt.ColClCd])
		        				&&((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColArrNo]).equals((String)ArrivalPlanMsRt[i02][T00015ArrivalPlanMsRt.ColArrNo])
		        				) {
			        			if(0==MsCount%MaxRowCount) {
			        				//ヘッダー生成
			        				if(null!=contentStream) {
			        					contentStream.close();
			        				}
			        				PDPage page	= B00180PdfControl.PageRt(PageRotateFg);
			        				document.addPage(page);
			        				
			        				contentStream = new PDPageContentStream(document, page,PDPageContentStream.AppendMode.APPEND,true,true);
			        				
			     			       	PageCount = PageCount+1;
			     			       	HdPage = HdPage +1;
			     			       	Hedder(contentStream,font,FontSize,Color.BLACK,ArrivalPlanHdRt[i01],(int)HdPageCount[i01][3],HdPage,TotalPageCount,PageCount,HeightStart,MaxWide,CreateDtm);
			        			}
			        			MsCount=MsCount+1;
				        	}
			        	}
			        	
			        	contentStream.close();
			        }
			        
			        /******************************************************************
			         * 入荷予定ヘッダ～明細で帳票生成ここまで
			        ******************************************************************/
			        
			        
			        document.save(FP);
			        document.close();
			        if(null!=collection) {
			        	collection.close();
			        }
			        
			        //ファイル開く
			        File file = new File(FP);
			        Desktop desktop = Desktop.getDesktop();
			        try {
			        	desktop.open(file);
			        } catch (IOException e1) {
			        	e1.printStackTrace();
			        }
		      	}
		      	catch (IOException e) {
		        e.printStackTrace();
		      	}
				
				//入荷予定リスト印刷済みとして登録する
				PrintEntry(TgtWhCd,TgtClCd,TgtArrNo);
			}else {
				JOptionPane.showMessageDialog(null, "対象データがねぇです");
			}
		}
	}
	
	private static PDPageContentStream Hedder(
			PDPageContentStream contentStream,
			PDFont font,int FontSize,Color FontColor,
			Object[] ArrivalPlanHdRt,int HdPageCount,int HdPage,int TotalPageCount,int PageCount,
			float HeightStart,float MaxWide,String CreateDtm) {
		float fontHeight = font.getFontDescriptor()
	            .getFontBoundingBox().getHeight() / 1000 * FontSize;
		float leading = fontHeight * (float)1.2;
		
		String SetText = "";
		float StringWidth = 0;
		
		SetText = "入荷予定No:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColArrNo];
        contentStream = B00180PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*0,250,leading,SetText,font,FontSize,Color.BLACK,0,false);
        
        SetText = "荷主管理No:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColClArrNo];
        contentStream = B00180PdfControl.TextSetBox(contentStream,270,(float)HeightStart-leading*0,250,leading,SetText,font,FontSize,Color.BLACK,0,false);
        
        SetText = "　発行日時："+CreateDtm;
        contentStream = B00180PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*0,250,leading,SetText,font,FontSize,Color.BLACK,1,false);
        
        
        SetText = "　　仕入先:("+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColSpCd]+")"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColSpName01];
        contentStream = B00180PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*1,500,leading,SetText,font,FontSize,Color.BLACK,0,false);
        SetText = "予定Noページ:"+HdPage+"/"+HdPageCount+" 発行総ページ:"+PageCount+"/"+TotalPageCount;
        contentStream = B00180PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*1,300,leading,SetText,font,FontSize,Color.BLACK,1,false);
        
        SetText =  "入荷予定日:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColPlanDate];
        contentStream = B00180PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*2,250,leading,SetText,font,FontSize,Color.BLACK,0,false);
        
        SetText =  "備考01:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColArCom01];
        contentStream = B00180PdfControl.TextSetBox(contentStream,270,(float)HeightStart-leading*2,530,leading,SetText,font,FontSize,Color.BLACK,0,false);
        
        
        SetText =  "備考02:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColArCom02];
        contentStream = B00180PdfControl.TextSetBox(contentStream,270,(float)HeightStart-leading*3,530,leading,SetText,font,FontSize,Color.BLACK,0,false);
        
        SetText =  "備考03:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColArCom03];
        contentStream = B00180PdfControl.TextSetBox(contentStream,270,(float)HeightStart-leading*4,530,leading,SetText,font,FontSize,Color.BLACK,0,false);
        
        
        SetText = "商品CD";
        contentStream = B00180PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*7,120,leading*2,SetText,font,FontSize,Color.BLACK,2,true);
        SetText = "総バラ数";
        contentStream = B00180PdfControl.TextSetBox(contentStream,140,(float)HeightStart-leading*7,100,leading*2,SetText,font,FontSize,Color.BLACK,2,true);
        
        SetText =  "ロット";
        contentStream = B00180PdfControl.TextSetBox(contentStream,240,(float)HeightStart-leading*6,100,leading,SetText,font,FontSize-2,Color.BLACK,2,true);
        SetText =  "賞味期限";
        contentStream = B00180PdfControl.TextSetBox(contentStream,240,(float)HeightStart-leading*7,100,leading,SetText,font,FontSize-2,Color.BLACK,2,true);
        
        SetText =  "バラ数";
        contentStream = B00180PdfControl.TextSetBox(contentStream,340,(float)HeightStart-leading*6,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true);
        SetText =  "ケース数";
        contentStream = B00180PdfControl.TextSetBox(contentStream,340,(float)HeightStart-leading*7,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true);
        
        SetText =  "カートン数";
        contentStream = B00180PdfControl.TextSetBox(contentStream,420,(float)HeightStart-leading*6,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true);
        SetText =  "パレット数";
        contentStream = B00180PdfControl.TextSetBox(contentStream,420,(float)HeightStart-leading*7,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true);
        
        SetText =  "商品名";
        contentStream = B00180PdfControl.TextSetBox(contentStream,500,(float)HeightStart-leading*7,300,leading*2,SetText,font,FontSize-2,Color.BLACK,2,true);
		
		return contentStream;
	}
	
	
	
	
	private static String OutPutPath() {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\ArrivalPlan";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanList0001";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		String FileName = "ArrivalPlanList0001"+NowDTM+".pdf";
		String OutPutPath = FLD_PATH+"\\"+FileName;
		
		//古いデータ削除
		B00040ToolsFolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ArrivalPlanList0001",B00100DefaultVariable.ErrTxtDelete);
		
		return OutPutPath;
	}
	
	
	
	private static Object[][] PlanHdDataGet(String TgtWhCd,String TgtClCd,ArrayList<String> TgtArrNo){
		ArrayList<String> SearchClWh 			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd 			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01 		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD 			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01 		= new ArrayList<String>();		//ヘッダ荷主グループ名1
		ArrayList<String> SearchArrNo 			= new ArrayList<String>();		//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo 		= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin 	= new ArrayList<String>();		//ヘッダ入荷予定日最小
		ArrayList<String> SearchPlanDateMax 	= new ArrayList<String>();		//ヘッダ入荷予定日最大
		ArrayList<String> SearchHdActualDateMin = new ArrayList<String>();		//ヘッダ入荷実績日最小
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日最大
		ArrayList<String> SearchSpCd 			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName 			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost 			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd 			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel 			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom 			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg 			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin 		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax 		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd 			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd 		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd 			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo 		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName 		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot 			= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin 		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax 		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin 	= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax 	= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin 	= new ArrayList<Integer>();		//実績数最小
		ArrayList<Integer> SearchActualQtyMax 	= new ArrayList<Integer>();		//実績数最大
		ArrayList<String> SearchActualDateMin 	= new ArrayList<String>();		//入荷日最小
		ArrayList<String> SearchActualDateMax 	= new ArrayList<String>();		//入荷日最大
		ArrayList<String> SearchCom 			= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin 	= new ArrayList<String>();		//登録日最小
		ArrayList<String> SearchEntryDateMax 	= new ArrayList<String>();		//登録日最大
		ArrayList<String> SearchUpdateDateMin 	= new ArrayList<String>();		//更新日最小
		ArrayList<String> SearchUpdateDateMax 	= new ArrayList<String>();		//更新日最大
		ArrayList<String> SearchEntryUser 		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser 		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		
		SearchClWh.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		
		for(int i=0;i<TgtArrNo.size();i++) {
			SearchArrNo.add(TgtArrNo.get(i));
		}
		
		Object[][] ArrivalPlanHdRt = T00016ArrivalPlanHdRt.ArrivalPlanHdRt(
				SearchClWh,				//ヘッダ担当倉庫
				SearchClCd,				//ヘッダ荷主CD
				SearchCLName01,			//ヘッダ荷主名
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchCLGpName01,		//ヘッダ荷主グループ名1
				SearchArrNo,			//ヘッダ入荷予定NO
				SearchClArrNo,			//ヘッダ荷主予定番号
				SearchPlanDateMin,		//ヘッダ入荷予定日最小
				SearchPlanDateMax,		//ヘッダ入荷予定日最大
				SearchHdActualDateMin,	//ヘッダ入荷実績日最小
				SearchHdActualDateMax,	//ヘッダ入荷実績日最大
				SearchSpCd,				//ヘッダ仕入先CD
				SearchSpName,			//ヘッダ仕入先名
				SearchSpPost,			//ヘッダ仕入先郵便
				SearchSpAdd,			//ヘッダ仕入先住所
				SearchSpTel,			//ヘッダ仕入先電話
				SearchArCom,			//ヘッダコメント
				SearchFixFg,			//ヘッダ状況
						
				SearchMsNoMin,			//明細番号最小
				SearchMsNoMax,			//明細番号最大
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchJanCd,			//JANCD（バラ）
				SearchItemMdNo,			//商品型番
				SearchItemName,			//商品名
				Searchlot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				SearchPlanQtyMin,		//予定数量最小
				SearchPlanQtyMax,		//予定数量最大
				SearchActualQtyMin,		//実績数最小
				SearchActualQtyMax,		//実績数最大
				SearchActualDateMin,	//入荷日最小
				SearchActualDateMax,	//入荷日最大
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				AllSearch);
		
		return ArrivalPlanHdRt;
	}
	
	private static Object[][] PlanMsDataGet(String TgtWhCd,String TgtClCd,ArrayList<String> TgtArrNo){
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ名1
		ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchHdActualDateMin	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin		= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax		= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin	= new ArrayList<Integer>();		//実績数
		ArrayList<Integer> SearchActualQtyMax	= new ArrayList<Integer>();		//実績数
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		
		SearchClWh.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		
		for(int i=0;i<TgtArrNo.size();i++) {
			SearchArrNo.add(TgtArrNo.get(i));
		}
		
		Object[][] ArrivalPlanMsRt	= T00015ArrivalPlanMsRt.ArrivalPlanMsRt(
				SearchClWh,					//ヘッダ担当倉庫
				SearchClCd,					//ヘッダ荷主CD
				SearchCLName01,				//ヘッダ荷主名
				SearchClGpCD,				//ヘッダ荷主グループCD
				SearchCLGpName01,			//ヘッダ荷主グループ名1
				SearchArrNo,				//ヘッダ入荷予定NO
				SearchClArrNo,				//ヘッダ荷主予定番号
				SearchPlanDateMin,			//ヘッダ入荷予定日
				SearchPlanDateMax,			//ヘッダ入荷予定日
				SearchHdActualDateMin,		//ヘッダ入荷実績日
				SearchHdActualDateMax,		//ヘッダ入荷実績日
				SearchSpCd,					//ヘッダ仕入先CD
				SearchSpName,				//ヘッダ仕入先名
				SearchSpPost,				//ヘッダ仕入先郵便
				SearchSpAdd,				//ヘッダ仕入先住所
				SearchSpTel,				//ヘッダ仕入先電話
				SearchArCom,				//ヘッダコメント
				SearchFixFg,				//ヘッダ状況
						
				SearchMsNoMin,				//明細番号最小
				SearchMsNoMax,				//明細番号最大
				SearchItemCd,				//商品コード
				SearchClItemCd,				//荷主商品コード
				SearchJanCd,				//JANCD（バラ）
				SearchItemMdNo,				//商品型番
				SearchItemName,				//商品名
				Searchlot,					//ロット
				SearchExpDateMin,			//消費期限最小
				SearchExpDateMax,			//消費期限最大
				SearchPlanQtyMin,			//予定数量最小
				SearchPlanQtyMax,			//予定数量最大
				SearchActualQtyMin,			//実績数
				SearchActualQtyMax,			//実績数
				SearchActualDateMin,		//入荷日
				SearchActualDateMax,		//入荷日
				SearchCom,					//コメント
				SearchEntryDateMin,			//登録日
				SearchEntryDateMax,			//登録日
				SearchUpdateDateMin,		//更新日
				SearchUpdateDateMax,		//更新日
				SearchEntryUser,			//登録者
				SearchUpdateUser,			//更新者
				AllSearch);
		
		return ArrivalPlanMsRt;
	}
	
	
	private static Object[][] PrintDataGet(String TgtWhCd,String TgtClCd,ArrayList<String> TgtArrNo) {
		//印刷済み情報を取得
		ArrayList<String> SearchPrintCd			= new ArrayList<String>();	//印刷帳票CD
		ArrayList<String> SearchOkuriNo			= new ArrayList<String>();	//送り状番号等
		ArrayList<String> SearchKey01			= new ArrayList<String>();	//サブキー01
		ArrayList<String> SearchKey02			= new ArrayList<String>();	//サブキー02
		ArrayList<String> SearchKey03			= new ArrayList<String>();	//サブキー03
		ArrayList<String> SearchKey04			= new ArrayList<String>();	//サブキー04
		ArrayList<String> SearchEntryDateStr	= new ArrayList<String>();	//登録日
		ArrayList<String> SearchUpdateDateStr	= new ArrayList<String>();	//更新日
		ArrayList<String> SearchEntryDateEnd	= new ArrayList<String>();	//登録日
		ArrayList<String> SearchUpdateDateEnd	= new ArrayList<String>();	//更新日
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();	//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();	//更新者
		boolean AllSearch = false;
		
		SearchPrintCd.add("ArrivalPlanList0001");
		for(int i=0;i<TgtArrNo.size();i++) {
			SearchOkuriNo.add(TgtArrNo.get(i));
		}
		SearchKey01.add(TgtWhCd);
		SearchKey02.add(TgtClCd);
		
		Object[][] PrintControlRt	= T00020PrintControlRt.PrintControlRt(
													SearchPrintCd,			//印刷帳票CD
													SearchOkuriNo,			//送り状番号等
													SearchKey01,			//サブキー01
													SearchKey02,			//サブキー02
													SearchKey03,			//サブキー03
													SearchKey04,			//サブキー04
													SearchEntryDateStr,		//登録日
													SearchUpdateDateStr,	//更新日
													SearchEntryDateEnd,		//登録日
													SearchUpdateDateEnd,	//更新日
													SearchEntryUser,		//登録者
													SearchUpdateUser,		//更新者
													AllSearch);
		return PrintControlRt;
	}
	
	private static void PrintEntry(String TgtWhCd,String TgtClCd,ArrayList<String> TgtArrNo) {
		//印刷済みデータ登録・更新
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String[] SetPrintCd		= new String[TgtArrNo.size()];	//印刷帳票CD
		String[] SetOkuriNo		= new String[TgtArrNo.size()];	//送り状番号等
		String[] SetKey01		= new String[TgtArrNo.size()];	//サブキー01
		String[] SetKey02		= new String[TgtArrNo.size()];	//サブキー02
		String[] SetKey03		= new String[TgtArrNo.size()];	//サブキー03
		String[] SetKey04		= new String[TgtArrNo.size()];	//サブキー04
		String[] SetEntryDate	= new String[TgtArrNo.size()];	//登録日
		String[] SetUpdateDate	= new String[TgtArrNo.size()];	//更新日
		String[] SetEntryUser	= new String[TgtArrNo.size()];	//登録者
		String[] SetUpdateUser	= new String[TgtArrNo.size()];	//更新者
		
		for(int i=0;i<TgtArrNo.size();i++) {
			SetPrintCd[i]		= "ArrivalPlanList0001";	//印刷帳票CD
			SetOkuriNo[i]		= TgtArrNo.get(i);				//送り状番号等
			SetKey01[i]			= TgtWhCd;					//サブキー01
			SetKey02[i]			= TgtClCd;					//サブキー02
			SetKey03[i]			= "";						//サブキー03
			SetKey04[i]			= "";						//サブキー04
			SetEntryDate[i]		= now_dtm;					//登録日
			SetUpdateDate[i]	= now_dtm;					//更新日
			SetEntryUser[i]		= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者
			SetUpdateUser[i]	= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者
		}
		
		Object[][] SetOb = {
				  {"PrintCd"	,"1"	,"1"	,"Key"	,SetPrintCd		}	//印刷帳票CD
				,{"OkuriNo"		,"1"	,"1"	,"Key"	,SetOkuriNo		}	//送り状番号等
				,{"Key01"		,"1"	,"1"	,"Key"	,SetKey01		}	//サブキー01
				,{"Key02"		,"1"	,"1"	,"Key"	,SetKey02		}	//サブキー02
				,{"Key03"		,"1"	,"1"	,"Key"	,SetKey03		}	//サブキー03
				,{"Key04"		,"1"	,"1"	,"Key"	,SetKey04		}	//サブキー04
				,{"EntryDate"	,"1"	,"0"	,""		,SetEntryDate	}	//登録日
				,{"UpdateDate"	,"1"	,"1"	,""		,SetUpdateDate	}	//更新日
				,{"EntryUser"	,"1"	,"0"	,""		,SetEntryUser	}	//登録者
				,{"UpdateUser"	,"1"	,"1"	,""		,SetUpdateUser	}	//更新者
				};
		
		String Hd_tgt_table = "KT0040_PrintControl";
		String Hd_TgtDB = "NYANKO";
		int Hd_non_msg_fg = 1;
		
		A00020InsertUdateSQL.InsertUpdateSomeRecord(SetOb,Hd_tgt_table,Hd_TgtDB,Hd_non_msg_fg);
	}
}

