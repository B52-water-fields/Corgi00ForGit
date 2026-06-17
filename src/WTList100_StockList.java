import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class WTList100_StockList{
	//T100_StockRt.StockRtの結果を受け取って在庫一覧表をPDF出力する
	public static void StockList(Object[][] StockRt) {
		if(null!=StockRt && 0<StockRt.length) {
			StockRt = DataMolding(StockRt);
			PdfCreate(StockRt);
		}else {
			JOptionPane.showMessageDialog(null, "対象データがねぇです");
		}
	}
	
	private static String OutPutPath() {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\Stock";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\Stock\\StockList0001";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		String FileName = "StockList0001"+NowDTM+".pdf";
		String OutPutPath = FLD_PATH+"\\"+FileName;
		
		//古いデータ削除
		B100_FolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"StockList0001",B100_DefaultVariable.ErrTxtDelete);
		
		return OutPutPath;
	}
	
	private static void PdfCreate(Object[][] StockRt) {
		String FP  = OutPutPath();														//ファイル保存先確保・ファイル名決定
		//PDF生成
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
	        
	        if(B100_FolderCheck.FLD_CHECK_ONRY(A00000_Main.FontFilePath)) {
	        	FontPath = A00000_Main.FontFilePath;
	        	FontName = A00000_Main.FontFileName;
	        }
	        
	        String FileName = B100_FolderCheck.FILENAME(FontPath);
	        
	        File FontFile = new File(FontPath);
	        PDFont font = null;
	        TrueTypeCollection collection = null;
	        if(!FileName.equals(FileName.replace(".ttc", ""))) {
	        	ArrayList<String> ttfTgtList	= B100_PdfControl.ttcFontttfListCheck(FontPath);
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
	         * 在庫帳票ヘッダ～明細で帳票生成　明細行数12行(3行×9)で設計
	        ******************************************************************/
	        int MaxRowCount 	= 9;
	        int TotalPageCount	= StockRt.length/MaxRowCount;
	        if(0<StockRt.length%MaxRowCount) {TotalPageCount=TotalPageCount+1;}
	        
	        int PageCount 	= 0;
	        int MsCount		= 0;
	        int FontSize 	= 12;
	        
	        //フィールド名と偶数行背景色
	        Color BackgroundColor = B100_FrameParts.SelectColer("Lavender");
	        
	        String CreateDtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
	        PDPageContentStream contentStream = null;
	        
	        for(int i=0;i<StockRt.length;i++) {
	        	if(0==MsCount%MaxRowCount) {
	        		//ヘッダー生成
    				if(null!=contentStream) {
    					contentStream.close();
    				}
    				PDPage page	= B100_PdfControl.PageRt(PageRotateFg);
    				document.addPage(page);
    				contentStream = new PDPageContentStream(document, page,PDPageContentStream.AppendMode.APPEND,true,true);
    				
 			       	PageCount = PageCount+1;
 			       	
 			       	contentStream	= Hedder(
 			  			contentStream,
 			  			font,FontSize,Color.BLACK,
 			  			HeightStart,MaxWide,CreateDtm,PageCount,TotalPageCount,BackgroundColor);
	        	}
	        	MsCount = MsCount+1;
	        	contentStream	= MS(contentStream,
	        			font,FontSize,Color.BLACK,
	        			HeightStart,MaxWide,StockRt[i],MsCount,MaxRowCount,BackgroundColor);
	        }
	        if(null!=contentStream) {
				contentStream.close();
			}
	        /******************************************************************
	         * 在庫帳票ヘッダ～明細で帳票生成ここまで
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
	}
	private static PDPageContentStream Hedder(
			PDPageContentStream contentStream,
			PDFont font,int FontSize,Color FontColor,
			float HeightStart,float MaxWide,String CreateDtm,int PageCount,int TotalPageCount,Color BackgroundColor) {
		float fontHeight = font.getFontDescriptor()
	            .getFontBoundingBox().getHeight() / 1000 * FontSize;
		float leading = fontHeight * (float)1.2;
		
		String SetText = "";
		
		SetText = "在庫検索結果";
        contentStream = B100_PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*0,250,leading,SetText,font,FontSize,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText = "　発行日時："+CreateDtm;
        contentStream = B100_PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*0,250,leading,SetText,font,FontSize,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        SetText = PageCount+"/"+TotalPageCount;
        contentStream = B100_PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*1,250,leading,SetText,font,FontSize,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        
        SetText = "ロケーション";
        contentStream = B100_PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*3,90,leading*3,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "商品CD";
        contentStream = B100_PdfControl.TextSetBox(contentStream,110,(float)HeightStart-leading*3,90,leading*3,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "ロット";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,200,(float)HeightStart-leading*3,80,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        SetText = "賞味期限";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,200,(float)HeightStart-leading*4,80,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        SetText = "入荷日";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,200,(float)HeightStart-leading*5,80,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "バラ換算";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,280,(float)HeightStart-leading*2,180,leading,SetText,font,FontSize-2,Color.BLACK,2,false,Color.BLACK,true,Color.WHITE);

        SetText = "在庫総";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,280,(float)HeightStart-leading*3, 60,leading*2,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        SetText = "引当済";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,340,(float)HeightStart-leading*3, 60,leading*2,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        SetText = "出荷可能";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,400,(float)HeightStart-leading*3, 60,leading*2,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "数量";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,280,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,2,false,Color.BLACK,false,BackgroundColor);
        SetText = "数量";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,340,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,2,false,Color.BLACK,false,BackgroundColor);
        SetText = "数量";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,400,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,2,false,Color.BLACK,false,BackgroundColor);
        
        SetText = "総数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,460,(float)HeightStart-leading*2,120,leading,SetText,font,FontSize-2,Color.BLACK,2,false,Color.BLACK,true,Color.WHITE);
        SetText = "バラ数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,460,(float)HeightStart-leading*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "ケース数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,460,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "カートン数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,520,(float)HeightStart-leading*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "パレット数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,520,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "引当済数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,580,(float)HeightStart-leading*2,120,leading,SetText,font,FontSize-2,Color.BLACK,2,false,Color.BLACK,true,Color.WHITE);
        SetText = "バラ数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,580,(float)HeightStart-leading*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "ケース数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,580,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "カートン数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,640,(float)HeightStart-leading*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "パレット数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,640,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "出荷可能数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,700,(float)HeightStart-leading*2,120,leading,SetText,font,FontSize-2,Color.BLACK,2,false,Color.BLACK,true,Color.WHITE);
        SetText = "バラ数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,700,(float)HeightStart-leading*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "ケース数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,700,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "カートン数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,760,(float)HeightStart-leading*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        SetText = "パレット数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,760,(float)HeightStart-leading*4, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "入数";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,280,(float)HeightStart-leading*5,180,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        SetText = "商品名";
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,460,(float)HeightStart-leading*5,360,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        
        SetText =  "WTList100_StockList";//修正時に探しやすくするためにクラス名
        contentStream = B100_PdfControl.TextSetBox(contentStream,20,0+leading,800,leading,SetText,font,9,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
		
		return contentStream;
	}
	private static PDPageContentStream MS(
			PDPageContentStream contentStream,
			PDFont font,int FontSize,Color FontColor,
			float HeightStart,float MaxWide,Object[] TgtData,int MsNo,int MaxRowCount,Color BackgroundColor) {
		float fontHeight = font.getFontDescriptor()
	            .getFontBoundingBox().getHeight() / 1000 * FontSize;
		float leading = fontHeight * (float)1.2;
		
		int PageRow = MsNo%MaxRowCount;
		if(0==PageRow) {PageRow=MaxRowCount;}
		boolean BackGroundFg = true;
		
		if(0==PageRow%2) {
			BackGroundFg = true;
		}else {
			BackGroundFg = false;
		}
		
		String GetClCd			= (String)TgtData[T100_StockRt.ColClCd];				//荷主コード
		String GetCLName		= (String)TgtData[T100_StockRt.ColCLName];			//荷主名1
		String GetWhCd			= (String)TgtData[T100_StockRt.ColWhCd];				//倉庫コード
		String GetClWHName		= (String)TgtData[T100_StockRt.ColClWHName];			//担当倉庫名
		String GetClGpCD		= (String)TgtData[T100_StockRt.ColClGpCD];			//荷主グループCD
		String GetClGpName		= (String)TgtData[T100_StockRt.ColClGpName];			//グループ名1
		String GetLoc			= (String)TgtData[T100_StockRt.ColLoc];				//ロケーション
		String GetLocName		= (String)TgtData[T100_StockRt.ColLocName];			//ロケーション名
		int GetType				= (int)TgtData[T100_StockRt.ColType];					//ロケタイプ
		String GetItemCd		= (String)TgtData[T100_StockRt.ColItemCd];			//商品コード
		String GetLot			= (String)TgtData[T100_StockRt.ColLot];				//ロット
		String GetExpdate		= (String)TgtData[T100_StockRt.ColExpdate];			//消費期限
		String GetActualDate	= (String)TgtData[T100_StockRt.ColActualDate];		//入荷実績日
		int GetQty				= (int)TgtData[T100_StockRt.ColQty];					//総数量
		int GetShipPlanQty		= (int)TgtData[T100_StockRt.ColShipPlanQty];			//引当済総数
		int GetPossibleQty		= (int)TgtData[T100_StockRt.ColPossibleQty];			//出荷可能総数
		String GetItemName		= (String)TgtData[T100_StockRt.ColItemName];			//商品名
		String GetItemName01	= (String)TgtData[T100_StockRt.ColItemName01];		//商品名1
		String GetItemName02	= (String)TgtData[T100_StockRt.ColItemName02];		//商品名2
		String GetItemName03	= (String)TgtData[T100_StockRt.ColItemName03];		//商品名3
		String GetClItemCd		= (String)TgtData[T100_StockRt.ColClItemCd];			//荷主商品コード
		String GetJanCd			= (String)TgtData[T100_StockRt.ColJanCd];				//ソースマーク_BCD（バラ）
		String GetItemMdNo		= (String)TgtData[T100_StockRt.ColItemMdNo];			//商品型番
		int GetCtUnitQty		= (int)TgtData[T100_StockRt.ColCtUnitQty];			//カートン入数
		int GetCsUnitQty		= (int)TgtData[T100_StockRt.ColCsUnitQty];			//ケース入数
		int GetPlUnitQty		= (int)TgtData[T100_StockRt.ColPlUnitQty];			//パレット入数
		String GetUnitName		= (String)TgtData[T100_StockRt.ColUnitName];			//商品単位
		String GetCtUnitName	= (String)TgtData[T100_StockRt.ColCtUnitName];		//カートン商品単位
		String GetCsUnitName	= (String)TgtData[T100_StockRt.ColCsUnitName];		//ケース商品単位
		String GetPlUnitName	= (String)TgtData[T100_StockRt.ColPlUnitName];		//パレット商品単位
		String GetEntryDate		= (String)TgtData[T100_StockRt.ColEntryDate];		//登録日時
		String GetUpdateDate	= (String)TgtData[T100_StockRt.ColUpdateDate];		//更新日時
		String GetEntryUser		= (String)TgtData[T100_StockRt.ColEntryUser];		//登録者
		String GetUpdateUser	= (String)TgtData[T100_StockRt.ColUpdateUser];		//更新者
		int GetBrQty			= (int)TgtData[T100_StockRt.ColBrQty];				//バラ数量
		int GetBrShipPlanQty	= (int)TgtData[T100_StockRt.ColBrShipPlanQty];		//引当済バラ数
		int GetBrPossibleQty	= (int)TgtData[T100_StockRt.ColBrPossibleQty];		//出荷可能バラ数
		int GetCtQty			= (int)TgtData[T100_StockRt.ColCtQty];				//カートン数量
		int GetCtShipPlanQty	= (int)TgtData[T100_StockRt.ColCtShipPlanQty];		//引当済カートン数
		int GetCtPossibleQty	= (int)TgtData[T100_StockRt.ColCtPossibleQty];		//出荷可能カートン数
		int GetCsQty			= (int)TgtData[T100_StockRt.ColCsQty];				//ケース数量
		int GetCsShipPlanQty	= (int)TgtData[T100_StockRt.ColCsShipPlanQty];		//引当済ケース数
		int GetCsPossibleQty	= (int)TgtData[T100_StockRt.ColCsPossibleQty];		//出荷可能ケース数
		int GetPlQty			= (int)TgtData[T100_StockRt.ColPlQty];				//パレット数量
		int GetPlShipPlanQty	= (int)TgtData[T100_StockRt.ColPlShipPlanQty];		//引当済パレット数
		int GetPlPossibleQty	= (int)TgtData[T100_StockRt.ColPlPossibleQty];		//出荷可能パレット数
		
		if(GetExpdate.equals(B100_DefaultVariable.DefaultExpDate)) {GetExpdate="";}
		if(GetActualDate.equals(B100_DefaultVariable.DefaultActualDate)) {GetActualDate="";}
		
		String SetText = "";
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		SetText = ""+GetLocName;		//ロケーション
        contentStream = B100_PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-5-leading*3-leading*PageRow*3,90,leading*3,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,BackGroundFg,BackgroundColor);
        
        SetText = ""+GetItemCd;			//商品CD
        contentStream = B100_PdfControl.TextSetBox(contentStream,110,(float)HeightStart-5-leading*3-leading*PageRow*3,90,leading*3,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,BackGroundFg,BackgroundColor);
        
        SetText = ""+GetLot;			//ロット
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,200,(float)HeightStart-5-leading*3-leading*PageRow*3,80,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+GetExpdate;		//賞味期限
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,200,(float)HeightStart-5-leading*4-leading*PageRow*3,80,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+GetActualDate;		//入荷日
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,200,(float)HeightStart-5-leading*5-leading*PageRow*3,80,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,BackGroundFg,BackgroundColor);
        
        SetText = ""+ni.format(GetQty);			//総数
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,280,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading*2,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetShipPlanQty);	//引当済
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,340,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading*2,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetPossibleQty);	//出荷可能
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,400,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading*2,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        
        SetText = ""+ni.format(GetBrQty);			//バラ
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,460,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetCsQty);			//ケース
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,460,(float)HeightStart-5-leading*4-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetCtQty);			//カートン
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,520,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetPlQty);			//パレット
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,520,(float)HeightStart-5-leading*4-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        
        SetText = ""+ni.format(GetBrShipPlanQty);	//バラ
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,580,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetCsShipPlanQty);	//ケース
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,580,(float)HeightStart-5-leading*4-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetCtShipPlanQty);	//カートン
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,640,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetPlShipPlanQty);	//パレット
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,640,(float)HeightStart-5-leading*4-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        
        SetText = ""+ni.format(GetBrPossibleQty);	//バラ
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,700,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetCsPossibleQty);	//ケース
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,700,(float)HeightStart-5-leading*4-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetCtPossibleQty);	//カートン
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,760,(float)HeightStart-5-leading*3-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+ni.format(GetPlPossibleQty);	//パレット
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,760,(float)HeightStart-5-leading*4-leading*PageRow*3, 60,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,BackGroundFg,BackgroundColor);
        
        SetText = " Ct:"+ni.format(GetCtUnitQty)+" Cs:"+ni.format(GetCsUnitQty)+" Pl:"+ni.format(GetPlUnitQty);//入数
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,280,(float)HeightStart-5-leading*5-leading*PageRow*3,180,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,BackGroundFg,BackgroundColor);
        SetText = ""+GetItemName01;		//商品名
        contentStream = B100_PdfControl.TextSetBoxFontAdjust(contentStream,460,(float)HeightStart-5-leading*5-leading*PageRow*3,360,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,BackGroundFg,BackgroundColor);
		
		return contentStream;
	}
	
	private static Object[][] DataMolding(Object[][] StockRt){
		//String[][]同等扱いで本来の想定の型にする
		Object[][] DataFormat = T100_StockRt.RtStockRt();
		int FormatColmn = 2;
		int TgtColmnNo 	= 1;
		for(int i=0;i<StockRt.length;i++) {
			float WFT 		= (float)0;
			int Wint 		= (int)0;
			for(int i01=0;i01<DataFormat.length;i01++) {
				String CST = B100_TextControl.Trim(""+StockRt[i][(int)DataFormat[i01][TgtColmnNo]]);
				StockRt[i][(int)DataFormat[i01][TgtColmnNo]]= CST;
				switch((String)DataFormat[i01][FormatColmn]) {
					case "int":
						CST		= B100_TextControl.num_only_String02(CST);
						if("".equals(CST)) {CST="0";}
						WFT 	= Float.parseFloat(CST);
						Wint 	= (int)WFT;
						StockRt[i][(int)DataFormat[i01][TgtColmnNo]] = Wint;
						break;
					case "float":
						CST		= B100_TextControl.num_only_String02(CST);
						if("".equals(CST)) {CST="0";}
						WFT 	= Float.parseFloat(CST);
						StockRt[i][(int)DataFormat[i01][TgtColmnNo]] = WFT;
						break;
					case"DateTime":
						CST		= B100_DateTimeControl.DateFormat(CST);
						StockRt[i][(int)DataFormat[i01][TgtColmnNo]] = CST;
						break;
					default:
						break;
				}
			}
		}
		return StockRt;
	}
}