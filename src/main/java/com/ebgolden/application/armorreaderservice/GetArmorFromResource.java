package application.armorreaderservice;

public interface GetArmorFromResource {
    /**
     * Returns a ArmorFromResourceResponse containing an Armor array.
     * Accepts a armorType String in a ArmorFromResourceRequest.
     * @param armorFromResourceRequest ArmorFromResourceRequest
     *                                 containing armorType String
     * @return ArmorFromResourceResponse containing Armor array
     */
    ArmorFromResourceResponse getArmorFromResourceResponse(ArmorFromResourceRequest armorFromResourceRequest);
}