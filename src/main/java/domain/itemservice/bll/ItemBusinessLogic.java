package domain.itemservice.bll;

import domain.itemservice.bll.bo.ItemAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityBo;

public interface ItemBusinessLogic {
    ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo);
}