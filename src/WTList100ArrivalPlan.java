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

public class WTList100ArrivalPlan{
	
	public static void ArrivalPlanList0001(String TgtWhCd,String TgtClCd,ArrayList<String> ArrNoList,boolean NewPrintOnly) {
		//入荷予定リストを発行し、印刷済みとして登録する
		//標準帳票　伝票ヘッダ・明細をそのまま発行する
		
		if(null!=ArrNoList && 0<ArrNoList.size()) {
			String FP  = OutPutPath();														//ファイル保存先確保・ファイル名決定
			Object[][] PrintControlRt 	= PrintDataGet(TgtWhCd,TgtClCd,ArrNoList);		//印刷済み情報を取得
			ArrayList<String> TgtArrNo = new ArrayList<String>();
			if(NewPrintOnly) {
				for(int i=0;i<ArrNoList.size();i++) {
					boolean UnHitFg = true;
					for(int i01=0;i01<PrintControlRt.length;i01++) {
						if(((String)PrintControlRt[i01][T100PrintControlRt.ColOkuriNo]).equals(ArrNoList.get(i))) {
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
				
				Object[][] ItemMstRt		= ItemMstRt(TgtClCd,ArrivalPlanMsRt);
				Object[][] ItemRecomendLocFromItemCd = ItemRecomendLocFromItemCd(TgtClCd,ArrivalPlanMsRt);
				
				Object[][] StockRt			= StockRt(TgtClCd,ArrivalPlanMsRt);
				
				
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
			        
			        if(B100FolderCheck.FLD_CHECK_ONRY(A00000Main.FontFilePath)) {
			        	FontPath = A00000Main.FontFilePath;
			        	FontName = A00000Main.FontFileName;
			        }
			        
			        String FileName = B100FolderCheck.FILENAME(FontPath);
			        
			        File FontFile = new File(FontPath);
			        PDFont font = null;
			        TrueTypeCollection collection = null;
			        if(!FileName.equals(FileName.replace(".ttc", ""))) {
			        	ArrayList<String> ttfTgtList	= B100PdfControl.ttcFontttfListCheck(FontPath);
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
			         * 入荷予定ヘッダ～明細で帳票生成　明細行数12行(2行×12)で設計
			        ******************************************************************/
			        int MaxRowCount = 12;
			        int TotalPageCount = 0;
			        
			        int PageCount = 0;
			        int MsCount = 0;
			        
			        int FontSize = 12;
			        String CreateDtm = B100DateTimeControl.dtmString2(B100DateTimeControl.dtm()[1])[1];
			        
			        Object[][] HdPageCount = new Object[ArrivalPlanHdRt.length][6];
			        
			        for(int i01=0;i01<ArrivalPlanHdRt.length;i01++) {
			        	HdPageCount[i01][0] = (String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClWh];
			        	HdPageCount[i01][1] = (String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClCd];
			        	HdPageCount[i01][2] = (String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColArrNo];
			        	HdPageCount[i01][3] = (int)0;
			        	HdPageCount[i01][4] = (boolean)false;
			        	HdPageCount[i01][5] = "";
			        	
			        	MsCount = 0;
			        	for(int i02=0;i02<ArrivalPlanMsRt.length;i02++) {
			        		if(
		        				((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClWh]).equals((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColClWh])
		        				&&((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClCd]).equals((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColClCd])
		        				&&((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColArrNo]).equals((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColArrNo])
		        				) {
			        			if(0==MsCount%MaxRowCount) {
			        				TotalPageCount = TotalPageCount+1;
			        				HdPageCount[i01][3] = (int)HdPageCount[i01][3]+(int)1;
			     			        PageCount = PageCount+1;
			        			}
			        			MsCount=MsCount+1;
			        		}
			        	}
			        	//再発行の場合HdPageCount[i01][4] = (boolean)true;
			        	if(!NewPrintOnly) {
			        		for(int i02=0;i02<PrintControlRt.length;i02++) {
								if(((String)PrintControlRt[i02][T100PrintControlRt.ColOkuriNo]).equals((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColArrNo])
										&&((String)PrintControlRt[i02][T100PrintControlRt.ColKey01]).equals((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClWh])			//サブキー01
										&&((String)PrintControlRt[i02][T100PrintControlRt.ColKey02]).equals((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClCd])			//サブキー02
										) {
									HdPageCount[i01][4] = (boolean)true;
									HdPageCount[i01][5] = (String)PrintControlRt[i02][T100PrintControlRt.ColUpdateDate];
								}
							}
			        	}
			        }
			        
			        PageCount = 0;
			        MsCount = 0;
			        PDPageContentStream contentStream = null;
			        boolean SwitchFg = true;
			        for(int i01=0;i01<ArrivalPlanHdRt.length;i01++) {
			        	MsCount = 0;
			        	int HdPage = 0;
			        	if(SwitchFg) {
		        			SwitchFg = false;
		        		}else {
		        			SwitchFg = true;
		        		}
			        	for(int i02=0;i02<ArrivalPlanMsRt.length;i02++) {
			        		if(
		        				((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClWh]).equals((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColClWh])
		        				&&((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColClCd]).equals((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColClCd])
		        				&&((String)ArrivalPlanHdRt[i01][T100ArrivalPlanHdRt.ColArrNo]).equals((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColArrNo])
		        				) {
			        			if(0==MsCount%MaxRowCount) {
			        				//ヘッダー生成
			        				if(null!=contentStream) {
			        					contentStream.close();
			        				}
			        				PDPage page	= B100PdfControl.PageRt(PageRotateFg);
			        				document.addPage(page);
			        				
			        				contentStream = new PDPageContentStream(document, page,PDPageContentStream.AppendMode.APPEND,true,true);
			        				
			     			       	PageCount = PageCount+1;
			     			       	HdPage = HdPage +1;
			     			       	Hedder(contentStream,font,FontSize,Color.BLACK,ArrivalPlanHdRt[i01],(int)HdPageCount[i01][3],HdPage,TotalPageCount,PageCount,HeightStart,MaxWide,CreateDtm,(boolean)HdPageCount[i01][4],(String)HdPageCount[i01][5],SwitchFg);
			        			}
			        			//明細生成
			        			MsCount=MsCount+1;
			        			int BaraQty = (int)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColPlanQty];
			        			int CtQty = 0;
			        			int CsQty = 0;
			        			int PlQty = 0;
			        			String RecomendLoc = "";
			        			
			        			int GetCtQty	= 0;					//カートン入数
	        					int GetCsQty	= 0;					//ケース入数
	        					int GetPlQty	= 0;					//パレット入数
	        					
	        					String GetBrUnitName	= "個";
	        					String GetCtUnitName	= "";
	        					String GetCsUnitName	= "";
	        					String GetPlUnitName	= "";
			        			
			        			for(int i03=0;i03<ItemMstRt.length;i03++){
			        				if(((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColItemCd]).equals((String)ItemMstRt[i03][M100ItemMstRt.ColItemCd])) {
			        					GetCtQty	= (int)ItemMstRt[i03][M100ItemMstRt.ColCtQty];					//カートン入数
			        					GetCsQty	= (int)ItemMstRt[i03][M100ItemMstRt.ColCsQty];					//ケース入数
			        					GetPlQty	= (int)ItemMstRt[i03][M100ItemMstRt.ColPlQty];					//パレット入数
			        					GetBrUnitName	= (String)ItemMstRt[i03][M100ItemMstRt.ColUnitName];
			        					GetCtUnitName	= (String)ItemMstRt[i03][M100ItemMstRt.ColCtUnitName];
			        					GetCsUnitName	= (String)ItemMstRt[i03][M100ItemMstRt.ColCsUnitName];
			        					GetPlUnitName	= (String)ItemMstRt[i03][M100ItemMstRt.ColPlUnitName];
			        					
			        					if(0<GetPlQty) {
			        						PlQty = (int)(BaraQty/GetPlQty);
			        						BaraQty = BaraQty%GetPlQty;
			        					}
			        					if(0<GetCsQty) {
			        						CsQty = (int)(BaraQty/GetCsQty);
			        						BaraQty = BaraQty%GetCsQty;
			        					}
			        					if(0<GetCtQty) {
			        						CtQty = (int)(BaraQty/GetCtQty);
			        						BaraQty = BaraQty%GetCtQty;
			        					}
			        				}
			        			}
			        			//マスタから推奨ロケ格納
			        			for(int i03=0;i03<ItemRecomendLocFromItemCd.length;i03++) {
			        				if(((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColItemCd]).equals((String)ItemRecomendLocFromItemCd[i03][M100ItemRecomendLocMstRt.ColItemCd])) {
			        					RecomendLoc = (String)ItemRecomendLocFromItemCd[i03][M100ItemRecomendLocMstRt.ColLocName];
			        					i03=ItemRecomendLocFromItemCd.length+1;
			        				}
			        			}
			        			
			        			//在庫から推奨ロケ格納　同一商品・同一ロットが保管ロケにある⇒同一商品・同一ロットが通常ロケにある⇒同一商品・別ロットが保管ロケにある⇒同一商品・別ロットが通常ロケにあるの順で推奨して上書き
			        			//同一判断基準の複数候補がある場合は先にあたったロケーション（ロケが小さい）を推奨
			        			//スルー出荷ロケと引当禁止ロケにお友達がいても推奨せずにマスタの推奨ロケを採用して上書きしない
			        			for(int i03=0;i03<StockRt.length;i03++) {
			        				//出荷ロケにお友達がいる
			        				if(0==(int)StockRt[i03][T100StockRt.ColType]) {
			        					if(((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColItemCd]).equals((String)StockRt[i03][T100StockRt.ColItemCd])) {
				        					RecomendLoc =  (String)StockRt[i03][T100StockRt.ColLocName];
				        					i03=StockRt.length+1;
				        				}
			        				}
			        			}
			        			for(int i03=0;i03<StockRt.length;i03++) {
			        				//保管ロケにお友達がいる
			        				if(1==(int)StockRt[i03][T100StockRt.ColType]) {
			        					if(((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColItemCd]).equals((String)StockRt[i03][T100StockRt.ColItemCd])) {
				        					RecomendLoc =  (String)StockRt[i03][T100StockRt.ColLocName];
				        					i03=StockRt.length+1;
				        				}
			        				}
			        			}
			        			for(int i03=0;i03<StockRt.length;i03++) {
			        				//出荷ロケにドッペルゲンガーがいる
			        				if(0==(int)StockRt[i03][T100StockRt.ColType]) {
			        					if(((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColItemCd]).equals((String)StockRt[i03][T100StockRt.ColItemCd])
			        							&& ((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.Collot]).equals((String)StockRt[i03][T100StockRt.ColLot])
			        							&& ((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColExpDate]).equals((String)StockRt[i03][T100StockRt.ColExpdate])
			        							) {
				        					RecomendLoc =  (String)StockRt[i03][T100StockRt.ColLocName];
				        					i03=StockRt.length+1;
				        				}
			        				}
			        			}
			        			for(int i03=0;i03<StockRt.length;i03++) {
			        				//保管ロケにドッペルゲンガーがいる
			        				if(1==(int)StockRt[i03][T100StockRt.ColType]) {
			        					if(((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColItemCd]).equals((String)StockRt[i03][T100StockRt.ColItemCd])
			        							&& ((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.Collot]).equals((String)StockRt[i03][T100StockRt.ColLot])
			        							&& ((String)ArrivalPlanMsRt[i02][T100ArrivalPlanMsRt.ColExpDate]).equals((String)StockRt[i03][T100StockRt.ColExpdate])
			        							) {
				        					RecomendLoc =  (String)StockRt[i03][T100StockRt.ColLocName];
				        					i03=StockRt.length+1;
				        				}
			        				}
			        			}
			        			contentStream = Ms(contentStream,font,FontSize,Color.BLACK,ArrivalPlanMsRt[i02],MsCount,MaxRowCount,HeightStart,MaxWide,BaraQty,
			        					CtQty,CsQty,PlQty,RecomendLoc,GetCtQty,GetCsQty,GetPlQty,GetBrUnitName,GetCtUnitName,GetCsUnitName,GetPlUnitName);
			        			
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
	private static PDPageContentStream Ms(
			PDPageContentStream contentStream,
			PDFont font,int FontSize,Color FontColor,
			Object[] ArrivalPlanMsRt,int MsCount,int MaxRowCount,float HeightStart,float MaxWide,
			int BaraQty,int CtQty,int CsQty,int PlQty,String RecomendLoc,
			int GetCtQty,int GetCsQty,int GetPlQty,
			String GetBrUnitName,String GetCtUnitName,String GetCsUnitName,String GetPlUnitName
			) {
		float fontHeight = font.getFontDescriptor()
	            .getFontBoundingBox().getHeight() / 1000 * FontSize;
		float leading = fontHeight * (float)1.2;
		
		
		//ヘッダ分Y開始位置ずらす
		HeightStart = (float)HeightStart-leading*8;
		
		//偶数行には背景色設定
		int PageRow = MsCount%MaxRowCount;
		if(0==PageRow) {PageRow=MaxRowCount;}
		boolean SetBackgrpundFg = false;
		if(0==PageRow%2) {
			SetBackgrpundFg = true;
		}
		String SetText ="";
		Color BackgroundColor = B100FrameParts.SelectColer("Lavender");
		int wint = 0;
		
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		SetText = ""+(String)ArrivalPlanMsRt[T100ArrivalPlanMsRt.ColItemCd];	//商品CD
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-10-(leading*PageRow*2)+leading*2,120,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        SetText = ""+ni.format((int)ArrivalPlanMsRt[T100ArrivalPlanMsRt.ColPlanQty])+""+GetBrUnitName;	//総バラ数
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-10-(leading*PageRow*2)+leading*1,120,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        
        SetText =  ""+(String)ArrivalPlanMsRt[T100ArrivalPlanMsRt.Collot];	//ロット
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream,140,(float)HeightStart-10-(leading*PageRow*2)+leading*2,100,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        SetText =  ""+(String)ArrivalPlanMsRt[T100ArrivalPlanMsRt.ColExpDate];	//賞味期限
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream,140,(float)HeightStart-10-(leading*PageRow*2)+leading*1,100,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        
        SetText =  ""+ni.format(BaraQty)+""+GetBrUnitName;	//バラ数
        if(0==BaraQty) {SetText="";}
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream,240,(float)HeightStart-10-(leading*PageRow*2)+leading*2, 80,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        
        wint = CsQty*GetCsQty;
        SetText =  ""+ni.format(CsQty)+" "+GetCsUnitName+"("+ni.format(wint)+GetBrUnitName+")";	//ケース数
        if(0==CsQty) {SetText="";}
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream,240,(float)HeightStart-10-(leading*PageRow*2)+leading*1, 80,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        
        wint = CtQty*GetCtQty;
        SetText =  ""+ni.format(CtQty)+" "+GetCtUnitName+"("+ni.format(wint)+GetBrUnitName+")";	//カートン数
        if(0==CtQty) {SetText="";}
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream,320,(float)HeightStart-10-(leading*PageRow*2)+leading*2, 80,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        
        wint = PlQty*GetPlQty;
        SetText =  ""+ni.format(PlQty)+" "+GetPlUnitName+"("+ni.format(wint)+GetBrUnitName+")";	//パレット数
        if(0==PlQty) {SetText="";}
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream,320,(float)HeightStart-10-(leading*PageRow*2)+leading*1, 80,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        
        SetText = ""+RecomendLoc;	//推奨ロケ
        contentStream = B100PdfControl.TextSetBox(contentStream,400,(float)HeightStart-10-(leading*PageRow*2)+leading*2,100,leading*2,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
        
        SetText =  ""+(String)ArrivalPlanMsRt[T100ArrivalPlanMsRt.ColItemName];	//商品名
        contentStream = B100PdfControl.TextSetBox(contentStream,500,(float)HeightStart-10-(leading*PageRow*2)+leading*2,300,leading*2,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,SetBackgrpundFg,BackgroundColor);
		
		return contentStream;
	}
	
	private static PDPageContentStream Hedder(
			PDPageContentStream contentStream,
			PDFont font,int FontSize,Color FontColor,
			Object[] ArrivalPlanHdRt,int HdPageCount,int HdPage,int TotalPageCount,int PageCount,
			float HeightStart,float MaxWide,String CreateDtm,
			boolean ReprintFg,String BeforePrintDtm,
			boolean SwitchFg) {
		float fontHeight = font.getFontDescriptor()
	            .getFontBoundingBox().getHeight() / 1000 * FontSize;
		float leading = fontHeight * (float)1.2;
		
		
		String SetText = "";
		
		if(SwitchFg) {
			SetText = "■";
		}else {
			SetText = "";
		}
		contentStream = B100PdfControl.TextSetBox(contentStream, 0,(float)HeightStart-leading*0,20,leading,SetText,font,FontSize,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
		
		SetText =  "　　　荷主:("+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColClCd]+")"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColCLName01];
        contentStream = B100PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*0,500,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText = "　発行日時："+CreateDtm;
        contentStream = B100PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*0,300,leading,SetText,font,FontSize-2,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        
        SetText = "　　仕入先:("+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColSpCd]+")"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColSpName01];
        contentStream = B100PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*1,500,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText = "予定Noページ:"+HdPage+"/"+HdPageCount+" 発行総ページ:"+PageCount+"/"+TotalPageCount;
        contentStream = B100PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*1,300,leading,SetText,font,FontSize-2,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
		
		SetText = "入荷予定No:"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColArrNo];
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*2,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText = "荷主管理No:"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColClArrNo];
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*3,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "入荷予定日:"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColPlanDate];
        contentStream = B100PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*4,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        
        
        SetText =  "備考01:"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColArCom01];
        contentStream = B100PdfControl.TextSetBox(contentStream,370,(float)HeightStart-leading*2,450,leading,SetText,font,FontSize-3,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "備考02:"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColArCom02];
        contentStream = B100PdfControl.TextSetBox(contentStream,370,(float)HeightStart-leading*3,450,leading,SetText,font,FontSize-3,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "備考03:"+(String)ArrivalPlanHdRt[T100ArrivalPlanHdRt.ColArCom03];
        contentStream = B100PdfControl.TextSetBox(contentStream,370,(float)HeightStart-leading*4,450,leading,SetText,font,FontSize-3,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        
        
        if(ReprintFg) {
        	SetText = "前回発行:"+BeforePrintDtm+"【再発行】";
        }else {
        	SetText = "";
        }
        contentStream = B100PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*5,300,leading,SetText,font,FontSize-2,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        
        //背景色
        Color BackgroundColor = B100FrameParts.SelectColer("Lavender");
        
        
        SetText = "商品CD";
        contentStream = B100PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-5-leading*6,120,leading,SetText,font,FontSize-2,Color.BLACK,0,true,Color.BLACK,true,BackgroundColor);
        SetText = "総バラ数";
        contentStream = B100PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-5-leading*7,120,leading,SetText,font,FontSize-2,Color.BLACK,1,true,Color.BLACK,true,BackgroundColor);
        
        SetText =  "ロット";
        contentStream = B100PdfControl.TextSetBox(contentStream,140,(float)HeightStart-5-leading*6,100,leading,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        SetText =  "賞味期限";
        contentStream = B100PdfControl.TextSetBox(contentStream,140,(float)HeightStart-5-leading*7,100,leading,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        
        SetText =  "バラ数";
        contentStream = B100PdfControl.TextSetBox(contentStream,240,(float)HeightStart-5-leading*6,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        SetText =  "ケース数";
        contentStream = B100PdfControl.TextSetBox(contentStream,240,(float)HeightStart-5-leading*7,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        
        SetText =  "カートン数";
        contentStream = B100PdfControl.TextSetBox(contentStream,320,(float)HeightStart-5-leading*6,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        SetText =  "パレット数";
        contentStream = B100PdfControl.TextSetBox(contentStream,320,(float)HeightStart-5-leading*7,80,leading,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        
        SetText = "推奨ロケ";
        contentStream = B100PdfControl.TextSetBox(contentStream,400,(float)HeightStart-5-leading*6,100,leading*2,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        
        SetText =  "商品名";
        contentStream = B100PdfControl.TextSetBox(contentStream,500,(float)HeightStart-5-leading*6,300,leading*2,SetText,font,FontSize-2,Color.BLACK,2,true,Color.BLACK,true,BackgroundColor);
        
        
        SetText =  "WTList100ArrivalPlan";//修正時に探しやすくするためにクラス名
        contentStream = B100PdfControl.TextSetBox(contentStream,20,0+leading,800,leading,SetText,font,9,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
		
		return contentStream;
	}
	
	private static String OutPutPath() {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\ArrivalPlan";
		B100FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanList0001";
		B100FolderCheck.FLD_CHECK(FLD_PATH);
		
		String NowDTM=B100DateTimeControl.dtmString2(B100DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		String FileName = "ArrivalPlanList0001"+NowDTM+".pdf";
		String OutPutPath = FLD_PATH+"\\"+FileName;
		
		//古いデータ削除
		B100FolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ArrivalPlanList0001",B100DefaultVariable.ErrTxtDelete);
		
		return OutPutPath;
	}
	
	private static Object[][] StockRt(String TgtClCd,Object[][] ArrivalPlanMsRt){
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
		ArrayList<Integer> SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		ArrayList<String> SearchItemCd				= new ArrayList<String>();			//商品コード
		ArrayList<String> SearchLot					= new ArrayList<String>();			//ロット
		ArrayList<String> SearchExpdateMin			= new ArrayList<String>();			//消費期限最小
		ArrayList<String> SearchExpdateMax			= new ArrayList<String>();			//消費期限最大
		ArrayList<String> SearchActualDateMin		= new ArrayList<String>();			//入荷実績日最小
		ArrayList<String> SearchActualDateMax		= new ArrayList<String>();			//入荷実績日最大
		ArrayList<Integer> SearchQtyMin				= new ArrayList<Integer>();			//数量最小
		ArrayList<Integer> SearchQtyMax				= new ArrayList<Integer>();			//数量最大
		ArrayList<Integer> SearchShipPlanQtyMin		= new ArrayList<Integer>();			//引当済数最小
		ArrayList<Integer> SearchShipPlanQtyMax		= new ArrayList<Integer>();			//引当済数最大
		ArrayList<Integer> SearchPossibleQtyMin		= new ArrayList<Integer>();			//出荷可能数最小
		ArrayList<Integer> SearchPossibleQtyMax		= new ArrayList<Integer>();			//出荷可能数最大
		ArrayList<String> SearchItemName			= new ArrayList<String>();			//商品名
		ArrayList<String> SearchClItemCd			= new ArrayList<String>();			//荷主商品コード
		ArrayList<String> SearchJanCd				= new ArrayList<String>();			//ソースマーク_BCD（バラ）
		ArrayList<String> SearchItemMdNo			= new ArrayList<String>();			//商品型番
		boolean LocExactMatch = false;													//ロケーション完全一致
		boolean AllSearch = false;														//全件検索
		boolean SortItemcdMode = true;													//商品CDでソート
		Object[][] ClMstRt	= ClMstRt(TgtClCd);
		if(0<ClMstRt.length) {SearchClGpCD.add((String)ClMstRt[0][M100ClMstRt.ColClGpCD]);}
		
		for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			SearchItemCd.add((String)ArrivalPlanMsRt[i][T100ArrivalPlanMsRt.ColItemCd]);
		}
		
		Object[][] StockRt= T100StockRt.StockRt(
								SearchClCd,				//荷主コード
								SearchWhCd,				//倉庫コード
								SearchClGpCD,			//荷主グループCD
								SearchLoc,				//ロケーション
								SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
								SearchItemCd,			//商品コード
								SearchLot,				//ロット
								SearchExpdateMin,		//消費期限最小
								SearchExpdateMax,		//消費期限最大
								SearchActualDateMin,	//入荷実績日最小
								SearchActualDateMax,	//入荷実績日最大
								SearchQtyMin,			//数量最小
								SearchQtyMax,			//数量最大
								SearchShipPlanQtyMin,	//引当済数最小
								SearchShipPlanQtyMax,	//引当済数最大
								SearchPossibleQtyMin,	//出荷可能数最小
								SearchPossibleQtyMax,	//出荷可能数最大
								SearchItemName,			//商品名
								SearchClItemCd,			//荷主商品コード
								SearchJanCd,			//ソースマーク_BCD（バラ）
								SearchItemMdNo,			//商品型番
								LocExactMatch,			//ロケーション完全一致
								AllSearch,
								SortItemcdMode);
		return StockRt;
	}
	
	private static Object[][] ItemMstRt(String TgtClCd,Object[][] ArrivalPlanMsRt){
		Object[][] ClMstRt	= ClMstRt(TgtClCd);
		//商品マスタ取得
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchCLItemCd 			= new ArrayList<String>();	//荷主商品コード
		ArrayList<String> SearchItemName 			= new ArrayList<String>();	//商品名
		ArrayList<String> SearchDeliveryTypeCd01 	= new ArrayList<String>();	//運送タイプコード01
		ArrayList<String> SearchDeliveryTypeCd02 	= new ArrayList<String>();	//運送タイプコード02
		ArrayList<String> SearchDeliveryTypeCd03 	= new ArrayList<String>();	//運送タイプコード03
		ArrayList<String> SearchDeliveryTypeCd04 	= new ArrayList<String>();	//運送タイプコード04
		ArrayList<String> SearchDeliveryTypeCd05 	= new ArrayList<String>();	//運送タイプコード05
		ArrayList<String> SearchItemMDNo 			= new ArrayList<String>();	//商品モデル番号（型番）
		ArrayList<String> SearchCategoryCd 			= new ArrayList<String>();	//商品カテゴリCD
		ArrayList<String> SearchCategoryName 		= new ArrayList<String>();	//商品カテゴリ名
		ArrayList<String> SearchItemColorCd 		= new ArrayList<String>();	//商品カラーコード
		ArrayList<String> SearchItemColorName 		= new ArrayList<String>();	//商品カラー名
		ArrayList<String> SearchItemSizeCd 			= new ArrayList<String>();	//商品サイズコード
		ArrayList<String> SearchItemSizeName 		= new ArrayList<String>();	//商品サイズ名
		ArrayList<String> SearchJanCd 				= new ArrayList<String>();	//JANCD
		ArrayList<String> SearchTildFG 				= new ArrayList<String>();	//温度区分
		ArrayList<String> SearchTildName 			= new ArrayList<String>();	//温度区分名
		ArrayList<String> SearchDelFg 				= new ArrayList<String>();	//削除フラグ
		boolean AllSearch = false;
		if(0<ClMstRt.length) {SearchClGpCd.add((String)ClMstRt[0][M100ClMstRt.ColClGpCD]);}
		
		for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			SearchItemCd.add((String)ArrivalPlanMsRt[i][T100ArrivalPlanMsRt.ColItemCd]);
		}
		
		Object[][] ItemMstRt = M100ItemMstRt.ItemMstRt(
				SearchClGpCd,			//荷主グループコード
				SearchItemCd,			//商品コード
				SearchCLItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchDeliveryTypeCd01,	//運送タイプコード01
				SearchDeliveryTypeCd02,	//運送タイプコード02
				SearchDeliveryTypeCd03,	//運送タイプコード03
				SearchDeliveryTypeCd04,	//運送タイプコード04
				SearchDeliveryTypeCd05,	//運送タイプコード05
				SearchItemMDNo,			//商品モデル番号（型番）
				SearchCategoryCd,		//商品カテゴリCD
				SearchCategoryName,		//商品カテゴリ名
				SearchItemColorCd,		//商品カラーコード
				SearchItemColorName,	//商品カラー名
				SearchItemSizeCd,		//商品サイズコード
				SearchItemSizeName,		//商品サイズ名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		return ItemMstRt;
	}
	
	private static Object[][] ItemRecomendLocFromItemCd(String TgtClCd,Object[][] ArrivalPlanMsRt){
		//推奨ロケマスタ取得
		String ClCd = TgtClCd;
		ArrayList<String> ItemCd= new ArrayList<String>();	//商品CD
		boolean ItemMstRecomendLocExistenceOnly = true;		//商品サブマスタ推奨ロケは対象荷主のロケーションマスタに存在する場合のみ値格納
		for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			ItemCd.add((String)ArrivalPlanMsRt[i][T100ArrivalPlanMsRt.ColItemCd]);
		}
		
		Object[][] ItemRecomendLocFromItemCd = M100ItemRecomendLocMstRt.ItemRecomendLocFromItemCd(ClCd,ItemCd,ItemMstRecomendLocExistenceOnly);
		
		return ItemRecomendLocFromItemCd;
	}
	
	private static Object[][] ClMstRt(String TgtClCd){
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchCLCD.add(TgtClCd);
		
		Object[][] ClMstRt = M100ClMstRt.ClMstRt(
			SearchClGpCD,
			SearchCLCD,
			SearchCLName,
			SearchPost,
			searchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchWHCD,
			AllSearch);
		return ClMstRt;
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
		
		Object[][] ArrivalPlanHdRt = T100ArrivalPlanHdRt.ArrivalPlanHdRt(
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
		
		Object[][] ArrivalPlanMsRt	= T100ArrivalPlanMsRt.ArrivalPlanMsRt(
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
		
		Object[][] PrintControlRt	= T100PrintControlRt.PrintControlRt(
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
		String now_dtm = B100DateTimeControl.dtmString2(B100DateTimeControl.dtm()[1])[1];
		
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
		
		A100InsertUdateSQL.InsertUpdateSomeRecord(SetOb,Hd_tgt_table,Hd_TgtDB,Hd_non_msg_fg);
	}
}

