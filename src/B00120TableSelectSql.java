public class B00120TableSelectSql{
	//指定されたテーブルに対してJoinを考慮しないSelect文を生成する
	//Select文を動的に作るクラスを作るための補助ツール
	
	public static void TableSelectSql() {
		String[][] Tgt = new String[1][2];
		Tgt[0][0] = "NYANKO";
		Tgt[0][1] = "KT0010_OKURI_HD";
		
		for(int i=0;i<Tgt.length;i++) {
			/*
			TableSelectSqlCore(Tgt[i][0],Tgt[i][1]);
			*/
		}
	}
	
	public static void TableSelectSqlCore(String TgtDB,String TgtTable) {
		//対象テーブルのカラム情報取得　開発コピペ用
		String[][] ColumnList = A00050OldDataTableCheck.ColumnList(TgtDB,TgtTable);
		if(0<ColumnList.length) {
			String sql = "\"select \"\n+\"";
			
			for(int i=0;i<ColumnList.length;i++) {
				if(0<i) {sql = sql + "+\",";}
				sql = sql + " ("+ColumnList[i][ 2]+"."+ColumnList[i][ 3]+") as "+ColumnList[i][ 3]+"\"\n";
			}
			
			sql = sql + "+\" from "+ColumnList[0][ 1]+"."+ColumnList[0][ 2]+"\"\n";
					sql = sql + "+\" where 1=1\"\n";
		
			System.out.println(sql);
			
			System.out.println("***********************************************");
			
			for(int i=0;i<ColumnList.length;i++) {
				switch(ColumnList[i][ 7]) {
					case "varchar":
						System.out.println(",ArrayList<String> Search"+ColumnList[i][ 3]);
						break;
					case "int":
						System.out.println(",ArrayList<Integer> Search"+ColumnList[i][ 3]+"Min");
						System.out.println(",ArrayList<Integer> Search"+ColumnList[i][ 3]+"Max");
						break;
					case "datetime":
						System.out.println(",ArrayList<String> Search"+ColumnList[i][ 3]+"From");
						System.out.println(",ArrayList<String> Search"+ColumnList[i][ 3]+"To");
						break;
					case "float":
						System.out.println(",ArrayList<Integer> Search"+ColumnList[i][ 3]+"Min");
						System.out.println(",ArrayList<Integer> Search"+ColumnList[i][ 3]+"Max");
						break;
					default:
						System.out.println(",ArrayList<String> Search"+ColumnList[i][ 3]);
						break;
				}
			}

			System.out.println("***********************************************");
			for(int i=0;i<ColumnList.length;i++) {
				switch(ColumnList[i][ 7]) {
					case "varchar":
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+" && 0<Search"+ColumnList[i][ 3]+".size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+".size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" like '%\"+Search"+ColumnList[i][ 3]+".get(i)+\"%'\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");
						break;
					case "int":
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+"Min && 0<Search"+ColumnList[i][ 3]+"Min.size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+"Min.size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" >= \"+Search"+ColumnList[i][ 3]+"Min.get(i)+\"\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");

						System.out.println("");
						
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+"Max && 0<Search"+ColumnList[i][ 3]+"Max.size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+"Max.size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" <= \"+Search"+ColumnList[i][ 3]+"Max.get(i)+\"\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");
						break;
					case "datetime":
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+"From && 0<Search"+ColumnList[i][ 3]+"From.size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+"From.size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" >= '\"+Search"+ColumnList[i][ 3]+"From.get(i)+\"'\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");

						System.out.println("");
						
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+"To && 0<Search"+ColumnList[i][ 3]+"To.size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+"To.size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" < '\"+Search"+ColumnList[i][ 3]+"To.get(i)+\"'\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");
						break;
					case "float":
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+"Min && 0<Search"+ColumnList[i][ 3]+"Min.size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+"Min.size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" >= \"+Search"+ColumnList[i][ 3]+"Min.get(i)+\"\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");

						System.out.println("");
						
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+"Max && 0<Search"+ColumnList[i][ 3]+"Max.size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+"Max.size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" <= \"+Search"+ColumnList[i][ 3]+"Max.get(i)+\"\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");
						break;
					default:
						System.out.println("ArrayList<String> Search"+ColumnList[i][ 3]);
						System.out.println("if(null!= Search"+ColumnList[i][ 3]+"&& 0<Search"+ColumnList[i][ 3]+".size()){;");
						System.out.println("sql = sql + \" and( \";");
						System.out.println("	for(int i=0;i<Search"+ColumnList[i][ 3]+".size();i++){");
						System.out.println("		if(0<i){sql = sql + \" or \";}");
						System.out.println("		sql = sql + \""+ColumnList[0][ 1]+"."+ColumnList[i][ 3] +" like '%\"+Search"+ColumnList[i][ 3]+".get(i)+\"%'\";");
						System.out.println("	}");
						System.out.println("sql = sql + \" ) \";");
						System.out.println("}");
						break;
				}
			}
			
			
			
			System.out.println("***********************************************");
			for(int i=0;i<ColumnList.length;i++) {
				switch(ColumnList[i][ 7]) {
					case "varchar":
						System.out.println("if(null==rset01.getString(\""+ColumnList[i][ 3]+"\")){rt[counter]["+i+"] = \"\";} else {rt[counter]["+i+"] = rset01.getString(\""+ColumnList[i][ 3]+"\");}");
						break;
					case "int":
						System.out.println("rt[counter]["+i+"] = rset01.getInt(\""+ColumnList[i][ 3]+"\");");
						break;
					case "datetime":
						System.out.println("if(null==rset01.getTimestamp(\""+ColumnList[i][ 3]+"\")){rt[counter]["+i+"]=\"\";}else{rt[counter]["+i+"]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp(\""+ColumnList[i][ 3]+"\"))[1];}");
						break;
					case "float":
						System.out.println("rt[counter]["+i+"] = rset01.getFloat(\""+ColumnList[i][ 3]+"\");");
						break;
					default:
						System.out.println("if(null==rset01.getString(\""+ColumnList[i][ 3]+"\")){rt[counter]["+i+"] = \"\";} else {rt[counter]["+i+"] = rset01.getString(\""+ColumnList[i][ 3]+"\");}");
						break;
				}
			}
		}
	}
}

