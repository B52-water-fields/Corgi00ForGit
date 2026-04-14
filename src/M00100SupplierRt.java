import java.util.ArrayList;

public class M00100SupplierRt{
	static int ColClWh				= (int)0;	//担当倉庫
	static int ColWHName			= (int)1;	//担当倉庫名
	static int ColClCd				= (int)2;	//荷主CD
	static int ColCLName01			= (int)3;	//荷主名1
	static int ColSPCd				= (int)4;	//仕入先コード
	static int ColSPName01			= (int)5;	//仕入先名1
	static int ColSPName02			= (int)6;	//仕入先名2
	static int ColSPName03			= (int)7;	//仕入先名3
	static int ColSPPost			= (int)8;	//仕入先郵便
	static int ColSPAdd01			= (int)9;	//仕入先住所1
	static int ColSPAdd02			= (int)10;	//仕入先住所2
	static int ColSPAdd03			= (int)11;	//仕入先住所3
	static int ColSPTel				= (int)12;	//仕入先電話
	static int ColSPFax				= (int)13;	//仕入先FAX
	static int ColSPMail			= (int)14;	//仕入先MAIL
	static int ColCom01				= (int)15;	//コメント1
	static int ColCom02				= (int)16;	//コメント2
	static int ColCom03				= (int)17;	//コメント3
	static int ColPTMSCDBMN		= (int)18;	//基幹Sysコード（部門）
	static int ColPTMSCDNINUSHI	= (int)19;	//基幹Sysコード（荷主）
	static int ColPaySite			= (int)20;	//支払いサイト（月数）
	static int ColPayDate			= (int)21;	//支払日　末日＝99
	static int ColShimeDate		= (int)22;	//締め日　末日＝99
	static int ColEntryDate		= (int)23;	//登録日
	static int ColUpdateDate		= (int)24;	//更新日
	static int ColEntryUser		= (int)25;	//登録者
	static int ColUpdateUser		= (int)26;	//更新者
	static int ColDECD				= (int)27;	//納品先コード
	static int ColDepartmentCd		= (int)28;	//部署CD
	static int ColDEName01			= (int)29;	//納品先名1
	
	public static Object[][] RtSupplierRt(){
		Object[][] RtSupplierRt = {
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
				,{"PayDate"			,ColPayDate			,"int"		,"支払日（末日＝99）"}
				,{"ShimeDate"		,ColShimeDate		,"int"		,"締め日（末日＝99）"}
				,{"EntryDate"		,ColEntryDate		,"String"	,"登録日"}
				,{"UpdateDate"		,ColUpdateDate		,"String"	,"更新日"}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"}
				,{"UpdateUser"		,ColUpdateUser		,"String"	,"更新者"}
				,{"DECD"			,ColDECD			,"String"	,"納品先コード"}
				,{"DepartmentCd"	,ColDepartmentCd	,"String"	,"部署CD"}
				,{"DEName01"		,ColDEName01		,"String"	,"納品先名1"}
				};
		
		return RtSupplierRt;
	}
	
