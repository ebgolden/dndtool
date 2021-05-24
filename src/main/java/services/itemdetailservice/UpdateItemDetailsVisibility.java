package services.itemdetailservice;

public interface UpdateItemDetailsVisibility {
    /**
     * Returns a ItemDetailsVisibilityResponse containing a visibility map.
     * Accepts a Item object, a visibility map, and a Player object in a
     * ItemDetailsVisibilityRequest.
     * @param itemDetailsVisibilityRequest ItemDetailsVisibilityRequest
     *                                     containing Item object,
     *                                     visibility map, and Player
     *                                     object
     * @return ItemDetailsVisibilityResponse containing visibility map
     */
    ItemDetailsVisibilityResponse getItemDetailsVisibilityResponse(ItemDetailsVisibilityRequest itemDetailsVisibilityRequest);
}