/*
if(null==rset01.getString("TABLE_CATALOG")) 			{rt[counter][ 0] = "null";} else {rt[counter][ 0] = rset01.getString("TABLE_CATALOG");}
if(null==rset01.getString("TABLE_SCHEMA"))				{rt[counter][ 1] = "null";} else {rt[counter][ 1] = rset01.getString("TABLE_SCHEMA");}
if(null==rset01.getString("TABLE_NAME"))				{rt[counter][ 2] = "null";} else {rt[counter][ 2] = rset01.getString("TABLE_NAME");}
if(null==rset01.getString("COLUMN_NAME"))				{rt[counter][ 3] = "null";} else {rt[counter][ 3] = rset01.getString("COLUMN_NAME");}
if(null==rset01.getString("ORDINAL_POSITION"))			{rt[counter][ 4] = "null";} else {rt[counter][ 4] = rset01.getString("ORDINAL_POSITION");}
if(null==rset01.getString("COLUMN_DEFAULT"))			{rt[counter][ 5] = "null";} else {rt[counter][ 5] = rset01.getString("COLUMN_DEFAULT");}
if(null==rset01.getString("IS_NULLABLE"))				{rt[counter][ 6] = "null";} else {rt[counter][ 6] = rset01.getString("IS_NULLABLE");}
if(null==rset01.getString("DATA_TYPE"))					{rt[counter][ 7] = "null";} else {rt[counter][ 7] = rset01.getString("DATA_TYPE");}
if(null==rset01.getString("CHARACTER_MAXIMUM_LENGTH"))	{rt[counter][ 8] = "null";} else {rt[counter][ 8] = rset01.getString("CHARACTER_MAXIMUM_LENGTH");}
if(null==rset01.getString("CHARACTER_OCTET_LENGTH"))	{rt[counter][ 9] = "null";} else {rt[counter][ 9] = rset01.getString("CHARACTER_OCTET_LENGTH");}
if(null==rset01.getString("NUMERIC_PRECISION"))			{rt[counter][10] = "null";} else {rt[counter][10] = rset01.getString("NUMERIC_PRECISION");}
if(null==rset01.getString("NUMERIC_SCALE"))				{rt[counter][11] = "null";} else {rt[counter][11] = rset01.getString("NUMERIC_SCALE");}
if(null==rset01.getString("DATETIME_PRECISION"))		{rt[counter][12] = "null";} else {rt[counter][12] = rset01.getString("DATETIME_PRECISION");}
if(null==rset01.getString("CHARACTER_SET_NAME"))		{rt[counter][13] = "null";} else {rt[counter][13] = rset01.getString("CHARACTER_SET_NAME");}
if(null==rset01.getString("COLLATION_NAME"))			{rt[counter][14] = "null";} else {rt[counter][14] = rset01.getString("COLLATION_NAME");}
if(null==rset01.getString("COLUMN_TYPE"))				{rt[counter][15] = "null";} else {rt[counter][15] = rset01.getString("COLUMN_TYPE");}
if(null==rset01.getString("COLUMN_KEY"))				{rt[counter][16] = "null";} else {rt[counter][16] = rset01.getString("COLUMN_KEY");}
if(null==rset01.getString("EXTRA"))						{rt[counter][17] = "null";} else {rt[counter][17] = rset01.getString("EXTRA");}
if(null==rset01.getString("PRIVILEGES"))				{rt[counter][18] = "null";} else {rt[counter][18] = rset01.getString("PRIVILEGES");}
if(null==rset01.getString("COLUMN_COMMENT"))			{rt[counter][19] = "null";} else {rt[counter][19] = rset01.getString("COLUMN_COMMENT");}
if(null==rset01.getString("GENERATION_EXPRESSION"))		{rt[counter][20] = "null";} else {rt[counter][20] = rset01.getString("GENERATION_EXPRESSION");}
*/