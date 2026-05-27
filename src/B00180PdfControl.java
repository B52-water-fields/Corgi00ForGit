import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class B00180PdfControl{
	
	public static void PdfCreate(String FP,boolean PageRotateFg) {
		//指定フルパスでPDFファイルを生成する
		//RotateFg = trueならA4横向き
	    try {
	    	PDDocument document = new PDDocument();
	    	PDPage page=PageRt(PageRotateFg);
	    	document.addPage(page);
	    	
	    	float HeightStart 	= 820;
	    	float MaxWide 		= 550;
	    	if(PageRotateFg) {
	    		/*用紙を横向きにする場合*/
				HeightStart = 570;
				MaxWide 	= 800;
	    	}
	    	
	        
	        
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
	        	ArrayList<String> ttfTgtList	= ttcFontttfListCheck(FontPath);
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
	        int FontSize = 12;
	        String[][] TableData = {
	        					 {"ページタイトル"}
	        					,{"001000","テスト01","111"}
	        					,{"002000","テスト02","222"}
	        					,{"003000","テスト03テスト03テスト03テスト03テスト03テスト03テスト03テスト03テスト03テスト03","333"}
	        					,{"004000","テスト04","444"}
	        					,{"005000","テスト05","555"}
	        					};
	        
			
	        PDPageContentStream contentStream = new PDPageContentStream(document, page);
	        contentStream.beginText();
	        contentStream.setFont(font, FontSize);
	        contentStream.newLineAtOffset((float)200, (float)500);
	        contentStream.showText( "お試し作成" );
	        contentStream.endText();
	        contentStream.close();
	        /*
	        page = PageRt(PageRotateFg);
	        document.addPage( page );
	        */
	        String SetString = "Str"
	        		+ "\nテスト02テスト02テスト02"
	        		+ "\nテスト03テスト03テスト03テスト03テスト03テスト03テスト03テスト03テスト03テスト03"
	        		+ "\nテスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01テスト01"
	        		+ "\nEnd";
	        
	        document = TextDraw(document,page,MaxWide,PageRotateFg,font,FontSize,Color.RED,10,HeightStart,SetString);
	        
	        page = PageRt(PageRotateFg);
	        document.addPage( page );
	        contentStream = new PDPageContentStream(document, page);
	        contentStream.close();
	        
	        SetString = "";
    		for(int i=0;i<150;i++) {
    			SetString = SetString+"テスト"+i+"\n";
    		}
	        		
	        document = TextDraw(document,page,MaxWide,PageRotateFg,font,FontSize,Color.BLACK,10,HeightStart,SetString);
	        
	        
	        /*
	        PDPageContentStream contentStream2 = SquareDraw(document,page,font,FontSize,SetString,(float)10,(float)10,(float)300,(float)3,(float)3);
	        contentStream2.close();
			*/
	        document.save(FP);
	        document.close();
	        if(null!=collection) {
	        	collection.close();
	        }
	      }
	      catch (IOException e) {
	        e.printStackTrace();
	      }
	}
	
	private static PDPage PageRt(boolean PageRotateFg) {
    	PDPage page; 
    	if(PageRotateFg) {
    		/*用紙を横向きにする場合*/
	        PDRectangle rectangle = new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth());
			page = new PDPage(rectangle);
    	}else {
    		page = new PDPage(PDRectangle.A4);
    	}
    	return page;
	}
	
	private static PDDocument TextDraw(PDDocument document,PDPage page,float MaxWide,boolean PageRotateFg,PDFont font,int FontSize,Color FontColor,float Xstr,float Ystr,String SetString) {
		PDPageContentStream contentStream;
		String[] SetStringList = SetString.split("\n");
		float fontHeight = font.getFontDescriptor()
	            .getFontBoundingBox().getHeight() / 1000 * FontSize;
		float leading = fontHeight * (float)1.2;
		float NowHeight = Ystr;
		try {
			contentStream = new PDPageContentStream(document, page,PDPageContentStream.AppendMode.APPEND,true,true);
			contentStream.beginText();
			contentStream.setFont(font, FontSize);
	        contentStream.setNonStrokingColor(FontColor);
	        contentStream.newLineAtOffset(Xstr, Ystr);
	        contentStream.setLeading(leading);
			for(int i01=0;i01<SetStringList.length;i01++) {
				float StringWidth = font.getStringWidth(SetStringList[i01]) / 1000 * FontSize;
				if(MaxWide>=StringWidth) {
					contentStream.showText(SetStringList[i01]);
				}else {
					String CST = "";
					for(int i02=1;i02<SetStringList[i01].length();i02++) {
						String AddST = SetStringList[i01].substring(i02-1,i02);
						float CheckWidth = font.getStringWidth(CST+AddST) / 1000 * FontSize;
						if(MaxWide>=CheckWidth) {
							CST = CST + AddST;
						}else {
							contentStream.showText(CST);
							CST = AddST;
							contentStream.newLine();
							NowHeight = NowHeight-leading;
						}
					}
					if(!"".equals(CST)) {
						contentStream.showText(CST);
					}
				}
		        if(i01+1<SetStringList.length) {
		        	NowHeight = NowHeight-leading;
		        	if(0>NowHeight) {
		        		contentStream.endText();
		    	        contentStream.close();
		        		NowHeight = Ystr;
		        		page = PageRt(PageRotateFg);
		    	        document.addPage( page );
		    	        contentStream = new PDPageContentStream(document, page);
		    	        contentStream.beginText();
		    			contentStream.setFont(font, FontSize);
		    	        contentStream.setNonStrokingColor(FontColor);
		    	        contentStream.newLineAtOffset(Xstr, Ystr);
		    	        contentStream.setLeading(leading);
		        	}else {
		        		contentStream.newLine();
		        	}
		        }
			}
		    contentStream.endText();
		    contentStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return document;
	}
	
	private static PDPageContentStream SquareDraw(PDDocument document,PDPage page,PDFont font,int FontSize,String SetString,float X,float Y,float Width,float WideMargin,float HeightMargin) {
		//所定の横幅の箱の中に文字列を表示する
		PDPageContentStream contentStream = null;
		try {
			float fontHeight = font.getFontDescriptor()
		            .getFontBoundingBox().getHeight() / 1000 * FontSize;
			
			String[] SetStringList = SetString.split("\n");
			
			
			float DrawWidth = Width - WideMargin*2;
			
			for(int i=0;i<SetStringList.length;i++) {
				ArrayList<String> SetLine = new ArrayList<String>();
				float StringWidth = font.getStringWidth(SetStringList[i]) / 1000 * FontSize;
				if(DrawWidth>=StringWidth) {
					SetLine.add(SetStringList[i]);
				}else {
					String CST = "";
					for(int i01=1;i01<SetStringList[i].length();i01++) {
						String AddST = SetStringList[i].substring(i01-1,i01);
						float CheckWidth = font.getStringWidth(CST+AddST) / 1000 * FontSize;
						if(DrawWidth>=CheckWidth) {
							CST = CST + AddST;
						}else {
							SetLine.add(CST);
							CST = AddST;
						}
					}
					if(!"".equals(CST)) {SetLine.add(CST);}
				}
				float Height = (fontHeight*SetLine.size())+HeightMargin*2;
				contentStream = new PDPageContentStream(document, page);
				contentStream.addRect(X, Y, Width, Height);
				contentStream.setStrokingColor(Color.RED);
				contentStream.stroke();
				
				float SetX = X+WideMargin;
		        float SetY = Y+Height-fontHeight-HeightMargin;
				
				for(int i01=0;i01<SetLine.size();i01++) {
					contentStream.beginText();
			        contentStream.setFont(font, 12);
			        
			        contentStream.newLineAtOffset(SetX,SetY);
			        contentStream.showText(SetLine.get(i01));
			        contentStream.endText();
			        
			        SetX = X+WideMargin;
			        SetY = SetY-fontHeight;
				}
				X = X;
				Y = Y-Height;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contentStream;
	}
	
	
	
	private static ArrayList<String> ttcFontttfListCheck(String FontPath) {
		//フォント名取得
		ArrayList<String> ttfTgtList = new ArrayList<String>();
		
		File file = new File(FontPath);

        Font[] fonts;
		try {
			fonts = Font.createFonts(file);
			for (Font f : fonts) {
				ttfTgtList.add(""+f.getPSName());
				/*
	            System.out.println("FontName      : " + f.getFontName());
	            System.out.println("Family        : " + f.getFamily());
	            System.out.println("PS Name       : " + f.getPSName());
	            System.out.println("-------------------------");
	            */
	        }
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return ttfTgtList;
	}
}