package dessert.service;

import java.util.Date;
import java.util.List;

import dessert.pvo.CartItemPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.commodity.InventoryRVO;

public interface CommodityService {
    public List<InventoryRVO> getByNameandDate(String name,Date date);
    public ResultVO reservate(List<CartItemPVO> pvos,String id);
}
