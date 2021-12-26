package com.ebgolden.domain.itemservice;

public interface GetUpdatedItem {
    /**
     * Returns a UpdatedItemResponse containing a Item object.
     * Accepts a Item object and a Player object in a
     * UpdatedItemRequest.
     * @param updatedItemRequest UpdatedItemRequest containing
     *                           Item object and Player object
     * @return UpdatedItemResponse containing Item object
     */
    UpdatedItemResponse getUpdatedItemResponse(UpdatedItemRequest updatedItemRequest);
}