/**
 * 
 */
package nc.ui.mallpos.vipdoc;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubVipItf;
import nc.ui.trade.bsdelegate.BDBusinessDelegator;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipdocBusinessDelegator extends BDBusinessDelegator {

	/**
	 * 
	 */
	public MPOS_VipdocBusinessDelegator() {
		// TODO Auto-generated constructor stub
		super();
	}

	private MPOS_PubVipItf pubVipItf =null;
	public MPOS_PubVipItf getPubVipItf(){
		if(pubVipItf==null){
			pubVipItf = (MPOS_PubVipItf) NCLocator.getInstance().lookup(MPOS_PubVipItf.class);
		}
		return pubVipItf;
	}
	@Override
	public CircularlyAccessibleValueObject[] queryBodyAllData(Class voClass,
			String billType, String key, String strWhere) throws Exception {
		// TODO Auto-generated method stub
		SuperVO vo = (SuperVO) voClass.newInstance();
        String strSql = null;
        if (vo.getParentPKFieldName() != null)
            strSql = "(" + vo.getParentPKFieldName() + "='" + key + "')";
        if (strWhere != null && strWhere.length() != 0)
            if (strSql != null)
                strSql = (strWhere) + " and " + strSql;
            else
                strSql = strWhere;
        return getPubVipItf().queryForBD(strSql);
	}
	@Override
	public SuperVO[] queryHeadAllData(Class headVoClass, String strBillType,
			String strWhere) throws Exception {
		// TODO Auto-generated method stub
		return super.queryHeadAllData(headVoClass, strBillType, strWhere);
	}
	
	

}
