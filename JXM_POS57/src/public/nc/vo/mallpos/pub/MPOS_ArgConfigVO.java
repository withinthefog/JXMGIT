package nc.vo.mallpos.pub;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;

/**
 * 参数配置表
 * 
 * @author chenjun
 * @date 2011-11-19 下午09:04:18
 */
public class MPOS_ArgConfigVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	private String pk_argconfig;

	/**
	 * 付款周期
	 */
	private int pay_cycle;

	/**
	 * 首次付款日期
	 */
	private UFDate first_paydate;

	/**
	 * 付款日期
	 */
	private UFDate paydate;
	
	/**
	 * 默认制单人
	 */
	private String pk_userid;

	@Override
	public String getPKFieldName()
	{
		return "pk_argconfig";
	}

	@Override
	public String getParentPKFieldName()
	{
		return null;
	}

	@Override
	public String getTableName()
	{
		return "mpos_argconfig";
	}

	/**
	 * @return the first_paydate
	 */
	public UFDate getFirst_paydate()
	{
		return first_paydate;
	}

	/**
	 * @param first_paydate
	 *            the first_paydate to set
	 */
	public void setFirst_paydate(UFDate first_paydate)
	{
		this.first_paydate = first_paydate;
	}

	/**
	 * @return the pay_cycle
	 */
	public int getPay_cycle()
	{
		return pay_cycle;
	}

	/**
	 * @param pay_cycle
	 *            the pay_cycle to set
	 */
	public void setPay_cycle(int pay_cycle)
	{
		this.pay_cycle = pay_cycle;
	}

	/**
	 * @return the paydate
	 */
	public UFDate getPaydate()
	{
		return paydate;
	}

	/**
	 * @param paydate
	 *            the paydate to set
	 */
	public void setPaydate(UFDate paydate)
	{
		this.paydate = paydate;
	}

	/**
	 * @return the pk_argconfig
	 */
	public String getPk_argconfig()
	{
		return pk_argconfig;
	}

	/**
	 * @param pk_argconfig
	 *            the pk_argconfig to set
	 */
	public void setPk_argconfig(String pk_argconfig)
	{
		this.pk_argconfig = pk_argconfig;
	}

	/**
	 * @return the pk_userid
	 */
	public String getPk_userid()
	{
		return pk_userid;
	}

	/**
	 * @param pk_userid the pk_userid to set
	 */
	public void setPk_userid(String pk_userid)
	{
		this.pk_userid = pk_userid;
	}

}
