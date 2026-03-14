import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		final JTextField  TB_RecomendLoc		= B00110FrameParts.JTextFieldSet( 710, 25,100,20,"",11,0);			//推奨ロケ
		final JTextField  TB_ItemMDNo			= B00110FrameParts.JTextFieldSet( 710, 50,100,20,"",11,0);			//商品型番
		final JTextField  TB_CategoryCd			= B00110FrameParts.JTextFieldSet( 710, 75,100,20,"",11,0);			//商品カテゴリCD
		final JTextField  TB_CategoryName		= B00110FrameParts.JTextFieldSet( 710,100,100,20,"",11,0);			//商品カテゴリ名
		
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
		
		/*
		
		
		JLabel LB_Com01					= B00110FrameParts.JLabelSet(  10, 25,100,20,"コメント1:"				,10,1);
		JLabel LB_Com02					= B00110FrameParts.JLabelSet(  10, 25,100,20,"コメント2:"				,10,1);
		JLabel LB_Com03					= B00110FrameParts.JLabelSet(  10, 25,100,20,"コメント3:"				,10,1);
		
		JLabel LB_PictPass01			= B00110FrameParts.JLabelSet(  10, 25,100,20,"画像パス01:"				,10,1);
		JLabel LB_PictPass02			= B00110FrameParts.JLabelSet(  10, 25,100,20,"画像パス02:"				,10,1);
		JLabel LB_PictPass03			= B00110FrameParts.JLabelSet(  10, 25,100,20,"画像パス03:"				,10,1);
		JLabel LB_PictPass04			= B00110FrameParts.JLabelSet(  10, 25,100,20,"画像パス04:"				,10,1);
		JLabel LB_PictPass05			= B00110FrameParts.JLabelSet(  10, 25,100,20,"画像パス05:"				,10,1);
		
		JLabel LB_EntryDate				= B00110FrameParts.JLabelSet(  10, 25,100,20,"データ登録日時:"			,10,1);
		JLabel LB_UpdateDate			= B00110FrameParts.JLabelSet(  10, 25,100,20,"データ更新日時:"			,10,1);
		JLabel LB_EntryUser				= B00110FrameParts.JLabelSet(  10, 25,100,20,"登録者コード:"			,10,1);
		JLabel LB_UpdateUser			= B00110FrameParts.JLabelSet(  10, 25,100,20,"更新者コード:"			,10,1);
		JLabel LB_DelFg					= B00110FrameParts.JLabelSet(  10, 25,100,20,"削除フラグ:"				,10,1);
		*/
		
		if(null==TgtClgpCd||"".equals(TgtClgpCd)) {TgtClgpCd = A00000Main.ClGp;}
		for(int i=0;i<B00100DefaultVariable.ClGpList[1].length;i++) {
			if(TgtClgpCd.equals(""+B00100DefaultVariable.ClGpList[0][i])) {
				TB_ClGpCd.setSelectedIndex(i);
			}
		}
		if("9".equals(A00000Main.LoginUserAuthorityFG)) {
			TB_ClGpCd.setEditable(true);
		}else {
			TB_ClGpCd.setEditable(false);
		}
		
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
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		main_fm.setVisible(true);
		
		
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