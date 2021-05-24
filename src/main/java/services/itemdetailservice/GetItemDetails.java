package services.itemdetailservice;

public interface GetItemDetails {
    /**
     * Returns a ItemDetailsResponse containing a Item object.
     * Accepts a Item object and a Player object in a
     * ItemDetailsRequest.
     * @param itemDetailsRequest ItemDetailsRequest containing
     *                           Item object and Player object
     * @return ItemDetailsResponse containing Item object
     */
    ItemDetailsResponse getItemDetailsResponse(ItemDetailsRequest itemDetailsRequest);
}