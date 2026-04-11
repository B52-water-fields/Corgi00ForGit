import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00000ParameterMstWankoRt{
	/*
	コピペ用
	ArrayList<String> SearchClWh 			= new ArrayList<String>();
	ArrayList<String> SearchClCd 			= new ArrayList<String>();
	ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
	ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
	ArrayList<String> SearchParaName 		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
	ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
	ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
	Boolean AllSearch = false;
	
	Object[][] ParameterMstWankoRt = M00000ParameterMstWankoRt.ParameterMstWankoRt(
			SearchClWh,SearchClCd,
			SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
			SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
			SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
			SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
			SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
			SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
			SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
			SearchParaTxtAll,
			AllSearch);
			
	String GetClWh			=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColClWh];			//担当倉庫コード
	String GetWHName		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColWHName];		//担当倉庫名
	String GetClCd			=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColClCd];			//荷主コード
	String GetCLName01		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColCLName01];		//荷主名
	String GetParaCd		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaCd];		//パラメータコード
	int GetParaCdSeq		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaCdSeq];		//ナンバリング
	String GetParaName		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaName];		//パラメータ名
	String GetParaTxt01		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt01];	//パラメータテキスト項目01
	String GetParaTxt02		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt02];	//パラメータテキスト項目02
	String GetParaTxt03		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt03];	//パラメータテキスト項目03
	String GetParaTxt04		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt04];	//パラメータテキスト項目04
	String GetParaTxt05		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt05];	//パラメータテキスト項目05
	String GetParaTxt06		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt06];	//パラメータテキスト項目06
	String GetParaTxt07		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt07];	//パラメータテキスト項目07
	String GetParaTxt08		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt08];	//パラメータテキスト項目08
	String GetParaTxt09		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt09];	//パラメータテキスト項目09
	String GetParaTxt10		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaTxt10];	//パラメータテキスト項目10
	int GetParaInt01		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt01];		//パラメータ数値項目01
	int GetParaInt02		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt02];		//パラメータ数値項目02
	int GetParaInt03		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt03];		//パラメータ数値項目03
	int GetParaInt04		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt04];		//パラメータ数値項目04
	int GetParaInt05		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt05];		//パラメータ数値項目05
	int GetParaInt06		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt06];		//パラメータ数値項目06
	int GetParaInt07		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt07];		//パラメータ数値項目07
	int GetParaInt08		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt08];		//パラメータ数値項目08
	int GetParaInt09		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt09];		//パラメータ数値項目09
	int GetParaInt10		=(int)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColParaInt10];		//パラメータ数値項目10
	String GetEntryDate		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColEntryDate];	//登録日
	String GetUpdateDate	=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColUpdateDate];	//更新日
	String GetEntryUser		=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColEntryUser];	//登録者
	String GetUpdateUser	=(String)ParameterMstWankoRt[i][M00000ParameterMstWankoRt.ColUpdateUser];	//更新者
	
	*/
	
	//戻り値カラム
	static int ColClWh			= (int) 0;	//担当倉庫コード
	static int ColWHName		= (int) 1;	//担当倉庫名
	static int ColClCd			= (int) 2;	//荷主コード
	static int ColCLName01		= (int) 3;	//荷主名
	static int ColParaCd		= (int) 4;	//パラメータコード
	static int ColParaCdSeq	= (int) 5;	//ナンバリング
	static int ColParaName		= (int) 6;	//パラメータ名
	static int ColParaTxt01	= (int) 7;	//パラメータテキスト項目01
	static int ColParaTxt02	= (int) 8;	//パラメータテキスト項目02
	static int ColParaTxt03	= (int) 9;	//パラメータテキスト項目03
	static int ColParaTxt04	= (int)10;	//パラメータテキスト項目04
	static int ColParaTxt05	= (int)11;	//パラメータテキスト項目05
	static int ColParaTxt06	= (int)12;	//パラメータテキスト項目06
	static int ColParaTxt07	= (int)13;	//パラメータテキスト項目07
	static int ColParaTxt08	= (int)14;	//パラメータテキスト項目08
	static int ColParaTxt09	= (int)15;	//パラメータテキスト項目09
	static int ColParaTxt10	= (int)16;	//パラメータテキスト項目10
	static int ColParaInt01	= (int)17;	//パラメータ数値項目01
	static int ColParaInt02	= (int)18;	//パラメータ数値項目02
	static int ColParaInt03	= (int)19;	//パラメータ数値項目03
	static int ColParaInt04	= (int)20;	//パラメータ数値項目04
	static int ColParaInt05	= (int)21;	//パラメータ数値項目05
	static int ColParaInt06	= (int)22;	//パラメータ数値項目06
	static int ColParaInt07	= (int)23;	//パラメータ数値項目07
	static int ColParaInt08	= (int)24;	//パラメータ数値項目08
	static int ColParaInt09	= (int)25;	//パラメータ数値項目09
	static int ColParaInt10	= (int)26;	//パラメータ数値項目10
	static int ColEntryDate	= (int)27;	//登録日
	static int ColUpdateDate	= (int)28;	//更新日
	static int ColEntryUser	= (int)29;	//登録者
	static int ColUpdateUser	= (int)30;	//更新者
	
	public static Object[][] RtParameterMstWankoRt(){
		Object[][] RtParameterMstWankoRt= {
					 {"ColClWh"		,ColClWh			,"String"	,"担当倉庫コード"}
					,{"WHName"		,ColWHName			,"String"	,"担当倉庫名"}
					,{"ClCd"		,ColClCd			,"String"	,"荷主コード"}
					,{"CLName01"	,ColCLName01		,"String"	,"荷主名"}
					,{"ParaCd"		,ColParaCd			,"String"	,"パラメータコード"}
					,{"ParaCdSeq"	,ColParaCdSeq		,"String"	,"ナンバリング"}
					,{"ParaName"	,ColParaName		,"String"	,"パラメータ名"}
					,{"ParaTxt01"	,ColParaTxt01		,"String"	,"パラメータテキスト項目01"}
					,{"ParaTxt02"	,ColParaTxt02		,"String"	,"パラメータテキスト項目02"}
					,{"ParaTxt03"	,ColParaTxt03		,"String"	,"パラメータテキスト項目03"}
					,{"ParaTxt04"	,ColParaTxt04		,"String"	,"パラメータテキスト項目04"}
					,{"ParaTxt05"	,ColParaTxt05		,"String"	,"パラメータテキスト項目05"}
					,{"ParaTxt06"	,ColParaTxt06		,"String"	,"パラメータテキスト項目06"}
					,{"ParaTxt07"	,ColParaTxt07		,"String"	,"パラメータテキスト項目07"}
					,{"ParaTxt08"	,ColParaTxt08		,"String"	,"パラメータテキスト項目08"}
					,{"ParaTxt09"	,ColParaTxt09		,"String"	,"パラメータテキスト項目09"}
					,{"ParaTxt10"	,ColParaTxt10		,"String"	,"パラメータテキスト項目10"}
					,{"ParaInt01"	,ColParaInt01		,"int"		,"パラメータ数値項目01"}
					,{"ParaInt02"	,ColParaInt02		,"int"		,"パラメータ数値項目02"}
					,{"ParaInt03"	,ColParaInt03		,"int"		,"パラメータ数値項目03"}
					,{"ParaInt04"	,ColParaInt04		,"int"		,"パラメータ数値項目04"}
					,{"ParaInt05"	,ColParaInt05		,"int"		,"パラメータ数値項目05"}
					,{"ParaInt06"	,ColParaInt06		,"int"		,"パラメータ数値項目06"}
					,{"ParaInt07"	,ColParaInt07		,"int"		,"パラメータ数値項目07"}
					,{"ParaInt08"	,ColParaInt08		,"int"		,"パラメータ数値項目08"}
					,{"ParaInt09"	,ColParaInt09		,"int"		,"パラメータ数値項目09"}
					,{"ParaInt10"	,ColParaInt10		,"int"		,"パラメータ数値項目10"}
					,{"EntryDate"	,ColEntryDate		,"String"	,"登録日"}
					,{"UpdateDate"	,ColUpdateDate	,"String"	,"更新日"}
					,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"}
					,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"}
					};
		return RtParameterMstWankoRt;
	}
	
	public static Object[][] ParameterMstWankoRt(
			ArrayList<String> SearchClWh,ArrayList<String> SearchClCd,
			ArrayList<String> SearchParaCd,	ArrayList<Integer> SearchParaCdSeqStr,ArrayList<Integer> SearchParaCdSeqEnd,ArrayList<String> SearchParaName,
			ArrayList<String> SearchParaTxt01,ArrayList<String> SearchParaTxt02,ArrayList<String> SearchParaTxt03,ArrayList<String> SearchParaTxt04,ArrayList<String> SearchParaTxt05,
			ArrayList<String> SearchParaTxt06,ArrayList<String> SearchParaTxt07,ArrayList<String> SearchParaTxt08,ArrayList<String> SearchParaTxt09,ArrayList<String> SearchParaTxt10,
			ArrayList<Integer> SearchParaInt01Str,ArrayList<Integer> SearchParaInt02Str,ArrayList<Integer> SearchParaInt03Str,ArrayList<Integer> SearchParaInt04Str,ArrayList<Integer> SearchParaInt05Str,
			ArrayList<Integer> SearchParaInt06Str,ArrayList<Integer> SearchParaInt07Str,ArrayList<Integer> SearchParaInt08Str,ArrayList<Integer> SearchParaInt09Str,ArrayList<Integer> SearchParaInt10Str,
			ArrayList<Integer> SearchParaInt01End,ArrayList<Integer> SearchParaInt02End,ArrayList<Integer> SearchParaInt03End,ArrayList<Integer> SearchParaInt04End,ArrayList<Integer> SearchParaInt05End,
			ArrayList<Integer> SearchParaInt06End,ArrayList<Integer> SearchParaInt07End,ArrayList<Integer> SearchParaInt08End,ArrayList<Integer> SearchParaInt09End,ArrayList<Integer> SearchParaInt10End,
			ArrayList<String> SearchParaTxtAll,
			Boolean AllSearch){
		
		SearchClWh		= B00150ArrayListControl.ArryListStringUniqueList(SearchClWh);
		SearchClCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);
		SearchParaCd 	= B00150ArrayListControl.ArryListStringUniqueList(SearchParaCd);
		SearchParaCdSeqStr = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaCdSeqStr);
		SearchParaCdSeqEnd = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaCdSeqEnd);
		SearchParaName 	= B00150ArrayListControl.ArryListStringUniqueList(SearchParaName);
		SearchParaTxt01 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt01);
		SearchParaTxt02 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt02);
		SearchParaTxt03 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt03);
		SearchParaTxt04 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt04);
		SearchParaTxt05 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt05);
		SearchParaTxt06 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt06);
		SearchParaTxt07 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt07);
		SearchParaTxt08 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt08);
		SearchParaTxt09 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt09);
		SearchParaTxt10 = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxt10);
		
		SearchParaInt01Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt01Str);
		SearchParaInt02Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt02Str);
		SearchParaInt03Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt03Str);
		SearchParaInt04Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt04Str);
		SearchParaInt05Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt05Str);
		SearchParaInt06Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt06Str);
		SearchParaInt07Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt07Str);
		SearchParaInt08Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt08Str);
		SearchParaInt09Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt09Str);
		SearchParaInt10Str = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt10Str);
		SearchParaInt01End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt01End);
		SearchParaInt02End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt02End);
		SearchParaInt03End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt03End);
		SearchParaInt04End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt04End);
		SearchParaInt05End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt05End);
		SearchParaInt06End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt06End);
		SearchParaInt07End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt07End);
		SearchParaInt08End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt08End);
		SearchParaInt09End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt09End);
		SearchParaInt10End = B00150ArrayListControl.ArryListIntegerUniqueList(SearchParaInt10End);
		
		SearchParaTxtAll = B00150ArrayListControl.ArryListStringUniqueList(SearchParaTxtAll);
		
		//WANKOパラメータ返却
		Object[][] rt=new Object[0][RtParameterMstWankoRt().length];
		boolean SearchKick = false;
		if(AllSearch) {
			SearchKick = true;
		}
		
		String sql= "Select \n"
				+"(WM0000PARAMETER.ClWh) as ClWh,\n"				//担当倉庫コード
				+"(KM0010_WHMST.WHName) as WHName,\n"				//担当倉庫名
				+"(WM0000PARAMETER.ClCd) as ClCd,\n"				//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"		//荷主名
				+"(WM0000PARAMETER.ParaCd) as ParaCd,\n"			//パラメータコード
				+"(WM0000PARAMETER.ParaCdSeq) as ParaCdSeq,\n"		//ナンバリング
				+"(WM0000PARAMETER.ParaName) as ParaName,\n"		//パラメータ名
				+"(WM0000PARAMETER.ParaTxt01) as ParaTxt01,\n"		//パラメータテキスト項目01
				+"(WM0000PARAMETER.ParaTxt02) as ParaTxt02,\n"		//パラメータテキスト項目02
				+"(WM0000PARAMETER.ParaTxt03) as ParaTxt03,\n"		//パラメータテキスト項目03
				+"(WM0000PARAMETER.ParaTxt04) as ParaTxt04,\n"		//パラメータテキスト項目04
				+"(WM0000PARAMETER.ParaTxt05) as ParaTxt05,\n"		//パラメータテキスト項目05
				+"(WM0000PARAMETER.ParaTxt06) as ParaTxt06,\n"		//パラメータテキスト項目06
				+"(WM0000PARAMETER.ParaTxt07) as ParaTxt07,\n"		//パラメータテキスト項目07
				+"(WM0000PARAMETER.ParaTxt08) as ParaTxt08,\n"		//パラメータテキスト項目08
				+"(WM0000PARAMETER.ParaTxt09) as ParaTxt09,\n"		//パラメータテキスト項目09
				+"(WM0000PARAMETER.ParaTxt10) as ParaTxt10,\n"		//パラメータテキスト項目10
				+"(WM0000PARAMETER.ParaInt01) as ParaInt01,\n"		//パラメータ数値項目01
				+"(WM0000PARAMETER.ParaInt02) as ParaInt02,\n"		//パラメータ数値項目02
				+"(WM0000PARAMETER.ParaInt03) as ParaInt03,\n"		//パラメータ数値項目03
				+"(WM0000PARAMETER.ParaInt04) as ParaInt04,\n"		//パラメータ数値項目04
				+"(WM0000PARAMETER.ParaInt05) as ParaInt05,\n"		//パラメータ数値項目05
				+"(WM0000PARAMETER.ParaInt06) as ParaInt06,\n"		//パラメータ数値項目06
				+"(WM0000PARAMETER.ParaInt07) as ParaInt07,\n"		//パラメータ数値項目07
				+"(WM0000PARAMETER.ParaInt08) as ParaInt08,\n"		//パラメータ数値項目08
				+"(WM0000PARAMETER.ParaInt09) as ParaInt09,\n"		//パラメータ数値項目09
				+"(WM0000PARAMETER.ParaInt10) as ParaInt10,\n"		//パラメータ数値項目10
				+"(WM0000PARAMETER.EntryDate) as EntryDate,\n"		//登録日
				+"(WM0000PARAMETER.UpdateDate) as UpdateDate,\n"	//更新日
				+"(WM0000PARAMETER.EntryUser) as EntryUser,\n"		//登録者
				+"(WM0000PARAMETER.UpdateUser) as UpdateUser\n"		//更新者
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WM0000PARAMETER"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST \n"
				+ " on("+A00000Main.MySqlDefaultSchemaWANKO+".WM0000PARAMETER.ClWh="+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST \n"
				+ " on("+A00000Main.MySqlDefaultSchemaWANKO+".WM0000PARAMETER.ClCd="+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " where 1=1\n";
		
		if(null!=SearchClWh && 0<SearchClWh.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchClWh.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ClWh =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchClCd && 0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchClCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ClCd =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaCd && 0<SearchParaCd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaCd =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaCdSeqStr && 0<SearchParaCdSeqStr.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeqStr.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaCdSeq >=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaCdSeqEnd && 0<SearchParaCdSeqEnd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeqEnd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaCdSeq <=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaName && 0<SearchParaName.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaName.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt01 && 0<SearchParaTxt01.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt01.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt01 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt02 && 0<SearchParaTxt02.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt02.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt02 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt03 && 0<SearchParaTxt03.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt03.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt04 && 0<SearchParaTxt04.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt04.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt04 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt05 && 0<SearchParaTxt05.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt05.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt05 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt06 && 0<SearchParaTxt06.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt06.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt06 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt07 && 0<SearchParaTxt07.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt07.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt07 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt08 && 0<SearchParaTxt08.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt08.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt08 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt09 && 0<SearchParaTxt09.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt09.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt09 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt10 && 0<SearchParaTxt10.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt10.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt10 like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchParaInt01Str && 0<SearchParaInt01Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt01 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt02Str && 0<SearchParaInt02Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt02 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt03Str && 0<SearchParaInt03Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt03 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt04Str && 0<SearchParaInt04Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt04 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt05Str && 0<SearchParaInt05Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt05 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt06Str && 0<SearchParaInt06Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt06 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt07Str && 0<SearchParaInt07Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt07 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt08Str && 0<SearchParaInt08Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt08 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt09Str && 0<SearchParaInt09Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt09 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt10Str && 0<SearchParaInt10Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt10 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt01End && 0<SearchParaInt01End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt01 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt02End && 0<SearchParaInt02End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt02 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt03End && 0<SearchParaInt03End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt03 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt04End && 0<SearchParaInt04End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt04 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt05End && 0<SearchParaInt05End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt05 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt06End && 0<SearchParaInt06End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt06 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt07End && 0<SearchParaInt07End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt07 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt08End && 0<SearchParaInt08End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt08 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt09End && 0<SearchParaInt09End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt09 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt10End && 0<SearchParaInt10End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt10 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxtAll && 0<SearchParaTxtAll.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxtAll.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt01 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt02 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt03 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt04 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt05 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt06 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt07 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt08 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt09 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt10 like ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + "order by WM0000PARAMETER.ClWh,WM0000PARAMETER.ClCd,WM0000PARAMETER.ParaCd,WM0000PARAMETER.ParaCdSeq";
		
		//System.out.println(sql);
		if(true==SearchKick) {
			A00010DbConnect.DB_CONN("WANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchClWh && 0<SearchClWh.size()){
					for(int i=0;i<SearchClWh.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClWh.get(i));
					}
				}
				if(null!=SearchClCd && 0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i));
					}
				}
				if(null!=SearchParaCd && 0<SearchParaCd.size()){
					for(int i=0;i<SearchParaCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCd.get(i));
					}
				}
				if(null!=SearchParaCdSeqStr && 0<SearchParaCdSeqStr.size()){
					for(int i=0;i<SearchParaCdSeqStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCdSeqStr.get(i));
					}
				}
				if(null!=SearchParaCdSeqEnd && 0<SearchParaCdSeqEnd.size()){
					for(int i=0;i<SearchParaCdSeqEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCdSeqEnd.get(i));
					}
				}
				if(null!=SearchParaName && 0<SearchParaName.size()){
					for(int i=0;i<SearchParaName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaName.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt01 && 0<SearchParaTxt01.size()){
					for(int i=0;i<SearchParaTxt01.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt01.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt02 && 0<SearchParaTxt02.size()){
					for(int i=0;i<SearchParaTxt02.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt02.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt03 && 0<SearchParaTxt03.size()){
					for(int i=0;i<SearchParaTxt03.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt03.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt04 && 0<SearchParaTxt04.size()){
					for(int i=0;i<SearchParaTxt04.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt04.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt05 && 0<SearchParaTxt05.size()){
					for(int i=0;i<SearchParaTxt05.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt05.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt06 && 0<SearchParaTxt06.size()){
					for(int i=0;i<SearchParaTxt06.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt06.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt07 && 0<SearchParaTxt07.size()){
					for(int i=0;i<SearchParaTxt07.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt07.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt08 && 0<SearchParaTxt08.size()){
					for(int i=0;i<SearchParaTxt08.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt08.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt09 && 0<SearchParaTxt09.size()){
					for(int i=0;i<SearchParaTxt09.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt09.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt10 && 0<SearchParaTxt10.size()){
					for(int i=0;i<SearchParaTxt10.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt10.get(i)+"%");
					}
				}
				
				if(null!=SearchParaInt01Str && 0<SearchParaInt01Str.size()){
					for(int i=0;i<SearchParaInt01Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt01Str.get(i));
					}
				}
				if(null!=SearchParaInt02Str && 0<SearchParaInt02Str.size()){
					for(int i=0;i<SearchParaInt02Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt02Str.get(i));
					}
				}
				if(null!=SearchParaInt03Str && 0<SearchParaInt03Str.size()){
					for(int i=0;i<SearchParaInt03Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt03Str.get(i));
					}
				}
				if(null!=SearchParaInt04Str && 0<SearchParaInt04Str.size()){
					for(int i=0;i<SearchParaInt04Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt04Str.get(i));
					}
				}
				if(null!=SearchParaInt05Str && 0<SearchParaInt05Str.size()){
					for(int i=0;i<SearchParaInt05Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt05Str.get(i));
					}
				}
				if(null!=SearchParaInt06Str && 0<SearchParaInt06Str.size()){
					for(int i=0;i<SearchParaInt06Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt06Str.get(i));
					}
				}
				if(null!=SearchParaInt07Str && 0<SearchParaInt07Str.size()){
					for(int i=0;i<SearchParaInt07Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt07Str.get(i));
					}
				}
				if(null!=SearchParaInt08Str && 0<SearchParaInt08Str.size()){
					for(int i=0;i<SearchParaInt08Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt08Str.get(i));
					}
					sql = sql + ")";
				}
				if(null!=SearchParaInt09Str && 0<SearchParaInt09Str.size()){
					for(int i=0;i<SearchParaInt09Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt09Str.get(i));
					}
				}
				if(null!=SearchParaInt10Str && 0<SearchParaInt10Str.size()){
					for(int i=0;i<SearchParaInt10Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt10Str.get(i));
					}
				}
				if(null!=SearchParaInt01End && 0<SearchParaInt01End.size()){
					for(int i=0;i<SearchParaInt01End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt01End.get(i));
					}
				}
				if(null!=SearchParaInt02End && 0<SearchParaInt02End.size()){
					for(int i=0;i<SearchParaInt02End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt02End.get(i));
					}
				}
				if(null!=SearchParaInt03End && 0<SearchParaInt03End.size()){
					for(int i=0;i<SearchParaInt03End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt03End.get(i));
					}
				}
				if(null!=SearchParaInt04End && 0<SearchParaInt04End.size()){
					for(int i=0;i<SearchParaInt04End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt04End.get(i));
					}
				}
				if(null!=SearchParaInt05End && 0<SearchParaInt05End.size()){
					for(int i=0;i<SearchParaInt05End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt05End.get(i));
					}
				}
				if(null!=SearchParaInt06End && 0<SearchParaInt06End.size()){
					for(int i=0;i<SearchParaInt06End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt06End.get(i));
					}
				}
				if(null!=SearchParaInt07End && 0<SearchParaInt07End.size()){
					for(int i=0;i<SearchParaInt07End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt07End.get(i));
					}
				}
				if(null!=SearchParaInt08End && 0<SearchParaInt08End.size()){
					for(int i=0;i<SearchParaInt08End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt08End.get(i));
					}
				}
				if(null!=SearchParaInt09End && 0<SearchParaInt09End.size()){
					for(int i=0;i<SearchParaInt09End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt09End.get(i));
					}
				}
				if(null!=SearchParaInt10End && 0<SearchParaInt10End.size()){
					for(int i=0;i<SearchParaInt10End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt10End.get(i));
					}
				}
				if(null!=SearchParaTxtAll && 0<SearchParaTxtAll.size()){
					for(int i=0;i<SearchParaTxtAll.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][RtParameterMstWankoRt().length];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClWh"			)){rt[counter][ColClWh]		="";}else{rt[counter][ColClWh]			=rset01.getString("ClWh");}			//担当倉庫コード
					if(null==rset01.getString("WHName"			)){rt[counter][ColWHName]		="";}else{rt[counter][ColWHName]		=rset01.getString("WHName");}		//担当倉庫名
					if(null==rset01.getString("ClCd"			)){rt[counter][ColClCd]		="";}else{rt[counter][ColClCd]			=rset01.getString("ClCd");}			//荷主コード
					if(null==rset01.getString("CLName01"		)){rt[counter][ColCLName01]	="";}else{rt[counter][ColCLName01]		=rset01.getString("CLName01");}		//荷主名
					if(null==rset01.getString("ParaCd"			)){rt[counter][ColParaCd]		="";}else{rt[counter][ColParaCd]		=rset01.getString("ParaCd");}		//パラメータコード
					rt[counter][ColParaCdSeq]	=rset01.getInt("ParaCdSeq");		//ナンバリング
					if(null==rset01.getString("ParaName"		)){rt[counter][ColParaName]	="";}else{rt[counter][ColParaName]		=rset01.getString("ParaName");}		//パラメータ名
					if(null==rset01.getString("ParaTxt01"		)){rt[counter][ColParaTxt01]	="";}else{rt[counter][ColParaTxt01]	=rset01.getString("ParaTxt01");}	//パラメータテキスト項目01
					if(null==rset01.getString("ParaTxt02"		)){rt[counter][ColParaTxt02]	="";}else{rt[counter][ColParaTxt02]	=rset01.getString("ParaTxt02");}	//パラメータテキスト項目02
					if(null==rset01.getString("ParaTxt03"		)){rt[counter][ColParaTxt03]	="";}else{rt[counter][ColParaTxt03]	=rset01.getString("ParaTxt03");}	//パラメータテキスト項目03
					if(null==rset01.getString("ParaTxt04"		)){rt[counter][ColParaTxt04]	="";}else{rt[counter][ColParaTxt04]	=rset01.getString("ParaTxt04");}	//パラメータテキスト項目04
					if(null==rset01.getString("ParaTxt05"		)){rt[counter][ColParaTxt05]	="";}else{rt[counter][ColParaTxt05]	=rset01.getString("ParaTxt05");}	//パラメータテキスト項目05
					if(null==rset01.getString("ParaTxt06"		)){rt[counter][ColParaTxt06]	="";}else{rt[counter][ColParaTxt06]	=rset01.getString("ParaTxt06");}	//パラメータテキスト項目06
					if(null==rset01.getString("ParaTxt07"		)){rt[counter][ColParaTxt07]	="";}else{rt[counter][ColParaTxt07]	=rset01.getString("ParaTxt07");}	//パラメータテキスト項目07
					if(null==rset01.getString("ParaTxt08"		)){rt[counter][ColParaTxt08]	="";}else{rt[counter][ColParaTxt08]	=rset01.getString("ParaTxt08");}	//パラメータテキスト項目08
					if(null==rset01.getString("ParaTxt09"		)){rt[counter][ColParaTxt09]	="";}else{rt[counter][ColParaTxt09]	=rset01.getString("ParaTxt09");}	//パラメータテキスト項目09
					if(null==rset01.getString("ParaTxt10"		)){rt[counter][ColParaTxt10]	="";}else{rt[counter][ColParaTxt10]	=rset01.getString("ParaTxt10");}	//パラメータテキスト項目10
					rt[counter][ColParaInt01]	=rset01.getInt("ParaInt01");	//パラメータ数値項目01
					rt[counter][ColParaInt02]	=rset01.getInt("ParaInt02");	//パラメータ数値項目02
					rt[counter][ColParaInt03]	=rset01.getInt("ParaInt03");	//パラメータ数値項目03
					rt[counter][ColParaInt04]	=rset01.getInt("ParaInt04");	//パラメータ数値項目04
					rt[counter][ColParaInt05]	=rset01.getInt("ParaInt05");	//パラメータ数値項目05
					rt[counter][ColParaInt06]	=rset01.getInt("ParaInt06");	//パラメータ数値項目06
					rt[counter][ColParaInt07]	=rset01.getInt("ParaInt07");	//パラメータ数値項目07
					rt[counter][ColParaInt08]	=rset01.getInt("ParaInt08");	//パラメータ数値項目08
					rt[counter][ColParaInt09]	=rset01.getInt("ParaInt09");	//パラメータ数値項目09
					rt[counter][ColParaInt10]	=rset01.getInt("ParaInt10");	//パラメータ数値項目10
					if(null==rset01.getTimestamp("EntryDate"	)){rt[counter][ColEntryDate]	="";}else{rt[counter][ColEntryDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//登録日
					if(null==rset01.getTimestamp("UpdateDate"	)){rt[counter][ColEntryDate]	="";}else{rt[counter][ColUpdateDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//更新日
					if(null==rset01.getString("EntryUser"		)){rt[counter][ColEntryUser]	="";}else{rt[counter][ColEntryUser]	=rset01.getString("EntryUser");}	//登録者
					if(null==rset01.getString("UpdateUser"		)){rt[counter][ColUpdateUser]	="";}else{rt[counter][ColUpdateUser]	=rset01.getString("UpdateUser");}	//更新者
					counter=counter+1;
				}
				
				if(rset01!=null){rset01.close();}
				if(stmt01!=null){stmt01.close();}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			A00010DbConnect.close();
		}
		
		
		
		
		
		return rt;
	}
}