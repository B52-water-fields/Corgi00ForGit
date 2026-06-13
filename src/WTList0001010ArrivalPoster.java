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

public class WTList0001010ArrivalPoster{
	public static void ArrivalPoster(String TgtWhCd,String TgtClCd,ArrayList<String> ArrNoList,boolean NewPrintOnly) {
		//入荷予定張り札発行
		if(null!=ArrNoList && 0<ArrNoList.size()) {
			String FP  = OutPutPath();														//ファイル保存先確保・ファイル名決定
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
			         * 入荷予定ヘッダ～明細で帳票生成
			        ******************************************************************/
			        int MaxRowCount 	= 12;
			        int TotalPageCount 	= 0;
			        int PageCount 		= 0;
			        
			        int FontSize = 12;
			        String CreateDtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
			        
			        Object[][] ArrivalPlanHdRt 	= PlanHdDataGet(TgtWhCd,TgtClCd,TgtArrNo);	//対象データヘッダ
					Object[][] ArrivalPlanMsRt 	= PlanMsDataGet(TgtWhCd,TgtClCd,TgtArrNo);	//対象データ明細
					
					Object[][] ItemMstRt		= ItemMstRt(TgtClCd,ArrivalPlanMsRt);
					Object[][] ItemRecomendLocFromItemCd = ItemRecomendLocFromItemCd(TgtClCd,ArrivalPlanMsRt);
					
					Object[][] StockRt			= StockRt(TgtClCd,ArrivalPlanMsRt);
					
					
					//入荷予定番号-商品CD-ロット-賞味期限-数量-推奨ロケで構成される明細に成型する為に
					//入荷予定番号-商品CD-ロット-賞味期限を結合して主キー化
					ArrayList<String> KeyArray = new ArrayList<String>();
			        for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			        	KeyArray.add((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColArrNo]
			        					+"-"+(String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColItemCd]
			        					+"-"+(String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.Collot]
			        					+"-"+(String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColExpDate]
			        					);
			        }
			        
			        KeyArray=B00150ArrayListControl.ArryListStringUniqueList(KeyArray);
			        
			        int MsColArrNo			= 0;
        			int MsColItemCd			= 1;
        			int MsColLot			= 2;
        			int MsColExpDate		= 3;
        			int MsColQty			= 4;
        			int MsColRecomendLoc	= 5;
        			int MsColBaraQty 		= 6;
        			int MsColCtQty 			= 7;
        			int MsColCsQty 			= 8;
        			int MsColPlQty 			= 9;
        			int MsColItemName 		=10;
        			int MsColCtUnitQty 		=11;
        			int MsColCsUnitQty 		=12;
        			int MsColPlUnitQty 		=13;
			        
			        Object[][] MsData = new Object[KeyArray.size()][14];
			        for(int i01=0;i01<MsData.length;i01++) {
			        	MsData[i01][MsColArrNo] 		= "";
	        			MsData[i01][MsColItemCd] 		= "";
	        			MsData[i01][MsColLot] 			= "";
	        			MsData[i01][MsColExpDate] 		= "";
	        			MsData[i01][MsColQty] 			= 0;
	        			MsData[i01][MsColRecomendLoc]	= "";
	        			MsData[i01][MsColBaraQty] 		= 0;
	        			MsData[i01][MsColCtQty] 		= 0;
	        			MsData[i01][MsColCsQty] 		= 0;
	        			MsData[i01][MsColPlQty] 		= 0;
	        			MsData[i01][MsColItemName]		= "";
	        			MsData[i01][MsColCtUnitQty] 	= 0;
	        			MsData[i01][MsColCsUnitQty] 	= 0;
	        			MsData[i01][MsColPlUnitQty] 	= 0;
			        }
			        
			        int Counter = 0;
			        for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			        	boolean UnHitFg = true;
			        	for(int i01=0;i01<MsData.length;i01++) {
				        	if((		(String)MsData[i01][MsColArrNo]		).equals((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColArrNo])
						        	&& ((String)MsData[i01][MsColItemCd]	).equals((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColItemCd])
						        	&& ((String)MsData[i01][MsColLot]		).equals((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.Collot])
						        	&& ((String)MsData[i01][MsColExpDate]	).equals((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColExpDate])
				        			) {
				        		MsData[i01][MsColQty] 		= (int)MsData[i01][MsColQty]	+(int)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColPlanQty];
				        		MsData[i01][MsColBaraQty] 	= (int)MsData[i01][MsColBaraQty]+(int)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColPlanQty];
				        		UnHitFg = false;
				        		i01=MsData.length+1;
				        	}
				        }
			        	if(UnHitFg) {
			        		MsData[Counter][MsColArrNo] 		= (String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColArrNo];
		        			MsData[Counter][MsColItemCd] 		= (String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColItemCd];
		        			MsData[Counter][MsColLot] 			= (String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.Collot];
		        			MsData[Counter][MsColExpDate] 		= (String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColExpDate];
		        			MsData[Counter][MsColQty] 			= (int)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColPlanQty];
		        			MsData[Counter][MsColRecomendLoc]	= "";
		        			MsData[Counter][MsColBaraQty] 		= (int)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColPlanQty];
		        			MsData[Counter][MsColCtQty] 		= 0;
		        			MsData[Counter][MsColCsQty] 		= 0;
		        			MsData[Counter][MsColPlQty] 		= 0;
		        			MsData[Counter][MsColItemName]		= "";
		        			MsData[Counter][MsColCtUnitQty] 	= 0;
		        			MsData[Counter][MsColCsUnitQty] 	= 0;
		        			MsData[Counter][MsColPlUnitQty] 	= 0;
			        		Counter = Counter + 1;
			        	}
			        }
			        
			        for(int i01=0;i01<MsData.length;i01++) {
			        	for(int i02=0;i02<ItemMstRt.length;i02++) {
			        		//商品マスタを元にパレット換算数・ケース換算数・カートン換算数を導出
				        	if(((String)MsData[i01][MsColItemCd]).equals((String)ItemMstRt[i02][M00070ItemMstRt.ColItemCd])) {
				        		MsData[i01][MsColItemName]		= (String)ItemMstRt[i02][M00070ItemMstRt.ColItemName01];
				        		
				        		int GetCtQty	= (int)ItemMstRt[i02][M00070ItemMstRt.ColCtQty];					//カートン入数
	        					int GetCsQty	= (int)ItemMstRt[i02][M00070ItemMstRt.ColCsQty];					//ケース入数
	        					int GetPlQty	= (int)ItemMstRt[i02][M00070ItemMstRt.ColPlQty];					//パレット入数
	        					int GetBaraQty 	= (int)MsData[i01][MsColBaraQty];
	        					
	        					MsData[i01][MsColCtUnitQty] 	= GetCtQty;
	    	        			MsData[i01][MsColCsUnitQty] 	= GetCsQty;
	    	        			MsData[i01][MsColPlUnitQty] 	= GetPlQty;
	        					if(0<GetPlQty) {
	        						MsData[i01][MsColPlQty] 	= (int)(GetBaraQty / GetPlQty);
	        						GetBaraQty					= GetBaraQty % GetPlQty;
	        						MsData[i01][MsColBaraQty] 	= GetBaraQty;
	        					}
	        					if(0<GetCsQty) {
	        						MsData[i01][MsColCsQty] 	= (int)(GetBaraQty / GetCsQty);
	        						GetBaraQty					= GetBaraQty % GetCsQty;
	        						MsData[i01][MsColBaraQty] 	= GetBaraQty;
	        					}
	        					if(0<GetCtQty) {
	        						MsData[i01][MsColCtQty] 	= (int)(GetBaraQty / GetCtQty);
	        						GetBaraQty					= GetBaraQty % GetCtQty;
	        						MsData[i01][MsColBaraQty] 	= GetBaraQty;
	        					}
				        	}
			        	}
			        	
			        	TotalPageCount = TotalPageCount + (int)MsData[i01][MsColPlQty];
			        	if(0<(int)MsData[i01][MsColBaraQty]||0<(int)MsData[i01][MsColCtQty]||0<(int)MsData[i01][MsColCsQty]) {TotalPageCount	=	TotalPageCount + 1;}
			        	
			        	//マスタから推奨ロケ格納
	        			for(int i02=0;i02<ItemRecomendLocFromItemCd.length;i02++) {
	        				if(((String)MsData[i01][MsColItemCd]).equals((String)ItemRecomendLocFromItemCd[i02][M00120ItemRecomendLocMstRt.ColItemCd])) {
	        					MsData[i01][MsColRecomendLoc] = (String)ItemRecomendLocFromItemCd[i02][M00120ItemRecomendLocMstRt.ColLocName];
	        					i02=ItemRecomendLocFromItemCd.length+1;
	        				}
	        			}
	        			//在庫から推奨ロケ格納　同一商品・同一ロットが保管ロケにある⇒同一商品・同一ロットが通常ロケにある⇒同一商品・別ロットが保管ロケにある⇒同一商品・別ロットが通常ロケにあるの順で推奨して上書き
	        			//同一判断基準の複数候補がある場合は先にあたったロケーション（ロケが小さい）を推奨
	        			//スルー出荷ロケと引当禁止ロケにお友達がいても推奨せずにマスタの推奨ロケを採用して上書きしない
	        			for(int i02=0;i02<StockRt.length;i02++) {
	        				//出荷ロケにお友達がいる
	        				if(0==(int)StockRt[i02][T00030StockRt.ColType]) {
	        					if(((String)MsData[i01][MsColItemCd]).equals((String)StockRt[i02][T00030StockRt.ColItemCd])) {
	        						MsData[i01][MsColRecomendLoc] =  (String)StockRt[i02][T00030StockRt.ColLocName];
		        					i02=StockRt.length+1;
		        				}
	        				}
	        			}
	        			for(int i02=0;i02<StockRt.length;i02++) {
	        				//保管ロケにお友達がいる
	        				if(1==(int)StockRt[i02][T00030StockRt.ColType]) {
	        					if(((String)MsData[i01][MsColItemCd]).equals((String)StockRt[i02][T00030StockRt.ColItemCd])) {
	        						MsData[i01][MsColRecomendLoc] =  (String)StockRt[i02][T00030StockRt.ColLocName];
		        					i02=StockRt.length+1;
		        				}
	        				}
	        			}
	        			for(int i02=0;i02<StockRt.length;i02++) {
	        				//出荷ロケにドッペルゲンガーがいる
	        				if(0==(int)StockRt[i02][T00030StockRt.ColType]) {
	        					if(((String)MsData[i01][MsColItemCd]).equals((String)StockRt[i02][T00030StockRt.ColItemCd])
	        							&& ((String)MsData[i01][MsColLot]).equals((String)StockRt[i02][T00030StockRt.ColLot])
	        							&& ((String)MsData[i01][MsColExpDate]).equals((String)StockRt[i02][T00030StockRt.ColExpdate])
	        							) {
	        						MsData[i01][MsColRecomendLoc] =  (String)StockRt[i02][T00030StockRt.ColLocName];
		        					i02=StockRt.length+1;
		        				}
	        				}
	        			}
	        			for(int i02=0;i02<StockRt.length;i02++) {
	        				//保管ロケにドッペルゲンガーがいる
	        				if(1==(int)StockRt[i02][T00030StockRt.ColType]) {
	        					if(((String)MsData[i01][MsColItemCd]).equals((String)StockRt[i02][T00030StockRt.ColItemCd])
	        							&& ((String)MsData[i01][MsColLot]).equals((String)StockRt[i02][T00030StockRt.ColLot])
	        							&& ((String)MsData[i01][MsColExpDate]).equals((String)StockRt[i02][T00030StockRt.ColExpdate])
	        							) {
	        						MsData[i01][MsColRecomendLoc] =  (String)StockRt[i02][T00030StockRt.ColLocName];
		        					i02=StockRt.length+1;
		        				}
	        				}
	        			}
			        }
			        
			        PDPageContentStream contentStream = null;
			        PageCount			= 0;
			        int NowTotalPage 	= 0;
			        boolean SwitchFg 	= true;
			        
			        int ArrNoTotalPage	= 0;
			        int ArrNoNowPage	= 0;
			        
			        for(int i01=0;i01<ArrivalPlanHdRt.length;i01++) {
			        	if(SwitchFg) {
		        			SwitchFg = false;
		        		}else {
		        			SwitchFg = true;
		        		}
			        	//入荷予定番号単位のページ数を把握
			        	for(int i02=0;i02<MsData.length;i02++) {
			        		if(((String)MsData[i02][MsColArrNo]).equals((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColArrNo])) {
			        			ArrNoTotalPage = ArrNoTotalPage + (int)MsData[i01][MsColPlQty];
			        			if(0<(int)MsData[i02][MsColBaraQty]||0<(int)MsData[i02][MsColCtQty]||0<(int)MsData[i02][MsColCsQty]) {ArrNoTotalPage	=	ArrNoTotalPage + 1;}
			        		}
			        	}
			        	ArrNoNowPage	= 0;
			        	
			        	//再発行であれば　ReprintFg = true;
			        	boolean ReprintFg = false;
			        	boolean UnHitFg = true;
			        	String BeforePrintDtm = "";
						for(int i02=0;i02<PrintControlRt.length;i02++) {
							if(((String)PrintControlRt[i02][T00020PrintControlRt.ColOkuriNo]).equals((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColArrNo])) {
								UnHitFg = false;
								BeforePrintDtm = (String)PrintControlRt[i02][T00020PrintControlRt.ColUpdateDate];
								i02=PrintControlRt.length+1;
								ReprintFg = true;
							}
						}
						if(UnHitFg) {
							ReprintFg = false;
						}
			        	
			        	for(int i02=0;i02<MsData.length;i02++) {
			        		if(((String)MsData[i02][MsColArrNo]).equals((String)ArrivalPlanHdRt[i01][T00016ArrivalPlanHdRt.ColArrNo])) {
			        			int NeedPage = (int)MsData[i02][MsColPlQty];
					        	if(0<(int)MsData[i02][MsColBaraQty]||0<(int)MsData[i02][MsColCtQty]||0<(int)MsData[i02][MsColCsQty]) {NeedPage	=	NeedPage + 1;}
					        	
					        	for(int i=0;i<NeedPage;i++) {
				        			if(null!=contentStream) {
			        					contentStream.close();
			        				}
				        			ArrNoNowPage = ArrNoNowPage+1;
				        			NowTotalPage = NowTotalPage+1;
				        			PDPage page	= B00180PdfControl.PageRt(PageRotateFg);
			        				document.addPage(page);
			        				
			        				contentStream = new PDPageContentStream(document, page,PDPageContentStream.AppendMode.APPEND,true,true);
			        				
			        				contentStream = ListSet(
			        						contentStream,
			        						font,FontSize,Color.BLACK,
			        						ArrivalPlanHdRt[i01],MsData[i02],ArrNoTotalPage,ArrNoNowPage,TotalPageCount,NowTotalPage,
			        						HeightStart,MaxWide,CreateDtm,
			        						ReprintFg,BeforePrintDtm,
			        						SwitchFg,
			        						MsColArrNo,
			        						MsColItemCd,
			        						MsColLot,
			        						MsColExpDate,
			        						MsColQty,
			        						MsColRecomendLoc,
			        						MsColBaraQty,
			        						MsColCtQty,
			        						MsColCsQty,
			        						MsColPlQty,
			        						MsColItemName,
			        						MsColCtUnitQty,
			        	        			MsColCsUnitQty,
			        	        			MsColPlUnitQty,
			        	        			NeedPage,
			        	        			i+1);
					        	}
			        		}
			        	}
			        }
			        if(null!=contentStream) {
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
	
	private static PDPageContentStream ListSet(
			PDPageContentStream contentStream,
			PDFont font,int FontSize,Color FontColor,
			Object[] ArrivalPlanHdRt,Object[] MsData,int ArrNoTotalPage,int ArrNoNowPage,int TotalPageCount,int NowTotalPage,
			float HeightStart,float MaxWide,String CreateDtm,
			boolean ReprintFg,String BeforePrintDtm,
			boolean SwitchFg,
			int MsColArrNo,
			int MsColItemCd,
			int MsColLot,
			int MsColExpDate,
			int MsColQty,
			int MsColRecomendLoc,
			int MsColBaraQty,
			int MsColCtQty,
			int MsColCsQty,
			int MsColPlQty,
			int MsColItemName,
			int MsColCtUnitQty,
			int MsColCsUnitQty,
			int MsColPlUnitQty,
			int ItemTotalPage,
			int ItemPageCount) {
		
		float fontHeight = font.getFontDescriptor()
	            .getFontBoundingBox().getHeight() / 1000 * FontSize;
		float leading = fontHeight * (float)1.2;
		
		String SetText = "";
		
		if(SwitchFg) {
			SetText = "■";
		}else {
			SetText = "";
		}
		contentStream = B00180PdfControl.TextSetBox(contentStream, 0,(float)HeightStart-leading*0,20,leading,SetText,font,FontSize,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
		
		SetText =  "　　　荷主:("+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColClCd]+")"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColCLName01];
        contentStream = B00180PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*0,500,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText = "　発行日時："+CreateDtm;
        contentStream = B00180PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*0,300,leading,SetText,font,FontSize-2,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        
        SetText = "　　仕入先:("+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColSpCd]+")"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColSpName01];
        contentStream = B00180PdfControl.TextSetBox(contentStream, 20,(float)HeightStart-leading*1,500,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText = "";
        if(ReprintFg) {
        	SetText = "【再発行】前回発行日時："+BeforePrintDtm;
        }
        contentStream = B00180PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*1,300,leading,SetText,font,FontSize-2,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        
        SetText = "入荷予定No:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColArrNo];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*2,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText = "予定Noページ:"+ArrNoNowPage+"/"+ArrNoTotalPage+" 発行総ページ:"+NowTotalPage+"/"+TotalPageCount;
        contentStream = B00180PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*2,300,leading,SetText,font,FontSize-2,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        
        
        SetText = "荷主管理No:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColClArrNo];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*3,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText = "商品毎ページ:"+ItemPageCount+"/"+ItemTotalPage;
        contentStream = B00180PdfControl.TextSetBox(contentStream,520,(float)HeightStart-leading*3,300,leading,SetText,font,FontSize-2,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "入荷予定日:"+(String)ArrivalPlanHdRt[T00016ArrivalPlanHdRt.ColPlanDate];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*4,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "商品CD";
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*6,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  (String)MsData[MsColItemCd];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*7,800,leading*7,SetText,font,80,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "ロット";
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*14,300,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText =  (String)MsData[MsColLot];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*15,300,leading*3,SetText,font,30,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "賞味期限";
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*18,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText =  (String)MsData[MsColExpDate];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*19,300,leading*3,SetText,font,30,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        
        SetText =  "商品名";
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream,320,(float)HeightStart-leading*14,150,leading,SetText,font,FontSize-2,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        SetText =  (String)MsData[MsColItemName];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream,320,(float)HeightStart-leading*15,500,leading*7,SetText,font,30,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        
        SetText =  "入数(カートン:"+(int)MsData[MsColCtUnitQty]+"バラ 　　　ケース:"+(int)MsData[MsColCsUnitQty]+"バラ　　　パレット:"+(int)MsData[MsColPlUnitQty]+"バラ)";
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*22,800,leading*2,SetText,font,15,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "予定総数:"+(int)MsData[MsColQty];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*24,800,leading*3,SetText,font,30,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "予定数("+"バラ:"+(int)MsData[MsColBaraQty]+"　カートン:"+(int)MsData[MsColCtQty]+" 　ケース:"+(int)MsData[MsColCsQty]+"　パレット:"+(int)MsData[MsColPlQty]+")";
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*27,800,leading*3,SetText,font,30,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        SetText =  "推奨ロケ:"+(String)MsData[MsColRecomendLoc];
        contentStream = B00180PdfControl.TextSetBoxFontAdjust(contentStream, 20,(float)HeightStart-leading*30,800,leading*3,SetText,font,15,Color.BLACK,0,false,Color.BLACK,false,Color.WHITE);
        
        
        SetText =  "WTList0001010ArrivalPoster";//修正時に探しやすくするためにクラス名
        contentStream = B00180PdfControl.TextSetBox(contentStream,20,0+leading,800,leading,SetText,font,9,Color.BLACK,1,false,Color.BLACK,false,Color.WHITE);
		return contentStream;
	}
	
	
	private static String OutPutPath() {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\ArrivalPlan";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\ArrivalPlan\\ArrivalPoster0001010";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		String FileName = "ArrivalPoster0001010"+NowDTM+".pdf";
		String OutPutPath = FLD_PATH+"\\"+FileName;
		
		//古いデータ削除
		B00040ToolsFolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ArrivalPlanList0001",B00100DefaultVariable.ErrTxtDelete);
		
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
		if(0<ClMstRt.length) {SearchClGpCD.add((String)ClMstRt[0][M00011ClMstRt.ColClGpCD]);}
		
		for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			SearchItemCd.add((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColItemCd]);
		}
		
		Object[][] StockRt= T00030StockRt.StockRt(
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
		if(0<ClMstRt.length) {SearchClGpCd.add((String)ClMstRt[0][M00011ClMstRt.ColClGpCD]);}
		
		for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			SearchItemCd.add((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColItemCd]);
		}
		
		Object[][] ItemMstRt = M00070ItemMstRt.ItemMstRt(
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
			ItemCd.add((String)ArrivalPlanMsRt[i][T00015ArrivalPlanMsRt.ColItemCd]);
		}
		
		Object[][] ItemRecomendLocFromItemCd = M00120ItemRecomendLocMstRt.ItemRecomendLocFromItemCd(ClCd,ItemCd,ItemMstRecomendLocExistenceOnly);
		
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
		
		Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
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
		
		SearchPrintCd.add("ArrivalPoster0001010");
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
			SetPrintCd[i]		= "ArrivalPoster0001010";	//印刷帳票CD
			SetOkuriNo[i]		= TgtArrNo.get(i);			//送り状番号等
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