package nc.vo.mallpos.bd;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;

/**
 * ¥�̵���
 * 
 * @author cj
 * @date 2012-8-7 ����12:29:08
 */
public class MPOS_HouseVO extends SuperVO
{
	private static final long serialVersionUID = 1L;

	private String pk_houses;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "¥�̱���"))
	private String house_code;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private String pk_areacl;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "¥������"))
	private String house_name;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "¥�̵�ַ"))
	private String address;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��ϵ�绰"))
	private String phone;

	/**
	 * <p>
	 * ȡ�ø�VO�����ֶ�.
	 * <p>
	 * ��������:2012-08-07 11:51:22
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName()
	{
		return "pk_areacl";
	}

	/**
	 * <p>
	 * ȡ�ñ�����.
	 * <p>
	 * ��������:2012-08-07 11:51:22
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName()
	{
		return "pk_houses";
	}

	/**
	 * <p>
	 * ���ر�����.
	 * <p>
	 * ��������:2012-08-07 11:51:22
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName()
	{
		return "mpos_houses";
	}

	/**
	 * ����Ĭ�Ϸ�ʽ����������.
	 * 
	 * ��������:2012-08-07 11:51:22
	 */
	public MPOS_HouseVO()
	{
		super();
	}

	/**
	 * ��ȡhouse_name
	 * 
	 * @return house_name house_name
	 */
	public String getHouse_name()
	{
		return house_name;
	}

	/**
	 * ����house_name
	 * 
	 * @param house_name
	 *            house_name
	 */
	public void setHouse_name(String house_name)
	{
		this.house_name = house_name;
	}

	/**
	 * ��ȡpk_areacl
	 * 
	 * @return pk_areacl pk_areacl
	 */
	public String getPk_areacl()
	{
		return pk_areacl;
	}

	/**
	 * ����pk_areacl
	 * 
	 * @param pk_areacl
	 *            pk_areacl
	 */
	public void setPk_areacl(String pk_areacl)
	{
		this.pk_areacl = pk_areacl;
	}

	/**
	 * ��ȡpk_houses
	 * 
	 * @return pk_houses pk_houses
	 */
	public String getPk_houses()
	{
		return pk_houses;
	}

	/**
	 * ����pk_houses
	 * 
	 * @param pk_houses
	 *            pk_houses
	 */
	public void setPk_houses(String pk_houses)
	{
		this.pk_houses = pk_houses;
	}

	/**
	 * ��ȡaddress
	 * @return address address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * ����address
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * ��ȡphone
	 * @return phone phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * ����phone
	 * @param phone phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * ��ȡhouse_code
	 * @return house_code house_code
	 */
	public String getHouse_code() {
		return house_code;
	}

	/**
	 * ����house_code
	 * @param house_code house_code
	 */
	public void setHouse_code(String house_code) {
		this.house_code = house_code;
	}
}
