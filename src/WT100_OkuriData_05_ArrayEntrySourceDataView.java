public class WT100_OkuriData_05_ArrayEntrySourceDataView{
	static final int ColClCd			= 0;
	static final int ColInvoiceWhCd	= 1;
	static final int ColClDeliNo		= 2;
	static final int ColPlanDate		= 3;
	static final int ColClDeliCd		= 4;
	static final int ColCom01			= 5;
	static final int ColMsClItemCd	= 6;
	static final int ColMsPackingQty	= 7;
	static final int ColMsCom01		= 8;
	
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
									};
		
		Object[][] Rt = new Object[RtOkuriDataArrayEntrySourceDataView.length][RtOkuriDataArrayEntrySourceDataView[0].length];
		
		for(int i=0;i<RtOkuriDataArrayEntrySourceDataView.length;i++) {
			for(int i01=0;i01<RtOkuriDataArrayEntrySourceDataView[i].length;i01++) {
				Rt[(int)RtOkuriDataArrayEntrySourceDataView[i][1]][i01]	= RtOkuriDataArrayEntrySourceDataView[i][i01];
			}
		}
		return Rt;
	}
}