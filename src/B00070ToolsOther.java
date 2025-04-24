import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class B00070ToolsOther{
	//その他のツール
	
	//税込み合計金額→金額・税率（パーセント10%の場合10）を受け取って内税計算し、税別金額・消費税を返却する
	public static int[] InsideTaxCalc(int TotalCost,int TaxRate) {
		int[] rt = new int[2];
		BigDecimal GetTotalCost = new BigDecimal(""+TotalCost);
		BigDecimal GetTaxRate = new BigDecimal(""+TaxRate);
		BigDecimal Hundred = new BigDecimal("100");
		BigDecimal GetConsumptionTax = ((GetTotalCost.divide((Hundred.add(GetTaxRate)),7,BigDecimal.ROUND_HALF_UP)).multiply(GetTaxRate)).setScale(0, BigDecimal.ROUND_HALF_UP);
		BigDecimal GetWithOutTax = GetTotalCost.subtract(GetConsumptionTax);
		rt[0] = GetWithOutTax.intValue();
		rt[1] = GetConsumptionTax.intValue();
		return rt;
	}
	//税込み合計金額→金額・税率（パーセント10%の場合10）を受け取って外税計算し、税別金額・消費税を返却する
	public static int[] OutsideTaxCalc(int TotalCost,int TaxRate) {
		int[] rt = new int[2];
		BigDecimal GetTotalCost = new BigDecimal(""+TotalCost);
		BigDecimal GetTaxRate = new BigDecimal(""+TaxRate);
		BigDecimal Hundred = new BigDecimal("100");
		BigDecimal GetConsumptionTax = (GetTotalCost.multiply(GetTaxRate)).divide(Hundred,0,BigDecimal.ROUND_DOWN);
		BigDecimal GetWithOutTax = GetTotalCost;
		rt[0] = GetWithOutTax.intValue();
		rt[1] = GetConsumptionTax.intValue();
		return rt;
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