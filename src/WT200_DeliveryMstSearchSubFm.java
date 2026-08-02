import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT200_DeliveryMstSearchSubFm{
	//検索子画面として届先マスタ検索する
	//業務系の画面から召喚される
	
	static boolean RenewFg;
	
	static final int RtJFrame				= 0;
	static final int RtDefaultTableModel	= 1;
	static final int RtJTable				= 2;
	static final int EntryBtn				= 3;
	
	public static Object[] DeliveryMstSearchSubFm(int x,int y,String ClWh,String ClCd,String BackGroundColor){
		RenewFg=false;
		if(null==ClWh) {ClWh="";}
		if(null==ClCd) {ClCd="";}
		if("".equals(ClWh)) {ClWh=A00000_Main.ClWh;}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
		
		final JFrame Delivery_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00届先検索　WT200_DeliveryMstSearchSubFm",BackGroundColor);
		JLabel 	DeliveryUserinfo 	= B100_FrameParts.UserInfo();
		JButton DeliveryExit_btn 	= B100_FrameParts.ExitBtn();
		JButton DeliveryEntry_btn 	= B100_FrameParts.EntryBtn();
		
		Delivery_fm.add(DeliveryUserinfo);
		Delivery_fm.add(DeliveryExit_btn);
		Delivery_fm.add(DeliveryEntry_btn);
		
		//検索条件
		JLabel LB_SearchDECD 			= B100_FrameParts.JLabelSet(	  0, 50,100,20,"届先CD:"	,11,1);
		JLabel LB_SearchDepartmentCd 	= B100_FrameParts.JLabelSet(	  0, 75,100,20,"部署CD:"	,11,1);
		JLabel LB_SearchDEName 			= B100_FrameParts.JLabelSet(	  0,100,100,20,"届先名:"	,11,1);
		JLabel LB_SearchPost 			= B100_FrameParts.JLabelSet(	300, 50,100,20,"届先郵便:"	,11,1);
		JLabel LB_SearchAdd 			= B100_FrameParts.JLabelSet(	300, 75,100,20,"届先住所:"	,11,1);
		JLabel LB_SearchTel 			= B100_FrameParts.JLabelSet(	300,100,100,20,"届先電話:"	,11,1);
		
		final JTextField TB_SearchDECD 			= B100_FrameParts.JTextFieldSet(	100, 50,100,20,""	,11,0);	//届先CD
		final JTextField TB_SearchDepartmentCd 	= B100_FrameParts.JTextFieldSet(	100, 75,100,20,""	,11,0);	//部署CD
		final JTextField TB_SearchDEName 		= B100_FrameParts.JTextFieldSet(	100,100,100,20,""	,11,0);	//届先名
		final JTextField TB_SearchPost 			= B100_FrameParts.JTextFieldSet(	400, 50,100,20,""	,11,0);	//届先郵便
		final JTextField TB_SearchAdd 			= B100_FrameParts.JTextFieldSet(	400, 75,100,20,""	,11,0);	//届先住所
		final JTextField TB_SearchTel 			= B100_FrameParts.JTextFieldSet(	400,100,100,20,""	,11,0);	//届先電話
		
		JLabel LB2_SearchDECD 			= B100_FrameParts.JLabelSet(	200, 50,100,20,"と一致"		,11,0);
		JLabel LB2_SearchDepartmentCd 	= B100_FrameParts.JLabelSet(	200, 75,100,20,"と一致"		,11,0);
		JLabel LB2_SearchDEName 		= B100_FrameParts.JLabelSet(	200,100,100,20,"を含む"		,11,0);
		JLabel LB2_SearchPost 			= B100_FrameParts.JLabelSet(	500, 50,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchAdd 			= B100_FrameParts.JLabelSet(	500, 75,100,20,"を含む"		,11,0);
		JLabel LB2_SearchTel 			= B100_FrameParts.JLabelSet(	500,100,100,20,"を含む"		,11,0);
		
		JButton DeliverySearchKickBtn						= B100_FrameParts.BtnSet(			400,125, 90,20,"検索",11);
		
		Delivery_fm.add(LB_SearchDECD);
		Delivery_fm.add(LB_SearchDepartmentCd);
		Delivery_fm.add(LB_SearchDEName);
		Delivery_fm.add(LB_SearchPost);
		Delivery_fm.add(LB_SearchAdd);
		Delivery_fm.add(LB_SearchTel);
		
		Delivery_fm.add(TB_SearchDECD);
		Delivery_fm.add(TB_SearchDepartmentCd);
		Delivery_fm.add(TB_SearchDEName);
		Delivery_fm.add(TB_SearchPost);
		Delivery_fm.add(TB_SearchAdd);
		Delivery_fm.add(TB_SearchTel);
		
		Delivery_fm.add(LB2_SearchDECD);
		Delivery_fm.add(LB2_SearchDepartmentCd);
		Delivery_fm.add(LB2_SearchDEName);
		Delivery_fm.add(LB2_SearchPost);
		Delivery_fm.add(LB2_SearchAdd);
		Delivery_fm.add(LB2_SearchTel);
		
		Delivery_fm.add(DeliverySearchKickBtn);
		
		Object[][] RtDeliveryMstRt = M100_DeliveryMstRt.RtDeliveryMstRt();
		
		String[] columnNamesDelivery = new String[RtDeliveryMstRt.length+1];
		
		columnNamesDelivery[0] = "Fg";
		for(int i=0;i<RtDeliveryMstRt.length;i++) {
			columnNamesDelivery[1+(int)RtDeliveryMstRt[i][1]] = ""+RtDeliveryMstRt[i][3];
		}
		
		//編集可能カラム1列目のみ
		final DefaultTableModel tableModel_msDelivery = new B100_TableControl.MyTableModel00(columnNamesDelivery,0);
		
		final JTable tbDelivery = new JTable(tableModel_msDelivery);
		tbDelivery.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbDelivery.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tbDelivery.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModelItem
		= (DefaultTableColumnModel)tbDelivery.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModelItem.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtDeliveryMstRt.length;i++) {
			if("int".equals((String)RtDeliveryMstRt[i][2])||"float".equals((String)RtDeliveryMstRt[i][2])) {
				column = columnModelItem.getColumn(1+(int)RtDeliveryMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelItem.getColumn(1+(int)RtDeliveryMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnDelivery = B100_FrameParts.JScrollPaneSet(10,200,760,400,tbDelivery);
		Delivery_fm.add(scpnDelivery);
		
		RenewFg=true;
		
		//検索ボタン押下時の挙動
		DeliverySearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msDelivery.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msDelivery.removeRow(0);
					}
					
					String GetSearchDECD			= B100_TextControl.Trim(TB_SearchDECD.getText());				//届先CD
					String GetSearchDepartmentCd	= B100_TextControl.Trim(TB_SearchDepartmentCd.getText());		//部署CD
					String GetSearchDEName			= B100_TextControl.Trim(TB_SearchDEName.getText());				//届先名
					String GetSearchPost			= B100_TextControl.Trim(TB_SearchPost.getText());				//届先郵便
					String GetSearchAdd				= B100_TextControl.Trim(TB_SearchAdd.getText());				//届先住所
					String GetSearchTel				= B100_TextControl.Trim(TB_SearchTel.getText());				//届先電話
					
					ArrayList<String> SearchDECD 			= new ArrayList<String>();
					ArrayList<String> SearchDepartmentCd 	= new ArrayList<String>();
					ArrayList<String> SearchDEName 			= new ArrayList<String>();
					ArrayList<String> SearchPost 			= new ArrayList<String>();
					ArrayList<String> SearchAdd 			= new ArrayList<String>();
					ArrayList<String> SearchTel 			= new ArrayList<String>();
					ArrayList<String> SearchFax 			= new ArrayList<String>();
					ArrayList<String> SearchMail 			= new ArrayList<String>();
					ArrayList<String> SearchCom 			= new ArrayList<String>();
					ArrayList<String> SearchPrefecturesCd 	= new ArrayList<String>();
					ArrayList<String> SearchMunicipalityCd 	= new ArrayList<String>();
					ArrayList<String> SearchDelFg 			= new ArrayList<String>();
					boolean SearcNotJis = true;
					boolean SearchTelExactMatch = false;
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchDECD			)){SearchDECD.add(GetSearchDECD);}
					if(!"".equals(GetSearchDepartmentCd	)){SearchDepartmentCd.add(GetSearchDepartmentCd);}
					if(!"".equals(GetSearchDEName		)){SearchDEName.add(GetSearchDEName);}
					if(!"".equals(GetSearchPost			)){SearchPost.add(GetSearchPost);}
					if(!"".equals(GetSearchAdd			)){SearchAdd.add(GetSearchAdd);}
					if(!"".equals(GetSearchTel			)){SearchTel.add(GetSearchTel);}
					
					Object[][] DeliveryMstRt = M100_DeliveryMstRt.DeliveryMstRt(
						SearchDECD,
						SearchDepartmentCd,
						SearchDEName,
						SearchPost,
						SearchAdd,
						SearchTel,
						SearchFax,
						SearchMail,
						SearchCom,
						SearchPrefecturesCd,
						SearchMunicipalityCd,
						SearchDelFg,
						SearcNotJis,
						SearchTelExactMatch,
						AllSearch
						);
					
					for(int i01=0;i01<DeliveryMstRt.length;i01++) {
						Object[] SetOb = new Object[1+DeliveryMstRt[i01].length];
						SetOb[0] = false;
						for(int i02=0;i02<DeliveryMstRt[i01].length;i02++) {
							SetOb[1+i02] = DeliveryMstRt[i01][i02];
						}
						tableModel_msDelivery.addRow(SetOb);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//届先マスタ検索チェックボックス操作時の挙動
		tableModel_msDelivery.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tbDelivery.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_msDelivery.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		
		DeliveryExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Delivery_fm.setVisible(false);
			}
		});
		
		Object[] Rt = {
				Delivery_fm
				,tableModel_msDelivery
				,tbDelivery
				,DeliveryEntry_btn
				};
		return Rt;
	}
}