import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_Ship_20_ForceEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	//登録明細情報テーブル列定義
	static final int ColSetFg				=  0;	//Fg
	static final int ColLoc				=  1;	//ロケーション
	static final int ColItemCd				=  2;	//商品コード
	static final int ColQty				=  3;	//数量 
	static final int ColLot				=  4;	//ロット
	static final int ColExpdate			=  5;	//消費期限
	static final int ColActualDate		=  6;	//入荷実績日
	static final int ColItemName			=  7;	//商品名
	static final int ColCom01				=  8;	//コメント01
	static final int ColCom02				=  9;	//コメント02
	static final int ColLocName			= 10;	//ロケーション名
	static final int ColCtUnitQty			= 11;	//カートン入数
	static final int ColCsUnitQty			= 12;	//ケース入数
	static final int ColPlUnitQty			= 13;	//パレット入数
	static final int ColNowQty				= 14;	//現在個総数量
	static final int ColNowShipPlanQty	= 15;	//現在個引当済総数
	static final int ColNowPossibleQty	= 16;	//現在個出荷可能総数
	static final int ColUnitName			= 17;	//商品単位
	static final int ColCtUnitName		= 18;	//カートン商品単位
	static final int ColCsUnitName		= 19;	//ケース商品単位
	static final int ColPlUnitName		= 20;	//パレット商品単位
	static final int ColCom03				= 21;	//コメント03
	static final int ColCom04				= 22;	//コメント04
	static final int ColCom05				= 23;	//コメント05
	
	
	//登録情報入力ボックス定義
	static final int ColTB_Row					= 0;
	static final int ColTB_Loc					= 1;
	static final int ColTB_LocName			= 2;
	static final int ColTB_ItemCd				= 3;
	static final int ColTB_ItemName			= 4;
	static final int ColTB_Lot					= 5;
	static final int ColTB_Expdate			= 6;
	static final int ColTB_ActualDate			= 7;
	
	static final int ColTB_PlUnitQty			= 8;
	static final int ColTB_CsUnitQty			= 9;
	static final int ColTB_CtUnitQty			=10;
	
	static final int ColTB_NowQty				=11;
	static final int ColTB_NowShipPlanQty	=12;
	static final int ColTB_NowPossibleQty	=13;
	
	static final int ColTB_Qty					=14;
	static final int ColTB_EntryMode			=15;
	static final int ColTB_PlQty				=16;
	static final int ColTB_CsQty				=17;
	static final int ColTB_CtQty				=18;
	static final int ColTB_BrQty				=19;
	
	static final int ColTB_QtyUnitname		=20;
	static final int ColTB_PlQtyUnitname		=21;
	static final int ColTB_CsQtyUnitname		=22;
	static final int ColTB_CtQtyUnitname		=23;
	static final int ColTB_BrQtyUnitname		=24;
	
	static final int ColTB_Com01				=25;
	static final int ColTB_Com02				=26;
	static final int ColTB_Com03				=27;
	static final int ColTB_Com04				=28;
	static final int ColTB_Com05				=29;
	
	//荷送人情報登録定義
	static final int ColTB_NiokuriCd				=  0;
	static final int ColTB_NiokuriDepartmentCd	=  1;
	static final int ColTB_NiokuriName01			=  2;
	static final int ColTB_NiokuriName02			=  3;
	static final int ColTB_NiokuriName03			=  4;
	static final int ColTB_NioKuriTel				=  5;
	static final int ColTB_NioKuriFax				=  6;
	
	static final int ColTB_NiokuriPost			=  7;
	static final int ColTB_NiokuriAdd01			=  8;
	static final int ColTB_NiokuriAdd02			=  9;
	static final int ColTB_NiokuriAdd03			= 10;
	static final int ColTB_NiokuriMunicCd		= 11;
	static final int ColTB_NioKuriMail			= 12;
	
	//荷届け先情報登録定義
	static final int ColTB_DeliCd					=  0;
	static final int ColTB_DeliDepartmentCd		=  1;
	static final int ColTB_DeliName01				=  2;
	static final int ColTB_DeliName02				=  3;
	static final int ColTB_DeliName03				=  4;
	static final int ColTB_DeliTel				=  5;
	static final int ColTB_DeliFax				=  6;
	
	static final int ColTB_DeliPost				=  7;
	static final int ColTB_DeliAdd01				=  8;
	static final int ColTB_DeliAdd02				=  9;
	static final int ColTB_DeliAdd03				= 10;
	static final int ColTB_DeliMunicCd			= 11;
	static final int ColTB_DeliMail				= 12;

	private static Object[][] MainFmTableModelLayout(){
		Object[][] Rt = {
						 {"SetFg"			,ColSetFg				,"String"	,"Fg"				,""		}
						,{"Loc"				,ColLoc				,"String"	,"ロケーション"		,"Key"	}
						,{"ItemCd"			,ColItemCd				,"String"	,"商品コード"		,"Key"	}
						,{"Qty"				,ColQty				,"int"		,"出荷数量"			,""		}
						,{"Lot"				,ColLot				,"String"	,"ロット"			,"Key"	}
						,{"Expdate"			,ColExpdate			,"Date"		,"消費期限"			,"Key"	}
						,{"ActualDate"		,ColActualDate		,"int"		,"入荷実績日"		,"Key"	}
						,{"ItemName"		,ColItemName			,"String"	,"商品名"			,""		}
						,{"Com01"			,ColCom01				,"String"	,"コメント01"		,""		}
						,{"Com02"			,ColCom02				,"String"	,"コメント02"		,""		}
						,{"LocName"			,ColLocName			,"String"	,"ロケーション名"	,""		}
						,{"CtUnitQty"		,ColCtUnitQty			,"int"		,"カートン入数"		,""		}
						,{"CsUnitQty"		,ColCsUnitQty			,"int"		,"ケース入数"		,""		}
						,{"PlUnitQty"		,ColPlUnitQty			,"int"		,"パレット入数"		,""		}
						,{"NowQty"			,ColNowQty				,"int"		,"現在総数量"		,""		}
						,{"NowShipPlanQty"	,ColNowShipPlanQty	,"int"		,"現在引当済数"		,""		}
						,{"NowPossibleQty"	,ColNowPossibleQty	,"int"		,"現在出荷可能数"	,""		}
						,{"UnitName"		,ColUnitName			,"String"	,"商品単位"			,""		}
						,{"CtUnitName"		,ColCtUnitName		,"String"	,"カートン商品単位"	,""		}
						,{"CsUnitName"		,ColCsUnitName		,"String"	,"ケース商品単位"	,""		}
						,{"PlUnitName"		,ColPlUnitName		,"String"	,"パレット商品単位"	,""		}
						,{"Com03"			,ColCom03				,"String"	,"コメント03"		,""		}
						,{"Com04"			,ColCom04				,"String"	,"コメント04"		,""		}
						,{"Com05"			,ColCom05				,"String"	,"コメント05"		,""		}
						};
		
		return Rt;
	}
	
	public static void ShipForceEntry(int x,int y,String TgtWhCd,String TgtClCd,String TgtLoc,String TgtItemCd,String TgtLot,String TgtExpDate,String TgtActualDate) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd	= A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd	= A00000_Main.ClCd;}

		final JFrame main_fm 	= B100_FrameParts.FrameCreate(x,y,1300,800,"Corgi00強制出荷（予定なし出荷）　WT100_Ship_20_ForceEntry","SP");
		JLabel userinfo 		= B100_FrameParts.UserInfo();
		JButton exit_btn 		= B100_FrameParts.ExitBtn();
		JButton entry_btn 		= B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ClCd		= B100_FrameParts.JLabelSet(  0, 25,100,20,"荷主コード:"		,11,1);
		JLabel LB_WhCd		= B100_FrameParts.JLabelSet(  0, 50,100,20,"倉庫コード:"		,11,1);
		
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(	100, 25,200,20,B100_DefaultVariable.SearchClList[0],11);
		final JComboBox TB_WhCd		= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchWhList[0],11);
		
		TB_ClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1],TgtClCd,true));
		TB_WhCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1],TgtWhCd,true));
		
		TB_ClCd.setEnabled(false);
		TB_WhCd.setEnabled(false);
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		
		//荷送人パネル
		JPanel PN_Niokuri 		= B100_FrameParts.JPanelSet( 10, 75,620,210,"White");
		JLabel PN_NiokuriLabel 	= B100_FrameParts.JLabelSet( 10,  0,200, 20,"荷送人情報",11,0);
		PN_Niokuri.add(PN_NiokuriLabel);
		
		JLabel LB_To			= B100_FrameParts.JLabelSet(630,180, 20, 20,"⇒"		,11,2);
		main_fm.add(LB_To);
		
		//荷届先パネル
		JPanel PN_Deli 			= B100_FrameParts.JPanelSet(650, 75,620,210,"White");
		JLabel PN_DeliLabel 	= B100_FrameParts.JLabelSet( 10,  0,200, 20,"荷届先情報",11,0);
		PN_Deli.add(PN_DeliLabel);
		
		//出荷登録パネル
		JPanel PN_Control 			= B100_FrameParts.JPanelSet( 10,300,620,400,"White");
		JLabel PN_ControlLabel 		= B100_FrameParts.JLabelSet( 10,  0,200, 20,"情報登録",11,0);
		PN_Control.add(PN_ControlLabel);
		
		JLabel LB_NiokuriCd				= B100_FrameParts.JLabelSet(  0, 25,100,20,"荷送人CD:"			,11,1);
		JLabel LB_NiokuriDepartmentCd	= B100_FrameParts.JLabelSet(  0, 50,100,20,"部署CD:"			,11,1);
		JLabel LB_NiokuriName01			= B100_FrameParts.JLabelSet(  0, 75,100,20,"荷送人名01:"		,11,1);
		JLabel LB_NiokuriName02			= B100_FrameParts.JLabelSet(  0,100,100,20,"荷送人名02:"		,11,1);
		JLabel LB_NiokuriName03			= B100_FrameParts.JLabelSet(  0,125,100,20,"荷送人名03:"		,11,1);
		JLabel LB_NioKuriTel			= B100_FrameParts.JLabelSet(  0,150,100,20,"荷送人TEL:"		,11,1);
		JLabel LB_NioKuriFax			= B100_FrameParts.JLabelSet(  0,175,100,20,"荷送人FAX:"		,11,1);
		
		JLabel LB_NiokuriPost			= B100_FrameParts.JLabelSet(300, 50,100,20,"荷送人郵便番号:"	,11,1);
		JLabel LB_NiokuriAdd01			= B100_FrameParts.JLabelSet(300, 75,100,20,"荷送人住所01:"		,11,1);
		JLabel LB_NiokuriAdd02			= B100_FrameParts.JLabelSet(300,100,100,20,"荷送人住所02:"		,11,1);
		JLabel LB_NiokuriAdd03			= B100_FrameParts.JLabelSet(300,125,100,20,"荷送人住所03:"		,11,1);
		JLabel LB_NiokuriMunicCd		= B100_FrameParts.JLabelSet(300,150,100,20,"市区町村CD:"		,11,1);
		JLabel LB_NioKuriMail			= B100_FrameParts.JLabelSet(300,175,100,20,"荷送人MAIL:"		,11,1);
		
		final JTextField TB_NiokuriCd			= B100_FrameParts.JTextFieldSet(100, 25,100,20,""	,11,0);
		final JTextField TB_NiokuriDepartmentCd	= B100_FrameParts.JTextFieldSet(100, 50,100,20,""	,11,0);
		final JTextField TB_NiokuriName01		= B100_FrameParts.JTextFieldSet(100, 75,200,20,""	,11,0);
		final JTextField TB_NiokuriName02		= B100_FrameParts.JTextFieldSet(100,100,200,20,""	,11,0);
		final JTextField TB_NiokuriName03		= B100_FrameParts.JTextFieldSet(100,125,200,20,""	,11,0);
		final JTextField TB_NioKuriTel			= B100_FrameParts.JTextFieldSet(100,150,100,20,""	,11,0);
		final JTextField TB_NioKuriFax			= B100_FrameParts.JTextFieldSet(100,175,100,20,""	,11,0);
		
		final JTextField TB_NiokuriPost			= B100_FrameParts.JTextFieldSet(400, 50,100,20,""	,11,0);
		final JTextField TB_NiokuriAdd01		= B100_FrameParts.JTextFieldSet(400, 75,200,20,""	,11,0);
		final JTextField TB_NiokuriAdd02		= B100_FrameParts.JTextFieldSet(400,100,200,20,""	,11,0);
		final JTextField TB_NiokuriAdd03		= B100_FrameParts.JTextFieldSet(400,125,200,20,""	,11,0);
		final JTextField TB_NiokuriMunicCd		= B100_FrameParts.JTextFieldSet(400,150,100,20,""	,11,0);
		final JTextField TB_NioKuriMail			= B100_FrameParts.JTextFieldSet(400,175,200,20,""	,11,0);
		
		//検索ボタン
		JButton NiokuriSearchBtn = B100_FrameParts.BtnSet(210, 25,100,20,"荷送人検索",11);
		PN_Niokuri.add(NiokuriSearchBtn);
		//検索子画面として届先マスタ検索し荷送人にセット
		Object[] NiokuriMstSearchSubFm = WT200_DeliveryMstSearchSubFm.DeliveryMstSearchSubFm(x+10,y+10,TgtWhCd,TgtClCd,"SP");
		
		
		TB_NiokuriCd.setEditable(false);
		TB_NiokuriDepartmentCd.setEditable(false);
		TB_NiokuriMunicCd.setEditable(false);
		
		PN_Niokuri.add(LB_NiokuriCd);
		PN_Niokuri.add(LB_NiokuriDepartmentCd);
		PN_Niokuri.add(LB_NiokuriName01);
		PN_Niokuri.add(LB_NiokuriName02);
		PN_Niokuri.add(LB_NiokuriName03);
		PN_Niokuri.add(LB_NiokuriPost);
		PN_Niokuri.add(LB_NiokuriAdd01);
		PN_Niokuri.add(LB_NiokuriAdd02);
		PN_Niokuri.add(LB_NiokuriAdd03);
		PN_Niokuri.add(LB_NioKuriMail);
		
		PN_Niokuri.add(LB_NioKuriTel);
		PN_Niokuri.add(LB_NioKuriFax);
		PN_Niokuri.add(LB_NiokuriMunicCd);
		
		PN_Niokuri.add(TB_NiokuriCd);
		PN_Niokuri.add(TB_NiokuriDepartmentCd);
		PN_Niokuri.add(TB_NiokuriName01);
		PN_Niokuri.add(TB_NiokuriName02);
		PN_Niokuri.add(TB_NiokuriName03);
		PN_Niokuri.add(TB_NiokuriPost);
		PN_Niokuri.add(TB_NiokuriAdd01);
		PN_Niokuri.add(TB_NiokuriAdd02);
		PN_Niokuri.add(TB_NiokuriAdd03);
		PN_Niokuri.add(TB_NioKuriMail);
		
		PN_Niokuri.add(TB_NioKuriTel);
		PN_Niokuri.add(TB_NioKuriFax);
		PN_Niokuri.add(TB_NiokuriMunicCd);
		
		JLabel LB_DeliCd			= B100_FrameParts.JLabelSet(  0, 25,100,20,"荷送人CD:"			,11,1);
		JLabel LB_DeliDepartmentCd	= B100_FrameParts.JLabelSet(  0, 50,100,20,"部署CD:"			,11,1);
		JLabel LB_DeliName01		= B100_FrameParts.JLabelSet(  0, 75,100,20,"荷届先名01:"		,11,1);
		JLabel LB_DeliName02		= B100_FrameParts.JLabelSet(  0,100,100,20,"荷届先名02:"		,11,1);
		JLabel LB_DeliName03		= B100_FrameParts.JLabelSet(  0,125,100,20,"荷届先名03:"		,11,1);
		JLabel LB_DeliTel			= B100_FrameParts.JLabelSet(  0,150,100,20,"荷届先TEL:"		,11,1);
		JLabel LB_DeliFax			= B100_FrameParts.JLabelSet(  0,175,100,20,"荷届先FAX:"		,11,1);
		
		JLabel LB_DeliPost			= B100_FrameParts.JLabelSet(300, 50,100,20,"荷届先郵便番号:"	,11,1);
		JLabel LB_DeliAdd01			= B100_FrameParts.JLabelSet(300, 75,100,20,"荷届先住所01:"		,11,1);
		JLabel LB_DeliAdd02			= B100_FrameParts.JLabelSet(300,100,100,20,"荷届先住所02:"		,11,1);
		JLabel LB_DeliAdd03			= B100_FrameParts.JLabelSet(300,125,100,20,"荷届先住所03:"		,11,1);
		JLabel LB_DeliMunicCd		= B100_FrameParts.JLabelSet(300,150,100,20,"市区町村CD:"		,11,1);
		JLabel LB_DeliMail			= B100_FrameParts.JLabelSet(300,175,100,20,"荷届先MAIL:"		,11,1);
		
		final JTextField TB_DeliCd				= B100_FrameParts.JTextFieldSet(100, 25,100,20,""	,11,0);
		final JTextField TB_DeliDepartmentCd	= B100_FrameParts.JTextFieldSet(100, 50,100,20,""	,11,0);
		final JTextField TB_DeliName01			= B100_FrameParts.JTextFieldSet(100, 75,200,20,""	,11,0);
		final JTextField TB_DeliName02			= B100_FrameParts.JTextFieldSet(100,100,200,20,""	,11,0);
		final JTextField TB_DeliName03			= B100_FrameParts.JTextFieldSet(100,125,200,20,""	,11,0);
		final JTextField TB_DeliTel				= B100_FrameParts.JTextFieldSet(100,150,100,20,""	,11,0);
		final JTextField TB_DeliFax				= B100_FrameParts.JTextFieldSet(100,175,100,20,""	,11,0);
		
		final JTextField TB_DeliPost			= B100_FrameParts.JTextFieldSet(400, 50,100,20,""	,11,0);
		final JTextField TB_DeliAdd01			= B100_FrameParts.JTextFieldSet(400, 75,200,20,""	,11,0);
		final JTextField TB_DeliAdd02			= B100_FrameParts.JTextFieldSet(400,100,200,20,""	,11,0);
		final JTextField TB_DeliAdd03			= B100_FrameParts.JTextFieldSet(400,125,200,20,""	,11,0);
		final JTextField TB_DeliMunicCd			= B100_FrameParts.JTextFieldSet(400,150,100,20,""	,11,0);
		final JTextField TB_DeliMail			= B100_FrameParts.JTextFieldSet(400,175,200,20,""	,11,0);
		
		//検索ボタン
		JButton DeliSearchBtn = B100_FrameParts.BtnSet(210, 25,100,20,"荷届先検索",11);
		PN_Deli.add(DeliSearchBtn);
		//検索子画面として届先マスタ検索し荷送人にセット
		Object[] DeliMstSearchSubFm = WT200_DeliveryMstSearchSubFm.DeliveryMstSearchSubFm(x+10,y+10,TgtWhCd,TgtClCd,"SP");
		
		TB_DeliCd.setEditable(false);
		TB_DeliDepartmentCd.setEditable(false);
		TB_DeliMunicCd.setEditable(false);

		PN_Deli.add(LB_DeliCd);
		PN_Deli.add(LB_DeliDepartmentCd);
		PN_Deli.add(LB_DeliName01);
		PN_Deli.add(LB_DeliName02);
		PN_Deli.add(LB_DeliName03);
		PN_Deli.add(LB_DeliPost);
		PN_Deli.add(LB_DeliAdd01);
		PN_Deli.add(LB_DeliAdd02);
		PN_Deli.add(LB_DeliAdd03);
		PN_Deli.add(LB_DeliMail);
		
		PN_Deli.add(LB_DeliTel);
		PN_Deli.add(LB_DeliFax);
		PN_Deli.add(LB_DeliMunicCd);
		
		PN_Deli.add(TB_DeliCd);
		PN_Deli.add(TB_DeliDepartmentCd);
		PN_Deli.add(TB_DeliName01);
		PN_Deli.add(TB_DeliName02);
		PN_Deli.add(TB_DeliName03);
		PN_Deli.add(TB_DeliPost);
		PN_Deli.add(TB_DeliAdd01);
		PN_Deli.add(TB_DeliAdd02);
		PN_Deli.add(TB_DeliAdd03);
		PN_Deli.add(TB_DeliMail);
		
		PN_Deli.add(TB_DeliTel);
		PN_Deli.add(TB_DeliFax);
		PN_Deli.add(TB_DeliMunicCd);
		
		JLabel LB_Row				= B100_FrameParts.JLabelSet(  0, 25,100,20,"対象行:"			,11,1);
		JLabel LB_Loc				= B100_FrameParts.JLabelSet(  0, 50,100,20,"ロケーション:"		,11,1);
		JLabel LB_ItemCd			= B100_FrameParts.JLabelSet(  0, 75,100,20,"商品:"				,11,1);
		JLabel LB_Lot				= B100_FrameParts.JLabelSet(  0,100,100,20,"ロット:"			,11,1);
		JLabel LB_Expdate			= B100_FrameParts.JLabelSet(  0,125,100,20,"消費期限:"			,11,1);
		JLabel LB_ActualDate		= B100_FrameParts.JLabelSet(  0,150,100,20,"入荷実績日:"		,11,1);
		
		JLabel LB_PlUnitQty			= B100_FrameParts.JLabelSet(200,100,100,20,"パレット入数:"		,11,1);
		JLabel LB_CsUnitQty			= B100_FrameParts.JLabelSet(200,125,100,20,"ケース入数:"		,11,1);
		JLabel LB_CtUnitQty			= B100_FrameParts.JLabelSet(200,150,100,20,"カートン入数:"		,11,1);
		
		JLabel LB_NowQty			= B100_FrameParts.JLabelSet(370,100,130,20,"現在総数量:"		,11,1);
		JLabel LB_NowShipPlanQty	= B100_FrameParts.JLabelSet(370,125,130,20,"現在引当済数:"		,11,1);
		JLabel LB_NowPossibleQty	= B100_FrameParts.JLabelSet(370,150,130,20,"現在出荷可能数:"	,11,1);
		
		JLabel LB_Qty				= B100_FrameParts.JLabelSet(  0,175,100,20,"出荷数量:"			,11,1);
		JLabel LB_PlQty				= B100_FrameParts.JLabelSet(  0,225,100,20,"パレット数量:"		,11,1);
		JLabel LB_CsQty				= B100_FrameParts.JLabelSet(  0,250,100,20,"ケース数量:"		,11,1);
		JLabel LB_CtQty				= B100_FrameParts.JLabelSet(  0,275,100,20,"カートン数量:"		,11,1);
		JLabel LB_BrQty				= B100_FrameParts.JLabelSet(  0,300,100,20,"バラ数量:"			,11,1);
		
		JLabel LB_Com01				= B100_FrameParts.JLabelSet(300,200,100,20,"コメント01:"		,11,1);
		JLabel LB_Com02				= B100_FrameParts.JLabelSet(300,225,100,20,"コメント02:"		,11,1);
		JLabel LB_Com03				= B100_FrameParts.JLabelSet(300,250,100,20,"コメント03:"		,11,1);
		JLabel LB_Com04				= B100_FrameParts.JLabelSet(300,275,100,20,"コメント04:"		,11,1);
		JLabel LB_Com05				= B100_FrameParts.JLabelSet(300,300,100,20,"コメント05:"		,11,1);
		
		final JTextField TB_Row				= B100_FrameParts.JTextFieldSet(100, 25,100,20,""	,11,0);
		final JTextField TB_Loc				= B100_FrameParts.JTextFieldSet(100, 50,100,20,""	,11,0);
		final JTextField TB_LocName			= B100_FrameParts.JTextFieldSet(210, 50,300,20,""	,11,0);
		final JTextField TB_ItemCd			= B100_FrameParts.JTextFieldSet(100, 75,100,20,""	,11,0);
		final JTextField TB_ItemName		= B100_FrameParts.JTextFieldSet(210, 75,300,20,""	,11,0);
		final JTextField TB_Lot				= B100_FrameParts.JTextFieldSet(100,100,100,20,""	,11,0);
		final JTextField TB_Expdate			= B100_FrameParts.JTextFieldSet(100,125,100,20,""	,11,0);
		final JTextField TB_ActualDate		= B100_FrameParts.JTextFieldSet(100,150,100,20,""	,11,0);
		
		final JTextField TB_PlUnitQty		= B100_FrameParts.JTextFieldSet(300,100, 70,20,""	,11,0);
		final JTextField TB_CsUnitQty		= B100_FrameParts.JTextFieldSet(300,125, 70,20,""	,11,0);
		final JTextField TB_CtUnitQty		= B100_FrameParts.JTextFieldSet(300,150, 70,20,""	,11,0);
		
		final JFormattedTextField TB_NowQty			= B100_FrameParts.JFormattedTextFieldSet(500,100,100,20,"",11,1,"####");
		final JFormattedTextField TB_NowShipPlanQty	= B100_FrameParts.JFormattedTextFieldSet(500,125,100,20,"",11,1,"####");
		final JFormattedTextField TB_NowPossibleQty	= B100_FrameParts.JFormattedTextFieldSet(500,150,100,20,"",11,1,"####");
		
		final JFormattedTextField TB_Qty			= B100_FrameParts.JFormattedTextFieldSet(	100,175,100,20,"",11,1,"####");
		final JCheckBox TB_EntryMode 				= B100_FrameParts.JCheckBoxSet(				100,200,150,20,"荷姿別で調整",11);
		final JFormattedTextField TB_PlQty			= B100_FrameParts.JFormattedTextFieldSet(	100,225,100,20,"",11,1,"####");
		final JFormattedTextField TB_CsQty			= B100_FrameParts.JFormattedTextFieldSet(	100,250,100,20,"",11,1,"####");
		final JFormattedTextField TB_CtQty			= B100_FrameParts.JFormattedTextFieldSet(	100,275,100,20,"",11,1,"####");
		final JFormattedTextField TB_BrQty			= B100_FrameParts.JFormattedTextFieldSet(	100,300,100,20,"",11,1,"####");
		
		final JLabel TB_QtyUnitname					= B100_FrameParts.JLabelSet(200,175,100,20,""		,11,0);
		final JLabel TB_PlQtyUnitname				= B100_FrameParts.JLabelSet(200,225,100,20,""		,11,0);
		final JLabel TB_CsQtyUnitname				= B100_FrameParts.JLabelSet(200,250,100,20,""		,11,0);
		final JLabel TB_CtQtyUnitname				= B100_FrameParts.JLabelSet(200,275,100,20,""		,11,0);
		final JLabel TB_BrQtyUnitname				= B100_FrameParts.JLabelSet(200,300,100,20,""		,11,0);
		
		final JTextField TB_Com01					= B100_FrameParts.JTextFieldSet(400,200,200,20,""	,11,0);
		final JTextField TB_Com02					= B100_FrameParts.JTextFieldSet(400,225,200,20,""	,11,0);
		final JTextField TB_Com03					= B100_FrameParts.JTextFieldSet(400,250,200,20,""	,11,0);
		final JTextField TB_Com04					= B100_FrameParts.JTextFieldSet(400,275,200,20,""	,11,0);
		final JTextField TB_Com05					= B100_FrameParts.JTextFieldSet(400,300,200,20,""	,11,0);
		
		TB_Row.setEditable(false);
		TB_Loc.setEditable(false);
		TB_LocName.setEditable(false);
		TB_ItemCd.setEditable(false);
		TB_ItemName.setEditable(false);
		TB_Lot.setEditable(false);
		TB_Expdate.setEditable(false);
		TB_ActualDate.setEditable(false);
		
		TB_CtUnitQty.setEditable(false);
		TB_CsUnitQty.setEditable(false);
		TB_PlUnitQty.setEditable(false);
		
		TB_NowQty.setEditable(false);
		TB_NowShipPlanQty.setEditable(false);
		TB_NowPossibleQty.setEditable(false);
		TB_Qty.setEditable(true);
		TB_PlQty.setEditable(false);
		TB_CsQty.setEditable(false);
		TB_CtQty.setEditable(false);
		TB_BrQty.setEditable(false);
		
		TB_Com01.setEditable(true);
		TB_Com02.setEditable(true);
		TB_Com03.setEditable(true);
		TB_Com04.setEditable(true);
		TB_Com05.setEditable(true);
		
		
		TB_Row.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_Loc.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_LocName.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_ItemCd.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_ItemName.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_Lot.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_Expdate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_ActualDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		TB_CtUnitQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_CsUnitQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_PlUnitQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		TB_NowQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_NowShipPlanQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_NowPossibleQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_Qty.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_PlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_CsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_CtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_BrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		TB_Com01.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Com02.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Com03.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Com04.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Com05.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		PN_Control.add(LB_Row);
		PN_Control.add(LB_Loc);
		PN_Control.add(LB_ItemCd);
		PN_Control.add(LB_Lot);
		PN_Control.add(LB_Expdate);
		PN_Control.add(LB_ActualDate);
		
		PN_Control.add(LB_CtUnitQty);
		PN_Control.add(LB_CsUnitQty);
		PN_Control.add(LB_PlUnitQty);
		
		PN_Control.add(LB_NowQty);
		PN_Control.add(LB_NowShipPlanQty);
		PN_Control.add(LB_NowPossibleQty);
		
		PN_Control.add(LB_Qty);
		PN_Control.add(LB_PlQty);
		PN_Control.add(LB_CsQty);
		PN_Control.add(LB_CtQty);
		PN_Control.add(LB_BrQty);
		
		PN_Control.add(LB_Com01);
		PN_Control.add(LB_Com02);
		PN_Control.add(LB_Com03);
		PN_Control.add(LB_Com04);
		PN_Control.add(LB_Com05);
		
		PN_Control.add(TB_Row);
		PN_Control.add(TB_Loc);
		PN_Control.add(TB_LocName);
		PN_Control.add(TB_ItemCd);
		PN_Control.add(TB_ItemName);
		PN_Control.add(TB_Lot);
		PN_Control.add(TB_Expdate);
		PN_Control.add(TB_ActualDate);
		
		PN_Control.add(TB_CtUnitQty);
		PN_Control.add(TB_CsUnitQty);
		PN_Control.add(TB_PlUnitQty);
		
		PN_Control.add(TB_NowQty);
		PN_Control.add(TB_NowShipPlanQty);
		PN_Control.add(TB_NowPossibleQty);
		
		PN_Control.add(TB_Qty);
		PN_Control.add(TB_EntryMode);
		PN_Control.add(TB_PlQty);
		PN_Control.add(TB_CsQty);
		PN_Control.add(TB_CtQty);
		PN_Control.add(TB_BrQty);
		
		PN_Control.add(TB_QtyUnitname);
		PN_Control.add(TB_PlQtyUnitname);
		PN_Control.add(TB_CsQtyUnitname);
		PN_Control.add(TB_CtQtyUnitname);
		PN_Control.add(TB_BrQtyUnitname);
		
		PN_Control.add(TB_Com01);
		PN_Control.add(TB_Com02);
		PN_Control.add(TB_Com03);
		PN_Control.add(TB_Com04);
		PN_Control.add(TB_Com05);
		
		Object[] EntryControlSet = {
				TB_Row,
				TB_Loc,
				TB_LocName,
				TB_ItemCd,
				TB_ItemName,
				TB_Lot,
				TB_Expdate,
				TB_ActualDate,
				
				TB_PlUnitQty,
				TB_CsUnitQty,
				TB_CtUnitQty,
				
				TB_NowQty,
				TB_NowShipPlanQty,
				TB_NowPossibleQty,
				
				TB_Qty,
				TB_EntryMode,
				TB_PlQty,
				TB_CsQty,
				TB_CtQty,
				TB_BrQty,
				
				TB_QtyUnitname,
				TB_PlQtyUnitname,
				TB_CsQtyUnitname,
				TB_CtQtyUnitname,
				TB_BrQtyUnitname,
				
				TB_Com01,
				TB_Com02,
				TB_Com03,
				TB_Com04,
				TB_Com05
				};
		
		Object[] NiokuriEntrySet	= {
				TB_NiokuriCd,
				TB_NiokuriDepartmentCd,
				TB_NiokuriName01,
				TB_NiokuriName02,
				TB_NiokuriName03,
				TB_NioKuriTel,
				TB_NioKuriFax,
				
				TB_NiokuriPost,
				TB_NiokuriAdd01,
				TB_NiokuriAdd02,
				TB_NiokuriAdd03,
				TB_NiokuriMunicCd,
				TB_NioKuriMail
				};
		
		Object[] DeliEntrySet	= {
				TB_DeliCd,
				TB_DeliDepartmentCd,
				TB_DeliName01,
				TB_DeliName02,
				TB_DeliName03,
				TB_DeliTel,
				TB_DeliFax,
				
				TB_DeliPost,
				TB_DeliAdd01,
				TB_DeliAdd02,
				TB_DeliAdd03,
				TB_DeliMunicCd,
				TB_DeliMail
				};
		
		//在庫検索ボタン
		JButton StockSearchBtn = B100_FrameParts.BtnSet(210, 25,100,20,"在庫検索",11);
		PN_Control.add(StockSearchBtn);
		//検索子画面として在庫検索
		final Object[] StockSearchSubFm	= WT200_StockSearchSubFm.StockSearchSubFm(0,0,TgtWhCd,TgtClCd,"SP");
		
		//引継いだ在庫を強制出荷対象にする
		if(null!=TgtWhCd && null!=TgtClCd && null!=TgtLoc && null!=TgtItemCd && null!=TgtLot && null!=TgtExpDate && null!=TgtActualDate
				&& !"".equals(TgtWhCd) && !"".equals(TgtClCd) && !"".equals(TgtLoc) && !"".equals(TgtItemCd) && !"".equals(TgtExpDate) && !"".equals(TgtActualDate)
				) {
			Object[][] StockRt	= StockRt(TgtWhCd,TgtClCd,TgtLoc,TgtItemCd,TgtLot,TgtExpDate,TgtActualDate);
			if(1==StockRt.length) {
				String GetClCd			= (String)StockRt[0][T100_StockRt.ColClCd];			//荷主コード
				String GetCLName		= (String)StockRt[0][T100_StockRt.ColCLName];			//荷主表記名
				String GetWhCd			= (String)StockRt[0][T100_StockRt.ColWhCd];			//倉庫コード
				String GetClWHName		= (String)StockRt[0][T100_StockRt.ColClWHName];		//担当倉庫名
				String GetClGpCD		= (String)StockRt[0][T100_StockRt.ColClGpCD];			//荷主グループCD
				String GetClGpName		= (String)StockRt[0][T100_StockRt.ColClGpName];		//グループ名1
				String GetLoc			= (String)StockRt[0][T100_StockRt.ColLoc];				//ロケーション
				String GetLocName		= (String)StockRt[0][T100_StockRt.ColLocName];			//ロケーション名
				int GetType				= (int)StockRt[0][T100_StockRt.ColType];				//ロケタイプ
				String GetItemCd		= (String)StockRt[0][T100_StockRt.ColItemCd];			//商品コード
				String GetLot			= (String)StockRt[0][T100_StockRt.ColLot];				//ロット
				String GetExpdate		= (String)StockRt[0][T100_StockRt.ColExpdate];			//消費期限
				String GetActualDate	= (String)StockRt[0][T100_StockRt.ColActualDate];		//入荷実績日
				int GetQty				= (int)StockRt[0][T100_StockRt.ColQty];				//総数量
				int GetShipPlanQty		= (int)StockRt[0][T100_StockRt.ColShipPlanQty];		//引当済総数
				int GetPossibleQty		= (int)StockRt[0][T100_StockRt.ColPossibleQty];		//出荷可能総数
				String GetItemName		= (String)StockRt[0][T100_StockRt.ColItemName];		//商品名
				String GetItemName01	= (String)StockRt[0][T100_StockRt.ColItemName01];		//商品表記名
				String GetItemName02	= (String)StockRt[0][T100_StockRt.ColItemName02];		//商品正式名
				String GetItemName03	= (String)StockRt[0][T100_StockRt.ColItemName03];		//商品略名
				String GetClItemCd		= (String)StockRt[0][T100_StockRt.ColClItemCd];		//荷主商品コード
				String GetJanCd			= (String)StockRt[0][T100_StockRt.ColJanCd];			//ソースマーク_BCD（バラ）
				String GetItemMdNo		= (String)StockRt[0][T100_StockRt.ColItemMdNo];		//商品型番
				int GetCtUnitQty		= (int)StockRt[0][T100_StockRt.ColCtUnitQty];			//カートン入数
				int GetCsUnitQty		= (int)StockRt[0][T100_StockRt.ColCsUnitQty];			//ケース入数
				int GetPlUnitQty		= (int)StockRt[0][T100_StockRt.ColPlUnitQty];			//パレット入数
				String GetUnitName		= (String)StockRt[0][T100_StockRt.ColUnitName];		//商品単位
				String GetCtUnitName	= (String)StockRt[0][T100_StockRt.ColCtUnitName];		//カートン商品単位
				String GetCsUnitName	= (String)StockRt[0][T100_StockRt.ColCsUnitName];		//ケース商品単位
				String GetPlUnitName	= (String)StockRt[0][T100_StockRt.ColPlUnitName];		//パレット商品単位
				String GetEntryDate		= (String)StockRt[0][T100_StockRt.ColEntryDate];		//登録日時
				String GetUpdateDate	= (String)StockRt[0][T100_StockRt.ColUpdateDate];		//更新日時
				String GetEntryUser		= (String)StockRt[0][T100_StockRt.ColEntryUser];		//登録者
				String GetUpdateUser	= (String)StockRt[0][T100_StockRt.ColUpdateUser];		//更新者
				int GetBrQty			= (int)StockRt[0][T100_StockRt.ColBrQty];				//バラ数量
				int GetBrShipPlanQty	= (int)StockRt[0][T100_StockRt.ColBrShipPlanQty];		//引当済バラ数
				int GetBrPossibleQty	= (int)StockRt[0][T100_StockRt.ColBrPossibleQty];		//出荷可能バラ数
				int GetCtQty			= (int)StockRt[0][T100_StockRt.ColCtQty];				//カートン数量
				int GetCtShipPlanQty	= (int)StockRt[0][T100_StockRt.ColCtShipPlanQty];		//引当済カートン数
				int GetCtPossibleQty	= (int)StockRt[0][T100_StockRt.ColCtPossibleQty];		//出荷可能カートン数
				int GetCsQty			= (int)StockRt[0][T100_StockRt.ColCsQty];				//ケース数量
				int GetCsShipPlanQty	= (int)StockRt[0][T100_StockRt.ColCsShipPlanQty];		//引当済ケース数
				int GetCsPossibleQty	= (int)StockRt[0][T100_StockRt.ColCsPossibleQty];		//出荷可能ケース数
				int GetPlQty			= (int)StockRt[0][T100_StockRt.ColPlQty];				//パレット数量
				int GetPlShipPlanQty	= (int)StockRt[0][T100_StockRt.ColPlShipPlanQty];		//引当済パレット数
				int GetPlPossibleQty	= (int)StockRt[0][T100_StockRt.ColPlPossibleQty];		//出荷可能パレット数
				
				TB_Row.setText("-1");
				TB_Loc.setText(GetLoc);
				TB_LocName.setText(GetLocName);
				TB_ItemCd.setText(GetItemCd);
				TB_ItemName.setText(GetItemName01);
				TB_Lot.setText(GetLot);
				TB_Expdate.setText(GetExpdate);
				TB_ActualDate.setText(GetActualDate);
				
				TB_CtUnitQty.setText(""+GetCtUnitQty);
				TB_CsUnitQty.setText(""+GetCsUnitQty);
				TB_PlUnitQty.setText(""+GetPlUnitQty);
				
				TB_NowQty.setText(""+GetQty);
				TB_NowShipPlanQty.setText(""+GetShipPlanQty);
				TB_NowPossibleQty.setText(""+GetPossibleQty);
				
				TB_Qty.setText("0");
				TB_EntryMode.setSelected(false);
				TB_PlQty.setText("0");
				TB_CsQty.setText("0");
				TB_CtQty.setText("0");
				TB_BrQty.setText("0");
				
				TB_QtyUnitname.setText(GetUnitName);
				TB_PlQtyUnitname.setText(GetPlUnitName);
				TB_CsQtyUnitname.setText(GetCsUnitName);
				TB_CtQtyUnitname.setText(GetCtUnitName);
				TB_BrQtyUnitname.setText(GetUnitName);
				
				TB_Com01.setText("");
				TB_Com02.setText("");
				TB_Com03.setText("");
				TB_Com04.setText("");
				TB_Com05.setText("");
			}
		}
		
		
		//明細確定ボタン
		JButton MsEntryBtn = B100_FrameParts.BtnSet(500, 350,100,20,"明細確定",11);
		PN_Control.add(MsEntryBtn);
		
		//明細削除ボタン
		JButton MsRemoveBtn = B100_FrameParts.BtnSet(380, 350,100,20,"明細削除",11);
		PN_Control.add(MsRemoveBtn);
		
		//荷主情報取得して荷送人に設定
		Object[][] ClMstRt	= ClMstRt(TgtClCd);
		if(1==ClMstRt.length) {
			String GetCLName01		= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColCLName01]);	//荷主表記名
			String GetPost			= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColPost]);			//郵便番号
			String GetAdd01			= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColAdd01]);		//住所1
			String GetAdd02			= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColAdd02]);		//住所2
			String GetAdd03			= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColAdd03]);		//住所3
			String GetTel			= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColTel]);			//電話番号
			String GetFax			= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColFax]);			//FAX
			String GetMail			= B100_TextControl.Trim((String)ClMstRt[0][M100_ClMstRt.ColMail]);			//メールアドレス
			
			TB_NiokuriName01.setText(GetCLName01);
			TB_NiokuriPost.setText(GetPost);
			TB_NiokuriAdd01.setText(GetAdd01);
			TB_NiokuriAdd02.setText(GetAdd02);
			TB_NiokuriAdd03.setText(GetAdd03);
			TB_NioKuriMail.setText(GetMail);
			TB_NioKuriTel.setText(GetTel);
			TB_NioKuriFax.setText(GetFax);
		}
		
		//強制出荷を届先に指定
		Object[][] ForceDeliGet	= ForceDeliGet();
		if(1==ForceDeliGet.length) {
			String GetDECD				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColDECD]);					//届先CD
			String GetDepartmentCd		= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColDepartmentCd]);		//部署CD
			String GetDEName01			= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColDEName01]);				//届先表記名
			String GetPost				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColPost]);					//届先郵便
			String GetAdd01				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColAdd01]);				//届先住所1
			String GetAdd02				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColAdd02]);				//届先住所2
			String GetAdd03				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColAdd03]);				//届先住所3
			String GetTel				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColTel]);					//届先電話
			String GetFax				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColFax]);					//届先FAX
			String GetMail				= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColMail]);					//届先MAIL
			String GetMunicipalityCd	= B100_TextControl.Trim((String)ForceDeliGet[0][M100_DeliveryMstRt.ColMunicipalityCd]);		//JIS市区町村CD5桁
			
			TB_DeliCd.setText(GetDECD);
			TB_DeliDepartmentCd.setText(GetDepartmentCd);
			TB_DeliName01.setText(GetDEName01);
			TB_DeliName02.setText("");
			TB_DeliName03.setText("");
			TB_DeliPost.setText(GetPost);
			TB_DeliAdd01.setText(GetAdd01);
			TB_DeliAdd02.setText(GetAdd02);
			TB_DeliAdd03.setText(GetAdd03);
			TB_DeliMail.setText(GetMail);
			
			TB_DeliTel.setText(GetTel);
			TB_DeliFax.setText(GetFax);
			TB_DeliMunicCd.setText(GetMunicipalityCd);
		}
		
		main_fm.add(PN_Niokuri);
		main_fm.add(PN_Deli);
		main_fm.add(PN_Control);
		
		Object[][] MainFmTableModelLayout	= MainFmTableModelLayout();
		
		String[] columnNames01 = new String[MainFmTableModelLayout.length];
		
		for(int i=0;i<MainFmTableModelLayout.length;i++) {
			columnNames01[(int)MainFmTableModelLayout[i][1]]	= (String)MainFmTableModelLayout[i][3];
		}
		
		final DefaultTableModel MainFmTableModel = new B100_TableControl.MyTableModel00(columnNames01,0);
		
		final JTable tb01 = new JTable(MainFmTableModel);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		column = columnModel01.getColumn(0);			column.setPreferredWidth( 20*A00000_Main.Mul/A00000_Main.Div);	
		for(int i=1;i<MainFmTableModelLayout.length;i++) {
			if("int".equals((String)MainFmTableModelLayout[i][2])||"float".equals((String)MainFmTableModelLayout[i][2])) {
				column = columnModel01.getColumn((int)MainFmTableModelLayout[i][1]);			column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn((int)MainFmTableModelLayout[i][1]);			column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(650,300,620,400,tb01);
		main_fm.add(scpn01);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String ClCd = B100_DefaultVariable.SearchClList[1][TB_ClCd.getSelectedIndex()];
					String WhCd = B100_DefaultVariable.SearchWhList[1][TB_WhCd.getSelectedIndex()];
					ArrayList<String> ErrMsg	= ForceEntryFix(WhCd,ClCd,NiokuriEntrySet,DeliEntrySet,MainFmTableModel);
					if(null!=ErrMsg&&0<ErrMsg.size()) {
						ErrView(ErrMsg);
					}else {
						JOptionPane.showMessageDialog(null, "登録完了　業務メニューに戻ります");
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						((JFrame)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
						((JFrame)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).dispose();
						
						((JFrame)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
						((JFrame)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).dispose();
						
						((JFrame)StockSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
						((JFrame)StockSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).dispose();

						main_fm.setVisible(false);
						main_fm.dispose();
						A00001_WorkMain.WorkMain(0, 0);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//明細確定ボタン押下時の挙動
		MsEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					MsEntry(EntryControlSet,MainFmTableModel);
					RenewFg = true;
				}
			}
		});
		
		//明細削除ボタン
		MsRemoveBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					MsDelete(EntryControlSet,MainFmTableModel);
					RenewFg = true;
				}
			}
		});
		
		//登録モード操作時の挙動
		TB_EntryMode.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					EntryModeControl(EntryControlSet);
					RenewFg = true;
				}
			}
		});
		
		//出荷数量フォーカス消失時の挙動
		TB_Qty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					QtyControl(EntryControlSet);
					RenewFg = true;
				}
			}
		});
		
		//パレット数量フォーカス消失時の挙動
		TB_PlQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(EntryControlSet);
					RenewFg = true;
				}
			}
		});
		//ケース数量フォーカス消失時の挙動
		TB_CsQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(EntryControlSet);
					RenewFg = true;
				}
			}
		});
		//カートン数量フォーカス消失時の挙動
		TB_CtQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(EntryControlSet);
					RenewFg = true;
				}
			}
		});
		//バラ数量フォーカス消失時の挙動
		TB_BrQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(EntryControlSet);
					RenewFg = true;
				}
			}
		});
		//荷送人郵便番号フォーカス消失時の挙動
		TB_NiokuriPost.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetNiokuriPost= B100_TextControl.num_only_String(TB_NiokuriPost.getText());
					Object[][] PostRt=PostRt(GetNiokuriPost);
					
					if(1==PostRt.length) {
						int option = JOptionPane.showConfirmDialog(null, "郵便番号を元に住所上書きしますか？","登録確認", JOptionPane.YES_NO_OPTION,
							      JOptionPane.WARNING_MESSAGE);
						if (option == JOptionPane.YES_OPTION){
							String GetPOST				= (String)PostRt[0][M100_PostMstRt.ColPOST];				//郵便番号
							String GetPREFECTURES		= (String)PostRt[0][M100_PostMstRt.ColPREFECTURES];		//県
							String GetMUNICI01			= (String)PostRt[0][M100_PostMstRt.ColMUNICI01];			//市区町村
							String GetMUNICI02			= (String)PostRt[0][M100_PostMstRt.ColMUNICI02];			//町丁目
							String GetMUNICIPALITY_CD 	= (String)PostRt[0][M100_PostMstRt.ColMUNICIPALITY_CD];	//市区町村CD
							
							TB_NiokuriPost.setText(GetPOST);
							TB_NiokuriAdd01.setText(GetPREFECTURES+GetMUNICI01+GetMUNICI02);
							TB_NiokuriAdd02.setText("");
							TB_NiokuriAdd03.setText("");
							TB_NiokuriMunicCd.setText(GetMUNICIPALITY_CD);
						}else {
							
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//届先郵便番号フォーカス消失時の挙動
		TB_DeliPost.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetDeliPost= B100_TextControl.num_only_String(TB_DeliPost.getText());
					Object[][] PostRt=PostRt(GetDeliPost);
					
					if(1==PostRt.length) {
						int option = JOptionPane.showConfirmDialog(null, "郵便番号を元に住所上書きしますか？","登録確認", JOptionPane.YES_NO_OPTION,
							      JOptionPane.WARNING_MESSAGE);
						if (option == JOptionPane.YES_OPTION){
							String GetPOST				= (String)PostRt[0][M100_PostMstRt.ColPOST];				//郵便番号
							String GetPREFECTURES		= (String)PostRt[0][M100_PostMstRt.ColPREFECTURES];		//県
							String GetMUNICI01			= (String)PostRt[0][M100_PostMstRt.ColMUNICI01];			//市区町村
							String GetMUNICI02			= (String)PostRt[0][M100_PostMstRt.ColMUNICI02];			//町丁目
							String GetMUNICIPALITY_CD 	= (String)PostRt[0][M100_PostMstRt.ColMUNICIPALITY_CD];	//市区町村CD
							
							TB_DeliPost.setText(GetPOST);
							TB_DeliAdd01.setText(GetPREFECTURES+GetMUNICI01+GetMUNICI02);
							TB_DeliAdd02.setText("");
							TB_DeliAdd03.setText("");
							TB_DeliMunicCd.setText(GetMUNICIPALITY_CD);
						}else {
							
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					TB_Row.setText("-1");
					TB_Loc.setText("");
					TB_LocName.setText("");
					TB_ItemCd.setText("");
					TB_ItemName.setText("");
					TB_Lot.setText("");
					TB_Expdate.setText("");
					TB_ActualDate.setText("");
					
					TB_CtUnitQty.setText("");
					TB_CsUnitQty.setText("");
					TB_PlUnitQty.setText("");
					
					TB_NowQty.setText("");
					TB_NowShipPlanQty.setText("");
					TB_NowPossibleQty.setText("");
					
					TB_Qty.setText("0");
					TB_EntryMode.setSelected(false);
					TB_PlQty.setText("0");
					TB_CsQty.setText("0");
					TB_CtQty.setText("0");
					TB_BrQty.setText("0");
					
					TB_QtyUnitname.setText("");
					TB_PlQtyUnitname.setText("");
					TB_CsQtyUnitname.setText("");
					TB_CtQtyUnitname.setText("");
					TB_BrQtyUnitname.setText("");
					
					TB_Com01.setText("");
					TB_Com02.setText("");
					TB_Com03.setText("");
					TB_Com04.setText("");
					TB_Com05.setText("");
					
					TB_Qty.setEditable(true);
					TB_PlQty.setEditable(false);
					TB_CsQty.setEditable(false);
					TB_CtQty.setEditable(false);
					TB_BrQty.setEditable(false);
					
					TB_Qty.setBackground(B100_FrameParts.SelectColer("Entry"));
					TB_PlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
					TB_CsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
					TB_CtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
					TB_BrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
					
					int row_count = MainFmTableModel.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
							if((boolean)MainFmTableModel.getValueAt(i, 0)) {
								String GetSetFg				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColSetFg));
								String GetLoc				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColLoc));
								String GetItemCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColItemCd));
								int GetQty					= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColQty));
								String GetLot				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColLot));
								String GetExpdate			= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i,ColExpdate));
								String GetActualDate		= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i,ColActualDate));
								String GetItemName			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColItemName));
								String GetCom01				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColCom01));
								String GetCom02				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColCom02));
								String GetLocName			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColLocName));
								int GetCtUnitQty			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColCtUnitQty));
								int GetCsUnitQty			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColCsUnitQty));
								int GetPlUnitQty			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColPlUnitQty));
								int GetNowQty				= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColNowQty));
								int GetNowShipPlanQty		= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColNowShipPlanQty));
								int GetNowPossibleQty		= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColNowPossibleQty));
								String GetUnitName			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColUnitName));
								String GetCtUnitName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColCtUnitName));
								String GetCsUnitName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColCsUnitName));
								String GetPlUnitName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColPlUnitName));
								String GetCom03				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColCom03));
								String GetCom04				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColCom04));
								String GetCom05				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColCom05));
								
								int BrQty = GetQty;
								int PlQty = 0;
								int CsQty = 0;
								int CtQty = 0;
								
								if(0<GetPlUnitQty) {
									PlQty	= (int)(BrQty/GetPlUnitQty);
									BrQty	= (int)(BrQty%GetPlUnitQty);
								}
								if(0<GetCsUnitQty) {
									CsQty	= (int)(BrQty/GetCsUnitQty);
									BrQty	= (int)(BrQty%GetCsUnitQty);
								}
								if(0<GetCtUnitQty) {
									CtQty	= (int)(BrQty/GetCtUnitQty);
									BrQty	= (int)(BrQty%GetCtUnitQty);
								}
								
								TB_Row.setText(""+i);
								TB_Loc.setText(GetLoc);
								TB_LocName.setText(GetLocName);
								TB_ItemCd.setText(GetItemCd);
								TB_ItemName.setText(GetItemName);
								TB_Lot.setText(GetLot);
								TB_Expdate.setText(GetExpdate);
								TB_ActualDate.setText(GetActualDate);
								
								TB_CtUnitQty.setText(""+GetCtUnitQty);
								TB_CsUnitQty.setText(""+GetCsUnitQty);
								TB_PlUnitQty.setText(""+GetPlUnitQty);
								
								TB_NowQty.setText(""+GetNowQty);
								TB_NowShipPlanQty.setText(""+GetNowShipPlanQty);
								TB_NowPossibleQty.setText(""+GetNowPossibleQty);
								
								TB_Qty.setText(""+GetQty);
								TB_EntryMode.setSelected(false);
								TB_PlQty.setText(""+PlQty);
								TB_CsQty.setText(""+CsQty);
								TB_CtQty.setText(""+CtQty);
								TB_BrQty.setText(""+BrQty);
								
								TB_QtyUnitname.setText(GetUnitName);
								TB_PlQtyUnitname.setText(GetPlUnitName);
								TB_CsQtyUnitname.setText(GetCsUnitName);
								TB_CtQtyUnitname.setText(GetCtUnitName);
								TB_BrQtyUnitname.setText(GetUnitName);
								
								TB_Com01.setText(GetCom01);
								TB_Com02.setText(GetCom02);
								TB_Com03.setText(GetCom03);
								TB_Com04.setText(GetCom04);
								TB_Com05.setText(GetCom05);

							}
						}
					}
					
					main_fm.setVisible(false);
					main_fm.setVisible(true);
					
					RenewFg = true;
				}
			}
		});
		
		//荷送人登録ボタン押下時の挙動
		((JButton)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getRowCount();
					for(int i01=0;i01<RowCount;i01++) {
						if((boolean)((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 0)) {
							String GetDECD				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDECD));				//届先CD
							String GetDepartmentCd		= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDepartmentCd));		//部署CD
							String GetDEName01			= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDEName01));			//届先表記名
							String GetDEName02			= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDEName02));			//届先正式名
							String GetDEName03			= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDEName03));			//届先略名
							String GetPost				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColPost));				//届先郵便
							String GetAdd01				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColAdd01));				//届先住所1
							String GetAdd02				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColAdd02));				//届先住所2
							String GetAdd03				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColAdd03));				//届先住所3
							String GetTel				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColTel));					//届先電話
							String GetFax				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColFax));					//届先FAX
							String GetMail				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColMail));				//届先MAIL
							String GetCom01				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColCom01));				//コメント1
							String GetCom02				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColCom02));				//コメント2
							String GetCom03				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColCom03));				//コメント3
							String GetPrefecturesCd		= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColPrefecturesCd));		//JIS県CD2桁
							String GetMunicipalityCd	= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColMunicipalityCd));		//JIS市区町村CD5桁
							String GetPTMSCD			= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColPTMSCD));				//基幹システム発着地コード
							String GetEntryDate			= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColEntryDate));			//データ登録日時
							String GetUpdateDate		= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColUpdateDate));			//データ更新日時
							String GetEntryUser			= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColEntryUser));			//登録者コード
							String GetUpdateUser		= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColUpdateUser));			//更新者コード
							String GetFirstClient		= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColFirstClient));		//登録した荷主CD
							String GetLastClient		= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColLastClient));			//更新した荷主CD
							String GetDelFg				= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDelFg));				//削除区分
							String GetFirstClientName	= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColFirstClientName));	//登録した荷主名
							String GetLastClientName	= B100_TextControl.Trim(""+((DefaultTableModel)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColLastClientName));	//登録した荷主名
						
							TB_NiokuriCd.setText(GetDECD);
							TB_NiokuriDepartmentCd.setText(GetDepartmentCd);
							TB_NiokuriName01.setText(GetDEName01);
							TB_NiokuriName02.setText("");
							TB_NiokuriName03.setText("");
							TB_NioKuriTel.setText(GetTel);
							TB_NioKuriFax.setText(GetFax);
							
							TB_NiokuriPost.setText(GetPost);
							TB_NiokuriAdd01.setText(GetAdd01);
							TB_NiokuriAdd02.setText(GetAdd02);
							TB_NiokuriAdd03.setText(GetAdd03);
							TB_NiokuriMunicCd.setText(GetMunicipalityCd);
							TB_NioKuriMail.setText(GetMail);
						}
					}
					((JFrame)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
					RenewFg = true;
				}
			}
		});
		
		//荷届先登録ボタン押下時の挙動
		((JButton)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getRowCount();
					for(int i01=0;i01<RowCount;i01++) {
						if((boolean)((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 0)) {
							String GetDECD				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDECD));				//届先CD
							String GetDepartmentCd		= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDepartmentCd));		//部署CD
							String GetDEName01			= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDEName01));			//届先表記名
							String GetDEName02			= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDEName02));			//届先正式名
							String GetDEName03			= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDEName03));			//届先略名
							String GetPost				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColPost));				//届先郵便
							String GetAdd01				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColAdd01));				//届先住所1
							String GetAdd02				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColAdd02));				//届先住所2
							String GetAdd03				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColAdd03));				//届先住所3
							String GetTel				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColTel));					//届先電話
							String GetFax				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColFax));					//届先FAX
							String GetMail				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColMail));				//届先MAIL
							String GetCom01				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColCom01));				//コメント1
							String GetCom02				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColCom02));				//コメント2
							String GetCom03				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColCom03));				//コメント3
							String GetPrefecturesCd		= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColPrefecturesCd));		//JIS県CD2桁
							String GetMunicipalityCd	= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColMunicipalityCd));	//JIS市区町村CD5桁
							String GetPTMSCD			= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColPTMSCD));				//基幹システム発着地コード
							String GetEntryDate			= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColEntryDate));			//データ登録日時
							String GetUpdateDate		= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColUpdateDate));		//データ更新日時
							String GetEntryUser			= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColEntryUser));			//登録者コード
							String GetUpdateUser		= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColUpdateUser));		//更新者コード
							String GetFirstClient		= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColFirstClient));		//登録した荷主CD
							String GetLastClient		= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColLastClient));		//更新した荷主CD
							String GetDelFg				= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDelFg));				//削除区分
							String GetFirstClientName	= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColFirstClientName));	//登録した荷主名
							String GetLastClientName	= B100_TextControl.Trim(""+((DefaultTableModel)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColLastClientName));	//登録した荷主名
						
							TB_DeliCd.setText(GetDECD);
							TB_DeliDepartmentCd.setText(GetDepartmentCd);
							TB_DeliName01.setText(GetDEName01);
							TB_DeliName02.setText("");
							TB_DeliName03.setText("");
							TB_DeliTel.setText(GetTel);
							TB_DeliFax.setText(GetFax);
							
							TB_DeliPost.setText(GetPost);
							TB_DeliAdd01.setText(GetAdd01);
							TB_DeliAdd02.setText(GetAdd02);
							TB_DeliAdd03.setText(GetAdd03);
							TB_DeliMunicCd.setText(GetMunicipalityCd);
							TB_DeliMail.setText(GetMail);
						}
					}
					((JFrame)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
					RenewFg = true;
				}
			}
		});
		
		//在庫検索登録ボタン押下時の挙動
		((JButton)StockSearchSubFm[WT200_StockSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getRowCount();
					int EntryHitRow = -1;
					for(int i01=0;i01<RowCount;i01++) {
						if((boolean)((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 0)) {
							String GetClCd			= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColClCd));						//荷主コード
							String GetCLName		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCLName));					//荷主表記名
							String GetWhCd			= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColWhCd));						//倉庫コード
							String GetClWHName		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColClWHName));					//担当倉庫名
							String GetClGpCD		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColClGpCD));					//荷主グループCD
							String GetClGpName		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColClGpName));					//グループ名1
							String GetLoc			= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColLoc));						//ロケーション
							String GetLocName		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColLocName));					//ロケーション名
							int GetType				= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColType));					//ロケタイプ
							String GetItemCd		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColItemCd));					//商品コード
							String GetLot			= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColLot));						//ロット
							String GetExpdate		= B100_TextControl.TextToDate(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColExpdate));			//消費期限
							String GetActualDate	= B100_TextControl.TextToDate(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColActualDate));		//入荷実績日
							int GetQty				= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColQty));					//総数量
							int GetShipPlanQty		= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColShipPlanQty));		//引当済総数
							int GetPossibleQty		= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColPossibleQty));		//出荷可能総数
							String GetItemName		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColItemName));					//商品名
							String GetItemName01	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColItemName01));				//商品表記名
							String GetItemName02	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColItemName02));				//商品正式名
							String GetItemName03	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColItemName03));				//商品略名
							String GetClItemCd		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColClItemCd));					//荷主商品コード
							String GetJanCd			= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColJanCd));						//ソースマーク_BCD（バラ）
							String GetItemMdNo		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColItemMdNo));					//商品型番
							int GetCtUnitQty		= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCtUnitQty));			//カートン入数
							int GetCsUnitQty		= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCsUnitQty));			//ケース入数
							int GetPlUnitQty		= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColPlUnitQty));			//パレット入数
							String GetUnitName		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColUnitName));					//商品単位
							String GetCtUnitName	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCtUnitName));				//カートン商品単位
							String GetCsUnitName	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCsUnitName));				//ケース商品単位
							String GetPlUnitName	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColPlUnitName));				//パレット商品単位
							String GetEntryDate		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColEntryDate));					//登録日時
							String GetUpdateDate	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColUpdateDate));				//更新日時
							String GetEntryUser		= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColEntryUser));					//登録者
							String GetUpdateUser	= B100_TextControl.Trim(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColUpdateUser));				//更新者
							int GetBrQty			= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColBrQty));				//バラ数量
							int GetBrShipPlanQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColBrShipPlanQty));		//引当済バラ数
							int GetBrPossibleQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColBrPossibleQty));		//出荷可能バラ数
							int GetCtQty			= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCtQty));				//カートン数量
							int GetCtShipPlanQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCtShipPlanQty));		//引当済カートン数
							int GetCtPossibleQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCtPossibleQty));		//出荷可能カートン数
							int GetCsQty			= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCsQty));				//ケース数量
							int GetCsShipPlanQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCsShipPlanQty));		//引当済ケース数
							int GetCsPossibleQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColCsPossibleQty));		//出荷可能ケース数
							int GetPlQty			= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColPlQty));				//パレット数量
							int GetPlShipPlanQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColPlShipPlanQty));		//引当済パレット数
							int GetPlPossibleQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)StockSearchSubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getValueAt(i01,1+T100_StockRt.ColPlPossibleQty));		//出荷可能パレット数
						
							TB_Row.setText("-1");
							TB_Loc.setText(GetLoc);
							TB_LocName.setText(GetLocName);
							TB_ItemCd.setText(GetItemCd);
							TB_ItemName.setText(GetItemName01);
							TB_Lot.setText(GetLot);
							TB_Expdate.setText(GetExpdate);
							TB_ActualDate.setText(GetActualDate);
							
							TB_PlUnitQty.setText(""+GetPlUnitQty);
							TB_CsUnitQty.setText(""+GetCsUnitQty);
							TB_CtUnitQty.setText(""+GetCtUnitQty);
							
							TB_NowQty.setText(""+GetQty);
							TB_NowShipPlanQty.setText(""+GetShipPlanQty);
							TB_NowPossibleQty.setText(""+GetPossibleQty);
							
							TB_Qty.setText("0");
							TB_EntryMode.setSelected(false);
							TB_PlQty.setText("0");
							TB_CsQty.setText("0");
							TB_CtQty.setText("0");
							TB_BrQty.setText("0");
							
							TB_QtyUnitname.setText(GetUnitName);
							TB_PlQtyUnitname.setText(GetPlUnitName);
							TB_CsQtyUnitname.setText(GetCsUnitName);
							TB_CtQtyUnitname.setText(GetCtUnitName);
							TB_BrQtyUnitname.setText(GetUnitName);
							
							TB_Com01.setText("");
							TB_Com02.setText("");
							TB_Com03.setText("");
							TB_Com04.setText("");
							TB_Com05.setText("");
							
							TB_Qty.setEditable(true);
							TB_PlQty.setEditable(false);
							TB_CsQty.setEditable(false);
							TB_CtQty.setEditable(false);
							TB_BrQty.setEditable(false);
							
							TB_Qty.setBackground(B100_FrameParts.SelectColer("Entry"));
							TB_PlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
							TB_CsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
							TB_CtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
							TB_BrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
						
							int MainRowCount = MainFmTableModel.getRowCount();
							for(int i02=0;i02<MainRowCount;i02++) {
								MainFmTableModel.setValueAt(false, i02, 0);
								String CheckLoc				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i02,ColLoc));
								String CheckItemCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i02,ColItemCd));
								String CheckLot				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i02,ColLot));
								String CheckExpdate			= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i02,ColExpdate));
								String CheckActualDate		= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i02,ColActualDate));
								
								if(CheckLoc.equals(GetLoc)
										&& CheckItemCd.equals(GetItemCd)
										&& CheckLot.equals(GetLot)
										&& CheckExpdate.equals(GetExpdate)
										&& CheckActualDate.equals(GetActualDate)
										) {
									EntryHitRow = i02;
									i02=MainRowCount+1;
								}
							}
						}
					}
					((JFrame)StockSearchSubFm[WT200_StockSearchSubFm.RtJFrame]).setVisible(false);
					RenewFg = true;
					if(0<=EntryHitRow) {
						JOptionPane.showMessageDialog(null, "既に明細登録している出荷情報と同じ在庫を選択しています。\nつまり修正ですね");
						MainFmTableModel.setValueAt(true, EntryHitRow, 0);
					}
				}
			}
		});
		
		//在庫検索ボタン押下時の挙動
		StockSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					((JFrame)StockSearchSubFm[WT200_StockSearchSubFm.RtJFrame]).setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//荷送人検索ボタン押下時の挙動
		NiokuriSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					((JFrame)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//荷届先検索ボタン押下時の挙動
		DeliSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					((JFrame)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				((JFrame)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
				((JFrame)NiokuriMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).dispose();
				
				((JFrame)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
				((JFrame)DeliMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).dispose();
				
				((JFrame)StockSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(false);
				((JFrame)StockSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_WorkMain.WorkMain(0, 0);
			}
		});
	}
	
	private static void MsEntry(Object[] EntryControlSet,DefaultTableModel MainFmTableModel) {
		int GetRow					= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_Row]).getText());
		String GetLoc				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Loc]).getText());
		String GetLocName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_LocName]).getText());
		String GetItemCd			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemCd]).getText());
		String GetItemName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemName]).getText());
		String GetLot				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Lot]).getText());
		String GetExpdate			= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_Expdate]).getText());
		String GetActualDate		= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_ActualDate]).getText());
		
		int GetPlUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_PlUnitQty]).getText());
		int GetCsUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CsUnitQty]).getText());
		int GetCtUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CtUnitQty]).getText());
		
		int GetNowQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowQty]).getText());
		int GetNowShipPlanQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowShipPlanQty]).getText());
		int GetNowPossibleQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowPossibleQty]).getText());
		
		int GetQty					= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_Qty]).getText());
		boolean GetEntryMode		= ((JCheckBox)EntryControlSet[ColTB_EntryMode]).isSelected();
		int GetPlQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_PlQty]).getText());
		int GetCsQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CsQty]).getText());
		int GetCtQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CtQty]).getText());
		int GetBrQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_BrQty]).getText());
		
		String GetQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_QtyUnitname]).getText());
		String GetPlQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_PlQtyUnitname]).getText());
		String GetCsQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CsQtyUnitname]).getText());
		String GetCtQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CtQtyUnitname]).getText());
		String GetBrQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_BrQtyUnitname]).getText());
		
		String GetCom01				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com01]).getText());
		String GetCom02				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com02]).getText());
		String GetCom03				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com03]).getText());
		String GetCom04				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com04]).getText());
		String GetCom05				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com05]).getText());
		
		Object[] SetOb = new Object[MainFmTableModelLayout().length];
		SetOb[ColSetFg] 			= true;
		SetOb[ColLoc] 				= GetLoc;
		SetOb[ColItemCd] 			= GetItemCd;
		SetOb[ColQty] 				= GetQty;
		SetOb[ColLot] 				= GetLot;
		SetOb[ColExpdate] 			= GetExpdate;
		SetOb[ColActualDate] 		= GetActualDate;
		SetOb[ColItemName] 		= GetItemName;
		SetOb[ColLocName] 			= GetLocName;
		SetOb[ColCtUnitQty] 		= GetPlUnitQty;
		SetOb[ColCsUnitQty] 		= GetCsUnitQty;
		SetOb[ColPlUnitQty] 		= GetCtUnitQty;
		SetOb[ColNowQty] 			= GetNowQty;
		SetOb[ColNowShipPlanQty] 	= GetNowShipPlanQty;
		SetOb[ColNowPossibleQty] 	= GetNowPossibleQty;
		SetOb[ColUnitName] 		= GetQtyUnitname;
		SetOb[ColCtUnitName] 		= GetCtQtyUnitname;
		SetOb[ColCsUnitName] 		= GetCsQtyUnitname;
		SetOb[ColPlUnitName] 		= GetPlQtyUnitname;
		SetOb[ColCom01] 			= GetCom01;
		SetOb[ColCom02] 			= GetCom02;
		SetOb[ColCom03] 			= GetCom03;
		SetOb[ColCom04] 			= GetCom04;
		SetOb[ColCom05] 			= GetCom05;
		
		int RowCount = MainFmTableModel.getRowCount();
		for(int i=0;i<RowCount;i++) {
			MainFmTableModel.setValueAt(false, i, 0);
		}
		if(0==GetQty) {
			if(0>GetRow) {
				JOptionPane.showMessageDialog(null, "数量ゼロの出荷なので何もしません");
			}else {
				MsDelete(EntryControlSet,MainFmTableModel);
				JOptionPane.showMessageDialog(null, "数量ゼロの出荷に変更したので行削除しました");
			}
		}else {
			if(GetNowPossibleQty<GetQty) {
				JOptionPane.showMessageDialog(null, "出荷数が出荷可能数を超えているので出せません");
			}else {
				if(0>GetRow) {
					MainFmTableModel.addRow(SetOb);
					((JTextField)EntryControlSet[ColTB_Row]).setText(""+RowCount);
				}else {
					for(int i=0;i<SetOb.length;i++) {
						MainFmTableModel.setValueAt(SetOb[i], GetRow, i);
					}
				}
			}
		}
	}
	
	private static void MsDelete(Object[] EntryControlSet,DefaultTableModel MainFmTableModel) {
		int GetRow					= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_Row]).getText());
		String GetLoc				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Loc]).getText());
		String GetLocName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_LocName]).getText());
		String GetItemCd			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemCd]).getText());
		String GetItemName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemName]).getText());
		String GetLot				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Lot]).getText());
		String GetExpdate			= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_Expdate]).getText());
		String GetActualDate		= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_ActualDate]).getText());
		
		int GetPlUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_PlUnitQty]).getText());
		int GetCsUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CsUnitQty]).getText());
		int GetCtUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CtUnitQty]).getText());
		
		int GetNowQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowQty]).getText());
		int GetNowShipPlanQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowShipPlanQty]).getText());
		int GetNowPossibleQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowPossibleQty]).getText());
		
		int GetQty					= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_Qty]).getText());
		boolean GetEntryMode		= ((JCheckBox)EntryControlSet[ColTB_EntryMode]).isSelected();
		int GetPlQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_PlQty]).getText());
		int GetCsQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CsQty]).getText());
		int GetCtQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CtQty]).getText());
		int GetBrQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_BrQty]).getText());
		
		String GetQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_QtyUnitname]).getText());
		String GetPlQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_PlQtyUnitname]).getText());
		String GetCsQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CsQtyUnitname]).getText());
		String GetCtQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CtQtyUnitname]).getText());
		String GetBrQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_BrQtyUnitname]).getText());
		
		String GetCom01				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com01]).getText());
		String GetCom02				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com02]).getText());
		String GetCom03				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com03]).getText());
		String GetCom04				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com04]).getText());
		String GetCom05				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com05]).getText());
		
		if(0>GetRow) {
			
		}else {
			MainFmTableModel.removeRow(GetRow);
		}
		EntryDefault(EntryControlSet);
	}
	private static void EntryDefault(Object[] EntryControlSet) {
		((JTextField)EntryControlSet[ColTB_Row]).setText("-1");
		((JTextField)EntryControlSet[ColTB_Loc]).setText("");
		((JTextField)EntryControlSet[ColTB_LocName]).setText("");
		((JTextField)EntryControlSet[ColTB_ItemCd]).setText("");
		((JTextField)EntryControlSet[ColTB_ItemName]).setText("");
		((JTextField)EntryControlSet[ColTB_Lot]).setText("");
		((JTextField)EntryControlSet[ColTB_Expdate]).setText("");
		((JTextField)EntryControlSet[ColTB_ActualDate]).setText("");
		
		((JTextField)EntryControlSet[ColTB_PlUnitQty]).setText("0");
		((JTextField)EntryControlSet[ColTB_CsUnitQty]).setText("0");
		((JTextField)EntryControlSet[ColTB_CtUnitQty]).setText("0");
		
		((JFormattedTextField)EntryControlSet[ColTB_NowQty]).setText("0");
		((JFormattedTextField)EntryControlSet[ColTB_NowShipPlanQty]).setText("0");
		((JFormattedTextField)EntryControlSet[ColTB_NowPossibleQty]).setText("0");
		
		((JFormattedTextField)EntryControlSet[ColTB_Qty]).setText("0");
		((JCheckBox)EntryControlSet[ColTB_EntryMode]).setSelected(false);
		((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setText("0");
		((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setText("0");
		((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setText("0");
		((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setText("0");
		
		((JLabel)EntryControlSet[ColTB_QtyUnitname]).setText("");
		((JLabel)EntryControlSet[ColTB_PlQtyUnitname]).setText("");
		((JLabel)EntryControlSet[ColTB_CsQtyUnitname]).setText("");
		((JLabel)EntryControlSet[ColTB_CtQtyUnitname]).setText("");
		((JLabel)EntryControlSet[ColTB_BrQtyUnitname]).setText("");
		
		((JTextField)EntryControlSet[ColTB_Com01]).setText("");
		((JTextField)EntryControlSet[ColTB_Com02]).setText("");
		((JTextField)EntryControlSet[ColTB_Com03]).setText("");
		((JTextField)EntryControlSet[ColTB_Com04]).setText("");
		((JTextField)EntryControlSet[ColTB_Com05]).setText("");
		
		
		((JFormattedTextField)EntryControlSet[ColTB_Qty]).setEditable(true);
		((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setEditable(false);
		((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setEditable(false);
		((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setEditable(false);
		((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setEditable(false);
		
		((JFormattedTextField)EntryControlSet[ColTB_Qty]).setBackground(B100_FrameParts.SelectColer("Entry"));
		((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
		((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
		((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
		((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
	}
	
	private static void QtyControl(Object[] EntryControlSet) {
		int GetRow					= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_Row]).getText());
		String GetLoc				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Loc]).getText());
		String GetLocName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_LocName]).getText());
		String GetItemCd			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemCd]).getText());
		String GetItemName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemName]).getText());
		String GetLot				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Lot]).getText());
		String GetExpdate			= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_Expdate]).getText());
		String GetActualDate		= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_ActualDate]).getText());
		
		int GetPlUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_PlUnitQty]).getText());
		int GetCsUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CsUnitQty]).getText());
		int GetCtUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CtUnitQty]).getText());
		
		int GetNowQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowQty]).getText());
		int GetNowShipPlanQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowShipPlanQty]).getText());
		int GetNowPossibleQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowPossibleQty]).getText());
		
		int GetQty					= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_Qty]).getText());
		boolean GetEntryMode		= ((JCheckBox)EntryControlSet[ColTB_EntryMode]).isSelected();
		int GetPlQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_PlQty]).getText());
		int GetCsQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CsQty]).getText());
		int GetCtQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CtQty]).getText());
		int GetBrQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_BrQty]).getText());
		
		String GetQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_QtyUnitname]).getText());
		String GetPlQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_PlQtyUnitname]).getText());
		String GetCsQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CsQtyUnitname]).getText());
		String GetCtQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CtQtyUnitname]).getText());
		String GetBrQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_BrQtyUnitname]).getText());
		
		String GetCom01				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com01]).getText());
		String GetCom02				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com02]).getText());
		String GetCom03				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com03]).getText());
		String GetCom04				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com04]).getText());
		String GetCom05				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com05]).getText());
		
		int BrQty = GetQty;
		int PlQty = 0;
		int CsQty = 0;
		int CtQty = 0;
		
		if(0<GetPlUnitQty) {
			PlQty	= (int)(BrQty/GetPlUnitQty);
			BrQty	= (int)(BrQty%GetPlUnitQty);
		}
		if(0<GetCsUnitQty) {
			CsQty	= (int)(BrQty/GetCsUnitQty);
			BrQty	= (int)(BrQty%GetCsUnitQty);
		}
		if(0<GetCtUnitQty) {
			CtQty	= (int)(BrQty/GetCtUnitQty);
			BrQty	= (int)(BrQty%GetCtUnitQty);
		}
		
		((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setText(""+PlQty);
		((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setText(""+CsQty);
		((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setText(""+CtQty);
		((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setText(""+BrQty);
		
	}
	
	private static void UnitQtyControl(Object[] EntryControlSet) {
		int GetRow					= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_Row]).getText());
		String GetLoc				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Loc]).getText());
		String GetLocName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_LocName]).getText());
		String GetItemCd			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemCd]).getText());
		String GetItemName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemName]).getText());
		String GetLot				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Lot]).getText());
		String GetExpdate			= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_Expdate]).getText());
		String GetActualDate		= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_ActualDate]).getText());
		
		int GetPlUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_PlUnitQty]).getText());
		int GetCsUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CsUnitQty]).getText());
		int GetCtUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CtUnitQty]).getText());
		
		int GetNowQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowQty]).getText());
		int GetNowShipPlanQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowShipPlanQty]).getText());
		int GetNowPossibleQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowPossibleQty]).getText());
		
		int GetQty					= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_Qty]).getText());
		boolean GetEntryMode		= ((JCheckBox)EntryControlSet[ColTB_EntryMode]).isSelected();
		int GetPlQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_PlQty]).getText());
		int GetCsQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CsQty]).getText());
		int GetCtQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CtQty]).getText());
		int GetBrQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_BrQty]).getText());
		
		String GetQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_QtyUnitname]).getText());
		String GetPlQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_PlQtyUnitname]).getText());
		String GetCsQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CsQtyUnitname]).getText());
		String GetCtQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CtQtyUnitname]).getText());
		String GetBrQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_BrQtyUnitname]).getText());
		
		String GetCom01				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com01]).getText());
		String GetCom02				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com02]).getText());
		String GetCom03				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com03]).getText());
		String GetCom04				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com04]).getText());
		String GetCom05				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com05]).getText());
		
		GetQty	= GetPlQty*GetPlUnitQty + GetCsQty*GetCsUnitQty + GetCtQty*GetCtUnitQty + GetBrQty;
		
		((JFormattedTextField)EntryControlSet[ColTB_Qty]).setText(""+GetQty);
		
		int BrQty = GetQty;
		int PlQty = 0;
		int CsQty = 0;
		int CtQty = 0;
		
		if(0<GetPlUnitQty) {
			PlQty	= (int)(BrQty/GetPlUnitQty);
			BrQty	= (int)(BrQty%GetPlUnitQty);
		}
		if(0<GetCsUnitQty) {
			CsQty	= (int)(BrQty/GetCsUnitQty);
			BrQty	= (int)(BrQty%GetCsUnitQty);
		}
		if(0<GetCtUnitQty) {
			CtQty	= (int)(BrQty/GetCtUnitQty);
			BrQty	= (int)(BrQty%GetCtUnitQty);
		}
		
		((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setText(""+PlQty);
		((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setText(""+CsQty);
		((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setText(""+CtQty);
		((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setText(""+BrQty);
		
	}
	
	
	
	private static void EntryModeControl(Object[] EntryControlSet) {
		int GetRow					= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_Row]).getText());
		String GetLoc				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Loc]).getText());
		String GetLocName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_LocName]).getText());
		String GetItemCd			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemCd]).getText());
		String GetItemName			= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_ItemName]).getText());
		String GetLot				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Lot]).getText());
		String GetExpdate			= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_Expdate]).getText());
		String GetActualDate		= B100_TextControl.TextToDate(((JTextField)EntryControlSet[ColTB_ActualDate]).getText());
		
		int GetPlUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_PlUnitQty]).getText());
		int GetCsUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CsUnitQty]).getText());
		int GetCtUnitQty			= B100_TextControl.TextToInt(((JTextField)EntryControlSet[ColTB_CtUnitQty]).getText());
		
		int GetNowQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowQty]).getText());
		int GetNowShipPlanQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowShipPlanQty]).getText());
		int GetNowPossibleQty		= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_NowPossibleQty]).getText());
		
		int GetQty					= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_Qty]).getText());
		boolean GetEntryMode		= ((JCheckBox)EntryControlSet[ColTB_EntryMode]).isSelected();
		int GetPlQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_PlQty]).getText());
		int GetCsQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CsQty]).getText());
		int GetCtQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_CtQty]).getText());
		int GetBrQty				= B100_TextControl.TextToInt(((JFormattedTextField)EntryControlSet[ColTB_BrQty]).getText());
		
		String GetQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_QtyUnitname]).getText());
		String GetPlQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_PlQtyUnitname]).getText());
		String GetCsQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CsQtyUnitname]).getText());
		String GetCtQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_CtQtyUnitname]).getText());
		String GetBrQtyUnitname		= B100_TextControl.Trim(((JLabel)EntryControlSet[ColTB_BrQtyUnitname]).getText());
		
		String GetCom01				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com01]).getText());
		String GetCom02				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com02]).getText());
		String GetCom03				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com03]).getText());
		String GetCom04				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com04]).getText());
		String GetCom05				= B100_TextControl.Trim(((JTextField)EntryControlSet[ColTB_Com05]).getText());
		
		if(GetEntryMode) {
			((JFormattedTextField)EntryControlSet[ColTB_Qty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			if(0<GetPlUnitQty) {((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setBackground(B100_FrameParts.SelectColer("Entry"));}else {((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));}
			if(0<GetCsUnitQty) {((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setBackground(B100_FrameParts.SelectColer("Entry"));}else {((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));}
			if(0<GetCtUnitQty) {((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setBackground(B100_FrameParts.SelectColer("Entry"));}else {((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));}
			((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setBackground(B100_FrameParts.SelectColer("Entry"));
			
			((JFormattedTextField)EntryControlSet[ColTB_Qty]).setEditable(false);
			if(0<GetPlUnitQty) {((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setEditable(true);}else {((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setEditable(false);}
			if(0<GetCsUnitQty) {((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setEditable(true);}else {((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setEditable(false);}
			if(0<GetCtUnitQty) {((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setEditable(true);}else {((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setEditable(false);}
			((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setEditable(true);
		}else {
			((JFormattedTextField)EntryControlSet[ColTB_Qty]).setBackground(B100_FrameParts.SelectColer("Entry"));
			((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
			((JFormattedTextField)EntryControlSet[ColTB_Qty]).setEditable(true);
			((JFormattedTextField)EntryControlSet[ColTB_PlQty]).setEditable(false);
			((JFormattedTextField)EntryControlSet[ColTB_CsQty]).setEditable(false);
			((JFormattedTextField)EntryControlSet[ColTB_CtQty]).setEditable(false);
			((JFormattedTextField)EntryControlSet[ColTB_BrQty]).setEditable(false);
		}
	}
	
	//在庫情報取得
	private static Object[][] StockRt(String TgtWhCd,String TgtClCd,String TgtLoc,String TgtItemCd,String TgtLot,String TgtExpDate,String TgtActualDate){
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
		ArrayList<Integer>SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
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
		boolean SortItemcdMode = false;													//商品CDでソート
		
		SearchClCd.add(TgtClCd);						//荷主コード
		SearchWhCd.add(TgtWhCd);						//倉庫コード
		SearchLoc.add(TgtLoc);							//ロケーション
		SearchItemCd.add(TgtItemCd);					//商品コード
		SearchLot.add(TgtLot);							//ロット
		SearchExpdateMin.add(TgtExpDate);				//消費期限最小
		SearchExpdateMax.add(TgtExpDate);				//消費期限最大
		SearchActualDateMin.add(TgtActualDate);			//入荷実績日最小
		SearchActualDateMax.add(TgtActualDate);			//入荷実績日最大
		
		Object[][] StockRt= T100_StockRt.StockRt(
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
	
	
	//荷主マスタ情報取得
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
		
		Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
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
	
	//強制出荷情報取得
	private static Object[][] ForceDeliGet(){
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
		boolean AllSearch = false;
		
		SearchDECD.add("0000000");
		SearchDepartmentCd.add("0000");
		
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
		return DeliveryMstRt;
	}
	
	private static Object[][] PostRt(String TgtPost){
		
		ArrayList<String> SearchPOST = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		boolean AllSearch = false;
		boolean PostPerfectMatch = false;
		
		if(null!=TgtPost && !"".equals(TgtPost)) {
			SearchPOST.add(TgtPost);
		}
		
		Object[][] PostRt = M100_PostMstRt.PostRt(
												SearchPOST,
												SearchAdd,
												AllSearch,
												PostPerfectMatch);
		return PostRt;
	}
	
	
	private static ArrayList<String> ForceEntryFix(String TgtWhCd,String TgtClCd,Object[] NiokuriEntrySet,Object[] DeliEntrySet,DefaultTableModel MainFmTableModel) {
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd	= A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd	= A00000_Main.ClCd;}
		String TgtClGp = "";
		//荷主マスタ情報取得
		Object[][] ClMstRt	= ClMstRt(TgtClCd);
		if(1==ClMstRt.length) {
			TgtClGp = (String)ClMstRt[0][M100_ClMstRt.ColClGpCD];
		}
		if(null==TgtClGp||"".equals(TgtClGp)) {TgtClGp	= A00000_Main.ClGp;}
		
		ArrayList<String> ErrMsg	= new ArrayList<String>();
		
		//荷送人情報
		String GetNiokuriCd				= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriCd]).getText());
		String GetNiokuriDepartmentCd	= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriDepartmentCd]).getText());
		String GetNiokuriName01			= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriName01]).getText());
		String GetNiokuriName02			= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriName02]).getText());
		String GetNiokuriName03			= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriName03]).getText());
		String GetNioKuriTel			= B100_TextControl.num_only_String(((JTextField)NiokuriEntrySet[ColTB_NioKuriTel]).getText());
		String GetNioKuriFax			= B100_TextControl.num_only_String(((JTextField)NiokuriEntrySet[ColTB_NioKuriFax]).getText());
		String GetNiokuriPost			= B100_TextControl.num_only_String(((JTextField)NiokuriEntrySet[ColTB_NiokuriPost]).getText());
		String GetNiokuriAdd01			= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriAdd01]).getText());
		String GetNiokuriAdd02			= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriAdd02]).getText());
		String GetNiokuriAdd03			= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NiokuriAdd03]).getText());
		String GetNiokuriMunicCd		= "";
		String GetNioKuriMail			= B100_TextControl.Trim(((JTextField)NiokuriEntrySet[ColTB_NioKuriMail]).getText());
		
		//荷届け先情報
		String GetDeliCd			= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliCd]).getText());
		String GetDeliDepartmentCd	= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliDepartmentCd]).getText());
		String GetDeliName01		= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliName01]).getText());
		String GetDeliName02		= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliName02]).getText());
		String GetDeliName03		= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliName03]).getText());
		String GetDeliTel			= B100_TextControl.num_only_String(((JTextField)DeliEntrySet[ColTB_DeliTel]).getText());
		String GetDeliFax			= B100_TextControl.num_only_String(((JTextField)DeliEntrySet[ColTB_DeliFax]).getText());
		String GetDeliPost			= B100_TextControl.num_only_String(((JTextField)DeliEntrySet[ColTB_DeliPost]).getText());
		String GetDeliAdd01			= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliAdd01]).getText());
		String GetDeliAdd02			= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliAdd02]).getText());
		String GetDeliAdd03			= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliAdd03]).getText());
		String GetDeliMunicCd		= "";
		String GetDeliMai			= B100_TextControl.Trim(((JTextField)DeliEntrySet[ColTB_DeliMail]).getText());
		
		//JIS市区町村CD特定
		Object[][]NiokuriPostRt	= PostRt(GetNiokuriPost);
		Object[][]DeliPostRt	= PostRt(GetDeliPost);
		if(1==NiokuriPostRt.length	) {GetNiokuriMunicCd	= B100_TextControl.Trim((String)NiokuriPostRt[0][M100_PostMstRt.ColMUNICIPALITY_CD]);}
		if(1==DeliPostRt.length		) {GetDeliMunicCd		= B100_TextControl.Trim((String)DeliPostRt[0][M100_PostMstRt.ColMUNICIPALITY_CD]);}
		//郵便番号で市区町村特定できなければ住所で特定、市区町村CD特定できなければ"00000"返却される
		if("".equals(GetNiokuriMunicCd)) {
			String[] AddList = {GetNiokuriAdd01+GetNiokuriAdd02+GetNiokuriAdd03};
			String[][] AddToMunicipality	= M100_PostMstRt.AddToMunicipality(AddList);
			GetNiokuriMunicCd	= AddToMunicipality[0][1];
		}
		if("".equals(GetDeliMunicCd)) {
			String[] AddList = {GetDeliAdd01+GetDeliAdd02+GetDeliAdd03};
			String[][] AddToMunicipality	= M100_PostMstRt.AddToMunicipality(AddList);
			GetDeliMunicCd	= AddToMunicipality[0][1];
		}
		
		if("".equals(GetNiokuriCd)) {
			GetNiokuriCd			= GetNiokuriMunicCd;
			GetNiokuriDepartmentCd	= "JIS";
		}

		if("".equals(GetDeliCd)) {
			GetDeliCd			= GetDeliMunicCd;
			GetDeliDepartmentCd	= "JIS";
		}
		
		//時間経過で在庫情報が変更されている可能性考慮して現在の在庫取得
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
		ArrayList<Integer>SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
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
		boolean SortItemcdMode = false;													//商品CDでソート
		
		SearchWhCd.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		
		//商品マスタ情報取得
		//ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		//ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		//ArrayList<String> SearchClItemCd 			= new ArrayList<String>();	//荷主商品コード
		//ArrayList<String> SearchItemName 			= new ArrayList<String>();	//商品名
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
		//ArrayList<String> SearchJanCd 				= new ArrayList<String>();	//JANCD
		ArrayList<String> SearchTildFG 				= new ArrayList<String>();	//温度区分
		ArrayList<String> SearchTildName 			= new ArrayList<String>();	//温度区分名
		ArrayList<String> SearchDelFg 				= new ArrayList<String>();	//削除フラグ
		//boolean AllSearch = false;
		
		int RowCount = MainFmTableModel.getRowCount();
		Object[][] StockControlSetData = new Object[RowCount][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
		
		for(int i=0;i<RowCount;i++) {
			String CheckLoc				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColLoc));
			String CheckItemCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColItemCd));
			String CheckLot				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColLot));
			String CheckExpdate			= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i,ColExpdate));
			String CheckActualDate		= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i,ColActualDate));
			int CheckQty				= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColQty));
			
			SearchLoc.add(CheckLoc);
			SearchItemCd.add(CheckItemCd);
			SearchLot.add(CheckLot);
			SearchExpdateMin.add(CheckExpdate);
			SearchExpdateMax.add(CheckExpdate);
			SearchActualDateMin.add(CheckActualDate);
			SearchActualDateMax.add(CheckActualDate);
			
			StockControlSetData[i][Tools100_StockQtyControl.ColClCd] 			= TgtClCd;
			StockControlSetData[i][Tools100_StockQtyControl.ColWhCd] 			= TgtWhCd;
			StockControlSetData[i][Tools100_StockQtyControl.ColLoc]			= CheckLoc;
			StockControlSetData[i][Tools100_StockQtyControl.ColItemCd]			= CheckItemCd;
			StockControlSetData[i][Tools100_StockQtyControl.ColLot] 			= CheckLot;
			StockControlSetData[i][Tools100_StockQtyControl.ColExpdate] 		= CheckExpdate;
			StockControlSetData[i][Tools100_StockQtyControl.ColActualDate] 	= CheckActualDate;
			StockControlSetData[i][Tools100_StockQtyControl.ColControlQty] 	= -1*CheckQty;
		}
		
		Object[][] StockRt= T100_StockRt.StockRt(
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
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
								SearchClCd,				//荷主コード
								SearchClGpCd,			//荷主グループコード
								SearchItemCd,			//商品コード
								SearchClItemCd,			//荷主商品コード
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
		
		for(int i=0;i<RowCount;i++) {
			String CheckLoc				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColLoc));
			String CheckItemCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColItemCd));
			String CheckItemName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColItemName));
			String CheckLot				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i,ColLot));
			String CheckExpdate			= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i,ColExpdate));
			String CheckActualDate		= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(i,ColActualDate));
			int CheckQty				= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColQty));
			
			boolean UnHitFg = true;
			if(0>CheckQty) {
				ErrMsg.add("ロケ:"+CheckLoc+" 商品:("+CheckItemCd+")"+CheckItemName+" ロット:"+CheckLot+" 賞味期限:"+CheckExpdate+" 入荷日:"+CheckActualDate+"の出荷数"+CheckQty+"はマイナスの為承りかねます");
			}
			
			for(int i01=0;i01<StockRt.length;i01++) {
				String GetClCd			= (String)StockRt[i01][T100_StockRt.ColClCd];				//荷主コード
				String GetCLName		= (String)StockRt[i01][T100_StockRt.ColCLName];			//荷主表記名
				String GetWhCd			= (String)StockRt[i01][T100_StockRt.ColWhCd];				//倉庫コード
				String GetClWHName		= (String)StockRt[i01][T100_StockRt.ColClWHName];			//担当倉庫名
				String GetClGpCD		= (String)StockRt[i01][T100_StockRt.ColClGpCD];			//荷主グループCD
				String GetClGpName		= (String)StockRt[i01][T100_StockRt.ColClGpName];			//グループ名1
				String GetLoc			= (String)StockRt[i01][T100_StockRt.ColLoc];				//ロケーション
				String GetLocName		= (String)StockRt[i01][T100_StockRt.ColLocName];			//ロケーション名
				int GetType				= (int)StockRt[i01][T100_StockRt.ColType];					//ロケタイプ
				String GetItemCd		= (String)StockRt[i01][T100_StockRt.ColItemCd];			//商品コード
				String GetLot			= (String)StockRt[i01][T100_StockRt.ColLot];				//ロット
				String GetExpdate		= (String)StockRt[i01][T100_StockRt.ColExpdate];			//消費期限
				String GetActualDate	= (String)StockRt[i01][T100_StockRt.ColActualDate];		//入荷実績日
				int GetQty				= (int)StockRt[i01][T100_StockRt.ColQty];					//総数量
				int GetShipPlanQty		= (int)StockRt[i01][T100_StockRt.ColShipPlanQty];			//引当済総数
				int GetPossibleQty		= (int)StockRt[i01][T100_StockRt.ColPossibleQty];			//出荷可能総数
				String GetItemName		= (String)StockRt[i01][T100_StockRt.ColItemName];			//商品名
				String GetItemName01	= (String)StockRt[i01][T100_StockRt.ColItemName01];		//商品表記名
				String GetItemName02	= (String)StockRt[i01][T100_StockRt.ColItemName02];		//商品正式名
				String GetItemName03	= (String)StockRt[i01][T100_StockRt.ColItemName03];		//商品略名
				String GetClItemCd		= (String)StockRt[i01][T100_StockRt.ColClItemCd];			//荷主商品コード
				String GetJanCd			= (String)StockRt[i01][T100_StockRt.ColJanCd];				//ソースマーク_BCD（バラ）
				String GetItemMdNo		= (String)StockRt[i01][T100_StockRt.ColItemMdNo];			//商品型番
				int GetCtUnitQty		= (int)StockRt[i01][T100_StockRt.ColCtUnitQty];			//カートン入数
				int GetCsUnitQty		= (int)StockRt[i01][T100_StockRt.ColCsUnitQty];			//ケース入数
				int GetPlUnitQty		= (int)StockRt[i01][T100_StockRt.ColPlUnitQty];			//パレット入数
				String GetUnitName		= (String)StockRt[i01][T100_StockRt.ColUnitName];			//商品単位
				String GetCtUnitName	= (String)StockRt[i01][T100_StockRt.ColCtUnitName];		//カートン商品単位
				String GetCsUnitName	= (String)StockRt[i01][T100_StockRt.ColCsUnitName];		//ケース商品単位
				String GetPlUnitName	= (String)StockRt[i01][T100_StockRt.ColPlUnitName];		//パレット商品単位
				String GetEntryDate		= (String)StockRt[i01][T100_StockRt.ColEntryDate];		//登録日時
				String GetUpdateDate	= (String)StockRt[i01][T100_StockRt.ColUpdateDate];		//更新日時
				String GetEntryUser		= (String)StockRt[i01][T100_StockRt.ColEntryUser];		//登録者
				String GetUpdateUser	= (String)StockRt[i01][T100_StockRt.ColUpdateUser];		//更新者
				int GetBrQty			= (int)StockRt[i01][T100_StockRt.ColBrQty];				//バラ数量
				int GetBrShipPlanQty	= (int)StockRt[i01][T100_StockRt.ColBrShipPlanQty];		//引当済バラ数
				int GetBrPossibleQty	= (int)StockRt[i01][T100_StockRt.ColBrPossibleQty];		//出荷可能バラ数
				int GetCtQty			= (int)StockRt[i01][T100_StockRt.ColCtQty];				//カートン数量
				int GetCtShipPlanQty	= (int)StockRt[i01][T100_StockRt.ColCtShipPlanQty];		//引当済カートン数
				int GetCtPossibleQty	= (int)StockRt[i01][T100_StockRt.ColCtPossibleQty];		//出荷可能カートン数
				int GetCsQty			= (int)StockRt[i01][T100_StockRt.ColCsQty];				//ケース数量
				int GetCsShipPlanQty	= (int)StockRt[i01][T100_StockRt.ColCsShipPlanQty];		//引当済ケース数
				int GetCsPossibleQty	= (int)StockRt[i01][T100_StockRt.ColCsPossibleQty];		//出荷可能ケース数
				int GetPlQty			= (int)StockRt[i01][T100_StockRt.ColPlQty];				//パレット数量
				int GetPlShipPlanQty	= (int)StockRt[i01][T100_StockRt.ColPlShipPlanQty];		//引当済パレット数
				int GetPlPossibleQty	= (int)StockRt[i01][T100_StockRt.ColPlPossibleQty];		//出荷可能パレット数
				
				if(CheckLoc.equals(GetLoc)
						&& CheckItemCd.equals(GetItemCd)
						&& CheckLot.equals(GetLot)
						&& CheckExpdate.equals(GetExpdate)
						&& CheckActualDate.equals(GetActualDate)
						) {
					
					UnHitFg = false;
					if(CheckQty>GetPossibleQty) {
						ErrMsg.add("ロケ:"+CheckLoc+" 商品:("+CheckItemCd+")"+CheckItemName+" ロット:"+CheckLot+" 賞味期限:"+CheckExpdate+" 入荷日:"+CheckActualDate+"の在庫が変化しているようです在庫不足で強制出荷できません");
					}
					i01=StockRt.length+1;
				}
			}
			if(UnHitFg) {
				ErrMsg.add("ロケ:"+CheckLoc+" 商品:("+CheckItemCd+")"+CheckItemName+" ロット:"+CheckLot+" 賞味期限:"+CheckExpdate+" 入荷日:"+CheckActualDate+"の在庫が変化しているようです在庫不足で強制出荷できません");
			}
			
		}
		
		//ケース数＝個数　端数があれば＋1として個口数計算 ※ケース入り数0ならバラ数＝個数
		int TotalQty 		= 0;
		float TotalSize 	= 0;
		float TotalWeight 	= 0;
		boolean FractionFg	= false;
		
		if(null!=ErrMsg&&0<ErrMsg.size()) {
			
		}else {
			//直撃で在庫を減らしてからゆっくり実績登録
			Object[][] StockQtyControlErr = Tools100_StockQtyControl.StockQtyControl(StockControlSetData) ;
			
			String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
			String now_Date = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[0];
			//送り状番号採番
			String OkuriNo	= ""+Tools100_OkuriNoGet.OkuriNoRt(1)[0];
			int EntryCount = 0;
			boolean AddErrFg = true;
			
			for(int i=0;i<StockQtyControlErr.length;i++) {
				if("EntryData".equals((String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrType])){
					EntryCount = EntryCount+1;
				}else{
					String 	ErrType = (String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrType];
					String 	ErrVol 	= (String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrVol];
					int 	ErrRow	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrRow];
					
					String CheckLoc				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(ErrRow,ColLoc));
					String CheckItemCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(ErrRow,ColItemCd));
					String CheckItemName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(ErrRow,ColItemName));
					String CheckLot				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(ErrRow,ColLot));
					String CheckExpdate			= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(ErrRow,ColExpdate));
					String CheckActualDate		= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(ErrRow,ColActualDate));
					int CheckQty				= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(ErrRow,ColQty));
					
					if(AddErrFg) {
						ErrMsg.add("***************奇跡的なタイミングで在庫更新時にエラーが発生しています。以下のデータについて登録されていませんが、これら以外は強制出庫済みになります*********************");
						AddErrFg = false;
					}
					
					ErrMsg.add("ロケ:"+CheckLoc+" 商品:("+CheckItemCd+")"+CheckItemName+" ロット:"+CheckLot+" 賞味期限:"+CheckExpdate+" 入荷日:"+CheckActualDate+"の在庫が絶妙なタイミングで変化しているようです。在庫不足で強制出荷できませんでした ");
					
				}
			}
			
			String[] Setcl_cd			= new String[EntryCount];	//荷主コード
			String[] SetInvoiceWHCD		= new String[EntryCount];	//倉庫コード
			String[] SetOkuriNo			= new String[EntryCount];	//送り状番号
			String[] SetMsNo			= new String[EntryCount];	//明細番号
			String[] SetDeliNo			= new String[EntryCount];	//出荷番号
			String[] SetDelliMsNo		= new String[EntryCount];	//出荷番号明細番号
			String[] SetClOrderNo		= new String[EntryCount];	//荷主管理番号
			String[] SetClGpCd			= new String[EntryCount];	//荷主グループコード
			String[] SetItemCd			= new String[EntryCount];	//商品コード
			String[] SetItemName01		= new String[EntryCount];	//商品表記名
			String[] SetItemName02		= new String[EntryCount];	//商品正式名
			String[] SetItemName03		= new String[EntryCount];	//商品略名
			String[] SetUnitWeight		= new String[EntryCount];	//単位重量
			String[] SetUnitSize		= new String[EntryCount];	//単位サイズ
			String[] SetQty				= new String[EntryCount];	//個数
			String[] SetPackingQty		= new String[EntryCount];	//荷姿数量
			String[] SetUnitName		= new String[EntryCount];	//明細単位
			String[] SetSubTotalWeight	= new String[EntryCount];	//明細重量
			String[] SetSubTotalSize	= new String[EntryCount];	//明細サイズ
			String[] SetUnitPrice		= new String[EntryCount];	//単価
			String[] SetSubTotalPrice	= new String[EntryCount];	//金額
			String[] SetCategoryCd		= new String[EntryCount];	//商品分類
			String[] SetCategoryName	= new String[EntryCount];	//商品分類名
			String[] SetTildFG			= new String[EntryCount];	//温度区分
			String[] SetTildName		= new String[EntryCount];	//温度区分名
			String[] SetCom01			= new String[EntryCount];	//コメント01
			String[] SetCom02			= new String[EntryCount];	//コメント02
			String[] SetCom03			= new String[EntryCount];	//コメント03
			String[] SetCom04			= new String[EntryCount];	//コメント04
			String[] SetCom05			= new String[EntryCount];	//コメント05
			String[] SetEntryDate		= new String[EntryCount];	//登録日
			String[] SetUpdateDate		= new String[EntryCount];	//更新日
			String[] SetEntryUser		= new String[EntryCount];	//登録者
			String[] SetUpdateUser		= new String[EntryCount];	//更新者
			String[] SetLot				= new String[EntryCount];	//ロット指定
			String[] SetExpDate			= new String[EntryCount];	//賞味期限指定
			String[] SetPackingType		= new String[EntryCount];	//荷姿タイプ
			String[] SetClItemCd		= new String[EntryCount];	//荷主商品CD
			String[] SetItemMDNo		= new String[EntryCount];	//型番
			String[] SetJanCd			= new String[EntryCount];	//荷姿JanCd
			
			String[] SetSpcl_cd				= new String[EntryCount];	//荷主コード
			String[] SetSpInvoiceWHCD		= new String[EntryCount];	//倉庫コード
			String[] SetSpOkuriNo			= new String[EntryCount];	//送り状番号
			String[] SetSpMsNo				= new String[EntryCount];	//明細番号
			String[] SetSpSeq				= new String[EntryCount];	//引当枝番
			String[] SetSpOrderItemCd		= new String[EntryCount];	//商品コード
			String[] SetSpOrderItemName01	= new String[EntryCount];	//商品表記名
			String[] SetSpOrderLot			= new String[EntryCount];	//受注ロット指定
			String[] SetSpOrderExpDate		= new String[EntryCount];	//受注賞味期限指定
			String[] SetSpOrderQty			= new String[EntryCount];	//受注個数
			String[] SetSpShipWhCd			= new String[EntryCount];	//倉庫コード
			String[] SetSpShipLoc			= new String[EntryCount];	//ロケーション
			String[] SetSpShipItemCd		= new String[EntryCount];	//商品コード
			String[] SetSpShipLot			= new String[EntryCount];	//ロット
			String[] SetSpShipExpdate		= new String[EntryCount];	//消費期限
			String[] SetSpShipActualDate	= new String[EntryCount];	//入荷実績日
			String[] SetSpShipQty			= new String[EntryCount];	//引当数量
			String[] SetSpFixFg				= new String[EntryCount];	//引落済フラグ
			String[] SetSpPackingType		= new String[EntryCount];	//荷姿タイプ
			String[] SetSpPackingQty		= new String[EntryCount];	//荷姿数量
			String[] SetSpUnitName			= new String[EntryCount];	//荷姿単位
			String[] SetSpPackingUnitQty	= new String[EntryCount];	//荷姿単位のバラ入数
			String[] SetSpBRShipQty			= new String[EntryCount];	//バラ数量
			String[] SetSpCTShipQty			= new String[EntryCount];	//カートン数量
			String[] SetSpCSShipQty			= new String[EntryCount];	//ケース数量
			String[] SetSpPLShipQty			= new String[EntryCount];	//パレット数量
			String[] SetSpBRUnitName		= new String[EntryCount];	//バラ単位名
			String[] SetSpCTUnitName		= new String[EntryCount];	//カートン単位名
			String[] SetSpCSUnitName		= new String[EntryCount];	//ケース単位名
			String[] SetSpPLUnitName		= new String[EntryCount];	//パレット単位名
			String[] SetSpEntryDate			= new String[EntryCount];	//登録日時
			String[] SetSpUpdateDate		= new String[EntryCount];	//更新日時
			String[] SetSpEntryUser			= new String[EntryCount];	//登録者
			String[] SetSpUpdateUser		= new String[EntryCount];	//更新者
			
			EntryCount = 0;
			for(int i=0;i<StockQtyControlErr.length;i++) {
				if("EntryData".equals((String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrType])){
					int EntryRow			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrRow];
					int BeforeQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforeQty];
					int BeforeShipPlanQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforeShipPlanQty];
					int BeforePossibleQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforePossibleQty];
					int AdjustQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAdjustQty];
					int AfterQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterQty];
					int AfterShipPlanQtyQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterShipPlanQty];
					int AfterPossibleQtyQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterPossibleQty];
					
					String CheckLoc				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColLoc));
					String CheckItemCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColItemCd));
					String CheckItemName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColItemName));
					String CheckLot				= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColLot));
					String CheckExpdate			= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(EntryRow,ColExpdate));
					String CheckActualDate		= B100_TextControl.TextToDate(""+MainFmTableModel.getValueAt(EntryRow,ColActualDate));
					int CheckQty				= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(EntryRow,ColQty));
					int CheckPlUnitQty			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(EntryRow,ColPlUnitQty));
					int CheckCsUnitQty			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(EntryRow,ColCsUnitQty));
					int CheckCtUnitQty			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(EntryRow,ColCtUnitQty));
					String CheckUnitName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColUnitName));
					String CheckCtUnitName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColCtUnitName));
					String CheckCsUnitName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColCsUnitName));
					String CheckPlUnitName		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColPlUnitName));
					String CheckCom01			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColCom01));
					String CheckCom02			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColCom02));
					String CheckCom03			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColCom03));
					String CheckCom04			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColCom04));
					String CheckCom05			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(EntryRow,ColCom05));
					
					int BrQty = CheckQty;
					int PlQty = 0;
					int CsQty = 0;
					int CtQty = 0;
					if(0<CheckPlUnitQty) {
						PlQty = (int)(BrQty/CheckPlUnitQty);
						BrQty = (int)(BrQty%CheckPlUnitQty);
					}
					if(0<CheckCsUnitQty) {
						CsQty = (int)(BrQty/CheckCsUnitQty);
						BrQty = (int)(BrQty%CheckCsUnitQty);
					}
					if(0<CheckCtUnitQty) {
						CtQty = (int)(BrQty/CheckCtUnitQty);
						BrQty = (int)(BrQty%CheckCtUnitQty);
					}
					if(0<CheckCsUnitQty) {
						TotalQty = TotalQty+(int)(CheckQty/CheckCsUnitQty);
						if(0<(int)(CheckQty%CheckCsUnitQty)) {
							FractionFg = true;
						}
					}else {
						TotalQty = TotalQty+CheckQty;
					}
					
					int MsNo = EntryCount+1;
					//消費マスタをひかずとも登録できる情報を格納
					Setcl_cd[EntryCount]				= TgtClCd;			//荷主コード
					SetInvoiceWHCD[EntryCount]			= TgtWhCd;			//倉庫コード
					SetOkuriNo[EntryCount]				= OkuriNo;			//送り状番号
					SetMsNo[EntryCount]					= ""+MsNo;			//明細番号
					SetDeliNo[EntryCount]				= "";				//出荷番号
					SetDelliMsNo[EntryCount]			= ""+MsNo;			//出荷番号明細番号
					SetClOrderNo[EntryCount]			= "";				//荷主管理番号
					SetClGpCd[EntryCount]				= TgtClGp;			//荷主グループコード
					SetItemCd[EntryCount]				= CheckItemCd;		//商品コード
					SetItemName01[EntryCount]			= CheckItemName;	//商品表記名
					SetItemName02[EntryCount]			= "";				//商品正式名
					SetItemName03[EntryCount]			= "";				//商品略名
					SetUnitWeight[EntryCount]			= "0";				//単位重量
					SetUnitSize[EntryCount]				= "0";				//単位サイズ
					SetQty[EntryCount]					= ""+CheckQty;		//個数
					SetPackingQty[EntryCount]			= ""+CheckQty;		//荷姿数量
					SetUnitName[EntryCount]				= CheckUnitName;	//明細単位
					SetSubTotalWeight[EntryCount]		= "0";				//明細重量
					SetSubTotalSize[EntryCount]			= "0";				//明細サイズ
					SetUnitPrice[EntryCount]			= "0";				//単価
					SetSubTotalPrice[EntryCount]		= "0";				//金額
					SetCategoryCd[EntryCount]			= "";				//商品分類
					SetCategoryName[EntryCount]			= "";				//商品分類名
					SetTildFG[EntryCount]				= "";				//温度区分
					SetTildName[EntryCount]				= "";				//温度区分名
					SetCom01[EntryCount]				= CheckCom01;		//コメント01
					SetCom02[EntryCount]				= CheckCom02;		//コメント02
					SetCom03[EntryCount]				= CheckCom03;		//コメント03
					SetCom04[EntryCount]				= CheckCom04;		//コメント04
					SetCom05[EntryCount]				= CheckCom05;		//コメント05
					SetEntryDate[EntryCount]			= now_dtm;			//登録日
					SetUpdateDate[EntryCount]			= now_dtm;			//更新日
					SetEntryUser[EntryCount]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
					SetUpdateUser[EntryCount]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
					SetLot[EntryCount]					= CheckLot;			//ロット指定
					SetExpDate[EntryCount]				= CheckExpdate;		//賞味期限指定
					SetPackingType[EntryCount]			= "0";				//荷姿タイプ
					SetClItemCd[EntryCount]				= "";				//荷主商品CD
					SetItemMDNo[EntryCount]				= "";				//型番
					SetJanCd[EntryCount]				= "";				//荷姿JanCd
					
					SetSpcl_cd[EntryCount]				= TgtClCd;			//荷主コード
					SetSpInvoiceWHCD[EntryCount]		= TgtWhCd;			//倉庫コード
					SetSpOkuriNo[EntryCount]			= OkuriNo;			//送り状番号
					SetSpMsNo[EntryCount]				= ""+MsNo;			//明細番号
					SetSpSeq[EntryCount]				= "0";				//引当枝番
					SetSpOrderItemCd[EntryCount]		= CheckItemCd;		//商品コード
					SetSpOrderItemName01[EntryCount]	= CheckItemName;	//商品表記名
					SetSpOrderLot[EntryCount]			= CheckLot;			//受注ロット指定
					SetSpOrderExpDate[EntryCount]		= CheckExpdate;		//受注賞味期限指定
					SetSpOrderQty[EntryCount]			= ""+CheckQty;		//受注個数
					SetSpShipWhCd[EntryCount]			= TgtWhCd;			//倉庫コード
					SetSpShipLoc[EntryCount]			= CheckLoc;			//ロケーション
					SetSpShipItemCd[EntryCount]			= CheckItemCd;		//商品コード
					SetSpShipLot[EntryCount]			= CheckLot;			//ロット
					SetSpShipExpdate[EntryCount]		= CheckExpdate;		//消費期限
					SetSpShipActualDate[EntryCount]		= CheckActualDate;	//入荷実績日
					SetSpShipQty[EntryCount]			= ""+CheckQty;		//引当数量
					SetSpFixFg[EntryCount]				= "1";				//引落済フラグ
					SetSpPackingType[EntryCount]		= "0";				//荷姿タイプ
					SetSpPackingQty[EntryCount]			= ""+CheckQty;		//荷姿数量
					SetSpUnitName[EntryCount]			= "0";				//荷姿単位
					SetSpPackingUnitQty[EntryCount]		= "1";				//荷姿単位のバラ入数
					SetSpBRShipQty[EntryCount]			= ""+BrQty;			//バラ数量
					SetSpCTShipQty[EntryCount]			= ""+CtQty;			//カートン数量
					SetSpCSShipQty[EntryCount]			= ""+CsQty;			//ケース数量
					SetSpPLShipQty[EntryCount]			= ""+PlQty;			//パレット数量
					SetSpBRUnitName[EntryCount]			= CheckUnitName;	//バラ単位名
					SetSpCTUnitName[EntryCount]			= CheckCtUnitName;	//カートン単位名
					SetSpCSUnitName[EntryCount]			= CheckCsUnitName;	//ケース単位名
					SetSpPLUnitName[EntryCount]			= CheckPlUnitName;	//パレット単位名
					SetSpEntryDate[EntryCount]			= now_dtm;			//登録日時
					SetSpUpdateDate[EntryCount]			= now_dtm;			//更新日時
					SetSpEntryUser[EntryCount]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
					SetSpUpdateUser[EntryCount]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
					
					for(int i01=0;i01<ItemMstRt.length;i01++) {
						if(CheckItemCd.equals((String)ItemMstRt[i01][M100_ItemMstRt.ColItemCd])) {
							String GetClGpCd				= (String)ItemMstRt[i01][M100_ItemMstRt.ColClGpCd];				//荷主グループコード
							String GetCLGpName01			= (String)ItemMstRt[i01][M100_ItemMstRt.ColCLGpName01];			//荷主グループ標記名
							String GetItemCd				= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemCd];				//商品コード
							String GetClItemCd				= (String)ItemMstRt[i01][M100_ItemMstRt.ColClItemCd];				//荷主商品コード
							String GetItemName01			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemName01];			//商品表記名
							String GetItemName02			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemName02];			//商品正式名
							String GetItemName03			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemName03];			//商品略名
							String GetDeliveryTypeCd01		= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeCd01];	//運送タイプコード01
							String GetDeliveryTypeName01	= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeName01];	//運送タイプ名01
							String GetDeliveryTypeCd02		= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeCd02];	//運送タイプコード02
							String GetDeliveryTypeName02	= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeName02];	//運送タイプ名02
							String GetDeliveryTypeCd03		= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeCd03];	//運送タイプコード03
							String GetDeliveryTypeName03	= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeName03];	//運送タイプ名03
							String GetDeliveryTypeCd04		= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeCd04];	//運送タイプコード04
							String GetDeliveryTypeName04	= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeName04];	//運送タイプ名04
							String GetDeliveryTypeCd05		= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeCd05];	//運送タイプコード05
							String GetDeliveryTypeName05	= (String)ItemMstRt[i01][M100_ItemMstRt.ColDeliveryTypeName05];	//運送タイプ名05
							String GetPTMSCD				= (String)ItemMstRt[i01][M100_ItemMstRt.ColPTMSCD];				//基幹システム商品コード
							int GetCtQty					= (int)ItemMstRt[i01][M100_ItemMstRt.ColCtQty];					//カートン入数
							int GetCsQty					= (int)ItemMstRt[i01][M100_ItemMstRt.ColCsQty];					//ケース入数
							int GetPlQty					= (int)ItemMstRt[i01][M100_ItemMstRt.ColPlQty];					//パレット入数
							String GetJanCd					= (String)ItemMstRt[i01][M100_ItemMstRt.ColJanCd];					//JANCD
							String GetCtJan					= (String)ItemMstRt[i01][M100_ItemMstRt.ColCtJan];					//カートンバーコード
							String GetCsJan					= (String)ItemMstRt[i01][M100_ItemMstRt.ColCsJan];					//ケースバーコード
							String GetPlJan					= (String)ItemMstRt[i01][M100_ItemMstRt.ColPlJan];					//パレットバーコード
							String GetCtName				= (String)ItemMstRt[i01][M100_ItemMstRt.ColCtName];				//カートン商品名称
							String GetCsName				= (String)ItemMstRt[i01][M100_ItemMstRt.ColCsName];				//ケース商品名称
							String GetPlName				= (String)ItemMstRt[i01][M100_ItemMstRt.ColPlName];				//パレット商品名称
							String GetUnitName				= (String)ItemMstRt[i01][M100_ItemMstRt.ColUnitName];				//商品単位
							String GetCtUnitName			= (String)ItemMstRt[i01][M100_ItemMstRt.ColCtUnitName];			//カートン商品単位
							String GetCsUnitName			= (String)ItemMstRt[i01][M100_ItemMstRt.ColCsUnitName];			//ケース商品単位
							String GetPlUnitName			= (String)ItemMstRt[i01][M100_ItemMstRt.ColPlUnitName];			//パレット商品単位
							float GetItemWeight				= (float)ItemMstRt[i01][M100_ItemMstRt.ColItemWeight];			//商品重量
							float GetCtWeight				= (float)ItemMstRt[i01][M100_ItemMstRt.ColCtWeight];				//カートン重量
							float GetCsWeight				= (float)ItemMstRt[i01][M100_ItemMstRt.ColCsWeight];				//ケース重量
							float GetPlWeight				= (float)ItemMstRt[i01][M100_ItemMstRt.ColPlWeight];				//パレット重量
							float GetItemSize				= (float)ItemMstRt[i01][M100_ItemMstRt.ColItemSize];				//商品サイズ
							float GetCtSize					= (float)ItemMstRt[i01][M100_ItemMstRt.ColCtSize];					//カートンサイズ
							float GetCsSize					= (float)ItemMstRt[i01][M100_ItemMstRt.ColCsSize];					//ケースサイズ
							float GetPlSize					= (float)ItemMstRt[i01][M100_ItemMstRt.ColPlSize];					//パレットサイズ
							String GetRecomendLoc			= (String)ItemMstRt[i01][M100_ItemMstRt.ColRecomendLoc];			//推奨ロケ
							String GetItemMDNo				= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemMDNo];				//商品モデル番号（型番）
							String GetCategoryCd			= (String)ItemMstRt[i01][M100_ItemMstRt.ColCategoryCd];			//商品カテゴリCD
							String GetCategoryName			= (String)ItemMstRt[i01][M100_ItemMstRt.ColCategoryName];			//商品カテゴリ名
							String GetItemColorCd			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemColorCd];			//商品カラーコード
							String GetItemColorName			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemColorName];		//商品カラー名
							String GetItemSizeCd			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemSizeCd];			//商品サイズコード
							String GetItemSizeName			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemSizeName];			//商品サイズ名
							String GetCom01					= (String)ItemMstRt[i01][M100_ItemMstRt.ColCom01];					//コメント1
							String GetCom02					= (String)ItemMstRt[i01][M100_ItemMstRt.ColCom02];					//コメント2
							String GetCom03					= (String)ItemMstRt[i01][M100_ItemMstRt.ColCom03];					//コメント3
							String GetTildFG				= (String)ItemMstRt[i01][M100_ItemMstRt.ColTildFG];				//温度区分
							String GetTildName				= (String)ItemMstRt[i01][M100_ItemMstRt.ColTildName];				//温度区分名
							String GetPictPass01			= (String)ItemMstRt[i01][M100_ItemMstRt.ColPictPass01];			//画像パス01
							String GetPictPass02			= (String)ItemMstRt[i01][M100_ItemMstRt.ColPictPass02];			//画像パス02
							String GetPictPass03			= (String)ItemMstRt[i01][M100_ItemMstRt.ColPictPass03];			//画像パス03
							String GetPictPass04			= (String)ItemMstRt[i01][M100_ItemMstRt.ColPictPass04];			//画像パス04
							String GetPictPass05			= (String)ItemMstRt[i01][M100_ItemMstRt.ColPictPass05];			//画像パス05
							int GetExpDateHowLong			= (int)ItemMstRt[i01][M100_ItemMstRt.ColExpDateHowLong];			//賞味期限日数
							String GetEntryDate				= (String)ItemMstRt[i01][M100_ItemMstRt.ColEntryDate];			//データ登録日時
							String GetUpdateDate			= (String)ItemMstRt[i01][M100_ItemMstRt.ColUpdateDate];			//データ更新日時
							String GetEntryUser				= (String)ItemMstRt[i01][M100_ItemMstRt.ColEntryUser];			//登録者コード
							String GetUpdateUser			= (String)ItemMstRt[i01][M100_ItemMstRt.ColUpdateUser];			//更新者コード
							int GetDelFg					= (int)ItemMstRt[i01][M100_ItemMstRt.ColDelFg];					//削除フラグ
							
							float MsWeight	= 0;
							float MsSize	= 0;
							
							if(0<GetPlWeight) {
								MsWeight = MsWeight + GetPlWeight*PlQty;
							}else {
								MsWeight = MsWeight + GetItemWeight*PlQty*CheckPlUnitQty;
							}
							if(0<GetCsWeight) {
								MsWeight = MsWeight + GetCsWeight*CsQty;
							}else {
								MsWeight = MsWeight + GetItemWeight*CsQty*CheckCsUnitQty;
							}
							if(0<GetCtWeight) {
								MsWeight = MsWeight + GetCtWeight*CtQty;
							}else {
								MsWeight = MsWeight + GetItemWeight*CtQty*CheckCtUnitQty;
							}
							MsWeight	= MsWeight + GetItemWeight*BrQty;
							
							TotalWeight = TotalWeight + MsWeight;
							
							if(0<GetPlSize) {
								MsSize = MsSize + GetPlSize*PlQty;
							}else {
								MsSize = MsSize + GetItemSize*PlQty*CheckPlUnitQty;
							}
							if(0<GetCsSize) {
								MsSize = MsSize + GetCsSize*CsQty;
							}else {
								MsSize = MsSize + GetItemSize*CsQty*CheckCsUnitQty;
							}
							if(0<GetCtSize) {
								MsSize = MsSize + GetCtSize*CtQty;
							}else {
								MsSize = MsSize + GetItemSize*CtQty*CheckCtUnitQty;
							}
							
							MsSize = MsSize + GetItemSize*BrQty;
							
							TotalSize = TotalSize + MsSize;
							
							SetItemName01[EntryCount]			= GetItemName01;	//商品表記名
							SetItemName02[EntryCount]			= GetItemName02;	//商品正式名
							SetItemName03[EntryCount]			= GetItemName03;	//商品略名
							SetUnitWeight[EntryCount]			= ""+GetItemWeight;	//単位重量
							SetUnitSize[EntryCount]				= ""+GetItemSize;	//単位サイズ
							SetSubTotalWeight[EntryCount]		= ""+MsWeight;		//明細重量
							SetSubTotalSize[EntryCount]			= ""+MsSize;		//明細サイズ
							SetCategoryCd[EntryCount]			= GetCategoryCd;	//商品分類
							SetCategoryName[EntryCount]			= GetCategoryName;	//商品分類名
							SetTildFG[EntryCount]				= GetTildFG;		//温度区分
							SetTildName[EntryCount]				= GetTildName;		//温度区分名
							SetClItemCd[EntryCount]				= GetClItemCd;		//荷主商品CD
							SetItemMDNo[EntryCount]				= GetItemMDNo;		//型番
							SetJanCd[EntryCount]				= ""+GetJanCd;		//荷姿JanCd
							SetSpOrderItemName01[EntryCount]	= GetItemName01;	//商品表記名
						}
					}
					EntryCount = EntryCount+1;
				}
			}
			
			if(FractionFg) {TotalQty	= TotalQty+1;}
			
			Object[][] HdSetOb	={
				  {"cl_cd"					,"1"	,"0"	,""		,TgtClCd							}	//荷主コード
				 ,{"InvoiceWHCD"			,"1"	,"0"	,""		,TgtWhCd							}	//倉庫コード
				 ,{"OkuriNo"				,"1"	,"0"	,"Key"	,OkuriNo							}	//送り状番号
				 ,{"ClDeliNo"				,"1"	,"0"	,""		,""									}	//荷主管理番号
				 ,{"PickupWHCD"				,"1"	,"0"	,""		,TgtWhCd							}	//集荷倉庫CD
				 ,{"PurposeFG"				,"1"	,"0"	,""		,"0"								}	//目的フラグ
				 ,{"PlanDate"				,"1"	,"0"	,""		,now_Date							}	//出荷予定日
				 ,{"ShipDate"				,"1"	,"0"	,""		,now_dtm							}	//出荷実績日
				 ,{"SPPlanDate"				,"1"	,"0"	,""		,now_Date							}	//着日指定
				 ,{"SPDate"					,"1"	,"0"	,""		,"null"								}	//着日実績
				 ,{"SPTimeFG"				,"1"	,"0"	,""		,""									}	//時間指定区分
				 ,{"SPTimeStr"				,"1"	,"0"	,""		,""									}	//時間指定開始
				 ,{"SPTimeEnd"				,"1"	,"0"	,""		,""									}	//時間指定終了
				 ,{"TotalWeight"			,"1"	,"0"	,""		,""+TotalWeight						}	//荷物重量(kg)
				 ,{"TotalSize"				,"1"	,"0"	,""		,""+TotalSize						}	//荷物サイズ
				 ,{"TotalQty"				,"1"	,"0"	,""		,""+TotalQty						}	//個口数
				 ,{"DeliveryTypeCd"			,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType01[1][0]		}	//運送タイプ01
				 ,{"DeliTypeName"			,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType01[2][0]		}	//運送タイプ名01
				 ,{"DeliveryTypeCd02"		,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType02[1][0]		}	//運送タイプ02
				 ,{"DeliTypeName02"			,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType02[2][0]		}	//運送タイプ名02
				 ,{"DeliveryTypeCd03"		,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType03[1][0]		}	//運送タイプ03
				 ,{"DeliTypeName03"			,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType03[2][0]		}	//運送タイプ名03
				 ,{"DeliveryTypeCd04"		,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType04[1][0]		}	//運送タイプ04
				 ,{"DeliTypeName04"			,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType04[2][0]		}	//運送タイプ名04
				 ,{"DeliveryTypeCd05"		,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType05[1][0]		}	//運送タイプ05
				 ,{"DeliTypeName05"			,"1"	,"0"	,""		,""+B100_DefaultVariable.DeliveryType05[2][0]		}	//運送タイプ名05
				 ,{"CodFG"					,"1"	,"0"	,""		,"0"								}	//代引フラグ
				 ,{"CodPayTotal"			,"1"	,"0"	,""		,"0"								}	//代引収受金額合計
				 ,{"CodPay"					,"1"	,"0"	,""		,"0"								}	//代引金額
				 ,{"CodConsumptionTax"		,"1"	,"0"	,""		,"0"								}	//代引消費税
				 ,{"ChildrenFG"				,"1"	,"0"	,""		,"0"								}	//赤黒区分
				 ,{"ParentOkuriNo"			,"1"	,"0"	,""		,OkuriNo							}	//親伝票番号
				 ,{"NiokuriCd"				,"1"	,"0"	,""		,GetNiokuriCd						}	//荷送り人コード
				 ,{"NiokuriDepartmentCd"	,"1"	,"0"	,""		,GetNiokuriDepartmentCd				}	//部署CD
				 ,{"NiokuriName01"			,"1"	,"0"	,""		,GetNiokuriName01					}	//荷送人名01
				 ,{"NiokuriName02"			,"1"	,"0"	,""		,GetNiokuriName02					}	//荷送人名02
				 ,{"NiokuriName03"			,"1"	,"0"	,""		,GetNiokuriName03					}	//荷送人名03
				 ,{"NiokuriPost"			,"1"	,"0"	,""		,GetNiokuriPost						}	//荷送人郵便番号
				 ,{"NiokuriAdd01"			,"1"	,"0"	,""		,GetNiokuriAdd01					}	//荷送人住所01
				 ,{"NiokuriAdd02"			,"1"	,"0"	,""		,GetNiokuriAdd02					}	//荷送人住所02
				 ,{"NiokuriAdd03"			,"1"	,"0"	,""		,GetNiokuriAdd03					}	//荷送人住所03
				 ,{"NioKuriTel"				,"1"	,"0"	,""		,GetNioKuriTel						}	//荷送人TEL
				 ,{"NioKuriFax"				,"1"	,"0"	,""		,GetNioKuriFax						}	//荷送人FAX
				 ,{"NioKuriMail"			,"1"	,"0"	,""		,GetNioKuriMail						}	//荷送人MAIL
				 ,{"NiokuriMunicCd"			,"1"	,"0"	,""		,GetNiokuriMunicCd					}	//荷送人市区町村CD
				 ,{"DeliCd"					,"1"	,"0"	,""		,GetDeliCd							}	//荷届け先コード
				 ,{"ClDeliCd"				,"1"	,"0"	,""		,""									}	//荷主荷届け先コード
				 ,{"DeliDepartmentCd"		,"1"	,"0"	,""		,GetDeliDepartmentCd				}	//部署CD
				 ,{"DeliName01"				,"1"	,"0"	,""		,GetDeliName01						}	//荷届先名01
				 ,{"DeliName02"				,"1"	,"0"	,""		,GetDeliName02						}	//荷届先名02
				 ,{"DeliName03"				,"1"	,"0"	,""		,GetDeliName03						}	//荷届先名03
				 ,{"DeliPost"				,"1"	,"0"	,""		,GetDeliPost						}	//荷届先郵便番号
				 ,{"DeliAdd01"				,"1"	,"0"	,""		,GetDeliAdd01						}	//荷届先住所01
				 ,{"DeliAdd02"				,"1"	,"0"	,""		,GetDeliAdd02						}	//荷届先住所02
				 ,{"DeliAdd03"				,"1"	,"0"	,""		,GetDeliAdd03						}	//荷届先住所03
				 ,{"DeliTel"				,"1"	,"0"	,""		,GetDeliTel							}	//荷届先TEL
				 ,{"DeliFax"				,"1"	,"0"	,""		,GetDeliFax							}	//荷届先FAX
				 ,{"DeliMail"				,"1"	,"0"	,""		,GetDeliMai							}	//荷届先MAIL
				 ,{"DeliMunicCd"			,"1"	,"0"	,""		,GetDeliMunicCd						}	//荷届先市区町村CD
				 ,{"Com01"					,"1"	,"0"	,""		,""									}	//コメント01
				 ,{"Com02"					,"1"	,"0"	,""		,""									}	//コメント02
				 ,{"Com03"					,"1"	,"0"	,""		,""									}	//コメント03
				 ,{"Com04"					,"1"	,"0"	,""		,""									}	//コメント04
				 ,{"Com05"					,"1"	,"0"	,""		,""									}	//コメント05
				 ,{"Status"					,"1"	,"0"	,""		,"0"								}	//状況
				 ,{"TaxFg"					,"1"	,"0"	,""		,"0"								}	//税区分
				 ,{"TaxRate"				,"1"	,"0"	,""		,"0"								}	//税率
				 ,{"DeliFee"				,"1"	,"0"	,""		,"0"								}	//運賃
				 ,{"AddDeliFee01"			,"1"	,"0"	,""		,"0"								}	//付帯費用1
				 ,{"AddDeliFee02"			,"1"	,"0"	,""		,"0"								}	//付帯費用2
				 ,{"AddDeliFee03"			,"1"	,"0"	,""		,"0"								}	//付帯費用3
				 ,{"HaighWayFee01"			,"1"	,"0"	,""		,"0"								}	//高速代等実費精算分1（内税）
				 ,{"HaighWayFee02"			,"1"	,"0"	,""		,"0"								}	//高速代等実費精算分2（内税）
				 ,{"ConsumptionTax"			,"1"	,"0"	,""		,"0"								}	//消費税
				 ,{"WithOutTaxTotal"		,"1"	,"0"	,""		,"0"								}	//税別合計金額
				 ,{"TotalFee"				,"1"	,"0"	,""		,"0"								}	//税込請求額合計
				 ,{"FeeFixFG"				,"1"	,"0"	,""		,"0"								}	//金額確定フラグ
				 ,{"FeeFixDate"				,"1"	,"0"	,""		,"null"								}	//金額確定日時
				 ,{"ReceiptStampFG"			,"1"	,"0"	,""		,"0"								}	//受領印チェック
				 ,{"ReceiptStampDate"		,"1"	,"0"	,""		,"null"								}	//受領印日時
				 ,{"InvoiceStatus"			,"1"	,"0"	,""		,"0"								}	//請求ステータス
				 ,{"EntryDate"				,"1"	,"0"	,""		,now_dtm							}	//登録日
				 ,{"UpdateDate"				,"1"	,"0"	,""		,now_dtm							}	//更新日
				 ,{"EntryUser"				,"1"	,"0"	,""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName		}	//登録者
				 ,{"UpdateUser"				,"1"	,"0"	,""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName		}	//更新者
				 ,{"EntryPG"				,"1"	,"0"	,""		,"WT100_Ship_20_ForceEntry"			}	//登録プログラム
				 ,{"UpdatePG"				,"1"	,"0"	,""		,"WT100_Ship_20_ForceEntry"			}	//更新プログラム
				 ,{"UseFeeBasePtCd"			,"1"	,"0"	,""		,""									}	//適用運賃タリフCD
				 ,{"WmsStatus"				,"1"	,"0"	,""		,"3"								}	//在庫管理ステータス
				 ,{"WmsShipDate"			,"1"	,"0"	,""		,now_dtm							}	//倉庫出荷日
				 ,{"CourseGpCd"				,"1"	,"0"	,""		,""									}	//コースグループコード
				 ,{"CourseCD"				,"1"	,"0"	,""		,""									}	//一次配車コースコード
				 ,{"CourseCDEda"			,"1"	,"0"	,""		,"0"								}	//一次配車コースコード枝番
				 ,{"PitGrp"					,"1"	,"0"	,""		,""									}	//一次配車払出ピットグループ
				 ,{"Pit01"					,"1"	,"0"	,""		,""									}	//一次配車払出ピット01
				 ,{"Pit02"					,"1"	,"0"	,""		,""									}	//一次配車払出ピット02
				 ,{"Pit03"					,"1"	,"0"	,""		,""									}	//一次配車払出ピット03
				 ,{"Pit04"					,"1"	,"0"	,""		,""									}	//一次配車払出ピット04
				 ,{"Pit05"					,"1"	,"0"	,""		,""									}	//一次配車払出ピット05
				};
			
			String Hd_tgt_table = "KT0010_OKURI_HD";
			String Hd_TgtDB = "NYANKO";
			int Hd_non_msg_fg = 1;
			
			A100_InsertUpdateSQL.InsertUpdateOneRecord(HdSetOb,Hd_tgt_table,Hd_TgtDB,Hd_non_msg_fg);
			
			Object[][] MsSetOb	={
				  {"cl_cd"			,"1"	,"0"	,""		,Setcl_cd			}	//荷主コード
				 ,{"InvoiceWHCD"	,"1"	,"0"	,""		,SetInvoiceWHCD		}	//倉庫コード
				 ,{"OkuriNo"		,"1"	,"0"	,"KEY"	,SetOkuriNo			}	//送り状番号
				 ,{"MsNo"			,"1"	,"0"	,"KEY"	,SetMsNo			}	//明細番号
				 ,{"DeliNo"			,"1"	,"0"	,""		,SetDeliNo			}	//出荷番号
				 ,{"DelliMsNo"		,"1"	,"0"	,""		,SetDelliMsNo		}	//出荷番号明細番号
				 ,{"ClOrderNo"		,"1"	,"0"	,""		,SetClOrderNo		}	//荷主管理番号
				 ,{"ClGpCd"			,"1"	,"0"	,""		,SetClGpCd			}	//荷主グループコード
				 ,{"ItemCd"			,"1"	,"0"	,""		,SetItemCd			}	//商品コード
				 ,{"ItemName01"		,"1"	,"0"	,""		,SetItemName01		}	//商品表記名
				 ,{"ItemName02"		,"1"	,"0"	,""		,SetItemName02		}	//商品正式名
				 ,{"ItemName03"		,"1"	,"0"	,""		,SetItemName03		}	//商品略名
				 ,{"UnitWeight"		,"1"	,"0"	,""		,SetUnitWeight		}	//単位重量
				 ,{"UnitSize"		,"1"	,"0"	,""		,SetUnitSize		}	//単位サイズ
				 ,{"Qty"			,"1"	,"0"	,""		,SetQty				}	//個数
				 ,{"PackingQty"		,"1"	,"0"	,""		,SetPackingQty		}	//荷姿数量
				 ,{"UnitName"		,"1"	,"0"	,""		,SetUnitName		}	//明細単位
				 ,{"SubTotalWeight"	,"1"	,"0"	,""		,SetSubTotalWeight	}	//明細重量
				 ,{"SubTotalSize"	,"1"	,"0"	,""		,SetSubTotalSize	}	//明細サイズ
				 ,{"UnitPrice"		,"1"	,"0"	,""		,SetUnitPrice		}	//単価
				 ,{"SubTotalPrice"	,"1"	,"0"	,""		,SetSubTotalPrice	}	//金額
				 ,{"CategoryCd"		,"1"	,"0"	,""		,SetCategoryCd		}	//商品分類
				 ,{"CategoryName"	,"1"	,"0"	,""		,SetCategoryName	}	//商品分類名
				 ,{"TildFG"			,"1"	,"0"	,""		,SetTildFG			}	//温度区分
				 ,{"TildName"		,"1"	,"0"	,""		,SetTildName		}	//温度区分名
				 ,{"Com01"			,"1"	,"0"	,""		,SetCom01			}	//コメント01
				 ,{"Com02"			,"1"	,"0"	,""		,SetCom02			}	//コメント02
				 ,{"Com03"			,"1"	,"0"	,""		,SetCom03			}	//コメント03
				 ,{"Com04"			,"1"	,"0"	,""		,SetCom04			}	//コメント04
				 ,{"Com05"			,"1"	,"0"	,""		,SetCom05			}	//コメント05
				 ,{"EntryDate"		,"1"	,"0"	,""		,SetEntryDate		}	//登録日
				 ,{"UpdateDate"		,"1"	,"0"	,""		,SetUpdateDate		}	//更新日
				 ,{"EntryUser"		,"1"	,"0"	,""		,SetEntryUser		}	//登録者
				 ,{"UpdateUser"		,"1"	,"0"	,""		,SetUpdateUser		}	//更新者
				 ,{"Lot"			,"1"	,"0"	,""		,SetLot				}	//ロット指定
				 ,{"ExpDate"		,"1"	,"0"	,""		,SetExpDate			}	//賞味期限指定
				 ,{"PackingType"	,"1"	,"0"	,""		,SetPackingType		}	//荷姿タイプ
				 ,{"ClItemCd"		,"1"	,"0"	,""		,SetClItemCd		}	//荷主商品CD
				 ,{"ItemMDNo"		,"1"	,"0"	,""		,SetItemMDNo		}	//型番
				 ,{"JanCd"			,"1"	,"0"	,""		,SetJanCd			}	//荷姿JanCd
				};
			
			String Ms_tgt_table = "KT0011_OKURI_MS";
			String Ms_TgtDB = "NYANKO";
			int Ms_non_msg_fg = 1;
			
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(MsSetOb,Ms_tgt_table,Ms_TgtDB,Ms_non_msg_fg);
			
			Object[][] SpSetOb	={
				 {"cl_cd"			,"1"	,"0"	,"KEY"	,SetSpcl_cd				}	//荷主コード
				,{"InvoiceWHCD"		,"1"	,"0"	,"KEY"	,SetSpInvoiceWHCD		}	//倉庫コード
				,{"OkuriNo"			,"1"	,"0"	,"KEY"	,SetSpOkuriNo			}	//送り状番号
				,{"MsNo"			,"1"	,"0"	,"KEY"	,SetSpMsNo				}	//明細番号
				,{"Seq"				,"1"	,"0"	,"KEY"	,SetSpSeq				}	//引当枝番
				,{"OrderItemCd"		,"1"	,"0"	,""		,SetSpOrderItemCd		}	//商品コード
				,{"OrderItemName01"	,"1"	,"0"	,""		,SetSpOrderItemName01	}	//商品表記名
				,{"OrderLot"		,"1"	,"0"	,""		,SetSpOrderLot			}	//受注ロット指定
				,{"OrderExpDate"	,"1"	,"0"	,""		,SetSpOrderExpDate		}	//受注賞味期限指定
				,{"OrderQty"		,"1"	,"0"	,""		,SetSpOrderQty			}	//受注個数
				,{"ShipWhCd"		,"1"	,"0"	,""		,SetSpShipWhCd			}	//倉庫コード
				,{"ShipLoc"			,"1"	,"0"	,""		,SetSpShipLoc			}	//ロケーション
				,{"ShipItemCd"		,"1"	,"0"	,""		,SetSpShipItemCd		}	//商品コード
				,{"ShipLot"			,"1"	,"0"	,""		,SetSpShipLot			}	//ロット
				,{"ShipExpdate"		,"1"	,"0"	,""		,SetSpShipExpdate		}	//消費期限
				,{"ShipActualDate"	,"1"	,"0"	,""		,SetSpShipActualDate	}	//入荷実績日
				,{"ShipQty"			,"1"	,"0"	,""		,SetSpShipQty			}	//引当数量
				,{"FixFg"			,"1"	,"0"	,""		,SetSpFixFg				}	//引落済フラグ
				,{"PackingType"		,"1"	,"0"	,""		,SetSpPackingType		}	//荷姿タイプ
				,{"PackingQty"		,"1"	,"0"	,""		,SetSpPackingQty		}	//荷姿数量
				,{"UnitName"		,"1"	,"0"	,""		,SetSpUnitName			}	//荷姿単位
				,{"PackingUnitQty"	,"1"	,"0"	,""		,SetSpPackingUnitQty	}	//荷姿単位のバラ入数
				,{"BRShipQty"		,"1"	,"0"	,""		,SetSpBRShipQty			}	//バラ数量
				,{"CTShipQty"		,"1"	,"0"	,""		,SetSpCTShipQty			}	//カートン数量
				,{"CSShipQty"		,"1"	,"0"	,""		,SetSpCSShipQty			}	//ケース数量
				,{"PLShipQty"		,"1"	,"0"	,""		,SetSpPLShipQty			}	//パレット数量
				,{"BRUnitName"		,"1"	,"0"	,""		,SetSpBRUnitName		}	//バラ単位名
				,{"CTUnitName"		,"1"	,"0"	,""		,SetSpCTUnitName		}	//カートン単位名
				,{"CSUnitName"		,"1"	,"0"	,""		,SetSpCSUnitName		}	//ケース単位名
				,{"PLUnitName"		,"1"	,"0"	,""		,SetSpPLUnitName		}	//パレット単位名
				,{"EntryDate"		,"1"	,"0"	,""		,SetSpEntryDate			}	//登録日時
				,{"UpdateDate"		,"1"	,"0"	,""		,SetSpUpdateDate		}	//更新日時
				,{"EntryUser"		,"1"	,"0"	,""		,SetSpEntryUser			}	//登録者
				,{"UpdateUser"		,"1"	,"0"	,""		,SetSpUpdateUser		}	//更新者
				};
			
			String Sp_tgt_table = "WW0020ShipPlovision";
			String Sp_TgtDB = "WANKO";
			int Sp_non_msg_fg = 1;
			
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(SpSetOb,Sp_tgt_table,Sp_TgtDB,Sp_non_msg_fg);
		}
		
		return ErrMsg;

	}
	
	private static void ErrView(ArrayList<String>ErrMsg){
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\ShipControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipControl\\ShipForceEntry";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipControl\\ShipForceEntry\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipControl\\ShipForceEntry\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ShipControl\\ShipForceEntry\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B100_TextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B100_FolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B100_DefaultVariable.ErrTxtDelete);
		
		//ファイル開く
		File file = new File(ErrFP);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}