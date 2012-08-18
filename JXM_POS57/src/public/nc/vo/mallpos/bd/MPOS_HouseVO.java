package nc.vo.mallpos.bd;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;

/**
 * 楼盘档案
 * 
 * @author cj
 * @date 2012-8-7 下午12:29:08
 */
public class MPOS_HouseVO extends SuperVO
{
	private static final long serialVersionUID = 1L;

	private String pk_houses;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "楼盘编码"))
	private String house_code;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "地区分类"))
	private String pk_areacl;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "楼盘名称"))
	private String house_name;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "楼盘地址"))
	private String address;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "联系电话"))
	private String phone;

	/**
	 * <p>
	 * 取得父VO主键字段.
	 * <p>
	 * 创建日期:2012-08-07 11:51:22
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName()
	{
		return "pk_areacl";
	}

	/**
	 * <p>
	 * 取得表主键.
	 * <p>
	 * 创建日期:2012-08-07 11:51:22
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName()
	{
		return "pk_houses";
	}

	/**
	 * <p>
	 * 返回表名称.
	 * <p>
	 * 创建日期:2012-08-07 11:51:22
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName()
	{
		return "mpos_houses";
	}

	/**
	 * 按照默认方式创建构造子.
	 * 
	 * 创建日期:2012-08-07 11:51:22
	 */
	public MPOS_HouseVO()
	{
		super();
	}

	/**
	 * 获取house_name
	 * 
	 * @return house_name house_name
	 */
	public String getHouse_name()
	{
		return house_name;
	}

	/**
	 * 设置house_name
	 * 
	 * @param house_name
	 *            house_name
	 */
	public void setHouse_name(String house_name)
	{
		this.house_name = house_name;
	}

	/**
	 * 获取pk_areacl
	 * 
	 * @return pk_areacl pk_areacl
	 */
	public String getPk_areacl()
	{
		return pk_areacl;
	}

	/**
	 * 设置pk_areacl
	 * 
	 * @param pk_areacl
	 *            pk_areacl
	 */
	public void setPk_areacl(String pk_areacl)
	{
		this.pk_areacl = pk_areacl;
	}

	/**
	 * 获取pk_houses
	 * 
	 * @return pk_houses pk_houses
	 */
	public String getPk_houses()
	{
		return pk_houses;
	}

	/**
	 * 设置pk_houses
	 * 
	 * @param pk_houses
	 *            pk_houses
	 */
	public void setPk_houses(String pk_houses)
	{
		this.pk_houses = pk_houses;
	}

	/**
	 * 获取address
	 * @return address address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置address
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取phone
	 * @return phone phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置phone
	 * @param phone phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取house_code
	 * @return house_code house_code
	 */
	public String getHouse_code() {
		return house_code;
	}

	/**
	 * 设置house_code
	 * @param house_code house_code
	 */
	public void setHouse_code(String house_code) {
		this.house_code = house_code;
	}
}
