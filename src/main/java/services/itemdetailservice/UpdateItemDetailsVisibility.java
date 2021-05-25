package services.itemdetailservice;

public interface UpdateItemDetailsVisibility {
    /**
     * Returns a ItemDetailsVisibilityResponse containing a Visibility map.
     * Accepts a Item object, a Visibility map, and a Player object in a
     * ItemDetailsVisibilityRequest.
     * @param itemDetailsVisibilityRequest ItemDetailsVisibilityRequest
     *                                     containing Item object,
     *                                     Visibility map, and Player
     *                                     object
     * @return ItemDetailsVisibilityResponse containing Visibility map
     */
    ItemDetailsVisibilityResponse getItemDetailsVisibilityResponse(ItemDetailsVisibilityRequest itemDetailsVisibilityRequest);
}