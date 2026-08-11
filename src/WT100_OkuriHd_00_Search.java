import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_OkuriHd_00_Search{
    static int SetX;
    static int SetY;
    static boolean RenewFg;

    static boolean MsViewMode;

    public static void OkuriHdSearch(int x,int y) {
        A00000_Main.LoginCheck();
        if(0==SetX) {SetX=100;}
        if(0==SetY) {SetY=100;}
        if(x==0) {x=SetX;}
        if(y==0) {y=SetY;}
        RenewFg = false;
        MsViewMode = false;

        final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1200,800,"Corgi00送り状検索　WT100_OkuriHd_00_Search","SP");
        JLabel userinfo = B100_FrameParts.UserInfo();
        JButton exit_btn = B100_FrameParts.ExitBtn();
        main_fm.add(userinfo);
        main_fm.add(exit_btn);

        //検索条件パネル
        JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,1160,300,"White");
        JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);

        /**********************************************************************
         * 左列：伝票・日付基本情報
         **********************************************************************/
        JLabel LB_SearchInvoiceWHCD  = B100_FrameParts.JLabelSet(  0, 25,100,20,"担当倉庫:",11,1);
        JLabel LB_SearchClCd         = B100_FrameParts.JLabelSet(  0, 50,100,20,"荷主:",11,1);
        JLabel LB_SearchClGpCD       = B100_FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:",10,1);
        JLabel LB_SearchOkuriNo      = B100_FrameParts.JLabelSet(  0,100,100,20,"送り状番号:",11,1);
        JLabel LB_SearchClDeliNo     = B100_FrameParts.JLabelSet(  0,125,100,20,"荷主管理番号:",10,1);
        JLabel LB_SearchPickupWhCd   = B100_FrameParts.JLabelSet(  0,150,100,20,"集荷倉庫:",11,1);
        JLabel LB_SearchPurposeFG    = B100_FrameParts.JLabelSet(  0,175,100,20,"目的:",11,1);
        JLabel LB_SearchPlanDate     = B100_FrameParts.JLabelSet(  0,200,100,20,"出荷予定日:",11,1);
        JLabel LB_SearchShipDate     = B100_FrameParts.JLabelSet(  0,225,100,20,"出荷実績日:",11,1);
        JLabel LB_SearchSPPlanDate   = B100_FrameParts.JLabelSet(  0,250,100,20,"着日指定:",11,1);
        JLabel LB_SearchSPDate       = B100_FrameParts.JLabelSet(  0,275,100,20,"着日実績:",11,1);

        final JComboBox TB_SearchInvoiceWHCD = B100_FrameParts.JComboBoxSet(100, 25,240,20,B100_DefaultVariable.SearchWhList[0],11);
        final JComboBox TB_SearchClCd        = B100_FrameParts.JComboBoxSet(100, 50,240,20,B100_DefaultVariable.SearchClList[0],11);
        final JComboBox TB_SearchClGpCD      = B100_FrameParts.JComboBoxSet(100, 75,240,20,B100_DefaultVariable.SearchClGpList[0],11);
        final JTextField TB_SearchOkuriNo    = B100_FrameParts.JTextFieldSet(100,100,100,20,"",11,0);
        final JTextField TB_SearchClDeliNo   = B100_FrameParts.JTextFieldSet(100,125,100,20,"",11,0);
        final JComboBox TB_SearchPickupWhCd  = B100_FrameParts.JComboBoxSet(100,150,240,20,B100_DefaultVariable.SearchWhList[0],11);
        final JComboBox TB_SearchPurposeFG   = B100_FrameParts.JComboBoxSet(100,175,240,20,B100_DefaultVariable.SearchPurposeList[0],11);
        final JFormattedTextField TB_SearchPlanDateStr   = B100_FrameParts.JFormattedTextFieldSet(100,200,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchPlanDateEnd   = B100_FrameParts.JFormattedTextFieldSet(230,200,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchShipDateStr   = B100_FrameParts.JFormattedTextFieldSet(100,225,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchShipDateEnd   = B100_FrameParts.JFormattedTextFieldSet(230,225,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchSPPlanDateStr = B100_FrameParts.JFormattedTextFieldSet(100,250,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchSPPlanDateEnd = B100_FrameParts.JFormattedTextFieldSet(230,250,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchSPDateStr     = B100_FrameParts.JFormattedTextFieldSet(100,275,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchSPDateEnd     = B100_FrameParts.JFormattedTextFieldSet(230,275,70,20,"",11,0,"YYYY/MM/DD");

        JLabel LB2_SearchOkuriNo    = B100_FrameParts.JLabelSet(200,100,40,20,"と一致",10,0);
        JLabel LB2_SearchClDeliNo   = B100_FrameParts.JLabelSet(200,125,40,20,"と一致",10,0);
        JLabel LB2_SearchPlanDate   = B100_FrameParts.JLabelSet(210,200,20,20,"～",10,2);
        JLabel LB2_SearchShipDate   = B100_FrameParts.JLabelSet(210,225,20,20,"～",10,2);
        JLabel LB2_SearchSPPlanDate = B100_FrameParts.JLabelSet(210,250,20,20,"～",10,2);
        JLabel LB2_SearchSPDate     = B100_FrameParts.JLabelSet(210,275,20,20,"～",10,2);
        
        /**********************************************************************
         * 中列：届先・荷送人・ステータス
         **********************************************************************/
        JLabel LB_SearchDeliCd       = B100_FrameParts.JLabelSet(340, 25,100,20,"届先コード:",11,1);
        JLabel LB_SearchClDeliCd     = B100_FrameParts.JLabelSet(340, 50,100,20,"荷主届先コード:",10,1);
        JLabel LB_SearchDeliName     = B100_FrameParts.JLabelSet(340, 75,100,20,"届先名:",11,1);
        JLabel LB_SearchDeliPost     = B100_FrameParts.JLabelSet(340,100,100,20,"届先郵便:",11,1);
        JLabel LB_SearchDeliAdd      = B100_FrameParts.JLabelSet(340,125,100,20,"届先住所:",11,1);
        JLabel LB_SearchDeliTel      = B100_FrameParts.JLabelSet(340,150,100,20,"届先電話:",11,1);
        JLabel LB_SearchNiokuriCd    = B100_FrameParts.JLabelSet(340,175,100,20,"荷送人コード:",10,1);
        JLabel LB_SearchNiokuriName  = B100_FrameParts.JLabelSet(340,200,100,20,"荷送人名:",11,1);
        JLabel LB_SearchStatus       = B100_FrameParts.JLabelSet(340,225,100,20,"運送状況:",11,1);
        JLabel LB_SearchWmsStatus    = B100_FrameParts.JLabelSet(340,250,100,20,"倉庫出荷状況:",10,1);
        JLabel LB_SearchCom          = B100_FrameParts.JLabelSet(340,275,100,20,"コメント:",11,1);

        final JTextField TB_SearchDeliCd      = B100_FrameParts.JTextFieldSet(440, 25,100,20,"",11,0);
        final JTextField TB_SearchClDeliCd    = B100_FrameParts.JTextFieldSet(440, 50,100,20,"",11,0);
        final JTextField TB_SearchDeliName    = B100_FrameParts.JTextFieldSet(440, 75,100,20,"",11,0);
        final JTextField TB_SearchDeliPost    = B100_FrameParts.JTextFieldSet(440,100,100,20,"",11,0);
        final JTextField TB_SearchDeliAdd     = B100_FrameParts.JTextFieldSet(440,125,100,20,"",11,0);
        final JTextField TB_SearchDeliTel     = B100_FrameParts.JTextFieldSet(440,150,100,20,"",11,0);
        final JTextField TB_SearchNiokuriCd   = B100_FrameParts.JTextFieldSet(440,175,100,20,"",11,0);
        final JTextField TB_SearchNiokuriName = B100_FrameParts.JTextFieldSet(440,200,100,20,"",11,0);
        final JComboBox TB_SearchStatus       = B100_FrameParts.JComboBoxSet( 440,225,100,20,B100_DefaultVariable.SearchStatusList[0],11);
        final JComboBox TB_SearchWmsStatus    = B100_FrameParts.JComboBoxSet(	440,250,100,20,B100_DefaultVariable.SearchWmsStatusList[0],11);
        final JTextField TB_SearchCom         = B100_FrameParts.JTextFieldSet(440,275,100,20,"",11,0);

        JLabel LB2_SearchDeliCd      = B100_FrameParts.JLabelSet(540, 25,40,20,"と一致",10,0);
        JLabel LB2_SearchClDeliCd    = B100_FrameParts.JLabelSet(540, 50,40,20,"と一致",10,0);
        JLabel LB2_SearchDeliName    = B100_FrameParts.JLabelSet(540, 75,40,20,"を含む",10,0);
        JLabel LB2_SearchDeliPost    = B100_FrameParts.JLabelSet(540,100,40,20,"を含む",10,0);
        JLabel LB2_SearchDeliAdd     = B100_FrameParts.JLabelSet(540,125,40,20,"を含む",10,0);
        JLabel LB2_SearchDeliTel     = B100_FrameParts.JLabelSet(540,150,40,20,"を含む",10,0);
        JLabel LB2_SearchNiokuriCd   = B100_FrameParts.JLabelSet(540,175,40,20,"と一致",10,0);
        JLabel LB2_SearchNiokuriName = B100_FrameParts.JLabelSet(540,200,40,20,"を含む",10,0);
        JLabel LB2_SearchCom         = B100_FrameParts.JLabelSet(540,275,40,20,"を含む",10,0);

        /**********************************************************************
         * 右列：運送条件・物量・商品明細条件
         **********************************************************************/
        JLabel LB_SearchDeliveryType = B100_FrameParts.JLabelSet(680, 25,100,20,"運送タイプ:",11,1);
        JLabel LB_SearchCodFG        = B100_FrameParts.JLabelSet(680, 50,100,20,"代引区分:",11,1);
        JLabel LB_SearchCodPayTotal  = B100_FrameParts.JLabelSet(680, 75,100,20,"代引収受額:",11,1);
        JLabel LB_SearchTotalQty     = B100_FrameParts.JLabelSet(680,100,100,20,"個口数:",11,1);
        JLabel LB_SearchTotalWeight  = B100_FrameParts.JLabelSet(680,125,100,20,"荷物重量(kg):",10,1);
        JLabel LB_SearchTotalSize    = B100_FrameParts.JLabelSet(680,150,100,20,"荷物サイズ:",11,1);
        JLabel LB_SearchMsItemCd     = B100_FrameParts.JLabelSet(680,175,100,20,"商品コード:",11,1);
        JLabel LB_SearchClItemCd     = B100_FrameParts.JLabelSet(680,200,100,20,"荷主商品コード:",10,1);
        JLabel LB_SearchMsItemName   = B100_FrameParts.JLabelSet(680,225,100,20,"商品名:",11,1);
        JLabel LB_SearchMsLot        = B100_FrameParts.JLabelSet(680,250,100,20,"ロット:",11,1);
        JLabel LB_SearchMsExpDate    = B100_FrameParts.JLabelSet(680,275,100,20,"賞味期限:",11,1);

        final JComboBox TB_SearchDeliveryTypeCd01 = B100_FrameParts.JComboBoxSet(780, 25,170,20,B100_DefaultVariable.SearchDeliveryType01[0],11);
        final JComboBox TB_SearchCodFG            = B100_FrameParts.JComboBoxSet(780, 50,170,20,B100_DefaultVariable.SearchCODList[0],11);
        final JFormattedTextField TB_SearchCodPayTotalMin = B100_FrameParts.JFormattedTextFieldSet(780, 75,70,20,"",11,1,"#,###");
        final JFormattedTextField TB_SearchCodPayTotalMax = B100_FrameParts.JFormattedTextFieldSet(910, 75,70,20,"",11,1,"#,###");
        final JFormattedTextField TB_SearchTotalQtyMin    = B100_FrameParts.JFormattedTextFieldSet(780,100,70,20,"",11,1,"#,###");
        final JFormattedTextField TB_SearchTotalQtyMax    = B100_FrameParts.JFormattedTextFieldSet(910,100,70,20,"",11,1,"#,###");
        final JFormattedTextField TB_SearchTotalWeightMin = B100_FrameParts.JFormattedTextFieldSet(780,125,70,20,"",11,1,"#,###.##");
        final JFormattedTextField TB_SearchTotalWeightMax = B100_FrameParts.JFormattedTextFieldSet(910,125,70,20,"",11,1,"#,###.##");
        final JFormattedTextField TB_SearchTotalSizeMin   = B100_FrameParts.JFormattedTextFieldSet(780,150,70,20,"",11,1,"#,###.##");
        final JFormattedTextField TB_SearchTotalSizeMax   = B100_FrameParts.JFormattedTextFieldSet(910,150,70,20,"",11,1,"#,###.##");
        final JTextField TB_SearchMsItemCd   = B100_FrameParts.JTextFieldSet(780,175,100,20,"",11,0);
        final JTextField TB_SearchClItemCd   = B100_FrameParts.JTextFieldSet(780,200,100,20,"",11,0);
        final JTextField TB_SearchMsItemName = B100_FrameParts.JTextFieldSet(780,225,100,20,"",11,0);
        final JTextField TB_SearchMsLot      = B100_FrameParts.JTextFieldSet(780,250,100,20,"",11,0);
        final JFormattedTextField TB_SearchMsExpDateStr = B100_FrameParts.JFormattedTextFieldSet(780,275,70,20,"",11,0,"YYYY/MM/DD");
        final JFormattedTextField TB_SearchMsExpDateEnd = B100_FrameParts.JFormattedTextFieldSet(910,275,70,20,"",11,0,"YYYY/MM/DD");

        JLabel LB2_SearchCodPayTotal  = B100_FrameParts.JLabelSet(870, 75,20,20,"～",10,2);
        JLabel LB2_SearchTotalQty     = B100_FrameParts.JLabelSet(870,100,20,20,"～",10,2);
        JLabel LB2_SearchTotalWeight  = B100_FrameParts.JLabelSet(870,125,20,20,"～",10,2);
        JLabel LB2_SearchTotalSize    = B100_FrameParts.JLabelSet(870,150,20,20,"～",10,2);
        JLabel LB2_SearchMsItemCd     = B100_FrameParts.JLabelSet(880,175,40,20,"と一致",10,0);
        JLabel LB2_SearchClItemCd     = B100_FrameParts.JLabelSet(880,200,40,20,"と一致",10,0);
        JLabel LB2_SearchMsItemName   = B100_FrameParts.JLabelSet(880,225,40,20,"を含む",10,0);
        JLabel LB2_SearchMsLot        = B100_FrameParts.JLabelSet(880,250,40,20,"と一致",10,0);
        JLabel LB2_SearchMsExpDate    = B100_FrameParts.JLabelSet(870,275,20,20,"～",10,2);

        
        //現在ログイン中の倉庫・荷主を初期選択
        TB_SearchInvoiceWHCD.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1],A00000_Main.ClWh,true));
        TB_SearchClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1],A00000_Main.ClCd,true));
        TB_SearchClGpCD.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1],A00000_Main.ClGp,true));

        //検索・条件クリア
        JButton SearchBtn       = B100_FrameParts.BtnSet(1050,275,100,20,"検索",11);
        JButton SearchCrearBtn  = B100_FrameParts.BtnSet(1050, 25,100,20,"条件クリア",10);

        //パネルへ追加
        PN_Search.add(PN_SearchLabel);
        PN_Search.add(LB_SearchInvoiceWHCD); PN_Search.add(TB_SearchInvoiceWHCD);
        PN_Search.add(LB_SearchClCd); PN_Search.add(TB_SearchClCd);
        PN_Search.add(LB_SearchClGpCD); PN_Search.add(TB_SearchClGpCD);
        PN_Search.add(LB_SearchOkuriNo); PN_Search.add(TB_SearchOkuriNo); PN_Search.add(LB2_SearchOkuriNo);
        PN_Search.add(LB_SearchClDeliNo); PN_Search.add(TB_SearchClDeliNo); PN_Search.add(LB2_SearchClDeliNo);
        PN_Search.add(LB_SearchPickupWhCd); PN_Search.add(TB_SearchPickupWhCd);
        PN_Search.add(LB_SearchPurposeFG); PN_Search.add(TB_SearchPurposeFG);
        PN_Search.add(LB_SearchPlanDate); PN_Search.add(TB_SearchPlanDateStr); PN_Search.add(LB2_SearchPlanDate); PN_Search.add(TB_SearchPlanDateEnd);
        PN_Search.add(LB_SearchShipDate); PN_Search.add(TB_SearchShipDateStr); PN_Search.add(LB2_SearchShipDate); PN_Search.add(TB_SearchShipDateEnd);
        PN_Search.add(LB_SearchSPPlanDate); PN_Search.add(TB_SearchSPPlanDateStr); PN_Search.add(LB2_SearchSPPlanDate); PN_Search.add(TB_SearchSPPlanDateEnd);
        PN_Search.add(LB_SearchSPDate); PN_Search.add(TB_SearchSPDateStr); PN_Search.add(LB2_SearchSPDate); PN_Search.add(TB_SearchSPDateEnd);

        PN_Search.add(LB_SearchDeliCd); PN_Search.add(TB_SearchDeliCd); PN_Search.add(LB2_SearchDeliCd);
        PN_Search.add(LB_SearchClDeliCd); PN_Search.add(TB_SearchClDeliCd); PN_Search.add(LB2_SearchClDeliCd);
        PN_Search.add(LB_SearchDeliName); PN_Search.add(TB_SearchDeliName); PN_Search.add(LB2_SearchDeliName);
        PN_Search.add(LB_SearchDeliPost); PN_Search.add(TB_SearchDeliPost); PN_Search.add(LB2_SearchDeliPost);
        PN_Search.add(LB_SearchDeliAdd); PN_Search.add(TB_SearchDeliAdd); PN_Search.add(LB2_SearchDeliAdd);
        PN_Search.add(LB_SearchDeliTel); PN_Search.add(TB_SearchDeliTel); PN_Search.add(LB2_SearchDeliTel);
        PN_Search.add(LB_SearchNiokuriCd); PN_Search.add(TB_SearchNiokuriCd); PN_Search.add(LB2_SearchNiokuriCd);
        PN_Search.add(LB_SearchNiokuriName); PN_Search.add(TB_SearchNiokuriName); PN_Search.add(LB2_SearchNiokuriName);
        PN_Search.add(LB_SearchStatus); PN_Search.add(TB_SearchStatus);
        PN_Search.add(LB_SearchWmsStatus); PN_Search.add(TB_SearchWmsStatus);
        PN_Search.add(LB_SearchCom); PN_Search.add(TB_SearchCom); PN_Search.add(LB2_SearchCom);

        PN_Search.add(LB_SearchDeliveryType); PN_Search.add(TB_SearchDeliveryTypeCd01);
        PN_Search.add(LB_SearchCodFG); PN_Search.add(TB_SearchCodFG);
        PN_Search.add(LB_SearchCodPayTotal); PN_Search.add(TB_SearchCodPayTotalMin); PN_Search.add(LB2_SearchCodPayTotal); PN_Search.add(TB_SearchCodPayTotalMax);
        PN_Search.add(LB_SearchTotalQty); PN_Search.add(TB_SearchTotalQtyMin); PN_Search.add(LB2_SearchTotalQty); PN_Search.add(TB_SearchTotalQtyMax);
        PN_Search.add(LB_SearchTotalWeight); PN_Search.add(TB_SearchTotalWeightMin); PN_Search.add(LB2_SearchTotalWeight); PN_Search.add(TB_SearchTotalWeightMax);
        PN_Search.add(LB_SearchTotalSize); PN_Search.add(TB_SearchTotalSizeMin); PN_Search.add(LB2_SearchTotalSize); PN_Search.add(TB_SearchTotalSizeMax);
        PN_Search.add(LB_SearchMsItemCd); PN_Search.add(TB_SearchMsItemCd); PN_Search.add(LB2_SearchMsItemCd);
        PN_Search.add(LB_SearchClItemCd); PN_Search.add(TB_SearchClItemCd); PN_Search.add(LB2_SearchClItemCd);
        PN_Search.add(LB_SearchMsItemName); PN_Search.add(TB_SearchMsItemName); PN_Search.add(LB2_SearchMsItemName);
        PN_Search.add(LB_SearchMsLot); PN_Search.add(TB_SearchMsLot); PN_Search.add(LB2_SearchMsLot);
        PN_Search.add(LB_SearchMsExpDate); PN_Search.add(TB_SearchMsExpDateStr); PN_Search.add(LB2_SearchMsExpDate); PN_Search.add(TB_SearchMsExpDateEnd);
        PN_Search.add(SearchBtn);
        PN_Search.add(SearchCrearBtn);
        main_fm.add(PN_Search);

        /**********************************************************************
         * 検索結果テーブル
         **********************************************************************/
        Object[][] RtOkuriHdRt = T100_OkuriHdRt.RtOkuriHdRt();
        String[] columnNames01 = new String[RtOkuriHdRt.length+1];
        columnNames01[0] = "Fg";
        for(int i=0;i<RtOkuriHdRt.length;i++) {
            columnNames01[1+(int)RtOkuriHdRt[i][1]] = ""+RtOkuriHdRt[i][3];
        }

        B100_TableControl.RenewTgt = new int[1];
        B100_TableControl.RenewTgt[0] = 0;
        final DefaultTableModel MainFmTableModel = new B100_TableControl.MyTableModel01(columnNames01,0);
        final JTable tb01 = new JTable(MainFmTableModel);
        tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
        tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));

        DefaultTableColumnModel columnModel01 = (DefaultTableColumnModel)tb01.getColumnModel();
        TableColumn column = columnModel01.getColumn(0);
        column.setPreferredWidth(30*A00000_Main.Mul/A00000_Main.Div);
        for(int i=0;i<RtOkuriHdRt.length;i++) {
            column = columnModel01.getColumn(1+(int)RtOkuriHdRt[i][1]);
            if("int".equals((String)RtOkuriHdRt[i][2]) || "float".equals((String)RtOkuriHdRt[i][2])) {
                column.setPreferredWidth(90*A00000_Main.Mul/A00000_Main.Div);
                column.setCellRenderer(B100_FrameParts.rightCellRenderer());
            }else {
                column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);
                column.setCellRenderer(B100_FrameParts.leftCellRenderer());
            }
        }

        JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,350,1160,275,tb01);
        main_fm.add(scpn01);

        main_fm.setVisible(true);
        RenewFg = true;

        /**********************************************************************
         * 検索ボタン押下時
         **********************************************************************/
        SearchBtn.addActionListener(new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                if(RenewFg) {
                    RenewFg = false;

                    int RowCount = MainFmTableModel.getRowCount();
                    for(int i=0;i<RowCount;i++) {
                        MainFmTableModel.removeRow(0);
                    }

                    String GetSearchInvoiceWHCD  = B100_DefaultVariable.SearchWhList[1][TB_SearchInvoiceWHCD.getSelectedIndex()];
                    String GetSearchClGpCD       = B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];
                    String GetSearchClCd         = B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];
                    String GetSearchOkuriNo      = TB_SearchOkuriNo.getText();
                    String GetSearchClDeliNo     = TB_SearchClDeliNo.getText();
                    String GetSearchPickupWhCd   = B100_DefaultVariable.SearchWhList[1][TB_SearchPickupWhCd.getSelectedIndex()];
                    String GetSearchPurposeFG    = B100_DefaultVariable.SearchPurposeList[1][TB_SearchPurposeFG.getSelectedIndex()];
                    String GetSearchPlanDateStr  = TB_SearchPlanDateStr.getText();
                    String GetSearchPlanDateEnd  = TB_SearchPlanDateEnd.getText();
                    String GetSearchShipDateStr  = TB_SearchShipDateStr.getText();
                    String GetSearchShipDateEnd  = TB_SearchShipDateEnd.getText();
                    String GetSearchSPPlanDateStr= TB_SearchSPPlanDateStr.getText();
                    String GetSearchSPPlanDateEnd= TB_SearchSPPlanDateEnd.getText();
                    String GetSearchSPDateStr    = TB_SearchSPDateStr.getText();
                    String GetSearchSPDateEnd    = TB_SearchSPDateEnd.getText();

                    String GetSearchDeliCd       = TB_SearchDeliCd.getText();
                    String GetSearchClDeliCd     = TB_SearchClDeliCd.getText();
                    String GetSearchDeliName     = TB_SearchDeliName.getText();
                    String GetSearchDeliPost     = TB_SearchDeliPost.getText();
                    String GetSearchDeliAdd      = TB_SearchDeliAdd.getText();
                    String GetSearchDeliTel      = TB_SearchDeliTel.getText();
                    String GetSearchNiokuriCd    = TB_SearchNiokuriCd.getText();
                    String GetSearchNiokuriName  = TB_SearchNiokuriName.getText();
                    String GetSearchStatus       = B100_DefaultVariable.SearchStatusList[1][TB_SearchStatus.getSelectedIndex()];
                    String GetSearchWmsStatus    = B100_DefaultVariable.SearchWmsStatusList[1][TB_SearchWmsStatus.getSelectedIndex()];
                    String GetSearchCom          = TB_SearchCom.getText();

                    String GetSearchDeliveryTypeCd01 = B100_DefaultVariable.SearchDeliveryType01[1][TB_SearchDeliveryTypeCd01.getSelectedIndex()];
                    String GetSearchCodFG        = B100_DefaultVariable.SearchCODList[1][TB_SearchCodFG.getSelectedIndex()];
                    String GetSearchCodPayTotalMin = TB_SearchCodPayTotalMin.getText();
                    String GetSearchCodPayTotalMax = TB_SearchCodPayTotalMax.getText();
                    String GetSearchTotalQtyMin    = TB_SearchTotalQtyMin.getText();
                    String GetSearchTotalQtyMax    = TB_SearchTotalQtyMax.getText();
                    String GetSearchTotalWeightMin = TB_SearchTotalWeightMin.getText();
                    String GetSearchTotalWeightMax = TB_SearchTotalWeightMax.getText();
                    String GetSearchTotalSizeMin   = TB_SearchTotalSizeMin.getText();
                    String GetSearchTotalSizeMax   = TB_SearchTotalSizeMax.getText();
                    String GetSearchMsItemCd       = TB_SearchMsItemCd.getText();
                    String GetSearchClItemCd       = TB_SearchClItemCd.getText();
                    String GetSearchMsItemName     = TB_SearchMsItemName.getText();
                    String GetSearchMsLot          = TB_SearchMsLot.getText();
                    String GetSearchMsExpDateStr   = TB_SearchMsExpDateStr.getText();
                    String GetSearchMsExpDateEnd   = TB_SearchMsExpDateEnd.getText();

                    Object[][] OkuriHdRt = OkuriHdRt(
                            GetSearchInvoiceWHCD, GetSearchClGpCD, GetSearchClCd,
                            GetSearchOkuriNo, GetSearchClDeliNo, GetSearchPickupWhCd, GetSearchPurposeFG,
                            GetSearchPlanDateStr, GetSearchShipDateStr, GetSearchSPPlanDateStr, GetSearchSPDateStr,
                            GetSearchPlanDateEnd, GetSearchShipDateEnd, GetSearchSPPlanDateEnd, GetSearchSPDateEnd,
                            GetSearchTotalWeightMin, GetSearchTotalSizeMin, GetSearchTotalQtyMin,
                            GetSearchTotalWeightMax, GetSearchTotalSizeMax, GetSearchTotalQtyMax,
                            GetSearchDeliveryTypeCd01, GetSearchCodFG, GetSearchCodPayTotalMin, GetSearchCodPayTotalMax,
                            GetSearchNiokuriCd, GetSearchNiokuriName,
                            GetSearchDeliCd, GetSearchClDeliCd, GetSearchDeliName, GetSearchDeliPost, GetSearchDeliAdd, GetSearchDeliTel,
                            GetSearchCom, GetSearchStatus, GetSearchWmsStatus,
                            GetSearchMsItemCd, GetSearchMsItemName, GetSearchClItemCd, GetSearchMsLot, GetSearchMsExpDateStr, GetSearchMsExpDateEnd);

                    if(0==OkuriHdRt.length) {
                        B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
                    }else {
                        for(int i=0;i<OkuriHdRt.length;i++) {
                            Object[] SetOb = new Object[OkuriHdRt[i].length+1];
                            SetOb[0] = false;
                            for(int i01=0;i01<OkuriHdRt[i].length;i01++) {
                                SetOb[i01+1] = ""+OkuriHdRt[i][i01];
                            }
                            MainFmTableModel.addRow(SetOb);
                        }
                        B100_TableControl.AddSortON(tb01,MainFmTableModel);
                    }
                    RenewFg = true;
                }
            }
        });

        /**********************************************************************
         * 条件クリア
         **********************************************************************/
        SearchCrearBtn.addActionListener(new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                if(RenewFg) {
                    RenewFg = false;
                    TB_SearchInvoiceWHCD.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1],A00000_Main.ClWh,true));
                    TB_SearchClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1],A00000_Main.ClCd,true));
                    TB_SearchClGpCD.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1],A00000_Main.ClGp,true));
                    TB_SearchPickupWhCd.setSelectedIndex(0);
                    TB_SearchPurposeFG.setSelectedIndex(0);
                    TB_SearchOkuriNo.setText("");
                    TB_SearchClDeliNo.setText("");
                    TB_SearchPlanDateStr.setText(""); TB_SearchPlanDateEnd.setText("");
                    TB_SearchShipDateStr.setText(""); TB_SearchShipDateEnd.setText("");
                    TB_SearchSPPlanDateStr.setText(""); TB_SearchSPPlanDateEnd.setText("");
                    TB_SearchSPDateStr.setText(""); TB_SearchSPDateEnd.setText("");
                    TB_SearchDeliCd.setText(""); TB_SearchClDeliCd.setText(""); TB_SearchDeliName.setText("");
                    TB_SearchDeliPost.setText(""); TB_SearchDeliAdd.setText(""); TB_SearchDeliTel.setText("");
                    TB_SearchNiokuriCd.setText(""); TB_SearchNiokuriName.setText("");
                    TB_SearchStatus.setSelectedIndex(0); TB_SearchWmsStatus.setSelectedIndex(0); TB_SearchCom.setText("");
                    TB_SearchDeliveryTypeCd01.setSelectedIndex(0); TB_SearchCodFG.setSelectedIndex(0);
                    TB_SearchCodPayTotalMin.setText(""); TB_SearchCodPayTotalMax.setText("");
                    TB_SearchTotalQtyMin.setText(""); TB_SearchTotalQtyMax.setText("");
                    TB_SearchTotalWeightMin.setText(""); TB_SearchTotalWeightMax.setText("");
                    TB_SearchTotalSizeMin.setText(""); TB_SearchTotalSizeMax.setText("");
                    TB_SearchMsItemCd.setText(""); TB_SearchClItemCd.setText(""); TB_SearchMsItemName.setText(""); TB_SearchMsLot.setText("");
                    TB_SearchMsExpDateStr.setText(""); TB_SearchMsExpDateEnd.setText("");
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
                A00001_WorkMain.WorkMain(0,0);
            }
        });
    }

    /**************************************************************************
     * 画面検索条件をT100_OkuriHdRt用ArrayListへ変換して検索
     * 画面未採用条件は空配列で渡す。
     **************************************************************************/
    private static Object[][] OkuriHdRt(
            String GetSearchInvoiceWHCD, String GetSearchClGpCD, String GetSearchClCd,
            String GetSearchOkuriNo, String GetSearchClDeliNo, String GetSearchPickupWhCd, String GetSearchPurposeFG,
            String GetSearchPlanDateStr, String GetSearchShipDateStr, String GetSearchSPPlanDateStr, String GetSearchSPDateStr,
            String GetSearchPlanDateEnd, String GetSearchShipDateEnd, String GetSearchSPPlanDateEnd, String GetSearchSPDateEnd,
            String GetSearchTotalWeightMin, String GetSearchTotalSizeMin, String GetSearchTotalQtyMin,
            String GetSearchTotalWeightMax, String GetSearchTotalSizeMax, String GetSearchTotalQtyMax,
            String GetSearchDeliveryTypeCd01, String GetSearchCodFG, String GetSearchCodPayTotalMin, String GetSearchCodPayTotalMax,
            String GetSearchNiokuriCd, String GetSearchNiokuriName,
            String GetSearchDeliCd, String GetSearchClDeliCd, String GetSearchDeliName, String GetSearchDeliPost, String GetSearchDeliAdd, String GetSearchDeliTel,
            String GetSearchCom, String GetSearchStatus, String GetSearchWmsStatus,
            String GetSearchMsItemCd, String GetSearchMsItemName, String GetSearchClItemCd, String GetSearchMsLot,
            String GetSearchMsExpDateStr, String GetSearchMsExpDateEnd) {

        GetSearchInvoiceWHCD = B100_TextControl.Trim(GetSearchInvoiceWHCD);
        GetSearchClGpCD = B100_TextControl.Trim(GetSearchClGpCD);
        GetSearchClCd = B100_TextControl.Trim(GetSearchClCd);
        GetSearchOkuriNo = B100_TextControl.Trim(GetSearchOkuriNo);
        GetSearchClDeliNo = B100_TextControl.Trim(GetSearchClDeliNo);
        GetSearchPickupWhCd = B100_TextControl.Trim(GetSearchPickupWhCd);
        GetSearchPurposeFG = B100_TextControl.Trim(GetSearchPurposeFG);
        GetSearchPlanDateStr = B100_TextControl.Trim(GetSearchPlanDateStr);
        GetSearchShipDateStr = B100_TextControl.Trim(GetSearchShipDateStr);
        GetSearchSPPlanDateStr = B100_TextControl.Trim(GetSearchSPPlanDateStr);
        GetSearchSPDateStr = B100_TextControl.Trim(GetSearchSPDateStr);
        GetSearchPlanDateEnd = B100_TextControl.Trim(GetSearchPlanDateEnd);
        GetSearchShipDateEnd = B100_TextControl.Trim(GetSearchShipDateEnd);
        GetSearchSPPlanDateEnd = B100_TextControl.Trim(GetSearchSPPlanDateEnd);
        GetSearchSPDateEnd = B100_TextControl.Trim(GetSearchSPDateEnd);
        GetSearchTotalWeightMin = B100_TextControl.Trim(GetSearchTotalWeightMin);
        GetSearchTotalSizeMin = B100_TextControl.Trim(GetSearchTotalSizeMin);
        GetSearchTotalQtyMin = B100_TextControl.Trim(GetSearchTotalQtyMin);
        GetSearchTotalWeightMax = B100_TextControl.Trim(GetSearchTotalWeightMax);
        GetSearchTotalSizeMax = B100_TextControl.Trim(GetSearchTotalSizeMax);
        GetSearchTotalQtyMax = B100_TextControl.Trim(GetSearchTotalQtyMax);
        GetSearchDeliveryTypeCd01 = B100_TextControl.Trim(GetSearchDeliveryTypeCd01);
        GetSearchCodFG = B100_TextControl.Trim(GetSearchCodFG);
        GetSearchCodPayTotalMin = B100_TextControl.Trim(GetSearchCodPayTotalMin);
        GetSearchCodPayTotalMax = B100_TextControl.Trim(GetSearchCodPayTotalMax);
        GetSearchNiokuriCd = B100_TextControl.Trim(GetSearchNiokuriCd);
        GetSearchNiokuriName = B100_TextControl.Trim(GetSearchNiokuriName);
        GetSearchDeliCd = B100_TextControl.Trim(GetSearchDeliCd);
        GetSearchClDeliCd = B100_TextControl.Trim(GetSearchClDeliCd);
        GetSearchDeliName = B100_TextControl.Trim(GetSearchDeliName);
        GetSearchDeliPost = B100_TextControl.Trim(GetSearchDeliPost);
        GetSearchDeliAdd = B100_TextControl.Trim(GetSearchDeliAdd);
        GetSearchDeliTel = B100_TextControl.Trim(GetSearchDeliTel);
        GetSearchCom = B100_TextControl.Trim(GetSearchCom);
        GetSearchStatus = B100_TextControl.Trim(GetSearchStatus);
        GetSearchWmsStatus = B100_TextControl.Trim(GetSearchWmsStatus);
        GetSearchMsItemCd = B100_TextControl.Trim(GetSearchMsItemCd);
        GetSearchMsItemName = B100_TextControl.Trim(GetSearchMsItemName);
        GetSearchClItemCd = B100_TextControl.Trim(GetSearchClItemCd);
        GetSearchMsLot = B100_TextControl.Trim(GetSearchMsLot);
        GetSearchMsExpDateStr = B100_TextControl.Trim(GetSearchMsExpDateStr);
        GetSearchMsExpDateEnd = B100_TextControl.Trim(GetSearchMsExpDateEnd);

        if(!"".equals(GetSearchTotalWeightMin)){GetSearchTotalWeightMin=B100_TextControl.num_only_String02(GetSearchTotalWeightMin);}
        if(!"".equals(GetSearchTotalWeightMax)){GetSearchTotalWeightMax=B100_TextControl.num_only_String02(GetSearchTotalWeightMax);}
        if(!"".equals(GetSearchTotalSizeMin)){GetSearchTotalSizeMin=B100_TextControl.num_only_String02(GetSearchTotalSizeMin);}
        if(!"".equals(GetSearchTotalSizeMax)){GetSearchTotalSizeMax=B100_TextControl.num_only_String02(GetSearchTotalSizeMax);}
        if(!"".equals(GetSearchTotalQtyMin)){GetSearchTotalQtyMin=B100_TextControl.num_only_String02(GetSearchTotalQtyMin);}
        if(!"".equals(GetSearchTotalQtyMax)){GetSearchTotalQtyMax=B100_TextControl.num_only_String02(GetSearchTotalQtyMax);}
        if(!"".equals(GetSearchCodFG)){GetSearchCodFG=B100_TextControl.num_only_String02(GetSearchCodFG);}
        if(!"".equals(GetSearchCodPayTotalMin)){GetSearchCodPayTotalMin=B100_TextControl.num_only_String02(GetSearchCodPayTotalMin);}
        if(!"".equals(GetSearchCodPayTotalMax)){GetSearchCodPayTotalMax=B100_TextControl.num_only_String02(GetSearchCodPayTotalMax);}
        if(!"".equals(GetSearchStatus)){GetSearchStatus=B100_TextControl.num_only_String02(GetSearchStatus);}
        if(!"".equals(GetSearchWmsStatus)){GetSearchWmsStatus=B100_TextControl.num_only_String02(GetSearchWmsStatus);}

        ArrayList<String> SearchInvoiceWHCD = new ArrayList<String>();
        ArrayList<String> SearchClGpCD = new ArrayList<String>();
        ArrayList<String> SearchClCd = new ArrayList<String>();
        ArrayList<String> SearchOkuriNo = new ArrayList<String>();
        ArrayList<String> SearchClDeliNo = new ArrayList<String>();
        ArrayList<String> SearchPickupWhCd = new ArrayList<String>();
        ArrayList<String> SearchPurposeFG = new ArrayList<String>();
        ArrayList<String> SearchPlanDateStr = new ArrayList<String>();
        ArrayList<String> SearchShipDateStr = new ArrayList<String>();
        ArrayList<String> SearchSPPlanDateStr = new ArrayList<String>();
        ArrayList<String> SearchSPDateStr = new ArrayList<String>();
        ArrayList<String> SearchPlanDateEnd = new ArrayList<String>();
        ArrayList<String> SearchShipDateEnd = new ArrayList<String>();
        ArrayList<String> SearchSPPlanDateEnd = new ArrayList<String>();
        ArrayList<String> SearchSPDateEnd = new ArrayList<String>();
        ArrayList<Float> SearchTotalWeightMin = new ArrayList<Float>();
        ArrayList<Float> SearchTotalSizeMin = new ArrayList<Float>();
        ArrayList<Integer> SearchTotalQtyMin = new ArrayList<Integer>();
        ArrayList<Float> SearchTotalWeightMax = new ArrayList<Float>();
        ArrayList<Float> SearchTotalSizeMax = new ArrayList<Float>();
        ArrayList<Integer> SearchTotalQtyMax = new ArrayList<Integer>();
        ArrayList<String> SearchDeliveryTypeCd = new ArrayList<String>();
        ArrayList<String> SearchDeliveryTypeCd02 = new ArrayList<String>();
        ArrayList<String> SearchDeliveryTypeCd03 = new ArrayList<String>();
        ArrayList<String> SearchDeliveryTypeCd04 = new ArrayList<String>();
        ArrayList<String> SearchDeliveryTypeCd05 = new ArrayList<String>();
        ArrayList<Integer> SearchCodFG = new ArrayList<Integer>();
        ArrayList<Integer> SearchCodPayTotalMin = new ArrayList<Integer>();
        ArrayList<Integer> SearchCodPayTotalMax = new ArrayList<Integer>();
        ArrayList<Integer> SearchChildrenFG = new ArrayList<Integer>();
        ArrayList<String> SearchParentOkuriNo = new ArrayList<String>();
        ArrayList<String> SearchNiokuriCd = new ArrayList<String>();
        ArrayList<String> SearchNiokuriDepartmentCd = new ArrayList<String>();
        ArrayList<String> SearchNiokuriName = new ArrayList<String>();
        ArrayList<String> SearchNiokuriPost = new ArrayList<String>();
        ArrayList<String> SearchNiokuriAdd = new ArrayList<String>();
        ArrayList<String> SearchNioKuriTel = new ArrayList<String>();
        ArrayList<String> SearchNioKuriFax = new ArrayList<String>();
        ArrayList<String> SearchNioKuriMail = new ArrayList<String>();
        ArrayList<String> SearchNiokuriMunicCd = new ArrayList<String>();
        ArrayList<String> SearchDeliCd = new ArrayList<String>();
        ArrayList<String> SearchClDeliCd = new ArrayList<String>();
        ArrayList<String> SearchDeliDepartmentCd = new ArrayList<String>();
        ArrayList<String> SearchDeliName = new ArrayList<String>();
        ArrayList<String> SearchDeliPost = new ArrayList<String>();
        ArrayList<String> SearchDeliAdd = new ArrayList<String>();
        ArrayList<String> SearchDeliTel = new ArrayList<String>();
        ArrayList<String> SearchDeliFax = new ArrayList<String>();
        ArrayList<String> SearchDeliMail = new ArrayList<String>();
        ArrayList<String> SearchDeliMunicCd = new ArrayList<String>();
        ArrayList<String> SearchCom = new ArrayList<String>();
        ArrayList<Integer> SearchStatus = new ArrayList<Integer>();
        ArrayList<Integer> SearchFeeFixFG = new ArrayList<Integer>();
        ArrayList<Integer> SearchReceiptStampFG = new ArrayList<Integer>();
        ArrayList<Integer> SearchInvoiceStatus = new ArrayList<Integer>();
        ArrayList<Integer> SearchWithOutTaxTotalMin = new ArrayList<Integer>();
        ArrayList<Integer> SearchTotalFeeMin = new ArrayList<Integer>();
        ArrayList<String> SearchFeeFixDateStr = new ArrayList<String>();
        ArrayList<String> SearchReceiptStampDateStr = new ArrayList<String>();
        ArrayList<String> SearchEntryDateStr = new ArrayList<String>();
        ArrayList<String> SearchUpdateDateStr = new ArrayList<String>();
        ArrayList<Integer> SearchWithOutTaxTotalMax = new ArrayList<Integer>();
        ArrayList<Integer> SearchTotalFeeMax = new ArrayList<Integer>();
        ArrayList<String> SearchFeeFixDateEnd = new ArrayList<String>();
        ArrayList<String> SearchReceiptStampDateEnd = new ArrayList<String>();
        ArrayList<String> SearchEntryDateEnd = new ArrayList<String>();
        ArrayList<String> SearchUpdateDateEnd = new ArrayList<String>();
        ArrayList<String> SearchEntryUser = new ArrayList<String>();
        ArrayList<String> SearchUpdateUser = new ArrayList<String>();
        ArrayList<String> SearchEntryPG = new ArrayList<String>();
        ArrayList<String> SearchUpdatePG = new ArrayList<String>();
        ArrayList<String> SearchUseFeeBasePtCd = new ArrayList<String>();
        ArrayList<Integer> SearchWmsStatus = new ArrayList<Integer>();
        ArrayList<String> SearchWmsShipDateStr = new ArrayList<String>();
        ArrayList<String> SearchWmsShipDateEnd = new ArrayList<String>();
        ArrayList<String> SearchCourseGpCd = new ArrayList<String>();
        ArrayList<String> SearchCourseCD = new ArrayList<String>();
        ArrayList<Integer> SearchCourseCDEda = new ArrayList<Integer>();
        ArrayList<String> SearchPitGrp = new ArrayList<String>();
        ArrayList<String> SearchPit = new ArrayList<String>();
        ArrayList<String> SearchMsItemCd = new ArrayList<String>();
        ArrayList<String> SearchMsItemName = new ArrayList<String>();
        ArrayList<String> SearchClItemCd = new ArrayList<String>();
        ArrayList<String> SearchMsCategoryCd = new ArrayList<String>();
        ArrayList<String> SearchMsCategoryName = new ArrayList<String>();
        ArrayList<String> SearchMsTildFG = new ArrayList<String>();
        ArrayList<String> SearchMsTildName = new ArrayList<String>();
        ArrayList<String> SearchMsLot = new ArrayList<String>();
        ArrayList<String> SearchMsExpDateStr = new ArrayList<String>();
        ArrayList<String> SearchMsExpDateEnd = new ArrayList<String>();
        ArrayList<Integer> SearchMsPackingType = new ArrayList<Integer>();

        if(!"".equals(GetSearchInvoiceWHCD)){SearchInvoiceWHCD.add(GetSearchInvoiceWHCD);}
        if(!"".equals(GetSearchClGpCD)){SearchClGpCD.add(GetSearchClGpCD);}
        if(!"".equals(GetSearchClCd)){SearchClCd.add(GetSearchClCd);}
        if(!"".equals(GetSearchOkuriNo)){SearchOkuriNo.add(GetSearchOkuriNo);}
        if(!"".equals(GetSearchClDeliNo)){SearchClDeliNo.add(GetSearchClDeliNo);}
        if(!"".equals(GetSearchPickupWhCd)){SearchPickupWhCd.add(GetSearchPickupWhCd);}
        if(!"".equals(GetSearchPurposeFG)){SearchPurposeFG.add(GetSearchPurposeFG);}
        if(!"".equals(GetSearchPlanDateStr)){SearchPlanDateStr.add(GetSearchPlanDateStr);}
        if(!"".equals(GetSearchShipDateStr)){SearchShipDateStr.add(GetSearchShipDateStr);}
        if(!"".equals(GetSearchSPPlanDateStr)){SearchSPPlanDateStr.add(GetSearchSPPlanDateStr);}
        if(!"".equals(GetSearchSPDateStr)){SearchSPDateStr.add(GetSearchSPDateStr);}
        if(!"".equals(GetSearchPlanDateEnd)){SearchPlanDateEnd.add(GetSearchPlanDateEnd);}
        if(!"".equals(GetSearchShipDateEnd)){SearchShipDateEnd.add(GetSearchShipDateEnd);}
        if(!"".equals(GetSearchSPPlanDateEnd)){SearchSPPlanDateEnd.add(GetSearchSPPlanDateEnd);}
        if(!"".equals(GetSearchSPDateEnd)){SearchSPDateEnd.add(GetSearchSPDateEnd);}
        if(!"".equals(GetSearchTotalWeightMin)){SearchTotalWeightMin.add(Float.parseFloat(GetSearchTotalWeightMin));}
        if(!"".equals(GetSearchTotalSizeMin)){SearchTotalSizeMin.add(Float.parseFloat(GetSearchTotalSizeMin));}
        if(!"".equals(GetSearchTotalQtyMin)){SearchTotalQtyMin.add((int)Float.parseFloat(GetSearchTotalQtyMin));}
        if(!"".equals(GetSearchTotalWeightMax)){SearchTotalWeightMax.add(Float.parseFloat(GetSearchTotalWeightMax));}
        if(!"".equals(GetSearchTotalSizeMax)){SearchTotalSizeMax.add(Float.parseFloat(GetSearchTotalSizeMax));}
        if(!"".equals(GetSearchTotalQtyMax)){SearchTotalQtyMax.add((int)Float.parseFloat(GetSearchTotalQtyMax));}
        if(!"".equals(GetSearchDeliveryTypeCd01)){SearchDeliveryTypeCd.add(GetSearchDeliveryTypeCd01);}
        if(!"".equals(GetSearchCodFG)){SearchCodFG.add((int)Float.parseFloat(GetSearchCodFG));}
        if(!"".equals(GetSearchCodPayTotalMin)){SearchCodPayTotalMin.add((int)Float.parseFloat(GetSearchCodPayTotalMin));}
        if(!"".equals(GetSearchCodPayTotalMax)){SearchCodPayTotalMax.add((int)Float.parseFloat(GetSearchCodPayTotalMax));}
        if(!"".equals(GetSearchNiokuriCd)){SearchNiokuriCd.add(GetSearchNiokuriCd);}
        if(!"".equals(GetSearchNiokuriName)){SearchNiokuriName.add(GetSearchNiokuriName);}
        if(!"".equals(GetSearchDeliCd)){SearchDeliCd.add(GetSearchDeliCd);}
        if(!"".equals(GetSearchClDeliCd)){SearchClDeliCd.add(GetSearchClDeliCd);}
        if(!"".equals(GetSearchDeliName)){SearchDeliName.add(GetSearchDeliName);}
        if(!"".equals(GetSearchDeliPost)){SearchDeliPost.add(GetSearchDeliPost);}
        if(!"".equals(GetSearchDeliAdd)){SearchDeliAdd.add(GetSearchDeliAdd);}
        if(!"".equals(GetSearchDeliTel)){SearchDeliTel.add(GetSearchDeliTel);}
        if(!"".equals(GetSearchCom)){SearchCom.add(GetSearchCom);}
        if(!"".equals(GetSearchStatus)){SearchStatus.add((int)Float.parseFloat(GetSearchStatus));}
        if(!"".equals(GetSearchWmsStatus)){SearchWmsStatus.add((int)Float.parseFloat(GetSearchWmsStatus));}
        if(!"".equals(GetSearchMsItemCd)){SearchMsItemCd.add(GetSearchMsItemCd);}
        if(!"".equals(GetSearchMsItemName)){SearchMsItemName.add(GetSearchMsItemName);}
        if(!"".equals(GetSearchClItemCd)){SearchClItemCd.add(GetSearchClItemCd);}
        if(!"".equals(GetSearchMsLot)){SearchMsLot.add(GetSearchMsLot);}
        if(!"".equals(GetSearchMsExpDateStr)){SearchMsExpDateStr.add(GetSearchMsExpDateStr);}
        if(!"".equals(GetSearchMsExpDateEnd)){SearchMsExpDateEnd.add(GetSearchMsExpDateEnd);}
        

        boolean AllSearch = false;
        return T100_OkuriHdRt.OkuriHdRt(
                SearchInvoiceWHCD, SearchClGpCD, SearchClCd, SearchOkuriNo, SearchClDeliNo, SearchPickupWhCd, SearchPurposeFG,
                SearchPlanDateStr, SearchShipDateStr, SearchSPPlanDateStr, SearchSPDateStr,
                SearchPlanDateEnd, SearchShipDateEnd, SearchSPPlanDateEnd, SearchSPDateEnd,
                SearchTotalWeightMin, SearchTotalSizeMin, SearchTotalQtyMin,
                SearchTotalWeightMax, SearchTotalSizeMax, SearchTotalQtyMax,
                SearchDeliveryTypeCd, SearchDeliveryTypeCd02, SearchDeliveryTypeCd03, SearchDeliveryTypeCd04, SearchDeliveryTypeCd05,
                SearchCodFG, SearchCodPayTotalMin, SearchCodPayTotalMax,
                SearchChildrenFG, SearchParentOkuriNo,
                SearchNiokuriCd, SearchNiokuriDepartmentCd, SearchNiokuriName, SearchNiokuriPost, SearchNiokuriAdd, SearchNioKuriTel, SearchNioKuriFax, SearchNioKuriMail, SearchNiokuriMunicCd,
                SearchDeliCd, SearchClDeliCd, SearchDeliDepartmentCd, SearchDeliName, SearchDeliPost, SearchDeliAdd, SearchDeliTel, SearchDeliFax, SearchDeliMail, SearchDeliMunicCd,
                SearchCom, SearchStatus,
                SearchFeeFixFG, SearchReceiptStampFG, SearchInvoiceStatus,
                SearchWithOutTaxTotalMin, SearchTotalFeeMin, SearchFeeFixDateStr, SearchReceiptStampDateStr, SearchEntryDateStr, SearchUpdateDateStr,
                SearchWithOutTaxTotalMax, SearchTotalFeeMax, SearchFeeFixDateEnd, SearchReceiptStampDateEnd, SearchEntryDateEnd, SearchUpdateDateEnd,
                SearchEntryUser, SearchUpdateUser, SearchEntryPG, SearchUpdatePG, SearchUseFeeBasePtCd,
                SearchWmsStatus, SearchWmsShipDateStr, SearchWmsShipDateEnd, SearchCourseGpCd, SearchCourseCD, SearchCourseCDEda, SearchPitGrp, SearchPit,
                SearchMsItemCd, SearchMsItemName, SearchClItemCd,
                SearchMsCategoryCd, SearchMsCategoryName, SearchMsTildFG, SearchMsTildName,
                SearchMsLot, SearchMsExpDateStr, SearchMsExpDateEnd, SearchMsPackingType,
                AllSearch);
    }
}

