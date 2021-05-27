package services.itemservice.bll;

import services.itemservice.bll.bo.ItemAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityBo;

public interface ItemBusinessLogic {
    ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo);
}