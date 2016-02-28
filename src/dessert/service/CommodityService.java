package dessert.service;

import java.util.Date;
import java.util.List;

import dessert.rvo.commodity.InventoryRVO;

public interface CommodityService {
    public List<InventoryRVO> getByNameandDate(String name,Date date);
}