/*
public class WT100_OkuriHd_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static boolean MsViewMode;
	public static void OkuriHdSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		MsViewMode = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1200,800,"Corgi00出荷指示検索　WT100_OkuriHd_00_Search","SP");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,1160,300,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		
		//検索条件		
		
		final JComboBox TB_SearchInvoiceWHCD		= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchWhList[0],11);		//倉庫CD
		final JComboBox TB_SearchClGpCD				= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchClGpList[0],11);	//荷主グループCD
		final JComboBox TB_SearchClCd				= B100_FrameParts.JComboBoxSet(	100, 25,200,20,B100_DefaultVariable.SearchClList[0],11);		//荷主CD
		final JTextField TB_SearchOkuriNo			= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//送り状番号
		final JTextField TB_SearchClDeliNo			= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷主管理番号
		final JComboBox TB_SearchPickupWhCd			= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchWhList[0],11);		//集荷倉庫CD
		final JComboBox TB_SearchPurposeFG						= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchPurposeList[0],11);		//目的フラグ
		final JFormattedTextField TB_SearchPlanDateStr			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//出荷予定日開始
		final JFormattedTextField TB_SearchShipDateStr			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//出荷実績日開始
		final JFormattedTextField TB_SearchSPPlanDateStr		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//着日指定開始
		final JFormattedTextField TB_SearchSPDateStr			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//着日実績開始
		
		final JFormattedTextField TB_SearchPlanDateEnd			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//出荷予定日終了
		final JFormattedTextField TB_SearchShipDateEnd			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//出荷実績日終了
		final JFormattedTextField TB_SearchSPPlanDateEnd		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//着日指定終了
		final JFormattedTextField TB_SearchSPDateEnd			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//着日実績終了
		
		final JFormattedTextField TB_SearchTotalWeightMin		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//荷物重量(kg)最小
		final JFormattedTextField TB_SearchTotalSizeMin			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###.###");				//荷物サイズ最小
		final JFormattedTextField TB_SearchTotalQtyMin			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###.###");				//個口数最小
		
		final JFormattedTextField TB_SearchTotalWeightMax		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//荷物重量(kg)最大
		final JFormattedTextField TB_SearchTotalSizeMax			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###.###");				//荷物サイズ最大
		final JFormattedTextField TB_SearchTotalQtyMax			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###.###");				//個口数最大
		
		final JComboBox TB_SearchDeliveryTypeCd01				= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchDeliveryType01[0],11);	//運送タイプ01
		final JComboBox TB_SearchDeliveryTypeCd02				= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchDeliveryType02[0],11);	//運送タイプ02
		final JComboBox TB_SearchDeliveryTypeCd03				= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchDeliveryType03[0],11);	//運送タイプ03
		final JComboBox TB_SearchDeliveryTypeCd04				= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchDeliveryType04[0],11);	//運送タイプ04
		final JComboBox TB_SearchDeliveryTypeCd05				= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchDeliveryType05[0],11);	//運送タイプ05
		
		final JComboBox TB_SearchCodFG							= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchCODList[0],11);			//代引区分
		final JFormattedTextField TB_SearchCodPayTotalMin		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//代引収受金額合計最小
		final JFormattedTextField TB_SearchCodPayTotalMax		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//代引収受金額合計最大
		
		final JComboBox TB_earchChildrenFG						= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchChildrenFGList[0],11);	//子伝票区分
		final JTextField TB_SearchParentOkuriNo					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//親伝票番号
		
		final JTextField TB_SearchNiokuriCd						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人CD
		final JTextField TB_SearchNiokuriDepartmentCd			= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人部署CD
		final JTextField TB_SearchNiokuriName					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人名称
		final JTextField TB_SearchNiokuriPost					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人郵便番号
		final JTextField TB_SearchNiokuriAdd					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人住所
		final JTextField TB_SearchNioKuriTel					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人Tel
		final JTextField TB_SearchNioKuriFax					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人Fax
		final JTextField TB_SearchNioKuriMail					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人Mail
		final JTextField TB_SearchNiokuriMunicCd				= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷送人市区町村CD
		
		final JTextField TB_SearchDeliCd						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先CD
		final JTextField TB_SearchClDeliCd						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷主届先CD
		final JTextField TB_SearchDeliDepartmentCd				= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先部署CD
		final JTextField TB_SearchDeliName						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先名称
		final JTextField TB_SearchDeliPost						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先郵便番号
		final JTextField TB_SearchDeliAdd						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先住所
		final JTextField TB_SearchDeliTel						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先Tel
		final JTextField TB_SearchDeliFax						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先Fax
		final JTextField TB_SearchDeliMail						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先Mail
		final JTextField TB_SearchDeliMunicCd					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//届先市区町村CD
		
		final JTextField TB_SearchCom							= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//コメント
		final JComboBox TB_SearchStatus							= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchStatusList[0],11);		//運送ステータス
		
		final JComboBox TB_SearchFeeFixFG						= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchFeeFixFgList[0],11);		//運賃確定フラグ
		final JComboBox TB_SearchReceiptStampFG					= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchReceiptStampFGList[0],11);//受領印フラグ
		final JComboBox TB_SearchInvoiceStatus					= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchInvoiceStatusList[0],11);	//請求ステータス
		
		final JFormattedTextField TB_SearchWithOutTaxTotalMin	= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//税別運賃合計最小
		final JFormattedTextField TB_SearchTotalFeeMin			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//税込運賃合計税込運賃合計
		final JFormattedTextField TB_SearchFeeFixDateStr		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//運賃確定日時開始
		final JFormattedTextField TB_SearchReceiptStampDateStr	= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//受領印日時開始
		final JFormattedTextField TB_SearchEntryDateStr			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//登録日終了
		final JFormattedTextField TB_SearchUpdateDateStr		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//更新日終了
		
		final JFormattedTextField TB_SearchWithOutTaxTotalMax	= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//税別運賃合計最大
		final JFormattedTextField TB_SearchTotalFeeMax			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"###,###");					//税込運賃合計最大
		final JFormattedTextField TB_SearchFeeFixDateEnd		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//運賃確定日時終了
		final JFormattedTextField TB_SearchReceiptStampDateEnd	= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//受領印日時終了
		final JFormattedTextField TB_SearchEntryDateEnd			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//登録日終了
		final JFormattedTextField TB_SearchUpdateDateEnd		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//更新日終了
		
		final JTextField TB_SearchEntryUser						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//登録者
		final JTextField TB_SearchUpdateUser					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//更新者
		final JTextField TB_SearchEntryPG						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//登録プログラム
		final JTextField TB_SearchUpdatePG						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//更新プログラム
		final JTextField TB_SearchUseFeeBasePtCd				= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//運転計算タリフ
		final JComboBox TB_SearchWmsStatus						= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchWmsStatusList[0],11);	//倉庫出荷ステータス
		final JTextField TB_SearchWmsShipDateStr				= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//倉庫出荷日時開始
		final JTextField TB_SearchWmsShipDateEnd				= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//倉庫出荷日時終了
		final JTextField TB_SearchCourseGpCd					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//配車コースグループコード
		final JTextField TB_SearchCourseCD						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//配車コースコード
		final JTextField TB_SearchCourseCDEda					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//配車コースコード枝番
		final JTextField TB_SearchPitGrp						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷物払出ピットグループ
		final JTextField TB_SearchPit							= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷物払出ピット
		
		final JTextField TB_SearchMsItemCd						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//商品CD
		final JTextField TB_SearchMsItemName					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//商品名
		
		final JTextField TB_SearchClItemCd						= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//荷主商品CD
		
		final JTextField TB_SearchMsCategoryCd					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//カテゴリCD
		final JTextField TB_SearchMsCategoryName				= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//カテゴリ名
		final JComboBox TB_SearchMsTildFG						= B100_FrameParts.JComboBoxSet(		100, 50,200,20,B100_DefaultVariable.SearchTildFG[0],11);		//温度区分
		final JTextField TB_SearchMsTildName					= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//温度区分名
		
		final JTextField TB_SearchMsLot							= B100_FrameParts.JTextFieldSet(	100,125,100,20,""	,11,0);										//ロット指定
		final JFormattedTextField TB_SearchMsExpDateStr			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//賞味期限指定開始
		final JFormattedTextField TB_SearchMsExpDateEnd			= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");				//賞味期限指定終了
		final JComboBox TB_SearchMsPackingType					= B100_FrameParts.JComboBoxSet(		100, 50,200,20,B100_DefaultVariable.SearchUnitTypeList[0],11);	//荷姿タイプ
		
		
		
		
		
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				Ms_fm.setVisible(false);
				Ms_fm.dispose();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_WorkMain.WorkMain(0,0);
			}
		});
	}
}
*/