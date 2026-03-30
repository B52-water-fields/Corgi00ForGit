import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WM00081ItemMstRenewAndCreate{
	static int SetX;
	static int SetY;
	public static void ItemMstRenewAndCreate(int x,int y,String TgtClgpCd,String TgtItemCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1400,800,"Corgi00商品マスタ登録・修正","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		//更新モードから切替えて商品コード入力可能にするためのボタン
		JButton NewEntryBtn = B00110FrameParts.BtnSet(220, 55, 80,20,"モード切替",9);	//新規登録
		
		JLabel LB_ClGpCd				= B00110FrameParts.JLabelSet(  10, 30,100,20,"荷主グループコード:"		,10,1);
		JLabel LB_ItemCd				= B00110FrameParts.JLabelSet(  10, 55,100,20,"商品コード:"				,10,1);
		JLabel LB_CLItemCd				= B00110FrameParts.JLabelSet(  10, 80,100,20,"荷主商品コード:"			,10,1);
		JLabel LB_ItemName01			= B00110FrameParts.JLabelSet(  10,105,100,20,"商品名1:"				,10,1);
		JLabel LB_ItemName02			= B00110FrameParts.JLabelSet(  10,130,100,20,"商品名2:"				,10,1);
		JLabel LB_ItemName03			= B00110FrameParts.JLabelSet(  10,155,100,20,"商品名3:"				,10,1);
		final JComboBox   TB_ClGpCd		= B00110FrameParts.JComboBoxSet(  110, 30,200,20,B00100DefaultVariable.ClGpList[0],11);	//荷主グループコード
		final JTextField  TB_ItemCd		= B00110FrameParts.JTextFieldSet( 110, 55,100,20,"",11,0);			//商品コード
		final JTextField  TB_CLItemCd	= B00110FrameParts.JTextFieldSet( 110, 80,100,20,"",11,0);			//荷主商品コード
		final JTextField  TB_ItemName01	= B00110FrameParts.JTextFieldSet( 110,105,200,20,"",11,0);			//商品名1
		final JTextField  TB_ItemName02	= B00110FrameParts.JTextFieldSet( 110,130,200,20,"",11,0);			//商品名2
		final JTextField  TB_ItemName03	= B00110FrameParts.JTextFieldSet( 110,155,200,20,"",11,0);			//商品名3
		
		
		JLabel LB_DeliveryTypeCd01		= B00110FrameParts.JLabelSet( 310, 30,100,20,"運送タイプコード01:"		,10,1);
		JLabel LB_DeliveryTypeCd02		= B00110FrameParts.JLabelSet( 310, 55,100,20,"運送タイプコード02:"		,10,1);
		JLabel LB_DeliveryTypeCd03		= B00110FrameParts.JLabelSet( 310, 80,100,20,"運送タイプコード03:"		,10,1);
		JLabel LB_DeliveryTypeCd04		= B00110FrameParts.JLabelSet( 310,105,100,20,"運送タイプコード04:"		,10,1);
		JLabel LB_DeliveryTypeCd05		= B00110FrameParts.JLabelSet( 310,130,100,20,"運送タイプコード05:"		,10,1);
		JLabel LB_PTMSCD				= B00110FrameParts.JLabelSet( 310,155,100,20,"基幹SYS商品コード:"		,10,1);
		final JComboBox   TB_DeliveryTypeCd01	= B00110FrameParts.JComboBoxSet(  410, 30,200,20,B00100DefaultVariable.DeliveryType01[0],11);	//運送タイプコード01
		final JComboBox   TB_DeliveryTypeCd02	= B00110FrameParts.JComboBoxSet(  410, 55,200,20,B00100DefaultVariable.DeliveryType02[0],11);	//運送タイプコード02
		final JComboBox   TB_DeliveryTypeCd03	= B00110FrameParts.JComboBoxSet(  410, 80,200,20,B00100DefaultVariable.DeliveryType03[0],11);	//運送タイプコード03
		final JComboBox   TB_DeliveryTypeCd04	= B00110FrameParts.JComboBoxSet(  410,105,200,20,B00100DefaultVariable.DeliveryType04[0],11);	//運送タイプコード04
		final JComboBox   TB_DeliveryTypeCd05	= B00110FrameParts.JComboBoxSet(  410,130,200,20,B00100DefaultVariable.DeliveryType05[0],11);	//運送タイプコード05
		final JTextField  TB_PTMSCD				= B00110FrameParts.JTextFieldSet( 410,155,100,20,"",11,0);										//基幹SYS商品コード
		
		
		JLabel LB_RecomendLoc			= B00110FrameParts.JLabelSet( 610, 25,100,20,"推奨ロケ:"				,10,1);
		JLabel LB_ItemMDNo				= B00110FrameParts.JLabelSet( 610, 50,100,20,"商品型番:"				,10,1);
		JLabel LB_CategoryCd			= B00110FrameParts.JLabelSet( 610, 75,100,20,"商品カテゴリCD:"			,10,1);
		JLabel LB_CategoryName			= B00110FrameParts.JLabelSet( 610,100,100,20,"商品カテゴリ名:"			,10,1);
		JLabel LB_DelFg					= B00110FrameParts.JLabelSet( 610,150,100,20,"削除フラグ:"				,10,1);
		final JTextField  TB_RecomendLoc		= B00110FrameParts.JTextFieldSet( 710, 25,100,20,"",11,0);			//推奨ロケ
		final JTextField  TB_ItemMDNo			= B00110FrameParts.JTextFieldSet( 710, 50,100,20,"",11,0);			//商品型番
		final JTextField  TB_CategoryCd			= B00110FrameParts.JTextFieldSet( 710, 75,100,20,"",11,0);			//商品カテゴリCD
		final JTextField  TB_CategoryName		= B00110FrameParts.JTextFieldSet( 710,100,100,20,"",11,0);			//商品カテゴリ名
		final JComboBox  TB_DelFg		= B00110FrameParts.JComboBoxSet(  		710,150,100,20,B00100DefaultVariable.DelList[0],11);	//削除フラグ
		
		JLabel LB_ItemColorCd			= B00110FrameParts.JLabelSet( 910, 25,100,20,"商品カラーコード:"		,10,1);
		JLabel LB_ItemColorName			= B00110FrameParts.JLabelSet( 910, 50,100,20,"商品カラー名:"			,10,1);
		JLabel LB_ItemSizeCd			= B00110FrameParts.JLabelSet( 910, 75,100,20,"商品サイズコード:"		,10,1);
		JLabel LB_ItemSizeName			= B00110FrameParts.JLabelSet( 910,100,100,20,"商品サイス名:"			,10,1);
		JLabel LB_TildFG				= B00110FrameParts.JLabelSet( 910,125,100,20,"温度区分:"				,10,1);
		JLabel LB_ExpDateHowLong		= B00110FrameParts.JLabelSet( 910,150,100,20,"賞味期限日数:"			,10,1);
		final JTextField  TB_ItemColorCd			= B00110FrameParts.JTextFieldSet(1010, 25,100,20,"",11,0);			//商品カラーコード
		final JTextField  TB_ItemColorName			= B00110FrameParts.JTextFieldSet(1010, 50,100,20,"",11,0);			//商品カラー名
		final JTextField  TB_ItemSizeCd				= B00110FrameParts.JTextFieldSet(1010, 75,100,20,"",11,0);			//商品サイズコード
		final JTextField  TB_ItemSizeName			= B00110FrameParts.JTextFieldSet(1010,100,100,20,"",11,0);			//商品サイス名
		final JComboBox   TB_TildFG					= B00110FrameParts.JComboBoxSet( 1010,125,100,20,B00100DefaultVariable.TildFG[0],11);	//温度区分
		final JFormattedTextField TB_ExpDateHowLong	= B00110FrameParts.JFormattedTextFieldSet(	1010,150,100,20,"0",11,1,"#,###");			//賞味期限日数
		

		JLabel LB_CtQty					= B00110FrameParts.JLabelSet(  10,230,100,20,"カートン入数:"			,10,1);
		JLabel LB_CsQty					= B00110FrameParts.JLabelSet(  10,255,100,20,"ケース入数:"				,10,1);
		JLabel LB_PlQty					= B00110FrameParts.JLabelSet(  10,280,100,20,"パレット入数:"			,10,1);
		final JFormattedTextField TB_CtQty	= B00110FrameParts.JFormattedTextFieldSet(110,230,100,20,"0",11,1,"#,###");	//カートン入数
		final JFormattedTextField TB_CsQty	= B00110FrameParts.JFormattedTextFieldSet(110,255,100,20,"0",11,1,"#,###");	//ケース入数
		final JFormattedTextField TB_PlQty	= B00110FrameParts.JFormattedTextFieldSet(110,280,100,20,"0",11,1,"#,###");	//パレット入数
		
		JLabel LB_ItemWeight			= B00110FrameParts.JLabelSet( 210,205,100,20,"商品重量:"				,10,1);
		JLabel LB_CtWeight				= B00110FrameParts.JLabelSet( 210,230,100,20,"カートン重量:"			,10,1);
		JLabel LB_CsWeight				= B00110FrameParts.JLabelSet( 210,255,100,20,"ケース重量:"				,10,1);
		JLabel LB_PlWeight				= B00110FrameParts.JLabelSet( 210,280,100,20,"パレット重量:"			,10,1);
		final JFormattedTextField TB_ItemWeight	= B00110FrameParts.JFormattedTextFieldSet(310,205,100,20,"0",11,1,"#,###.##");	//商品重量
		final JFormattedTextField TB_CtWeight	= B00110FrameParts.JFormattedTextFieldSet(310,230,100,20,"0",11,1,"#,###.##");	//カートン重量
		final JFormattedTextField TB_CsWeight	= B00110FrameParts.JFormattedTextFieldSet(310,255,100,20,"0",11,1,"#,###.##");	//ケース重量
		final JFormattedTextField TB_PlWeight	= B00110FrameParts.JFormattedTextFieldSet(310,280,100,20,"0",11,1,"#,###.##");	//パレット重量

		JLabel LB_ItemSize				= B00110FrameParts.JLabelSet( 410,205,100,20,"商品サイズ:"				,10,1);
		JLabel LB_CtSize				= B00110FrameParts.JLabelSet( 410,230,100,20,"カートンサイズ:"			,10,1);
		JLabel LB_CsSize				= B00110FrameParts.JLabelSet( 410,255,100,20,"ケースサイズ:"			,10,1);
		JLabel LB_PlSize				= B00110FrameParts.JLabelSet( 410,280,100,20,"パレットサイズ:"			,10,1);
		final JFormattedTextField TB_ItemSize	= B00110FrameParts.JFormattedTextFieldSet(510,205,100,20,"0",11,1,"#,###.##");	//商品サイズ
		final JFormattedTextField TB_CtSize		= B00110FrameParts.JFormattedTextFieldSet(510,230,100,20,"0",11,1,"#,###.##");	//カートンサイズ
		final JFormattedTextField TB_CsSize		= B00110FrameParts.JFormattedTextFieldSet(510,255,100,20,"0",11,1,"#,###.##");	//ケースサイズ
		final JFormattedTextField TB_PlSize		= B00110FrameParts.JFormattedTextFieldSet(510,280,100,20,"0",11,1,"#,###.##");	//パレットサイズ
		
		JLabel LB_CtName				= B00110FrameParts.JLabelSet( 610,230,100,20,"カートン商品名称:"		,10,1);
		JLabel LB_CsName				= B00110FrameParts.JLabelSet( 610,255,100,20,"ケース商品名称:"			,10,1);
		JLabel LB_PlName				= B00110FrameParts.JLabelSet( 610,280,100,20,"パレット商品名称:"		,10,1);
		final JTextField  TB_CtName		= B00110FrameParts.JTextFieldSet( 710,230,200,20,"",11,0);	//カートン商品名称
		final JTextField  TB_CsName		= B00110FrameParts.JTextFieldSet( 710,255,200,20,"",11,0);	//ケース商品名称
		final JTextField  TB_PlName		= B00110FrameParts.JTextFieldSet( 710,280,200,20,"",11,0);	//パレット商品名称
		
		JLabel LB_UnitName				= B00110FrameParts.JLabelSet( 910,205,100,20,"商品単位:"				,10,1);
		JLabel LB_CtUnitName			= B00110FrameParts.JLabelSet( 910,230,100,20,"カートン商品単位:"		,10,1);
		JLabel LB_CsUnitName			= B00110FrameParts.JLabelSet( 910,255,100,20,"ケース商品単位:"			,10,1);
		JLabel LB_PlUnitName			= B00110FrameParts.JLabelSet( 910,280,100,20,"パレット商品単位:"		,10,1);
		final JTextField  TB_UnitName	= B00110FrameParts.JTextFieldSet(1010,205,100,20,"",11,0);	//商品単位
		final JTextField  TB_CtUnitName	= B00110FrameParts.JTextFieldSet(1010,230,100,20,"",11,0);	//カートン商品単位
		final JTextField  TB_CsUnitName	= B00110FrameParts.JTextFieldSet(1010,255,100,20,"",11,0);	//ケース商品単位
		final JTextField  TB_PlUnitName	= B00110FrameParts.JTextFieldSet(1010,280,100,20,"",11,0);	//パレット商品単位
		
		JLabel LB_JanCd					= B00110FrameParts.JLabelSet(1110,205,100,20,"JANCD:"					,10,1);
		JLabel LB_CtJan					= B00110FrameParts.JLabelSet(1110,230,100,20,"カートンバーコード:"		,10,1);
		JLabel LB_CsJan					= B00110FrameParts.JLabelSet(1110,255,100,20,"ケースバーコード:"		,10,1);
		JLabel LB_PlJan					= B00110FrameParts.JLabelSet(1110,280,100,20,"パレットバーコード:"		,10,1);
		final JTextField  TB_JanCd		= B00110FrameParts.JTextFieldSet(1210,205,150,20,"",11,0);	//JANCD
		final JTextField  TB_CtJan		= B00110FrameParts.JTextFieldSet(1210,230,150,20,"",11,0);	//カートンバーコード
		final JTextField  TB_CsJan		= B00110FrameParts.JTextFieldSet(1210,255,150,20,"",11,0);	//ケースバーコード
		final JTextField  TB_PlJan		= B00110FrameParts.JTextFieldSet(1210,280,150,20,"",11,0);	//パレットバーコード
		
		
		JLabel LB_Com01					= B00110FrameParts.JLabelSet(  10,650,100,20,"コメント1:"				,10,1);
		JLabel LB_Com02					= B00110FrameParts.JLabelSet(  10,675,100,20,"コメント2:"				,10,1);
		JLabel LB_Com03					= B00110FrameParts.JLabelSet(  10,700,100,20,"コメント3:"				,10,1);
		final JTextField  TB_Com01		= B00110FrameParts.JTextFieldSet( 110,650,200,20,"",11,0);	//コメント1
		final JTextField  TB_Com02		= B00110FrameParts.JTextFieldSet( 110,675,200,20,"",11,0);	//コメント2
		final JTextField  TB_Com03		= B00110FrameParts.JTextFieldSet( 110,700,200,20,"",11,0);	//コメント3
		
		JLabel LB_EntryDate				= B00110FrameParts.JLabelSet( 310,650,100,20,"データ登録日時:"			,10,1);
		JLabel LB_UpdateDate			= B00110FrameParts.JLabelSet( 310,675,100,20,"データ更新日時:"			,10,1);
		JLabel LB_EntryUser				= B00110FrameParts.JLabelSet( 310,700,100,20,"登録者コード:"			,10,1);
		JLabel LB_UpdateUser			= B00110FrameParts.JLabelSet( 310,725,100,20,"更新者コード:"			,10,1);
		final JTextField  TB_EntryDate	= B00110FrameParts.JTextFieldSet( 410,650,200,20,"",11,0);	//データ登録日時
		final JTextField  TB_UpdateDate	= B00110FrameParts.JTextFieldSet( 410,675,200,20,"",11,0);	//データ更新日時
		final JTextField  TB_EntryUser	= B00110FrameParts.JTextFieldSet( 410,700,200,20,"",11,0);	//登録者コード
		final JTextField  TB_UpdateUser	= B00110FrameParts.JTextFieldSet( 410,725,200,20,"",11,0);	//更新者コード
		
		//DB登録用画像パス※表示しません。内部的に使います
		final JTextField  TB_ItemImageEntryPath01 = B00110FrameParts.JTextFieldSet(	 170,350,250,20,"",11,0);			//画像パス01 
		final JTextField  TB_ItemImageEntryPath02 = B00110FrameParts.JTextFieldSet(	 430,350,250,20,"",11,0);			//画像パス02 
		final JTextField  TB_ItemImageEntryPath03 = B00110FrameParts.JTextFieldSet(	 600,350,250,20,"",11,0);			//画像パス03 
		final JTextField  TB_ItemImageEntryPath04 = B00110FrameParts.JTextFieldSet(	 860,350,250,20,"",11,0);			//画像パス04 
		final JTextField  TB_ItemImageEntryPath05 = B00110FrameParts.JTextFieldSet(	1120,350,250,20,"",11,0);			//画像パス04 
		
		
		JButton ItemImage01Btn = B00110FrameParts.BtnSet(			 				  80,325, 80,20,"画像01選択",9);	//画像選択ボタン01
		final JTextField  TB_ItemImagePath01	= B00110FrameParts.JTextFieldSet(	 170,325,160,20,"",11,0);			//画像パス01
		ImageIcon ItemImage01 = new ImageIcon();
		final JLabel TB_ItemImage01 = B00110FrameParts.JLabelPictSet( 		 80,350,250,250,ItemImage01,0);
		JButton ItemImage01RotateBtn = B00110FrameParts.BtnSet(			 	 80,600, 80,20,"回転",11);	//画像回転ボタン
		final JTextField  ItemImage01Rotate	= B00110FrameParts.JTextFieldSet( 170,600, 80,20,"0",11,0);	//回転角度
		
		JButton ItemImage02Btn = B00110FrameParts.BtnSet(			 				 340,325, 80,20,"画像02選択",9);	//画像選択ボタン02
		final JTextField  TB_ItemImagePath02	= B00110FrameParts.JTextFieldSet(	 430,325,160,20,"",11,0);			//画像パス02
		ImageIcon ItemImage02 = new ImageIcon();
		final JLabel TB_ItemImage02 = B00110FrameParts.JLabelPictSet( 		340,350,250,250,ItemImage02,0);
		JButton ItemImage02RotateBtn = B00110FrameParts.BtnSet(			 	340,600, 80,20,"回転",11);	//画像回転ボタン
		final JTextField  ItemImage02Rotate	= B00110FrameParts.JTextFieldSet( 430,600, 80,20,"0",11,0);	//回転角度
		
		JButton ItemImage03Btn = B00110FrameParts.BtnSet(			 				 600,325, 80,20,"画像03選択",9);	//画像選択ボタン03
		final JTextField  TB_ItemImagePath03	= B00110FrameParts.JTextFieldSet(	 690,325,160,20,"",11,0);			//画像パス03
		ImageIcon ItemImage03 = new ImageIcon();
		final JLabel TB_ItemImage03 = B00110FrameParts.JLabelPictSet( 		600,350,250,250,ItemImage03,0);
		JButton ItemImage03RotateBtn = B00110FrameParts.BtnSet(			 	600,600, 80,20,"回転",11);	//画像回転ボタン
		final JTextField  ItemImage03Rotate	= B00110FrameParts.JTextFieldSet( 690,600, 80,20,"0",11,0);	//回転角度
		
		JButton ItemImage04Btn = B00110FrameParts.BtnSet(			 				 860,325, 80,20,"画像04選択",9);	//画像選択ボタン04
		final JTextField  TB_ItemImagePath04	= B00110FrameParts.JTextFieldSet(	 950,325,160,20,"",11,0);			//画像パス04
		ImageIcon ItemImage04 = new ImageIcon();
		final JLabel TB_ItemImage04 = B00110FrameParts.JLabelPictSet(			 860,350,250,250,ItemImage04,0);
		JButton ItemImage04RotateBtn = B00110FrameParts.BtnSet(			 	 860,600, 80,20,"回転",11);	//画像回転ボタン
		final JTextField  ItemImage04Rotate	= B00110FrameParts.JTextFieldSet(  950,600, 80,20,"0",11,0);	//回転角度
		
		JButton ItemImage05Btn = B00110FrameParts.BtnSet(			 				1120,325, 80,20,"画像05選択",9);	//画像選択ボタン05
		final JTextField  TB_ItemImagePath05	= B00110FrameParts.JTextFieldSet(	1210,325,160,20,"",11,0);			//画像パス05
		ImageIcon ItemImage05 = new ImageIcon();
		final JLabel TB_ItemImage05 = B00110FrameParts.JLabelPictSet(			1120,350,250,250,ItemImage05,0);
		JButton ItemImage05RotateBtn = B00110FrameParts.BtnSet(			 	1120,600, 80,20,"回転",11);	//画像回転ボタン
		final JTextField  ItemImage05Rotate	= B00110FrameParts.JTextFieldSet( 1210,600, 80,20,"0",11,0);	//回転角度
		
		if(null==TgtClgpCd||"".equals(TgtClgpCd)) {TgtClgpCd = A00000Main.ClGp;}
		for(int i=0;i<B00100DefaultVariable.ClGpList[1].length;i++) {
			if(TgtClgpCd.equals(""+B00100DefaultVariable.ClGpList[1][i])) {
				TB_ClGpCd.setSelectedIndex(i);
			}
		}
		TB_ClGpCd.setEnabled(false);
		if(null==TgtItemCd||"".equals(TgtItemCd)) {TgtItemCd = "";}
		
		if(!"".equals(TgtItemCd)) {
			ArrayList<String> SearchClGpCd = new ArrayList<String>();			//荷主グループコード
			ArrayList<String> SearchItemCd = new ArrayList<String>();			//商品コード
			ArrayList<String> SearchCLItemCd = new ArrayList<String>();			//荷主商品コード
			ArrayList<String> SearchItemName = new ArrayList<String>();			//商品名
			ArrayList<String> SearchDeliveryTypeCd01 = new ArrayList<String>();	//運送タイプコード01
			ArrayList<String> SearchDeliveryTypeCd02 = new ArrayList<String>();	//運送タイプコード02
			ArrayList<String> SearchDeliveryTypeCd03 = new ArrayList<String>();	//運送タイプコード03
			ArrayList<String> SearchDeliveryTypeCd04 = new ArrayList<String>();	//運送タイプコード04
			ArrayList<String> SearchDeliveryTypeCd05 = new ArrayList<String>();	//運送タイプコード05
			ArrayList<String> SearchItemMDNo = new ArrayList<String>();			//商品モデル番号（型番）
			ArrayList<String> SearchCategoryCd = new ArrayList<String>();		//商品カテゴリCD
			ArrayList<String> SearchCategoryName = new ArrayList<String>();		//商品カテゴリ名
			ArrayList<String> SearchItemColorCd = new ArrayList<String>();		//商品カラーコード
			ArrayList<String> SearchItemColorName = new ArrayList<String>();	//商品カラー名
			ArrayList<String> SearchItemSizeCd = new ArrayList<String>();		//商品サイズコード
			ArrayList<String> SearchItemSizeName = new ArrayList<String>();		//商品サイス名
			ArrayList<String> SearchJanCd = new ArrayList<String>();			//JANCD
			ArrayList<String> SearchTildFG = new ArrayList<String>();			//温度区分
			ArrayList<String> SearchTildName = new ArrayList<String>();			//温度区分名
			ArrayList<String> SearchDelFg = new ArrayList<String>();			//削除フラグ
			boolean AllSearch = false;
			
			SearchClGpCd.add(TgtClgpCd);
			SearchItemCd.add(TgtItemCd);
			
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
					SearchItemSizeName,		//商品サイス名
					SearchJanCd,			//JANCD
					SearchTildFG,			//温度区分
					SearchTildName,			//温度区分名
					SearchDelFg,			//削除フラグ
					AllSearch);
			
			if(0<ItemMstRt.length) {
				TB_CLItemCd.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCLItemCd]);				//荷主商品コード
				TB_ItemName01.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemName01]);		//商品名1
				TB_ItemName02.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemName02]);		//商品名2
				TB_ItemName03.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemName03]);		//商品名3
				for(int i=0;i<B00100DefaultVariable.DeliveryType01[1].length;i++) {
					if(B00100DefaultVariable.DeliveryType01[1][i].equals(""+ItemMstRt[0][M00070ItemMstRt.ColDeliveryTypeCd01])) {
						TB_DeliveryTypeCd01.setSelectedIndex(i);;				//運送タイプコード01
					}
				}
				for(int i=0;i<B00100DefaultVariable.DeliveryType02[1].length;i++) {
					if(B00100DefaultVariable.DeliveryType02[1][i].equals(""+ItemMstRt[0][M00070ItemMstRt.ColDeliveryTypeCd02])) {
						TB_DeliveryTypeCd02.setSelectedIndex(i);;				//運送タイプコード02
					}
				}
				for(int i=0;i<B00100DefaultVariable.DeliveryType03[1].length;i++) {
					if(B00100DefaultVariable.DeliveryType03[1][i].equals(""+ItemMstRt[0][M00070ItemMstRt.ColDeliveryTypeCd03])) {
						TB_DeliveryTypeCd03.setSelectedIndex(i);;				//運送タイプコード03
					}
				}
				for(int i=0;i<B00100DefaultVariable.DeliveryType04[1].length;i++) {
					if(B00100DefaultVariable.DeliveryType04[1][i].equals(""+ItemMstRt[0][M00070ItemMstRt.ColDeliveryTypeCd04])) {
						TB_DeliveryTypeCd04.setSelectedIndex(i);;				//運送タイプコード04
					}
				}
				for(int i=0;i<B00100DefaultVariable.DeliveryType05[1].length;i++) {
					if(B00100DefaultVariable.DeliveryType05[1][i].equals(""+ItemMstRt[0][M00070ItemMstRt.ColDeliveryTypeCd05])) {
						TB_DeliveryTypeCd05.setSelectedIndex(i);;				//運送タイプコード05
					}
				}
				TB_PTMSCD.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPTMSCD]);					//基幹システム商品コード
				TB_CtQty.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCtQty]);					//カートン入数
				TB_CsQty.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCsQty]);					//ケース入数
				TB_PlQty.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPlQty]);					//パレット入数
				TB_JanCd.setText(""+ItemMstRt[0][M00070ItemMstRt.ColJanCd]);					//JANCD
				TB_CtJan.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCtJan]);					//カートンバーコード
				TB_CsJan.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCsJan]);					//ケースバーコード
				TB_PlJan.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPlJan]);					//パレットバーコード
				TB_CtName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCtName]);					//カートン商品名称
				TB_CsName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCsName]);					//ケース商品名称
				TB_PlName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPlName]);					//パレット商品名称
				TB_UnitName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColUnitName]);				//商品単位
				TB_CtUnitName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCtUnitName]);		//カートン商品単位
				TB_CsUnitName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCsUnitName]);		//ケース商品単位
				TB_PlUnitName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPlUnitName]);		//パレット商品単位
				TB_ItemWeight.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemWeight]);		//商品重量
				TB_CtWeight.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCtWeight]);				//カートン重量
				TB_CsWeight.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCsWeight]);				//ケース重量
				TB_PlWeight.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPlWeight]);				//パレット重量
				TB_ItemSize.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemSize]);				//商品サイズ
				TB_CtSize.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCtSize]);					//カートンサイズ
				TB_CsSize.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCsSize]);					//ケースサイズ
				TB_PlSize.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPlSize]);					//パレットサイズ
				TB_RecomendLoc.setText(""+ItemMstRt[0][M00070ItemMstRt.ColRecomendLoc]);		//推奨ロケ
				TB_ItemMDNo.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemMDNo]);				//商品モデル番号（型番）
				TB_CategoryCd.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCategoryCd]);		//商品カテゴリCD
				TB_CategoryName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCategoryName]);	//商品カテゴリ名
				TB_ItemColorCd.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemColorCd]);		//商品カラーコード
				TB_ItemColorName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemColorName]);	//商品カラー名
				TB_ItemSizeCd.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemSizeCd]);		//商品サイズコード
				TB_ItemSizeName.setText(""+ItemMstRt[0][M00070ItemMstRt.ColItemSizeName]);	//商品サイス名
				TB_Com01.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCom01]);					//コメント1
				TB_Com02.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCom02]);					//コメント2
				TB_Com03.setText(""+ItemMstRt[0][M00070ItemMstRt.ColCom03]);					//コメント3
				for(int i=0;i<B00100DefaultVariable.TildFG[1].length;i++) {
					if(B00100DefaultVariable.TildFG[1][i].equals(""+ItemMstRt[0][M00070ItemMstRt.ColTildFG])) {
						TB_TildFG.setSelectedIndex(i);;				//温度区分
					}
				}
				TB_ExpDateHowLong.setText(""+ItemMstRt[0][M00070ItemMstRt.ColExpDateHowLong]);	//賞味期限日数
				TB_EntryDate.setText(""+ItemMstRt[0][M00070ItemMstRt.ColEntryDate]);				//データ登録日時
				TB_UpdateDate.setText(""+ItemMstRt[0][M00070ItemMstRt.ColUpdateDate]);			//データ更新日時
				TB_EntryUser.setText(""+ItemMstRt[0][M00070ItemMstRt.ColEntryUser]);				//登録者コード
				TB_UpdateUser.setText(""+ItemMstRt[0][M00070ItemMstRt.ColUpdateUser]);			//更新者コード
				
				for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++) {
					if(B00100DefaultVariable.DelList[1][i].equals(""+ItemMstRt[0][M00070ItemMstRt.ColDelFg])) {
						TB_DelFg.setSelectedIndex(i);;				//削除フラグ
					}
				}
				TB_ItemImageEntryPath01.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass01]);			//画像パス01 
				TB_ItemImageEntryPath02.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass02]);			//画像パス02 
				TB_ItemImageEntryPath03.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass03]);			//画像パス03 
				TB_ItemImageEntryPath04.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass04]);			//画像パス04
				TB_ItemImageEntryPath05.setText(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass05]);			//画像パス05
				if (B00140PictControl.PictCheck(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass01])) {
					Image image = B00140PictControl.PictReSize(250,250,""+ItemMstRt[0][M00070ItemMstRt.ColPictPass01]);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
						TB_ItemImage01.setIcon(Setimage);
					}
				}
				if (B00140PictControl.PictCheck(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass02])) {
					Image image = B00140PictControl.PictReSize(250,250,""+ItemMstRt[0][M00070ItemMstRt.ColPictPass02]);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
						TB_ItemImage02.setIcon(Setimage);
					}
				}
				if (B00140PictControl.PictCheck(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass03])) {
					Image image = B00140PictControl.PictReSize(250,250,""+ItemMstRt[0][M00070ItemMstRt.ColPictPass03]);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
						TB_ItemImage03.setIcon(Setimage);
					}
				}
				if (B00140PictControl.PictCheck(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass04])) {
					Image image = B00140PictControl.PictReSize(250,250,""+ItemMstRt[0][M00070ItemMstRt.ColPictPass04]);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
						TB_ItemImage04.setIcon(Setimage);
					}
				}
				if (B00140PictControl.PictCheck(""+ItemMstRt[0][M00070ItemMstRt.ColPictPass05])) {
					Image image = B00140PictControl.PictReSize(250,250,""+ItemMstRt[0][M00070ItemMstRt.ColPictPass05]);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
						TB_ItemImage05.setIcon(Setimage);
					}
				}
			}
		}
		
		if(!"".equals(TgtItemCd)) {
			main_fm.add(NewEntryBtn);
			TB_ItemCd.setText(TgtItemCd);
			TB_ItemCd.setEnabled(false);
			LB_CLItemCd.requestFocusInWindow();
		}else {
			TB_ItemCd.setEnabled(true);
			//商品コードが指定されていなければ荷主商品コード意外全ての入力項目入力不可
			TB_ItemName01.setEnabled(false);
			TB_ItemName02.setEnabled(false);
			TB_ItemName03.setEnabled(false);
			TB_DeliveryTypeCd01.setEnabled(false);
			TB_DeliveryTypeCd02.setEnabled(false);
			TB_DeliveryTypeCd03.setEnabled(false);
			TB_DeliveryTypeCd04.setEnabled(false);
			TB_DeliveryTypeCd05.setEnabled(false);
			TB_PTMSCD.setEnabled(false);
			TB_RecomendLoc.setEnabled(false);
			TB_ItemMDNo.setEnabled(false);
			TB_CategoryCd.setEnabled(false);
			TB_CategoryName.setEnabled(false);
			TB_ItemColorCd.setEnabled(false);
			TB_ItemColorName.setEnabled(false);
			TB_ItemSizeCd.setEnabled(false);
			TB_ItemSizeName.setEnabled(false);
			TB_TildFG.setEnabled(false);
			TB_ExpDateHowLong.setEnabled(false);
			TB_CtQty.setEnabled(false);
			TB_CsQty.setEnabled(false);
			TB_PlQty.setEnabled(false);
			TB_ItemWeight.setEnabled(false);
			TB_CtWeight.setEnabled(false);
			TB_CsWeight.setEnabled(false);
			TB_PlWeight.setEnabled(false);
			TB_ItemSize.setEnabled(false);
			TB_CtSize.setEnabled(false);
			TB_CsSize.setEnabled(false);
			TB_PlSize.setEnabled(false);
			TB_CtName.setEnabled(false);
			TB_CsName.setEnabled(false);
			TB_PlName.setEnabled(false);
			TB_UnitName.setEnabled(false);
			TB_CtUnitName.setEnabled(false);
			TB_CsUnitName.setEnabled(false);
			TB_PlUnitName.setEnabled(false);
			TB_JanCd.setEnabled(false);
			TB_CtJan.setEnabled(false);
			TB_CsJan.setEnabled(false);
			TB_PlJan.setEnabled(false);
			TB_Com01.setEnabled(false);
			TB_Com02.setEnabled(false);
			TB_Com03.setEnabled(false);
			TB_EntryDate.setEnabled(false);
			TB_UpdateDate.setEnabled(false);
			TB_EntryUser.setEnabled(false);
			TB_UpdateUser.setEnabled(false);
			TB_DelFg.setEnabled(false);
			TB_ItemImagePath01.setEnabled(false);
			ItemImage01Btn.setEnabled(false);
			TB_ItemImagePath02.setEnabled(false);
			ItemImage02Btn.setEnabled(false);
			TB_ItemImagePath03.setEnabled(false);
			ItemImage03Btn.setEnabled(false);
			TB_ItemImagePath04.setEnabled(false);
			ItemImage04Btn.setEnabled(false);
			TB_ItemImagePath05.setEnabled(false);
			ItemImage05Btn.setEnabled(false);
		}
		
		TB_ItemImagePath01.setEditable(false);
		TB_ItemImagePath02.setEditable(false);
		TB_ItemImagePath03.setEditable(false);
		TB_ItemImagePath04.setEditable(false);
		TB_ItemImagePath05.setEditable(false);
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		ItemImage01Rotate.setEditable(false);
		ItemImage02Rotate.setEditable(false);
		ItemImage03Rotate.setEditable(false);
		ItemImage04Rotate.setEditable(false);
		ItemImage05Rotate.setEditable(false);
		
		main_fm.add(LB_ClGpCd);
		main_fm.add(LB_ItemCd);
		main_fm.add(LB_CLItemCd);
		main_fm.add(LB_ItemName01);
		main_fm.add(LB_ItemName02);
		main_fm.add(LB_ItemName03);
		main_fm.add(LB_DeliveryTypeCd01);
		main_fm.add(LB_DeliveryTypeCd02);
		main_fm.add(LB_DeliveryTypeCd03);
		main_fm.add(LB_DeliveryTypeCd04);
		main_fm.add(LB_DeliveryTypeCd05);
		main_fm.add(LB_PTMSCD);
		main_fm.add(LB_RecomendLoc);
		main_fm.add(LB_ItemMDNo);
		main_fm.add(LB_CategoryCd);
		main_fm.add(LB_CategoryName);
		main_fm.add(LB_ItemColorCd);
		main_fm.add(LB_ItemColorName);
		main_fm.add(LB_ItemSizeCd);
		main_fm.add(LB_ItemSizeName);
		main_fm.add(LB_TildFG);
		main_fm.add(LB_ExpDateHowLong);
		main_fm.add(LB_CtQty);
		main_fm.add(LB_CsQty);
		main_fm.add(LB_PlQty);
		main_fm.add(LB_ItemWeight);
		main_fm.add(LB_CtWeight);
		main_fm.add(LB_CsWeight);
		main_fm.add(LB_PlWeight);
		main_fm.add(LB_ItemSize);
		main_fm.add(LB_CtSize);
		main_fm.add(LB_CsSize);
		main_fm.add(LB_PlSize);
		main_fm.add(LB_JanCd);
		main_fm.add(LB_CtJan);
		main_fm.add(LB_CsJan);
		main_fm.add(LB_PlJan);
		main_fm.add(LB_CtName);
		main_fm.add(LB_CsName);
		main_fm.add(LB_PlName);
		main_fm.add(LB_UnitName);
		main_fm.add(LB_CtUnitName);
		main_fm.add(LB_CsUnitName);
		main_fm.add(LB_PlUnitName);
		main_fm.add(LB_Com01);
		main_fm.add(LB_Com02);
		main_fm.add(LB_Com03);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		main_fm.add(LB_DelFg);
		
		main_fm.add(TB_ClGpCd);
		main_fm.add(TB_ItemCd);
		main_fm.add(TB_CLItemCd);
		main_fm.add(TB_ItemName01);
		main_fm.add(TB_ItemName02);
		main_fm.add(TB_ItemName03);
		main_fm.add(TB_DeliveryTypeCd01);
		main_fm.add(TB_DeliveryTypeCd02);
		main_fm.add(TB_DeliveryTypeCd03);
		main_fm.add(TB_DeliveryTypeCd04);
		main_fm.add(TB_DeliveryTypeCd05);
		main_fm.add(TB_PTMSCD);
		main_fm.add(TB_RecomendLoc);
		main_fm.add(TB_ItemMDNo);
		main_fm.add(TB_CategoryCd);
		main_fm.add(TB_CategoryName);
		main_fm.add(TB_ItemColorCd);
		main_fm.add(TB_ItemColorName);
		main_fm.add(TB_ItemSizeCd);
		main_fm.add(TB_ItemSizeName);
		main_fm.add(TB_TildFG);
		main_fm.add(TB_ExpDateHowLong);
		main_fm.add(TB_CtQty);
		main_fm.add(TB_CsQty);
		main_fm.add(TB_PlQty);
		main_fm.add(TB_ItemWeight);
		main_fm.add(TB_CtWeight);
		main_fm.add(TB_CsWeight);
		main_fm.add(TB_PlWeight);
		main_fm.add(TB_ItemSize);
		main_fm.add(TB_CtSize);
		main_fm.add(TB_CsSize);
		main_fm.add(TB_PlSize);
		main_fm.add(TB_CtName);
		main_fm.add(TB_CsName);
		main_fm.add(TB_PlName);
		main_fm.add(TB_UnitName);
		main_fm.add(TB_CtUnitName);
		main_fm.add(TB_CsUnitName);
		main_fm.add(TB_PlUnitName);
		main_fm.add(TB_JanCd);
		main_fm.add(TB_CtJan);
		main_fm.add(TB_CsJan);
		main_fm.add(TB_PlJan);
		main_fm.add(TB_Com01);
		main_fm.add(TB_Com02);
		main_fm.add(TB_Com03);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		main_fm.add(TB_DelFg);
		main_fm.add(TB_ItemImagePath01);
		main_fm.add(ItemImage01Btn);
		main_fm.add(TB_ItemImage01);
		main_fm.add(TB_ItemImagePath02);
		main_fm.add(ItemImage02Btn);
		main_fm.add(TB_ItemImage02);
		main_fm.add(TB_ItemImagePath03);
		main_fm.add(ItemImage03Btn);
		main_fm.add(TB_ItemImage03);
		main_fm.add(TB_ItemImagePath04);
		main_fm.add(ItemImage04Btn);
		main_fm.add(TB_ItemImage04);
		main_fm.add(TB_ItemImagePath05);
		main_fm.add(ItemImage05Btn);
		main_fm.add(TB_ItemImage05);
		
		main_fm.setVisible(true);
		if(!"".equals(TgtItemCd)) {
			TB_CLItemCd.requestFocusInWindow();
		}else {
			TB_ItemCd.requestFocusInWindow();
		}
		
		ItemImage01Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String MSG = "画像ファイル選択";
				String[] file_type = {".jpg",".png"};
				String file_type_name = "画像ファイル";
				String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					Image image = B00140PictControl.PictReSize(250,250,Selected);
					if(null!=image) {
						TB_ItemImagePath01.setText(Selected);
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage01.setIcon(Setimage);
					    main_fm.add(ItemImage01RotateBtn);
						main_fm.add(ItemImage01Rotate);
						main_fm.setVisible(false);
						main_fm.setVisible(true);
					}
				}else {
					String GetMstImage = TB_ItemImageEntryPath01.getText();	if(null==GetMstImage) {GetMstImage="";}
					TB_ItemImagePath01.setText("");
					TB_ItemImage01.setIcon(null);
					main_fm.remove(ItemImage01RotateBtn);
					main_fm.remove(ItemImage01Rotate);
					
					if(null!=GetMstImage && B00140PictControl.PictCheck(GetMstImage)) {
						Image image = B00140PictControl.PictReSize(250,250,GetMstImage);
						if(null!=image) {
							ImageIcon Setimage = new ImageIcon(image);
						    TB_ItemImage01.setIcon(Setimage);
						}
					}
					main_fm.setVisible(false);
					main_fm.setVisible(true);
				}
			}
		});
		
		ItemImage02Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String MSG = "画像ファイル選択";
				String[] file_type = {".jpg",".png"};
				String file_type_name = "画像ファイル";
				String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					Image image = B00140PictControl.PictReSize(250,250,Selected);
					if(null!=image) {
						TB_ItemImagePath02.setText(Selected);
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage02.setIcon(Setimage);
					    main_fm.add(ItemImage02RotateBtn);
						main_fm.add(ItemImage02Rotate);
						main_fm.setVisible(false);
						main_fm.setVisible(true);
					}
				}else {
					String GetMstImage = TB_ItemImageEntryPath02.getText();	if(null==GetMstImage) {GetMstImage="";}
					TB_ItemImagePath02.setText("");
					TB_ItemImage02.setIcon(null);
					main_fm.remove(ItemImage02RotateBtn);
					main_fm.remove(ItemImage02Rotate);
					
					if(null!=GetMstImage && B00140PictControl.PictCheck(GetMstImage)) {
						Image image = B00140PictControl.PictReSize(250,250,GetMstImage);
						if(null!=image) {
							ImageIcon Setimage = new ImageIcon(image);
						    TB_ItemImage02.setIcon(Setimage);
						}
					}
					main_fm.setVisible(false);
					main_fm.setVisible(true);
				}
			}
		});
		
		ItemImage03Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String MSG = "画像ファイル選択";
				String[] file_type = {".jpg",".png"};
				String file_type_name = "画像ファイル";
				String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					Image image = B00140PictControl.PictReSize(250,250,Selected);
					if(null!=image) {
						TB_ItemImagePath03.setText(Selected);
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage03.setIcon(Setimage);
					    main_fm.add(ItemImage03RotateBtn);
						main_fm.add(ItemImage03Rotate);
						main_fm.setVisible(false);
						main_fm.setVisible(true);
					}
				}else {
					String GetMstImage = TB_ItemImageEntryPath03.getText();	if(null==GetMstImage) {GetMstImage="";}
					TB_ItemImagePath03.setText("");
					TB_ItemImage03.setIcon(null);
					main_fm.remove(ItemImage03RotateBtn);
					main_fm.remove(ItemImage03Rotate);
					
					if(null!=GetMstImage && B00140PictControl.PictCheck(GetMstImage)) {
						Image image = B00140PictControl.PictReSize(250,250,GetMstImage);
						if(null!=image) {
							ImageIcon Setimage = new ImageIcon(image);
						    TB_ItemImage03.setIcon(Setimage);
						}
					}
					main_fm.setVisible(false);
					main_fm.setVisible(true);
				}
			}
		});
		
		ItemImage04Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String MSG = "画像ファイル選択";
				String[] file_type = {".jpg",".png"};
				String file_type_name = "画像ファイル";
				String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
				
				if(null!=Selected  && B00140PictControl.PictCheck(Selected)) {
					Image image = B00140PictControl.PictReSize(250,250,Selected);
					if(null!=image) {
						TB_ItemImagePath04.setText(Selected);
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage04.setIcon(Setimage);
					    main_fm.add(ItemImage04RotateBtn);
						main_fm.add(ItemImage04Rotate);
						main_fm.setVisible(false);
						main_fm.setVisible(true);
					}
				}else {
					String GetMstImage = TB_ItemImageEntryPath04.getText();	if(null==GetMstImage) {GetMstImage="";}
					TB_ItemImagePath04.setText("");
					TB_ItemImage04.setIcon(null);
					main_fm.remove(ItemImage04RotateBtn);
					main_fm.remove(ItemImage04Rotate);
					
					if(null!=GetMstImage && B00140PictControl.PictCheck(GetMstImage)) {
						Image image = B00140PictControl.PictReSize(250,250,GetMstImage);
						if(null!=image) {
							ImageIcon Setimage = new ImageIcon(image);
						    TB_ItemImage04.setIcon(Setimage);
						}
					}
					main_fm.setVisible(false);
					main_fm.setVisible(true);
				}
			}
		});

		ItemImage05Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String MSG = "画像ファイル選択";
				String[] file_type = {".jpg",".png"};
				String file_type_name = "画像ファイル";
				String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
				
				if(null!=Selected  && B00140PictControl.PictCheck(Selected)) {
					Image image = B00140PictControl.PictReSize(250,250,Selected);
					if(null!=image) {
						TB_ItemImagePath05.setText(Selected);
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage05.setIcon(Setimage);
					    main_fm.add(ItemImage05RotateBtn);
						main_fm.add(ItemImage05Rotate);
						main_fm.setVisible(false);
						main_fm.setVisible(true);
					}
				}else {
					String GetMstImage = TB_ItemImageEntryPath05.getText();	if(null==GetMstImage) {GetMstImage="";}
					TB_ItemImagePath05.setText("");
					TB_ItemImage05.setIcon(null);
					main_fm.remove(ItemImage05RotateBtn);
					main_fm.remove(ItemImage05Rotate);
					
					if(null!=GetMstImage && B00140PictControl.PictCheck(GetMstImage)) {
						Image image = B00140PictControl.PictReSize(250,250,GetMstImage);
						if(null!=image) {
							ImageIcon Setimage = new ImageIcon(image);
						    TB_ItemImage05.setIcon(Setimage);
						}
					}
					main_fm.setVisible(false);
					main_fm.setVisible(true);
				}
			}
		});
		ItemImage01RotateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String Selected = TB_ItemImagePath01.getText();
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					String Rotate = ItemImage01Rotate.getText();	if(null==Rotate) {Rotate = "0";}
					Rotate = B00020ToolsTextControl.num_only_String02(Rotate);
					if("".equals(Rotate)) {Rotate = "0";}
					int RotateSet = Integer.parseInt(Rotate)+90;
					RotateSet = RotateSet%360;
					Image image = B00140PictControl.PictRotate(250,250,RotateSet,Selected);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage01.setIcon(Setimage);
					    ItemImage01Rotate.setText(""+RotateSet);
					}
				}
			}
		});
		
		ItemImage02RotateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String Selected = TB_ItemImagePath02.getText();
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					String Rotate = ItemImage02Rotate.getText();	if(null==Rotate) {Rotate = "0";}
					Rotate = B00020ToolsTextControl.num_only_String02(Rotate);
					if("".equals(Rotate)) {Rotate = "0";}
					int RotateSet = Integer.parseInt(Rotate)+90;
					RotateSet = RotateSet%360;
					Image image = B00140PictControl.PictRotate(250,250,RotateSet,Selected);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage02.setIcon(Setimage);
					    ItemImage02Rotate.setText(""+RotateSet);
					}
				}
			}
		});
		
		ItemImage03RotateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String Selected = TB_ItemImagePath03.getText();
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					String Rotate = ItemImage03Rotate.getText();	if(null==Rotate) {Rotate = "0";}
					Rotate = B00020ToolsTextControl.num_only_String02(Rotate);
					if("".equals(Rotate)) {Rotate = "0";}
					int RotateSet = Integer.parseInt(Rotate)+90;
					RotateSet = RotateSet%360;
					Image image = B00140PictControl.PictRotate(250,250,RotateSet,Selected);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage03.setIcon(Setimage);
					    ItemImage03Rotate.setText(""+RotateSet);
					}
				}
			}
		});
		
		ItemImage04RotateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String Selected = TB_ItemImagePath04.getText();
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					String Rotate = ItemImage04Rotate.getText();	if(null==Rotate) {Rotate = "0";}
					Rotate = B00020ToolsTextControl.num_only_String02(Rotate);
					if("".equals(Rotate)) {Rotate = "0";}
					int RotateSet = Integer.parseInt(Rotate)+90;
					RotateSet = RotateSet%360;
					Image image = B00140PictControl.PictRotate(250,250,RotateSet,Selected);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage04.setIcon(Setimage);
					    ItemImage04Rotate.setText(""+RotateSet);
					}
				}
			}
		});
		
		ItemImage05RotateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String Selected = TB_ItemImagePath05.getText();
				
				if(null!=Selected && B00140PictControl.PictCheck(Selected)) {
					String Rotate = ItemImage05Rotate.getText();	if(null==Rotate) {Rotate = "0";}
					Rotate = B00020ToolsTextControl.num_only_String02(Rotate);
					if("".equals(Rotate)) {Rotate = "0";}
					int RotateSet = Integer.parseInt(Rotate)+90;
					RotateSet = RotateSet%360;
					Image image = B00140PictControl.PictRotate(250,250,RotateSet,Selected);
					if(null!=image) {
						ImageIcon Setimage = new ImageIcon(image);
					    TB_ItemImage05.setIcon(Setimage);
					    ItemImage05Rotate.setText(""+RotateSet);
					}
				}
			}
		});
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClGpCd 			= B00100DefaultVariable.ClGpList[1][TB_ClGpCd.getSelectedIndex()];		//荷主グループコード
				String GetItemCd 			= TB_ItemCd.getText();				//商品コード
				String GetCLItemCd 			= TB_CLItemCd.getText();			//荷主商品コード
				String GetItemName01 		= TB_ItemName01.getText();			//商品名1
				String GetItemName02 		= TB_ItemName02.getText();			//商品名2
				String GetItemName03 		= TB_ItemName03.getText();			//商品名3
				String GetDeliveryTypeCd01 	= B00100DefaultVariable.DeliveryType01[1][TB_DeliveryTypeCd01.getSelectedIndex()];	//運送タイプコード01
				String GetDeliveryTypeCd02 	= B00100DefaultVariable.DeliveryType02[1][TB_DeliveryTypeCd02.getSelectedIndex()];	//運送タイプコード02
				String GetDeliveryTypeCd03 	= B00100DefaultVariable.DeliveryType03[1][TB_DeliveryTypeCd03.getSelectedIndex()];	//運送タイプコード03
				String GetDeliveryTypeCd04 	= B00100DefaultVariable.DeliveryType04[1][TB_DeliveryTypeCd04.getSelectedIndex()];	//運送タイプコード04
				String GetDeliveryTypeCd05 	= B00100DefaultVariable.DeliveryType05[1][TB_DeliveryTypeCd05.getSelectedIndex()];	//運送タイプコード05
				String GetPTMSCD 			= TB_PTMSCD.getText();				//基幹SYS商品コード
				String GetRecomendLoc 		= TB_RecomendLoc.getText();			//推奨ロケ
				String GetItemMDNo 			= TB_ItemMDNo.getText();			//商品型番
				String GetCategoryCd 		= TB_CategoryCd.getText();			//商品カテゴリCD
				String GetCategoryName 		= TB_CategoryName.getText();		//商品カテゴリ名
				String GetItemColorCd 		= TB_ItemColorCd.getText();			//商品カラーコード
				String GetItemColorName 	= TB_ItemColorName.getText();		//商品カラー名
				String GetItemSizeCd 		= TB_ItemSizeCd.getText();			//商品サイズコード
				String GetItemSizeName 		= TB_ItemSizeName.getText();		//商品サイス名
				String GetTildFG 			= B00100DefaultVariable.TildFG[1][TB_TildFG.getSelectedIndex()];	//温度区分
				String GetTildFGName 		= B00100DefaultVariable.TildFG[2][TB_TildFG.getSelectedIndex()];	//温度区分
				String GetExpDateHowLong 	= TB_ExpDateHowLong.getText();		//賞味期限日数
				String GetCtQty 			= TB_CtQty.getText();				//カートン入数
				String GetCsQty 			= TB_CsQty.getText();				//ケース入数
				String GetPlQty 			= TB_PlQty.getText();				//パレット入数
				String GetItemWeight 		= TB_ItemWeight.getText();			//商品重量
				String GetCtWeight 			= TB_CtWeight.getText();			//カートン重量
				String GetCsWeight 			= TB_CsWeight.getText();			//ケース重量
				String GetPlWeight 			= TB_PlWeight.getText();			//パレット重量
				String GetItemSize 			= TB_ItemSize.getText();			//商品サイズ
				String GetCtSize 			= TB_CtSize.getText();				//カートンサイズ
				String GetCsSize 			= TB_CsSize.getText();				//ケースサイズ
				String GetPlSize 			= TB_PlSize.getText();				//パレットサイズ
				String GetCtName 			= TB_CtName.getText();				//カートン商品名称
				String GetCsName 			= TB_CsName.getText();				//ケース商品名称
				String GetPlName 			= TB_PlName.getText();				//パレット商品名称
				String GetUnitName 			= TB_UnitName.getText();			//商品単位
				String GetCtUnitName 		= TB_CtUnitName.getText();			//カートン商品単位
				String GetCsUnitName 		= TB_CsUnitName.getText();			//ケース商品単位
				String GetPlUnitName 		= TB_PlUnitName.getText();			//パレット商品単位
				String GetJanCd 			= TB_JanCd.getText();				//JANCD
				String GetCtJan 			= TB_CtJan.getText();				//カートンバーコード
				String GetCsJan 			= TB_CsJan.getText();				//ケースバーコード
				String GetPlJan 			= TB_PlJan.getText();				//パレットバーコード
				String GetCom01 			= TB_Com01.getText();				//コメント1
				String GetCom02 			= TB_Com02.getText();				//コメント2
				String GetCom03 			= TB_Com03.getText();				//コメント3
				String GetDelFg 			= B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];	//削除フラグ
				
				String GetItemImagePath01 = TB_ItemImagePath01.getText();		//画像パス01 
				String GetItemImagePath02 = TB_ItemImagePath02.getText();		//画像パス02
				String GetItemImagePath03 = TB_ItemImagePath03.getText();		//画像パス03
				String GetItemImagePath04 = TB_ItemImagePath04.getText();		//画像パス04
				String GetItemImagePath05 = TB_ItemImagePath05.getText();		//画像パス05
				
				String GetItemImageEntryPath01 = TB_ItemImageEntryPath01.getText();			//現在マスタ画像パス01 
				String GetItemImageEntryPath02 = TB_ItemImageEntryPath02.getText();			//現在マスタ画像パス02 
				String GetItemImageEntryPath03 = TB_ItemImageEntryPath03.getText();			//現在マスタ画像パス03 
				String GetItemImageEntryPath04 = TB_ItemImageEntryPath04.getText();			//現在マスタ画像パス04 
				String GetItemImageEntryPath05 = TB_ItemImageEntryPath05.getText();			//現在マスタ画像パス04
				
				String GetItemImageSetPath01 = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_01.jpg";			//現在マスタ画像パス01 
				String GetItemImageSetPath02 = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_02.jpg";			//現在マスタ画像パス02 
				String GetItemImageSetPath03 = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_03.jpg";			//現在マスタ画像パス03 
				String GetItemImageSetPath04 = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_04.jpg";			//現在マスタ画像パス04 
				String GetItemImageSetPath05 = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_05.jpg";			//現在マスタ画像パス05 
				
				GetClGpCd 				= B00020ToolsTextControl.Trim(GetClGpCd);				//荷主グループコード
				GetItemCd 				= B00020ToolsTextControl.Trim(GetItemCd);				//商品コード
				GetCLItemCd 			= B00020ToolsTextControl.Trim(GetCLItemCd);				//荷主商品コード
				GetItemName01 			= B00020ToolsTextControl.Trim(GetItemName01);			//商品名1
				GetItemName02 			= B00020ToolsTextControl.Trim(GetItemName02);			//商品名2
				GetItemName03 			= B00020ToolsTextControl.Trim(GetItemName03);			//商品名3
				GetDeliveryTypeCd01 	= B00020ToolsTextControl.Trim(GetDeliveryTypeCd01);		//運送タイプコード01
				GetDeliveryTypeCd02 	= B00020ToolsTextControl.Trim(GetDeliveryTypeCd02);		//運送タイプコード02
				GetDeliveryTypeCd03 	= B00020ToolsTextControl.Trim(GetDeliveryTypeCd03);		//運送タイプコード03
				GetDeliveryTypeCd04 	= B00020ToolsTextControl.Trim(GetDeliveryTypeCd04);		//運送タイプコード04
				GetDeliveryTypeCd05 	= B00020ToolsTextControl.Trim(GetDeliveryTypeCd05);		//運送タイプコード05
				GetPTMSCD 				= B00020ToolsTextControl.Trim(GetPTMSCD);				//基幹SYS商品コード
				GetRecomendLoc 			= B00020ToolsTextControl.Trim(GetRecomendLoc);			//推奨ロケ
				GetItemMDNo 			= B00020ToolsTextControl.Trim(GetItemMDNo);				//商品型番
				GetCategoryCd 			= B00020ToolsTextControl.Trim(GetCategoryCd);			//商品カテゴリCD
				GetCategoryName 		= B00020ToolsTextControl.Trim(GetCategoryName);			//商品カテゴリ名
				GetItemColorCd 			= B00020ToolsTextControl.Trim(GetItemColorCd);			//商品カラーコード
				GetItemColorName 		= B00020ToolsTextControl.Trim(GetItemColorName);		//商品カラー名
				GetItemSizeCd 			= B00020ToolsTextControl.Trim(GetItemSizeCd);			//商品サイズコード
				GetItemSizeName 		= B00020ToolsTextControl.Trim(GetItemSizeName);			//商品サイス名
				GetTildFG 				= B00020ToolsTextControl.Trim(GetTildFG);				//温度区分
				GetTildFGName 			= B00020ToolsTextControl.Trim(GetTildFGName);			//温度区分名
				GetExpDateHowLong 		= B00020ToolsTextControl.Trim(GetExpDateHowLong);		//賞味期限日数
				GetCtQty 				= B00020ToolsTextControl.Trim(GetCtQty);				//カートン入数
				GetCsQty 				= B00020ToolsTextControl.Trim(GetCsQty);				//ケース入数
				GetPlQty 				= B00020ToolsTextControl.Trim(GetPlQty);				//パレット入数
				GetItemWeight 			= B00020ToolsTextControl.Trim(GetItemWeight);			//商品重量
				GetCtWeight 			= B00020ToolsTextControl.Trim(GetCtWeight);				//カートン重量
				GetCsWeight 			= B00020ToolsTextControl.Trim(GetCsWeight);				//ケース重量
				GetPlWeight 			= B00020ToolsTextControl.Trim(GetPlWeight);				//パレット重量
				GetItemSize 			= B00020ToolsTextControl.Trim(GetItemSize);				//商品サイズ
				GetCtSize 				= B00020ToolsTextControl.Trim(GetCtSize);				//カートンサイズ
				GetCsSize 				= B00020ToolsTextControl.Trim(GetCsSize);				//ケースサイズ
				GetPlSize 				= B00020ToolsTextControl.Trim(GetPlSize);				//パレットサイズ
				GetCtName 				= B00020ToolsTextControl.Trim(GetCtName);				//カートン商品名称
				GetCsName 				= B00020ToolsTextControl.Trim(GetCsName);				//ケース商品名称
				GetPlName 				= B00020ToolsTextControl.Trim(GetPlName);				//パレット商品名称
				GetUnitName 			= B00020ToolsTextControl.Trim(GetUnitName);				//商品単位
				GetCtUnitName 			= B00020ToolsTextControl.Trim(GetCtUnitName);			//カートン商品単位
				GetCsUnitName 			= B00020ToolsTextControl.Trim(GetCsUnitName);			//ケース商品単位
				GetPlUnitName 			= B00020ToolsTextControl.Trim(GetPlUnitName);			//パレット商品単位
				GetJanCd 				= B00020ToolsTextControl.Trim(GetJanCd);				//JANCD
				GetCtJan 				= B00020ToolsTextControl.Trim(GetCtJan);				//カートンバーコード
				GetCsJan 				= B00020ToolsTextControl.Trim(GetCsJan);				//ケースバーコード
				GetPlJan 				= B00020ToolsTextControl.Trim(GetPlJan);				//パレットバーコード
				GetCom01 				= B00020ToolsTextControl.Trim(GetCom01);				//コメント1
				GetCom02 				= B00020ToolsTextControl.Trim(GetCom02);				//コメント2
				GetCom03 				= B00020ToolsTextControl.Trim(GetCom03);				//コメント3
				GetDelFg 				= B00020ToolsTextControl.Trim(GetDelFg);				//削除フラグ
				
				GetItemImagePath01 		= B00020ToolsTextControl.Trim(GetItemImagePath01);		//画像パス01 
				GetItemImagePath02 		= B00020ToolsTextControl.Trim(GetItemImagePath02);		//画像パス02
				GetItemImagePath03 		= B00020ToolsTextControl.Trim(GetItemImagePath03);		//画像パス03
				GetItemImagePath04 		= B00020ToolsTextControl.Trim(GetItemImagePath04);		//画像パス04
				GetItemImagePath05 		= B00020ToolsTextControl.Trim(GetItemImagePath05);		//画像パス05
				
				GetItemImageEntryPath01 = B00020ToolsTextControl.Trim(GetItemImageEntryPath01);	//現在マスタ画像パス01 
				GetItemImageEntryPath02 = B00020ToolsTextControl.Trim(GetItemImageEntryPath02);	//現在マスタ画像パス02 
				GetItemImageEntryPath03 = B00020ToolsTextControl.Trim(GetItemImageEntryPath03);	//現在マスタ画像パス03 
				GetItemImageEntryPath04 = B00020ToolsTextControl.Trim(GetItemImageEntryPath04);	//現在マスタ画像パス04 
				GetItemImageEntryPath05 = B00020ToolsTextControl.Trim(GetItemImageEntryPath05);	//現在マスタ画像パス05
				
				GetExpDateHowLong 		= B00020ToolsTextControl.num_only_String02(GetExpDateHowLong);	//賞味期限日数
				GetCtQty 				= B00020ToolsTextControl.num_only_String02(GetCtQty);				//カートン入数
				GetCsQty 				= B00020ToolsTextControl.num_only_String02(GetCsQty);				//ケース入数
				GetPlQty 				= B00020ToolsTextControl.num_only_String02(GetPlQty);				//パレット入数
				GetItemWeight 			= B00020ToolsTextControl.num_only_String02(GetItemWeight);		//商品重量
				GetCtWeight 			= B00020ToolsTextControl.num_only_String02(GetCtWeight);			//カートン重量
				GetCsWeight 			= B00020ToolsTextControl.num_only_String02(GetCsWeight);			//ケース重量
				GetPlWeight 			= B00020ToolsTextControl.num_only_String02(GetPlWeight);			//パレット重量
				GetItemSize 			= B00020ToolsTextControl.num_only_String02(GetItemSize);			//商品サイズ
				GetCtSize 				= B00020ToolsTextControl.num_only_String02(GetCtSize);			//カートンサイズ
				GetCsSize 				= B00020ToolsTextControl.num_only_String02(GetCsSize);			//ケースサイズ
				GetPlSize 				= B00020ToolsTextControl.num_only_String02(GetPlSize);			//パレットサイズ
				
				if("".equals(GetExpDateHowLong)) {GetExpDateHowLong="0";}	//賞味期限日数
				if("".equals(GetCtQty)) {GetCtQty="0";}						//カートン入数
				if("".equals(GetCsQty)) {GetCsQty="0";}						//ケース入数
				if("".equals(GetPlQty)) {GetPlQty="0";}						//パレット入数
				if("".equals(GetItemWeight)) {GetItemWeight="0";}			//商品重量
				if("".equals(GetCtWeight)) {GetCtWeight="0";}				//カートン重量
				if("".equals(GetCsWeight)) {GetCsWeight="0";}				//ケース重量
				if("".equals(GetPlWeight)) {GetPlWeight="0";}				//パレット重量
				if("".equals(GetItemSize)) {GetItemSize="0";}				//商品サイズ
				if("".equals(GetCtSize)) {GetCtSize="0";}					//カートンサイズ
				if("".equals(GetCsSize)) {GetCsSize="0";}					//ケースサイズ
				if("".equals(GetPlSize)) {GetPlSize="0";}					//パレットサイズ
				
				B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth);
				B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth+"\\ItemImage");
				B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd);
				
				if(!"".equals(GetClGpCd)&&!"".equals(GetItemCd)&&!"".equals(GetItemName01)) {
					//新規に画像選択されていたら画像を保存 選択されていなければ現状のマスタのまま
					if("".equals(GetItemImagePath01)){		//画像パス01
						GetItemImageSetPath01 = GetItemImageEntryPath01;
					}else {
						if(B00140PictControl.PictCheck(GetItemImagePath01)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd)) {
							String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath01);
							String Rotate = ItemImage01Rotate.getText();	if(null==Rotate) {Rotate = "0";}
							int RotateSet = Integer.parseInt(Rotate);
							Image image = B00140PictControl.PictRotate(1024,1024,RotateSet,GetItemImagePath01);
							GetItemImageSetPath01 =  A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_01."+FileType;
							B00140PictControl.PictSave(image,FileType,GetItemImageSetPath01);
						}else {
							GetItemImageSetPath01 = GetItemImageEntryPath01;
						}
					}
					if("".equals(GetItemImagePath02)){		//画像パス02
						GetItemImageSetPath02 = GetItemImageEntryPath02;
					}else {
						if(B00140PictControl.PictCheck(GetItemImagePath02)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd)) {
							String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath02);
							String Rotate = ItemImage02Rotate.getText();	if(null==Rotate) {Rotate = "0";}
							int RotateSet = Integer.parseInt(Rotate);
							Image image = B00140PictControl.PictRotate(1024,1024,RotateSet,GetItemImagePath02);
							GetItemImageSetPath02 =  A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_02."+FileType;
							B00140PictControl.PictSave(image,FileType,GetItemImageSetPath02);
						}else {
							GetItemImageSetPath02 = GetItemImageEntryPath02;
						}
					}
					if("".equals(GetItemImagePath03)){		//画像パス03
						GetItemImageSetPath03 = GetItemImageEntryPath03;
					}else {
						if(B00140PictControl.PictCheck(GetItemImagePath03)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd)) {
							String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath03);
							String Rotate = ItemImage03Rotate.getText();	if(null==Rotate) {Rotate = "0";}
							int RotateSet = Integer.parseInt(Rotate);
							Image image = B00140PictControl.PictRotate(1024,1024,RotateSet,GetItemImagePath03);
							GetItemImageSetPath03 =  A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_03."+FileType;
							B00140PictControl.PictSave(image,FileType,GetItemImageSetPath03);
						}else {
							GetItemImageSetPath03 = GetItemImageEntryPath03;
						}
					}
					if("".equals(GetItemImagePath04)){		//画像パス04
						GetItemImageSetPath04 = GetItemImageEntryPath04;
					}else {
						if(B00140PictControl.PictCheck(GetItemImagePath04)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd)) {
							String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath04);
							String Rotate = ItemImage04Rotate.getText();	if(null==Rotate) {Rotate = "0";}
							int RotateSet = Integer.parseInt(Rotate);
							Image image = B00140PictControl.PictRotate(1024,1024,RotateSet,GetItemImagePath04);
							GetItemImageSetPath04 =  A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_04."+FileType;
							B00140PictControl.PictSave(image,FileType,GetItemImageSetPath04);
						}else {
							GetItemImageSetPath04 = GetItemImageEntryPath04;
						}
					}
					if("".equals(GetItemImagePath05)){		//画像パス05
						GetItemImageSetPath05 = GetItemImageEntryPath05;
					}else {
						if(B00140PictControl.PictCheck(GetItemImagePath05)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd)) {
							String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath05);
							String Rotate = ItemImage05Rotate.getText();	if(null==Rotate) {Rotate = "0";}
							int RotateSet = Integer.parseInt(Rotate);
							Image image = B00140PictControl.PictRotate(1024,1024,RotateSet,GetItemImagePath05);
							GetItemImageSetPath05 =  A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd+"\\"+GetItemCd+"_05."+FileType;
							B00140PictControl.PictSave(image,FileType,GetItemImageSetPath05);
						}else {
							GetItemImageSetPath05 = GetItemImageEntryPath05;
						}
					}
					
					String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
					
					String[][] ItemMstSetString = {
							  {"ClGpCd"				,"1","1",GetClGpCd}				//荷主グループコード
							 ,{"ItemCd"				,"1","1",GetItemCd}				//商品コード
							 ,{"CLItemCd"			,"1","1",GetCLItemCd}			//荷主商品コード
							 ,{"ItemName01"			,"1","1",GetItemName01}			//商品名1
							 ,{"ItemName02"			,"1","1",GetItemName02}			//商品名2
							 ,{"ItemName03"			,"1","1",GetItemName03}			//商品名3
							 ,{"ItemWeight"			,"1","1",GetItemWeight}			//商品重量
							 ,{"ItemSize"			,"1","1",GetItemSize}			//商品サイズ
							 ,{"DeliveryTypeCd"		,"1","1",GetDeliveryTypeCd01}	//運送タイプコード01
							 ,{"DeliveryTypeCd02"	,"1","1",GetDeliveryTypeCd02}	//運送タイプコード02
							 ,{"DeliveryTypeCd03"	,"1","1",GetDeliveryTypeCd03}	//運送タイプコード03
							 ,{"DeliveryTypeCd04"	,"1","1",GetDeliveryTypeCd04}	//運送タイプコード04
							 ,{"DeliveryTypeCd05"	,"1","1",GetDeliveryTypeCd05}	//運送タイプコード05
							 ,{"EntryDate"			,"1","0",now_dtm}				//データ登録日時
							 ,{"UpdateDate"			,"1","1",now_dtm}				//データ更新日時
							 ,{"EntryUser"			,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}			//登録者コード
							 ,{"UpdateUser"			,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}			//更新者コード
							 ,{"DelFg"				,"1","1",GetDelFg}				//削除フラグ
							 ,{"PTMSCD"				,"1","1",GetPTMSCD}				//基幹SYS商品コード
							 ,{"JanCd"				,"1","1",GetJanCd}				//JANCD
							 ,{"UnitName"			,"1","1",GetUnitName}			//商品単位
							};
					String tgt_table = "KM0060_ITEMMST";
					String[][] field_name = new String[ItemMstSetString.length][3];
					String[][] entry_data = new String[1][ItemMstSetString.length];
					String[] judg_field = new String[2];
					String[][] judg_data = new String[1][2];
					String TgtDB = "NYANKO";
					int non_msg_fg = 1;
		
					judg_field[0] = "ClGpCd";			//荷主グループコード
					judg_field[1] = "ItemCd";			//商品コード
		
					judg_data[0][0] = GetClGpCd;		//荷主グループコード
					judg_data[0][1] = GetItemCd;		//商品コード
					
					for(int i=0;i<ItemMstSetString.length;i++) {
						field_name[i][0] = ItemMstSetString[i][0];
						field_name[i][1] = ItemMstSetString[i][1];
						field_name[i][2] = ItemMstSetString[i][2];
						entry_data[0][i] = ItemMstSetString[i][3];
					}
					A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					String[][] ItemMstSubSetString = {
							 {"ClGpCd"			,"1","1",GetClGpCd}				//荷主グループコード
							,{"ItemCd"			,"1","1",GetItemCd}				//商品コード
							,{"CtQty"			,"1","1",GetCtQty}				//カートン入数
							,{"CsQty"			,"1","1",GetCsQty}				//ケース入数
							,{"PlQty"			,"1","1",GetPlQty}				//パレット入数
							,{"CtJan"			,"1","1",GetCtJan}				//カートンバーコード
							,{"CsJan"			,"1","1",GetCsJan}				//ケースバーコード
							,{"PlJan"			,"1","1",GetPlJan}				//パレットバーコード
							,{"CtName"			,"1","1",GetCtName}				//カートン商品名称
							,{"CsName"			,"1","1",GetCsName}				//ケース商品名称
							,{"PlName"			,"1","1",GetPlName}				//パレット商品名称
							,{"CtUnitName"		,"1","1",GetCtUnitName}			//カートン商品単位
							,{"CsUnitName"		,"1","1",GetCsUnitName}			//ケース商品単位
							,{"PlUnitName"		,"1","1",GetPlUnitName}			//パレット商品単位
							,{"CtWeight"		,"1","1",GetCtWeight}			//カートン重量
							,{"CsWeight"		,"1","1",GetCsWeight}			//ケース重量
							,{"PlWeight"		,"1","1",GetPlWeight}			//パレット重量
							,{"CtSize"			,"1","1",GetCtSize}				//カートンサイズ
							,{"CsSize"			,"1","1",GetCsSize}				//ケースサイズ
							,{"PlSize"			,"1","1",GetPlSize}				//パレットサイズ
							,{"RecomendLoc"		,"1","1",GetRecomendLoc}		//推奨ロケ
							,{"ItemMDNo"		,"1","1",GetItemMDNo}			//商品モデル番号（型番）
							,{"CategoryCd"		,"1","1",GetCategoryCd}			//商品カテゴリCD
							,{"CategoryName"	,"1","1",GetCategoryName}		//商品カテゴリ名
							,{"ItemColorCd"		,"1","1",GetItemColorCd}		//商品カラーコード
							,{"ItemColorName"	,"1","1",GetItemColorName}		//商品カラー名
							,{"ItemSizeCd"		,"1","1",GetItemSizeCd}			//商品サイズコード
							,{"ItemSizeName"	,"1","1",GetItemSizeName}		//商品サイス名
							,{"Com01"			,"1","1",GetCom01}				//コメント1
							,{"Com02"			,"1","1",GetCom02}				//コメント2
							,{"Com03"			,"1","1",GetCom03}				//コメント3
							,{"EntryDate"		,"1","0",now_dtm}				//データ登録日時
							,{"UpdateDate"		,"1","1",now_dtm}				//データ更新日時
							,{"EntryUser"		,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//登録者コード
							,{"UpdateUser"		,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//更新者コード
							,{"TildFG"			,"1","1",GetTildFG}				//温度区分
							,{"TildName"		,"1","1",GetTildFGName}			//温度区分名
							,{"PictPass01"		,"1","1",GetItemImageSetPath01}	//画像パス01
							,{"PictPass02"		,"1","1",GetItemImageSetPath02}	//画像パス02
							,{"PictPass03"		,"1","1",GetItemImageSetPath03}	//画像パス03
							,{"PictPass04"		,"1","1",GetItemImageSetPath04}	//画像パス04
							,{"PictPass05"		,"1","1",GetItemImageSetPath05}	//画像パス05
							,{"ExpDateHowLong"	,"1","1",GetExpDateHowLong}	//賞味期限日数
							};
					tgt_table = "KM0061_ITEMMSTSUB";
					field_name = new String[ItemMstSubSetString.length][3];
					entry_data = new String[1][ItemMstSubSetString.length];
					judg_field = new String[2];
					judg_data = new String[1][2];
					TgtDB = "NYANKO";
					non_msg_fg = 1;
		
					judg_field[0] = "ClGpCd";			//荷主グループコード
					judg_field[1] = "ItemCd";			//商品コード
		
					judg_data[0][0] = GetClGpCd;		//荷主グループコード
					judg_data[0][1] = GetItemCd;		//商品コード
					
					for(int i=0;i<ItemMstSubSetString.length;i++) {
						field_name[i][0] = ItemMstSubSetString[i][0];
						field_name[i][1] = ItemMstSubSetString[i][1];
						field_name[i][2] = ItemMstSubSetString[i][2];
						entry_data[0][i] = ItemMstSubSetString[i][3];
					}
					
					A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					ItemMstRenewAndCreate(0,0,GetClGpCd,GetItemCd);
				}else {
					JOptionPane.showMessageDialog(null, "商品CD・商品名01は必須です");
				}
			}
		});
		
		//商品コードフォーカス消失時の挙動
		TB_ItemCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				String GetClGpCd = B00100DefaultVariable.ClGpList[1][TB_ClGpCd.getSelectedIndex()];
				String GetItemCd = TB_ItemCd.getText();	if(null==GetItemCd) {GetItemCd="";}
				if(!"".equals(GetItemCd)) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					ItemMstRenewAndCreate(0,0,GetClGpCd,GetItemCd);
				}
			}
		});
		
		NewEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				ItemMstRenewAndCreate(0,0,"","");
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00080ItemMstSearch.ItemMstSearch(0, 0);
			}
		});
	}
}