import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_OkuriData_05_ArrayEntrySourceDataView{
	static final int ColClCd			= 0;
	static final int ColInvoiceWhCd	= 1;
	static final int ColClDeliNo		= 2;
	static final int ColPlanDate		= 3;
	static final int ColClDeliCd		= 4;
	static final int ColCom01			= 5;
	static final int ColMsClItemCd	= 6;
	static final int ColMsLot			= 7;	//明細ロット指定
	static final int ColMsExpDate		= 8;	//明細賞味期限指定
	static final int ColMsPackingQty	= 9;
	static final int ColMsCom01		=10;
	
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static Object[][] RtOkuriDataArrayEntrySourceDataView(){
		Object[][] RtOkuriDataArrayEntrySourceDataView= {
									 {"ClCd"			,ColClCd				,"String"	,"荷主CD"			,""	}
									,{"InvoiceWhCd"		,ColInvoiceWhCd		,"String"	,"担当倉庫CD"		,""	}
									,{"ClDeliNo"		,ColClDeliNo			,"String"	,"荷主予定番号"		,""	}
									,{"PlanDate"		,ColPlanDate			,"Date"		,"出荷予定日"		,""	}
									,{"ClDeliCd"		,ColClDeliCd			,"String"	,"荷主荷届先CD"		,""	}
									,{"Com01"			,ColCom01				,"String"	,"コメント01"		,""	}
									,{"MsClItemCd"		,ColMsClItemCd		,"String"	,"明細荷主商品CD"	,""	}
									,{"MsPackingQty"	,ColMsPackingQty		,"int"		,"明細荷姿数量"		,""	}
									,{"MsCom01"			,ColMsCom01			,"String"	,"明細コメント01"	,""	}
									,{"MsLot"			,ColMsLot				,"String"	,"明細ロット指定"	,""	}
									,{"MsExpDate"		,ColMsExpDate			,"Date"		,"明細賞味期限指定"	,""	}
									};
		
		Object[][] Rt = new Object[RtOkuriDataArrayEntrySourceDataView.length][RtOkuriDataArrayEntrySourceDataView[0].length];
		
		for(int i=0;i<RtOkuriDataArrayEntrySourceDataView.length;i++) {
			for(int i01=0;i01<RtOkuriDataArrayEntrySourceDataView[i].length;i01++) {
				Rt[(int)RtOkuriDataArrayEntrySourceDataView[i][1]][i01]	= RtOkuriDataArrayEntrySourceDataView[i][i01];
			}
		}
		return Rt;
	}
	
	public static void OkuriDataArrayEntrySourceDataView(int x,int y,String[][] EntryData){
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00出荷予定取込（データ内容確認）　WT100_OkuriData_05_ArrayEntrySourceDataView","SP");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_Msg 	= B100_FrameParts.JLabelSet( 10, 50,300,20,"以下のデータを取込もうとしています"	,11,0);
		main_fm.add(LB_Msg);
		
		Object[][] RtOkuriDataArrayEntrySourceDataView = WT100_OkuriData_05_ArrayEntrySourceDataView.RtOkuriDataArrayEntrySourceDataView();
		
		String[] columnNames01 = new String[RtOkuriDataArrayEntrySourceDataView.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtOkuriDataArrayEntrySourceDataView.length;i++) {
			columnNames01[1+i] = ""+RtOkuriDataArrayEntrySourceDataView[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel MainFmTableModel = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(MainFmTableModel);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtOkuriDataArrayEntrySourceDataView.length;i++) {
			if("int".equals((String)RtOkuriDataArrayEntrySourceDataView[i][2])||"float".equals((String)RtOkuriDataArrayEntrySourceDataView[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,75,860,500,tb01);
		main_fm.add(scpn01);
		
		for(int i=0;i<EntryData.length;i++) {
			Object[] SetOb = new Object[RtOkuriDataArrayEntrySourceDataView.length+1];
			SetOb[0] = false;
			SetOb[1+ColClCd]			= B100_TextControl.Trim(EntryData[i][ColClCd]);					//荷主CD
			SetOb[1+ColInvoiceWhCd]	= B100_TextControl.Trim(EntryData[i][ColInvoiceWhCd]);			//担当倉庫CD
			SetOb[1+ColClDeliNo]		= B100_TextControl.Trim(EntryData[i][ColClDeliNo]);				//荷主予定番号
			SetOb[1+ColPlanDate]		= B100_TextControl.TextToDate(EntryData[i][ColPlanDate]);		//出荷予定日
			SetOb[1+ColClDeliCd]		= B100_TextControl.Trim(EntryData[i][ColClDeliCd]);				//荷主荷届先CD
			SetOb[1+ColCom01]			= B100_TextControl.Trim(EntryData[i][ColCom01]);					//コメント01
			SetOb[1+ColMsClItemCd]	= B100_TextControl.Trim(EntryData[i][ColMsClItemCd]);				//明細荷主商品CD
			SetOb[1+ColMsPackingQty]	= B100_TextControl.TextToInt(EntryData[i][ColMsPackingQty]);	//明細荷姿数量
			SetOb[1+ColMsCom01]		= B100_TextControl.Trim(EntryData[i][ColMsCom01]);				//明細コメント01
			SetOb[1+ColMsLot]			= B100_TextControl.Trim(EntryData[i][ColMsLot]);					//明細ロット指定
			SetOb[1+ColMsExpDate]		= B100_TextControl.TextToDate(EntryData[i][ColMsExpDate]);		//明細賞味期限指定
			
			if(0<(int)SetOb[1+ColMsPackingQty] && !"".equals((String)SetOb[1+ColClDeliCd]) && !"".equals((String)SetOb[1+ColMsClItemCd])) {
				SetOb[1+ColMsPackingQty]	= ""+SetOb[1+ColMsPackingQty];
				MainFmTableModel.addRow(SetOb);
			}
		}
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_OkuriHd_00_Search.OkuriHdSearch(0,0);
			}
		});
	}
}