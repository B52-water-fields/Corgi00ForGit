public class T100_ArrivalMsRt{
	static final int ColClWh			=  0;		//担当倉庫
	static final int ColWHName			=  1;		//担当倉庫名
	static final int ColClCd			=  2;		//荷主CD
	static final int ColCLName01		=  3;		//ヘッダ荷主名
	static final int ColClGpCD			=  4;		//ヘッダ荷主グループCD
	static final int ColCLGpName01	=  5;		//ヘッダ荷主グループ標記名
	static final int ColArrNo			=  6;		//入荷予定NO
	static final int ColArrCount		=  7;		//入荷予定枝番
	static final int ColClArrNo		=  8;		//荷主予定番号
	static final int ColPlanDate		=  9;		//入荷予定日
	static final int ColActualDate	= 10;		//入荷実績日
	static final int ColSpCd			= 11;		//仕入先CD
	static final int ColSpName01		= 12;		//仕入先名01
	static final int ColSpName02		= 13;		//仕入先名02
	static final int ColSpName03		= 14;		//仕入先名03
	static final int ColSpPost			= 15;		//仕入先郵便
	static final int ColSpAdd01		= 16;		//仕入先住所01
	static final int ColSpAdd02		= 17;		//仕入先住所02
	static final int ColSpAdd03		= 18;		//仕入先住所03
	static final int ColSpTel			= 19;		//仕入先電話"}
	static final int ColArCom01		= 20;		//コメント1
	static final int ColArCom02		= 21;		//コメント2
	static final int ColArCom03		= 22;		//コメント3
	static final int ColEntryDate		= 23;		//登録日
	static final int ColUpdateDate	= 24;		//更新日
	static final int ColEntryUser		= 25;		//登録者
	static final int ColUpdateUser	= 26;		//更新者
	
	//明細WW0013ArrivaMs由来
	static final int ColMsNo			= 27;		//明細番号
	static final int ColMsSeq			= 28;		//明細Seq番号
	static final int ColItemCd			= 29;		//商品コード
	static final int ColClItemCd		= 30;		//荷主商品コード
	static final int ColJanCd			= 31;		//JanCd(バラ)
	static final int ColItemMdNo		= 32;		//商品型番
	static final int ColItemName		= 33;		//商品名
	static final int ColLot			= 34;		//ロット
	static final int ColExpDate		= 35;		//消費期限
	static final int ColPlanQty		= 36;		//予定数量
	static final int ColActualQty		= 37;		//実績数
	static final int ColMsActualDate	= 38;		//入荷日
	static final int ColCom01			= 39;		//コメント1
	static final int ColCom02			= 40;		//コメント2
	static final int ColMsEntryDate	= 41;		//登録日
	static final int ColMsUpdateDate	= 42;		//更新日
	static final int ColMsEntryUser	= 43;		//登録者
	static final int ColMsUpdateUser	= 44;		//更新者
	
	public static Object[][] RtArrivalMsRt(){
		Object[][] Rt = {
					{"",""}
					/*
					
					
					*/
					};
		
		return Rt;
	}
	
	public static Object[][] ArrivalMsRt(
			
			){
		Object[][] Rt = new Object[0][0];
		String sql = "select \n"
				//ヘッダWW0012ArrivalHd由来
				+"(WW0012ArrivalHd.ClWh) 			as	ClWh,\n"			//担当倉庫
				+"(KM0010_WHMST.WHName)				as	WHName,\n"			//担当倉庫名
				+"(WW0012ArrivalHd.ClCd) 			as	ClCd,\n"			//荷主CD
				+"(KM0030_CLIENTMST.CLName01)       as	CLName01,\n"		//ヘッダ荷主名
				+"(KM0030_CLIENTMST.ClGpCD)         as	ClGpCD,\n"			//ヘッダ荷主グループCD
				+"(KM0031_CLIENT_GROUP.CLGpName01)  as	CLGpName01,\n"		//ヘッダ荷主グループ標記名
				+"(WW0012ArrivalHd.ArrNo)			as	ArrNo,\n"			//入荷予定NO
				+"(WW0012ArrivalHd.ArrCount) 		as	ArrCount,\n"		//入荷予定枝番
				+"(WW0012ArrivalHd.ClArrNo) 		as	ClArrNo,\n"			//荷主予定番号
				+"(WW0012ArrivalHd.PlanDate) 		as	PlanDate,\n"		//入荷予定日
				+"(WW0012ArrivalHd.ActualDate)		as	ActualDate,\n"		//入荷実績日
				+"(WW0012ArrivalHd.SpCd)			as	SpCd,\n"			//仕入先CD
				+"(WW0012ArrivalHd.SpName01)		as	SpName01,\n"		//仕入先名01
				+"(WW0012ArrivalHd.SpName02)		as	SpName02,\n"		//仕入先名02
				+"(WW0012ArrivalHd.SpName03)		as	SpName03,\n"		//仕入先名03
				+"(WW0012ArrivalHd.SpPost)			as	SpPost,\n"			//仕入先郵便
				+"(WW0012ArrivalHd.SpAdd01)			as	SpAdd01,\n"			//仕入先住所01
				+"(WW0012ArrivalHd.SpAdd02)			as	SpAdd02,\n"			//仕入先住所02
				+"(WW0012ArrivalHd.SpAdd03)			as	SpAdd03,\n"			//仕入先住所03
				+"(WW0012ArrivalHd.SpTel)			as	SpTel,\n"			//仕入先電話"}
				+"(WW0012ArrivalHd.ArCom01)			as	ArCom01,\n"			//コメント1
				+"(WW0012ArrivalHd.ArCom02)			as	ArCom02,\n"			//コメント2
				+"(WW0012ArrivalHd.ArCom03)			as	ArCom03,\n"			//コメント3
				+"(WW0012ArrivalHd.EntryDate)		as	EntryDate,\n"		//登録日
				+"(WW0012ArrivalHd.UpdateDate)		as	UpdateDate,\n"		//更新日
				+"(WW0012ArrivalHd.EntryUser)		as	EntryUser,\n"		//登録者
				+"(WW0012ArrivalHd.UpdateUser)		as	UpdateUser,\n"		//更新者
				
				//明細WW0013ArrivaMs由来
				+"(WW0013ArrivaMs.MsNo)				as	MsNo,\n"			//明細番号
				+"(WW0013ArrivaMs.MsSeq)			as	MsSeq,\n"			//明細Seq番号
				+"(WW0013ArrivaMs.ItemCd)			as	ItemCd,\n"			//商品コード
				+"(WW0013ArrivaMs.ClItemCd)			as	ClItemCd,\n"		//荷主商品コード
				+"(WW0013ArrivaMs.JanCd)			as	JanCd,\n"			//JanCd(バラ)
				+"(WW0013ArrivaMs.ItemMdNo)			as	ItemMdNo,\n"		//商品型番
				+"(WW0013ArrivaMs.ItemName)			as	ItemName,\n"		//商品名
				+"(WW0013ArrivaMs.Lot)				as	Lot,\n"				//ロット
				+"(WW0013ArrivaMs.ExpDate)			as	ExpDate,\n"			//消費期限
				+"(WW0013ArrivaMs.PlanQty)			as	PlanQty,\n"			//予定数量
				+"(WW0013ArrivaMs.ActualQty)		as	ActualQty,\n"		//実績数
				+"(WW0013ArrivaMs.ActualDate)		as	MsActualDate,\n"	//入荷日
				+"(WW0013ArrivaMs.Com01)			as	Com01,\n"			//コメント1
				+"(WW0013ArrivaMs.Com02)			as	Com02,\n"			//コメント2
				+"(WW0013ArrivaMs.EntryDate)		as	MsEntryDate,\n"		//登録日
				+"(WW0013ArrivaMs.UpdateDate)		as	MsUpdateDate,\n"	//更新日
				+"(WW0013ArrivaMs.EntryUser)		as	MsEntryUser,\n"		//登録者
				+"(WW0013ArrivaMs.UpdateUser)		as	MsUpdateUser \n"	//更新者
				
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0012ArrivalHd \n"
				
				+ " left outher join "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0013ArrivaMs \n"
				+ " on( WW0012ArrivalHd.ClWh = WW0013ArrivaMs.ClWh"
				+ " and WW0012ArrivalHd.ClCd = WW0013ArrivaMs.ClCd"
				+ " and WW0012ArrivalHd.ArrNo = WW0013ArrivaMs.ArrNo"
				+ ")\n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0012ArrivalHd.ClWh = KM0010_WHMST.WHCD"
				+ ")\n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0012ArrivalHd.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				;
		
		
		
		return Rt;
	}
}