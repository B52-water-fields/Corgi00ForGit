import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WT0001020StockAdjust{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void StockAdjust(int x,int y,final String HandoverWhCd,final String HandoverClCd,final String HandoverLoc,final String HandoverItemCd,final String HandoverLot,final String HandoverExpdate,final String HandoverActualDate) {
		A00000Main.LoginCheck();//
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		String TgtWhCd		= HandoverWhCd;
		String TgtClCd		= HandoverClCd;
		String TgtLoc		= HandoverLoc;
		String TgtItemCd	= HandoverItemCd;
		String TgtLot		= HandoverLot;
		String TgtExpdate	= HandoverExpdate;
		String TgtActualDate= HandoverActualDate;
		
		
		/******************************************
			現在の在庫情報を取得する
		******************************************/
		
		if(null==TgtWhCd		) {TgtWhCd			="";}
		if(null==TgtClCd		) {TgtClCd			="";}
		if(null==TgtLoc			) {TgtLoc			="";}
		if(null==TgtItemCd		) {TgtItemCd		="";}
		if(null==TgtLot			) {TgtLot			="";}
		if(null==TgtExpdate		) {TgtExpdate		="";}
		if(null==TgtActualDate	) {TgtActualDate	="";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	=A00000Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	=A00000Main.ClCd;}
		
		String GetClCd			= TgtClCd;				//荷主コード
		String GetCLName		= "";					//荷主名1
		String GetWhCd			= TgtWhCd;				//倉庫コード
		String GetClWHName		= "";					//担当倉庫名
		String GetClGpCD		= "";					//荷主グループCD
		String GetClGpName		= "";					//グループ名1
		String GetLoc			= TgtLoc;				//ロケーション
		String GetLocName		= "";					//ロケーション名
		int GetType				= 0;					//ロケタイプ
		String GetItemCd		= TgtItemCd;			//商品コード
		String GetLot			= TgtLot;				//ロット
		String GetExpdate		= TgtExpdate;			//消費期限
		String GetActualDate	= TgtActualDate;		//入荷実績日
		int GetQty				= 0;					//総数量
		int GetShipPlanQty		= 0;					//引当済総数
		int GetPossibleQty		= 0;					//出荷可能総数
		String GetItemName		= "";					//商品名
		String GetItemName01	= "";					//商品名1
		String GetItemName02	= "";					//商品名2
		String GetItemName03	= "";					//商品名3
		String GetClItemCd		= "";					//荷主商品コード
		String GetJanCd			= "";					//ソースマーク_BCD（バラ）
		String GetItemMdNo		= "";					//商品型番
		int GetCtUnitQty		= 0;					//カートン入数
		int GetCsUnitQty		= 0;					//ケース入数
		int GetPlUnitQty		= 0;					//パレット入数
		String GetUnitName		= "";					//商品単位
		String GetCtUnitName	= "";					//カートン商品単位
		String GetCsUnitName	= "";					//ケース商品単位
		String GetPlUnitName	= "";					//パレット商品単位
		String GetEntryDate		= "";					//登録日時
		String GetUpdateDate	= "";					//更新日時
		String GetEntryUser		= "";					//登録者
		String GetUpdateUser	= "";					//更新者
		int GetBrQty			= 0;					//バラ数量
		int GetBrShipPlanQty	= 0;					//引当済バラ数
		int GetBrPossibleQty	= 0;					//出荷可能バラ数
		int GetCtQty			= 0;					//カートン数量
		int GetCtShipPlanQty	= 0;					//引当済カートン数
		int GetCtPossibleQty	= 0;					//出荷可能カートン数
		int GetCsQty			= 0;					//ケース数量
		int GetCsShipPlanQty	= 0;					//引当済ケース数
		int GetCsPossibleQty	= 0;					//出荷可能ケース数
		int GetPlQty			= 0;					//パレット数量
		int GetPlShipPlanQty	= 0;					//引当済パレット数
		int GetPlPossibleQty	= 0;					//出荷可能パレット数
		
		boolean MstCheckFg = true;
		
		if(!"".equals(TgtWhCd)
				&&!"".equals(TgtClCd)
				&&!"".equals(TgtLoc)
				&&!"".equals(TgtItemCd)
				&&!"".equals(TgtLot)
				&&!"".equals(TgtExpdate)
				&&!"".equals(TgtActualDate)
				) {
			
			Object[][] StockRt= StockRt(
										TgtWhCd,
										TgtClCd,
										TgtLoc,
										TgtItemCd,
										TgtLot,
										TgtExpdate,
										TgtActualDate
										);
			if(1==StockRt.length) {
				GetClCd				= (String)StockRt[0][T00030StockRt.ColClCd];			//荷主コード
				GetCLName			= (String)StockRt[0][T00030StockRt.ColCLName];			//荷主名1
				GetWhCd				= (String)StockRt[0][T00030StockRt.ColWhCd];			//倉庫コード
				GetClWHName			= (String)StockRt[0][T00030StockRt.ColClWHName];		//担当倉庫名
				GetClGpCD			= (String)StockRt[0][T00030StockRt.ColClGpCD];			//荷主グループCD
				GetClGpName			= (String)StockRt[0][T00030StockRt.ColClGpName];		//グループ名1
				GetLoc				= (String)StockRt[0][T00030StockRt.ColLoc];			//ロケーション
				GetLocName			= (String)StockRt[0][T00030StockRt.ColLocName];		//ロケーション名
				GetType				= (int)StockRt[0][T00030StockRt.ColType];				//ロケタイプ
				GetItemCd			= (String)StockRt[0][T00030StockRt.ColItemCd];			//商品コード
				GetLot				= (String)StockRt[0][T00030StockRt.ColLot];			//ロット
				GetExpdate			= (String)StockRt[0][T00030StockRt.ColExpdate];		//消費期限
				GetActualDate		= (String)StockRt[0][T00030StockRt.ColActualDate];	//入荷実績日
				GetQty				= (int)StockRt[0][T00030StockRt.ColQty];				//総数量
				GetShipPlanQty		= (int)StockRt[0][T00030StockRt.ColShipPlanQty];		//引当済総数
				GetPossibleQty		= (int)StockRt[0][T00030StockRt.ColPossibleQty];		//出荷可能総数
				GetItemName			= (String)StockRt[0][T00030StockRt.ColItemName];		//商品名
				GetItemName01		= (String)StockRt[0][T00030StockRt.ColItemName01];	//商品名1
				GetItemName02		= (String)StockRt[0][T00030StockRt.ColItemName02];	//商品名2
				GetItemName03		= (String)StockRt[0][T00030StockRt.ColItemName03];	//商品名3
				GetClItemCd			= (String)StockRt[0][T00030StockRt.ColClItemCd];		//荷主商品コード
				GetJanCd			= (String)StockRt[0][T00030StockRt.ColJanCd];			//ソースマーク_BCD（バラ）
				GetItemMdNo			= (String)StockRt[0][T00030StockRt.ColItemMdNo];		//商品型番
				GetCtUnitQty		= (int)StockRt[0][T00030StockRt.ColCtUnitQty];		//カートン入数
				GetCsUnitQty		= (int)StockRt[0][T00030StockRt.ColCsUnitQty];		//ケース入数
				GetPlUnitQty		= (int)StockRt[0][T00030StockRt.ColPlUnitQty];		//パレット入数
				GetUnitName			= (String)StockRt[0][T00030StockRt.ColUnitName];		//商品単位
				GetCtUnitName		= (String)StockRt[0][T00030StockRt.ColCtUnitName];	//カートン商品単位
				GetCsUnitName		= (String)StockRt[0][T00030StockRt.ColCsUnitName];	//ケース商品単位
				GetPlUnitName		= (String)StockRt[0][T00030StockRt.ColPlUnitName];	//パレット商品単位
				GetEntryDate		= (String)StockRt[0][T00030StockRt.ColEntryDate];		//登録日時
				GetUpdateDate		= (String)StockRt[0][T00030StockRt.ColUpdateDate];	//更新日時
				GetEntryUser		= (String)StockRt[0][T00030StockRt.ColEntryUser];		//登録者
				GetUpdateUser		= (String)StockRt[0][T00030StockRt.ColUpdateUser];	//更新者
				GetBrQty			= (int)StockRt[0][T00030StockRt.ColBrQty];				//バラ数量
				GetBrShipPlanQty	= (int)StockRt[0][T00030StockRt.ColBrShipPlanQty];	//引当済バラ数
				GetBrPossibleQty	= (int)StockRt[0][T00030StockRt.ColBrPossibleQty];	//出荷可能バラ数
				GetCtQty			= (int)StockRt[0][T00030StockRt.ColCtQty];				//カートン数量
				GetCtShipPlanQty	= (int)StockRt[0][T00030StockRt.ColCtShipPlanQty];	//引当済カートン数
				GetCtPossibleQty	= (int)StockRt[0][T00030StockRt.ColCtPossibleQty];	//出荷可能カートン数
				GetCsQty			= (int)StockRt[0][T00030StockRt.ColCsQty];				//ケース数量
				GetCsShipPlanQty	= (int)StockRt[0][T00030StockRt.ColCsShipPlanQty];	//引当済ケース数
				GetCsPossibleQty	= (int)StockRt[0][T00030StockRt.ColCsPossibleQty];	//出荷可能ケース数
				GetPlQty			= (int)StockRt[0][T00030StockRt.ColPlQty];				//パレット数量
				GetPlShipPlanQty	= (int)StockRt[0][T00030StockRt.ColPlShipPlanQty];	//引当済パレット数
				GetPlPossibleQty	= (int)StockRt[0][T00030StockRt.ColPlPossibleQty];	//出荷可能パレット数
				
				MstCheckFg = false;
			}
		}
		if(MstCheckFg) {
			//在庫検索結果で対象特定されていなければ　マスタ情報を引っ張る
			
			//荷主コードを元に荷主情報取得
			Object[][] ClMstRt = ClMstRt(GetClCd);
			if(1==ClMstRt.length) {
				GetClCd			=(String)ClMstRt[0][M00011ClMstRt.Colcl_cd];		//荷主CD
				GetCLName		=(String)ClMstRt[0][M00011ClMstRt.ColCLName01];	//荷主名1
				GetClGpCD		=(String)ClMstRt[0][M00011ClMstRt.ColClGpCD];		//荷主グループCD
				GetClGpName		=(String)ClMstRt[0][M00011ClMstRt.ColClGpName];	//グループ名1
			}
			
			//倉庫マスタ
			Object[][] WhMstRt = WhMstRt(GetWhCd);
			if(1==WhMstRt.length){
				GetWhCd				= (String)WhMstRt[0][M00001WhMstRt.ColNoWHCD];			//倉庫コード
				GetClWHName			= (String)WhMstRt[0][M00001WhMstRt.ColNoWHName];		//担当倉庫名
			}
			
			//商品マスタ
			Object[][] ItemMstRt = ItemMstRt(GetClGpCD,GetItemCd);
			if(1==ItemMstRt.length) {
				GetItemCd			= (String)ItemMstRt[0][M00070ItemMstRt.ColItemCd];			//商品コード
				GetItemName			= (String)ItemMstRt[0][M00070ItemMstRt.ColItemName01];	//商品名
				GetItemName01		= (String)ItemMstRt[0][M00070ItemMstRt.ColItemName01];	//商品名1
				GetItemName02		= (String)ItemMstRt[0][M00070ItemMstRt.ColItemName02];	//商品名2
				GetItemName03		= (String)ItemMstRt[0][M00070ItemMstRt.ColItemName03];	//商品名3
				GetClItemCd			= (String)ItemMstRt[0][M00070ItemMstRt.ColCLItemCd];		//荷主商品コード
				GetJanCd			= (String)ItemMstRt[0][M00070ItemMstRt.ColJanCd];			//ソースマーク_BCD（バラ）
				GetItemMdNo			= (String)ItemMstRt[0][M00070ItemMstRt.ColItemMDNo];		//商品型番
				GetCtUnitQty		= (int)ItemMstRt[0][M00070ItemMstRt.ColCtQty];				//カートン入数
				GetCsUnitQty		= (int)ItemMstRt[0][M00070ItemMstRt.ColCsQty];				//ケース入数
				GetPlUnitQty		= (int)ItemMstRt[0][M00070ItemMstRt.ColPlQty];				//パレット入数
				GetUnitName			= (String)ItemMstRt[0][M00070ItemMstRt.ColUnitName];		//商品単位
				GetCtUnitName		= (String)ItemMstRt[0][M00070ItemMstRt.ColCtUnitName];	//カートン商品単位
				GetCsUnitName		= (String)ItemMstRt[0][M00070ItemMstRt.ColCsUnitName];	//ケース商品単位
				GetPlUnitName		= (String)ItemMstRt[0][M00070ItemMstRt.ColPlUnitName];	//パレット商品単位
			}
			
			//ロケーションマスタ
			Object[][] LocationMstRt = LocationMstRt(GetClCd,GetWhCd,GetLoc);
			if(1==LocationMstRt.length) {
				GetLoc				= (String)LocationMstRt[0][M00090LocationMstRt.ColLoc];			//ロケーション
				GetLocName			= (String)LocationMstRt[0][M00090LocationMstRt.ColLocName];		//ロケーション名
				GetType				= (int)LocationMstRt[0][M00090LocationMstRt.ColType];			//ロケタイプ
			}
		}
		
		//入荷実績日空白で、入荷日管理しない場合デフォルト入荷実績日セット
		if(B00100DefaultVariable.ActualDateUnControl && "".equals(GetActualDate)) {
			GetActualDate = B00100DefaultVariable.DefaultActualDate;
		}
		//賞味期限空白の場合、一旦デフォルト賞味期限セット
		if("".equals(GetExpdate)) {
			GetExpdate = B00100DefaultVariable.DefaultExpDate;
		}
		
		/******************************************
			現在の在庫情報を取得するここまで
		******************************************/
		
		final JFrame main_fm 	= B00110FrameParts.FrameCreate(x,y,800,700,"Corgi00在庫調整","ZK");
		JLabel userinfo 		= B00110FrameParts.UserInfo();
		JButton exit_btn 		= B00110FrameParts.ExitBtn();
		JButton entry_btn 		= B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		final NumberFormat ni = NumberFormat.getNumberInstance();

		JLabel LB_ClCd			= B00110FrameParts.JLabelSet(	  0, 50,150,20,"荷主:"				,11,1);
		JLabel LB_WhCd			= B00110FrameParts.JLabelSet(	  0, 75,150,20,"倉庫:"				,11,1);
		
		JLabel LB_Loc				= B00110FrameParts.JLabelSet(	  0,100,150,20,"ロケーション:"		,11,1);
		JLabel LB_LocName			= B00110FrameParts.JLabelSet(	  0,125,150,20,"ロケーション名:"	,11,1);
		JLabel LB_LocType			= B00110FrameParts.JLabelSet(	  0,150,150,20,"ロケータイプ:"		,11,1);
		JLabel LB_ItemCd			= B00110FrameParts.JLabelSet(	  0,175,150,20,"商品コード:"		,11,1);
		JLabel LB_ItemName			= B00110FrameParts.JLabelSet(	  0,200,150,20,"商品名:"			,11,1);
		JLabel LB_Lot				= B00110FrameParts.JLabelSet(	  0,225,150,20,"ロット:"			,11,1);
		JLabel LB_Expdate			= B00110FrameParts.JLabelSet(	  0,250,150,20,"消費期限:"			,11,1);
		JLabel LB_ActualDate		= B00110FrameParts.JLabelSet(	  0,275,150,20,"入荷実績日:"		,11,1);
		
		JLabel LB_CtUnitQty			= B00110FrameParts.JLabelSet(		250,250,150,20,"カートン入数(バラ換算):"	,11,1);
		JLabel LB_CsUnitQty			= B00110FrameParts.JLabelSet(		250,275,150,20,"ケース入数(バラ換算):"		,11,1);
		JLabel LB_PlUnitQty			= B00110FrameParts.JLabelSet(		250,300,150,20,"パレット入数(バラ換算):"	,11,1);
		
		final JFormattedTextField TB_CtUnitQty		= B00110FrameParts.JFormattedTextFieldSet(400,250,70,20,""	+ni.format(GetCtUnitQty)		,11,1,"#,###");
		final JFormattedTextField TB_CsUnitQty		= B00110FrameParts.JFormattedTextFieldSet(400,275,70,20,""	+ni.format(GetCsUnitQty)		,11,1,"#,###");
		final JFormattedTextField TB_PlUnitQty		= B00110FrameParts.JFormattedTextFieldSet(400,300,70,20,""	+ni.format(GetPlUnitQty)		,11,1,"#,###");
		
		JLabel LB_Qty				= B00110FrameParts.JLabelSet(	  0,450,150,20,"調整前数量:"		,11,1);
		JLabel LB_ShipPlanQty		= B00110FrameParts.JLabelSet(	  0,475,150,20,"調整前引当済数:"	,11,1);
		JLabel LB_PossibleQty		= B00110FrameParts.JLabelSet(	  0,500,150,20,"調整前出荷可能数:"	,11,1);
		
		JLabel LB_Msg				= B00110FrameParts.JLabelSet(	270,475, 70,20,"⇒⇒⇒"				,11,2);
		
		JLabel LB_AdjustQty			= B00110FrameParts.JLabelSet(	  0,325,150,20,"バラ換算調整数:"	,11,1);
		JLabel LB_AdjustReason 		= B00110FrameParts.JLabelSet(	380,325,100,20,"調整理由:"			,11,1);
		
		JLabel LB_PlAdjustQty		= B00110FrameParts.JLabelSet(	130,350,100,20,"パレット:"			,11,1);
		JLabel LB_CsAdjustQty		= B00110FrameParts.JLabelSet(	130,375,100,20,"ケース:"			,11,1);
		JLabel LB_CtAdjustQty		= B00110FrameParts.JLabelSet(	130,400,100,20,"カートン:"			,11,1);
		JLabel LB_BrAdjustQty		= B00110FrameParts.JLabelSet(	130,425,100,20,"バラ:"				,11,1);
		
		JLabel LB_PlAdjustUnitName	= B00110FrameParts.JLabelSet(	300,350,100,20,GetPlUnitName		,11,0);
		JLabel LB_CsAdjustUnitName	= B00110FrameParts.JLabelSet(	300,375,100,20,GetCsUnitName		,11,0);
		JLabel LB_CtAdjustUnitName	= B00110FrameParts.JLabelSet(	300,400,100,20,GetCtUnitName		,11,0);
		JLabel LB_BrAdjustUnitName	= B00110FrameParts.JLabelSet(	300,425,100,20,GetUnitName			,11,0);
		
		
		JLabel LB_AfterQty			= B00110FrameParts.JLabelSet(	320,450,150,20,"調整後数量:"		,11,1);
		JLabel LB_AfterShipPlanQty	= B00110FrameParts.JLabelSet(	320,475,150,20,"調整後引当済数:"	,11,1);
		JLabel LB_AfterPossibleQty	= B00110FrameParts.JLabelSet(	320,500,150,20,"調整後出荷可能数:"	,11,1);
		
		final JComboBox TB_ClCd		= B00110FrameParts.JComboBoxSet(	150, 50,200,20,B00100DefaultVariable.ClList[0],11);	//荷主コード
		final JComboBox TB_WhCd		= B00110FrameParts.JComboBoxSet(	150, 75,200,20,B00100DefaultVariable.WhList[0],11);	//倉庫コード
		
		final JTextField TB_Loc		= B00110FrameParts.JTextFieldSet(	150,100,200,20,GetLoc,11,0);							//ロケーション
		final JTextField TB_LocName	= B00110FrameParts.JTextFieldSet(	150,125,200,20,GetLocName,11,0);						//ロケーション名
		final JComboBox TB_LocType	= B00110FrameParts.JComboBoxSet(	150,150,200,20,B00100DefaultVariable.LocType[0],11);	//ロケータイプ
		final JTextField TB_ItemCd	= B00110FrameParts.JTextFieldSet(	150,175,200,20,GetItemCd,11,0);							//商品コード
		final JTextField TB_ItemName= B00110FrameParts.JTextFieldSet(	150,200,200,20,GetItemName01,11,0);						//商品名
		final JTextField TB_Lot		= B00110FrameParts.JTextFieldSet(	150,225,200,20,GetLot,11,0);							//ロット
		final JFormattedTextField TB_Expdate			= B00110FrameParts.JFormattedTextFieldSet(	150,250, 70,20,GetExpdate,11,0,"YYYY/MM/DD");				//消費期限
		final JFormattedTextField TB_ActualDate			= B00110FrameParts.JFormattedTextFieldSet(	150,275, 70,20,GetActualDate,11,0,"YYYY/MM/DD");			//入荷実績日
		
		final JFormattedTextField TB_Qty				= B00110FrameParts.JFormattedTextFieldSet(	150,450, 70,20,""+ni.format(GetQty),11,1,"#,###");			//調整前数量
		final JFormattedTextField TB_ShipPlanQty		= B00110FrameParts.JFormattedTextFieldSet(	150,475, 70,20,""+ni.format(GetShipPlanQty),11,1,"#,###");	//調整前引当済数
		final JFormattedTextField TB_PossibleQty		= B00110FrameParts.JFormattedTextFieldSet(	150,500, 70,20,""+ni.format(GetPossibleQty),11,1,"#,###");	
		
		final JFormattedTextField TB_AdjustQty			= B00110FrameParts.JFormattedTextFieldSet(	150,325, 70,20,"0",11,1,"#,###");							//調整数
		final JCheckBox TB_EntryMode 					= B00110FrameParts.JCheckBoxSet(				230,325,150,20,"荷姿別で調整",11);
		final JComboBox TB_AdjustReason 				= B00110FrameParts.JComboBoxSet(				480,325,200,20,B00100DefaultVariable.AdjustReasonList[0],11);	//調整理由
		final JFormattedTextField TB_PlAdjustQty		= B00110FrameParts.JFormattedTextFieldSet(	230,350, 70,20,"0",11,1,"#,###");
		final JFormattedTextField TB_CsAdjustQty		= B00110FrameParts.JFormattedTextFieldSet(	230,375, 70,20,"0",11,1,"#,###");
		final JFormattedTextField TB_CtAdjustQty		= B00110FrameParts.JFormattedTextFieldSet(	230,400, 70,20,"0",11,1,"#,###");
		final JFormattedTextField TB_BrAdjustQty		= B00110FrameParts.JFormattedTextFieldSet(	230,425, 70,20,"0",11,1,"#,###");
		
		final JFormattedTextField TB_AfterQty			= B00110FrameParts.JFormattedTextFieldSet(	470,450, 70,20,""+ni.format(GetQty)			,11,1,"#,###");				//調整後数量
		final JFormattedTextField TB_AfterShipPlanQty	= B00110FrameParts.JFormattedTextFieldSet(	470,475, 70,20,""+ni.format(GetShipPlanQty)	,11,1,"#,###");				//調整後引当済数
		final JFormattedTextField TB_AfterPossibleQty	= B00110FrameParts.JFormattedTextFieldSet(	470,500, 70,20,""+ni.format(GetPossibleQty)	,11,1,"#,###");				//調整後出荷可能数
		
		TB_ClCd.setSelectedIndex(	GetSelectIndex(B00100DefaultVariable.ClList[1]		,GetClCd ) );		//荷主コード
		TB_WhCd.setSelectedIndex(	GetSelectIndex(B00100DefaultVariable.ClList[1]		,GetWhCd ) );		//倉庫コード
		TB_LocType.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.LocType[1]		,GetType+"" ) );	//ロケタイプ
		
		TB_ClCd.setEnabled(false);		//荷主コード
		TB_WhCd.setEnabled(false);		//倉庫コード
		TB_LocType.setEnabled(false);
		TB_LocName.setEditable(false);
		TB_ItemName.setEditable(false);
		
		if(!MstCheckFg) {TB_Lot.setEditable(false);}
		if(!MstCheckFg) {TB_Expdate.setEditable(false);}
		if(!"".equals(GetActualDate)) {TB_ActualDate.setEditable(false);}
		TB_Qty.setEditable(false);
		TB_ShipPlanQty.setEditable(false);
		TB_PossibleQty.setEditable(false);
		TB_CtUnitQty.setEditable(false);
		TB_CsUnitQty.setEditable(false);
		TB_PlUnitQty.setEditable(false);
		TB_PlAdjustQty.setEditable(false);
		TB_CsAdjustQty.setEditable(false);
		TB_CtAdjustQty.setEditable(false);
		TB_BrAdjustQty.setEditable(false);
		
		if(0==GetCtUnitQty&&0==GetCsUnitQty&&0==GetPlUnitQty) {
			TB_EntryMode.setEnabled(false);
		}
		
		TB_AfterQty.setEditable(false);
		TB_AfterShipPlanQty.setEditable(false);
		TB_AfterPossibleQty.setEditable(false);
		
		
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		main_fm.add(LB_Loc);
		main_fm.add(LB_LocName);
		main_fm.add(LB_LocType);
		main_fm.add(LB_ItemCd);
		main_fm.add(LB_ItemName);
		main_fm.add(LB_Lot);
		main_fm.add(LB_Expdate);
		main_fm.add(LB_ActualDate);
		main_fm.add(LB_Qty);
		main_fm.add(LB_ShipPlanQty);
		main_fm.add(LB_PossibleQty);
		main_fm.add(LB_Msg);
		main_fm.add(LB_AdjustQty);
		main_fm.add(LB_AdjustReason);
		main_fm.add(LB_PlAdjustQty);
		main_fm.add(LB_CsAdjustQty);
		main_fm.add(LB_CtAdjustQty);
		main_fm.add(LB_BrAdjustQty);
		main_fm.add(LB_PlAdjustUnitName);
		main_fm.add(LB_CsAdjustUnitName);
		main_fm.add(LB_CtAdjustUnitName);
		main_fm.add(LB_BrAdjustUnitName);
		main_fm.add(LB_AfterQty);
		main_fm.add(LB_AfterShipPlanQty);
		main_fm.add(LB_AfterPossibleQty);
		
		main_fm.add(LB_CtUnitQty);
		main_fm.add(LB_CsUnitQty);
		main_fm.add(LB_PlUnitQty);
		main_fm.add(TB_CtUnitQty);
		main_fm.add(TB_CsUnitQty);
		main_fm.add(TB_PlUnitQty);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_Loc);
		main_fm.add(TB_LocName);
		main_fm.add(TB_LocType);
		main_fm.add(TB_ItemCd);
		main_fm.add(TB_ItemName);
		main_fm.add(TB_Lot);
		main_fm.add(TB_Expdate);
		main_fm.add(TB_ActualDate);
		main_fm.add(TB_Qty);
		main_fm.add(TB_ShipPlanQty);
		main_fm.add(TB_PossibleQty);
		main_fm.add(TB_AdjustQty);
		main_fm.add(TB_EntryMode);
		main_fm.add(TB_AdjustReason);
		main_fm.add(TB_PlAdjustQty);
		main_fm.add(TB_CsAdjustQty);
		main_fm.add(TB_CtAdjustQty);
		main_fm.add(TB_BrAdjustQty);
		main_fm.add(TB_AfterQty);
		main_fm.add(TB_AfterShipPlanQty);
		main_fm.add(TB_AfterPossibleQty);
		
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String SetClCd		= B00020ToolsTextControl.Trim(B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String SetWhCd		= B00020ToolsTextControl.Trim(B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					
					String SetLoc			= B00020ToolsTextControl.Trim(TB_Loc.getText());							//ロケーション
					String SetLocName		= B00020ToolsTextControl.Trim(TB_LocName.getText());						//ロケーション名
					String SetLocType		= B00020ToolsTextControl.Trim(B00100DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
					String SetItemCd		= B00020ToolsTextControl.Trim(TB_ItemCd.getText());						//商品コード
					String SetItemName		= B00020ToolsTextControl.Trim(TB_ItemName.getText());					//商品名
					String SetLot			= B00020ToolsTextControl.Trim(TB_Lot.getText());						//ロット
					String SetExpdate		= B00020ToolsTextControl.Trim(TB_Expdate.getText());					//消費期限
					String SetActualDate	= B00020ToolsTextControl.Trim(TB_ActualDate.getText());					//入荷実績日
					String SetAdjustReason	= B00020ToolsTextControl.Trim(B00100DefaultVariable.AdjustReasonList[1][TB_AdjustReason.getSelectedIndex()]);	//調整理由
					
					int SetQty				= TextToInt(TB_Qty.getText());				//調整前数量
					int SetShipPlanQty		= TextToInt(TB_ShipPlanQty.getText());		//調整前引当済数
					int SetPossibleQty		= TextToInt(TB_PossibleQty.getText());	
					
					int SetAdjustQty		= TextToInt(TB_AdjustQty.getText());			//調整数
					int SetEntryMode		= TextToInt(TB_EntryMode.getText());
					
					int SetPlAdjustQty		= TextToInt(TB_PlAdjustQty.getText());
					int SetCsAdjustQty		= TextToInt(TB_CsAdjustQty.getText());
					int SetCtAdjustQty		= TextToInt(TB_CtAdjustQty.getText());
					int SetBrAdjustQty		= TextToInt(TB_BrAdjustQty.getText());
					
					int SetAfterQty			= TextToInt(TB_AfterQty.getText());			//調整後数量
					int SetAfterShipPlanQty	= TextToInt(TB_AfterShipPlanQty.getText());	//調整後引当済数
					int SetAfterPossibleQty	= TextToInt(TB_AfterPossibleQty.getText());	//調整後出荷可能数
					RenewFg = true;
				}
			}
		});
		
		//商品コードフォーカス消失時の挙動
		TB_ItemCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String SetClCd			= B00020ToolsTextControl.Trim(B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String SetWhCd			= B00020ToolsTextControl.Trim(B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String SetLoc			= B00020ToolsTextControl.Trim(TB_Loc.getText());							//ロケーション
					String SetItemCd		= B00020ToolsTextControl.Trim(TB_ItemCd.getText());		//商品コード
					String SetLot			= B00020ToolsTextControl.Trim(TB_Lot.getText());		//ロット
					String SetExpdate		= TextToDate(TB_Expdate.getText());					//消費期限
					String SetActualDate	= TextToDate(TB_ActualDate.getText());					//入荷実績日
					
					int SetAdjustQty		= TextToInt(TB_AdjustQty.getText());			//調整数
					
					String GetClCd			= SetClCd;				//荷主コード
					String GetCLName		= "";					//荷主名1
					String GetWhCd			= SetWhCd;				//倉庫コード
					String GetClWHName		= "";					//担当倉庫名
					String GetClGpCD		= "";					//荷主グループCD
					String GetClGpName		= "";					//グループ名1
					String GetLoc			= SetLoc;				//ロケーション
					String GetLocName		= "";					//ロケーション名
					
					String GetItemCd		= SetItemCd;			//商品コード
					String GetLot			= SetLot;				//ロット
					String GetExpdate		= SetExpdate;			//消費期限
					String GetActualDate	= SetActualDate;		//入荷実績日
					int GetQty				= 0;					//総数量
					int GetShipPlanQty		= 0;					//引当済総数
					int GetPossibleQty		= 0;					//出荷可能総数
					String GetItemName		= "";					//商品名
					String GetItemName01	= "";					//商品名1
					String GetItemName02	= "";					//商品名2
					String GetItemName03	= "";					//商品名3
					String GetClItemCd		= "";					//荷主商品コード
					String GetJanCd			= "";					//ソースマーク_BCD（バラ）
					String GetItemMdNo		= "";					//商品型番
					int GetCtUnitQty		= 0;					//カートン入数
					int GetCsUnitQty		= 0;					//ケース入数
					int GetPlUnitQty		= 0;					//パレット入数
					String GetUnitName		= "";					//商品単位
					String GetCtUnitName	= "";					//カートン商品単位
					String GetCsUnitName	= "";					//ケース商品単位
					String GetPlUnitName	= "";					//パレット商品単位
					String GetEntryDate		= "";					//登録日時
					String GetUpdateDate	= "";					//更新日時
					String GetEntryUser		= "";					//登録者
					String GetUpdateUser	= "";					//更新者
					int GetBrQty			= 0;					//バラ数量
					int GetBrShipPlanQty	= 0;					//引当済バラ数
					int GetBrPossibleQty	= 0;					//出荷可能バラ数
					int GetCtQty			= 0;					//カートン数量
					int GetCtShipPlanQty	= 0;					//引当済カートン数
					int GetCtPossibleQty	= 0;					//出荷可能カートン数
					int GetCsQty			= 0;					//ケース数量
					int GetCsShipPlanQty	= 0;					//引当済ケース数
					int GetCsPossibleQty	= 0;					//出荷可能ケース数
					int GetPlQty			= 0;					//パレット数量
					int GetPlShipPlanQty	= 0;					//引当済パレット数
					int GetPlPossibleQty	= 0;					//出荷可能パレット数
					
					boolean MstCheckFg = true;
					if(!"".equals(SetClCd)
						&& !"".equals(SetWhCd)
						&& !"".equals(SetLoc)
						&& !"".equals(SetItemCd)
						&& !"".equals(SetLot)
						&& !"".equals(SetExpdate)
						&& !"".equals(SetActualDate)
						) {
						Object[][] StockRt= StockRt(
								SetWhCd,
								SetClCd,
								SetLoc,
								SetItemCd,
								SetLot,
								SetExpdate,
								SetActualDate
								);
						if(1==StockRt.length) {
							TB_LocName.setText((String)StockRt[0][T00030StockRt.ColLocName]);										//ロケーション名
							TB_ItemName.setText((String)StockRt[0][T00030StockRt.ColItemName01]);									//商品名
							
							int GetType			= (int)StockRt[0][T00030StockRt.ColType];											//ロケタイプ
							TB_LocType.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.LocType[1]		,GetType+"" ) );	//ロケタイプ
							
							GetQty				= (int)StockRt[0][T00030StockRt.ColQty];				//総数量
							GetShipPlanQty		= (int)StockRt[0][T00030StockRt.ColShipPlanQty];		//引当済総数
							GetPossibleQty		= (int)StockRt[0][T00030StockRt.ColPossibleQty];		//出荷可能総数
							
							TB_Qty.setText(""+ni.format(GetQty));					//調整前数量
							TB_ShipPlanQty.setText(""+ni.format(GetShipPlanQty));	//調整前引当済数
							TB_PossibleQty.setText(""+ni.format(GetPossibleQty));	//調整前出荷可能総数
							
							int AfterQty			= GetQty;						//調整後数量
							int AfterShipPlanQty	= GetShipPlanQty;				//調整後引当済数
							int AfterPossibleQty	= GetPossibleQty;				//調整後出荷可能数
							
							boolean ShipTgtLoc = true;
							for(int i=0;i<B00100DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
								if(B00100DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+GetType)) {
									ShipTgtLoc = false;
								}
							}
							if(ShipTgtLoc) {
								AfterPossibleQty		= GetQty+SetAdjustQty;
							}
							TB_AfterQty.setText(""+ni.format(AfterQty));
							TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
							TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
							MstCheckFg = false;
						}
					}
					RenewFg = true;
				}
			}
		});
		
		
		//調整数フォーカス消失時の挙動
		TB_AdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int  SetPlUnitQty	= TextToInt(TB_PlUnitQty.getText());
					int  SetCsUnitQty	= TextToInt(TB_CsUnitQty.getText());
					int  SetCtUnitQty	= TextToInt(TB_CtUnitQty.getText());
					
					int AdjustQty	= TextToInt(TB_AdjustQty.getText());
					int PlAdjustQty	= TextToInt(TB_PlAdjustQty.getText());
					int CsAdjustQty	= TextToInt(TB_CsAdjustQty.getText());
					int CtAdjustQty	= TextToInt(TB_CtAdjustQty.getText());
					
					int BeforeQty				= TextToInt(TB_Qty.getText());
					int BeforeShipPlanQty		= TextToInt(TB_ShipPlanQty.getText());
					int BeforePossibleQty		= TextToInt(TB_PossibleQty.getText());
					
					int LocType	= TextToInt(B00100DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
					
					int AfterQty				= BeforeQty+AdjustQty;
					int AfterShipPlanQty		= BeforeShipPlanQty;
					
					int AfterPossibleQty		= BeforePossibleQty;
					
					boolean ShipTgtLoc = true;
					for(int i=0;i<B00100DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
						if(B00100DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+LocType)) {
							ShipTgtLoc = false;
						}
					}
					if(ShipTgtLoc) {
						AfterPossibleQty		= BeforePossibleQty+AdjustQty;
					}
					
					TB_AfterQty.setText(""+ni.format(AfterQty));
					TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
					TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
					
					if(0<SetPlUnitQty) {
						PlAdjustQty = (int)(AdjustQty/SetPlUnitQty);
						AdjustQty = AdjustQty%SetPlUnitQty;
						TB_PlAdjustQty.setText(""+ni.format(PlAdjustQty));
					}
					if(0<SetCsUnitQty) {
						CsAdjustQty = (int)(AdjustQty/SetCsUnitQty);
						AdjustQty = AdjustQty%SetCsUnitQty;
						TB_CsAdjustQty.setText(""+ni.format(CsAdjustQty));
					}
					if(0<SetCtUnitQty) {
						CtAdjustQty = (int)(AdjustQty/SetCtUnitQty);
						AdjustQty = AdjustQty%SetCtUnitQty;
						TB_CtAdjustQty.setText(""+ni.format(CtAdjustQty));
					}
					TB_BrAdjustQty.setText(""+ni.format(AdjustQty));
					
					RenewFg = true;
				}
			}
		});
		//調整数パレットフォーカス消失時の挙動
		TB_PlAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int  SetPlUnitQty	= TextToInt(TB_PlUnitQty.getText());
					int  SetCsUnitQty	= TextToInt(TB_CsUnitQty.getText());
					int  SetCtUnitQty	= TextToInt(TB_CtUnitQty.getText());
					
					int AdjustQty	= TextToInt(TB_AdjustQty.getText());
					int PlAdjustQty	= TextToInt(TB_PlAdjustQty.getText());
					int CsAdjustQty	= TextToInt(TB_CsAdjustQty.getText());
					int CtAdjustQty	= TextToInt(TB_CtAdjustQty.getText());
					int BrAdjustQty	= TextToInt(TB_BrAdjustQty.getText());
					
					AdjustQty = PlAdjustQty*SetPlUnitQty+CsAdjustQty*SetCsUnitQty+CtAdjustQty*SetCtUnitQty+BrAdjustQty;
					TB_AdjustQty.setText(""+ni.format(AdjustQty));
					
					int BeforeQty				= TextToInt(TB_Qty.getText());
					int BeforeShipPlanQty		= TextToInt(TB_ShipPlanQty.getText());
					int BeforePossibleQty		= TextToInt(TB_PossibleQty.getText());
					
					int LocType	= TextToInt(B00100DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
					
					int AfterQty				= BeforeQty+AdjustQty;
					int AfterShipPlanQty		= BeforeShipPlanQty;
					
					int AfterPossibleQty		= BeforePossibleQty;
					
					boolean ShipTgtLoc = true;
					for(int i=0;i<B00100DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
						if(B00100DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+LocType)) {
							ShipTgtLoc = false;
						}
					}
					if(ShipTgtLoc) {
						AfterPossibleQty		= BeforePossibleQty+AdjustQty;
					}
					TB_AfterQty.setText(""+ni.format(AfterQty));
					TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
					TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
					
					RenewFg = true;
				}
			}
		});
		//調整数ケースフォーカス消失時の挙動
		TB_CsAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int  SetPlUnitQty	= TextToInt(TB_PlUnitQty.getText());
					int  SetCsUnitQty	= TextToInt(TB_CsUnitQty.getText());
					int  SetCtUnitQty	= TextToInt(TB_CtUnitQty.getText());
					
					int AdjustQty	= TextToInt(TB_AdjustQty.getText());
					int PlAdjustQty	= TextToInt(TB_PlAdjustQty.getText());
					int CsAdjustQty	= TextToInt(TB_CsAdjustQty.getText());
					int CtAdjustQty	= TextToInt(TB_CtAdjustQty.getText());
					int BrAdjustQty	= TextToInt(TB_BrAdjustQty.getText());
					
					AdjustQty = PlAdjustQty*SetPlUnitQty+CsAdjustQty*SetCsUnitQty+CtAdjustQty*SetCtUnitQty+BrAdjustQty;
					TB_AdjustQty.setText(""+ni.format(AdjustQty));
					
					int BeforeQty				= TextToInt(TB_Qty.getText());
					int BeforeShipPlanQty		= TextToInt(TB_ShipPlanQty.getText());
					int BeforePossibleQty		= TextToInt(TB_PossibleQty.getText());
					
					int LocType	= TextToInt(B00100DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
					
					int AfterQty				= BeforeQty+AdjustQty;
					int AfterShipPlanQty		= BeforeShipPlanQty;
					
					int AfterPossibleQty		= BeforePossibleQty;
					
					boolean ShipTgtLoc = true;
					for(int i=0;i<B00100DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
						if(B00100DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+LocType)) {
							ShipTgtLoc = false;
						}
					}
					if(ShipTgtLoc) {
						AfterPossibleQty		= BeforePossibleQty+AdjustQty;
					}
					TB_AfterQty.setText(""+ni.format(AfterQty));
					TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
					TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
					RenewFg = true;
				}
			}
		});
		//調整数カートンフォーカス消失時の挙動
		TB_CtAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int  SetPlUnitQty	= TextToInt(TB_PlUnitQty.getText());
					int  SetCsUnitQty	= TextToInt(TB_CsUnitQty.getText());
					int  SetCtUnitQty	= TextToInt(TB_CtUnitQty.getText());
					
					int AdjustQty	= TextToInt(TB_AdjustQty.getText());
					int PlAdjustQty	= TextToInt(TB_PlAdjustQty.getText());
					int CsAdjustQty	= TextToInt(TB_CsAdjustQty.getText());
					int CtAdjustQty	= TextToInt(TB_CtAdjustQty.getText());
					int BrAdjustQty	= TextToInt(TB_BrAdjustQty.getText());
					
					AdjustQty = PlAdjustQty*SetPlUnitQty+CsAdjustQty*SetCsUnitQty+CtAdjustQty*SetCtUnitQty+BrAdjustQty;
					TB_AdjustQty.setText(""+ni.format(AdjustQty));
					
					int BeforeQty				= TextToInt(TB_Qty.getText());
					int BeforeShipPlanQty		= TextToInt(TB_ShipPlanQty.getText());
					int BeforePossibleQty		= TextToInt(TB_PossibleQty.getText());
					
					int LocType	= TextToInt(B00100DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
					
					int AfterQty				= BeforeQty+AdjustQty;
					int AfterShipPlanQty		= BeforeShipPlanQty;
					
					int AfterPossibleQty		= BeforePossibleQty;
					
					boolean ShipTgtLoc = true;
					for(int i=0;i<B00100DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
						if(B00100DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+LocType)) {
							ShipTgtLoc = false;
						}
					}
					if(ShipTgtLoc) {
						AfterPossibleQty		= BeforePossibleQty+AdjustQty;
					}
					TB_AfterQty.setText(""+ni.format(AfterQty));
					TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
					TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
					RenewFg = true;
				}
			}
		});
		//調整数バラフォーカス消失時の挙動
		TB_BrAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int  SetPlUnitQty	= TextToInt(TB_PlUnitQty.getText());
					int  SetCsUnitQty	= TextToInt(TB_CsUnitQty.getText());
					int  SetCtUnitQty	= TextToInt(TB_CtUnitQty.getText());
					
					int AdjustQty	= TextToInt(TB_AdjustQty.getText());
					int PlAdjustQty	= TextToInt(TB_PlAdjustQty.getText());
					int CsAdjustQty	= TextToInt(TB_CsAdjustQty.getText());
					int CtAdjustQty	= TextToInt(TB_CtAdjustQty.getText());
					int BrAdjustQty	= TextToInt(TB_BrAdjustQty.getText());
					
					AdjustQty = PlAdjustQty*SetPlUnitQty+CsAdjustQty*SetCsUnitQty+CtAdjustQty*SetCtUnitQty+BrAdjustQty;
					TB_AdjustQty.setText(""+ni.format(AdjustQty));
					
					int BeforeQty				= TextToInt(TB_Qty.getText());
					int BeforeShipPlanQty		= TextToInt(TB_ShipPlanQty.getText());
					int BeforePossibleQty		= TextToInt(TB_PossibleQty.getText());
					
					int LocType	= TextToInt(B00100DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
					
					int AfterQty				= BeforeQty+AdjustQty;
					int AfterShipPlanQty		= BeforeShipPlanQty;
					
					int AfterPossibleQty		= BeforePossibleQty;
					
					boolean ShipTgtLoc = true;
					for(int i=0;i<B00100DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
						if(B00100DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+LocType)) {
							ShipTgtLoc = false;
						}
					}
					if(ShipTgtLoc) {
						AfterPossibleQty		= BeforePossibleQty+AdjustQty;
					}
					TB_AfterQty.setText(""+ni.format(AfterQty));
					TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
					TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
					RenewFg = true;
				}
			}
		});
		
		TB_EntryMode.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int  SetCtUnitQty	= TextToInt(TB_CtUnitQty.getText());
					int  SetCsUnitQty	= TextToInt(TB_CsUnitQty.getText());
					int  SetPlUnitQty	= TextToInt(TB_PlUnitQty.getText());
					
					if(TB_EntryMode.isSelected()) {
						if(0<SetPlUnitQty){TB_PlAdjustQty.setEditable(true);}
						if(0<SetCsUnitQty){TB_CsAdjustQty.setEditable(true);}
						if(0<SetCtUnitQty){TB_CtAdjustQty.setEditable(true);}
						TB_BrAdjustQty.setEditable(true);
						TB_AdjustQty.setEditable(false);
					}else {
						TB_PlAdjustQty.setEditable(false);
						TB_CsAdjustQty.setEditable(false);
						TB_CtAdjustQty.setEditable(false);
						TB_BrAdjustQty.setEditable(false);
						TB_AdjustQty.setEditable(true);
					}
					RenewFg = true;
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT0001010StockSearch.StockSearch(0,0);
			}
		});
	}
	
	private static Object[][] StockRt(String TgtWhCd,
			String TgtClCd,
			String TgtLoc,
			String TgtItemCd,
			String TgtLot,
			String TgtExpdate,
			String TgtActualDate){
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
		boolean LocExactMatch = true;													//ロケーション完全一致
		boolean AllSearch = false;														//全件検索
		boolean SortItemcdMode = false;													//商品CDでソート
		
		if(!"".equals(TgtWhCd		)) {SearchWhCd.add(				TgtWhCd);}
		if(!"".equals(TgtClCd		)) {SearchClCd.add(				TgtClCd);}
		if(!"".equals(TgtLoc		)) {SearchLoc.add(				TgtLoc);}
		if(!"".equals(TgtItemCd		)) {SearchItemCd.add(			TgtItemCd);}
		if(!"".equals(TgtLot		)) {SearchLot.add(				TgtLot);}
		if(!"".equals(TgtExpdate	)) {SearchExpdateMin.add(		TgtExpdate);}
		if(!"".equals(TgtActualDate	)) {SearchActualDateMin.add(	TgtActualDate);}
		if(!"".equals(TgtExpdate	)) {SearchExpdateMax.add(		TgtExpdate);}
		if(!"".equals(TgtActualDate	)) {SearchActualDateMax.add(	TgtActualDate);}
		
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
	
	private static int TextToInt(String Tgt) {
		if(null==Tgt) {Tgt	= "";}
		Tgt	= B00020ToolsTextControl.Trim(Tgt);
		Tgt	= B00020ToolsTextControl.num_only_String02(Tgt);
		if("".equals(Tgt)) {Tgt	= "0";}
		int rt	= Integer.parseInt(Tgt);
		return rt;
	}
	
	private static String TextToDate(String Tgt) {
		if(null==Tgt) {Tgt	= "";}
		Tgt	= B00020ToolsTextControl.Trim(Tgt);
		Tgt	= B00050ToolsDateTimeControl.DateFormat(Tgt);
		return Tgt;
	}
	
	private static Object[][] LocationMstRt(String ClCd,String WhCd,String Loc){
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = true;	//ロケーション完全一致
		boolean AllSearch = false;
		
		SearchClCd.add(ClCd);
		SearchWhCd.add(WhCd);
		SearchLoc.add(Loc);
		
		Object[][] LocationMstRt = M00090LocationMstRt.LocationMstRt(
				SearchClCd,		//荷主コード
				SearchWhCd,		//倉庫コード
				SearchLoc,		//ロケーション
				SearchLocName,	//ロケーション名
				SearchType,		//ロケタイプ
				LocExactMatch,	//ロケーション完全一致
				AllSearch);
		return LocationMstRt;
	}
	
	private static Object[][] WhMstRt(String WHCD){
		ArrayList<String> SearchWHCD 	= new ArrayList<String>();
		ArrayList<String> SearchWHName 	= new ArrayList<String>();
		ArrayList<String> SearchPost 	= new ArrayList<String>();
		ArrayList<String> SearchAdd 	= new ArrayList<String>();
		ArrayList<String> SearchTel 	= new ArrayList<String>();
		ArrayList<String> SearchFax 	= new ArrayList<String>();
		ArrayList<String> SearchMail 	= new ArrayList<String>();
		ArrayList<String> SearchCom 	= new ArrayList<String>();
		ArrayList<String> SearchPTMSCD 	= new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchWHCD.add(WHCD);
		
		Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
				SearchWHCD,
				SearchWHName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				SearchPTMSCD,
				AllSearch);
		return WhMstRt;
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
	
	private static Object[][] ItemMstRt(String ClGpCd,String ItemCd){
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
		
		SearchClGpCd.add(ClGpCd);
		SearchItemCd.add(ItemCd);
		
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
	
	private static int GetSelectIndex(String[] SelectList,String TgtData ) {
		int rt = 0;
		for(int i=0;i<SelectList.length;i++) {
			if(TgtData.equals(SelectList[i])) {
				rt	= i;
				i	= SelectList.length+1;
			}
		}
		return rt;
	}
}