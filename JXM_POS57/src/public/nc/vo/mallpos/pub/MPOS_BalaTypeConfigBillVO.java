package nc.vo.mallpos.pub;

import java.util.Arrays;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

/**
 * 结算方式配置信息聚合类
 * @author cj
 * @date 2012-8-7 下午03:59:34
 */
public class MPOS_BalaTypeConfigBillVO extends HYBillVO{

	private static final long serialVersionUID = 1L;
	
	public CircularlyAccessibleValueObject[] getChildrenVO() {
		return (MPOS_BalaTypeConfigVO[]) super.getChildrenVO();
	}

	public CircularlyAccessibleValueObject getParentVO() {
		return super.getParentVO();
	}

	public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
		if( children == null || children.length == 0 ){
			super.setChildrenVO(null);
		}
		else{
			super.setChildrenVO((CircularlyAccessibleValueObject[]) Arrays.asList(children).toArray(new MPOS_BalaTypeConfigVO[0]));
		}
	}

	public void setParentVO(CircularlyAccessibleValueObject parent) {
		super.setParentVO(parent);
	}


}
