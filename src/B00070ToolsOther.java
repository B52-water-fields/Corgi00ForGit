import java.math.BigDecimal;

public class B00070ToolsOther{
	//その他のツール達似たようなツールが増えたら切り出し
	
	//税込み合計金額→金額・税率（パーセント10%の場合10）を受け取って内税計算し、税別金額・消費税を返却する
	public static int[] InsideTaxCalc(int TotalCost,int TaxRate) {
		int[] rt = new int[2];
		BigDecimal GetTotalCost = new BigDecimal(""+TotalCost);
		BigDecimal GetTaxRate = new BigDecimal(""+TaxRate);
		BigDecimal Hundred = new BigDecimal("100");
		BigDecimal GetConsumptionTax = ((GetTotalCost.divide((Hundred.add(GetTaxRate)),7,BigDecimal.ROUND_HALF_UP)).multiply(GetTaxRate)).setScale(0, BigDecimal.ROUND_HALF_UP);
		BigDecimal GetWithOutTax = GetTotalCost.subtract(GetConsumptionTax);
		rt[0] = GetWithOutTax.intValue();
		rt[1] = GetConsumptionTax.intValue();
		return rt;
	}
	//税込み合計金額→金額・税率（パーセント10%の場合10）を受け取って外税計算し、税別金額・消費税を返却する
	public static int[] OutsideTaxCalc(int TotalCost,int TaxRate) {
		int[] rt = new int[2];
		BigDecimal GetTotalCost = new BigDecimal(""+TotalCost);
		BigDecimal GetTaxRate = new BigDecimal(""+TaxRate);
		BigDecimal Hundred = new BigDecimal("100");
		BigDecimal GetConsumptionTax = (GetTotalCost.multiply(GetTaxRate)).divide(Hundred,0,BigDecimal.ROUND_DOWN);
		BigDecimal GetWithOutTax = GetTotalCost;
		rt[0] = GetWithOutTax.intValue();
		rt[1] = GetConsumptionTax.intValue();
		return rt;
	}
}