	public static Object[][] M00100SupplierRt(
			ArrayList<String> SearchClWh,			//担当倉庫
			ArrayList<String> SearchClCd,			//荷主CD
			ArrayList<String> SearchSPCd,			//仕入先コード
			ArrayList<String> SearchSPName,			//仕入先名
			ArrayList<String> SearchSPPost,			//仕入先郵便
			ArrayList<String> SearchSPAdd,			//仕入先住所
			ArrayList<String> SearchSPTel,			//仕入先電話
			ArrayList<String> SearchSPFax,			//仕入先FAX
			ArrayList<String> SearchSPMail,			//仕入先MAIL
			ArrayList<String> SearchCom,			//コメント
			ArrayList<String> SearchPTMSCDBMN,		//基幹Sysコード（部門）
			ArrayList<String> SearchPTMSCDNINUSHI,	//基幹Sysコード（荷主）
			ArrayList<Integer> SearchPaySiteStr,	//支払いサイト（月数）
			ArrayList<Integer> SearchPayDateStr,	//支払日（日＝99）
			ArrayList<Integer> SearchShimeDateStr,	//締め日（末日＝99
			ArrayList<Integer> SearchPaySiteEnd,	//支払いサイト（月数）
			ArrayList<Integer> SearchPayDateEnd,	//支払日（日＝99）
			ArrayList<Integer> SearchShimeDateEnd,	//締め日（末日＝99）
			ArrayList<String> SearchDECD,			//納品先コード
			ArrayList<String> SearchDepartmentCd,	//部署CD
			boolean AllSearch){
		
		SearchClWh			= B00150ArrayListControl.ArryListStringUniqueList(SearchClWh);			//担当倉庫
		SearchClCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);			//荷主CD
		SearchSPCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPCd);			//仕入先コード
		SearchSPName		= B00150ArrayListControl.ArryListStringUniqueList(SearchSPName);			//仕入先名
		SearchSPPost		= B00150ArrayListControl.ArryListStringUniqueList(SearchSPPost);			//仕入先郵便
		SearchSPAdd			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPAdd);			//仕入先住所
		SearchSPTel			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPTel);			//仕入先電話
		SearchSPFax			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPFax);			//仕入先FAX
		SearchSPMail		= B00150ArrayListControl.ArryListStringUniqueList(SearchSPMail);			//仕入先MAIL
		SearchCom			= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);				//コメント
		SearchPTMSCDBMN		= B00150ArrayListControl.ArryListStringUniqueList(SearchPTMSCDBMN);		//基幹Sysコード（部門）
		SearchPTMSCDNINUSHI	= B00150ArrayListControl.ArryListStringUniqueList(SearchPTMSCDNINUSHI);	//基幹Sysコード（荷主）
		SearchPaySiteStr	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPaySiteStr);		//支払いサイト（月数）
		SearchPayDateStr	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPayDateStr);		//支払日（日＝99）
		SearchShimeDateStr	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShimeDateStr);	//締め日（末日＝99
		SearchPaySiteEnd	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPaySiteEnd);		//支払いサイト（月数）
		SearchPayDateEnd	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPayDateEnd);		//支払日（日＝99）
		SearchShimeDateEnd	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShimeDateEnd);	//締め日（末日＝99）
		SearchDECD			= B00150ArrayListControl.ArryListStringUniqueList(SearchDECD);			//納品先コード
		SearchDepartmentCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchDepartmentCd);	//部署CD
		
		
		
		Object [][]rt = new Object[0][RtSupplierRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select "
				+"(WM0010Supplier.ClWh) as ClWh,\n"						//担当倉庫
				+"(KM0010_WHMST.WHName) as WHName,\n"					//担当倉庫名
				+"(WM0010Supplier.ClCd) as ClCd,\n"						//荷主CD
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"			//荷主名1
				+"(WM0010Supplier.SPCd) as SPCd,\n"						//仕入先コード
				+"(WM0010Supplier.SPName01) as SPName01,\n"				//仕入先名1
				+"(WM0010Supplier.SPName02) as SPName02,\n"				//仕入先名2
				+"(WM0010Supplier.SPName03) as SPName03,\n"				//仕入先名3
				+"(WM0010Supplier.SPPost) as SPPost,\n"					//仕入先郵便
				+"(WM0010Supplier.SPAdd01) as SPAdd01,\n"				//仕入先住所1
				+"(WM0010Supplier.SPAdd02) as SPAdd02,\n"				//仕入先住所2
				+"(WM0010Supplier.SPAdd03) as SPAdd03,\n"				//仕入先住所3
				+"(WM0010Supplier.SPTel) as SPTel,\n"					//仕入先電話
				+"(WM0010Supplier.SPFax) as SPFax,\n"					//仕入先FAX
				+"(WM0010Supplier.SPMail) as SPMail,\n"					//仕入先MAIL
				+"(WM0010Supplier.Com01) as Com01,\n"					//コメント2
				+"(WM0010Supplier.Com03) as Com03,\n"					//コメント3
				+"(WM0010Supplier.PTMSCDBMN) as PTMSCDBMN,\n"			//基幹Sysコード（部門）
				+"(WM0010Supplier.PTMSCDNINUSHI) as PTMSCDNINUSHI,\n"	//基幹Sysコード（荷主）
				+"(WM0010Supplier.PaySite) as PaySite,\n"				//支払いサイト（月数）
				+"(WM0010Supplier.PayDate) as PayDate,\n"				//支払日（日＝99）
				+"(WM0010Supplier.ShimeDate) as ShimeDate,\n"			//締め日（末日＝99）
				+"(WM0010Supplier.EntryDate) as EntryDate,\n"			//登録日
				+"(WM0010Supplier.UpdateDate) as UpdateDate,\n"			//更新日
				+"(WM0010Supplier.EntryUser) as EntryUser,\n"			//登録者
				+"(WM0010Supplier.UpdateUser) as UpdateUser,\n"			//更新者
				+"(WM0010Supplier.DECD) as DECD,\n"						//納品先コード
				+"(WM0010Supplier.DepartmentCd) as DepartmentCd,\n"		//部署CD
				+"(KM0040_DELIVERYMST.DEName01) as DEName01\n"			//納品先名1
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