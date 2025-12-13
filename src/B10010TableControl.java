import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class B10010TableControl{
	//明細業表示テーブル制御
	//RenewTgt列のみ編集可
	static int[] RenewTgt;
	static class MyTableModel01 extends DefaultTableModel{
		MyTableModel01(String[] columnNames, int rowNum){
			super(columnNames, rowNum);
		}
		//RenewTgtのデータは項目のクラス取得する
		public Class getColumnClass(int col){
			boolean Renew = false;
			if(null==RenewTgt) {RenewTgt = new int[0];}
			for(int i=0;i<RenewTgt.length;i++) {
				if(RenewTgt[i]==col) {
					Renew = true;
				}
			}
			
			if(Renew){
				Object ob = getValueAt(0,col);
				return ob.getClass();
			}else{
				Object ob = new Object();
				return ob.getClass();
			}
		}
		//RenewTgt以外編集不可
		public boolean isCellEditable(int row, int column) {
			Object flag = this.getValueAt(0, column);
			boolean Renew = false;
			if(null==RenewTgt) {RenewTgt = new int[0];}
			for(int i=0;i<RenewTgt.length;i++) {
				if(RenewTgt[i]==column) {
					Renew = true;
				}
			}
			
			if(Renew){
				return true;
			}else{
				return false;
			}
		}
	}
	
	//テーブル情報をcsv出力する
	public static void TableOutPutCsv(String SelectMSG,String OutPutName,JTable TgtTable) {
		String Selected = B00080FolderSelect.FolderSelect(SelectMSG);
		if(null!=Selected) {
			int row_count = TgtTable.getRowCount();
			int col_count = TgtTable.getColumnCount();
			String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
			String FileName = OutPutName+NowDTM+".csv";
			ArrayList<String> OutString = new ArrayList<String>();
			
			String SetSt = "";
			for(int i=0;i<col_count;i++) {
				//項目名取得
				SetSt=SetSt+TgtTable.getColumnName(i)+",";
			}
			OutString.add(SetSt);
			for(int i=0;i<row_count;i++){
				SetSt = "";
				for(int i01=0;i01<col_count;i01++) {
					//項目値取得カンマ除去 改行コード除去
					String WST = ""+TgtTable.getValueAt(i,i01);
					SetSt=SetSt+WST.replace(",", "").replace("\n", "").replace("\"", "")+",";
				}
				OutString.add(SetSt);
			}
			String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
			now_dtm=now_dtm.replace("/", "").replace(":", "").replace(" ", "");
			FileName = now_dtm + FileName;

			//ファイルに出力
			String FP = Selected+"\\"+FileName;
			B00030ToolsTextExport.txt_exp2(OutString,FP,"UTF-8");

			//ファイル開く
			File file = new File(FP);
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}