package services.itemdetailservice.bll;

import services.itemdetailservice.bll.bo.ItemAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityBo;

public interface ItemDetailBusinessLogic {
    ItemDetailsAndVisibilityBo getItemDetailsAndVisibilityBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemDetailsAndVisibilityBo getItemDetailsAndVisibilityBo(ItemDetailsAndVisibilityAndPlayerBo itemDetailsAndVisibilityAndPlayerBo);
}