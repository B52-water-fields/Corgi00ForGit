public class M00100SupplierRt{
	public static Object[][] RtSupplierRt(){
		Object[][] RtSupplierRt = {
				/*
				 {"ClWh"			,ColClWh			,"String"	,"担当倉庫"}
				,{"WHName"			,ColWHName			,"String"	,"担当倉庫名"}
				,{"ClCd"			,ColClCd			,"String"	,"荷主CD"}
				,{"CLName01"		,ColCLName01		,"String"	,"荷主名1"}
				,{"SPCd"			,ColSPCd			,"String"	,"仕入先コード"}
				,{"SPName01"		,ColSPName01		,"String"	,"仕入先名1"}
				,{"SPName02"		,ColSPName02		,"String"	,"仕入先名2"}
				,{"SPName03"		,ColSPName03		,"String"	,"仕入先名3"}
				,{"SPPost"			,ColSPPost			,"String"	,"仕入先郵便"}
				,{"SPAdd01"			,ColSPAdd01			,"String"	,"仕入先住所1"}
				,{"SPAdd02"			,ColSPAdd02			,"String"	,"仕入先住所2"}
				,{"SPAdd03"			,ColSPAdd03			,"String"	,"仕入先住所3"}
				,{"SPTel"			,ColSPTel			,"String"	,"仕入先電話"}
				,{"SPFax"			,ColSPFax			,"String"	,"仕入先FAX"}
				,{"SPMail"			,ColSPMail			,"String"	,"仕入先MAIL"}
				,{"Com01"			,ColCom01			,"String"	,"コメント1"}
				,{"Com02"			,ColCom02			,"String"	,"コメント2"}
				,{"Com03"			,ColCom03			,"String"	,"コメント3"}
				,{"PTMSCDBMN"		,ColPTMSCDBMN		,"String"	,"基幹Sysコード（部門）"}
				,{"PTMSCDNINUSHI"	,ColPTMSCDNINUSHI	,"String"	,"基幹Sysコード（荷主）"}
				,{"PaySite"			,ColPaySite			,"int"		,"支払いサイト（月数）"}
				,{"PayDate"			,ColPayDate			,"int"		,"支払日　末日＝99"}
				,{"ShimeDate"		,ColShimeDate		,"int"		,"締め日　末日＝99"}
				,{"EntryDate"		,ColEntryDate		,"String"	,"登録日"}
				,{"UpdateDate"		,ColUpdateDate		,"String"	,"更新日"}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"}
				,{"UpdateUser"		,ColUpdateUser		,"String"	,"更新者"}
				,{"DECD"			,ColDECD			,"String"	,"納品先コード"}
				,{"DEName01"		,ColDEName01		,"String"	,"納品先名1"}
				,{"DepartmentCd"	,ColDepartmentCd	,"String"	,"部署CD"}
				*/
				};
		
		return RtSupplierRt;
	}
	
	public static Object[][] M00100SupplierRt(
			){
		Object [][]rt = new Object[0][100];
		String sql = "select "
				/*
				+"(WM0010Supplier.ClWh) as ClWh,\n"				//担当倉庫
				+"(KM0010_WHMST.WHName) as WHName,\n"			//担当倉庫名
				+"(WM0010Supplier.ClCd) as ClCd,\n"				//荷主CD
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"	//荷主名1
				+"(WM0010Supplier.SPCd) as SPCd,\n"				//仕入先コード
				+"(WM0010Supplier.SPName01) as ,\n	//仕入先名1
				+"(WM0010Supplier.SPName02) as ,\n	//仕入先名2
				+"(WM0010Supplier.SPName03) as ,\n	//仕入先名3
				+"(WM0010Supplier.SPPost) as ,\n	//仕入先郵便
				+"(WM0010Supplier.SPAdd01) as ,\n	//仕入先住所1
				+"(WM0010Supplier.SPAdd02) as ,\n	//仕入先住所2
				+"(WM0010Supplier.SPAdd03	VARCHAR2(100 BYTE)	Yes		仕入先住所3
				+"(WM0010Supplier.SPTel	VARCHAR2(20 BYTE)	Yes		仕入先電話
				+"(WM0010Supplier.SPFax	VARCHAR2(20 BYTE)	Yes		仕入先FAX
				+"(WM0010Supplier.SPMail	VARCHAR2(200 BYTE)	Yes		仕入先MAIL
				+"(WM0010Supplier.Com01	VARCHAR2(100 BYTE)	Yes		コメント1
				+"(WM0010Supplier.Com02	VARCHAR2(100 BYTE)	Yes		コメント2
				+"(WM0010Supplier.Com03	VARCHAR2(100 BYTE)	Yes		コメント3
				+"(WM0010Supplier.PTMSCDBMN	VARCHAR2(12 BYTE)	Yes		基幹Sysコード（部門）
				+"(WM0010Supplier.PTMSCDNINUSHI	VARCHAR2(12 BYTE)	Yes		基幹Sysコード（荷主）
				+"(WM0010Supplier.PaySite	INT	Yes		支払いサイト（月数）
				+"(WM0010Supplier.PayDate	INT	Yes		支払日　末日＝99
				+"(WM0010Supplier.ShimeDate	INT	Yes		締め日　末日＝99
				+"(WM0010Supplier.EntryDate	TIMESTAMP(6)	Yes		登録日
				+"(WM0010Supplier.UpdateDate	TIMESTAMP(6)	Yes		更新日
				+"(WM0010Supplier.EntryUser	VARCHAR2(50 BYTE)	Yes		登録者
				+"(WM0010Supplier.UpdateUser	VARCHAR2(50 BYTE)	Yes		更新者
				+"(WM0010Supplier.DECD	VARCHAR2(20 BYTE)	Yes		納品先コード
				+"(KM0040_DELIVERYMST.DEName01	VARCHAR2(50 BYTE)	Yes		納品先名1
				+"(WM0010Supplier.DepartmentCd	VARCHAR2(20 BYTE)	Yes		部署CD

				*/
				+ ""
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010Supplier"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WM0010Supplier.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WM0010Supplier.ClWh = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST"
				+ " on("
				+ " WM0010Supplier.DECD = KM0010_WHMST.DECD"
				+ " and WM0010Supplier.DepartmentCd = KM0010_WHMST.DepartmentCd"
				+ ")\n"
				+" where 1=1 \n";
		
		
		
		
		
		return rt;
	}
}