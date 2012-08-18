package nc.vo.mallpos.sale;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 应收款项
 * 
 * @author chenjun
 * @date 2011-11-22 下午02:54:55
 */
public class MPOS_ReceivablesVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	private String pk_receivables;

	private String pk_cubasdoc;

	private UFDate billdate;

	private UFDouble amount;

	private String billstatus;

	private String pk_busibill;
	
	
	//----------------------------------
	
	private String custcode;
	private String custname;
	
	private String strbillstatus;
	
	
	//----------------------------------

	/**
	 * @return the amount
	 */
	public UFDouble getAmount()
	{
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(UFDouble amount)
	{
		this.amount = amount;
	}

	/**
	 * @return the billdate
	 */
	public UFDate getBilldate()
	{
		return billdate;
	}

	/**
	 * @param billdate the billdate to set
	 */
	public void setBilldate(UFDate billdate)
	{
		this.billdate = billdate;
	}

	/**
	 * @return the billstatus
	 */
	public String getBillstatus()
	{
		return billstatus;
	}

	/**
	 * @param billstatus the billstatus to set
	 */
	public void setBillstatus(String billstatus)
	{
		this.billstatus = billstatus;
	}

	/**
	 * @return the pk_busibill
	 */
	public String getPk_busibill()
	{
		return pk_busibill;
	}

	/**
	 * @param pk_busibill the pk_busibill to set
	 */
	public void setPk_busibill(String pk_busibill)
	{
		this.pk_busibill = pk_busibill;
	}

	/**
	 * @return the pk_cubasdoc
	 */
	public String getPk_cubasdoc()
	{
		return pk_cubasdoc;
	}

	/**
	 * @param pk_cubasdoc the pk_cubasdoc to set
	 */
	public void setPk_cubasdoc(String pk_cubasdoc)
	{
		this.pk_cubasdoc = pk_cubasdoc;
	}

	/**
	 * @return the pk_receivables
	 */
	public String getPk_receivables()
	{
		return pk_receivables;
	}

	/**
	 * @param pk_receivables the pk_receivables to set
	 */
	public void setPk_receivables(String pk_receivables)
	{
		this.pk_receivables = pk_receivables;
	}

	@Override
	public String getPKFieldName()
	{
		return "pk_receivables";
	}

	@Override
	public String getParentPKFieldName()
	{
		return null;
	}

	@Override
	public String getTableName()
	{
		return "mpos_receivables";
	}

	/**
	 * @return the custcode
	 */
	public String getCustcode()
	{
		return custcode;
	}

	/**
	 * @param custcode the custcode to set
	 */
	public void setCustcode(String custcode)
	{
		this.custcode = custcode;
	}

	/**
	 * @return the custname
	 */
	public String getCustname()
	{
		return custname;
	}

	/**
	 * @param custname the custname to set
	 */
	public void setCustname(String custname)
	{
		this.custname = custname;
	}

	/**
	 * @return the strbillstatus
	 */
	public String getStrbillstatus()
	{
		return strbillstatus;
	}

	/**
	 * @param strbillstatus the strbillstatus to set
	 */
	public void setStrbillstatus(String strbillstatus)
	{
		this.strbillstatus = strbillstatus;
	}

}
