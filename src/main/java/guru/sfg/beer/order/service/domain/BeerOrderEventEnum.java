package guru.sfg.beer.order.service.domain;

/**
 * @author Alessandro Arosio - 20/02/2020 19:01
 */
public enum BeerOrderEventEnum {
    VALIDATE_ORDER, VALIDATION_PASSED, VALIDATION_FAILED,
    ALLOCATE_ORDER, ALLOCATION_SUCCESS, ALLOCATION_NO_INVENTORY, ALLOCATION_FAILED,
    BEERODER_PICKED_UP
}
